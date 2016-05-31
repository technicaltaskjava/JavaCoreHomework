$(function () {
    $(':checkbox').on('click', function (event) {
            $(this).parents('tr').toggleClass("error");

        }
    );

    $("table").on('dblclick', ".field", function () {
        var id = $(".number", this);

        var var2 = $(this).text();
        $(this).html('<input name="change_cookie" class="input-cookie" autofocus="autofocus" value="' + var2 + '" /><td><input class="edit" type="submit" value="change"/></td><td><input class="delete" type="submit" value="delete"/></td>');

        $('.edit', this).click(function () {


            var var3 = $("input[name='change_cookie']").val();
            $(this).parent().html(var3);
            console.log(var2);
            console.log(var3);
            $.post("ControllerCookie", {prediction: var2, predictionUpdate: var3, method: 'update'}, function () {
                console.log('update success');
            });
        });

        $(".delete", this).click(function () {

            var var3 = $("input[name='change_cookie']").val();
            $(this).closest('tr').remove();
            console.log(var2);
            $.post("ControllerCookie", {var3key: var3, method: 'delete'}, function () {
                console.log('delete success');
            });
        });
    });


    $(".add").click(function () {
        var var1 = $("input[name='insert_cookie']").val();
        var obj = document.getElementById("table_prediction");
        if (var1 != 0) {
            $(obj).append('<tr><td style="width: 14px;" class="editor">' + var1 + '</td></tr>');
            submit(var1);

        }
        return false;

    });


});


