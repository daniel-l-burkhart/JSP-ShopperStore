package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAOImpl;
import model.Product;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDAOImpl productDAO = new ProductDAOImpl();
	private ArrayList<Product> list = new ArrayList<Product>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
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

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String productName = request.getParameter("productName");
		String categoryName = request.getParameter("categoryName");
		String err = "";

		if (productName.equals("") && categoryName.equals("")) {
			err += "You must enter at least one search term";
		}

		if (err.length() > 0) {
			request.setAttribute("err", err);
		}

		if (this.productDAO.searchProducts(productName, categoryName).size() != 0 && err == "") {
			this.list = this.productDAO.searchProducts(productName, categoryName);
			request.setAttribute("resultList", this.list);
		} else {

			request.setAttribute("resultList", null);
			
		}

		String url = "/search_page.jsp";
		try {

			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/login.jsp");
		}
	}

}
