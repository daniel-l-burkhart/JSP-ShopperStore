<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<title>Shopper store</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<nav class="navbar navbar-inverse">
<div class="container-fluid">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">WebSiteName</a>
	</div>
	<ul class="nav navbar-nav">
		<li class="active"><a href="#">Home</a></li>
		<li><a href="index.jsp">Home Page</a></li>
		<li><a href="product.jsp">Products</a></li>
		<li><a href="category.jsp">Categories</a></li>
	</ul>


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


	<ul class="nav navbar-nav navbar-right">
		<li><a href="logout.jsp"><span
				class="glyphicon glyphicon-user"></span> Logout</a></li>
		<li><a href="update_user.jsp?username=<%=username%>"><span
				class="glyphicon glyphicon-log-in"></span> <%=username%></a></li>

	</ul>


	<%
		} else {
	%>


	<ul class="nav navbar-nav navbar-right">
		<li><a href="register.asp"><span
				class="glyphicon glyphicon-user"></span> Sign Up</a></li>
		<li><a href="login.jsp"><span
				class="glyphicon glyphicon-log-in"></span> Login</a></li>
	</ul>


	<%
		}
	%>

</div>
</nav>

