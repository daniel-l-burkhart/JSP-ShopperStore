<%@page import="java.util.ArrayList"%>
<%@page import="model.Product"%><%@page import="model.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.ProductDAOImpl"%><%@page
	import="dao.CategoryDAOImpl"%>
<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Products</title>

<%@ include file="header.jsp"%>
</head>
<body>

	<%
		CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
		ArrayList<Category> categoryList = new ArrayList<Category>();
		categoryList = categoryDAO.getCategories();
		ArrayList<Product> resultList = new ArrayList<Product>();
		String err = "";
	%>

	<div class="container">
		<div class="jumbotron">
			<h1>Search</h1>
		</div>
	</div>

	<%
		if (request.getAttribute("err") != null) {
			err = (String) request.getAttribute("err");
	%>
	<ul>
		<li style="color: red"><%=err%></li>
	</ul>
	<%
		}
	%>

	<div class="container">

		<form method="post" action="SearchServlet" name="SearchServlet">
			<div class="form-group">
				<label for="productName">Product Name</label> <input type="text"
					class="form-control" name="productName">
			</div>
			<div class="form-group">
				<label for="categoryName">Category</label> <br> <select
					name="categoryName" class="form-control">
					<option value="" selected="selected">--Select Category--</option>
					<%
						for (Category c : categoryList) {
					%>
					<option value="<%=c.getCategoryName()%>"><%=c.getCategoryName()%></option>
					<%
						}
					%>
				</select>
			</div>

			<button type="submit" class="btn btn-primary" name="search"
				value="search">Search</button>
		</form>
	</div>

	<br />
	<br />

	<div class="container">

		<%
			if (request.getAttribute("err") != null) {
				err = (String) request.getAttribute("err");
		%>
		<h3><%=err%></h3>
		<%
			}
		%>

		<%
			if (request.getAttribute("resultList") == null
					&& (request.getAttribute("categoryName") != null || request.getAttribute("productName") != null)) {
		%>
		<h3>No products found</h3>
		<%
			}
		%>

		<%
			if (request.getAttribute("resultList") != null) {
				resultList = (ArrayList<Product>) request.getAttribute("resultList");
		%>
		<h4>Results</h4>
		<table class="table">

			<tr>
				<th>Image</th>
				<th>Name</th>
				<th>Price</th>
				<th>Company</th>
				<th>Details</th>
			</tr>

			<%
				for (Product p : resultList) {
			%>

			<tr>
				<td><a href="detail.jsp?productCode=<%=p.getProductCode()%>">
						<img src="productImages/<%=p.getPictureName()%>" width=" 250px"
						height="250px" />
				</a></td>

				<td><a href="detail.jsp?productCode=<%=p.getProductCode()%>">
						<%=p.getProductName()%>
				</a></td>
				<td><%=p.getPrice()%></td>
				<td><%=p.getManufacturer()%></td>
				<td><%=p.getInformation()%></td>
			</tr>

			<%
				}
			%>

		</table>

		<%
			}
		%>
	</div>

</body>
<%@ include file="footer.jsp"%>

</html>