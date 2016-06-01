<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="predictionsBean" scope="session" class="entities.PredictionsBean" />
<!DOCTYPE html>
<html>
    <head>
        <title>Fortune Cookies</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <script src="scripts/jquery-2.2.3.min.js"></script>
        <script src="scripts/main.js"></script>
    </head>

    <body>
        <div id="page">
            <div id="header">
                <div id="name">
                    <p><h1> FORTUNE COOKIES </h1>
                </div>
                <div class="welcome">
                </div>
                <form class="signIn" action="index.jsp" method='post'>
                    <input id="login" type="text" placeholder="login (admin)" required >
                    <input id="pass" type="password" placeholder="pass (admin)" required>
                    <button id="signIn" type="submit"> Sign in </button>
                </form>
                <form class="signIn" action="index.jsp" method='post'>
                    <button id="regSignButton" type='submit' name='regSignButton'> Registration </button>
                </form>
                <form class='reg' action='index.jsp' method='post'>
                    <input id="loginReg" type="text" placeholder="login" required >
                    <input id="passReg" type="password" placeholder="pass" required>
                    <input id="confirmPass" type="password" placeholder="confirm pass" required>
                    <input id="email" name="email" type="email" placeholder="email" required>
                    <button id="regButton" type='submit' name='regButton'> Registration </button>
                </form>
                <form class='signOut' action='index.jsp' method='post'>
                    <button type='submit' name='singneOutButton'> Sign out </button>
                </form>
            </div>
            <div id="content">
                 <div class="predictions">
                        <form method='post'>
                            <button type='submit' id="openMyCookie"> Open my cookie </button>
                        </form>
                        <p></p>
                </div>
                <c:if test="${!empty(sessionScope.login)}" >
                    <form  action='index.jsp' method='post'>
                        <button type='submit' id='tablePredictions'> Get table of predictions </button>
                    </form>
                    <c:if test="${!empty(predictionsBean.messages)}" >
                        <c:set var="countPredPerPage"  scope="session"  value="15" />
                        <c:if test="${empty(pageNumber)}">
                            <c:set var="pageNumber"  scope="session"  value="1" />
                        </c:if>
                        <c:set var="index"  scope="session"  value="${(pageNumber - 1) * countPredPerPage + 1}" />
                        <table>
                            <c:forEach items="${predictionsBean.messages}" begin="${(pageNumber - 1) * countPredPerPage}" end="${countPredPerPage * pageNumber - 1}" var="prediction">
                                    <tr>
                                        <td><c:out value="${index}"/></td>
                                        <td><c:out value="${prediction}"/></td>
                                    </tr>
                                    <c:set var="index"  scope="session"  value="${index + 1}" />
                            </c:forEach>
                        </table>
                        <form class='predictionsEditorForm' action='index.jsp' method='post'>
                            <button type='submit' id="delPred"> delete prediction </button>
                            <button type='submit' id="insPred"> insert prediction </button>
                            <button type='submit' id="editPred"> edit prediction </button>
                            <input type="number" id="numPred" min="${index - countPredPerPage}" max="${index - 1}"/>
                            <button type='submit' id="deleteConfirm"> delete </button>
                            <button type='submit' id="editConfirm"> edit </button>
                            <p id="textEditPred"></p>
                            <button type='submit' id="sendPred"> send prediction </button>
                            <button type='submit' id="sendEditedPrediction"> send edited prediction </button>
                        </form>
                        <c:if test="${pageNumber > 1}">
                            <form  action='index.jsp' method='post' class="navigate">
                                <button type='submit' id='prevPage'> Prev page </button>
                            </form>
                        </c:if>
                        <c:if test="${fn:length(predictionsBean.messages) + 1 > index}">
                            <form  action='index.jsp' method='post'  class="navigate">
                                <button type='submit' id='nextPage'> Next page </button>
                            </form>
                        </c:if>
                    </c:if>
                </c:if>
                <div class="errors">
                </div>
            </div>
            <div id="footer">
                <p> VEL SAPIENTISSIMUS ERRARE POTEST </p>
            </div>
        </div>
    </body>
</html>