
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
   
});
