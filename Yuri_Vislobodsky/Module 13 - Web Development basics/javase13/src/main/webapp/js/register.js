$(function () {
    var form = $('.register-form'),
        submitButton = form.find('button[type="submit"]'),
        fields = [
            {
                element: form.find('#username'),
                callback: validateUserName,
                message: 'Invalid user name (must have <nobr>A-Z</nobr> and <nobr>a-z</nobr> characters only and spaces between parts)'
            },
            {
                element: form.find('#email'),
                callback: validateEmail,
                message: 'Invalid email'
            }, {
                element: form.find('#year'),
                callback: validateYear,
                message: 'Invalid year of&nbsp;birth (your age must be&nbsp;16&ndash;100)'
            }, {
                element: form.find('#password'),
                callback: validatePassword,
                message: 'Invalid password (must have 6&ndash;16 valid characters, at&nbsp;least a&nbsp;number, and at&nbsp;least a&nbsp;special character)'
            }, {
                element: form.find('#password2'),
                callback: validatePassword2,
                message: 'The passwords are not the same',
                elementCompare: form.find('#password')
            }
        ],
        fieldById = {
            username: fields[0],
            email: fields[1],
            year: fields[2],
            password: fields[3],
            password2: fields[4]
        };

    function validateElement(elem) {
        var field = fieldById[$(elem).attr('id')];
        var elemCompare = field.elementCompare;
        if (!field.callback($(elem).val(), $(elemCompare).val())) {
            showError(elem, field.message);
            return false;
        } else {
            hideError(elem);
            return true;
        }
    }

    submitButton.on('click', function (e) {
        e.preventDefault();
        var isValid = true;

        fields.forEach(function (el) {
            if (!validateElement($(el.element))) {
                isValid = false;
            }
        });

        if (isValid) {
            form.submit();
        }
    });

    fields.forEach(function (el) {
        $(el.element).blur(function () {
            validateElement(this);
        });
    });
});
