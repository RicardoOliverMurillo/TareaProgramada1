<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="careerLogic.Plan"%>
<%@ page import="careerLogic.Course"%>
<%@ page import="dao.DaoPlan" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Equivalences</title>
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
					href="RegisterEquivalencesView.jsp">Equivalences Register</a></li>
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
	<%DaoPlan db = new DaoPlan(); %>
	<%ArrayList<Plan> planList = db.selectQuery("SELECT * FROM PLANS");%>
	<%ArrayList courseList1 = (ArrayList) request.getAttribute("list1Courses");%>
	<%ArrayList courseList2 = (ArrayList) request.getAttribute("list2Courses");%>
	<!--End of Navbar-->
	<h4 class="text-center">Register Plan</h4>
	<div class="col-md-6 mx-auto">
		<div class="card">
			<div class="card-body">
				<form action="EquivalencesController" method="POST">				
					<select class="custom-select" name="idPlan1">
						<option selected>Plan 1</option>
						<% for (int i = 0; i < planList.size(); i++) { %>
							<option value=<%=planList.get(i).getId()%>><%=planList.get(i).getId()%></option>
						<% } %>
					</select>
					<button name = "choosePlan1" type="submit" class="btn btn-success btn-block">Select Plan 1</button>
					<br>
					<div>
						<select class="custom-select" name="idCourse1">
							<option selected>Course 1</option>
							<% if (courseList1 != null) { %>
							<% System.out.println("courseList1.size() "+ courseList1.size()); %>
							<% for (int i = 0; i < courseList1.size(); i++) { %>
								<% Course course1 = (Course) courseList1.get(i); %>
								<% System.out.println("course1.getId() "+ course1.getId()); %>
								<option value=<%=course1.getId()%>><%=course1.getName()%></option>
							<% } %>
							<% } %>
						</select>
						<button name = "chooseCourse1" type="submit" class="btn btn-success btn-block">Select Course 1</button>
					</div>
					<br>
					<div>
						<select class="custom-select" name="idPlan2">
							<option selected>Plan 2</option>
							<% for (int i = 0; i < planList.size(); i++) { %>
								<option value=<%=planList.get(i).getId()%>><%=planList.get(i).getId()%></option>
							<% } %>
						</select>
						<button name = "choosePlan2" type="submit" class="btn btn-success btn-block">Select Plan 2</button>
					</div>	
					<br>
					<div>
						<select class="custom-select" name="idCourse2">
							<option selected>Course 2</option>
							<% if (courseList2 != null) { %>
							<% for (int i = 0; i < courseList2.size(); i++) { %>
								<% Course course2 = (Course) courseList2.get(i); %>
								<option value=<%=course2.getId()%>><%=course2.getName()%></option>
							<% } %>
							<% } %>
						</select>
						<button name = "chooseCourse2" type="submit" class="btn btn-success btn-block">Select Course 2</button>
					</div>
					<br>
					<button name = "addEquivalence" type="submit" class="btn btn-success btn-block">Add Equivalence</button>
			</div>
		</div>
	</div>
	<br>
</body>
</html>