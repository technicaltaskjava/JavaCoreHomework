/**
 * Created by Olga Kramska on 16-May-16.
 */
$(function () {
    var dialog, form,
        cookie = $("#cookie"),
        prediction = $("#prediction"),
        password = $("#password");

    function addCookie() {
        var valid = true;
        valid = valid && (cookie.val().length > 3);
        valid = valid && (prediction.val().length > 2);

        if (valid) {
            $("#cookie-table").find("tbody").append(
                "<tr>" +
                "<td class='checkBoxPlace'><input class='checkBox' type='checkbox' name='select'/></td>" +
                "<td>" + cookie.val() + "</td>" +
                "<td>" + prediction.val() + "</td>" +
                "<td>" + (new Date()).toLocaleString() + "</td>" +
                "</tr>");
            dialog.dialog("close");
        }
        return valid;
    }

    dialog = $("#dialog-form").dialog({
        autoOpen: false,
        height: 300,
        width: 350,
        modal: true,
        buttons: {
            "Create a cookie": addCookie,
            Cancel: function () {
                dialog.dialog("close");
            }
        },
        close: function () {
            form[0].reset();
        }
    });

    $("#add-cookie").button().on("click", function () {
        dialog.dialog("open");
    });

    form = dialog.find("form").on("submit", function (event) {
        event.preventDefault();
        addCookie();
    });

});