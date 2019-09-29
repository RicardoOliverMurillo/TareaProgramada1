<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="careerLogic.Course"%>
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
				<li class="nav-item active"><a class="nav-link" href="StudentView.jsp">Study plans</a></li>
				<li class="nav-item active"><a class="nav-link" href="#">Reports</a></li>
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
		int passCredits = (int) request.getAttribute("passCredits");
		int totalCredits = (int) request.getAttribute("totalCredits");
		Course course = new Course();
	%>
	<br>
	<h5>Approved courses: <%=pass.size() %></h5>
	<h5>Approved credits: <%=passCredits %></h5>
	<h5>Total credits: <%=totalCredits %></h5>
	<br>
	<%
		if (result != null) {
	%>
	<form action="CourseController" method="GET">
		<table id= "plan" class="table table-borderless">
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
					<td><button name="course" type="submit" class="btn btn-success"
							formaction="CourseController" formmethod="GET"
							value=<%=result[row][col]%>><%=result[row][col]%></button></td>
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
</body>
</html>