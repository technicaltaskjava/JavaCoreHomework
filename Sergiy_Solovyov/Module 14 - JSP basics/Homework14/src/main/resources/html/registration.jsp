<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
   <meta charset="utf-8">
   <title>Slick Login</title>
   <link rel="stylesheet" type="text/css" href="/resources/css/login.css" />
   <link rel="stylesheet" type="text/css" href="/resources/css/tooltip.css" />
   <script src="/resources/js/jquery-2.2.3.js"></script>
   <script src="/resources/js/reg.js"></script>
   <script src="/resources/js/ajaxreg.js"></script>
   <script src="/resources/js/jquery.cookie.js"></script>
</head>
<body>
   <header>
      <a class="main" href="../index">
         <h1 id="slogan">Taste our cookies and become a bit happier</h1>
         <span>Go to home page</span>
      </a>
      <img id="logo"src="/resources/img/irb3.gif">
      </img>
      <%
      String nick = (String) session.getAttribute("nick");
      if (nick != null)
      out.print("<h4 id='session'>You logged as <span>" +
                    nick +"</span> <a href='../admin'>admin panel</a>  <a href='../logout'>logout</a></h4>");
      %>
   </header>
   <div id = "regsuccess"></div>
   <form id="slick-login">
      <img id= "anim" src="/resources/img/reg.jpg">
      <h3>Registration form</h3>
      <div class="errors">
      </div>
      <label for="email">username</label><a class="tooltip-1" href="#">
      <span>Lower case</span><input   id = "email" type="email" name="email" class="placeholder" placeholder="Email"required></a>
      <label for="nick">nick</label><a class="tooltip-1" href="#">
      <span>Min 3 letters</span><input  id = "nick" type="text" name="nick" class="placeholder" placeholder="Nick"required></a>
      <label for="age">age</label><a class="tooltip-1" href="#">
      <span>Range 3 - 100</span><input  id = "age" type="number" min="3" max="100" name="age" class="placeholder" placeholder="Your age"></a>
      <label for="password">password</label><a class="tooltip-2" href="#">
      <span>Should contain at least one number, one lowercase and one uppercase letter</span><input id = "password" type="password" name="password" class="placeholder" placeholder="Password" required></a>
      <label for="password2">password</label><a class="tooltip-2" href="#">
      <span>Should contain at least one number, one lowercase and one uppercase letter</span><input id = "password2" type="password" name="password2" class="placeholder" placeholder="Confirm Password" required></a>
      <input  id="sub" type="submit" value="Register" >
   </form>
   <footer>Copyright © Solovyov Production, all rights reserved 2016</footer>
</body>
</html>