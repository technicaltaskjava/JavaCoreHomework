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
		redirect(getBaseUrl());
	}
});

function inputValidation() {
	var inputLogin = $('.input-login').val();
	var inputPass = $('.input-pass').val();
	if (validateLogin(inputLogin) && validatePass(inputPass)) {
		$('.warning').css('color', '#fff');
		var cryptPass = crypt(inputPass);
		verifyUser(inputLogin, cryptPass)
	} else {
		validateFail();
	}
}

function verifyUser(name, pass) {
	var dataAuth = {username: name, pass: pass};
	$.ajax(
		{
			type: "POST",
			url: "login",
			data: dataAuth,
			success: function () {
				redirect(getBaseUrl());
			},
			error: function (jqXhr) {
				if (jqXhr !== undefined) {
					validateFail();
				}
			}
		}
	);
}

function validateFail() {
	$('.warning').css('color', '#f00');
}

