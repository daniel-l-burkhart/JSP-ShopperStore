package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import dao.UserDAOImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAOImpl userDAO;
	private Cart cart;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String err = "";

		if (username.equals("") || password.equals("")) {
			err += "Must enter full information!";
		} else {
			if (this.userDAO.login(username, password) == false) {
				err += "Username or password is incorrect";
			}
		}

		if (err.length() > 0) {
			request.setAttribute("err", err);
		}

		String url = "/login.jsp";

		try {
			if (err.length() == 0) {

				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				session.setAttribute("cart", this.cart);

				this.userDAO.login(username, password);
				Cookie loginCookie = new Cookie("username", username);
				loginCookie.setMaxAge(30 * 60);

				response.addCookie(loginCookie);
				response.sendRedirect("index.jsp");
				url = "/index.jsp";

			} else {
				url = "/login.jsp";
				RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/login.jsp");
		}
	}

}
