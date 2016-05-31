/**
 * Created by Olga Kramska on 15-May-16.
 */
$(function () {

    $('#random').click(function () {

        $.get("random")
            .done(function (data) {
                $('#random-prediction').html(data);
            })
            .fail(function (jqXHR, textStatus, error) {
                console.error(error + ": " + jqXHR.status);
            });
    });
});