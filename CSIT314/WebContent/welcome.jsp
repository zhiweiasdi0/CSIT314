<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Welcome to TravelX</title>
</head>
<body>

<% if(session.getAttribute("username") == null)
	{
		response.sendRedirect("login.jsp");
	}
	else
	{%>
	Welcome! ${username}
  <%} %>
  
<form action="TourControllerServlet" method="GET">
<input type="hidden" name="command" value="LIST">
<input type="submit" value="View Tour">
</form>

 			<c:url var="tempLink" value="UserControllerServlet">
			<c:param name="command" value="LOADUsername" />
			<c:param name="userName" value="${username}" />
			</c:url>

			<a href=${tempLink}>View Profile</a>
			
			
<form action="LoginServlet" method="post">
<input type="hidden" name="command" Value="LOGOUT">
<input type="submit" Value="Logout">
</form>

</body>

</html>