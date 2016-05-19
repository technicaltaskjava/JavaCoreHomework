$(function () {
    var form = $('.showCookie'),
        showText = form.find('.cookieText'),
        showButton = form.find('#getCookie');
        
    showButton.on('click', function (e) {
        var cookie = [
            "Examine the situation before you act impulsively.",
            "Grasp the opportunities that come your way.",
            "Your trouble will pass away soon.",
            "Be cautious in your daily affairs.",
            "Accept the next proposition you hear.",
            "Remember yesterday, but live for today.",
            "Demonstrate refinement in everything you do.",
            "Financial prosperity is coming your way!",
            "You are a person of culture, cultivate it.",
            "A cheerful letter or message is on its way to you."
        ];
        var currentCookieNumber = Math.floor(Math.random() * 10 + 1);
        e.preventDefault();
        showText.empty();
        showText.append('Your cookie is:<br><p class="currentCookie" align="center">' + cookie[currentCookieNumber-1] + '</p>');
    });
});
