/**
 * Created by Olga Kramska on 15-May-16.
 */
$(function () {
    var form = $('#registration'),
        fields = {
            username: form.find('#username'),
            email: form.find('#email'),
            password1: form.find('#password1'),
            password2: form.find('#password2')
        },
        submitButton = form.find('#register'),
        errors = $('.errors'),
        validations = [
            {
                field: 'username',
                callback: validateUser,
                message: 'Username is not allowed'
            }, {
                field: 'email',
                callback: validateEmail,
                message: 'Invalid e-mail address'
            }, {
                field: 'password1',
                callback: validatePassword1,
                message: 'Your password is too short'
            }, {
                field: 'password2',
                callback: validatePassword2,
                message: 'Passwords do not match'
            }
        ];

    function validateUser(value) {
        if (value && value.length > 2) {
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

    function validatePassword1(value) {
        if (value && value.length > 5) {
            return true;
        }
        return false;
    }

    function validatePassword2(value) {
        if (value === fields.password1.val()) {
            return true;
        }
        return false;
    }

    submitButton.on('click', function (e) {
        e.preventDefault();
        form.find('.errors').remove();

        var allIsValid = true;

        validations.forEach(function (el) {
            if (!el.callback(fields[el.field].val())) {
                fields[el.field].after(errors.clone().append(el.message));
                allIsValid = false;
            }
        });

        if (allIsValid) {
            form.submit();
        }
    });
});