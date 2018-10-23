<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "db.methods" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insertion</title>
</head>
<body>
<form>
<input type="text" name="username">
<input type="password" name="password">
<input type="submit" value="Insert" name="insertion">
<%
if(request.getParameter("insertion")!=null){
	int x;
	x = methods.insert(request);
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