package com.servlet;

import com.data.cookie.Cookie;
import com.data.mydao.base.BaseContact;
import com.data.users.User;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class MyCookieServlet extends HttpServlet {
    private String prediction;


    public void createPrediction(List<Cookie> cookies) {
        Random random = new Random();
        int id = random.nextInt(cookies.size());
        prediction = cookies.get(id).getPrediction();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List cookies;
        ServletContext servletContext = req.getServletContext();
        String info = "start \"" + this.getServletName() + "\"  user " + ((User) req.getSession().getAttribute("PRINCIPAL")).getLogin() + "on the page";
        req.setAttribute("info", info);
        cookies = (List) ((BaseContact) (servletContext.getAttribute("baseContact"))).getCookies();
        createPrediction(cookies);
        resp.setContentType("text/html");

        resp.getWriter().println("<!DOCTYPE html>\n" +
                "<html id=\"background\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Fortune Cookie</title>\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n" +
                "    <script src=\"js/jquery-2.2.3.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "<header id=\"head\">\n" +
                "    <img src=\"img/MyFortuneCookie.png\" alt=\"MyFortuneCookie\">\n" +
                "</header>\n" +
                "<div class=\"main\">\n" +
                "    <h3>Virtual Fortune Cookie</h3>\n" +
                "    <p class=\"simplyClick\">\n" +
                "        To open up your virtual fortune cookie, simply click the button below:\n" +
                "    </p>\n" +
                "    <div class=\"massage\"></div>\n" +
                "    <form class=\"cookies\" method=\"post\">\n" +
                "        <button id=\"show\" type=\"submit\" name=\"button\">Open my cookie</button>\n" +
                "        <div class=\"message\">\n" +
                "        </div>\n" +
                "    </form>\n" +
                "    <p class=\"line\"></p>\n" +
                "    <img src=\"img/cookie.png\" alt=\"Foodzia Fortune Cookie\" class=\"img\">\n" +
                "    <span id=\"tableHead\">Predictions</span>\n" +
                "    <img src=\"img/cookie.png\" alt=\"Foodzia Fortune Cookie\" class=\"img\">\n" +
                "    <div class=\"content\"></div>\n" +
                "    <div class=\"configureTable\">\n" +
                "        <script>\n" +
                "            $(function () {\n" +
                "                var form = $('form');\n" +
                "                var massege = form.find('.message');\n" +
                "                var change = form.find('.change');\n" +
                "                var addPrediction = $('#commands');\n" +
                "                var lines = [\n" +
                "                    \"Tell them what you really think. Otherwise, nothing will change.\",\n" +
                "                    \"A package of value will arrive soon.\",\n" +
                "                    \"A loved one is of utmost importance at this time.\",\n" +
                "                    \"Look to your inner being for guidance.\",\n" +
                "                    \"Good things are on their way.\",\n" +
                "                    \"A warm smile is testimony of a generous nature.\",\n" +
                "                    \"An unexpected relationship will become permanent.\"\n" +
                "                ];\n" +
                "                var TableTitle = [\"id\", \"message\"];\n" +
                "                var TableValue = [];\n" +
                "                function addMassege() {\n" +
                "                    for (var i = 0; i < lines.length; i++) {\n" +
                "                        var id = TableValue.length;\n" +
                "                        TableValue[id] = [id + 1, [lines[i]]];\n" +
                "                    }\n" +
                "                }\n" +
                "                addMassege();\n" +
                "                var mytable = $('<table/>', {\n" +
                "                    class: 'mytable'\n" +
                "                }).append(\n" +
                "                        $('<thead/>'),\n" +
                "                        $('<tfoot/>'),\n" +
                "                        $('<tbody/>')\n" +
                "                );\n" +
                "                var TitleCell = $('<tr/>');\n" +
                "                $.each(TableTitle, function (myIndex, myData) {\n" +
                "                    TitleCell.append(\n" +
                "                            $('<th/>', {\n" +
                "                                text: myData\n" +
                "                            })\n" +
                "                    );\n" +
                "                });\n" +
                "                $(\"thead\", mytable).append(TitleCell);\n" +
                "                $.each(TableValue, function () {\n" +
                "                    var DataCell = $('<tr/>');\n" +
                "                    $.each(this, function () {\n" +
                "                        DataCell.append(\n" +
                "                                $('<td/>', {\n" +
                "                                    text: this\n" +
                "                                })\n" +
                "                        );\n" +
                "                    });\n" +
                "                    $(\"tbody\", mytable).append(DataCell);\n" +
                "                });\n" +
                "                $('.content').append(mytable);\n" +
                "                function getRandomInt(min, max) {\n" +
                "                    return Math.floor(Math.random() * (max - min + 1)) + min;\n" +
                "                }\n" +
                "                $(\"#show\").click(function (event) {\n" +
                "                    massege.empty();\n" +
                "                    var id = getRandomInt(0, lines.length - 1);\n" +
                "                    massege.append(\n" +
                "                            '<p class=\"getMasseg\">' + lines[id] + '</p>');\n" +
                "                    event.preventDefault();\n" +
                "                });\n" +
                "                var id = TableValue.length + 1;\n" +
                "                $(\"#addPre\").click(function (event) {\n" +
                "                    addPrediction.empty();\n" +
                "                    event.preventDefault();\n" +
                "                    addPrediction.append('<textarea id=\"second\" type=\"text\"></textarea><br><button id=\"bt2\">ENTER TEXT</button><div id=\"addEl\"></div>');\n" +
                "                    var addEl = $(\"#addEl\");\n" +
                "                    $('#bt2').click(function (event) {\n" +
                "                        event.preventDefault();\n" +
                "                        var newPrediction = $(\"#second\")[0];\n" +
                "                        $(\"tbody\", mytable).append('<tr><td>' + (id++) + '</td> <td>' + newPrediction.value + '</td> </tr> ');\n" +
                "                        lines[lines.length] = newPrediction.value;\n" +
                "                        addPrediction.empty();\n" +
                "                    });\n" +
                "                });\n" +
                "                $(\"#delPre\").click(function (event) {\n" +
                "                    addPrediction.empty();\n" +
                "                    addPrediction.append('<input id=\"trees\" type=\"text\" placeholder=\" ID \"><br><button id=\"bt3\">DELL \\'ID\\'</button><div id=\"dellEl\"></div>');\n" +
                "                    var dellEl = $(\"#dellEl\");\n" +
                "                    event.preventDefault();\n" +
                "                    $('#bt3').click(function (event) {\n" +
                "                        event.preventDefault();\n" +
                "                        var id = $(\"#trees\")[0].value;\n" +
                "                        var regexp = /^[0-9]/;\n" +
                "                        var serchRes = true;\n" +
                "                        if (regexp.test(id)) {\n" +
                "                            for (var i = 0; i < mytable.length; i++) {\n" +
                "                                for (var r = 0; r < mytable[i].rows.length; r++) {\n" +
                "                                    if (id === mytable[i].rows[r].cells[0].innerHTML) {\n" +
                "                                        mytable[i].deleteRow(r);\n" +
                "                                        serchRes = false;\n" +
                "                                        addPrediction.empty();\n" +
                "                                    }\n" +
                "                                }\n" +
                "                            }\n" +
                "                            if (serchRes) {\n" +
                "                                addPrediction.empty();\n" +
                "                                addPrediction.append('<p class=\"errors\">ID NOT FOUND<br>Please try again</p>');\n" +
                "                            }\n" +
                "                        }\n" +
                "                        else {\n" +
                "                            addPrediction.empty();\n" +
                "                            addPrediction.append('<p class=\"errors\">ID NOT CORRECT<br>Please try again</p>');\n" +
                "                        }\n" +
                "                    });\n" +
                "                });\n" +
                "                $(\"#changePre\").click(function (event) {\n" +
                "                    addPrediction.empty();\n" +
                "                    addPrediction.append('<input id=\"forth\" type=\"text\" placeholder=\" ID \"><br><button id=\"bt4\">ENTER \\'ID\\'</button><div id=\"changeEl\"></div>');\n" +
                "                    event.preventDefault();\n" +
                "                    $('#bt4').click(function (event) {\n" +
                "                        event.preventDefault();\n" +
                "                        var id = $(\"#forth\")[0].value;\n" +
                "                        var regexp = /^[0-9]/;\n" +
                "                        var serchRes = true;\n" +
                "                        if (regexp.test(id)) {\n" +
                "                            for (var i = 0; i < mytable.length; i++) {\n" +
                "                                for (var r = 0; r < mytable[i].rows.length; r++) {\n" +
                "                                    if (id === mytable[i].rows[r].cells[0].innerHTML) {\n" +
                "                                        serchRes = false;\n" +
                "                                    }\n" +
                "                                }\n" +
                "                            }\n" +
                "                            if (serchRes) {\n" +
                "                                addPrediction.empty();\n" +
                "                                addPrediction.append('<p class=\"errors\">ID NOT FOUND<br>Please try again</p>');\n" +
                "                            }\n" +
                "                            else {\n" +
                "                                addPrediction.empty();\n" +
                "                                addPrediction.append('<textarea id=\"fifth\" type=\"text\"></textarea><br><button id=\"bt5\">ENTER TEXT</button>');\n" +
                "                                $('#bt5').click(function (event) {\n" +
                "                                    event.preventDefault();\n" +
                "                                    var text = $(\"#fifth\")[0].value;\n" +
                "                                    for (var i = 0; i < mytable.length; i++) {\n" +
                "                                        for (var r = 0; r < mytable[i].rows.length; r++) {\n" +
                "                                            if (id === mytable[i].rows[r].cells[0].innerHTML) {\n" +
                "                                                for (var start = 0; start < lines.length; start++) {\n" +
                "                                                    if (lines[start] === mytable[i].rows[r].cells[1].innerHTML) {\n" +
                "                                                        lines[start] = text;\n" +
                "                                                    }\n" +
                "                                                }\n" +
                "                                                mytable[i].rows[r].cells[1].innerHTML = text;\n" +
                "                                                addPrediction.empty();\n" +
                "                                            }\n" +
                "                                        }\n" +
                "                                    }\n" +
                "                                });\n" +
                "                            }\n" +
                "                        }\n" +
                "                        else {\n" +
                "                            addPrediction.empty();\n" +
                "                            addPrediction.append('<p class=\"errors\">ID NOT CORRECT<br>Please try again</p>');\n" +
                "                        }\n" +
                "                    });\n" +
                "                });\n" +
                "            });\n" +

                "        </script>\n" +

                "    </div>\n" +
                "<form action=\"logout\" class=\"cookies\" method=\"post\">\n" +
                "    <input id=\"btnEnter\" type=\"submit\" value=\"LOGOUT\"/>\n" +
                "</form>" +
                "</div>\n" +
                "</body>\n" +
                "</html>");

    }

}
