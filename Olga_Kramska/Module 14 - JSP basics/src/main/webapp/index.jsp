<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Fortune Cookies</title>
</head>
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="js/lib/jquery-2.2.3.min.js"></script>
<script src="js/lib/jquery-ui.min.js"></script>
<script src="js/loginCheck.js"></script>
<script src="js/randomPrediction.js"></script>
<body>
<h1>Welcome to the "Fortune Cookies"</h1>
<c:if test="${sessionScope.user != null}">
    <div align="right">
        <p>Hello, ${sessionScope.user.username}</p>
        <form action="predictions" method="get">
            <p><button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
                <span class="ui-button-text">Show you predictions</span></button></p>
        </form>
        <form action="logout" method="get">
            <p>
                <button id="logout" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
                    <span class="ui-button-text">LogOut</span></button>
            </p>
        </form>
    </div>
</c:if>

<div align="center">
    <p><b>LogIn to see your predictions</b></p>

    <form id="login" action="login" method="post">
        <input id="username" type="text" min="3" max="25" name="username" placeholder="username"/>
        <input id="password" type="password" min="6" max="40" name="password" placeholder="password"/>

        <p>
            <button id="login-button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
                <span class="ui-button-text">LogIn</span>
            </button>
        </p>
        <div class="errors"></div>
    </form>
</div>
<div align="center">If you don't have account you can register <a href="registration.html">here</a></div>
<hr/>
<p>
    <button id="random" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
        <span class="ui-button-text">Random prediction</span>
    </button>
</p>
<div id="random-prediction" align="center"></div>
</body>
</html>