$(document).ready(function () {
	centered();
});

$(document).on('click', function (event) {
	var target = $(event.target);
	if (target.hasClass('add-button')) {
		alert("Not implements in current homework"); //NOSONAR this is alert for not supported operation in this homework
	}
	if (target.hasClass('back-button')) {
		redirect('/mycookie');
	}
});

function error(msg) {
	$('.alert').text(msg);
}

function isAuth() {
	var login = getAuth(keyAuth);
	if (!login) {
		$(location).attr('href', '/mycookie');
	}
}