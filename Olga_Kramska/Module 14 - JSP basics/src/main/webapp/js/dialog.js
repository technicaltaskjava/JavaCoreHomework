/**
 * Created by Olga Kramska on 31-May-16.
 */
function createEditCookieDialog(id) {
    var dialog = $("#dialog-edit").dialog({
        autoOpen: false,
        height: 300,
        width: 350,
        modal: true,
        buttons: {
            "Save": editCookie,
            Cancel: function () {
                dialog.dialog("close");
            }
        },
        title: "Edit prediction",
        close: function () {
            form[0].reset();
        }
    });

    var form = dialog.find("#edit").on("#edit-submit", function (event) {
        event.preventDefault();
        editCookie();
    });

    function editCookie() {
        var cookie = $("#cookie-edit").val(),
            prediction = $("#prediction-edit").val(),
            valid = true;
        valid = valid && (cookie.length > 3);
        valid = valid && (prediction.length > 2);

        if (valid) {
            $.post("edit",
                {
                    'id': id,
                    cookie: cookie,
                    prediction: prediction
                }).done(function (data) {
                    var row = $('#' + id).find('td');
                    $(row).eq(0).html(cookie);
                    $(row).eq(1).html(prediction);
                }).fail(function(jqXHR) {
                	console.error(jqXHR.responseText);
                });
            dialog.dialog("close");
        }
        return valid;
    }

    return dialog;
}

function createAddCookieDialog() {
	var dialog = $("#dialog-add").dialog({
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
        title: "Add prediction",
        close: function () {
            form[0].reset();
        }
    });
	
	var form = dialog.find("#add").on("#add-submit", function (event) {
        event.preventDefault();
        addCookie();
    });
	
	function addCookie() {
        var valid = true,
            cookie = $("#cookie").val(),
            prediction = $("#prediction").val();
        valid = valid && (cookie.length > 3);
        valid = valid && (prediction.length > 2);

        if (valid) {
            $.post("predictions",
                {
                    cookie: cookie,
                    prediction: prediction
                }).done(function (data) {
                    $("#cookie-table").find("tbody").append(data);
                }).fail(function(jqXHR) {
                	console.error(jqXHR.responseText);
                });
            dialog.dialog("close");
        }
        return valid;
    }
	
	return dialog;
}