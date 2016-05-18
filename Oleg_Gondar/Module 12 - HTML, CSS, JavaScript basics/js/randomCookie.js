
$(document).ready(function() {

  var predictions = [
           '11111111111',
           '22222222222',
           '33333333333',
           '44444444444',
           '55555555555',
           '66666666666',
           '77777777777',
           '88888888888',
           '99999999999',
           '10101010101'
       ],
       index = predictions.length - 1,
       randomNumber = -1,
       button = $('#random'),
       randomPrediction = $('#prediction');

   function randomNumberFromRange(min, max) {
       return Math.floor(Math.random() * (max - min + 1) + min);
   }
   var newNumber = randomNumberFromRange(0, index);
        if (newNumber !== randomNumber) {
  document.getElementById("message").innerHTML = predictions[newNumber];
}
});
