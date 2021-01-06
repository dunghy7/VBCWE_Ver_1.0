$(document).ready(function() {
    setValidationForInfoForm($("#formSignup"));
    
    $("#btnSignup").on("click", function(event) {
        event.preventDefault();
        $(".content-header").remove();
        if ($("#formSignup").valid()) {
            $("#formSignup").submit();
        }
    });
});