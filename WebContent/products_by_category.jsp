<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="dao.CategoryDAOImpl"%>
<%@page import="dao.ProductDAOImpl"%>
<%@page import="model.Category"%>
<%@page import="model.Product"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Get products by category</title>
</head>
<%@ include file="header.jsp"%>
<body>

	<%
		ProductDAOImpl productDAO = new ProductDAOImpl();
		CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
		ArrayList<Product> list = new ArrayList<Product>();

		list = productDAO.getProducts();
		String categoryID = null;
		String categoryName = null;

		if (request.getParameter("categoryID") != null) {
			categoryID = request.getParameter("categoryID");
			categoryName = categoryDAO.getCategory(Integer.parseInt(categoryID)).getCategoryName();
		}
	%>

	<div class="container">
		<div class="jumbotron">
			<h1>Products by category</h1>
			<h3>
				Category:
				<%=categoryName%></h3>
		</div>
	</div>

	<%
		if (categoryID != null) {
			for (Product currProduct : productDAO.getAllProductsFromCategory(Integer.parseInt(categoryID))) {
	%>

	<table class="table table-striped table-bordered table-responsive">
		<tr>
			<th>Image</th>
			<th>Product Name</th>
			<th>Price</th>
			<th>Manufacturer</th>
			<th>Detail</th>
		</tr>
		<tr>
			<td><a
				href="detail.jsp?productCode=<%=currProduct.getProductCode()%>">

					<img src="productImages/<%=currProduct.getPictureName()%>"
					width="250px" height="250px" />

			</a></td>

			<td><%=currProduct.getProductName()%></td>
			<td>$<%=currProduct.getPrice()%></td>
			<td><%=currProduct.getManufacturer()%></td>
			<td><a class="btn btn-primary"
				href="detail.jsp?productCode=<%=currProduct.getProductCode()%>">Details
			</a></td>

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