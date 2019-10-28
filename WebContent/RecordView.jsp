<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="observerLogic.Action"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Record</title>
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
	<!--End of navbar-->
	<%ArrayList<Action> data = (ArrayList<Action>) request.getAttribute("list");  %>
	<form action="RecordController" method="POST">
		<div>
			<select class="custom-select" id="groupOptions1" name="format">
				<option selected>Choose a format...</option>
				<option value="XML">XML</option>
				<option value="CSV">CSV</option>
				<option value="TXT">TXT</option>
			</select>
		</div>
		<div>
			<button type="submit" class="btn btn-success btn-block">View record</button>
		</div>
	</form>
	<%if(data != null) {%>
		<%for(Action action : data) {%>
			<p><%= action.getUserId() %></p>
			<p><%= action.getAction() %></p>
			<p><%= action.getDate() %></p>
			<p><%= action.getTime() %></p>
		<%} %>
	<%} %>
</body>
</html>