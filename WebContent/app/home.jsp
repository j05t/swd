<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
}

.topnav {
	overflow: hidden;
	background-color: #333;
}

.topnav a {
	float: left;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.topnav a:hover {
	background-color: #ddd;
	color: black;
}

.topnav a.active {
	background-color: #4CAF50;
	color: white;
}

#logout {
	background: none;
	float: right;
	color: #f2f2f2;
	text-align: center;
	padding: 10px;
	text-decoration: none;
	font-size: 17px;
}
</style>
</head>
<body>

	<script>
		function loadDoc(which) {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					document.getElementById("content").innerHTML = this.responseText;
				}
			};

			xhttp.open("GET", which, true);
			xhttp.send();
		}
	</script>

	<div class="topnav">
		<a class="active" href="#">Home</a> <a href="#"
			onclick="loadDoc('Patienten')">Patienten</a> <a href="#"
			onclick="loadDoc('Termine')">Termine</a> <a href="#"
			onclick="loadDoc('Personal')">Personal</a>
		<form id="logout" action="LogoutController" method="post">
			<input class="w3-button w3-red" type="submit" value="Logout">
		</form>
	</div>


	<%
		if (session != null) {
			if (session.getAttribute("role") != null) {
				String role = (String) session.getAttribute("role");
				out.print("<h2>Hello, " + role + "  Welcome to your Dashboard</h2>");
				out.print("  <p>Some content..</p>");
			} else {
				response.sendRedirect("/index.html");
			}
		}
	%>

	<div id="content" style="padding-left: 16px"></div>


</body>
</html>