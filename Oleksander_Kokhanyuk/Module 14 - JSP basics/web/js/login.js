$(function () {
    var form = $('.test'),
        fields = {
            firstName: form.find('#username'),
            password: form.find('#password'),
        },
        submitButton = form.find('button[type="submit"]'),
        errors = form.find('.errors'),
        validations = [
            {
                field: 'firstName',
                callback: validateFirstName,
                message: 'Invalid first name (can\'t be empty)'
            }
        ];

    function validateFirstName(value) {
        if (value && value.length > 1) {
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
