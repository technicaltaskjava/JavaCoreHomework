/**
 * Created by Olga Kramska on 15-May-16.
 */
$(function () {
    var predictions = [
            'you will get mark A',
            'you will catch you dream',
            'you can do it',
            'keep it up',
            'things are looking up',
            'everything will be alright',
            'everything is possible',
            'appreciate what you have',
            'you are intelligent',
            'Santa exists'
        ],
        index = predictions.length - 1,
        randomNumber = -1,
        button = $('#random'),
        randomPrediction = $('#random-prediction');

    function randomNumberFromRange(min, max) {
        return Math.floor(Math.random() * (max - min + 1) + min);
    }

    button.click(function () {

        var newNumber = randomNumberFromRange(0, index);
        if (newNumber !== randomNumber) {
            randomPrediction.empty();
            randomPrediction.append(predictions[newNumber]);
            randomNumber = newNumber
        }
    });
});