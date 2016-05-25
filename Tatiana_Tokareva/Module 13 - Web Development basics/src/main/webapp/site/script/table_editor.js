
$(function(){
        $(':checkbox').on('click', function(event) {
                $(this).parents('tr').toggleClass("error");

            }
        )

        $("table").on('dblclick', ".editor", function()  {
            var var2 = $(this).text();
            $(this).html('<input class="input-cookie" autofocus="autofocus" value="'+ var2 +'" /><input class="edit" type="submit" value="change"/><input class="delete" type="submit" value="delete"/>');
            $('.edit', this).click(function() {
                var var2 = $(this).parent().children('.input-cookie').val();
                $(this).parent().html(var2);
            });
						
						$('.delete').click(function(){
   						$(this).closest('tr').remove();
						});
       });


			 
        $(".button").click(function() {
            var var1 = $("input[name='var1']").val();     
            var tablename = $(this).attr("tag");

            var obj = document.getElementById(tablename);
						if (var1!=0){
            $(obj).append('<tr><td style="width: 14px;" class="editor">'+var1+'</td></tr>');
		
						}
            return false;
				
	        });
					
    });