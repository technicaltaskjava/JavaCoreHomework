$(function () {
    var form = $('.registerVolid'),
        fields = {
            email: form.find('#email'),
            login: form.find('#login'),
            password: form.find('#password'),
        },
        submitButton = form.find('button[type="submit"]'),
        errors = form.find('.errors'),
        validations = [
            {
                field: 'email',
                callback: validateEmail,
                message: 'Invalid email'
            }, {
                field: 'login',
                callback: validateLogin,
                message: 'Invalid login name (can\'t be empty)'
            }, {
                field: 'password',
                callback: validatePassword,
                message: 'Invalid password (password > 5 and  password < 10)'
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

    function validateEmail(value) {
        var regexp = /^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$/;
        if (value && regexp.test(value)) {
            return true;
        }
        return false;
    }

    submitButton.on('click', function (e) {
        e.preventDefault();
        errors.empty();
        var allIsValid = true;
        var index = validations.length - 1;

        validations.forEach(function (el) {
            if (!el.callback(fields[el.field].val())) {
                errors.append('<p>' + el.message + '</p>');
                allIsValid = false;
            }
        });

        if (allIsValid) {
            form.submit();
        }
    });
});
