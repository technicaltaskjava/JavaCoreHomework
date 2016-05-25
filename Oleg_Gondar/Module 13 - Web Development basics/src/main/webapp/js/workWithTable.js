$(document).ready(function () {

    $('#cook_add').click(insertInTable);
    $('#cookie').on('click', '.editRowButton', editRow())
    $('#cookie').on('click', '.deleteRowButton', deleteRow())

});


function insertInTable() {
    $("#errors").empty();
    var hasData = true;
    hasData = hasData && ($('#ins_cookie').val().length > 1);
    hasData = hasData && ($('#ins_id').val().length > 0);

    if (hasData) {
        $("#cookie").find("tbody").append(
            "<tr>" +
            "<td>" + $('#ins_id').val() + "</td>" +
            "<td>" + $('#ins_cookie').val() + "</td>" +
            "<td><button class='deleteRowButton' type='button'>Delete row</button>" +
            "<button class='editRowButton' type='button'>Edit row</button><td></tr>"
        );
        $('#ins_id').val('');
        $('#ins_cookie').val('');
        $('#cookie').trigger("update");

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