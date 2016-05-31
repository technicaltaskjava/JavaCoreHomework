<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>My Fortune Cookies</title>
    <style type="text/css">
        @import url("css/style.css");
    </style>
    <script src="js/validate.js"></script>
</head>
<body>
<div class="box" align='center'>
    <p class="text1">Fortune cookies</p>
    <p class="text2">Enter the data for registration:</p>
    <form class="text" action="Registry" method="post">
        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="username" required placeholder="Input your name"></td>
            </tr>
            <tr>
                <td>Surname:</td>
                <td>
                    <input type="text" name="surname" required placeholder="Input your last name">
                </td>
            </tr>
            <tr>
                <td>DOB:</td>
                <td>
                    <input type="text" name="dob" required placeholder="Input DOB">
                </td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" required placeholder="Input password"></td>
            </tr>
            <tr>
                <td>
                    e-mail:
                </td>
                <td>
                    <input type="text" name="email" pattern="^\s*\w+@\w+\.\w+\s*$" placeholder="Input your e-mail"><br>
                </td>
            </tr>
        </table>
        <input type="submit" value="Register">
    </form>

</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
