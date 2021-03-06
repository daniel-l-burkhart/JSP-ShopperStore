package controller;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAOImpl;
import model.User;

@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAOImpl userDAO = new UserDAOImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResetPassword() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/index.jsp";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");

		String err = "";
		if (email.equals("")) {
			err += "Email cannot be blank!";
		} else {
			if (userDAO.checkUser(email) == false) {
				err += "Username does not exist!";
			}
		}

		if (err.length() > 0) {
			request.setAttribute("err", err);
		}

		String url = "/resetpassword.jsp";
		try {
			if (err.length() == 0) {

				String newPass = this.getSaltString();

				User u = this.userDAO.getUser(email);
				User new_user = new User(u.getUser_id(), u.getEmail(), newPass, u.getFullName(), u.getPhone(),
						u.getAddress(), u.getRole());

				this.userDAO.updateUser(new_user);
				url = "/login.jsp";

				String mess = "Check email for a new password";
				request.setAttribute("mess", mess);

				final String username_mail = "mySQLShopperStore@gmail.com";
				final String password = "mySQLShopper";

				String to = u.getEmail();
				String subject = "Reset Password";
				String text = "<i>Reset Password</i><br/>";
				text += "<p>User: <strong>";
				text += email;
				text += "</strong></p>";
				text += "<p>New password: <strong>newPass</strong></p>";
				text += "<br/><br/> <p>Copy and paste this password into the login page</p>";
				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");

				Session session_mail = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username_mail, password);
					}
				});

				try {
					Message message = new MimeMessage(session_mail);
					message.setHeader("Content-Type", "text/plain; charset=UTF-8");
					message.setFrom(new InternetAddress(username_mail));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
					message.setSubject(subject);
					message.setContent(text, "text/html; charset=utf-8");
					Transport.send(message);

				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}

			} else {
				url = "/resetpassword.jsp";

			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/resetpassword.jsp");
		}
	}

	private String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 18) {
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

}
