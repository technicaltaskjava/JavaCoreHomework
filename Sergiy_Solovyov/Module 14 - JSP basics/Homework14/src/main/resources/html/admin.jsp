<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
    <meta charset="utf-8">
    <title>Slick Login</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/admin.css" />
    <script src="/resources/js/jquery-2.2.3.js"></script>
    <script src="/resources/js/ajaxadmin.js"></script>
    <script src="/resources/js/langvalidation.js"></script>
    <script src="/resources/js/validationlang.js"></script>
</head>
<body>
<header>
    <a class="main" href="../index">
        <h1 id="slogan">Taste our cookies and become a bit happier</h1>
        <span>Go to home page</span>
    </a>
    <img id="logo"src="/resources/img/irb3.gif"></img>
</header>
<form id="send-cookie"  lang="en" action="#" method="post">
    <input class="myButton"  type="submit" value="Add new cookie">

    <input  id = "cookie"  type="text" name="cookie" class="placeholder" placeholder="Cookie text">

</form>
<div class="errors">
</div>
<form id = "edit-cookie" lang="en" action="#" method="post">
    <input class="myButton"  type="submit" value="Edit cookie">

    <input  id = "cook"  type="text" name="cook" class="placeholder" >

</form>
<c:if test="${!empty requestScope.cookieList}">
    <div id="table">
    <table id="admintable">
    <tr>
    <th>id</th>
    <th>Cookie text</th>
    <th></th>
    <th></th>
    </tr>
    <c:forEach items="${requestScope.cookieList}" var="cook">
        <tr>
            <td><c:out value="${cook.id}"></c:out></td>
            <td id="${cook.id}"><c:out value="${cook.cookieText}"></c:out></td>
            <td align="center" class="edit"><a class="editable" href="<c:url value='/admin/edit/${cook.id}' />" >
                <img class="edi"  src="/resources/img/Highlightmarker-orange.png"></img></a></td>
            <td align="center" class="delete"><a methods="delete" class="remove" href="<c:url value='/admin/remove/${cook.id}' />" >
                <img class="del"  src="/resources/img/058.png"></img></a></td>

        </tr>
    </c:forEach>
    </table>
    </div>
    </c:if>
</body>
</html>