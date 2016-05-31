
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>My Fortune Cookies</title>
    <style type="text/css">
        @import url("css/style.css");
    </style>
    <script src="js/jquery-2.2.3.js"></script>
    <script src="js/edit.js"></script>
</head>
<body>
<div class="box" align='center'>
    <p class="text1">Fortune cookies</p>
    <p class="text1">Hello ${sessionScope.get('userInfo').getUser()}!</p>
    <form class="test" action="edit.jsp" method="post">
        <table class="message"></table>
        <button type="button" name="submit">Show all</button>
        <button type="button" name="clear">Hide</button>
        <table>
            <tr>
                <td>
                    <button type="button" name="edit">Edit/Add...</button>
                </td>
                <td>
                    <input id="idEdit" type="text" size=3 placeholder="id">
                </td>
                <td><input id="editMessage" type="text" placeholder="Input new message"></td>
            </tr>
            <tr>
                <td>
                    <button type="button" name="remove">Remove</button>
                </td>
                <td>
                    <input id="idRemove" type="text" size=3 placeholder="id">
                </td>
                <td></td>
            </tr>
        </table>
    </form>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
