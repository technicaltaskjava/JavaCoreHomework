var lastIndex = -1;

$(document).ready(function () {
	centered();
	login();
});

$(document).on('click', function () {
	var target = $(event.target);
	if (target.hasClass('get-button')) {
		getCookie();
	}
	if (target.hasClass('login-button')) {
		redirect('/mycookie/view/html/login.html')
	}
	if (target.hasClass('logout-button')) {
		removeAuth();
	}
	if (target.hasClass('admin-button')) {
		redirect('/mycookie/view/html/admin.html')
	}
});

function getCookie() {
	$.get('getcookie', {id: lastIndex}, function (data) {
		$.each(data, function (index, element) {
			if (index === 'id') {
				lastIndex = element;
			}
			if (index === 'cookie') {
				$('.cookie').text(element);
			}
			if (index === 'luckyNumber') {
				var msg = "Your Lucky Number is ";
				$('.lucky').text(msg + element);
			}
		});
	});
}

function login() {
	var login = getAuth(keyAuth);
	var $title = $('.title');
	var $login = $('.login-button');
	var $logout = $('.logout-button');
	var $admin = $('.admin-button');
	if (login) {
		title = $title.text(); //NOSONAR This is global var
		$title.text(title + ' for \'' + getAuth(keyName) + '\'');
		$login.hide();
		$logout.show();
		$admin.show();
	} else {
		$title.text(title);
		$login.show();
		$logout.hide();
		$admin.hide();
	}
}


