<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Verify OTP</title>
<link rel="stylesheet" href="../css/merchant.css">
</head>
<body>
	<h1 style="color: green">${pass}</h1>
	<h1 style="color: red">${fail}</h1>
	<h1>Verify Otp Here</h1>
	<form action="/merchant/verify-otp" method="post">
		<input type="number" name="id" value="${id}" hidden="hidden">
		Enter OTP:<input type="number" name="otp">
		<button>submit</button>
	</form>
	<br>
	<a href="/merchant/signup"><button>Back</button></a>
</body>
</html>