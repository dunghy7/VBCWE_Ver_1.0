$(document).ready(function() {
    setValidationForInfoForm($("#formChangePass"));
    
    $("#btnChangePass").on("click", function(event) {
        event.preventDefault();
        if ($("#formChangePass").valid()) {
            $("#formChangePass").submit();
        }
    });
});