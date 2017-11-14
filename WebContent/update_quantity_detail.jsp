<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="dao.ProductDAOImpl"%>
<%@page import="model.User"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Quantity of product</title>
<%@ include file="header.jsp"%>

</head>
<body>

	<div class="container">
		<div class="jumbotron">
			<h3>Update product quantity</h3>
		</div>



		<%
			//NEW QUANTITY SHOULDN"T BE ADDED BUT REPLACED

			ProductDAOImpl productDAO = new ProductDAOImpl();
			if (username != null && sessionUser != null && (sessionUser.getRole().equals("1"))) {

				String productCode = "";
				String user = null;
				int productCodeInt = 0;
				if (request.getParameter("productCode") != null) {
					productCode = request.getParameter("productCode");
					productCodeInt = Integer.parseInt(productCode);
				}
		%>

		<table class="table table-responsive">

			<tr>
				<td><strong>Product Name:</strong></td>
				<td colspan="2"><%=productDAO.getSingleProductFromID(productCodeInt).getProductName()%>
				</td>
			</tr>
			<tr>
				<td><strong>Manufacturer:</strong></td>
				<td><%=productDAO.getSingleProductFromID(productCodeInt).getManufacturer()%></td>
			</tr>

			<tr>
				<td><strong>Price:</strong></td>
				<td><%=productDAO.getSingleProductFromID(productCodeInt).getPrice()%>
				</td>
			</tr>
			<tr>
				<td><strong>Quantity:</strong></td>
				<td><%=productDAO.getSingleProductFromID(productCodeInt).getQuantity()%>
				</td>
			</tr>

			<tr>
				<td><strong>New Quantity:</strong></td>
				<td>

					<form action="UpdateQuantity" method="post">
						<div class="form-group">
							<input type="number" class="form-control" name="newQuantity">
							<input type="hidden" name="productCode" value="<%=productCode%>">
						</div>
						<button type="submit" class="btn btn-primary">Update
							Quantity</button>
					</form>
				</td>
			</tr>
		</table>

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