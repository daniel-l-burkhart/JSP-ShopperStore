package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.CartItem;
import model.Order;
import model.User;
import dao.OrderDAOImpl;
import dao.ProductDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;

@WebServlet("/ConfirmServlet")
public class ConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAOImpl userDAO = new UserDAOImpl();
	private ProductDAOImpl productDAO = new ProductDAOImpl();
	private OrderDAOImpl historyDAO = new OrderDAOImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfirmServlet() {
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

		String username = request.getParameter("username");
		User u = this.userDAO.getUser(username);
		Calendar calendar = Calendar.getInstance();
		java.sql.Timestamp tdate = new java.sql.Timestamp(calendar.getTime().getTime());

		double total = 0;
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(0);

		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a");

		final String username_mail = "mysqlshopperstore@gmail.com";
		final String password = "mySQLShopper";
		String to = u.getEmail();
		String subject = "Confirm Cart";
		String text = "<strong>Order Details - " + username + " - </strong><i> " + ft.format(dNow) + "</i> <ul>";
		if (cart != null) {
			for (CartItem item : cart.getLineItems()) {

				total = total + (item.getQuantity()
						* this.productDAO.getSingleProductFromID(item.getProduct().getProductCode()).getPrice());

				text += "<li>"
						+ this.productDAO.getSingleProductFromID(item.getProduct().getProductCode()).getProductName()
						+ ": "
						+ nf.format(
								this.productDAO.getSingleProductFromID(item.getProduct().getProductCode()).getPrice())
						+ " </li>";

				Order h = new Order(0, u.getUser_id(), item.getProduct().getProductCode(), tdate, item.getQuantity(),
						(item.getQuantity() * this.productDAO.getSingleProductFromID(item.getProduct().getProductCode())
								.getPrice()));

				this.productDAO.updateQuantity(item.getProduct().getProductCode(), item.getQuantity());

				this.historyDAO.addHistory(h);
			}
		}
		text += "Total Payment: <strong> $" + nf.format(total) + " </strong>";

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

		cart.clearCart();
		request.getSession().setAttribute("cart", cart);
		response.sendRedirect("index.jsp");

	}
}
