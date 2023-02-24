<%@page import="java.util.Random"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1 style="color: blue; text-align: center">Loan Approval Page</h1>
	<b>Your are approved for loan amount :: <%=new Random().nextInt(1000000)%></b>
	<a href="./">Home</a>
	<br>
<!-- 	<a href="logout">logout</a> -->
	<a href="signout">signout</a>
</body>
</html>