<%@page import="java.util.ArrayList"%>
<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@page import="dao.ProductDAOImpl"%>
<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Products</title>
<link rel="stylesheet" href="css/product.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/menu.css" />

<%@ include file="header.jsp"%>
<body>

	<div class="container">

		<%!ProductDAOImpl productDAO = new ProductDAOImpl();
	NumberFormat nf = NumberFormat.getInstance();%>

		<table class="datatable table table-striped table-bordered">

			<tr>
				<th>Image</th>
				<th>Product Name</th>
				<th>Price</th>
				<th>Manufacturer</th>
				<th>Detail</th>
			</tr>

			<%
				for (Product currProduct : this.productDAO.getProducts()) {
			%>

			<tr>
				<td><a
					href="detail.jsp?productCode=<%=currProduct.getProductCode()%>">

						<img src="productImages/<%=currProduct.getPictureName()%>"
						width="250px" height="250px" />

				</a></td>
				<td><%=currProduct.getProductName()%></td>
				<td><%=currProduct.getPrice()%></td>
				<td><%=currProduct.getManufacturer()%></td>
				<td><a
					href="detail.jsp?productCode=<%=currProduct.getProductCode()%>">Details
				</a></td>

			</tr>

			<%
				}
			%>

		</table>

	</div>
	<%@ include file="footer.jsp"%>

</body>
</html>