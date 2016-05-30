$(document).ready(function()
{
    var host = window.location.origin;
    setTimeout(function(){
        $(window).attr('location',host + '/index');
    }, 1500);
});
