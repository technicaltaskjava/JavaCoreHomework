$(function () {
    var form = $('.AddCookieForm'),
        addButton = form.find('#addCookie'),
        cookie = form.find('#cookie'),
        addError = form.find('.addError');

    addButton.on('click', function(e) {
        e.preventDefault();
        if (cookie.val().length < 10) {
            addError.empty();
            addError.append('Cookie must have at least ten characters.');
        }
        else {
            addError.empty();
            form.submit();
        }
    });

});