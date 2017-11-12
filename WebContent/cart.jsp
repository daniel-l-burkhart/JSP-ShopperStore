<%@page import="model.Cart"%>
<%@page import="model.CartItem"%>
<%@page import="java.util.List"%>
<%@page import="dao.ProductDAOImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CartJSP page</title>

<%@ include file="header.jsp"%>

</head>
<body>
	<%
		ProductDAOImpl productDAO = new ProductDAOImpl();
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumIntegerDigits(0);

		double total = 0;
		Cart cart = null;

		if (session.getAttribute("cart") != null) {
			cart = (Cart) session.getAttribute("cart");
			System.out.println(cart);
		}

		System.out.println(cart);

		if (cart != null) {
	%>

	<div class="container">
		<div class="jumbotron">
			<h1>Shopping cart!</h1>
		</div>
	</div>

	<table class="table">
		<tr>
			<th>Product Image</th>
			<th>Product Name</th>
			<th>Product Price</th>
			<th>Product Quantity</th>
			<th>Product Line Price</th>
		</tr>

		<%
			if (cart != null) {
					for (CartItem item : cart.getLineItems()) {
						total = total + (item.getQuantity()
								* productDAO.getSingleProductFromID(item.getProduct().getProductCode()).getPrice());
		%>
		<tr>


			<td><img
				src="productImages/<%=productDAO.getSingleProductFromID(item.getProduct().getProductCode()).getPictureName()%>"
				width="250px" height="250px" /></td>

			<td><%=productDAO.getSingleProductFromID(item.getProduct().getProductCode()).getProductName()%>
			</td>

			<td>$<%=nf.format(
								productDAO.getSingleProductFromID(item.getProduct().getProductCode()).getPrice())%>
			</td>

			<td><a class="cart_quantity_up"
				href="CartServlet?command=deleteCart&ma_san_pham=<%=item.getProduct().getProductCode()%>">
					- </a> <input class="cart_quantity_input" type="number"
				value="<%=item.getQuantity()%>" disabled="disabled"> <a
				class="cart_quantity_up"
				href="CartServlet?command=addCart&ma_san_pham=<%=item.getProduct().getProductCode()%>">
					+ </a></td>


			<td>$<%=nf
								.format(productDAO.getSingleProductFromID(item.getProduct().getProductCode()).getPrice()
										* item.getQuantity())%> <a
				href="CartServlet?command=removeCart&ma_san_pham=<%=item.getProduct().getProductCode()%>"><img
					style="margin-left: 10px" src="images/delete.png"></a></td>

		</tr>
		<%
			}
				}
		%>


	</table>

	<div class="container">
		<p>
			TOTAL:
			<%=nf.format(total)%></p>

		<%
			if (cart.getLineItems().size() > 0) {
		%>


		<a class="checkout" href="history.jsp" style="text-decoration: none;">Order
			History</a> <a class="checkout"
			href="ConfirmServlet?username=<%=username%>"
			style="text-decoration: none;">Checkout</a>
		<%
			} else {
		%>
		<a class="checkout" href="history.jsp" style="text-decoration: none;">History</a>
		<a class="checkout" href="product.jsp" style="text-decoration: none;">Add
			more products</a>
		<%
			}
		%>

	</div>

	<%
		} else {

			String redirectURL = "index.jsp";
			response.sendRedirect(redirectURL);

		}
	%>


	<div id="footer"><jsp:include page="footer.jsp"></jsp:include></div>
	</div>
</body>
</html>