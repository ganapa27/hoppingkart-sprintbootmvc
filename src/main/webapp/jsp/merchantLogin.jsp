<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Merchant Login</title>
    <link rel="stylesheet" href="../css/merchant.css">
</head>
<body>
    <h1>Merchant Login</h1>
    <h2 style="color: green;">${pass}</h2>
    <h3 style="color: red;">${fail}</h3>
    <form action="" method="post">
        <fieldset>
            <legend>Merchant Login</legend>
		Enter Email: <input type="text" name="email" required><br>	
		Enter Password:	<input type="password" name="password" required><br>
    </fieldset><br>
        <a href="/merchant/signup">Click here to create account</a><br>
	
		<button type="submit">Submit</button>
		<button type="reset">Cancel</button>        
		<button type="button"><a href="/">Back</a></button>
	</form>
    <script>
        setTimeout(function () {
            var h2Element = document.querySelector("h2");
            var h3Element = document.querySelector("h3");
            if (h2Element) {
                h2Element.style.display = "none";
            }
            if (h3Element) {
                h3Element.style.display = "none";
            }
        }, 2000);
    </script>
</body>
</html>