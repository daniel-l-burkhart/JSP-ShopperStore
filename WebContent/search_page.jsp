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
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/menu.css" />
<link rel="stylesheet" href="css/product.css" />

<%@ include file="header.jsp"%>
</head>
<body>

	<div class="left">

		<%
			CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
			ArrayList<Category> categoryList = new ArrayList<Category>();
			categoryList = categoryDAO.getCategories();
			String err = "";
		%>
		<div class="container">
			<nav>
			<ul class="mcd-menu">
				<li>
					<form accept-charset="utf-8" method="post" action="SearchServlet"
						name="SearchServlet">
						<p>
							<label for="productName">Product Name</label> <br> <input
								type="text" name="productName" style="width: 97%">
						</p>
						<p>
							<label for="categoryName">Category</label> <br> <select
								name="categoryName" style="width: 100%">
								<option value="" selected="selected">--Select
									Category--</option>
								<%
									for (Category c : categoryList) {
								%>
								<option value="<%=c.getCategoryName()%>"><%=c.getCategoryName()%></option>
								<%
									}
								%>
							</select>
						</p>
						<input type="submit" value="Search" name="search">
					</form>
				</li>
				<li style="color: red"><%=err%></li>
			</ul>
			</nav>
		</div>
	</div>

	<div id="content">

		<div id="right">
			<%
				ProductDAOImpl productDAO = new ProductDAOImpl();
				ArrayList<Product> list = new ArrayList<Product>();
				list = productDAO.getProducts();
				String productName = "";
				String categoryName = "";
				if (request.getParameter("productName") != null && request.getParameter("categoryName") != null) {
					productName = request.getParameter("productName");
					categoryName = request.getParameter("categoryName");
				}
				NumberFormat nf = NumberFormat.getInstance();
				nf.setMinimumFractionDigits(0);
			%>
			<%
				if (request.getAttribute("err") != null) {
					err = (String) request.getAttribute("err");
			%>
			<h3><%=err%></h3>
			<%
				}
			%>

			<%
				if (productDAO.searchProducts(productName, categoryName).size() == 0 && err == "") {
			%>
			<h3>No products found</h3>
			<%
				}
			%>
			<div id="site-wrapper" style="float: left">
				<ul class="products homepage">

					<%
						if (categoryName != null || productName != null) {
							for (Product p : productDAO.searchProducts(productName, categoryName)) {
					%>

					<li class="preorder"><span class="tagimg "> </span> <a
						href="detail.jsp?productCode=<%=p.getProductCode()%>"> <img
							src="productImages/<%=p.getPictureName()%>" width=" 250px"
							height="250px" />
							<h3><%=p.getProductName()%></h3>
							<h4>
								Price: $
								<%=nf.format(p.getPrice())%>

							</h4>
							<p class="info">
								<span>Manufacturer: <%=p.getManufacturer()%>
								</span> <span>Price: $<%=nf.format(p.getPrice())%>
								</span> <span>Details: <%=p.getInformation()%>
							</p>
					</a></li>

					<%
						}
						} else {
					%>
					<h3>Enter search information</h3>
					<%
						}
					%>
				</ul>
			</div>
		</div>
	</div>
	<div id="footer"><jsp:include page="footer.jsp"></jsp:include></div>
	</div>

</body>
</html>