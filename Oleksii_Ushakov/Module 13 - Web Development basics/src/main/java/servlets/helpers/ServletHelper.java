package servlets.helpers;

import dao.entity.UserStatus;

import javax.servlet.http.Cookie;

/**
 * @author augustprime
 */
public class ServletHelper {
    private static final String PAGE_FOOTER = "</section></body></html>";

    private ServletHelper() {
    }

    public static boolean isUserRegistered(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("Status".equals(c.getName())) {
                    return UserStatus.fromString(c.getValue()) == UserStatus.REGISTERED;
                }
            }
        }
        return false;
    }

    public static String getHTMLPageHeader() {
        StringBuilder pageHeader = new StringBuilder("<!doctype html>");
        pageHeader.append("<html lang='en'>")
                  .append("<head>")
                  .append("    <title>Predication</title>")
                  .append("    <meta charset='utf-8'/>")
                  .append("    <link rel='shortcut icon' href='images/favicon.ico' type='image/x-icon'>")
                  .append("    <link href='css/style.css' rel='stylesheet'>")
                  .append("    <script src='js/jquery-2.2.3.min.js'></script>")
                  .append("</head>")
                  .append("<body>")
                  .append("")
                  .append("<section class='content'>")
                  .append("    <h1><a href='http://augustprime.github.io/'>Fortune Cookies</a></h1>");

        return pageHeader.toString();
    }

    public static String getHTMLPageRegisteredMenu() {
        StringBuilder pageMenu = new StringBuilder();
        pageMenu.append("<nav>")
                .append("    <ul>")
                .append("		<li><a href='login'>Login</a></li>")
                .append("		<li><a href='predication'>Prediction</a></li>")
                .append("		<li><a href='predication_table'>Prediction table</a></li>")
                .append("	</ul>")
                .append("</nav>");

        return pageMenu.toString();
    }

    public static String getHTMLPageNotRegisteredMenu() {
        StringBuilder pageMenu = new StringBuilder();
        pageMenu.append("<nav>")
                .append("    <ul>")
                .append("		<li><a href='login'>Login</a></li>")
                .append("		<li><a href='registration'>Registration</a></li>")
                .append("		<li><a href='predication'>Prediction</a></li>")
                .append("	</ul>")
                .append("</nav>");

        return pageMenu.toString();
    }

    public static String getHTMLPageFooter() {
        return PAGE_FOOTER;
    }
}
