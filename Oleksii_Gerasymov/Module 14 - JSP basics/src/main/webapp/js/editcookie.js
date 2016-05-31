$(function () {
    var form = $('.EditCookieForm'),
        editButton = form.find('#editCookie'),
        cookieName = form.find('#cookieName'),
        editError = form.find('.editError');

    editButton.on('click', function(e) {
        e.preventDefault();
        if (cookieName.val().length < 10) {
            editError.empty();
            editError.append('Cookie must have at least ten characters.');
        }
        else {
            editError.empty();
            form.submit();
        }
    });

});