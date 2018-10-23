<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import = "db.methods" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Deletion</title>
</head>
<body>
<form>
<input type="text" name="username">
<input type="submit" value="Delete" name="deletion">
<%
if(request.getParameter("deletion")!=null){
	int x;
	x = methods.delete(request);
	if(x>0){
		response.sendRedirect("success.jsp");
	}
	else {
		response.sendRedirect("failure.jsp");
	}
}	
%> 
</form>
</body>
</html>