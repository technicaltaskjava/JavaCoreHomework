<%@ page import="servlets.helpers.ServletHelper" %>
<%@ page import="dao.util.PredicationList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<html lang="en">
<head>
    <title>Predication table</title>
    <jsp:include page="utilpages/links.jsp"/>
    <link href="css/table.css" rel="stylesheet">
    <script src="js/tableScript.js" rel="script"></script>
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
        <form method="post" id="predictionForm" action="${pageContext.request.contextPath}/renderTable">
            <table id="predictionTable">
                <thead>
                <tr>
                    <th colspan="2">
                        <input type="button" class="addButton" id="addPredicationButton" value="Add">
                        <input type="button" class="deleteButton" id="deletePredicationButton" value="Delete">
                    </th>
                    <th>
                        <%--<input type="button" class="pageNavigation" id="previousPage" value="<<">--%>
                        <%--<input type="button" class="pageNavigation" id="nextPage" value=">>">--%>
                    </th>
                </tr>
                </thead>

                <tbody>
                <% for (int i = 0; i < PredicationList.getSize(); i++) {%>
                <tr id="<%=PredicationList.getPredication(i).getId()%>">
                    <th><input type="checkbox" name="deletePredication"></th>
                    <th class="predicationCell">
                        <%=PredicationList.getPredication(i).getValue()%>
                    </th>
                    <th><input type="button" class="editButton" value="Edit"></th>
                </tr>
                <%} %>

                </tbody>

                <tfoot>
                <tr>
                    <th colspan="2"><input type="text" id="predictionAddField" class="inputPredication">
                        <input type="button" class="addPredication" value="Save"></th>
                    <th><input type="button" class="cancelButton" value="Cancel"></th>
                </tr>
                </tfoot>
            </table>
        </form>
    </fieldset>

</section>

</body>
</html>
