<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your predictions</title>
</head>
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/cookie.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="js/lib/jquery-2.2.3.min.js"></script>
<script src="js/lib/jquery-ui.min.js"></script>
<script src="js/dialog.js"></script>
<script src="js/prediction.js"></script>
<body>
<h1>Your predictions</h1>
<span align="right">
    <p>Hello, ${user.username}</p>
    <p>
        <a href="/logout" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
            <span class="ui-button-text">LogOut</span>
        </a>
    </p>
</span>

<table id="cookie-table" border="1" width="80%" cellpadding="5">
    <thead>
    <tr>
        <th>Cookie name</th>
        <th>Prediction</th>
        <th>Time added</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="prediction" items="${predictions}">
        <tr id="${prediction.id}">
            <td>${prediction.cookie}</td>
            <td>${prediction.prediction}</td>
            <td><fmt:formatDate value="${prediction.timeAdded}" type="both"/></td>
            <td>
                <button class="edit-cookie icon-btn"></button>
                <button class="delete-cookie icon-btn"></button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div id="dialog-edit" title="Edit cookie" hidden>
    <form id="edit">
        <fieldset>
            <label>Cookie</label>
            <input type="text" name="cookie" id="cookie-edit" min="3" max="25"/>
            <label>Prediction</label>
            <input type="text" name="prediction" id="prediction-edit" min="2" max="100"/>
            <input id="edit-submit" type="submit" tabindex="-1">
        </fieldset>
    </form>
</div>

<p>
    <button id="add-cookie" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only">
        <span class="ui-button-text">Add cookie</span>
    </button>
</p>

<div id="dialog-add" title="Create new cookie" hidden>
    <form id="add" action="predictions" method="post">
        <fieldset>
            <label>Cookie</label>
            <input type="text" name="cookie" id="cookie" min="3" max="25"/>
            <label>Prediction</label>
            <input type="text" name="prediction" id="prediction" min="2" max="100"/>
            <input id="add-submit" type="submit" tabindex="-1">
        </fieldset>
    </form>
</div>
<div align="center">
    <a href="index.jsp">Back to the main page</a>
</div>
</body>
</html>