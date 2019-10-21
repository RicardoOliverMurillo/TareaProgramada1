<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="businessLogic.Comment" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Comment analysis</title>
<!--BOOTSTRAP-->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
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
			</ul>
			<ul class="navbar-nav ml-auto"> 
				<li class="nav-item active"><a class="nav-link" href="loginView.jsp">sign out</a></li>
			</ul>
		</div>
	</nav>
	<!--End of Navbar-->
	<br>
	<% ArrayList commentList = (ArrayList)request.getAttribute("list"); %>
	<div class="col-md-8 mx-auto">
		<div class="card">
			<div class="card-body">
				<h5 class="text-center">Color key</h5>
				<p class="text-primary">The comment has a score of 0.5</p>
				<p class="text-success">The comment has a score greater than 0.5
					and less than 0.75</p>
				<p class="text-warning">The comment has a score greater than 0.75</p>
				<form action="AddComment" method="GET">
					<select class="custom-select" id="groupOptions1" name="option">
						<option selected>Choose an emotion...</option>
						<option value="Anger">Anger</option>
						<option value="Fear">Fear</option>
						<option value="Joy">Joy</option>
						<option value="Sadness">Sadness</option>
						<option value="Analytical">Analytical</option>
						<option value="Confident">Confident</option>
						<option value="Tentative">Tentative</option>
					</select>
					<div>
						<button type="submit" class="btn btn-outline-secondary btn-block">Analyze</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<br>
	<br>
	<div class="col-md-8 mx-auto">
		<div class="card">
			<div class="card-body">
			<%if(commentList!= null) {%>
				<table class="table table-sm col-md-12 mx-auto">
					<thead>
						<tr>
							<th scope="col">Tone Name</th>
							<th scope="col">Score</th>
							<th scope="col">Comment</th>
						</tr>
					</thead>
					<tbody>
						<% for (int i = 0; i < commentList.size(); i++) {%>
						<% Comment comment = (Comment) commentList.get(i); %>
						<% float score = comment.getScore(); %>
							<% if (score < 0.5) { %>
								<tr  class="table-primary">
								<td><%= comment.getToneName() %></td>
								<td><%= comment.getScore() %></td>
								<td><%= comment.getDescription() %></td>
								</tr>
							<% } %>
							<% if(score > 0.5 && score < 0.75){ %>
								<tr  class="table-success">
								<td><%= comment.getToneName() %></td>
								<td><%= comment.getScore() %></td>
								<td><%= comment.getDescription() %></td>
								</tr>
							<% } %>
							<% if(score > 0.75){ %>
								<tr  class="table-warning">
								<td><%= comment.getToneName() %></td>
								<td><%= comment.getScore() %></td>
								<td><%= comment.getDescription() %></td>
								</tr>
							<% } %>
						<% } %>
					</tbody>
				</table>
				<%} %>
			</div>
		</div>
	</div>
</body>
</html>