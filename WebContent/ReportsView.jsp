<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="businessLogic.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="businessLogic.Plan" %>
<%@ page import="dao.DaoPlan" %>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View career information</title>
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
				<li class="nav-item active"><a class="nav-link"
					href="StudentView.jsp">Study plans</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="ReportsView.jsp">Reports</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="AddComment.jsp">Comment</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="ViewInformation.jsp">Information</a></li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link"
					href="loginView.jsp">sign out</a></li>
			</ul>
		</div>
	</nav>
	<!--End of Navbar-->
	<br>
	<% Plan plan = new Plan(); %>
	<% ArrayList<Plan> plans = (ArrayList<Plan>) plan.getAllPlans(); %>
	<div class="col-md-8 mx-auto">
		<div class="card">
			<div class="card-body">
				<h5 align="center">View report by plan</h5>
				<form action="CourseController" method="GET">
					<select class="custom-select" id="groupOptions1"
						name="planSelected">
						<option selected>Choose a plan...</option>
						<%for(int i = 0; i < plans.size(); i++){ %>
						<option value=<%=plans.get(i).getId()%>><%= plans.get(i).getId() %></option>
						<%} %>
					</select>
					<div>
						<button name="planReports" type="submit"
							class="btn btn-outline-info btn-block">Search</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<br>
	<%
		String pass = (String) request.getAttribute("passCourses");
		String passCredits = (String) request.getAttribute("passCredits");
		String totalCredits = (String) request.getAttribute("totalCredits");
		String pendingCourses = (String) request.getAttribute("pendingCourses");
	%>
	<%
		if (pass != null) {
	%>
	<div class="col-md-8 mx-auto">
		<div class="card">
			<div class="card-body">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>Courses Approved</th>
							<th>Total Credits</th>
							<th>Pending Courses</th>
							<th>Pass Credits</th>
						</tr>
					</thead>
					<tbody>
					<tbody>
						<tr>
							<td><%= pass %></td>
							<td><%= totalCredits %></td>
							<td><%= pendingCourses %></td>
							<td><%= passCredits %></td>
						</tr>
					</tbody>

					</tbody>
				</table>
				<%
		}
		%>
			</div>
		</div>
	</div>
	<div class="col-md-8 mx-auto">
		<div class="card">
			<div class="card-body">
				<h5 align="center">Send report by email</h5>
				<form action="CourseController" method="GET">
					<select class="custom-select" id="groupOptions1" name="languageSelected">
						<option selected>Choose a language...</option>
						<option value="spanish">Spanish</option>
						<option value="english">English</option>
						<option value="both">Both Languages</option>
					</select>
					<div>
						<button name="emailReport" type="submit" class="btn btn-outline-info btn-block">Send email</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>