
<%@page import="dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dao.UserDAOImpl"%>
<%@page import="model.User"%>
<%@page import="dao.UserDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/login.css" rel='stylesheet' type='text/css' />
<title>Update user account</title>
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

	<!--/start-login-two-->
	<div class="login-02">
		<div class="two-login  hvr-float-shadow">
			<div class="two-login-head">
				<img src="images/top-note.png" alt="" />
				<h2>Update User account</h2>

			</div>

			<ul>
				<li style="color: red"><%=err%></li>
			</ul>
			<form action="UpdateUser" method="post">
				<table>

					<tr>
						<td>Email</td>
						<td><input type="text" class="text" value="<%=u.getEmail()%>"
							name="email"><a href="#" class=" icon2 user2"></a></td>
					</tr>

					<tr>
						<td>Update Password</td>
						<td><input type="password" value="<%=u.getPassword()%>"
							onfocus="this.value = '';"
							onblur="if (this.value == '') {this.value = '<%=u.getPassword()%>';}"
							name="password"><a href="#" class=" icon2 lock2"></a></td>
					</tr>

					<tr>

						<td>Full Name</td>
						<td><input type="text" value="<%=u.getFullName()%>"
							onfocus="this.value = '';"
							onblur="if (this.value == '') {this.value = '<%=u.getFullName()%>';}"
							name="fullName"><a href="#" class=" icon2 lock2"></a></td>

					</tr>
					<tr>
						<td>Phone Number</td>
						<td><input type="text" value="<%=u.getPhone()%>"
							onfocus="this.value = '';"
							onblur="if (this.value == '') {this.value = '<%=u.getPhone()%>';}"
							name="phone"><a href="#" class=" icon2 lock2"></a></td>

					</tr>

					<tr>

						<td>Address</td>
						<td><input type="text" value="<%=u.getAddress()%>"
							onfocus="this.value = '';"
							onblur="if (this.value == '') {this.value = '<%=u.getAddress()%>';}"
							name="address"><a href="#" class=" icon2 lock2"></a></td>
					</tr>

				</table>

				<div class="submit two">
					<input type="submit" value="Submit"> <input type="hidden"
						value="<%=u.getUser_id()%>" name="id">
				</div>
				<h5>
					<a href="index.jsp">Back to the home page!</a>
				</h5>
			</form>
		</div>
	</div>

	<%
		} else {
			String redirectURL = "index.jsp";
			response.sendRedirect(redirectURL);
		}
	%>
</body>
</html>