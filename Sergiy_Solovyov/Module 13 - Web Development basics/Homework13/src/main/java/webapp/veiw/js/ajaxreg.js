
$(document).ready(function()
{

    $('#slick-login').submit(function(event) {
        event.preventDefault();
        var msg   = $('#slick-login').serialize();
        $.ajax({

            type: 'POST',
            url: '/registration',
            data: msg,
            success: function(response, status, xhr){

                $('#slick-login').fadeOut('fast', function(){
                    $('#regsuccess').html('Dear ' +
                        xhr.getResponseHeader('nick')+' registration is successful, now you should log in').fadeIn(3000
                    );
                });
                var host = window.location.origin;
                setTimeout(function(){
                    $(window).attr('location',host + '/login');
                }, 4000);
            }

        });
    });
    var user = $.cookie('user');
    if (user !== undefined){
        $( "#logo" ).after( "<h4 id='session'>You logged as <span>"
            + user +
            "</span> <a href='../admin'>admin panel</a>  <a href='../logout'>logout</a></h4>" );
    }
});
