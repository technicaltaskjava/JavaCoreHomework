


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




