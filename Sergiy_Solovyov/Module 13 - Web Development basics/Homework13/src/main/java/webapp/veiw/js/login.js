$(function () {
    var form = $('#slick-login'),
        fields = {
            email: form.find('#email'),
            password: form.find('#password')
        },
        submitButton = form.find('input[type="submit"]'),
        errors = form.find('.errors'),
        validations = [
            {
                field: 'email',
                callback: validateEmail,
                message: 'Invalid email'
            }, {
                field: 'password',
                callback: validatePassword,
                message: 'Password must contain at least one number, one lowercase and one uppercase letter'
            }
        ];


    function validateEmail(value) {
        var regexp = /^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$/;

        if (value && regexp.test(value)) {
            return true;
        }

        return false;
    }
    function validatePassword(value) {
        var regexp = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/;

        if (value && regexp.test(value)) {
            return true;
        }

        return false;
    }

    submitButton.on('click', function (e) {
        e.preventDefault();
        errors.empty();

        var allIsValid = true;
        validations.forEach(function (el) {
            if ( !el.callback(fields[el.field].val()) ) {
                errors.append('<div>' + el.message + '</div>');
				errors.append('-----------------------------------------');
                allIsValid = false;
            }
        });

        if (allIsValid) {
            form.submit();
        }
    });
});