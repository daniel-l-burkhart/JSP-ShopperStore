package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.CartItem;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Cart cart;

	public CartServlet() {
		super();
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter("command");
		String productCode = request.getParameter("productCode");

		if (command.equals("addCart")) {

			int productCodeInteger = Integer.parseInt(productCode);
			CartItem newLineItem = new CartItem(productCodeInteger, 1);
			this.addToCart(newLineItem);

			HttpSession session = request.getSession();
			System.out.println(this.cart.getLineItems().size());
			session.setAttribute("cart", this.cart);
			response.sendRedirect("cart.jsp");

		} else if (command.equals("deleteCart")) {

			int productCodeInteger = Integer.parseInt(productCode);
			CartItem deletingItem = new CartItem(productCodeInteger, 1);
			this.deleteFromCart(deletingItem);

			HttpSession session = request.getSession();
			System.out.println(this.cart.getLineItems().size());
			session.setAttribute("cart", this.cart);
			response.sendRedirect("cart.jsp");

		} else if (command.equals("removeCart")) {

			int productCodeInteger = Integer.parseInt(productCode);
			CartItem removingItem = new CartItem(productCodeInteger, 1);
			this.removeFromCart(removingItem);

			HttpSession session = request.getSession();
			session.setAttribute("cart", this.cart);
			response.sendRedirect("cart.jsp");

		} else if (command.equals("setCart")) {

			int productCodeInteger = Integer.parseInt(productCode);
			int newNumber = Integer.parseInt(request.getParameter("number"));
			CartItem setCartItem = new CartItem(productCodeInteger, newNumber);
			this.setCart(setCartItem);
			HttpSession session = request.getSession();

			session.setAttribute("cart", this.cart);
			response.sendRedirect("cart.jsp");

		}
	}

	private void addToCart(CartItem cartItem) {

		for (CartItem item : this.cart.getLineItems()) {
			if (item.getProduct().getProductCode() == cartItem.getProduct().getProductCode()) {
				item.setQuantity(item.getQuantity() + 1);
				// return "cart";
			}
		}

		this.cart.addLineItemToCart(cartItem);
		// return "cart";
	}

	private void deleteFromCart(CartItem itemToBeDeleted) {

		for (CartItem currItem : this.cart.getLineItems()) {
			if (currItem.getProduct().getProductCode() == itemToBeDeleted.getProduct().getProductCode()
					&& itemToBeDeleted.getQuantity() > 1) {
				itemToBeDeleted.setQuantity(itemToBeDeleted.getQuantity() - 1);
				// return "cart";
			}
		}
		// return "cart";
	}

	private void removeFromCart(CartItem removingItem) {
		for (CartItem currItem : this.cart.getLineItems()) {
			if (currItem.getProduct().getProductCode() == removingItem.getProduct().getProductCode()) {
				this.cart.removeCartItem(currItem);
				// return "cart";
			}
		}
		// return "cart";
	}

	private void setCart(CartItem myCartItem) {

		for (CartItem item : this.cart.getLineItems()) {
			if (item.getProduct().getProductCode() == myCartItem.getProduct().getProductCode()) {
				item.setQuantity(myCartItem.getQuantity());
				// return "cart";
			}
		}

		this.cart.addLineItemToCart(myCartItem);
		// return "cart";
	}

}