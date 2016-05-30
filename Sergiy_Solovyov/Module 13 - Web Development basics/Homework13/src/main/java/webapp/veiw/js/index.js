


function madeAjaxCall(){
    $('#cookie').fadeTo("slow",0 , function() {
        $.ajax({
            type: "head",
            url: "/index",
            cache: false,
            success: function(response, status, xhr){
                $('#cookie').html(xhr.getResponseHeader('cookie')).fadeTo('slow', 1);;

            }
        });
    });
}

$(document).ready(function()
{
    var user = $.cookie('user');
    if (user !== undefined){
        $( "#logo" ).after( "<h4 id='session'>You logged as <span>"
        + user +
        "</span> <a href='../admin'>admin panel</a>  <a href='../logout'>logout</a></h4>" );
    }

});



