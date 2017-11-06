package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.User;
import dao.UserDAOImpl;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserDAOImpl userDAO;
	private Cart cart;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();

		this.userDAO = new UserDAOImpl();
		this.cart = new Cart();
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		java.sql.Date dateOfBirth = null;

		try {
			dateOfBirth = new java.sql.Date(
					(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateOfBirth"))).getTime());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String address = request.getParameter("address");
		System.out.println(username);

		String err = "";
		String url = "/register.jsp";

		if (username.equals("") || password.equals("") || email.equals("") || address.equals("")
				|| fullName.equals("")) {
			err += "Fields cannot be blank!";
		} else {
			if (userDAO.checkUser(username) == true) {
				err += "Account Already Exists!";
			} else {
				Pattern pattenObj = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
				Matcher matcherObj = pattenObj.matcher(email);

				if (!matcherObj.matches()) {
					err += "Please enter a valid email";
				}
			}
		}

		if (err.length() > 0) {
			request.setAttribute("err", err);
		}

		try {
			if (err.length() == 0) {
				HttpSession session = request.getSession();
				session.setAttribute("cart", cart);
				userDAO.addUser(new User(0, username, password, dateOfBirth, gender, email, fullName, address, "2"));
				userDAO.login(username, password);

				Cookie loginCookie = new Cookie("username", username);
				// setting cookie to expiry in 30 mins
				loginCookie.setMaxAge(30 * 60);
				response.addCookie(loginCookie);
				response.sendRedirect("index.jsp");

			} else {
				url = "/register.jsp";
				RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/register.jsp");
		}

	}

}
