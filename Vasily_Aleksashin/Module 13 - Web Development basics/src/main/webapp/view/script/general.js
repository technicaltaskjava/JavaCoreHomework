var title;
var keyAuth = 'auth';
var keyName = 'name';

function isEmpty(value) {
	return value.length === 0;
}

function validateEmail(input) {
	var format = /^[\w]+[\w\W]*@[\w\W]+(\.\w{2,3})+$/;
	return !isEmpty(input) && format.test(input);
}

function validatePass(input) {
	return !isEmpty(input) && input.length >= 5;
}

function validatePassConf(pass, confirm) {
	if (validatePass(pass)) {
		return pass === confirm;
	}
	return false;
}

function validateLogin(input) {
	var format = /^[a-zA-Z][\w\d]*/;
	return !isEmpty(input) && format.test(input);
}

function validateName(input) {
	var format = /[a-zA-Z]+/;
	return !isEmpty(input) && format.test(input);
}

function validateAge(input) {
	var format = /[1-9][0-9]?/;
	return !isEmpty(input) && format.test(input);
}

function centered() {
	var container = $('.container');
	container.css('top', (window.innerHeight - container.height()) / 2);
	container.css('left', (window.innerWidth - container.width()) / 2);
}

function redirect(location) {
	window.location = location;
}

function crypt(value) {
	return $().crypt({method: "md5", source: value});
}

function getAuth(name) {
	return $.cookie(name);
}

function removeAuth() {
	$.ajax(
		{
			type: "POST",
			url: "userlogout",
			success: function () {
				redirect('/mycookie');
			}
		}
	);
}
