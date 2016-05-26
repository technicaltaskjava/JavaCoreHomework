/**
 * Created by Slithy on 16.05.2016.
 */

var cookies = [
    {id: 1, message: 'Something-something 1'},
    {id: 2, message: 'Something-something 2'},
    {id: 3, message: 'Something-something 3'}
];
var currentCookie;

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

function addCookie() {
    var message = document.getElementById('cookie-add-message').value;
    cookies[cookies.length] = {id: cookies.length+1, message: message};
    alert('Cookie added.');
    refresh();
}

function findCookie() {
    var id = document.getElementById('cookie-find-id').value;
    for (i = 0; i < cookies.length; i++) {
        if (cookies[i].id == id){
            currentCookie = i;
            document.getElementById('cookie-edit').style.display = 'block';
            document.getElementById('cookie-find').style.display = 'none';
            document.getElementById('cookie-edit-message').value = cookies[currentCookie].message;
            return;
        }
    }
    alert('Cookie not found.');
}

function editCookie() {
    cookies[currentCookie].message = document.getElementById('cookie-edit-message').value;
    document.getElementById('cookie-find').style.display = 'block';
    document.getElementById('cookie-edit').style.display = 'none';
    alert('Cookie edited.');
    refresh();
}

function deleteCookie() {
    var id = document.getElementById('cookie-delete-id').value;
    for (i = 0; i < cookies.length; i++) {
        if (cookies[i].id == id){
            cookies.splice(i, 1);
            alert('Cookie deleted.');
            refresh();
            return;
        }
    }
    alert('Cookie not found.');
}

function refresh() {
    cookies.sort(function (first, second) {
       return first.id - second.id;
    });
    var table = document.getElementById('cookie-table');
    table.innerHTML = '<tr class="output"><th>ID</th><th>Message</th></tr>';
    for (i = 0; i < cookies.length; i++){
        if (cookies[i]) {
            var row = document.createElement('tr');
            row.className = 'output';
            for (property in cookies[i]) {
                var field = document.createElement('td');
                field.innerText = cookies[i][property];
                row.appendChild(field);
            }
            table.appendChild(row);
        }
    }
}
