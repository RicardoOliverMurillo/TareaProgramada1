<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Welcome</title>
<!--BOOTSTRAP-->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">Welcome! Please sign in.</a>
	</nav>
	<br><br>
	<div class="col-md-4 mx-auto">
		<div class="card">
			<div class="card-body">
				<form action="LoginCheck" method="POST">
					<div class="form-group">
						<input type="text" name="username" placeholder="ID"
							class="form-control"  required >
					</div>
					<div class="form-group">
						<input type="password" name="password" placeholder="Password"
							class="form-control" required>
					</div>
					<div>
						<button type="submit" class="btn btn-success btn-block">Log
							in</button>
					</div>
					<div>
						<a href="RegisterStudentView.jsp" class="btn btn-primary btn-block">Register</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>