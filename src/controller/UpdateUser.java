package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAOImpl;
import model.User;

@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAOImpl userDAO = new UserDAOImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUser() {
		super();
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
		String user_id = request.getParameter("userID");

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String fullName = request.getParameter("fullName");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");

		String err = "";
		String url = "/update_user.jsp";

		if (password.equals("") || email.equals("") || fullName.equals("") || address.equals("")) {
			err += "* Cannot leave fields blank!";
		}

		else {

			Pattern pattenObj = Pattern.compile(
					"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			Matcher matcherObj = pattenObj.matcher(email);
			if (!matcherObj.matches()) {
				err += "* Please enter a valid email";
			}

			Pattern pattenObj2 = Pattern.compile("^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$");
			Matcher matcherObj2 = pattenObj2.matcher(phone);
			if (!matcherObj2.matches()) {
				err += "* Wrong phone number format!";
			}

		}

		if (err.length() > 0) {
			request.setAttribute("err", err);
		}

		try {
			if (err.length() == 0) {

				User u = new User(Integer.parseInt(user_id), email, password, fullName, phone, address, "2");

				this.userDAO.updateUser(u);

				url = "/index.jsp";
			} else {
				url = "/update_user.jsp";
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/register.jsp");
		}
	}

}
