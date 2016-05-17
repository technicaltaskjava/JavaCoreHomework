$(document).ready(function () {
    function clearTable() {
        var rows = $("#predictionTable").children()[0].getElementsByTagName("tr");
        for (var i = rows.length - 1; i >= 0; i--) {
            rows[i].remove();
        }
    }

    function addTableRow(predication) {
        if (!predication)
            predication = "";

        $("#predictionTable").append('<tr><th>' + predication + '</th><th><input type="button" value="Edit" class="editButton"></th><th><input type="button" value="Delete" class="deleteButton"></th></tr>');
    }

    function renderTable() {
        for (var i = 0; i < cookies.length; i++) {
            addTableRow(cookies[i].cookie);
        }
    }

    clearTable();
    renderTable();

    $("#addNewPredication").click(function (event) {
        clearTable();
        renderTable();
        addTableRow();

        var table = $('#predictionTable');
        var addedRow = table[0].lastElementChild.lastElementChild;
        // var addedRow = table.;
        // var editButton = addedRow.getElementsByClassName('editButton');
        var editButton = $('.editButton').last();

        // console.log(editButton.firstElementChild);
        // console.log(editButton.lastElementChild);
        console.log(addedRow);
        console.log(editButton);
        // console.log(editButton.lastChild);
        // console.log(editButton.lastIndex);
        // console.log(editButton.lastIndexOf());

        editPredicationInTh(addedRow.firstElementChild, editButton);

    });

    function rowIndex(row) {
        var rows = $("#predictionTable").children()[0].getElementsByTagName("tr");

        for (var i = 0; i < rows.length; i++) {
            if (rows[i] === row[0]) {
                return i;
            }
        }

        return -1;
    }

    function editPredicationInTh(th, target) {
        var predicationTh = th;
        var predication = predicationTh.innerText;
        predicationTh.innerText = "";

        var input = document.createElement("input");
        input.type = "text";
        input.value = predication;
        input.className = "editInput";
        predicationTh.appendChild(input);

        target.removeClass('editButton');
        target.addClass('saveButton');
        target.val("Save");
        predicationTh.appendChild(input);

        target.removeClass('editButton');
        target.addClass('saveButton');
        target.val("Save");

    }

    $(document).on('click', function (event) {
        var target = $(event.target);
        var row = target.closest('tr');
        var predicationTh = row.children()[0];
        var predication = "";

        if (target.hasClass('deleteButton')) {
            deleteCookieByIndex(rowIndex(row));
            row.remove();
        } else if (target.hasClass('editButton')) {
            editPredicationInTh(row.children()[0], target);
        } else if (target.hasClass("saveButton")) {

            target.removeClass('saveButton');
            target.addClass('editButton');
            target.val("Edit");

            predication = predicationTh.getElementsByTagName("input")[0].value;
            predicationTh.innerText = predication;
            setPredicationToCookie(rowIndex(row), predication);
        }
    });

});
