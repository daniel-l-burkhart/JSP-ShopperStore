<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New user sign up!</title>

</head>
<%@ include file="header.jsp"%>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<body>

	<div class="container">
		<div class="jumbotron">
			<h1>New user sign up</h1>
		</div>

		<h1 id="errors"></h1>

		<form action="RegisterServlet" method="post" id="registerForm"
			onsubmit="return validateRegistrationForm();">

			<div class="form-group">
				<label class="control-label" for="email">Email:</label> <input
					required type="email" class="form-control" name="email" id="email"
					placeholder="Enter email">
			</div>
			<br />

			<div class="form-group">
				<label class="control-label" for="pwd">Password:</label> <input
					required type="password" class="form-control" name="password"
					id="pwd" placeholder="Enter password">

			</div>
			<br />

			<div class="form-group">
				<label class="control-label " for="fullName">Full Name:</label> <input
					required type="text" class="form-control" name="fullName"
					id="fullName" placeholder="Enter Full Name">

			</div>
			<br />

			<div class="form-group">
				<label class="control-label " for="phone">Phone:</label> <input
					required type="tel" class="form-control" name="phone" id="phone"
					placeholder="Enter Phone Number">
			</div>

			<br />

			<div class="form-group">
				<label class="control-label" for="address">Address:</label> <input
					required type="text" class="form-control" name="address"
					id="address" placeholder="Enter Address">
			</div>

			<br />

			<button type="submit" class="btn btn-primary">Submit</button>

		</form>
	</div>

</body>
<%@ include file="footer.jsp"%>
</html>