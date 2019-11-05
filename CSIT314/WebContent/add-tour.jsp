<html>
<head>

<title>Add Tour</title>
</head>
<body>


<form action="TourControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="ADD" />
			
			<table>
		
					<tr>
						<td>Title:</td>
						<td><input type="text" name="title"  /></td>
					</tr>
					
					<tr>
						<td>Description:</td>
						<td><input type="text" name="description"  /></td>
					</tr>
					
					
					<tr>
						<td>Nationality:</td>
						<td><input type="text" name="nationality"  /></td>
					</tr>

					<tr>
						<td>Tour Guide ID:</td>
						<td><input type="text" name="tourguide_id"  value="${username}"/></td>
					</tr>

					<tr>
						<td>Route Start:</td>
						<td><input type="text" name="route_start"  /></td>
					</tr>
					
					<tr>
						<td>Route End:</td>
						<td><input type="text" name="route_end"  /></td>
					</tr>
					
					<tr>
						<td>Date:</td>
						<td><input type="datetime-local" name="date" /></td>
					</tr>
					
					<tr>
						<td>Capacity</td>
						<td><input type="text" name="capacity"/></td>
					</tr>
					
					<tr>
						<td>Max Capacity</td>
						<td><input type="text" name="max_capacity"  /></td>
					</tr>
					
					<tr>
						<td>Rating</td>
						<td><input type="text" name="rating" /></td>
					</tr>
					
					<tr>
						<td>Delete_IND</td>
						<td><input type="text" name="delete_ind" /></td>
					</tr>
					
					
					<tr>
						<td></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
			
			</table>
		</form>
		
</body>
</html>