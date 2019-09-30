<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="careerLogic.RelevantInfo" %>
<%@ page import="careerLogic.Career" %>
<%@ page import="dao.DaoCareer" %>
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
	<br>
	<%
		ArrayList result = (ArrayList) request.getAttribute("result");
	%>
	<% DaoCareer db = new DaoCareer(); %>
	<% ArrayList<Career> careers = (ArrayList<Career>) db.selectQueryCareer("SELECT * FROM CAREERS"); %>
	<div class="col-md-8 mx-auto">
		<div class="card">
			<div class="card-body">
				<form action="InformationController" method="GET">
					<select class="custom-select" id="groupOptions1" name="career">
						<option selected>Choose a career...</option>
						<%for(int i = 0; i < careers.size(); i++){ %>
							<option value=<%=careers.get(i).getId() %>><%=careers.get(i).getName() %></option>
						<%} %>
					</select>
					<div>
						<button name="searchInfo" type="submit"
							class="btn btn-info btn-block">Search</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<br>
	<%
		if (result != null) {
	%>
	<div class="col-md-8 mx-auto">
		<div class="card">
			<div class="card-body">
				<table class="table table-sm col-md-12 mx-auto">
					<thead>
						<tr>
							<th scope="col">Type</th>
							<th scope="col">Information</th>
							<th scope="col">Listen</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (int i = 0; i < result.size(); i++) {
						%>
						<%
							RelevantInfo info = (RelevantInfo) result.get(i);
						%>
						<tr>
							<td class="font-weight-bold"><%=info.getType()%></td>
							<td><%=info.getDescription()%></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
				<%
					}
				%>
			</div>
		</div>
	</div>
</body>
</html>