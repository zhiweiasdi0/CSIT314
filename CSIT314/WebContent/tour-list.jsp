<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Tour List</title>
</head>
<body>

<form action="TourControllerServlet" method="GET">
<input type="hidden" name="command" value="SEARCH" />
<input type="text" name="SearchText">
<input type="submit" value="Search"  >
</form>
<% if(session.getAttribute("username") == null)
	{
		response.sendRedirect("login.jsp");
	}
	else
	{%>
	Welcome! ${username}
  <%} %>
	

<input type="button" value="Add Tour" 
				   onclick="window.location.href='add-tour.jsp'; return false;"/>
<table border='1'>
<tr>
	<td>Id</td>
	<td>Title</td>
	<td>Description</td>
	<td>Nationality</td>
	<td>Tour Guide ID</td>
	<td>Route Start</td>
	<td>Route End</td>
	<td>Date</td>
	<td>Capacity</td>
	<td>max_capacity</td>
	<td>Rating</td>
	<td>Delete_IND</td>
	<td>Action</td>
</tr>
<c:forEach var="tempTour" items="${TOUR_LIST}">
	
					
	<tr>
		<td>${tempTour.id}</td>
		<td>${tempTour.title}</td>
		<td>${tempTour.description}</td>
		<td>${tempTour.nationality}</td>
		<td>${tempTour.tourguide_id}
		
		</td>
		<td>${tempTour.route_start}</td>
		<td>${tempTour.route_end}</td>
		<td>${tempTour.date}</td>
		<td>${tempTour.capacity}</td>
		<td>${tempTour.max_capacity}</td>
		<td>${tempTour.rating}</td>
		<td>${tempTour.delete_ind}</td>
		<td>
		
 			<c:url var="tempLink" value="TourControllerServlet">
			<c:param name="command" value="LOAD" />
			<c:param name="tourId" value="${tempTour.id}" />
			</c:url>
			
			<a href="${tempLink}">VIEW</a> |
			
			
			<c:url var="updateLink" value="TourControllerServlet">
			<c:param name="command" value="LOAD2UPDATE" />
			<c:param name="tourId" value="${tempTour.id}" />
			</c:url>
			
			<a href="${updateLink}">UPDATE</a> |
			<%-- <c:url var="deleteLink" value="UserControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="userId" value="${tempUser.id}" />
					</c:url>
					
			
			<a href="${deleteLink}"
			onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">
			Delete</a>	 --%> 
		</td>
	</tr>
	
	
</c:forEach>
</table>

<form action="LoginServlet" method="POST">
<input type="hidden" name="command" Value="LOGOUT">
<input type="submit" Value="Logout">
</form>
</body>
</html>