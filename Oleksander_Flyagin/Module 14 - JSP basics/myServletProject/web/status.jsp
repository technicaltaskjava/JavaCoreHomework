
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test COOKIY</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="js/jquery-2.2.3.js"></script>

</head>
<body>
<header id="head">
    <img src="img/MyFortuneCookie.png" alt="MyFortuneCookie">
</header>
<div class="main">



    <p class="line">INFORMATION</p>
    <img src="img/cookie.png" alt="Foodzia Fortune Cookie" class="img">
    <span id="tableHead">Predictions</span>
    <img src="img/cookie.png" alt="Foodzia Fortune Cookie" class="img">

<h1><%= session.getAttribute("action")%></h1>
    <form action="/cookieServletComands" class="cookies" method="get">
        <input id="show" type="submit" value="Back &#9786;">
    </form>

</div>
</body>
</html>
