
$(function () {
    var form = $('#send-cookie'),
        fields = {
            cookie: form.find('#cookie')
        },
        submitButton = form.find('input[type="submit"]'),
        errors = form.find('.errors'),
        validations = [
            {
                field: 'cookie',
                callback: validateLang,
                message: 'Only English'
            }
        ];

    function validateLang(value) {
        var regexp = /[a-zA-Z][a-zA-Z0-9]*/;
        if (regexp.test(value)) {

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
                $('.errors').html("Only English");
                errors.append('-----------------------------------------');
                allIsValid = false;
            }
        });

        if (allIsValid) {
            form.submit();
        }
    });
});
