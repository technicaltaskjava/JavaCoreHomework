$(function () {
    var form = $('#login'),
        fields = {
            username: form.find('#username'),
            password: form.find('#password'),
        },
        submitButton = form.find('#login-button'),
        errors = form.find('.errors'),
        validations = [
            {
                field: 'username',
                callback: validateUsername,
                message: 'Invalid username'
            }, {
                field: 'password',
                callback: validatePassword,
                message: 'Invalid password'
            }
        ];

    function validateUsername(value) {
        if (value && value.length > 3) {
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
