$(document).ready(function () {
	initDataStorage();
	isAuthCookie();
	login();
	centered();
});

$(document).on('click', function () {
	var target = $(event.target);
	if (target.hasClass('get-button')) {
		var cookieStore = getData(cookie);
		var index = Math.floor(Math.random() * cookieStore.length);
		$('.cookie').text(cookieStore[index][0]);
		var msg = "Your Lucky Number is ";
		$('.lucky').text(msg + cookieStore[index][1]);
	}
	if (target.hasClass('login-button')) {
		redirect('html/login.html')
	}
	if (target.hasClass('logout-button')) {
		removeAuth();
		login();
	}
	if (target.hasClass('admin-button')) {
		redirect('html/admin.html')
	}
});

function login() {
	var login = getAuth(keyAuth);
	var $title = $('.title');
	var $login = $('.login-button');
	var $logout = $('.logout-button');
	var $admin = $('.admin-button');
	if (login) {
		title = $title.text();
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


