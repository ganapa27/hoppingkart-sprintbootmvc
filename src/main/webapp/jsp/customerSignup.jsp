<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Signup</title>
    <link rel="stylesheet" href="../css/customer.css">
</head>

<body>
    <h1>Customer Signup</h1>
    <form action="" method="post">
        <fieldset>
            <legend>Customer Signup</legend>
            Enter Name: <input type="text" name="name" required><br>
            Enter Email: <input type="email" name="email" required><br>
            Enter Date of Birth: <input type="date" name="dob" required><br>
            Enter Phone: <input type="number" name="phone" required><br>
            Enter Password: <input type="password" name="password" required><br>
        </fieldset><br>
        <button type="submit">Signup</button>
        <button type="reset">Cancel</button>
        <button type="button"><a href="/customer/login">Back</a></button>
    </form>
</body>

</html>