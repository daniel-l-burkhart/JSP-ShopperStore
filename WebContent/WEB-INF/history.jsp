<%@page import="model.History"%>
<%@page import="dao.HistoryDAOImpl"%>
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
<title>Cart</title>
<link rel="stylesheet" href="css/cart.css" />
<link rel="stylesheet" href="css/menu.css" />
<link rel="stylesheet" href="css/main.css" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<style type="text/css">
#main {
	width: 1060px;
	padding: 0;
	margin-left: auto;
	margin-right: auto;
}

#head {
	height: 200px;
	background-color: #F5F5F5;
	border: 1px solid #CDCDCD;
	margin-bottom: 5px;
	margin-top: 5px;
}

#head-link {
	height: 50px;
	line-height: 30px;
	border: 1px solid #CDCDCD;
	background-color: #F5F5F5;
	margin-bottom: 5px;
	clear: both;
}

#content {
	width: 1060px;
	min-height: 430px;
	border: 1px solid #CDCDCD;
	float: left;
	margin-bottom: 5px;
	clear: both;
}

#footer {
	height: 50px;
	clear: both;
	border: 1px solid #CDCDCD;
	background-color: #F8F8FF;
	margin-bottom: 5px;
}
</style>
</head>
<body>
	<%
		String username = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username"))
					username = cookie.getValue();
			}
		}
	%>
	<div id="main">
		<div id="head">
			<img src="images/banner.jpg" width="1057px" height="200px" />
		</div>
		<div id="head-link">
			<div id='menungang'>
				<ul>
					<li class='last'><a href="index.jsp"><span>Home
								page</span></a></li>
					<li class='last'><a href="product.jsp"><span>Products</span></a></li>
					<li class='last'><a href="cart.jsp"><span>Cart</span></a></li>
					<li class='last'><a href="search_page.jsp"><span>Search
								Products</span></a></li>
					<li class='last' style="float: right;"><a href="login.jsp"><span>Login</span></a></li>
					<li class='last' style="float: right;"><a
						href="update_user.jsp?username=<%=username%>"><span><%=username%></span></a></li>
				</ul>
			</div>
		</div>
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
					ProductDAOImpl productDAO = new ProductDAOImpl();
					HistoryDAOImpl historyDAO = new HistoryDAOImpl();
					UserDAOImpl userDAO = new UserDAOImpl();
					User u = userDAO.getUser(username);

					List<History> L = historyDAO.getHistoriesForUser(u.getUser_id());
					NumberFormat nf = NumberFormat.getInstance();
					nf.setMinimumIntegerDigits(0);
				%>
				<%
					if (L != null) {
						for (History h : L) {
				%>
				<div class="product">
					<div class="product-image">
						<img
							src="productImages/<%=productDAO.getSingleProductFromID(h.getProductCode()).getPictureName()%>">

					</div>
					<div class="product-details">
						<div class="product-title">
							<%=productDAO.getSingleProductFromID(h.getProductCode()).getProductName()%>
						</div>
						<p class="product-description"></p>
					</div>
					<div class="product-price">
						<%=h.getMoneyPaid()%>
					</div>
					<div class="product-quantity cart_quantity_button">
						<%=h.getNumber()%>

					</div>
					<div class="product-line-price" style="text-align: right">
						<%=nf.format(productDAO.getSingleProductFromID(h.getProductCode()).getPrice())%>

					</div>

				</div>
				<%
					}
					}
				%>


			</div>

		</div>
		<div id="footer"><jsp:include page="footer.jsp"></jsp:include></div>
	</div>
</body>
</html>