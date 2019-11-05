<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>List Users</title>
</head>
<body>


<form action="UserControllerServlet" method="GET">
<input type="hidden" name="command" value="SEARCH" />
<input type="text" name="SearchText">
<input type="submit" value="Search"  >
 </form>
			 
<table border='1'>
<tr>
	<td>Id</td>
	<td>User Name</td>
	<td>Password</td>
	<td>First Name</td>
	<td>Last Name</td>
	<td>Email</td>
	<td>Contact Number</td>
	<td>Action</td>
</tr>
<c:forEach var="tempUser" items="${USER_LIST}">
	
					
	<tr>
		<td>${tempUser.id}</td>
		<td>${tempUser.username}</td>
		<td>${tempUser.password}</td>
		<td>${tempUser.firstName}</td>
		<td>${tempUser.lastName}</td>
		<td>${tempUser.email}</td>
		<td>${tempUser.contactNumber}</td>
		<td>
		
			<c:url var="tempLink" value="UserControllerServlet">
			<c:param name="command" value="LOAD" />
			<c:param name="userId" value="${tempUser.id}" />
			</c:url>
			
			<c:url var="updateLink" value="UserControllerServlet">
			<c:param name="command" value="LOAD2UPDATE" />
			<c:param name="userId" value="${tempUser.id}" />
			</c:url>
			
			
			<c:url var="deleteLink" value="UserControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="userId" value="${tempUser.id}" />
					</c:url>
			
			<a href="${tempLink}">VIEW</a> |
			<a href="${updateLink}">Update</a> |
			<a href="${deleteLink}"
			onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">
			Delete</a>	
		</td>
	</tr>
	
	
</c:forEach>
</table>
</body>
</html>