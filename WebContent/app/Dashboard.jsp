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
	margin: 2px 2px;
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
	height: 80%;
	background: none;
	float: right;
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

tr {
	cursor: pointer;
}

tr:hover {
	background-color: #caffee;
}

.tooltip {
	position: relative;
	display: inline-block;
	border-bottom: 1px dotted black;
}

.tooltip .tooltiptext {
	visibility: hidden;
	width: 120px;
	background-color: #555;
	color: #fff;
	text-align: center;
	border-radius: 6px;
	padding: 5px 0;
	position: absolute;
	z-index: 1;
	bottom: 125%;
	left: 50%;
	margin-left: -60px;
	opacity: 0;
	transition: opacity 0.3s;
}

.tooltip .tooltiptext::after {
	content: "";
	position: absolute;
	top: 100%;
	left: 50%;
	margin-left: -5px;
	border-width: 5px;
	border-style: solid;
	border-color: #555 transparent transparent transparent;
}

.tooltip:hover .tooltiptext {
	visibility: visible;
	opacity: 1;
}

#content {
	padding: 32px;
}

#detail {
	padding: 32px;
}

/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
	border: 3px solid black;
}

/* Modal Content */
.modal-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	width: 500px;
}

/* Modal Content */
.modal-content label {
	width: 180px;
	clear: left;
	text-align: right;
	padding-right: 10px;
}

.modal-content input, label {
	float: left;
}

/* The Close Button */
.close {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}

#status {
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	background-color: #333;
	color: white;
	text-align: center;
}

input[type=button], input[type=submit], input[type=reset] {
	background-color: #4CAF50;
	border: none;
	color: white;
	padding: 16px 32px;
	text-decoration: none;
	margin: 2px 2px;
	cursor: pointer;
}

#newButton {
	cursor: pointer;
	background-color: #4CAF50; /* Green */
	border: none;
	color: white;
	margin: 8px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 24px;
	border-radius: 50%;
	height: 32px;
	width: 32px;
}

#editSubmit {
	cursor: pointer;
	background-color: #4CAF50; /* Green */
	border: none;
	color: white;
	text-align: center;
	text-decoration: none;
	font-size: 16px;
	margin-left: 32px;
}
</style>


</head>


<body>

	<div class="topnav">
		<a href="#" id="Patienten" onclick="loadDoc('Patienten')">Patienten</a>
		<a href="#" id="Termine" onclick="loadDoc('Termine')">Termine</a> <a
			href="#" id="Personal" onclick="loadDoc('Personal')">Personal</a>

		<form id="logout" action="LogoutController" method="post">
			<input class="w3-button w3-red" type="submit" value="Logout">
		</form>
	</div>


	<div id="content"></div>


	<!-- The Modal -->
	<div id="myModal" class="modal">
		<!-- Modal content -->
		<div id="editContent" class="modal-content"></div>
	</div>


	<div id="status" class="status"></div>


	<script type="text/javascript">
		function loadDoc(which, where) {
			where = where || "content";

			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					document.getElementById(where).innerHTML = this.responseText;
				}
			};

			xhttp.open("GET", which, true);
			xhttp.send();

			if (where == "content") {
				var x = document.getElementsByClassName("active");
				for (var i = 0; i < x.length; i++) {
					x[i].classList.remove("active");
				}

				document.getElementById(which).classList.add("active");
			}
		}

		function sendPatientForm() {
			var modal = document.getElementById('myModal');
			
			var id = document.getElementById('id').value;
			var create = document.getElementById('create').value;
			var first_name = document.getElementById('first_name').value;
			var last_name = document.getElementById('last_name').value;
			var age = document.getElementById('age').value;
			var admission_date = document.getElementById('admission_date').value;
			var comment = document.getElementById('comment').value;

			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {

					document.getElementById('status').innerHTML = this.responseText;
					modal.style.display = "none";
					loadDoc('Patienten');
				}
			};
			xhttp.open("POST", 'EditPatient', true);
			xhttp.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");


			xhttp.send("id=" + id + "&first_name=" + first_name + "&create="
					+ create + "&last_name=" + last_name + "&age=" + age
					+ "&admission_date=" + admission_date + "&comment="
					+ comment);

			console.log("sent form via post");
		}

		// Get the modal
		var modal = document.getElementById('edit');

		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];

		// When the user clicks anywhere outside of the modal,
		// close dialog
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";

				//sendPatientForm();
			}
		}
	</script>

	<%
		if (session != null) {
			if (session.getAttribute("role") != null) {
				String role = (String) session.getAttribute("role");

				out.print("<script>loadDoc('Patienten')</script>");

			} else {
				response.sendRedirect("/index.html");
			}
		}
	%>

</body>
</html>