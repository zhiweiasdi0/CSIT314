<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>View Bookings</title>
</head>
<body>

<table border='1'>
<tr>
	<td>Id</td>
	<td>Userid</td>
	<td>Tourid</td>
	<td>Status</td>
	<td>Action</td>
</tr>
<c:forEach var="tempBook" items="${BOOK_LIST}">
	
					
	<tr>
		<td>${tempBook.id}</td>
		<td>${tempBook.userid}</td>
		<td>${tempBook.tourid}</td>
		<td>${tempBook.status}</td>
		<td>
		
		<c:url var="tempLink" value="TourControllerServlet">
			<c:param name="command" value="LOAD" />
			<c:param name="tourId" value="${tempBook.tourid}" />
			</c:url>
			
			<a href="${tempLink}">VIEW</a> |
			
		</td>
		
		
	</tr>
		
		
	
	
</c:forEach>
</table>


</body>
</html>