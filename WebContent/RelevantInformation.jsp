<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Relevant Information</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
</head>
<body>
	<!--Navbar-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="AdminView.jsp">Admin Page</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">

				<li class="nav-item active"><a class="nav-link"
					href="CommentAnalysis.jsp">Comment analysis</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="RelevantInformation.jsp">Information</a></li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link"
					href="loginView.jsp">sign out</a></li>
			</ul>
		</div>
	</nav>
	<br>
	<h4 class="text-center">Relevant information</h4>
	<div class="row">
		<div class="col-sm-4">
			<div class="card">
				<div class="card-body">
					<form action="InformationController" method="POST">
						<h5 class="card-title">Employment Area</h5>
						<div>
							<button name="addEA" type="submit"
								class="btn btn-outline-info btn-block">Add info</button>
						</div>
						<br>
						<div>
							<button name="updateEA" type="submit"
								class="btn btn-outline-info btn-block">Update</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="card">
				<div class="card-body">
					<form action="InformationController" method="POST">
						<h5 class="card-title">Student Profile</h5>
						<div>
							<button name="addSP" type="submit"
								class="btn btn-outline-info btn-block">Add info</button>
						</div>
						<br>
						<div>
							<button name="updateSP" type="submit"
								class="btn btn-outline-info btn-block">Update</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="card">
				<div class="card-body">
					<form action="InformationController" method="POST">
						<h5 class="card-title">Major Characteristics</h5>
						<div>
							<button name="addMC" type="submit"
								class="btn btn-outline-info btn-block">Add info</button>
						</div>
						<br>
						<div>
							<button name="updateMC" type="submit"
								class="btn btn-outline-info btn-block">Update</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>