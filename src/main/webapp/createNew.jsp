<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div align="center">
		<h1>Create New Customer</h1>
		<form action="<%=request.getContextPath()%>/create" method="post">
			<table style="with: 100%">
				<tr>
					<td>First Name</td>
					<td><input type="text" name="first_name" placeholder ="Enter your name" required /></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><input type="text" name="last_name" placeholder = "Enter your name" required /></td>
				</tr>
				<tr>
					<td>Street Name</td>
					<td><input type="text" name="street_name" placeholder = "Enter your street name" /></td>
				</tr>
				
				<tr>
					<td>Address</td>
					<td><input type="text" name="address_name" placeholder = "Enter your address" /></td>
				</tr>
				
				<tr>
					<td>City Name</td>
					<td><input type="text" name="city_name" placeholder = "Enter your city" /></td>
				</tr>
				<tr>
					<td>State</td>
					<td><input type="text" name="state_name" placeholder = "Enter your state" /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="email_name" placeholder = "Enter your email" /></td>
				</tr>
				<tr>
					<td>Phone Number</td>
					<td><input type="text" name="phone_name" placeholder = "Enter your number" /></td>
				</tr>
			</table>
			<input type="submit" value="Submit" />
		</form>
	</div>
</body>
</html>