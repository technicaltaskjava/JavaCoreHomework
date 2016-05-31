$(document).ready(function () {

    $("#login-button").click(function (e) {
        var allValid = true;

        function validateName(value) {
            if (value && value.length > 1) {
                return false;
            }

            return true;
        }


        function validatePassword(value) {
            if (value && value.length > 5) {
                return false;
            }

            return true;
        }

        $("#errors").empty();
        e.preventDefault();
        if (validateName($('#username').val())) {
            $("#errors").append("<p>Error in username, must be 3 characters at least</p>");
            allValid = false;
        }
        if (validatePassword($('#password').val())) {
            $("#errors").append("<p>Error in password, must be 6 characters at least</p>");
            allValid = false;
        }
        if (allValid) {
            $("#mainpage").submit();
        }

    });
});
