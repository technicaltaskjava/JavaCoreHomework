$(function () {
    var form = $('.test'),
        submitButton = form.find('button[type="submit"]'),
        message = form.find('.message');

    submitButton.on('click', function (e) {
        e.preventDefault();
        message.empty();
            $.ajax({
            type: "POST",
            url: "Fortune",
            data: {request: "message"},
            success: function (data) {
                message.append('<p>' + data + '</p>');
            }
        });
    });
});
