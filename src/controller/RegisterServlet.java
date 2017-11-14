package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAOImpl;
import model.Cart;
import model.User;

@WebServlet("/RegisterServlet")
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String password = request.getParameter("password");

		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");

		String err = "";
		String url = "/register.jsp";

		if (fullName.equals("") || password.equals("") || email.equals("") || address.equals("") || phone.equals("")) {
			err += "Fields cannot be blank!";
		} else if (this.userDAO.checkUser(email) == true) {
			err += "Account Already Exists!";
		} else {
			Pattern pattenObj = Pattern.compile(
					"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			Matcher matcherObj = pattenObj.matcher(email);

			if (!matcherObj.matches()) {
				err += "Please enter a valid email";
			}
		}

		if (err.length() > 0) {
			request.setAttribute("err", err);
		}

		try {
			if (err.length() == 0) {
				HttpSession session = request.getSession();
				session.setAttribute("cart", this.cart);

				User newUser = new User();

				newUser.setEmail(email);
				newUser.setPassword(password);

				newUser.setFullName(fullName);
				newUser.setPhone(phone);
				newUser.setAddress(address);
				newUser.setRole("2");

				this.userDAO.addUser(newUser);

				newUser = this.userDAO.login(newUser.getEmail(), newUser.getPassword());

				session.setAttribute("user", newUser);
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
