<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dao.util.PredicationList" %>
<%@ page import="servlets.helpers.ServletHelper" %>
<html lang="en">
<head>
    <title>Predication</title>
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
        <span id="predictionText">
            <%= PredicationList.getRandomPredicationValue()%>
        </span>

        <form id="predictionForm" method="post">
            <input id="predictionNext"
                   type="submit"
                   value="New prediction"
                   tabindex="1">
        </form>
    </fieldset>
</section>

</body>
</html>
