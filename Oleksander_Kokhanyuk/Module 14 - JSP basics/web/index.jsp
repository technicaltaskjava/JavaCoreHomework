<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>My Fortune Cookies</title>
    <style type="text/css">
        @import url("css/style.css");
    </style>
    <script src="js/jquery-2.2.3.js"></script>
    <script src="js/fortune.js"></script>
</head>
<body>
<div class="box1" align='center'>
<div class="text" align="left">
    <c:if test="${not empty sessionScope.get('userInfo')}">
        Hello  <c:out value="${sessionScope.get('userInfo').getUser()}"/>!
    </c:if>
</div>
    <p class="text1">Fortune cookies</p>
    <form class="test" action="index.jsp">
        <button type="submit" name="submit">Open Cookies</button>
        <div class="message">
        </div>
    </form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
