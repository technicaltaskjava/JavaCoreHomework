<%@ page session="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Fortune cookies</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="description"
          content="Fortune cookie - these cookie with message inside. Do you want to know your prediction? Open up!"/>
    <meta name="keywords" content="fortune cookies"/>
    <link href="site/styles/styles.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="site/images/favicon.ico" type="image/x-icon">

</head>

<script src="site/js/jquery-2.2.3.js"></script>

<script src="site/script/editor_table.js"></script>

<body>
<div id="wrapper">

    <div id="header">

        <div class="logo">
            <a href="/index"> <b>Fortune cookies</b> </a>
        </div>

        <div class="menu">
            <ul class="navigate">
                <li class="exit"><a href="/ControllerLogout">Exit</a></li>
            </ul>
        </div>

    </div>

    <div class="line"></div>

    <div class="content">

            <p>if you want to change some cookie click on it double</p>

        <div class="blok">


            <table id="table_prediction">
                <thead>
                <tr>
                    <th>number</th>
                    <th>prediction</th>

                </tr>
                </thead>

                <c:forEach items="${predictions}" var="cookieItem">
                    <tr>
                        <td class="number">${cookieItem.id}</td>
                        <td class="field">${cookieItem.cookie}</td>

                    </tr>
                </c:forEach>

                <tfoot>
                <tr>
                    <form method="get" action="ControllerCookie">
                        <input type="hidden" name="move" value="return"/>
                        <input type="submit" value="return"/>
                    </form>
                </tr>
                <tr>


                        <form method="get" action="ControllerCookie">
                            <input type="hidden" name="move" value="next"/>
                            <input type="submit" value="next"/>
                        </form>


                </tr>

                <tr>

                    <td>
                        <form class="insert" action="/insert" method="post">
                            <input type="text" class="input-cookie" name="insert_cookie">

                            <input class="add" type="submit" name="add" value="add">

                            <input type="reset" value="clean">

                        </form>
                    </td>
                </tr>
                </tfoot>


            </table>

        </div>


    </div>

</div>


<div class="clear"></div>


<div id="footer">

    <p>Virtual cookies by Tokareva Tania</p>
    <p>2016</p>

</div>
</body>
</html>