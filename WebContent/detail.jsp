<%@page import="dao.ProductDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product Details</title>

<%@ include file="header.jsp"%>
</head>
<body>

	<%
		String productCode = "";
		String user = null;
		if (request.getParameter("productCode") != null) {
			productCode = request.getParameter("productCode");
		}

		if (request.getSession().getAttribute("user") != null) {
			User loggedInUser = (User) request.getSession().getAttribute("user");
			user = loggedInUser.getEmail();
		}

		ProductDAOImpl productDAO = new ProductDAOImpl();
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(0);
	%>

	<img
		src="productImages/<%=productDAO.getSingleProductFromID(Integer.parseInt(productCode)).getPictureName()%>"
		width="200px" height="200px" />


	<table>
		<tr>
			<td><%=productDAO.getSingleProductFromID(Integer.parseInt(productCode)).getProductName()%>
			</td>
		</tr>
		<tr>
			<td class="col1">Manufacturer:</td>
			<td class="col2"><%=productDAO.getSingleProductFromID(Integer.parseInt(productCode)).getManufacturer()%></td>
		</tr>

		<tr>
			<td class="col1">Price:</td>
			<td class="col2"><%=nf.format(productDAO.getSingleProductFromID(Integer.parseInt(productCode)).getPrice())%>
			</td>
		</tr>

	</table>

	<%
		if (user != null) {
	%>
	<div
		style="margin-left: auto; margin-right: auto; text-align: center; margin-top: 10px; padding: 10px; clear: both;">
		<a href="#"><img src="images/cart.png" /></a>
		
		<form action="CartServlet" method="post">
			<input type="number" min="1" value="1" name="number" /> <input
				type="hidden" value="setCart" name="command" /> <input
				type="hidden" value="<%=productCode%>" name="productCode" /> <input
				type="submit" value="Add to cart">
		</form>
	</div>
	<%
		} else {
	%>
	<div
		style="margin-left: auto; margin-right: auto; text-align: center; margin-top: 10px; padding: 10px; clear: both;">
		<a href="login.jsp"><img src="images/giohang.png" /></a>
	</div>
	<%
		}
	%>
	<div class="left-3">
		<article> <input type="checkbox" id="read_more"
			role="button"> <label for="read_more" onclick=""
			style="width: 770px; margin-left: 150px; margin-right: auto;">
			<span>View Details</span> <span>Close</span>
		</label> <section>
		<table>


			<tr rowspan="2">
				<td class="detail-1">Details</td>
				<td class="detail-2">Update</td>
			</tr>


		</table>
		</section> </article>
	</div>

	</div>

</body>
<%@ include file="footer.jsp"%>
</html>
