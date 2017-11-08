<%@page import="java.util.ArrayList"%>
<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<%@page import="dao.CategoryDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="header.jsp"%>

</head>
<body>


	<%
		CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
		ArrayList<Category> list = new ArrayList<Category>();
		list = categoryDAO.getCategories();
	%>
	<div class="container">
		<nav>
		<ul class="mcd-menu">
			<%
				for (Category c : list) {
			%>
			<li><a href="index.jsp?categoryID=<%=c.getCategoryID()%>"> <i
					class="fafa-home"> <img src="images/<%=c.getCategoryID()%>.png">
				</i> <strong><%=c.getCategoryName()%></strong>
			</a></li>
			<%
				}
			%>
		</ul>
		</nav>
	</div>
</body>
</html>