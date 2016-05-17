$(function () {
    var form = $('.test'),
        submitButton = form.find('button[type="submit"]'),

        message = form.find('.message');
    var messages = [
        'Life is too short to hold grudges.',
        'An unexpected aquaintance will resurface.',
        'Romance follows you if you can only see it.',
        'Next time you have the opportunity, go on a rollercoaster.',
        'Every solution breeds new problems.',
        'Expand your horizons.',
        'Look to your inner being for guidance.',
        'Keep your eyes open. You never know what you might see.',
        'Demonstrate refinement in everything you do.',
        'Your talents will be recognized and suitably rewarded.'
    ];

    function randomInteger(min, max) {
        var rand = min + Math.random() * (max - min);
        rand = Math.round(rand);
        return rand;
    }

    submitButton.on('click', function (e) {
        e.preventDefault();
        message.empty();
        message.append('<p>' + messages[randomInteger(0, 9)] + '</p>')
    });
});
