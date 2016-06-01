/**
 * Created by Slithy on 14.05.2016.
 */
var validations = {
    username: false,
    password: false,
    passwordRepeat: false,
    email: false};

var usernameRegex = /^[a-zA-Z0-9].{5,}$/;

var passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/;

var emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

function validateUsername() {
    var username = document.getElementById('username').value;
    if (username === ''){
        document.getElementById('username-error').innerText = 'Username can\'t be empty.';
        validations.username = false;
        return;
    }
    if (!usernameRegex.test(username)){
        document.getElementById('username-error').innerText = 'Username must be at least 6 characters long, only alphanumeric characters are allowed.';
        validations.username = false;
        return;
    }
    document.getElementById('username-error').innerText = '';
    validations.username = true;
    checkRegistrationAvailability()
}

function validatePassword() {
    var password = document.getElementById('password').value;
    if (password.length < 6 || password.length > 20) {
        document.getElementById('password-error').innerText = 'Password must be 6 to 20 characters long.';
        validations.password = false;
        return;
    }
    if (!passwordRegex.test(password)){
        document.getElementById('password-error').innerText = 'Passwords must contain at least one numeric digit, one uppercase and one lowercase letter';
        validations.password = false;
        return;
    }
    document.getElementById('password-error').innerText = '';
    validations.password = true;
    checkRegistrationAvailability()
}

function validateRepeatPassword() {
    var password = document.getElementById('password').value;
    var passwordRepeat = document.getElementById('password-repeat').value;
    if (passwordRepeat !== password){
        document.getElementById('password-repeat-error').innerText = 'Passwords do not match.';
        validations.passwordRepeat = false;
        return;
    }
    document.getElementById('password-repeat-error').innerText = '';
    validations.passwordRepeat = true;
    checkRegistrationAvailability()
}

function validateEmail() {
    var email = document.getElementById('email').value;
    if (email === '') {
        document.getElementById('email-error').innerText = 'Email can\'t be empty.';
        validations.email = false;
        return;
    }
    if (!emailRegex.test(email)){
        document.getElementById('email-error').innerText = 'Invalid email.';
        validations.email = false;
        return;
    }
    document.getElementById('email-error').innerText = '';
    validations.email = true;
    checkRegistrationAvailability()
}

function checkRegistrationAvailability() {
    for (property in validations) {
        if (validations[property] === false) {
            document.getElementById('register-button').disabled = true;
            return;
        }
    }
    document.getElementById('register-button').disabled = false;
}