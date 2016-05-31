$(function () {
    var form = $('.SignUpForm'),
        signUpButton = form.find('#signUp'),
        username = form.find('#username'),
        email = form.find('#email'),
        password = form.find('#password'),
        passwordCheck = form.find('#passwordCheck'),
        emailError = form.find('.emailError'),
        logError = form.find('.logError'),
        pwdError = form.find('.pwdError');

    function validateUsername(value) {
        var regexp = /^[a-zA-Z0-9_]+$/;
        return (value && regexp.test(value));
    }

    function validateEmail(value) {
        var regexp = /^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$/;
        return (value && regexp.test(value));
    }

    username.on('blur', function () {
        if (!validateUsername(username.val())) {
            logError.empty();
            logError.append('Login must contains only letters, digits and underscore.');
        }
        else {
            logError.empty();
        }
    });

    password.on('blur', function () {
         if (password.val().length < 1) {
            pwdError.empty();
            pwdError.append('Password must have at least one character.');
         }
         else {
            logError.empty();
         }
    });

    email.on('blur', function () {
        if (!validateEmail(email.val())) {
            emailError.empty();
            emailError.append('Please enter correct email.');
        }
        else {
            emailError.empty();
        }
    });

    signUpButton.on('click', function(e) {
        e.preventDefault();
        emailError.empty();
        logError.empty();
        pwdError.empty();
        var valid = true;
        if (!validateUsername(username.val())) {
            logError.append('Login must contains only letters, digits and underscore.');
            valid = false;
        }
        if (!validateEmail(email.val())) {
            emailError.append('Please enter correct email.');
            valid = false;
        }
        if (password.val().length < 1) {
            pwdError.append('Password must have at least one character.');
            valid = false;
        }
        if (!(password.val() === passwordCheck.val())) {
            pwdError.append('Passwords are not equal.');
            valid = false;
        }
        if (valid) {
            form.submit();
        }
    });
});