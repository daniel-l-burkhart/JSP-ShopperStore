<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.UserDAOImpl"%>
<%@page import="dao.OrderDAOImpl"%>
<%@page import="dao.ProductDAOImpl"%>
<%@page import="model.Product"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Quantity</title>

<%@ include file="header.jsp"%>
</head>
<body>

	<div class="container">
		<div class="jumbotron">
			<h1>Update Quantity of inventory</h1>
		</div>
	</div>

	<%
		ProductDAOImpl productDAO = new ProductDAOImpl();
		if (username != null && sessionUser != null && (sessionUser.getRole().equals("1"))) {
	%>


	<table class="table table-striped table-bordered">

		<tr>
			<th>Image</th>
			<th>Product Name</th>
			<th>Price</th>
			<th>Manufacturer</th>
			<th>Current Quantity</th>
			<th>New Quantity</th>

		</tr>

		<%
			for (Product currProduct : productDAO.getProducts()) {
		%>

		<tr>
			<td><a
				href="detail.jsp?productCode=<%=currProduct.getProductCode()%>">

					<img src="productImages/<%=currProduct.getPictureName()%>"
					width="100px" height="100px" />

			</a></td>
			<td><%=currProduct.getProductName()%></td>
			<td><%=currProduct.getPrice()%></td>
			<td><%=currProduct.getManufacturer()%></td>
			<td><%=currProduct.getQuantity()%></td>
			<td><a class="btn btn-primary"
				href="update_quantity_detail.jsp?productCode=<%=currProduct.getProductCode()%>">Update
					Quantity</a></td>

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
	%>
</body>
<%@ include file="footer.jsp"%>

</html>