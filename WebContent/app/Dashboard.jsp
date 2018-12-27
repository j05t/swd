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


table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}

#content {
padding: 32px;

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
			
			var x = document.getElementsByClassName("active");
			var i;
			for (i = 0; i < x.length; i++) {
			  x[i].classList.remove("active");
			}
			
			document.getElementById(which).classList.add("active");	
		}
	</script>

	<div class="topnav">
		<a href="#" id="Home" onclick="loadDoc('Home')">Home</a> 
		<a href="#" id="Patienten" onclick="loadDoc('Patienten')">Patienten</a> 
		<a href="#" id="Termine" onclick="loadDoc('Termine')">Termine</a> 
		<a href="#" id="Personal" onclick="loadDoc('Personal')">Personal</a>
		
		<form id="logout" action="LogoutController" method="post">
			<input class="w3-button w3-red" type="submit" value="Logout">
		</form>
	</div>


	<%
		if (session != null) {
			if (session.getAttribute("role") != null) {
				String role = (String) session.getAttribute("role");
				
				out.print("<script>loadDoc('Home')</script>");

			} else {
				response.sendRedirect("/index.html");
			}
		}
	%>

	<div id="content"></div>


</body>
</html>