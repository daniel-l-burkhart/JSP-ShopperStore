<%@page import="java.util.ArrayList"%>
<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<%@page import="dao.CategoryDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head></head>

<%@ include file="header.jsp"%>

<body>


	<div class="container">
		<div class="jumbotron">
			<h1>View Categories</h1>
		</div>
	</div>

	<%
		CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
		ArrayList<Category> list = new ArrayList<Category>();
		list = categoryDAO.getCategories();
	%>
	<div class="container">
		<nav>
		<ul class="list-group">
			<%
				for (Category c : list) {
			%>
			<li class="list-group-item"><a
				href="products_by_category.jsp?categoryID=<%=c.getCategoryID()%>"> <i
					class="fafa-home"> <img src="images/<%=c.getCategoryID()%>.png">
				</i> <strong><%=c.getCategoryName()%></strong>
			</a></li>
			<%
				}
			%>
		</ul>
		</nav>
	</div>

	<%@ include file="footer.jsp"%>

</body>
</html>