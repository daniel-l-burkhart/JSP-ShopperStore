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

			<td>
				<div class="form-group">

					<div class="col-sm-4">
						<a class="cart_quantity_up"
							href="CartServlet?command=deleteCart&productCode=<%=item.getProduct().getProductCode()%>">
							- </a>
					</div>

					<div class="col-sm-4">
						<input class="form-control" type="number"
							value="<%=item.getQuantity()%>" disabled="disabled">
					</div>

					<div class="col-sm-4">
						<a class="cart_quantity_up"
							href="CartServlet?command=addCart&productCode=<%=item.getProduct().getProductCode()%>">
							+ </a>
					</div>

				</div>

			</td>

			<td>$ <%=nf
								.format(productDAO.getSingleProductFromID(item.getProduct().getProductCode()).getPrice()
										* item.getQuantity())%> <a
				href="CartServlet?command=removeCart&productCode=<%=item.getProduct().getProductCode()%>">

					<img style="margin-left: 10px" src="images/delete.png">
			</a></td>
		</tr>
		<%
			}
				}
		%>

	</table>

	<div class="container">
		<ul class="list-group">

			<%
				if (cart.getLineItems().size() > 0) {
			%>

			<li class="list-group-item">TOTAL: <%=nf.format(total)%></li>

			<li class="list-group-item"><a href="history.jsp"
				style="text-decoration: none;">Order History</a></li>

			<li class="list-group-item"><a class="checkout"
				href="ConfirmServlet?username=<%=username%>">Checkout</a></li>
			<%
				} else {
			%>
			<li class="list-group-item"><a href="history.jsp">Order
					History</a></li>
			<li class="list-group-item"><a href="product.jsp">Add more
					products</a></li>
			<%
				}
			%>
		</ul>

	</div>

	<%
		} else {
			String redirectURL = "index.jsp";
			response.sendRedirect(redirectURL);
		}
	%>

</body>
<%@ include file="footer.jsp"%>
</html>