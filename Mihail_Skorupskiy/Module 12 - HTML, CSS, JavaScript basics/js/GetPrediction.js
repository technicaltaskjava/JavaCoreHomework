/**
 * Created by Slithy on 15.05.2016.
 */
var messages = [
    "Today it's up to you to create the peacefulness you long for.",
    "A friend asks only for your time not your money.",
    "If you refuse to accept anything but the best, you very often get it.",
    "Your high-minded principles spell success.",
    "Hard work pays off in the future, laziness pays off now.",
    "Change can hurt, but it leads a path to something better.",
    "A chance meeting opens new doors to success and friendship.",
    "You cannot love life until you live the life you love.",
    "Meeting adversity well is the source of your strength.",
    "Our deeds determine us, as much as we determine our deeds."
];

function getNewMessage() {
    document.getElementById('prediction-text').innerHTML = messages[getRandomInt(0, messages.length - 1)];
}

function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}