<%--
  JSP for FortuneCookie Table handling
  Created by Yury Vislobodsky on 26.05.2016.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="locale.table" var="loc"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="local.title" bundle="${loc}"/></title>
    <link rel="stylesheet" href="../css/style.css" type="text/css"/>
    <script src="../js/jquery-2.2.3.js"></script>
    <script src="../js/table.js"></script>
</head>
<body>
<div class="img-container">
    <img src="../images/logo.png" alt="MyFortuneCookie">
</div>
<div class="user-message"></div>
<div class="user-authorize">
    <c:if test="${not empty requestScope.userName}">
        <fmt:message key="local.welcome" bundle="${loc}"/>, <c:out value="${requestScope.userName}"/>
        &nbsp;&nbsp;|&nbsp;&nbsp;
    </c:if>
    <a href="logout"><fmt:message key="local.logout" bundle="${loc}"/></a>
</div>
<div class="table-div">
    <form action="table?page=${requestScope.currentPage}" method="post">
        <input type="hidden" name="local" value="en">
        <input type="hidden" id="command" name="command">
        <input type="hidden" id="id" name="id">
        <input type="hidden" id="name" name="name">
        <input type="hidden" id="message" name="message">
        <table class="table-cookies">
            <tr>
                <th>
                    <button class="button-add" type="button"></button>
                </th>
                <th><fmt:message key="local.table.header.cookieName" bundle="${loc}"/></th>
                <th><fmt:message key="local.table.header.cookieMessage" bundle="${loc}"/></th>
            </tr>

            <c:forEach var="element" items="${requestScope.list}">
                <tr>
                    <td>
                        <button class="button-remove" type="button" value="${element.id}"></button>
                    </td>
                    <td>${element.cookieName}</td>
                    <td>${element.message}</td>
                </tr>
            </c:forEach>
        </table>
    </form>

    <ul class="pagination">
        <fmt:message key="local.pagination.button.previous" var="previousButton" bundle="${loc}"/>
        <c:choose>
            <c:when test="${requestScope.currentPage != 1}">
                <li><a href="table?page=${requestScope.currentPage - 1}">${previousButton}</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="#">${previousButton}</a></li>
            </c:otherwise>
        </c:choose>

        <c:forEach begin="1" end="${requestScope.pageCount}" var="i">
            <c:choose>
                <c:when test="${requestScope.currentPage eq i}">
                    <li><a class="active" href="#">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="table?page=${i}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <fmt:message key="local.pagination.button.next" var="nextButton" bundle="${loc}"/>
        <c:choose>
            <c:when test="${requestScope.currentPage lt requestScope.pageCount}">
                <li><a href="table?page=${requestScope.currentPage + 1}">${nextButton}</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="#">${nextButton}</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>

<div class="copyright">
    &copy; Vislobodsky Yury, 2016
</div>
</body>
</html>
