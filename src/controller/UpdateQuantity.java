package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAOImpl;

/**
 * Servlet implementation class UpdateQuantity
 */
@WebServlet("/UpdateQuantity")
public class UpdateQuantity extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAOImpl productDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateQuantity() {
		super();
		this.productDAO = new ProductDAOImpl();
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

		String newQuantity = request.getParameter("newQuantity");
		int newQuantityInt = Integer.parseInt(newQuantity);
		String productCode = request.getParameter("productCode");
		int productCodeInt = Integer.parseInt(productCode);

		boolean result = this.productDAO.replinishQuantity(productCodeInt, newQuantityInt);

		request.setAttribute("sqlResult", result);

		String url = "/update_quantity.jsp";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
