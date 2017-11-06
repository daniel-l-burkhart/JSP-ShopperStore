package controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAOImpl;
import model.User;

/**
 * Servlet implementation class ResetPassword
 */
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAOImpl userDAO = new UserDAOImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResetPassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");

		String err = "";
		if (username.equals("")) {
			err += "Username cannot be blank!";
		} else {
			if (userDAO.checkUser(username) == false) {
				err += "Username does not exist!";
			}
		}

		if (err.length() > 0) {
			request.setAttribute("err", err);
		}

		String url = "/resetpassword.jsp";
		try {
			if (err.length() == 0) {

				User u = this.userDAO.getUser(username);
				User new_user = new User(u.getUser_id(), username, "passwordreset", u.getDateOfBirth(), u.getGender(),
						u.getEmail(), u.getFullName(), u.getAddress(), u.getRole());

				this.userDAO.updateUser(new_user);
				url = "/login.jsp";

				String mess = "Check email for a new password";
				request.setAttribute("mess", mess);

				final String username_mail = "GMAILENTERHERE@gmail.com";
				final String password = "ENTER-EMAIL-PASSWORD-HERE";

				String to = u.getEmail();
				String subject = "Reset Password";
				String text = "<i>Reset Password</i><br/>";
				text += "<p>User: <strong>";
				text += username;
				text += "</strong></p>";
				text += "<p>New password: <strong>passwordreset</strong></p>";
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

}
