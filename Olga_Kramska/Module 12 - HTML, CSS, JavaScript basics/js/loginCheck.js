/**
 * Created by Olga Kramska on 14-May-16.
 */
$(function () {
    var form = $('#login'),
        fields = {
            username: form.find('#username'),
            password: form.find('#password')
        },
        submitButton = form.find('#login-button'),
        errors = form.find('.errors'),
        validations = [
            {
                field: 'username',
                callback: validateUser,
                message: 'User not found'
            }, {
                field: 'password',
                callback: validatePassword,
                message: 'Invalid username or password'
            }
        ];

    function validateUser(value) {
        if (value && value.length > 2) {//TODO check validation algorithm
            return true;
        }
        return false;
    }

    function validatePassword(value) {
        if (value && value.length > 5) {//TODO check validation algorithm
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
                errors.append('<p>' + el.message + '</p>');
                allIsValid = false;
            }
        });

        if (allIsValid) {
            form.submit();
        }
    });
});