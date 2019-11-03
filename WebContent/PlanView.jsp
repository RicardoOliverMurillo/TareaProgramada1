<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="businessLogic.career.Course"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Plan information</title>

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
				<li class="nav-item active"><a class="nav-link"
					href="StudentView.jsp">Study plans</a></li>
				<li class="nav-item active"><a class="nav-link" href="ReportsView.jsp">Reports</a></li>
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
	<%
		String[][] result = (String[][]) request.getAttribute("result");
		ArrayList<String> pass = (ArrayList<String>) request.getAttribute("pass");
		String passCredits = (String) request.getAttribute("passCredits");
		String totalCredits = (String) request.getAttribute("totalCredits");
		String plan = (String) request.getAttribute("planId");
		Course course = new Course();
	%>
	<%
		if (result != null) {
	%>
	<br>
	<h3 align="center" class="font-weight-bold text-center">Study plan #<%=plan %></h3>
	<br>
	<form action="CourseController" method="GET">
		<table id="plan" class="table table-borderless">
			<thead>
				<tr>
					<th>Semester 0</th>
					<th>Semester 1</th>
					<th>Semester 2</th>
					<th>Semester 3</th>
					<th>Semester 4</th>
					<th>Semester 5</th>
					<th>Semester 6</th>
					<th>Semester 7</th>
					<th>Semester 8</th>
					<th>Semester 9</th>
					<th>Semester 10</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (int row = 0; row < 10; row++) {
				%>
				<tr>
					<%
						for (int col = 0; col < 11; col++) {
					%>
					<%
						if (result[row][col] != null) {
					%>
					<%
						if (course.isAprove(pass, result[row][col])) {
					%>
					<td><button name="course" type="submit"
							class="btn btn-success" formaction="CourseController"
							formmethod="GET" value=<%=result[row][col]%>><%=result[row][col]%></button></td>
					<%
						} else {
					%>
					<td><button name="course" type="submit" class="btn btn-info"
							formaction="CourseController" formmethod="GET"
							value=<%=result[row][col]%>><%=result[row][col]%></button></td>
					<%
						}
					%>

					<%
						} else {
					%>
					<td></td>
					<%
						}
					%>
					<%
						}
					%>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<%}%>
	</form>
	<div class="container">
		<div class="row">
			<div class="col-3">
				<h5 class="font-weight-bold text-left">General information</h5>
				<h5 class="text-left">
					Approved courses:
					<%=pass.size()%></h5>
				<h5 class="text-left">
					Approved credits:
					<%=passCredits%></h5>
				<h5 class="text-left">
					Total credits:
					<%=totalCredits%></h5>
				<h6 class="font-weight-bold text-left">Color code</h6>
				<h6 class="text-left text-success">Approved courses</h6>
				<h6 class="text-left text-info">Pending courses</h6>
			</div>
			<div class="col-9">
				<div class="row">
					<div class="col">
					<form action="ApprovedCoursesController" method="POST">
						<h5  class="font-weight-bold">Select by semester</h5>
						<div>
							<select class="custom-select" name="ApprovedSem">
								<option selected>Choose a semester...</option>
								<%for(int i = 0; i <= 10; i++){ %>
								<option value=<%=i %>><%=i %></option>
								<%} %>
							</select>
						</div>
						<br>
						<button name="addSem" type="submit" class="btn btn-outline-success"  value=<%=plan %>>add</button>
						<button name="removeSem" type="submit" class="btn btn-outline-danger"  value=<%=plan %>>remove</button>
					</form>
					</div>
					<div class="col">
					<form action="ApprovedCoursesController" method="POST">
						<h5 class="font-weight-bold">Select by knowledge area</h5>
						<div>
							<select class="custom-select" name="ApprovedArea">
								<option selected>Choose a knowledge area...</option>
								<option value="Plataforma Tecnológica de las Organizaciones">Plataforma
									Tecnológica de las Organizaciones</option>
								<option value="Fundamentos de las Organizaciones">Fundamentos
									de las Organizaciones</option>
								<option value="Conocimientos y Competencias Fundamentales">Conocimientos
									y Competencias Fundamentales</option>
								<option value="Desarrollo Tecnológico Empresarial">Desarrollo
									Tecnológico Empresarial</option>
								<option value=" ">Sin área</option>
							</select>
						</div>
						<br>
						<button name="addArea" type="submit" class="btn btn-outline-success" value=<%=plan %>>add</button>
						<button name="removeArea" type="submit" class="btn btn-outline-danger" value=<%=plan %>>remove</button>
					</form>
					</div>
					<form action="ApprovedCoursesController" method="POST">
					<div class="col">
						<h5 class="font-weight-bold">Select by plan</h5>
						<button name="addPlan" type="submit" class="btn btn-outline-success" value=<%=plan %>>add</button>
						<button name="removePlan" type="submit" class="btn btn-outline-danger" value=<%=plan %>>remove</button>
					</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<br>
</body>
</html>