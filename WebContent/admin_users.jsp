<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.UserDAOImpl"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="header.jsp"%>
</head>
<body>

	<div class="container">
		<div class="jumbotron">
			<h1>Users List</h1>
		</div>
	</div>

	<%
		HttpSession adminCheckSession = request.getSession();
		ArrayList<User> usersList = null;

		User checkUser = null;
		checkUser = (User) adminCheckSession.getAttribute("user");

		if (adminCheckSession.getAttribute("user") != null && checkUser.getRole().equals("1")) {

			UserDAOImpl users = new UserDAOImpl();
			usersList = users.getUsersForAdminView();
	%>

	<table class="table table-striped table-responsive table-bordered">

		<tr>
			<th>User ID</th>
			<th>Email</th>
			<th>Full Name</th>
			<th>Phone</th>
			<th>Address</th>
			<th>Role</th>
			<th>Actions</th>
		</tr>

		<%
			for (User currUser : usersList) {
		%>

		<tr>
			<td><%=currUser.getUser_id()%></td>
			<td><%=currUser.getEmail()%></td>
			<td><%=currUser.getFullName()%></td>
			<td><%=currUser.getPhone()%></td>
			<td><%=currUser.getAddress()%></td>
			<td><%=currUser.getRole()%>
			<td><form action="AdminUsers" method="post">
					<input type="hidden" value="<%=currUser.getUser_id()%>"
						name="userID" />
					<button class="btn btn-danger" type="submit">Delete User</button>
				</form></td>
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