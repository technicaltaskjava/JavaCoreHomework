<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Mike's Fortune Cookies - main page</title>
        <link rel="stylesheet" type="text/css" href="css/FortuneCookies.css" />
        <script src="js/GetPrediction.js"></script>
    </head>
	<body>
        <a href="index" id="title">Mike's Fortune Cookies</a>
        <div id="login-container">
            <!-- Following lines are used for testing purposes for now.></!-->
        <c:out value="Username"/>
        <c:set var="currentUser" value="${User}"/>
        <c:out value="${currentUser}"/>
        <c:choose>
            <c:when test="${empty currentUser}">
                <p id="login-link">
                    <a href="login.html">Login.</a>
                </p>
                <p id="register-link">
                    <a href="register.html">Register a new account.</a>
                </p>
            </c:when>
            <c:otherwise>
                <c:out value="Logged in as ${currentUser}"/>
                <p id="logout-link">
                    <a href="logout">Log out.</a>
                </p>
            </c:otherwise>
        </c:choose>
        </div>
        <div id="image-container">
            <img src="img/Fortune-cookie-shutterstock.png" alt="Fortune cookie" class="bordered">
            <img src="img/Fortune-cookie-shutterstock.png" alt="Fortune cookie" class="bordered">
            <img src="img/Fortune-cookie-shutterstock.png" alt="Fortune cookie" class="bordered">
        </div>
		<div class="centered">
            <p id="prediction-text" class="output bordered">
                Fortune favors fools.
            </p>
            <p>
                <input type="submit" value="Get a prediction" onclick="getNewMessage()" class="bordered rounded">
            </p>
		</div>
	</body>
</html>