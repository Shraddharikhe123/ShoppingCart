<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<style>
.div {
	background-color: #3b5998;
	text-align: center;
	height: 100px;
	color: white;
}

.div1 {
	background-color: #f7f7f7;
	height: 10px;
}

.div3 {
	text-align: left;
}

.div4 {
	text-align: right;
}

.div5 {
	height: 20px;
	position: static;
}
</style>
<head>
<meta charset="UTF-8">
<div class="div">
	<br>
	<h1>Shoppingkart</h1>
</div>
</head>
<body>
	<div class="div1" class="div3" class="div5">
		<form action="register" method="post">
			<h1>Registration</h1>
			Enter UserName: <br> <input type="text" name="userName"
				placeholder="UserName" required><br> <br> Enter
			Password: <br> <input type="password" name="password"
				placeholder="Password" required><br> <br> Enter
			Email: <br> <input type="text" name="email" placeholder="Email"
				required> <br> <br> Enter address:<br> <input
				type="text" name="address" placeholder="Address" required><br>
			<br> Enter MobileNo: <br> <input type="text"
				name="mobileNo" placeholder="MobileNo" required
				pattern="(^$|[0-9]{10})" title="Enter Validate MobileNo"
				oninput="validateMobieNo(this);" onchange="validateMobileNo(this);">
			<br> <br> 
				<input type="submit" value="Register">
		</form>
	</div>
	<div class="div1" style="text-align: right;"div5">
		<form action="login">
			<h1>Login</h1>
			<div style="color: red;">
				*${error}<br>
			</div>
			Enter Username: <br> <input type="text" name="userName"
				placeholder="Username" required><br> <br> Enter
			Password: <br> <input type="password" name="password"
				placeholder="Password" required><br> <br> <input
				type="submit" value="Login">
		</form>
	</div>



</body>
</html>
