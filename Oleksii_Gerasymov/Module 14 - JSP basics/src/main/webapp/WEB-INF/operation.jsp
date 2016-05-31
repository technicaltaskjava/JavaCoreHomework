<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Operation - My Fortune Cookies</title>
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
                        Operate cookies
                        </h1>
                            <br>
                            ${editMessage}
                            <br><br>
                            <table>
                                <tr>
                                    <td>
                                        ID
                                    </td>
                                    <td>
                                        COOKIE
                                    </td>
                                    <td>
                                        ACTIVE
                                    </td>
                                    <td>
                                    </td>
                                </tr>
                                <c:set var="pageIndex" scope="session" value="${viewPage}"/>
                                <c:set var="currentRecord" scope="page" value="${1}"/>
                                <c:forEach var="currentCookie" items="${cookieList}">
                                <c:if test="${((currentRecord >= ((pageIndex - 1) * cookiesPerPage) + 1 ) and (currentRecord <= (pageIndex * cookiesPerPage)))}">
                                    <tr>
                                        <td>
                                            <c:out value="${currentCookie.getId()}" />
                                        </td>
                                        <td>
                                            <c:out value="${currentCookie.getCookieName()}" />
                                        </td>
                                        <td>
                                            <c:out value="${currentCookie.isActive()}" />
                                        </td>
                                        <td>
                                            <form action="GetCurrentCookie" method="post">
                                                <input type="hidden" name="cookieId" value="${currentCookie.getId()}">
                                                <input type="hidden" name="cookieName" value="${currentCookie.getCookieName()}">
                                                <input type="hidden" name="active" value="${currentCookie.isActive()}">
                                                <input type="image" class="submit" src="img/edit.gif"
                                                    title="Edit Cookie" alt="Edit Cookie">
                                            </form>
                                            <form action="DeleteCookie" method="post">
                                                <input type="hidden" name="cookieId" value="${currentCookie.getId()}">
                                                <input type="image" class="submit" src="img/delete.gif"
                                                    title="Delete Cookie" alt="Delete Cookie">
                                            </form>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:set var="currentRecord" scope="page" value="${currentRecord + 1}"/>
                                </c:forEach>
                            </table>
                            <br>
                            <a href="PreviousPage" action="PreviousPage">
                                <img src="img/previous.gif" alt="Previous Page" title="Previous Page">
                            </a>
                            <a href="NextPage" action="NextPage">
                                <img src="img/next.gif" alt="Next Page" title="Next Page">
                            </a>
                            <br><br>
                            <form action="AddCookie" method="post">
                                <input type="image" class="submit" src="img/addcookie.gif"
                                    title="Add New Fortune Cookie" alt="Add New Fortune Cookie">
                            </form>
                            <form action="Logout" method="post">
                                <input type="image" class="submit" src="img/logout.gif"
                                    title="Logout" alt="Logout">
                            </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>