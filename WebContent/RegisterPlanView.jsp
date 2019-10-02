<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="careerLogic.Plan"%>
<%@ page import="careerLogic.Career" %>
<%@ page import="dao.DaoCareer" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Plan</title>
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
					href="RegisterCourse.jsp">Course Register</a></li>
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
	<%DaoCareer db = new DaoCareer(); %>
	<%ArrayList<Career> careerList = db.selectQueryCareer("SELECT * FROM CAREERS");%>
	<%ArrayList planList = (ArrayList) request.getAttribute("listPlans");%>
	<!--End of Navbar-->
	<br>
	<div class="col-md-6 mx-auto">
		<div class="card">
			<div class="card-body">
			<h4 class="text-center">Register Plan</h4>
			<br>
				<form action="PlanController" method="POST">				
					<div class="form-group">
						<input type="text" name="idPlan" placeholder="ID"
							class="form-control">
					</div>
					<select class="custom-select" name="idCareer">
						<option selected>Choose a career...</option>
						<% for (int i = 0; i < careerList.size(); i++) { %>
							<option value=<%=careerList.get(i).getId()%>><%=careerList.get(i).getId()%></option>
						<% } %>
					</select>
					<div>
						<button name = "addPlan" type="submit" class="btn btn-success btn-block">Confirm</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<br>
	<div class="col-md-6 mx-auto">
		<div class="card">
			<div class="card-body">
				<form action="PlanController" method="POST">
					<select class="custom-select" name="careerOption">
						<option selected>Choose a career...</option>
						<% for (int i = 0; i < careerList.size(); i++) { %>
							<option value=<%=careerList.get(i).getId()%>><%=careerList.get(i).getId()%></option>
						<% } %>
					</select>
					<div>
						<button name = "allPlans" type="submit" class="btn btn-outline-secondary btn-block">Show all plans</button>
					</div>
					<br>
					<% if (planList != null) { %>
					<table class="table table-sm col-md-12 mx-auto">
						<thead>
							<tr>
								<th scope="col">PLAN</th>
							</tr>
						</thead>
						<tbody>
							<% for (int i = 0; i < planList.size(); i++) { %>
							<% Plan plan = (Plan) planList.get(i); %>
								<tr>
									<td><%= plan.getId() %></td>
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