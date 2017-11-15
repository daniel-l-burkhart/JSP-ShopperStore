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
	<div class="container">

		<div class="jumbotron">
			<h2>Product Details</h2>
		</div>
	</div>

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

		int productCodeInt = Integer.parseInt(productCode);
	%>
	<div class="container">

		<div class="row">
			<div class="col-lg-4">

				<img
					src="productImages/<%=productDAO.getSingleProductFromID(productCodeInt).getPictureName()%>"
					width="260px" />
			</div>

			<div class="col-lg-8">

				<table class="table table-responsive">
					<tr>
						<td>Product Name</td>
						<td><%=productDAO.getSingleProductFromID(productCodeInt).getProductName()%>
						</td>
					</tr>
					<tr>
						<td>Manufacturer:</td>
						<td><%=productDAO.getSingleProductFromID(productCodeInt).getManufacturer()%></td>
					</tr>

					<tr>
						<td>Price:</td>
						<td><%=productDAO.getSingleProductFromID(productCodeInt).getPrice()%>
						</td>
					</tr>

					<tr>
						<td>Information:</td>
						<td><%=productDAO.getSingleProductFromID(productCodeInt).getInformation()%></td>
					</tr>

				</table>

				<%
					if (user != null) {
				%>
				<div class="pull-right">
					<form action="CartServlet" method="post">
						<div class="form-group">
							<label for="number">Quantity: </label> <input
								class="form-control" type="number" min="1" value="1"
								name="number" /> <input type="hidden" value="setCart"
								name="command" /> <input type="hidden" value="<%=productCode%>"
								name="productCode" />
						</div>

						<button class="btn btn-success" type="submit">Add to Cart</button>
					</form>
				</div>
				<%
					} else {
				%>


				<div class="pull-right">
					<a class="btn btn-primary" href="login.jsp">Login to add to
						cart!</a>

				</div>
				<%
					}
				%>
			</div>
		</div>

	</div>





</body>
<%@ include file="footer.jsp"%>
</html>
