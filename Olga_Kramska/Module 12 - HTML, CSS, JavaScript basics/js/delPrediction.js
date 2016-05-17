/**
 * Created by Olga Kramska on 15-May-16.
 */
$(function () {
    $('#delete').on('click', deleteDataHandler);
    $(document).on('change', '.checkBox', changeHandler);

    function deleteDataHandler(e) {
        $('.checkBox:checked').each(function (idx, item) {
            item.closest('tr').remove();
        });
    }

    $("#selectAll").change(function () {
        $(".checkBox").prop('checked', $(this).prop("checked"));
    });

    function changeHandler() {
        if ($('#selectAll:checked').length) {
            $("#selectAll").prop('checked', $(this).prop("checked"))
        }
    }

});