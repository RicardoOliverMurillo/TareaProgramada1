<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
		<a class="navbar-brand" href="#">Student Page</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="#">2050
						Plan</a></li>
				<li class="nav-item active"><a class="nav-link" href="#">2051
						Plan</a></li>
				<li class="nav-item active"><a class="nav-link" href="#">Reports</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="AddComment.jsp">Comment</a></li>
				<li class="nav-item active"><a class="nav-link" href="#">Information</a></li>
			</ul>
		</div>
	</nav>
	<!--End of Navbar-->
	<br><br>
	<h4 class="text-center"> Add new comment</h4>
		<div class="col-md-6 mx-auto">
			<div class="card">
				<div class="card-body">
					<form action="AddComment" method="POST">
						<div class="form-group">
							<p>We would like to know your opinion. Please add a comment
								to improve.</p>
							<textarea class="form-control" name="comment"
								rows="3" required></textarea>
						</div>
						<div>
						<button type="submit" class="btn btn-info btn-block">Add comment</button>
					</div>
					</form>
				</div>
			</div>
		</div>
</body>
</html>