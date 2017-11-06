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
<title>Home page!</title>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/menu.css" />
<link rel="stylesheet" href="css/product.css" />
</head>
<body>

	<div id="main">
		<div id="head">
			<img src="images/banner.jpg" width="1057px" height="200px" />
		</div>

		<%
			String username = null;
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("username"))
						username = cookie.getValue();
				}
			}

			if (username != null) {
		%>
		<div id="head-link">
			<div id='menungang'>
				<ul>
					<li class='last'><a href="index.jsp"><span>Home
								page </span></a></li>
					<li class='last'><a href="product.jsp"><span>Products
						</span></a></li>
					<li class='last'><a href="cart.jsp"><span>Cart</span></a></li>
					<li class='last'><a href="search_page.jsp"><span>
								Search Products</span></a></li>
					<li class='last' style="float: right;"><a href="LogoutServlet"><span>Logout
						</span></a></li>
					<li class='last' style="float: right;"><a
						href="update_user.jsp?username=<%=username%>"><span><%=username%></span></a></li>
				</ul>
			</div>
		</div>
		<%
			} else {
		%>
		<div id="head-link">
			<div id='menungang'>
				<ul>
					<li class='last'><a href="index.jsp"><span>Home
								page</span></a></li>
					<li class='last'><a href="product.jsp"><span>Products</span></a></li>
					<li class='last'><a href="search_page.jsp"><span>Search
								Products</span></a></li>
					<li class='last' style="float: right;"><a href="register.jsp"><span>Register</span></a></li>
					<li class='last' style="float: right;"><a href="login.jsp"><span>Login</span></a></li>
				</ul>
			</div>
		</div>
		<%
			}
		%>
		<div id="content">
			<div id="left"><jsp:include page="category.jsp"></jsp:include></div>
			<div id="right">
				<%
					ProductDAOImpl productDAO = new ProductDAOImpl();
					List<Product> list = new ArrayList<Product>();
					list = productDAO.getProducts();
					String categoryID = null;
					if (request.getParameter("categoryID") != null) {
						categoryID = request.getParameter("categoryID");
					}

					NumberFormat nf = NumberFormat.getInstance();
					nf.setMinimumFractionDigits(0);
				%>
				<div id="site-wrapper" style="float: left">
					<ul class="products homepage">
						<%
							if (categoryID != null) {
								for (Product p : productDAO.getAllProductsFromCategory(Integer.parseInt(categoryID))) {
						%>

						<li class="preorder"><span class="tagimg "> </span> <a
							href="detail.jsp?ma_san_pham=<%=p.getProductCode()%>"> <img
								src="productImages" width=" 250px" height="250px" />
								<h3><%=p.getPictureName()%></h3>
								<h4>
									Price:
									<%=nf.format(p.getPrice())%>

								</h4> <span class="textkm">Promotion <strong>500.000₫</strong>
							</span>
								<p class="info">
									<span>Manufacturer: <%=p.getManufacturer()%>
									</span> <span>Price:<%=nf.format(p.getPrice())%>
									</span> <span>Details: <%=p.getInformation()%>
								</p>
						</a></li>

						<%
							}
							} else {
								for (Product p : productDAO.getProducts()) {
						%>

						<li class="preorder"><span class="tagimg "></span> <a
							href="detail.jsp?ma_san_pham=<%=p.getProductCode()%>"> <img
								src="productImages/<%=p.getPictureName()%>" width=" 250px"
								height="250px" />
								<h3><%=p.getProductName()%></h3>
								<h4>
									Price:
									<%=nf.format(p.getPrice())%>

								</h4> <span class="textkm">Promotion <strong>500.000₫</strong>
							</span>
								<p class="info">
									<span>Company: <%=p.getManufacturer()%></span> <span>Price:
										<%=nf.format(p.getPrice())%>
									</span> <span>Information: <%=p.getInformation()%>
								</p>
						</a></li>
						<%
							}
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