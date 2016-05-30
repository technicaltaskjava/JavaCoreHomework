$(function () {
    var form = $('.login-form'),
        submitButton = form.find('button[type="submit"]'),
        fields = [
            {
                element: form.find('#email'),
                callback: validateEmail,
                message: 'Invalid email'
            }, {
                element: form.find('#password'),
                callback: validatePassword,
                message: 'Invalid password (must have 6&ndash;16 valid characters, at&nbsp;least a&nbsp;number, and at&nbsp;least a&nbsp;special character)'
            }
        ],
        fieldById = {
            email: fields[0],
            password: fields[1]
        };

    function validateElement(elem) {
        var field = fieldById[$(elem).attr('id')];
        if (!field.callback($(elem).val())) {
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
