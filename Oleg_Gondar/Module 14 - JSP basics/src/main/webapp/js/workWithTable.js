$(document).ready(function () {

    $('#cook_add').click(insertInTable);
    $('#back').click(insertInTable);

});


function insertInTable() {
    $("#errors").empty();
    var hasData = true;
    hasData = hasData && ($('#ins_cookie').val().length > 1);
    hasData = hasData && ($('#ins_id').val().length > 0);

    if (hasData) {

        $("#cook_add_form").submit();

    } else {
        $("#errors").append("<p>Fill all fields with right values!!!</p>");
    }
}
function deleteRow() {
    $("#cookie").on('click', '.deleteRowButton', function () {
        $(this).parents('tr').remove();
    });
}
function editRow() {
    $("#cookie").on('click', '.editRowButton', function () {
        var par = $(this).parent().parent();
        var id = par.children("td:nth-child(1)");
        var cookie = par.children("td:nth-child(2)");
        $('#ins_id').val(id.html());
        $('#ins_cookie').val(cookie.html());
        $(this).parents('tr').remove();

    });
}