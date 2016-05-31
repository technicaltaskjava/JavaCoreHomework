var oldCookieValue = '';
var oldNumberValue = '';
var editMode = false;

$(document).ready(function () {
	centered();
});

$(document).on('click', function (event) {
	var target = $(event.target);
	if (target.hasClass('add-button')) {
		addRow();
	}
	if (target.hasClass('back-button')) {
		redirect(getBaseUrl());
	}
	if (target.hasClass('edit-button')) {
		setEditableRow(target.closest('tr'), true);
	}
	if (target.hasClass('delete-button')) {
		deleteCookie(target.closest('tr'));
	}
	if (target.hasClass('save-button')) {
		saveCookie(target.closest('tr'));
	}
	if (target.hasClass('cancel-button')) {
		cancelEdit(target.closest('tr'));
	}
});

function canEdit() {
	if (editMode) {
		error('Complete line editing to continue');
		return false;
	}
	return true;
}

function cancelEdit(element) {
	if (oldCookieValue === '') {
		element.remove();
	} else {
		element.html(getColumn(oldCookieValue, oldNumberValue, false));
		oldCookieValue = '';
		oldNumberValue = '';
	}
	editMode = false;
	error('');
}

function error(msg) {
	$('.alert').text(msg);
}

function addRow() {
	if (!canEdit()) {
		return;
	}
	var table = $('.table');
	var row = $('<tr class="added"></tr>');
	row.html(getColumn('', '', true));
	table.append(row);
	oldCookieValue = '';
	oldNumberValue = '';
	error('');
	editMode = true;
}

function setEditableRow(element, isEditable) {
	if (!canEdit()) {
		return;
	}
	var rowElement = $(element).children();
	var cookieMsg = rowElement[0].textContent.trim();
	var number = rowElement[1].textContent.trim();
	element.html(getColumn(cookieMsg, number, isEditable));
	if (isEditable) {
		oldCookieValue = cookieMsg;
		oldNumberValue = number;
	} else {
		oldCookieValue = '';
		oldNumberValue = '';
	}
	error('');
	editMode = true;
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

function deleteCookie(element) {
	if (!canEdit()) {
		return;
	}
	var rowElement = $(element).children();
	var cookieMsg = rowElement[0].textContent.trim();
	var data = {cookieMsg: cookieMsg, method: 'delete'};
	$.ajax(
		{
			type: "POST",
			url: "service/cookie",
			data: data,
			success: function () {
				error('');
				reloadPage();
			},
			error: function () {
				error('Cookie not found');
			}
		}
	);
}

function saveCookie(element) {
	var rowElement = $(element).children();
	var columnElement = $(rowElement).children();
	var cookieMsg = columnElement[0].value;
	var number = columnElement[1].value;
	
	if (!validateName(cookieMsg) || !validateInt(number)) {
		error('Cannot save row');
		return;
	}
	
	if (isEmpty(oldCookieValue)) {
		createCookie(cookieMsg, number);
	} else {
		if (oldCookieValue === cookieMsg && oldNumberValue === number) {
			setStaticRow(element, cookieMsg, number);
			return;
		}
		updateCookie(cookieMsg, number);
	}
	
}
function setStaticRow(element, cookieMsg, number) {
	element.html(getColumn(cookieMsg, number, false));
	oldCookieValue = '';
	oldNumberValue = '';
	editMode = false;
	error('');
}

function createCookie(cookieMsg, number) {
	var dataAdd = {cookieMsg: cookieMsg, cookieNum: number, method: 'put'};
	$.ajax(
		{
			type: "POST",
			url: "service/cookie",
			data: dataAdd,
			success: function () {
				reloadPage()
			},
			error: function () {
				error('Cannot save row');
			}
		}
	);
}

function updateCookie(cookieMsg, number) {
	var dataUpdate = {cookieMsg: cookieMsg, cookieNum: number, oldCookie: oldCookieValue, method: 'update'};
	$.ajax(
		{
			type: "POST",
			url: "service/cookie",
			data: dataUpdate,
			success: function () {
				reloadPage();
			},
			error: function () {
				error('Cannot save row');
			}
		}
	);
}

function reloadPage() {
	location.reload();
}


