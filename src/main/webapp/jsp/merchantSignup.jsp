<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Merchant Signup</title>
    <link rel="stylesheet" href="../css/merchant.css">
</head>
<body>
    <h1>Merchant Signup</h1>
    <form action="/merchant/signup" method="post">
        <fieldset>
            <legend>Merchant Signup</legend>
            <h2 style="color: red; display:flex; justify-content: center;">${fail}</h2>
		Enter Name: <input type="text" name="name" required><br>	
		Enter Email: <input type="email" name="email" required><br>
		Enter Date of Birth: <input type="date" name="dob" required><br>	
		Enter Phone: <input type="number" name="phone" required><br>
        Enter Password: <input type="password" name="password" required><br>
    </fieldset><br>
		<button type="submit">Signup</button>
		<button type="reset">Cancel</button>        
		<button type="button"><a href="/merchant/login">Back</a></button>
	</form>
</body>
</html>