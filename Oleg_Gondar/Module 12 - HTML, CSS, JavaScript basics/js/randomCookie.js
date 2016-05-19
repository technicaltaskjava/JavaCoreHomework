$(document).ready(function () {

    var cookies = [
            'Your trouble will pass away soon.',
            'Beauty will surround you - open your eyes to see it.',
            'Grasp the opportunities that come your way.',
            'Love comes quickly, whatever you do.',
            'Jealousy is unattractive - show some other emotion.',
            'Tomorrow, take a moment to do something just for yourself.',
            'An idea is not responsible for the people who believe in it.',
            'You are special in a way you will soon begin to understand.',
            'A loved one is of utmost importance at this time.',
            'Do not be hasty, prosperity will knock on your door soon.'
        ],
        index = cookies.length - 1,
        randomNumber = -1;

    function randomNumberFromRange(min, max) {
        return Math.floor(Math.random() * (max - min + 1) + min);
    }


    var newNumber = randomNumberFromRange(0, index);
    if (newNumber !== randomNumber) {
        
        document.getElementById("message").innerHTML = cookies[newNumber];
	   $('#message').popup('show');
    }

});
