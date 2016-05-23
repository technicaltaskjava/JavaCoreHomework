$(function () {
    $('td:not(:first-child)').on('click', onEdit);
    $('th:first-child').on('click', onAdd);
    $('td:first-child').on('click', onDelete);
});

$(window).keydown(function (e) {
    if (e.keyCode === 13) {
        $('#edit').blur();
    }
});

function onAdd() {
    $('tr:last-child').after('<tr><td><button class="button-remove"></button></td><td>Cookie new</td><td>Coming soon...</td></tr>');
    $('tr:last-child td:first-child').on('click', onDelete);
    $('tr:last-child td:not(:first-child)').on('click', onEdit);
}

function onEdit(e) {
    var t = e.target || e.srcElement;
    var elementName = t.tagName.toLowerCase();
    if (elementName === 'input') {
        return false;
    }
    var val = $(this).html();
    var code = '<input type="text" id="edit" value="' + val + '" />';
    $(this).empty().append(code);
    var editElement = $('#edit');
    editElement.focus();
    editElement.blur(function () {
        var val = $(this).val();
        $(this).parent().empty().html(val);
    });
}

function onDelete() {
    $(this).closest('tr').remove();
}