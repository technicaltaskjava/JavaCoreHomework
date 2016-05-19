/**
 * Created by Slithy on 14.05.2016.
 */
var users = [
    {username: 'User1', email: 'zax@cs.d', password: 'qweasd'},
    {username: 'User2', email: 'asd@zx.c', password: '123456'}
];

var newUserData = {
    username: false,
    password: false,
    passwordRepeat: false,
    email: false
};

var usernameRegex = /^[a-zA-Z0-9]+$/;

var passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/;

var emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

function validateUsername() {
    var username = document.getElementById('username').value;
    if (username === ''){
        alert('Username can\'t be empty!');
        return;
    }
    for (i = 0; i < users.length; i++){
        if (username === users[i].username){
            alert('Username already taken.');
            return;
        }
    }
    if (!usernameRegex.test(username)){
        alert('Only alphanumeric characters are allowed!');
        return;
    }
    newUserData.username = username;
}

function validatePassword() {
    var password = document.getElementById('password').value;
    if (password.length < 6 || password.length > 20) {
        alert('Password must be 6 to 20 characters long.');
        return;
    }
    if (!passwordRegex.test(password)){
        alert('Passwords must contain at least one numeric digit, one uppercase and one lowercase letter');
        return;
    }
    newUserData.password = password;
}

function validateRepeatPassword() {
    var passwordRepeat = document.getElementById('password-repeat').value;
    if (passwordRepeat !== newUserData.password){
        alert('Passwords do not match.');
        return;
    }
    newUserData.passwordRepeat = true;
}

function validateEmail() {
    var email = document.getElementById('email').value;
    if (email === '') {
        alert('Email can\'t be empty.');
        return;
    }
    if (!emailRegex.test(email)){
        alert('Invalid email.');
        return;
    }
    newUserData.email = email;
}

function submitRegistration() {
    for (property in newUserData) {
        if (newUserData[property] === false) {
            alert('All fields must be validated to register.');
            return;
        }
    }
    users[users.length] = {username: newUserData.username, password: newUserData.password, email: newUserData.email}
    alert('Registragion complete, you will now be redirected to main page.');
    window.location.href = 'index.html';
}