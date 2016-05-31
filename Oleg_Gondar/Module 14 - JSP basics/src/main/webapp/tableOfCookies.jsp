<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cookies</title>
    <link rel="stylesheet" media="screen" href="css/styles.css">
</head>
<script src="js/jquery-2.2.3.js"></script>
<script src="js/workWithTable.js"></script>

<body class="common_form">

<ul>
    <li>
        <h2>Cookies</h2>

    </li>
    <li>

        <div class="datagrid">
            <table id="cookie">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Cookie</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                <tbody>


                <c:forEach items="${cookieList}" var="cookieItem">
                    <tr>
                        <td>${cookieItem.id}</td>
                        <td>${cookieItem.cookie}</td>
                        <td>
                            <a href="/DeleteCookie?id=${cookieItem.id}">
                                <button class="deleteRowButton" type="button">Delete row</button>
                            </a>
                            <a href="/EditCookie?id=${cookieItem.id}">
                                <button class="editRowButton" type="button">Edit row</button>
                            </a>
                        </td>
                    </tr>

                </c:forEach>


                </tbody>

            </table>

            <c:if test="${currentPage != 1}">
                <td><a href="GetCookiesPage?page=${currentPage - 1}">Previous</a></td>
            </c:if>


            <table>
                <tr>
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <td>${i}</td>
                            </c:when>
                            <c:otherwise>
                                <td><a href="GetCookiesPage?page=${i}">${i}</a></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </table>

            <c:if test="${currentPage lt noOfPages}">
                <td><a href="GetCookiesPage?page=${currentPage + 1}">Next</a></td>
            </c:if>

        </div>
    </li>
    <li>
        <form id="cook_add_form" action="AddCookie" method="post">
            <input id="ins_id" type="number" min="1" step="1" name="ins_id" placeholder="Enter cookieId" required/>
            <input id="ins_cookie" type="text" name="ins_cookie" placeholder="Enter cookie"/>
        </form>
    </li>

    <li>
        <button id="cook_add" class="submit" type="button">Add cookie</button>
        <a href="index.html">
            <button class="submit" type="button">Back to main page</button>
        </a>
    </li>
    <li>
        <form action="Logout" method="post">
            <button class="submit" type="submit" value="Logout">Logout</button>
        </form>
    </li>
    <li id="errors">
        <c:if test="${id ne null}">
            <p>Error - id exist, try another id!!!</p>
        </c:if>

    </li>
</ul>
</div>
</body>
</html>
