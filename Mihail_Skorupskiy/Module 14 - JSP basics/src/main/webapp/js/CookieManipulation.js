/**
 * Created by Slithy on 16.05.2016.
 */

window.onload = function() {
    refresh();
};

function showAddCookie() {
    document.getElementById('cookie-add-form').style.display = 'block';
    document.getElementById('cookie-delete-form').style.display = 'none';
    document.getElementById('cookie-edit-form').style.display = 'none';
}

function showDeleteCookie() {
    document.getElementById('cookie-delete-form').style.display = 'block';
    document.getElementById('cookie-add-form').style.display = 'none';
    document.getElementById('cookie-edit-form').style.display = 'none';
}

function showEditCookie() {
    document.getElementById('cookie-edit-form').style.display = 'block';
    document.getElementById('cookie-delete-form').style.display = 'none';
    document.getElementById('cookie-add-form').style.display = 'none';
}

function refresh() {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'getcookietable', false);
    xhr.send();
}
