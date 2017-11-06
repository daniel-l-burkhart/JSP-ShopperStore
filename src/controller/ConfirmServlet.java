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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.CartItem;
import model.History;
import model.User;
import dao.HistoryDAOImpl;
import dao.ProductDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;

/**
 * Servlet implementation class ConfirmServlet
 */
public class ConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAOImpl userDAO = new UserDAOImpl();
	private ProductDAOImpl productDAO = new ProductDAOImpl();
	private HistoryDAOImpl historyDAO = new HistoryDAOImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfirmServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		User u = userDAO.getUser(username); // lấy time lưu csdl
		Calendar calendar = Calendar.getInstance();
		java.sql.Timestamp tdate = new java.sql.Timestamp(calendar.getTime().getTime());

		double total = 0;
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(0);

		// lay time gửi mail
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a");

		// xác nhận đơn đặt hàng qua mail
		final String username_mail = "webbanhang1212050@gmail.com";
		final String password = "matkhauwebbanhang";
		String to = u.getEmail();
		String subject = "Confirm Cart";
		String text = "<strong>Đơn Hàng - " + username + " - </strong><i> " + ft.format(dNow) + "</i> <ul>";
		if (cart != null) {
			for (CartItem item : cart.getLineItems()) {

				total = total + (item.getQuantity()
						* this.productDAO.getSingleProductFromID(item.getProduct().getProductCode()).getPrice());

				text += "<li>"
						+ this.productDAO.getSingleProductFromID(item.getProduct().getProductCode()).getProductName()
						+ ": "
						+ nf.format(
								this.productDAO.getSingleProductFromID(item.getProduct().getProductCode()).getPrice())
						+ "VNĐ </li>";

				History h = new History(0, u.getUser_id(), item.getProduct().getProductCode(), tdate,
						item.getQuantity(), (item.getQuantity() * this.productDAO
								.getSingleProductFromID(item.getProduct().getProductCode()).getPrice()));

				historyDAO.addHistory(h);
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
		response.sendRedirect("/shop/index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
