$(document).ready(function() {
	$(".page").click(function() {
        $('#search-form').append('<input type="hidden" name="page" value="' + $(this).val() +'" />');
        $('#search-form').submit();
    });
});