var oldCookieValue = '';
var editMode = false;

$(document).ready(function () {
	centered();
	showCookies();
});

$(document).on('click', function (event) {
	var target = $(event.target);
	if (target.hasClass('delete-button')) {
		if (editMode) {
			error("Complete line editing to continue")
		} else {
			if (deleteCookie(target.closest('tr'))) {
				target.closest('tr').remove();
				error('');
				eraseListCookie();
				showCookies();
			} else {
				error('Cookie not found');
			}
		}
	}
	if (target.hasClass('edit-button')) {
		if (editMode) {
			error("Complete line editing to continue")
		} else {
			error('');
			editMode = true;
			editCookie(target.closest('tr'));
		}
	}
	if (target.hasClass('cancel-button')) {
		editMode = false;
		target.closest('tr').remove();
		oldCookieValue = '';
		error('');
		eraseListCookie();
		showCookies();
	}
	if (target.hasClass('add-button')) {
		if (editMode) {
			error("Complete line editing to continue")
		} else {
			error('');
			editMode = true;
			addRow();
		}
	}
	if (target.hasClass('back-button')) {
		redirect('../index.html');
	}
	if (target.hasClass('save-button')) {
		if (saveCookie(target.closest('tr'))) {
			eraseListCookie();
			showCookies();
		} else {
			error('Cannot save row');
		}
	}
});

function error(msg) {
	$('.alert').text(msg);
}

function isAuth() {
	var login = getAuth(keyAuth);
	if (!login) {
		$(location).attr('href', '../index.html');
	}
}

function showCookies() {
	var table = $('.table');
	var cookieStore = getData(cookie);
	$.each(cookieStore, function (index, value) {
		table.append('<tr class="added">' + getColumn(value[0], value[1], false) +
			'</tr>');
	});
}

function addRow() {
	var table = $('.table');
	var row = $('<tr class="added"></tr>');
	row.html(getColumn('', '', true));
	table.append(row);
	oldCookieValue = '';
}

function editCookie(element) {
	var rowElement = $(element).children();
	var cookieMsg = rowElement[0].textContent;
	var number = rowElement[1].textContent;
	element.html(getColumn(cookieMsg, number, true));
	oldCookieValue = cookieMsg;
}

function getColumn(msg, num, isInput) {
	if (isInput) {
		return '<td class="text cookie-msg"><input class="input-script" type="text" value="' + msg + '" placeholder="Cookie message" ></td>' +
			'<td class="text"><input style="width: 80px" class="input-script" type="text" value="' + num + '" placeholder="Lucky number" ></td>' +
			'<td ><button class="save-button">Save</button></td>' +
			'<td ><button class="cancel-button">Cancel</button></td>';
	} else {
		return '<td class="text">' + msg + '</td>' +
			'<td class="text">' + num + '</td>' +
			'<td class="edit"><button class="edit-button">Edit</button></td>' +
			'<td class="del"><button class="delete-button">Delete</button></td>'
	}
}


function eraseListCookie() {
	$('.added').remove();
}

function deleteCookie(element) {
	var rowElement = $(element).children();
	var cookieMsg = rowElement[0].textContent;
	var cookieStore = getData(cookie);
	for (var index = 0; index < cookieStore.length; index++) {
		if (cookieStore[index][0] === cookieMsg) {
			cookieStore.splice(index, index + 1);
			setData(cookie, cookieStore);
			return true;
		}
	}
	return false;
}

function saveCookie(element) {
	var rowElement = $(element).children();
	var columnElement = $(rowElement).children();
	
	var cookieMsg = columnElement[0].value;
	var number = columnElement[1].value;
	
	if (!validateName(cookieMsg) || !validateInt(number)) {
		return false;
	}
	var cookieStore = getData(cookie);
	if (isEmpty(oldCookieValue)) {
		cookieStore.push([cookieMsg, number]);
	} else {
		var findIndex = findCookie(oldCookieValue);
		if (findIndex === -1) {
			return false;
		}
		cookieStore[findIndex][0] = cookieMsg;
		cookieStore[findIndex][1] = number;
		oldCookieValue = '';
	}
	setData(cookie, cookieStore);
	editMode = false;
	return true;
}

function findCookie(value) {
	var cookieStore = getData(cookie);
	for (var index = 0; index < cookieStore.length; index++) {
		if (cookieStore[index][0] === value) {
			return index;
		}
	}
	return -1;
}
