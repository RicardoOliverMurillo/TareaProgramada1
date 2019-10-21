<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="businessLogic.Career"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Career</title>
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
					href="RegisterCareerView.jsp">Career Register</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="RegisterPlanView.jsp">Plan Register</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="AdminView.jsp">Course Register</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="RegisterEquivalencesView.jsp">Equivalences Register</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="CommentAnalysis.jsp">Comment analysis</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="RelevantInformation.jsp">Information</a></li>
			</ul>
			<ul class="navbar-nav ml-auto"> 
				<li class="nav-item active"><a class="nav-link" href="loginView.jsp">sign out</a></li>
			</ul>
		</div>
	</nav>
	<%
		ArrayList careerList = (ArrayList) request.getAttribute("list");
	%>
	<!--End of Navbar-->
	<br>
	<div class="col-md-6 mx-auto">
		<div class="card">
			<div class="card-body">
			<h4 class="text-center">Register Career</h4>
			<br>
				<form action="CareerController" method="POST">				
					<div class="form-group">
							<input type="text" name="id" placeholder="ID"
								class="form-control">
						</div>
						<div class="form-group">
							<input type="text" name="name" placeholder="Name"
								class="form-control">
						</div>
						<div>
							<button name = "add" type="submit" class="btn btn-success btn-block">Confirm</button>
						</div>
				</form>
			</div>
		</div>
	</div>
	<br>
	<div class="col-md-6 mx-auto">
		<div class="card">
			<div class="card-body">
				<form action="CareerController" method="POST">
					<div>
						<button name = "allCareers" type="submit" class="btn btn-outline-secondary btn-block">Show all careers</button>
					</div>
					<br>
					<% if (careerList != null) { %>
					<table class="table table-sm col-md-12 mx-auto">
						<thead>
							<tr>
								<th scope="col">ID</th>
								<th scope="col">NAME</th>
							</tr>
						</thead>
						<tbody>
							<% for (int i = 0; i < careerList.size(); i++) { %>
							<% Career career = (Career) careerList.get(i); %>
								<tr>
									<td><%= career.getId() %></td>
									<td><%= career.getName() %></td>
								</tr>
							<% } %>
						</tbody>
					</table>
					<% } %>
				</form>
			</div>
		</div>
	</div>
	<br>
</body>
</html>