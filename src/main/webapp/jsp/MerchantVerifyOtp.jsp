<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>OTP verification</title>
    <link rel="stylesheet" href="../css/merchant.css">
</head>
<body>
    <h2 style="color: green;">${pass}</h2>
    <h3 style="color: red;">${fail}</h3>
    <h1>Merchant OTP signup</h1>
    <form action="/merchant/verify" method="post">
        <input type="hidden" name="id" value="${id}">
        Enter OTP<input type="number" name="otp" placeholder="Enter OTP"><br>
        <button type="submit">Submit</button>
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