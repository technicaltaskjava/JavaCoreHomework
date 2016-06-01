var prediction = [
    ['I wish you luck!'],
    ['Have a good rest!'],
    ['Behave yourself .be good!'],
    ['Success to you in defending your thesis!'],
    ['May your dreams come true!'],
    ['Good luck on your business!'],
    ['Enjoy!!'],
    ['Lets hope the weather keeps!'],
    ['Good luck with your exam!'],
    ['Please, accept my best most heartfelt sincerest wishes!']
];


$(document).ready(function () {
    $('.cookies').click(function () {
        $.getJSON('ControllerCookie', function (data) {
            $.each(data, function (index, element) {
                if (index === "cookie") {
                    $('.message').text(element);
                }
            })
        });
    });
});


$(document).ready(function () {

    for (i = 0; i < prediction.length; i++)
        $('#myTable1').append('<tr><td class="editor">' + prediction[i] + '</td></tr>');

});

$(document).ready(function () {

    var cookie = $.cookie("user");
    var $registr = $('.reg');
    var $login = $('.login');
    var $logout = $('.exit');
    var $table = $('.cookie_table');
    console.log(cookie);
    if (cookie) {

        $login.hide();
        $logout.show();
        $registr.hide();
        $table.show()
    } else {

        $login.show();
        $registr.show();
        $logout.hide();
        $table.hide();

    }
});

