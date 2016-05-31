<%@ page import="servlets.helpers.ServletHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Logout</title>
    <jsp:include page="utilpages/links.jsp"/>
</head>
<body>

<section class="content">
    <h1>Fortune Cookies</h1>

    <% if (ServletHelper.isUserRegistered(request.getCookies())) {%>
        <jsp:include page="utilpages/registeredUserMenu.jsp"/>
    <%} else {%>
        <jsp:include page="utilpages/notRegisteredUserMenu.jsp"/>
    <%}%>

    <fieldset>
        <form class="loginForm" id="loginForm" method="post" action="logoutAction">
            <section class="column">
                <input id="enter"
                       type="submit"
                       value="Logout"
                       tabindex="1">
            </section>

            <section class="column">
                <input type="reset"
                       value="Cancel"
                       tabindex="2">
            </section>
        </form>
    </fieldset>
</section>

</body>
</html>
