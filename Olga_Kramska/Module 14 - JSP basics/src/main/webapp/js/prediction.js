/**
 * Created by Olga Kramska on 30-May-16.
 */

$(function () {

    function init() {
        bindEvents();
    }

    function bindEvents() {
        $(document).on('click', '.delete-cookie', deletePredictionHandler);
        $(document).on('click', '.edit-cookie', editPredictionHandler);
        $("#add-cookie").on('click', addPredictionHandler);
    }

    function deletePredictionHandler() {
        var id = $(this).closest("tr").attr('id');
        $.post("delete", {'id': id})
        	.done(function (data, status) {
                $('#' + id).remove();
            })
            .fail(function(jqXHR) {
            	console.error(jqXHR.responseText);
            });
    }

    function editPredictionHandler() {
        var id = $(this).closest("tr").attr('id');
        var dialog = createEditCookieDialog(id);
        dialog.dialog("open");
        var tds = $(this).closest("tr").find('td');
        var cookie = $(tds).eq(0).text();
        dialog.find('#cookie-edit').val(cookie);
        var prediction = $(tds).eq(1).text();
        dialog.find('#prediction-edit').val(prediction);
    }

    function addPredictionHandler() {
    	var dialog = createAddCookieDialog();
    	dialog.dialog("open");
    }

    init();

});