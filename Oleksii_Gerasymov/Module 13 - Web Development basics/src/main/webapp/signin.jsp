<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign In - My Fortune Cookies</title>
    <script src="js/jquery-2.2.3.js"></script>
    <script src="js/signin.js"></script>
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
                        <form class="signInForm" action="LoginUser" method="post">
                            <div>
                                ${successReg}
                                <br><br>
                            </div>
                            <input class="inputLine" id="username" type="text" name="username" placeholder="Input username"><br>
                            <div class="logError">

                            </div>
                            <input class="inputLine" id="password" type="password" name="password" placeholder="Input password"><br>
                            <div class="pwdError">

                            </div>
                            <div>
                                ${loginError}
                                <br>
                                ${passwordError}
                            </div>
                            <input type="image" class="submit" id="signIn" src="img/signin.gif">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>