<%@ page import="servlets.helpers.ServletHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Login</title>
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

    <span class="errorSpan">
        No such login in database
    </span>

    <fieldset>
        <form class="loginForm" id="loginForm" method="post" action="loginAction">
            <section class="column">
                <label for="login">Login</label>

                <input id="login"
                       title="Input you login"
                       type="text"
                       name="login"
                       value=""
                       tabindex="1">

                <input id="enter"
                       type="submit"
                       value="OK"
                       tabindex="3">
            </section>

            <section class="column">
                <label for="password">Password</label>

                <input id="password"
                       title="Input you password"
                       type="password"
                       name="password"
                       value=""
                       tabindex="2">

                <input type="reset"
                       value="Cancel"
                       tabindex="4">
            </section>
        </form>
    </fieldset>

</section>

</body>
</html>
