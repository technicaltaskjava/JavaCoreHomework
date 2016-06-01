<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Mike's Fortune Cookies - cookie control</title>
        <link rel="stylesheet" type="text/css" href="css/FortuneCookies.css" />
        <script src="js/CookieManipulation.js"></script>
    </head>
    <body>
        <a href="index" id="title">Mike's Fortune Cookies</a>
        <div id="cookies" class="centered">
            Cookies in the database:
            <table id="cookie-table" class="bordered">
                <thead>
                    <tr>
                        <td>ID</td>
                        <td>Message</td>
                    </tr>
                </thead>
                <c:forEach var="element" items="${requestScope.CookieList}">
                    <tr>
                        <td>${element.id}</td>
                        <td>${element.message}</td>
                    </tr>
                </c:forEach>
            </table>
            <input type="button" value="Refresh" onclick="refresh()" class="bordered rounded">
        </div>
        <div id="cookie-delete-form" class="centered hidden">
            <p>
                <label for="cookie-delete-id">Cookie ID:</label>
                <input type="number" id="cookie-delete-id" min="0" class="bordered rounded">
            </p>
            <input type="button" value="Delete" onclick="deleteCookie()" class="bordered rounded">
        </div>
        <div id="cookie-edit-form" class="centered hidden">
            <div id="cookie-find" class="centered">
                <p>
                    <label for="cookie-find-id">Cookie ID:</label>
                    <input type="number" id="cookie-find-id" min="0" class="bordered rounded">
                </p>
                <input type="button" value="Find" onclick="findCookie()" class="bordered rounded">
            </div>
            <div id="cookie-edit" class="centered hidden">
                <p>
                    <label for="cookie-edit-message">Message:</label>
                    <input type="text" id="cookie-edit-message" class="bordered rounded">
                </p>
                <input type="button" value="Edit" onclick="editCookie()" class="bordered rounded">
            </div>
        </div>
        <div id="cookie-add-form" class="centered hidden">
            <p>
                <label for="cookie-add-message">Message:</label>
                <input type="text" id="cookie-add-message" class="bordered rounded">
            </p>
            <input type="button" value="Add" onclick="addCookie()" class="bordered rounded">
        </div>
        <div id="cookies-control" class="centered">
            <input type="button" value="Add a cookie" onclick="showAddCookie()" class="bordered rounded">
            <input type="button" value="Edit a cookie" onclick="showEditCookie()" class="bordered rounded">
            <input type="button" value="Delete a cookie" onclick="showDeleteCookie()" class="bordered rounded">
        </div>
    </body>
</html>