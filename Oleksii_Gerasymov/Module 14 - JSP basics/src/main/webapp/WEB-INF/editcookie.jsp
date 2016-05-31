<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Cookie - My Fortune Cookies</title>
    <script src="js/jquery-2.2.3.js"></script>
    <script src="js/editcookie.js"></script>
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
                        Edit current cookie
                        </h1>
                        <br>
                        <form class="EditCookieForm" action="EditCurrentCookie" method="post">
                            <input id="cookieId" type="hidden" name="cookieId" value="${cookieId}">
                            <input class="inputLine" id="cookieName" type="text" size=50 name="cookieName" value="${cookieName}"><br>
                                <div class="editError">

                                </div>
                            Cookie status: <select name="active">
                            <c:if test="${not active}">
                                <option selected value="false">Disabled</option>
                                <option value="true">Enabled</option>
                            </c:if>
                            <c:if test="${active}">
                                <option value="false">Disabled</option>
                                <option selected value="true">Enabled</option>
                            </c:if>
                            </select><br>
                            <input type="image" class="submit" id="editCookie" src="img/editcookie.gif"
                                 title="Edit Fortune Cookie" alt="Edit Fortune Cookie">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>