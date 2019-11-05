
<html>
<head>
<title>View User</title>
</head>
<body>


		<form action="UserControllerServlet" method="GET">
		

			<input type="hidden" name="userId" value="${THE_USER.id}" />
			
			<table>
				
					<tr>
						<td>Username:</td>
						<td><input type="text" name="username" 
								   value="${THE_USER.username}" readonly/></td>
					</tr>
					
					<tr>
						<td>Password:</td>
						<td><input type="text" name="password" 
								   value="${THE_USER.password}"readonly /></td>
					</tr>
					
					
					<tr>
						<td>First name:</td>
						<td><input type="text" name="firstName" 
								   value="${THE_USER.firstName}"readonly /></td>
					</tr>

					<tr>
						<td>Last name:</td>
						<td><input type="text" name="lastName" 
								   value="${THE_USER.lastName}" readonly/></td>
					</tr>

					<tr>
						<td>Email:</td>
						<td><input type="text" name="email" 
								   value="${THE_USER.email}"readonly /></td>
					</tr>
					
					<tr>
						<td>Contact Number:</td>
						<td><input type="text" name="contactNumber" 
								   value="${THE_USER.contactNumber}"readonly /></td>
					</tr>
					
					
			
			</table>
			
		</form>
		
		
		<form action="TourControllerServlet" method="GET">
		

			<input type="hidden" name="command" value="LOADBOOKING" />
			
			<input type="submit" Value="View My Upcoming Tours">
					
					
		</form>
		
		
		
		
		
		
</body>
</html>