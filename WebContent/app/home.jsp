<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

    <%
		if (session != null) {
			if (session.getAttribute("role") != null) {
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
				response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
				response.setDateHeader("Expires", 0);
				
				String role = (String) session.getAttribute("role");
				out.print("Hello, " + role + "  Welcome to your Dashboard");
			} else {
				response.sendRedirect("/index.html");
			}
		}
	%>

	<form action="LogoutController" method="post">
		<input type="submit" value="Logout">
	</form>
</body>
</html>