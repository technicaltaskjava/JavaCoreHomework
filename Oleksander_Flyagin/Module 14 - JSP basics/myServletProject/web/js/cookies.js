$(function () {

    var form = $('form');
    var massege = form.find('.message');
    var change = form.find('.change');
    var addPrediction = $('#commands');

    var lines = [
        "Tell them what you really think. Otherwise, nothing will change.",
        "A package of value will arrive soon.",
        "A loved one is of utmost importance at this time.",
        "Look to your inner being for guidance.",
        "Good things are on their way.",
        "A warm smile is testimony of a generous nature.",
        "An unexpected relationship will become permanent."
    ];

    var TableTitle = ["id", "message"];
    var TableValue = [];

    function addMassege() {
        for (var i = 0; i < lines.length; i++) {
            var id = TableValue.length;
            TableValue[id] = [id + 1, [lines[i]]];
        }
    }

    addMassege();

    var mytable = $('<table/>', {
        class: 'mytable'
    }).append(
        $('<thead/>'),
        $('<tfoot/>'),
        $('<tbody/>')
    );

    var TitleCell = $('<tr/>');
    $.each(TableTitle, function (myIndex, myData) {
        TitleCell.append(
            $('<th/>', {
                text: myData
            })
        );
    });
    $("thead", mytable).append(TitleCell);

    $.each(TableValue, function () {
        var DataCell = $('<tr/>');
        $.each(this, function () {
            DataCell.append(
                $('<td/>', {
                    text: this
                })
            );
        });
        $("tbody", mytable).append(DataCell);
    });


    $('.content').append(mytable);

    function getRandomInt(min, max) {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }

    $("#show").click(function (event) {
        massege.empty();
        var id = getRandomInt(0, lines.length - 1);
        massege.append(
            '<p class="getMasseg">' + lines[id] + '</p>');
        event.preventDefault();

    });

    var id = TableValue.length + 1;
    $("#addPre").click(function (event) {
        addPrediction.empty();
        event.preventDefault();
        addPrediction.append('<textarea id="second" type="text"></textarea><br><button id="bt2">ENTER TEXT</button><div id="addEl"></div>');
        var addEl = $("#addEl");
        $('#bt2').click(function (event) {
            event.preventDefault();
            var newPrediction = $("#second")[0];
            $("tbody", mytable).append('<tr><td>' + (id++) + '</td> <td>' + newPrediction.value + '</td> </tr> ');
            lines[lines.length] = newPrediction.value;
            addPrediction.empty();
        });
    });

    $("#delPre").click(function (event) {
        addPrediction.empty();
        addPrediction.append('<input id="trees" type="text" placeholder=" ID "><br><button id="bt3">DELL \'ID\'</button><div id="dellEl"></div>');
        var dellEl = $("#dellEl");
        event.preventDefault();
        $('#bt3').click(function (event) {
            event.preventDefault();
            var id = $("#trees")[0].value;
            var regexp = /^[0-9]/;
            var serchRes = true;
            if (regexp.test(id)) {
                for (var i = 0; i < mytable.length; i++) {
                    for (var r = 0; r < mytable[i].rows.length; r++) {
                        if (id === mytable[i].rows[r].cells[0].innerHTML) {
                            mytable[i].deleteRow(r);
                            serchRes = false;
                            addPrediction.empty();
                        }
                    }
                }
                if (serchRes) {
                    addPrediction.empty();
                    addPrediction.append('<p class="errors">ID NOT FOUND<br>Please try again</p>');
                }
            }
            else {
                addPrediction.empty();
                addPrediction.append('<p class="errors">ID NOT CORRECT<br>Please try again</p>');
            }
        });
    });

    $("#changePre").click(function (event) {
        addPrediction.empty();
        addPrediction.append('<input id="forth" type="text" placeholder=" ID "><br><button id="bt4">ENTER \'ID\'</button><div id="changeEl"></div>');
        event.preventDefault();
        $('#bt4').click(function (event) {
            event.preventDefault();
            var id = $("#forth")[0].value;
            var regexp = /^[0-9]/;
            var serchRes = true;
            if (regexp.test(id)) {
                for (var i = 0; i < mytable.length; i++) {
                    for (var r = 0; r < mytable[i].rows.length; r++) {
                        if (id === mytable[i].rows[r].cells[0].innerHTML) {
                            serchRes = false;
                        }
                    }
                }
                if (serchRes) {
                    addPrediction.empty();
                    addPrediction.append('<p class="errors">ID NOT FOUND<br>Please try again</p>');
                }
                else {
                    addPrediction.empty();
                    addPrediction.append('<textarea id="fifth" type="text"></textarea><br><button id="bt5">ENTER TEXT</button>');
                    $('#bt5').click(function (event) {
                        event.preventDefault();
                        var text = $("#fifth")[0].value;
                        for (var i = 0; i < mytable.length; i++) {
                            for (var r = 0; r < mytable[i].rows.length; r++) {
                                if (id === mytable[i].rows[r].cells[0].innerHTML) {
                                    for (var start = 0; start < lines.length; start++) {
                                        if (lines[start] === mytable[i].rows[r].cells[1].innerHTML) {
                                            lines[start] = text;
                                        }
                                    }
                                    mytable[i].rows[r].cells[1].innerHTML = text;
                                    addPrediction.empty();
                                }
                            }
                        }
                    });
                }
            }
            else {
                addPrediction.empty();
                addPrediction.append('<p class="errors">ID NOT CORRECT<br>Please try again</p>');
            }
        });
    });
});
