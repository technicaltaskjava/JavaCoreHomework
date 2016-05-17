$(document).ready(function () {
	centered();
});

$(document).on('click', function (event) {
	var target = $(event.target);
	if (target.hasClass('login-button')) {
		inputValidation();
	}
	if (target.hasClass('reg-button')) {
		redirect('registration.html');
	}
	if (target.hasClass('back-button')) {
		redirect('../index.html');
	}
});

function inputValidation() {
	var inputLogin = $('.input-login').val();
	var inputPass = $('.input-pass').val();
	if (validateLogin(inputLogin) && validatePass(inputPass)) {
		$('.warning').css('color', '#fff');
		var cryptPass = crypt(inputPass);
		if (verifyUser(inputLogin, cryptPass)) {
			redirect('../index.html');
		} else {
			validateFail();
		}
	} else {
		validateFail();
	}
}

function verifyUser(name, pass) {
	var usersStorage = getData(users);
	for (var index = 0; index < usersStorage.length; index++) {
		var user = usersStorage[index];
		if (name === user.userName && pass === user.password) {
			setAuth(name);
			return true;
		}
	}
	return false;
}

function validateFail() {
	$('.warning').css('color', '#f00');
	removeAuth();
}

