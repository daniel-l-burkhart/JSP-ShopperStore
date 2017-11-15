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

	<div class="container">
		<div class="jumbotron">
			<h1>Orders</h1>
		</div>

		<%
			if (username != null) {

				ProductDAOImpl productDAO = new ProductDAOImpl();
				OrderDAOImpl historyDAO = new OrderDAOImpl();
				UserDAOImpl userDAO = new UserDAOImpl();
				User u = userDAO.getUser(username);

				ArrayList<Order> orderHistory = historyDAO.getHistoriesForUser(u.getUser_id());
				NumberFormat nf = NumberFormat.getInstance();
				nf.setMinimumIntegerDigits(0);

				if (orderHistory != null) {
		%>

		<table class="table table-striped">

			<thead>
				<th>Product Image</th>
				<th>Product Details</th>
				<th>Product Price</th>
				<th>Product Quantity</th>
				<th>Line item price</th>

			</thead>
			<tbody>

				<%
					for (Order currOrder : orderHistory) {
				%>
				<tr>

					<td><img
						src="productImages/<%=productDAO.getSingleProductFromID(currOrder.getProductCode()).getPictureName()%>"
						width="200px"></td>
					<td><strong><%=productDAO.getSingleProductFromID(currOrder.getProductCode()).getProductName()%></strong>
						<p><%=productDAO.getSingleProductFromID(currOrder.getProductCode()).getInformation()%></p>

					</td>
					<td><%=nf.format(productDAO.getSingleProductFromID(currOrder.getProductCode()).getPrice())%></td>
					<td><%=currOrder.getNumber()%></td>
					<td><%=currOrder.getMoneyPaid()%></td>

				</tr>
				<%
					}
				%>

			</tbody>

		</table>
	</div>


	<%
		}
		} else {
			String redirectURL = "index.jsp";
			response.sendRedirect(redirectURL);
		}
	%>

</body>
<%@ include file="footer.jsp"%>

</html>