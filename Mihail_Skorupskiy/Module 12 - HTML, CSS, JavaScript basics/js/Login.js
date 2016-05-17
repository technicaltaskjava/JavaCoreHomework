/**
 * Created by Slithy on 14.05.2016.
 */
var users = [
    {username: 'User1', email: 'zax@cs.d', password: 'qweasd'},
    {username: 'User2', email: 'asd@zx.c', password: '123456'}
];

function validate() {
    if (!validateLogin(document.getElementById('username').value)){
        alert('Invalid login');
        return;
    }
    if (!validatePassword(document.getElementById('password').value)){
        alert('Invalid password');
        return;
    }
    alert('Login successful, you will now be redirected to control panel.');
    window.location.href = 'cookies.html';
}

function validateLogin(login) {
    var valid = false;
    for(var i = 0; i < users.length; i++){
        if (login === users[i].username){
            valid = true;
            break;
        }
    }
    return valid;
}

function validatePassword(password) {
    var valid = false;
    for(var i = 0; i < users.length; i++){
        if (password === users[i].password){
            valid = true;
            break;
        }
    }
    return valid;
}