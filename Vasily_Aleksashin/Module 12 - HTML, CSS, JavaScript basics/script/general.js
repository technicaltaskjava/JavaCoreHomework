var storage = $.sessionStorage;
var authCookie = 'isAuth';
var title;
var cookie = 'cookie';
var users = 'users';
var keyAuth = 'auth';
var keyName = 'name';
var initCookie = [
	["Life is too short to hold grudges", 13],
	["Make a wish, it might come true", 7],
	["Look to your inner being for guidance", 666],
	["A frivolous gift is a gift, nonetheless", 69],
	["You will have long and healthy life", 777],
	["You learn about another person in the process of teaching them something", 21],
	["Dream your dream and your dream will dream of you", 999],
	["Relish the transitions in your life - they will happen anyway", 12],
	["The time is right to make new friends", 15],
	["You are filled with a sense of urgency. Be patient or you may end up confused", 100]
];
var initUsers = [
	{
		userName: 'Admin',
		email: 'admin@mail.com',
		password: 'ff9830c42660c1dd1942844f8069b74a',
		firstName: 'Admin',
		lastName: 'Admin',
		age: 1
	}
];

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

function validateInt(input) {
	var format = /[1-9][0-9]*/;
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

function isStorage() {
	return storage.isSet(cookie) && storage.isSet(users);
}

function initDataStorage() {
	if (!isStorage()) {
		setData(cookie, initCookie);
		setData(users, initUsers);
	}
}

function getData(key) {
	return storage.isSet(key) ? storage.get(key) : null;
}

function setData(key, value) {
	storage.set(key, value);
}

function crypt(value) {
	return $().crypt({method: "md5", source: value});
}

function isAuthCookie() {
	var testCookie = 'test';
	$.cookie(testCookie, testCookie);
	if ($.cookie(testCookie) !== testCookie) {
		storage.set(authCookie, 'false');
	} else {
		storage.set(authCookie, 'true');
		$.removeCookie(testCookie);
	}
}

function getAuth(name) {
	return storage.get(authCookie) === 'true' ? $.cookie(name) : storage.get(name);
}

function setAuth(user) {
	if (storage.get(authCookie) === 'true') {
		$.cookie(keyAuth, 'true', {expires: 1, path: '/'});
		$.cookie(keyName, user, {expires: 1, path: '/'});
	} else {
		storage.set(keyAuth, 'true');
		storage.set(keyName, user);
	}
}

function removeAuth() {
	if (storage.get(authCookie) === 'true') {
		$.removeCookie(keyAuth, {expires: 1, path: '/'});
		$.removeCookie(keyName, {expires: 1, path: '/'});
	} else {
		storage.remove(keyAuth);
		storage.remove(keyName);
	}
}