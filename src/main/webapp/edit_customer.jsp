<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="net.customer_list.loginbean.customer"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>edit customer info</title>
<script>
function updateInput(field,ish){
    document.getElementById(field).value = ish;
}
</script>
</head>
<body>
<div align="center">
		<h1>Edit Customer</h1>
		<form action="<%=request.getContextPath()%>/EditServlet" method="post">
		<% customer Customer = (customer)request.getAttribute("data"); %>
			<table style="with: 100%">
				<tr>
					<td>First Name</td>
					<td><input type="text" id ="first" name="first_name" placeholder ="Enter your name" required
					 value ="<%= Customer.getFirst_name() %>" onchange = "updateInput(first,this.value)" /></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><input type="text" id ="last" name="last_name" placeholder = "Enter your name" required 
					value = "<%= Customer.getLast_name() %>" onchange = "updateInput(last,this.value)" /></td>
				</tr>
				<tr>
					<td>Street Name</td>
					<td><input type="text" id = "street" name="street_name" placeholder = "Enter your street name" 
					value="<%= Customer.getStreet() %>" onchange = "updateInput(street,this.value)" /></td>
				</tr>
				
				<tr>
					<td>Address</td>
					<td><input type="text" id ="address" name="address_name" placeholder = "Enter your address" 
					value = "<%= Customer.getAddress() %>" onchange = "updateInput(address,this.value)" /></td>
				</tr>
				
				<tr>
					<td>City Name</td>
					<td><input type="text" id ="city" name="city_name" placeholder = "Enter your city" 
					value="<%= Customer.getCity() %>" onchange = "updateInput(city,this.value)" /></td>
				</tr>
				<tr>
					<td>State</td>
					<td><input type="text" id ="state" name="state_name" placeholder = "Enter your state" 
					value ="<%= Customer.getState() %>" onchange = "updateInput(state,this.value)" /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" id ="email" name="email_name" placeholder = "Enter your email" 
					value ="<%= Customer.getEmail() %>" onchange = "updateInput(email,this.value)" /></td>
				</tr>
				<tr>
					<td>Phone Number</td>
					<td><input type="text" id ="phone" name="phone_name" placeholder = "Enter your number" 
					value = "<%= Customer.getPhone() %>" onchange = "updateInput(phone,this.value)" /></td>
				</tr>
			</table>
			<input type="submit" value="Submit" />
		</form>
	</div>

</body>
</html>