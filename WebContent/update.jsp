<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "db.methods" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Updation</title>
</head>
<body>
<form>
<input type="text" name="username">
<input type="password" name="password">
<input type="submit" value="Update" name="updation">
<%
if(request.getParameter("updation")!=null){
	int x;
	x = methods.update(request);
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