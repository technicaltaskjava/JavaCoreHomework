<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Fortune Cookies</title>
    <script src="js/jquery-2.2.3.js"></script>
    <script src="js/index.js"></script>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
</head>
<body>
<div class="page" align="center">
    <a href="index.jsp"><img src="img/header.gif" title="My Fortune Cookie" alt="My Fortune Cookie Logo"></a>
<div class="box">
    <div class="top">
        <div class="bot">
            <div class="pad">
                <div class="wa">
                    <form class="showCookie" action="ShowDbCookie" method="get">
                        <div class="cookieText">
                        ${currentCookie}
                        </div>
                        <input type="image" class="submit" id="getCookie" src="img/getcookie.gif">
                    </form>

                    <a href="signin.jsp"><img src="img/signin.gif" title="Sign In My Fortune Cookie"
                                               alt="Sign In My Fortune Cookie"></a>
                    <a href="signup.jsp"><img src="img/signup.gif" title="Sign Up My Fortune Cookie"
                                               alt="Sign Up My Fortune Cookie"></a>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>