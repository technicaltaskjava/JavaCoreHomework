<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Cookie - My Fortune Cookies</title>
    <script src="js/jquery-2.2.3.js"></script>
    <script src="js/addcookie.js"></script>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
</head>
<body>
<div class="page" align="center">
    <a href="Main"><img src="img/header.gif" title="My Fortune Cookie" alt="My Fortune Cookie Logo"></a>
    <div class="box">
        <div class="top">
            <div class="bot">
                <div class="pad">
                    <div class="wa">
                        <h1>
                        Adding new cookie to database
                        </h1>
                        <br>
                        <form class="AddCookieForm" action="AddNewCookie" method="post">
                            <input class="inputLine" id="cookie" type="text" size=50 name="cookie" placeholder="Input cookie"><br>
                                <div class="addError">

                                </div>
                            Cookie status: <select name="active">
                                <option value="false">Disabled</option>
                                <option selected value="true">Enabled</option>
                            </select><br>
                            <input type="image" class="submit" id="addCookie" src="img/addcookie.gif"
                                 title="Add Fortune Cookie" alt="Add Fortune Cookie">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>