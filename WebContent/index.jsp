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

<%@ include file="header.jsp"%>

</head>
<body>


	<div class="container">

		<div class="jumbotron">
			<h1>Welcome to the JSP Shopper Store!</h1>
			<h3>You can view products and categories as guest. Or log in to
				start buying stuff!</h3>
		</div>
	</div>

	<%@ include file="footer.jsp"%>



</body>
</html>