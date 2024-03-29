<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="businessLogic.user.Comment"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add new comment</title>
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
				<li class="nav-item active"><a class="nav-link" href="ReportsView.jsp">Reports</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="AddComment.jsp">Comment</a></li>
				<li class="nav-item active"><a class="nav-link" href="ViewInformation.jsp">Information</a></li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link"
					href="loginView.jsp">sign out</a></li>
			</ul>
		</div>
	</nav>
	<%
		ArrayList commentList = (ArrayList) request.getAttribute("list");
	%>
	<!--End of Navbar-->
	<br>
	<br>
	<div class="col-md-6 mx-auto">
		<div class="card">
			<div class="card-body">
			<h4 class="text-center">Add new comment</h4>
			<br>
				<form action="AddComment" method="POST">
					<div class="form-group">
						<p>We would like to know your opinion. Please add a comment to help us
							improve.</p>
						<textarea class="form-control" name="comment" rows="3" required></textarea>
					</div>
					<div>
						<button name = "add" type="submit" class="btn btn-info btn-block">Add
							comment</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<br>
	<div class="col-md-6 mx-auto">
		<div class="card">
			<div class="card-body">
				<form action="AddComment" method="POST">
					<div>
						<button name = "historial" type="submit" class="btn btn-outline-secondary btn-block">Show my comments...</button>
					</div>
					<br>
					<% if (commentList != null) { %>
					<table class="table table-sm col-md-12 mx-auto">
						<thead>
							<tr>
								<th scope="col">Student Id</th>
								<th scope="col">Comment</th>
								<th scope="col">Tone</th>
							</tr>
						</thead>
						<tbody>
							<% for (int i = 0; i < commentList.size(); i++) { %>
							<% Comment comment = (Comment) commentList.get(i); %>
								<tr>
									<td><%= comment.getIdOwner() %></td>
									<td><%= comment.getDescription() %></td>
									<td><%= comment.getToneName() %></td>
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