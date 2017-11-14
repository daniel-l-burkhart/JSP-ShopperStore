<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dao.UserDAOImpl"%>
<%@page import="model.User"%>
<%@page import="dao.UserDAO"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update user account</title>
<%@ include file="header.jsp"%>

</head>
<body>
	<%
		if (request.getSession().getAttribute("user") != null) {
			String err = "";
			if (request.getAttribute("err") != null) {
				err = (String) request.getAttribute("err");
			}
			String email = request.getParameter("username");
			UserDAOImpl userDAO = new UserDAOImpl();
			User u = userDAO.getUser(email);
	%>

	<div class="container">

		<div class="jumbotron">
			<h1>Update User Account</h1>
		</div>

		<ul>
			<li style="color: red"><%=err%></li>
		</ul>

		<form action="UpdateUser" method="post">


			<div class="form-group">
				<label for="email">Email address</label> <input type="text"
					class="form-control" value="<%=u.getEmail()%>"
					onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = '<%=u.getEmail()%>';}"
					name="email">

			</div>

			<div class="form-group">
				<label for="password">Update Password</label> <input type="password"
					class="form-control" value="<%=u.getPassword()%>"
					onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = '<%=u.getPassword()%>';}"
					name="password">
			</div>

			<div class="form-group">
				<label for="fullName">Full Name</label> <input class="form-control"
					type="text" value="<%=u.getFullName()%>" onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = '<%=u.getFullName()%>';}"
					name="fullName">

			</div>
			<div class="form-group">
				<label for="phone">Phone Number</label> <input class="form-control"
					type="text" value="<%=u.getPhone()%>" onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = '<%=u.getPhone()%>';}"
					name="phone">
			</div>

			<div class="form-group">
				<label for="address">Address</label> <input class="form-control"
					type="text" value="<%=u.getAddress()%>" onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = '<%=u.getAddress()%>';}"
					name="address">
			</div>



			<button type="submit" class="btn btn-primary">Update Account</button>

			<input type="hidden" value="<%=u.getUser_id()%>" name="id">
		</form>

		<h5>
			<a href="index.jsp">Back to the home page!</a>
		</h5>

	</div>

	<%
		} else {
			String redirectURL = "index.jsp";
			response.sendRedirect(redirectURL);
		}
	%>
</body>
<%@ include file="footer.jsp"%>

</html>