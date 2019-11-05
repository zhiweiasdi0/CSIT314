
<html>
<head>
<title>View User</title>
</head>
<body>


		<form action="UserControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE" />

			<input type="hidden" name="userId" value="${UPDATE_USER.id}" />
			
			<table>
				
					<tr>
						<td>Username:</td>
						<td><input type="text" name="username" 
								   value="${UPDATE_USER.username}" /></td>
					</tr>
					
					<tr>
						<td>Password:</td>
						<td><input type="text" name="password" 
								   value="${UPDATE_USER.password}" /></td>
					</tr>
					
					
					<tr>
						<td>First name:</td>
						<td><input type="text" name="firstName" 
								   value="${UPDATE_USER.firstName}" /></td>
					</tr>

					<tr>
						<td>Last name:</td>
						<td><input type="text" name="lastName" 
								   value="${UPDATE_USER.lastName}" /></td>
					</tr>

					<tr>
						<td>Email:</td>
						<td><input type="text" name="email" 
								   value="${UPDATE_USER.email}" /></td>
					</tr>
					
					<tr>
						<td>Contact Number:</td>
						<td><input type="text" name="contactNumber" 
								   value="${UPDATE_USER.contactNumber}" /></td>
					</tr>
					
					
					<tr>
						<td></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
			
			</table>
		</form>
		
</body>
</html>