$(function () {
    var form = $('.test'),
        fields = {
            email: form.find('#email'),
            username: form.find('#username'),
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
                field: 'username',
                callback: validateName,
                message: 'Invalid name (can\'t be empty)'
            }, {
                field: 'password',
                callback: validatePassword,
                message: 'Invalid password must ba at last 6 symbols'
            }
        ];

    function validateName(value) {
        if (value && value.length > 1) {
            return true;
        }

        return false;
    }

    function validatePassword(value) {
        if (value && value.length > 6) {
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
            if ( !el.callback(fields[el.field].val()) ) {
                errors.append('<p>' + el.message + '</p>');
                allIsValid = false;
            }
        });

        if (allIsValid) {
            form.submit();
        }
    });
});
