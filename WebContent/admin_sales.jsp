<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.UserDAOImpl"%>
<%@page import="dao.OrderDAOImpl"%>
<%@page import="dao.ProductDAOImpl"%>
<%@page import="model.Order"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="header.jsp"%>
</head>
<body>

	<div class="container">
		<div class="jumbotron">
			<h1>Sales Records</h1>
		</div>
	</div>

	<%
		HttpSession adminCheckSession = request.getSession();
		ArrayList<Order> historyList = null;
		OrderDAOImpl historyDAO = new OrderDAOImpl();
		UserDAOImpl userDAO = new UserDAOImpl();
		ProductDAOImpl productDAO = new ProductDAOImpl();

		User checkUser = null;
		if (adminCheckSession.getAttribute("user") != null) {
			checkUser = (User) adminCheckSession.getAttribute("user");

			if (checkUser.getRole().equals("1")) {
				historyList = historyDAO.getHistoriesForAdmin();
	%>

	<table class="table table-striped table-responsive table-bordered">
		<tr>
			<th>History ID</th>
			<th>User</th>
			<th>Product</th>
			<th>TimeStamp</th>
			<th>Quantity</th>
			<th>Order total</th>
		</tr>

		<%
			for (Order order : historyList) {
		%>

		<tr>
			<td><%=order.getOrderID()%></td>
			<td><%=userDAO.getUserFromID(Integer.toString(order.getUserID())).getFullName()%>
			</td>
			<td><%=productDAO.getSingleProductFromID(order.getProductCode()).getProductName()%></td>
			<td><%=order.getTimeStamp()%></td>
			<td><%=order.getNumber()%></td>
			<td><%=order.getMoneyPaid()%></td>
		</tr>

		<%
			}
		%>
	</table>


	<%
		} else {
				String redirectURL = "index.jsp";
				response.sendRedirect(redirectURL);
			}
		} else {
			String redirectURL = "index.jsp";
			response.sendRedirect(redirectURL);
		}
	%>

</body>
<%@ include file="footer.jsp"%>
</html>