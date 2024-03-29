<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="businessLogic.career.Career" %>
<%@ page import="dao.DaoCareer" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Information</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
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
				<li class="nav-item active"><a class="nav-link"
					href="RecordView.jsp">Record</a></li>
			</ul>
			<ul class="navbar-nav ml-auto"> 
				<li class="nav-item active"><a class="nav-link" href="loginView.jsp">sign out</a></li>
			</ul>
		</div>
	</nav>
	<br>
	<%String text = (String) request.getAttribute("text"); %>
	<% String type = (String)request.getAttribute("type"); %>
	<% Career career = new Career(); %>
	<% ArrayList<Career> careers = (ArrayList<Career>) career.getAllCareer(); %>
	<h4 class="text-center">Update career information</h4>
	<div class="col-md-8 mx-auto">
		<div class="card">
			<div class="card-body">
				<form action="InformationController" method="GET">
					<p>General data</p>
					<select class="custom-select" id="groupOptions1" name="career">
						<option selected>Choose a career...</option>
						<%for(int i = 0; i < careers.size(); i++){ %>
							<option value=<%=careers.get(i).getId() %>><%=careers.get(i).getName() %></option>
						<%} %>
					</select> 
					<input type="text" class="form-control" name="type" value=<%=type%>> 
					<br>
					<div>
						<button name="search" type="submit"
							class="btn btn-outline-info btn-block">Search</button>
					</div>
					<br>
					<p>Update the information</p>
					<div class="form-group">
						<textarea class="form-control" name="information" rows="5"><%= text %></textarea>
					</div>
					<div>
						<button name="update" type="submit" class="btn btn-info btn-block">Update information</button>
					</div>
				</form>
				<br>
			</div>
		</div>
	</div>
</body>
</html>