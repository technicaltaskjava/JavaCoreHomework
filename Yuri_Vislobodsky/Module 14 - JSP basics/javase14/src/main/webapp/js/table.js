$(function () {
    $('.table-cookies td:not(:first-child)').on('click', onEdit);
    $('.table-cookies th:first-child').on('click', onAdd);
    $('.table-cookies td:first-child').on('click', onDelete);
});

$(window).keydown(function (e) {
    if (e.keyCode === 13) {
        $('#edit').blur();
    }
});

function onEditKeyPress() {
    var that = this;
    setTimeout(function () {
        var res = /[^A-Za-z0-9\.,\- ]/g.exec(that.value);
        that.value = that.value.replace(res, '');
    }, 0);
}

function onAdd() {
    $("#command").val("add");
    $("#name").val("New cookie");
    $("#message").val("Enter a message");
    $(this).closest("form").submit();
}

function onDelete() {
    $("#command").val("delete");
    $("#id").val($(this).find("button").val());
    $(this).closest("form").submit();
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
    editElement.on('keypress', onEditKeyPress);
    editElement.on('blur', onEditBlur);
    editElement.on('change', onEditChange);
}

function onEditChange() {
    var val = $(this).val();
    var tdCurr = $(this).parent();
    $(tdCurr).empty().html(val);
    $("#command").val("edit");
    var tdSet = tdCurr.closest("tr").find("td");
    $("#id").val($(tdSet).eq(0).find("button").val());
    $("#name").val($(tdSet).eq(1).html());
    $("#message").val($(tdSet).eq(2).html());
    $(tdCurr).closest("form").submit();
}

function onEditBlur () {
    var val = $(this).val();
    $(this).parent().empty().html(val);
}
