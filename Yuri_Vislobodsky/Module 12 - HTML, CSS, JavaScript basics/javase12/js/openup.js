$(function () {
    $('.cookie-text').hide();
    $('button[type="submit"]').on('click', onSubmit);
});

$(window).keydown(function (e) {
    if (e.keyCode === 13) {
        $('button[type="submit"]').click();
    }
});

function onSubmit(e) {
    e.preventDefault();
    $('.caption').hide();
    $('.cookie-text').show();
    $(this).empty().append('Open another cookie');
}