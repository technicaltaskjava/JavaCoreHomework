$(document).ready(function()
{
    var user = $.cookie('user');
    if (user !== undefined){
        $( "#logo" ).after( "<h4 id='session'>You logged as <span>"
            + user +
            "</span> <a href='../admin'>admin panel</a>  <a href='../logout'>logout</a></h4>" );
    }
    
    $('#slick-login').submit(function(event) {
        event.preventDefault();
        var msg   = $('#slick-login').serialize();
        $.ajax({

            type: 'POST',
            url: '/login',
            data: msg,
            success: function(response, status, xhr){
                var fail = xhr.getResponseHeader('fail');

                if (fail === 'noUser'){
                    $('.errors').append('<div>Email '+ xhr.getResponseHeader('email') + ' not found</div>');
                }

                else if (fail === 'failPassword') {
                    $('.errors').append('<div>Incorrect password</div>');
                }
                else{
                    var host = window.location.origin;
                    $(window).attr('location',host + '/index');
                }
            }

        });
    });
});