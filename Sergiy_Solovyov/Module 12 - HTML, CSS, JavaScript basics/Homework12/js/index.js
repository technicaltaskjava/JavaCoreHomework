
		 	  
$(function () {
   var arr = [ "Ask a friend to join you on your next journey.",
          "This life is yours. Some of it was given to you; the rest, you made yourself.",
		  "Strength is built upon inner character.",
          "You learn about another person in the process of teaching them something.",
          "Your aims are high, and you are capable of much.",
          "Take the time to be considerate of others.",
          "You are special in a way you will soon begin to understand.",
          "Someone is speaking well of you.",
          "Others will say you are too idealistic.",
          "In order to have great friends, you must first learn to be a great friend."]
		  function getRandomInt(max){
             return Math.floor((Math.random() * max));;
           }
 
document.getElementById("demo").addEventListener("click", myFunction);


  function myFunction() {
             var element = document.getElementById("cookie");
             var index = getRandomInt(10);
             element.innerHTML = arr[index];;
  }
   $("a").click(function(){
      
        $("#cookie").fadeIn(3000);
	
    });
	
	
 
});
