<%@page import="model.Order"%>
<%@page import="dao.OrderDAOImpl"%>
<%@page import="model.Cart"%>
<%@page import="java.util.List"%>
<%@page import="dao.ProductDAOImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dao.UserDAOImpl"%>
<%@page import="model.User"%>
<%@page import="dao.UserDAO"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order History</title>

<%@ include file="header.jsp"%>
</head>
<body>
	<%
		ProductDAOImpl productDAO = new ProductDAOImpl();
		OrderDAOImpl historyDAO = new OrderDAOImpl();
		UserDAOImpl userDAO = new UserDAOImpl();
		User u = userDAO.getUser(username);

		ArrayList<Order> orderHistory = historyDAO.getHistoriesForUser(u.getUser_id());
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumIntegerDigits(0);
	%>
	<div id="content">

		<div class="shopping-cart">

			<div class="column-labels">
				<label class="product-image">Product Image</label> <label
					class="product-details">Product Details </label> <label
					class="product-price">Product Price</label> <label
					class="product-quantity">Product Quantity</label> <label
					class="product-line-price">Line item price</label>
			</div>

			<%
				if (orderHistory != null) {
					for (Order currOrder : orderHistory) {
			%>
			<div class="product">
				<div class="product-image">
					<img
						src="productImages/<%=productDAO.getSingleProductFromID(currOrder.getProductCode()).getPictureName()%>">

				</div>
				<div class="product-details">
					<div class="product-title">
						<%=productDAO.getSingleProductFromID(currOrder.getProductCode()).getProductName()%>
					</div>
					<p class="product-description"></p>
				</div>
				<div class="product-price">
					<%=currOrder.getMoneyPaid()%>
				</div>
				<div class="product-quantity cart_quantity_button">
					<%=currOrder.getNumber()%>

				</div>
				<div class="product-line-price" style="text-align: right">
					<%=nf.format(productDAO.getSingleProductFromID(currOrder.getProductCode()).getPrice())%>
				</div>

			</div>
			<%
				}
				}
			%>


		</div>
	</div>

</body>
<%@ include file="footer.jsp"%>

</html>