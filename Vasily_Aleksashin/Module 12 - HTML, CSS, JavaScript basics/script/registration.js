$(document).ready(function () {
	centered();
});

$(document).on('click', function (event) {
	var target = $(event.target);
	if (target.hasClass('logon-button')) {
		inputValidation();
	}
	if (target.hasClass('back-button')) {
		redirect('login.html');
	}
});

function inputValidation() {
	$('.alert').css('color', '#fff');
	var userName = $('.input-username').val();
	var email = $('.input-email').val();
	var password = $('.input-password').val();
	var confirm = $('.input-passconf').val();
	var firstName = $('.input-firstname').val();
	var lastName = $('.input-lastname').val();
	var age = $('.input-age').val();
	var warnings = [
		$('.username'),
		$('.mail'),
		$('.pass'),
		$('.pass-confirm'),
		$('.firstname'),
		$('.lastname'),
		$('.age')
	];
	var validators = [
		validateLogin(userName),
		validateEmail(email),
		validatePass(password),
		validatePassConf(password, confirm),
		validateName(firstName),
		validateName(lastName),
		validateAge(age)
	];
	var index = 0;
	var valid = true;
	while (index < validators.length) {
		if (validators[index]) {
			warnings[index].css('color', '#fff');
		} else {
			warnings[index].css('color', '#f00');
			valid = false;
		}
		index++;
	}
	if (valid) {
		if (addUser(userName, email, password, firstName, lastName, age)) {
			setAuth(userName);
			redirect('../index.html');
		}
	} else {
		removeAuth();
	}
}

function addUser(userName, email, password, firstName, lastName, age) {
	var cryptPass = crypt(password);
	var newUser = {
		userName: userName,
		email: email,
		password: cryptPass,
		firstName: firstName,
		lastName: lastName,
		age: age
	};
	var usersStore = getData(users);
	for (var index = 0; index < usersStore.length; index++) {
		var user = usersStore[index];
		if (userName === user.userName || email === user.email) {
			$('.alert').css('color', '#f00');
			return false;
		} else {
			$('.alert').css('color', '#fff');
		}
	}
	usersStore.push(newUser);
	setData(users, usersStore);
	return true;
}