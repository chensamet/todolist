<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<style>
body {
	background-image:
		url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABoAAAAaCAYAAACpSkzOAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEgAACxIB0t1+/AAAABZ0RVh0Q3JlYXRpb24gVGltZQAxMC8yOS8xMiKqq3kAAAAcdEVYdFNvZnR3YXJlAEFkb2JlIEZpcmV3b3JrcyBDUzVxteM2AAABHklEQVRIib2Vyw6EIAxFW5idr///Qx9sfG3pLEyJ3tAwi5EmBqRo7vHawiEEERHS6x7MTMxMVv6+z3tPMUYSkfTM/R0fEaG2bbMv+Gc4nZzn+dN4HAcREa3r+hi3bcuu68jLskhVIlW073tWaYlQ9+F9IpqmSfq+fwskhdO/AwmUTJXrOuaRQNeRkOd5lq7rXmS5InmERKoER/QMvUAPlZDHcZRhGN4CSeGY+aHMqgcks5RrHv/eeh455x5KrMq2yHQdibDO6ncG/KZWL7M8xDyS1/MIO0NJqdULLS81X6/X6aR0nqBSJcPeZnlZrzN477NKURn2Nus8sjzmEII0TfMiyxUuxphVWjpJkbx0btUnshRihVv70Bv8ItXq6Asoi/ZiCbU6YgAAAABJRU5ErkJggg==);
}
</style>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<head>
<style type="text/css">
</style>
<meta charset="windows-1255">
<title>Login form</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h1>Log in</h1>
					</div>
					<form method="POST"
						action="http://localhost:8080/toDoList/router/user/logIn">
						<div class="panel-body">
							<div class="form-group">
								user name: <input type="text" name="usn"
									placeholder="Enter username" required="required" /> </br> </br>
								password: <input type="password" name="psw"
									placeholder="Enter Password" required="required" /> </br> </br> <input
									type="submit" value="LogIn" /> <br> <br>
							</div>
						</div>
						<a href="<%=request.getServletContext().getContextPath()%>"
							data-theme="c" data-role="button" data-icon="home"
							data-inline="true">Back to home page</a>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>