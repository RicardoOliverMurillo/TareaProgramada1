<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Register Course</title>
<!--BOOTSTRAP-->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
<body>
	<!--Navbar-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">Admin Page</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="RegisterCourse.jsp">Course Register</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="CommentAnalysis.jsp">Comment analysis</a></li>
			</ul>
		</div>
	</nav>
	<!--End of Navbar-->
	<br><br>
	<div class="row">
		<!--FORM-->
		<div class="col-md-5 mx-auto">
			<div class="card">
				<div class="card-body">
					<form action="CourseController" method="POST">
						<div class="form-group">
							<input type="text" name="id" placeholder="ID"
								class="form-control">
						</div>
						<div class="form-group">
							<input type="text" name="name" placeholder="Course Name"
								class="form-control">
						</div>
						<div class="form-group">
							<input type="number" name="sumCredits" placeholder="Credits"
								class="form-control">
						</div>
						<div class="form-group">
							<input type="number" name="semester" placeholder="Semester"
								class="form-control">
						</div>
						<div class="form-group">
							<input type="text" name="knowledgeArea" placeholder="Knowledge Area"
								class="form-control">
						</div>
						<div class="form-group">
							<input type="text" name="type" placeholder="Type"
								class="form-control">
						</div>
						<div>
							<button type="submit" class="btn btn-success btn-block">Confirm</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>