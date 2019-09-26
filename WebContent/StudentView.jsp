<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student View</title>
<!--BOOTSTRAP-->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<!--Navbar-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="StudentView.jsp">Student Page</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="#">2050 Plan</a></li>
				<li class="nav-item active"><a class="nav-link" href="#">2051 Plan</a></li>
				<li class="nav-item active"><a class="nav-link" href="#">Reports</a></li>
				<li class="nav-item active"><a class="nav-link" href="AddComment.jsp">Comment</a></li>
				<li class="nav-item active"><a class="nav-link" href="ViewInformation.jsp">Information</a></li>
			</ul>
			<ul class="navbar-nav ml-auto"> 
				<li class="nav-item active"><a class="nav-link" href="loginView.jsp">sign out</a></li>
			</ul>
		</div>
	</nav>
	<!--End of Navbar-->
</body>
</html>