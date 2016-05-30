$(function () {
    var form = $('.volidation'),
        fieldSighn = {
            login: form.find('#login'),
            password: form.find('#password'),
        },

        submitButton = form.find('button[type="submit"]'),
        errors = form.find('.errors'),
        validationSig = [
            {
                field: 'login',
                callback: validateLogin,
                message: 'Invalid login name (can\'t be empty)'
            }, {
                field: 'password',
                callback: validatePassword,
                message: 'Invalid password (password > 5 and password < 10)'
            }
        ];

    function validateLogin(value) {
        if (value && value.length > 1) {
            return true;
        }
        return false;
    }

    function validatePassword(value) {
        if (value.length > 4 && value.length < 10) {
            return true;
        }
        return false;
    }

    submitButton.on('click', function (e) {
        e.preventDefault();
        errors.empty();
        var allIsValid = true;
        var index = validationSig.length - 1;
        validationSig.forEach(function (el) {
            if (!el.callback(fieldSighn[el.field].val())) {
                errors.append('<p>' + el.message + '</p>');
                allIsValid = false;
            }
        });

        if (allIsValid) {
            {
                form.submit();
            }
        }
    });
});
