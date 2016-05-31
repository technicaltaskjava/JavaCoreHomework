<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit cookie</title>
    <link rel="stylesheet" media="screen" href="css/styles.css">
</head>
<script src="js/jquery-2.2.3.js"></script>
<script src="js/workWithTable.js"></script>

<body class="common_form">

    <ul>
        <li>
            <h2>Edit cookie</h2>

        </li>
    <li>
        <form id="cook_add_form" action="DoEditCookie" method="post">
            <input id="ins_id" type="hidden" min="1" step="1" name="ins_id" placeholder="Enter cookieId" value="${cookieItem.id}" required/>
            <input id="ins_cookie" type="text" name="ins_cookie" placeholder="Enter cookie" value="${cookieItem.cookie}" />
        </form>
    </li>

    <li>
        <button id="cook_add" class="submit" type="button">Update cookie</button>
        <a href="/GetCookiesPage">
            <button class="submit" type="button">Back to table</button>
        </a>
    </li>
    <li>
        <form action="Logout" method="post">
            <button class="submit" type="submit" value="Logout">Logout</button>
        </form>
    </li>
    <li id="errors">


    </li>
</ul>
</body>
</html>
