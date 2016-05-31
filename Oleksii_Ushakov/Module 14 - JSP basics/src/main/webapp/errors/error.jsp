<%@ page import="servlets.helpers.ServletHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Error</title>
    <jsp:include page="../utilpages/links.jsp"/>
</head>
<body>

<section class="content">
    <h1>Fortune Cookies</h1>

    <% if (ServletHelper.isUserRegistered(request.getCookies())) {%>
        <jsp:include page="../utilpages/registeredUserMenu.jsp"/>
    <%} else {%>
        <jsp:include page="../utilpages/notRegisteredUserMenu.jsp"/>
    <%}%>

    <h1>We get some error :(</h1>
</section>

</body>
</html>
