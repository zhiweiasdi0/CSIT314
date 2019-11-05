<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Tour View</title>
</head>
<body>


				   
	<form action="TourControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE" />

			<input type="hidden" name="tourId" value="${UPDATE_TOUR.id}" />
			
			<table>
				
					<tr>
						<td>Title:</td>
						<td><input type="text" name="title" 
								   value="${UPDATE_TOUR.title}" /></td>
					</tr>
					
					<tr>
						<td>Description:</td>
						<td><input type="text" name="description" 
								   value="${UPDATE_TOUR.description}" /></td>
					</tr>
					
					
					<tr>
						<td>Nationality:</td>
						<td><input type="text" name="nationality" 
								   value="${UPDATE_TOUR.nationality}"/></td>
					</tr>

					<tr>
						<c:url var="tempLink" value="UserControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="userId" value="${UPDATE_TOUR.tourguide_id}" />
						</c:url>
						<td><a href="${tempLink}">Tour Guide ID:</a></td>
					</tr>

					<tr>
						<td>Route Start:</td>
						<td><input type="text" name="route_start" 
								   value="${UPDATE_TOUR.route_start}" /></td>
					</tr>
					
					<tr>
						<td>Route End:</td>
						<td><input type="text" name="route_end" 
								   value="${UPDATE_TOUR.route_end}" /></td>
					</tr>
					
					<tr>
						<td>Date:</td>
						<td><input type="text" name="date" 
								   value="${UPDATE_TOUR.date}" /></td>
					</tr>
					
					<tr>
						<td>Capacity</td>
						<td><input type="text" name="capacity" 
								   value="${UPDATE_TOUR.capacity}" /></td>
					</tr>
					
					<tr>
						<td>Max Capacity</td>
						<td><input type="text" name="max_capacity" 
								   value="${UPDATE_TOUR.max_capacity}" /></td>
					</tr>
					
					<tr>
						<td>Rating</td>
						<td><input type="text" name="rating" 
								   value="${UPDATE_TOUR.rating}" /></td>
					</tr>
					
					<tr>
						<td>Delete_IND</td>
						<td><input type="text" name="delete_ind" 
								   value="${UPDATE_TOUR.delete_ind}" /></td>
					</tr>
					
					
					<tr>
						<td></td>
						<td><input type="submit" value="Save" /></td>
					</tr>
					
			
			</table>
		</form>
		
</body>
</html>