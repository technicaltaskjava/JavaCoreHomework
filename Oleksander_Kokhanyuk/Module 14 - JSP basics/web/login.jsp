<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="utf-8">
    <title>My Fortune Cookies</title>
    <style type="text/css">
        @import url("css/style.css");
    </style>
    <script src="js/jquery-2.2.3.js"></script>
    <script src="js/login.js"></script>
</head>
<body>
<div class="box" align='center'>
    <p class="text1">Fortune cookies</p>
    <p class="text2">Please enter login and password:</p>
    <form class="test" action=login method="post">
        <table>
            <tr>
                <td>
                    <input id="username" type="text" name="username" placeholder="Input login"><br>
                </td>
            </tr>
            <tr>
                <td>
                    <input id="password" type="password" name="password" placeholder="Input password"><br>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="submit" name="button">Enter</button>
                </td>
            </tr>
        </table>
        <div class="errors">
        </div>
    </form>

</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
