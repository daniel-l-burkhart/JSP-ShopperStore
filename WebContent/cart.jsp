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

		if (cart != null) {
	%>

	<div class="container">
		<div class="jumbotron">
			<h1>Shopping cart!</h1>
		</div>

		<%
			if (cart.getLineItems().size() > 0) {
		%>

		<table class="table">
			<thead>
				<th class="text-center">Image</th>
				<th class="text-center">Product Name</th>
				<th class="text-center">Price</th>
				<th class="text-center">Quantity</th>
				<th class="text-center">Line Price</th>
				<th class="text-center">Remove</th>
			</thead>

			<%
				for (CartItem item : cart.getLineItems()) {
							total = total + (item.getQuantity()
									* productDAO.getSingleProductFromID(item.getProduct().getProductCode()).getPrice());
			%>
			<tr>

				<td><img
					src="productImages/<%=productDAO.getSingleProductFromID(item.getProduct().getProductCode()).getPictureName()%>"
					width="200px" /></td>

				<td><%=productDAO.getSingleProductFromID(item.getProduct().getProductCode()).getProductName()%>
				</td>

				<td>$<%=nf.format(
								productDAO.getSingleProductFromID(item.getProduct().getProductCode()).getPrice())%>
				</td>

				<td>
					<div class="form-group">

						<div class="text-center col-sm-4">

							<form action="CartServlet" method="post">
								<input type="hidden" name="command" value="deleteCart" /> <input
									type="hidden" name="productCode"
									value="<%=item.getProduct().getProductCode()%>" />
								<button class="btn btn-default" type="submit">
									<span class="glyphicon glyphicon-minus"></span>
								</button>
							</form>

						</div>

						<div class="text-center col-sm-4">
							<input class="form-control" type="number"
								value="<%=item.getQuantity()%>" disabled="disabled">
						</div>

						<div class="text-center col-sm-4">


							<form action="CartServlet" method="post">
								<input type="hidden" name="command" value="addCart" /> <input
									type="hidden" name="productCode"
									value="<%=item.getProduct().getProductCode()%>" />
								<button type="submit" class="btn btn-default">
									<span class="glyphicon glyphicon-plus"></span>
								</button>
							</form>
						</div>

					</div>

				</td>

				<td>$ <%=nf
								.format(productDAO.getSingleProductFromID(item.getProduct().getProductCode()).getPrice()
										* item.getQuantity())%>




				</td>
				<td>

					<form action="CartServlet" method="post">
						<input type="hidden" name="command" value="removeCart" /> <input
							type="hidden" name="productCode"
							value="<%=item.getProduct().getProductCode()%>" />
						<button class="btn btn-danger" type="submit">
							<span class="glyphicon glyphicon-remove"></span>
						</button>
					</form>
				</td>
			</tr>
			<%
				}
			%>

		</table>

		<%
			} else {
		%>


		<div class="jumbotron">

			<h2>No items in cart yet! Keep shopping!</h2>
		</div>

		<%
			}
		%>

	</div>


	<div class="container">
		<ul class="list-group">

			<%
				if (cart.getLineItems().size() > 0) {
			%>

			<li class="list-group-item">TOTAL: <%=nf.format(total)%></li>

			<li class="list-group-item"><a class="btn btn-primary"
				href="order.jsp" style="text-decoration: none;">Order History</a></li>

			<li class="list-group-item">

				<form action="ConfirmServlet" method="post" name="checkout">
					<input type="hidden" name="username" value="<%=username%>" />
					<button class="btn btn-success" type="submit">Checkout</button>

				</form>
			</li>
			<%
				} else {
			%>
			<li class="list-group-item"><a class="btn btn-primary"
				href="order.jsp">Order History</a></li>
			<li class="list-group-item"><a class="btn btn-success"
				href="product.jsp">Add more products</a></li>
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