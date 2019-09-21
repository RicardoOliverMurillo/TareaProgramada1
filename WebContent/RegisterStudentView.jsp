<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Register</title>
<!--BOOTSTRAP-->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">Personal information</a>
	</nav>
	<br><br>
	<div class="row">
		<!--FORM-->
		<div class="col-md-5 mx-auto">
			<div class="card">
				<div class="card-body">
					<form action="RegisterStudent" method="POST">
						<div class="form-group">
							<input type="text" name="id" placeholder="ID"
								class="form-control">
						</div>
						<div class="form-group">
							<input type="text" name="name" placeholder="Name"
								class="form-control">
						</div>
						<div class="form-group">
							<input type="text" name="lastName" placeholder="Last name"
								class="form-control">
						</div>
						<div class="form-group">
							<input type="text" name="email" placeholder="Personal email"
								class="form-control">
						</div>
						<div class="form-group">
							<input type="password" name="password" placeholder="Password"
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