$(function () {
    var form = $('#login'),

        submitButton = form.find('#login-button'),
        errors = form.find('.errors'),


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

    errors.append("test");
    form.submit();


    });
});
