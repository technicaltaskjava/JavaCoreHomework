var prediction=[
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
	$('.cookie').click(function () {
		var index = Math.floor(Math.random()*prediction.length);
		$('.message' ).text(prediction[index]);
	});
});

$(document).ready(function() {

    for(i=0;i < prediction.length;i++)
        $('#myTable1').append('<tr><td class="editor">' + prediction[i] + '</td></tr>');

	});


	