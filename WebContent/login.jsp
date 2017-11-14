<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Log in here!</title>
<%@ include file="header.jsp"%>
</head>

<body>
	<div class="container">
		<div class="jumbotron">
			<h1>Login</h1>
		</div>

		<ul>
			<%
				String err = "";
				if (request.getAttribute("err") != null) {
					err = (String) request.getAttribute("err");
			%>
			<li style="color: red"><%=err%></li>
			<%
				}
			%>
		</ul>

		<ul>
			<%
				String mess = "";
				if (request.getAttribute("mess") != null) {
					mess = (String) request.getAttribute("mess");
			%>
			<li style="color: red"><%=mess%></li>
			<%
				}
			%>

		</ul>

		<form action="LoginServlet" method="post">

			<div class="form-group">

				<label for="email">Email</label> <input type="email"
					class="form-control" name="email">
			</div>

			<div class="form-group">

				<label for="password">Password</label> <input class="form-control"
					type="password" name="password">
			</div>

			<button class="btn btn-primary" type="submit">Sign in</button>
		</form>

		<h6>
			<a href="resetpassword.jsp">Forgot password</a>
		</h6>

		<h5>
			Are you new here?<a href="register.jsp"> Register here! </a>
		</h5>

	</div>
</body>
<%@ include file="footer.jsp"%>

</html>