<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<title>Shopper store</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/footer.css">


<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript">
	function validateRegistrationForm() {
		var form = $('#registerForm');
		var isValid = true;
		$('.form-control').each(function() {
			if ($(this).val() === '')
				isValid = false;
		});

		if (isValid === false) {
			document.getElementById('errors').innerHTML = "*Please fill out all fields!!!*";
		}

		return isValid;

	}
</script>

<nav class="navbar navbar-inverse">
<div class="container-fluid">
	<div class="navbar-header">
		<a class="navbar-brand" href="index.jsp">Shopper Store</a>
	</div>
	<ul class="nav navbar-nav">
		<li><a href="product.jsp">Products</a></li>
		<li><a href="category.jsp">Categories</a></li>
	</ul>


	<%
		HttpSession getSession = request.getSession();
		String username = null;

		User sessionUser = (User) getSession.getAttribute("user");
		if (sessionUser != null && sessionUser.getFullName() != null) {

			username = sessionUser.getEmail();

			//if (username != null) {
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
		<li><a href="register.jsp"><span
				class="glyphicon glyphicon-user"></span> Sign Up</a></li>
		<li><a href="login.jsp"><span
				class="glyphicon glyphicon-log-in"></span> Login</a></li>
	</ul>


	<%
		}
	%>

</div>
</nav>

