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
<link rel="stylesheet" href="css/detail.css" />
<link rel="stylesheet" href="css/menu.css" />
<style type="text/css">
#main {
	width: 1060px;
	padding: 0;
	margin-left: auto;
	margin-right: auto;
}

#head {
	height: 200px;
	background-color: #F5F5F5;
	border: 1px solid #CDCDCD;
	margin-bottom: 5px;
	margin-top: 5px;
}

#head-link {
	height: 50px;
	line-height: 30px;
	border: 1px solid #CDCDCD;
	background-color: #F5F5F5;
	margin-bottom: 5px;
	clear: both;
}

#content {
	width: 1060px;
	min-height: 430px;
	border: 1px solid #CDCDCD;
	float: left;
	margin-bottom: 5px;
	clear: both;
}

#footer {
	height: 50px;
	clear: both;
	border: 1px solid #CDCDCD;
	background-color: #F8F8FF;
	margin-bottom: 5px;
}
</style>
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

	<div id="content">

		<div class="left-1">
			<img
				src="productImages/<%=productDAO.getSingleProductFromID(Integer.parseInt(productCode)).getPictureName()%>"
				width="200px" height="200px" />
		</div>
		<div class="left-2">
			<table>
				<tr class="row1">
					<td class="col2" colspan="2"
						style="padding: 10px; color: blue; font-size: 15px; text-transform: uppercase; text-align: center; font-weight: bold">
						<%=productDAO.getSingleProductFromID(Integer.parseInt(productCode)).getProductName()%>
					</td>
				</tr>
				<tr class="row2">
					<td class="col1">Manufacturer:</td>
					<td class="col2"><%=productDAO.getSingleProductFromID(Integer.parseInt(productCode)).getManufacturer()%></td>
				</tr>

				<tr class="row2">
					<td class="col1">Price:</td>
					<td class="col2"><%=nf.format(productDAO.getSingleProductFromID(Integer.parseInt(productCode)).getPrice())%>
					</td>
				</tr>

			</table>
		</div>
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
