<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Welcome to TravelX</title>
</head>
<body>

  
<form action="UserControllerServlet" method="GET">
<input type="hidden" name="command" value="LIST">
<input type="submit" value="View User">
</form>
			
			
<form action="LoginServlet" method="post">
<input type="hidden" name="command" Value="LOGOUT">
<input type="submit" Value="Logout">
</form>

</body>

</html>