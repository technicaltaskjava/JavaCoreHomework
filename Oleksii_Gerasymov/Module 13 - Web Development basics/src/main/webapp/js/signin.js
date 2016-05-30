$(function () {
    var form = $('.signInForm'),
        signInButton = form.find('#signIn'),
        username = form.find('#username'),
        password = form.find('#password'),
        logError = form.find('.logError');
        pwdError = form.find('.pwdError');

    function validateUsername(value) {
        var regexp = /^[a-zA-Z0-9_]+$/;
        return (value && regexp.test(value));
    }

    username.on('blur', function (e) {
       if (!validateUsername(username.val())) {
           logError.empty();
           logError.append('Login must contains only letters, digits and underscore.');
       }
        else {
           logError.empty();
       }
    });

    password.on('blur', function (e) {
           if (password.val().length < 1) {
               pwdError.empty();
               pwdError.append('Password must have at least one character.');
           }
            else {
               pwdError.empty();
           }
        });

    signInButton.on('click', function(e) {
        e.preventDefault();
        logError.empty();
        var valid = true;
        if (!validateUsername(username.val())) {
            pwdError.empty();
            logError.append('Login must contains only letters, digits and underscore.');
            valid = false;
        }
        if (password.val().length < 1) {
            pwdError.empty();
            pwdError.append('Password must have at least one character.');
            valid = false;
        }
        if (valid) {
            form.submit();
        }
    });

});