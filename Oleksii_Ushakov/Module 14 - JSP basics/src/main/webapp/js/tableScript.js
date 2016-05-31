var page = 1;
var editLink = "editPredication";
var addNewLink = "addNewPredication";
var deleteLink = "deletePredication";

$(function () {

    $('#predictionTable').children().last().hide();
    var inputPredicationField = $('.inputPredication');

    $(document).on('click', function (event) {
        var target = $(event.target);

        if (target.hasClass('deleteButton')) {
            deletePredictions();
            reloadPage();

        } else if (target.hasClass('editButton')) {
            editPredication(target);

        } else if (target.hasClass("saveButton")) {
            savePredication(target);

        } else if (target.hasClass("addButton")) {
            $('#predictionTable').children().last().show();
            inputPredicationField.focus();

        } else if (target.hasClass("addPredication")) {
            addPredication(target);
            reloadPage();

        } else if (target.hasClass("cancelButton")) {
            $('#predictionTable').children().last().hide();
            inputPredicationField[0].value = "";

        } else if (target.hasClass("pageNavigation")) {

            if (target.attr("id") === 'nextPage') {
                page++;
            } else {
                page--;
            }

            $.post('/pageView', {pageNumber: page}, function () {

            });

            //todo jquery-2.2.3.min.js:2305 GET http://localhost:8080/pageView 405 (Method Not Allowed)
            $.get('/pageView', function () {

            });
        }
    });

    inputPredicationField.on('keyup', checkPredication);
    inputPredicationField.on('focus', checkPredication);

});

function checkPredication() {
    var predication = this.value;

    if (isCorrectPredication(predication)) {
        this.className = 'inputPredication';
    } else {
        this.className = 'inputPredicationError';
    }
}

function isCorrectPredication(predication) {
    return /^[A-Z][a-z \',-.!?]{9,}$/.test(predication);
}

function deletePredictions() {
    var checkBoxes = document.getElementsByName("deletePredication");
    var tableRows = document.getElementById("predictionTable").getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    var selected = [];

    for (var i = 0; i < checkBoxes.length; i++) {
        if (checkBoxes[i].checked) {
            selected.push(tableRows[i].id);
        }
    }

    // $.post('/deletePredication', {indexArray: selected}, function () {
    $.post(deleteLink, {indexArray: selected}, function () {

    });
}

function editPredication(target) {
    var predicationTh = target.closest('tr').children()[1];
    var predication = predicationTh.innerText;
    predicationTh.innerText = "";

    var input = document.createElement("input");
    input.type = "text";
    input.name = "predInput";
    input.value = predication;
    input.className = "inputPredication";
    input.onkeyup = checkPredication;
    predicationTh.appendChild(input);
    input.focus();

    target.removeClass('editButton');
    target.addClass('saveButton');
    target.val("Save");

    target.removeClass('editButton');
    target.addClass('saveButton');
    target.val("Save");
}

function savePredication(target) {
    var predicationTh = target.closest('tr').children()[1];
    var predication = predicationTh.getElementsByTagName("input")[0].value;

    if (isCorrectPredication(predication)) {
        target.removeClass('saveButton');
        target.addClass('editButton');
        target.val("Edit");

        predicationTh.innerText = predication;
        var id = target.closest('tr').attr('id');

        // $.post('/editPredication', {index: id, value: predication}, function () {
        $.post(editLink, {index: id, value: predication}, function () {

        });

    }
}

function addPredication(target) {
    var predicationTh = target.closest('tr').children()[0];
    var predication = predicationTh.getElementsByTagName("input")[0].value;

    if (isCorrectPredication(predication)) {
        // $.post('/addNewPredication', {newPredication: predication}, function () {
        $.post(addNewLink, {newPredication: predication}, function () {

        });

        $('#predictionTable').children().last().hide();
    }
}

function reloadPage() {
    location.reload();
}