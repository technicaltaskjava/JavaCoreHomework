<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up - My Fortune Cookies</title>
    <script src="js/jquery-2.2.3.js"></script>
    <script src="js/signup.js"></script>
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
                        <form class="signUpForm" action="RegisterUser" method="post">
                            <input class="inputLine" id="username" type="text" name="username" placeholder="Input username"><br>
                            <div class="logError">

                            </div>
                            <input class="inputLine" id="email" type="text" name="email" placeholder="Input e-mail"><br>
                            <div class="emailError">

                            </div>
                            <input class="inputLine" id="password" type="password" name="password" placeholder="Input password"><br>
                            <input class="inputLine" id="passwordCheck" type="password" placeholder="Repeat password"><br>
                            <div class="pwdError">

                            </div>
                            Input your birth date:<br>
                            <input class="inputLine" id="dateOfBirth" type="date" name="birthdate"><br>
                            <script>
                                document.getElementById('dateOfBirth').valueAsDate = new Date();
                            </script>
                            <input type="image" class="submit" id="signUp" src="img/signup.gif">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>