$(document).ready(function()
{
    $('.remove').click(function(event) {
        event.preventDefault();
        var href   = $(this).attr('href');
        $.ajax({

            type: 'delete',
            url: href,
            success: function(response, status, xhr){
                location.reload();
            }
        });
    });
    $('.editable').click(function(event) {
        event.preventDefault();
        var href   = $(this).attr('href');
        var stringLength = href.length;
        var arr = href.split('/');
        var idd = "#"+arr[3];
        var text = $(idd).text();
        $('#cook').val(text);
        $('#edit-cookie').attr('action', href).css('visibility', 'visible');
    });

    $('#edit-cookie').submit(function(event) {
        event.preventDefault();
        var text =  $('#cook').val();
        var  url = $('#edit-cookie').attr('action');
        var msg   = $('#edit-cookie').serialize();
        $.ajax({
            type: 'PUT',
            headers: {
                'cook':text
            },
            url: url,
            data: msg,
            success: function(response, status, xhr){
                location.reload();
            }

        });
    });
});
