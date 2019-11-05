<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Tour View</title>
</head>
<body>


				   
	<form action="TourControllerServlet" method="GET">
		<input type="hidden" name="command" value="BOOK" />
			<input type="hidden" name="tourId" value="${THE_TOUR.id}" />
			
			<table>
				
					<tr>
						<td>Title:</td>
						<td><input type="text" name="title" 
								   value="${THE_TOUR.title}" readonly /></td>
					</tr>
					
					<tr>
						<td>Description:</td>
						<td><input type="text" name="description" 
								   value="${THE_TOUR.description}" readonly /></td>
					</tr>
					
					
					<tr>
						<td>Nationality:</td>
						<td><input type="text" name="nationality" 
								   value="${THE_TOUR.nationality}" readonly/></td>
					</tr>

					<tr>
						<c:url var="tempLink" value="UserControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="userId" value="${THE_TOUR.tourguide_id}" />
						</c:url>
						<td><a href="${tempLink}">Tour Guide ID:</a></td>
					</tr>

					<tr>
						<td>Route Start:</td>
						<td><input type="text" name="route_start" 
								   value="${THE_TOUR.route_start}" readonly /></td>
					</tr>
					
					<tr>
						<td>Route End:</td>
						<td><input type="text" name="route_end" 
								   value="${THE_TOUR.route_end}"readonly /></td>
					</tr>
					
					<tr>
						<td>Date:</td>
						<td><input type="text" name="date" 
								   value="${THE_TOUR.date}" readonly/></td>
					</tr>
					
					<tr>
						<td>Capacity</td>
						<td><input type="text" name="capacity" 
								   value="${THE_TOUR.capacity}"readonly /></td>
					</tr>
					
					<tr>
						<td>Max Capacity</td>
						<td><input type="text" name="max_capacity" 
								   value="${THE_TOUR.max_capacity}"readonly /></td>
					</tr>
					
					<tr>
						<td>Rating</td>
						<td><input type="text" name="rating" 
								   value="${THE_TOUR.rating}" readonly/></td>
					</tr>
					
					<tr>
						<td>Delete_IND</td>
						<td><input type="text" name="delete_ind" 
								   value="${THE_TOUR.delete_ind}"readonly /></td>
					</tr>
					
					<tr>
						<input type="submit" value="BOOK">
					</tr>
							
							
							
			
			</table>
		</form>
		
</body>
</html>