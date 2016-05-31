<%@ page import="java.util.List" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="com.data.cookie.Cookie" %>
<%@ page import="com.data.users.User" %>
<%@ page import="java.util.Random" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MY COOKIYS</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="js/jquery-2.2.3.js"></script>

</head>
<body>
<header id="head">
    <img src="img/MyFortuneCookie.png" alt="MyFortuneCookie">
</header>
<div class="main">
    <%!ServletContext servletContext; %>
    <%
        List<Cookie> cookies = (List<Cookie>) request.getAttribute("table");
    %>
    <h3>Hello <%= ((User) session.getAttribute("PRINCIPAL")).getLogin() %>
    </h3>

    <h3>If you wont to go to <a href="startPage.html">start page</a> </h3>
    <h3>If you wont to go to <a href="home.html">home page</a> </h3>


    <p class="line"></p>
    <img src="img/cookie.png" alt="Foodzia Fortune Cookie" class="img">
    <span id="tableHead">Predictions</span>
    <img src="img/cookie.png" alt="Foodzia Fortune Cookie" class="img">

    <table>
            <tr>
                <th>ID</th>
                <th>PERDICTION</th>
            </tr>
            <% for (Cookie cookiy : cookies) {%>
            <tr>
                <td><%= cookiy.getId() %>
                </td>
                <td><%= cookiy.getPrediction() %>
                </td>
            </tr>
            <% } %>
        </table>
    <form action="/myCookieServlet" class="cookies" method="get">
        <input style="margin-left: 5%;   background-color: #0bc408 " type="submit"  name="Previous"  value="Previous">
        <input style="margin-left: 70%;  background-color: #0bc408 " type="submit"  name="NEXT" value="NEXT">
    </form>
    <div class="message">
    </div>
    <br>
    <form class="chengTable" method="post">
        <input id="addPre" type="submit" value="ADD"/>
        <input id="delPre" type="submit" value="DELL"/>
        <input id="changePre" type="submit" value="CHANGE"/>
        <div id="commands"></div>
    </form>

    <script>
        $(function () {
            var addPrediction = $('#commands');
            $("#addPre").click(function (event) {
                addPrediction.empty();
                event.preventDefault();
                addPrediction.append(
                        '<form action="/myCookieServlet" method="get">' +
                        '<input id="second" type="text" name="addPrediction"  placeholder="Prediction"/>' +
                        '<br>' +
                        '<input  id = "bt2" type="submit" name="add" value="ENTER TEXT"/>' +
                        '</form>');
            });


            $("#delPre").click(function (event) {
                addPrediction.empty();
                event.preventDefault();
                addPrediction.append(
                        '<form action="/myCookieServlet" method="get">' +
                        '<input id="trees" type="text" name="dellPrediction" placeholder="ID"/>' +
                        '<br>' +
                        '<input id="bt3" type="submit" name="dell" value="ENTER ID"/>' +
                        '</form>');
                event.preventDefault();

            });


            $("#changePre").click(function (event) {
                addPrediction.empty();
                event.preventDefault();
                addPrediction.append(
                        '<form action="/myCookieServlet" method="get">' +
                        '<input id="forth" type="text" name="upID" placeholder="ID"/>' +
                        '<input id="second" type="text" name="upPrediction" placeholder="Prediction"/>' +
                        '<br>' +
                        '<input id="bt4" type="submit" name="up" value="ENTER ID"/>' +
                        '</form>');
                event.preventDefault();
            });



        });

    </script>
</div>
</body>
</html>
