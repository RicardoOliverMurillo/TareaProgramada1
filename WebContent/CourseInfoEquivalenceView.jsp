<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="businessLogic.Course"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Course information</title>
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
	<%
		Course course = (Course) request.getAttribute("data");
	%>
	<%
		ArrayList<String> req = (ArrayList<String>) request.getAttribute("requirements");
	%>
	<%
		ArrayList<String> coreq = (ArrayList<String>) request.getAttribute("corequirements");
	%>
	<!--End of Navbar-->
	<br>
	<div class="col-md-8 mx-auto">
		<div class="card">
			<div class="card-body">
				<h5>Course information</h5>
				<br>
				<ul class="list-group">
					<li class="list-group-item">Course id: <%=course.getId()%></li>
					<li class="list-group-item">Name: <%=course.getName()%></li>
					<li class="list-group-item">Credits: <%=course.getSumCredits()%></li>
					<li class="list-group-item">Semester: <%=course.getSemester()%></li>
					<li class="list-group-item">Knowledge Area: <%=course.getKnowledgeArea()%></li>
				</ul>
				<br>
				<div>
					<ul class="list-group">
						<li class="list-group-item">REQUISITES</li>
						<%
							for (int i = 0; i < req.size(); i++) {
						%>
						<li class="list-group-item list-group-item-success">Course
							id: <%=req.get(i)%></li>
						<%
							}
						%>
					</ul>
				</div>
				<br>
				<div>
					<ul class="list-group">
						<li class="list-group-item">COREQUISITES</li>
						<%
							for (int i = 0; i < coreq.size(); i++) {
						%>
						<li class="list-group-item list-group-item-info">Course id: <%=coreq.get(i)%></li>
						<%
							}
						%>
					</ul>
				</div>
				<br>
				<br>
				<div>
					<form action="CourseController" method="GET">
						<button name="translateCourse" value="<%=course.getName()%>" type="submit" class="btn btn-info btn-block">Translate</button>
					</form>
				</div>
				<br>
			</div>
		</div>
	</div>
</body>
</html>