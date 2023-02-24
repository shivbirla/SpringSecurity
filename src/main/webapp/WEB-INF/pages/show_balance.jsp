<%@page import="java.util.*" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <h1 style="color: blue;text-align: center">Show Balance Page</h1>
    <b> Balance :: <%=new Random().nextInt(10000) %></b>
    <a href="./">Home</a>
	<br>
	<a href="signout">signout</a>
</body>
</html>