package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAOImpl;
import model.User;

/**
 * Servlet implementation class AdminUsers
 */
@WebServlet("/AdminUsers")
public class AdminUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAOImpl userDAO = new UserDAOImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUsers() {
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

		String userID = request.getParameter("userID");

		System.out.println(userID);

		this.userDAO.deleteUserByID(userID);

		String url = "/admin_users.jsp";
		try {

			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
