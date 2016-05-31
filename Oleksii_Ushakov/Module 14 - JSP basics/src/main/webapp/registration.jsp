<%@ page import="servlets.helpers.ServletHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Registration</title>
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

    <span class="messageSpan">
        <%--message--%>
    </span>

    <fieldset>
        <form class="registrationForm" id="registrationForm" method="post" action="registrationAction">
            <section class="column">
                <label for="login">Login</label>

                <input id="login"
                       title="Input you login"
                       type="text"
                       name="login"
                       value=""
                       tabindex="1">

                <section class="blank"></section>

                <label for="firstName">First name</label>

                <input id="firstName"
                       title="Input you first name"
                       type="text"
                       name="firstName"
                       value=""
                       tabindex="4">

                <section class="blank"></section>

                <input id="enter"
                       type="submit"
                       value="OK"
                       tabindex="7">
            </section>

            <section class="column">
                <label for="password">Password</label>

                <input id="password"
                       title="Input you password"
                       type="password"
                       name="password"
                       value=""
                       tabindex="2">

                <label for="passwordCheck">Password again</label>

                <input id="passwordCheck"
                       title="Input you password again"
                       type="password"
                       tabindex="3">

                <label for="secondName">Second name</label>

                <input id="secondName"
                       title="Input you second name"
                       type="text"
                       name="secondName" value=""
                       tabindex="5">

                <label for="email">E-mail</label>

                <input id="email"
                       title="Input you email"
                       type="text"
                       name="email"
                       value=""
                       tabindex="6">

                <input type="reset"
                       value="Cancel"
                       tabindex="8">
            </section>
        </form>
    </fieldset>

</section>

</body>
</html>
