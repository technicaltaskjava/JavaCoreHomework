$(function () {
    var form = $('.test'),
        showButton = form.find('button[name="submit"]'),
        editButton = form.find('button[name="edit"]'),
        removeButton = form.find('button[name="remove"]'),
        clearButton = form.find('button[name="clear"]'),
        message = form.find('.message');
      showButton.on('click', function (e) {
        e.preventDefault();
        message.empty();

        $.ajax({
            type: "POST",
            url: "Edit",
            data: {request: "Show message"},
            success: function (data) {
                message.append('<p>' + data + '</p>');
            }
        });
    });

    clearButton.on('click', function (e) {
        e.preventDefault(),
            message.empty()

    });

    editButton.on('click', function (e) {
        e.preventDefault();
        message.empty()
        $.ajax({
            type: "POST",
            url: "Edit",
            data: {request: document.getElementById('idEdit').value + ":" + document.getElementById('editMessage').value},
            success: function (data) {
                message.append('<p>' + data + '</p>');
            }
        });
    });

    removeButton.on('click', function (e) {
        e.preventDefault();
        message.empty()
        $.ajax({
            type: "POST",
            url: "Remove",
            data: {request: document.getElementById('idRemove').value},
            success: function (data) {
                message.append('<p>' + data + '</p>');
            }
        });
    });
});

