var loginErrorMessage = "Login incorrect. It should begin with letter, and its length must be more than 3";
var passwordErrorMessage = "Password incorrect. It should contain only letters and numbers, and it length must be greater than 3 and smaller than 9";
var firstNameErrorMessage = "First name should contain only letters, and it length must be greater than 1";
var secondNameErrorMessage = "Second name should contain only letters, and it length must be greater than 1";
var emailErrorMessage = "Wrong email";

var differentPasswordConfirmMessage = "Password and it confirmation are different";

function isCorrectLogin(login) {
    return /^[a-zA-Z][\w\d]{3,}/.test(login);
}

function isCorrectPassword(password) {
    return /^[\w\d]{4,8}/.test(password);
}

function isCorrectName(name) {
    return /^[\w]{2,}/.test(name);

}

function isCorrectEmail(email) {
    return /^[\w]+[\w\W\d]+@[\w\W]{2,}(\.\w{2,4})+$/.test(email);
}

$(document).ready(function () {
    var messageSpan = $(".messageSpan");
    messageSpan.empty().hide();

    var hasError = false;
    var login = "";
    var password = "";
    var passwordCheck = "";
    var firstName = "";
    var secondName = "";
    var email = "";

    function addErrorMessage(message) {
        hasError = true;
        messageSpan.append("<p>" + message + "</p>");
    }

    function validateLoginAndPassword() {
        login = $("#login").val();
        password = $("#password").val();

        if (!isCorrectLogin(login)) {
            addErrorMessage(loginErrorMessage);
        }

        if (!isCorrectPassword(password)) {
            addErrorMessage(passwordErrorMessage);
        }
    }

    function validateLoginForm() {
        validateLoginAndPassword();
    }

    function validateRegistrationForm() {
        validateLoginAndPassword();

        passwordCheck = $("#passwordCheck").val();
        firstName = $("#firstName").val();
        secondName = $("#secondName").val();
        email = $("#email").val();

        if (!(passwordCheck === password)) {
            addErrorMessage(differentPasswordConfirmMessage);
        }

        if (!isCorrectName(firstName)) {
            addErrorMessage(firstNameErrorMessage);
        }

        if (!isCorrectName(secondName)) {
            addErrorMessage(secondNameErrorMessage);
        }

        if (!isCorrectEmail(email)) {
            addErrorMessage(emailErrorMessage);
        }

    }

    var formID = document.getElementsByTagName("form").item(0).getAttribute("id");
    var form = $("#" + formID);


    $('input[type="reset"]').click(function () {
        messageSpan.empty().hide();
    });

    form.submit(function () {
        messageSpan.empty().hide();

        if (formID === "loginForm") {
            validateLoginForm();
        } else if (formID === "registrationForm") {
            validateRegistrationForm();
        }

        if (hasError) {
            messageSpan.show();
        }

        return !hasError;
    });

});
