///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
$(document).ready(function() {
   $("#downloadSurvey").click(function(event ){
   		event.preventDefault();
   		//taoj form
   		var form = document.createElement("form");
        form.setAttribute("method", "post");
        form.setAttribute("action", $("#urlDownloadSurvey").val());
        form.setAttribute("style", "display: none;");
        //tạo hidden
        var hiddenField = document.createElement("input");
        hiddenField.setAttribute("name", "surveyPath");
        hiddenField.setAttribute("value", $(this).attr("href"));
        form.appendChild(hiddenField);
        document.body.appendChild(form);
        form.submit();
        form.remove();
   })
   
    // Upload file GEARS HR Data Table Template.xlsx
    $("#uploadFileGearsInput").click(function(event ){
        
        event.preventDefault();
        $("#uploadFileGearsInput").prop("disabled", true);
        var filesInput = document.getElementById("inputGroupFile01").files;
        // Check đã chọn file chưa ?
        if (filesInput.length <= 0) {
            $("#uploadFileGearsInput").prop("disabled", false);
            return false;
        }
        // kiểm tra đuôi mở rộng của file
        var lstExtension = ".xls,.xlsx";
        var extension = filesInput[0].name.split('.').pop();
        if (lstExtension.indexOf(extension) == -1) {
            alert(messageError.notAcceptFile);
            $("#uploadFileGearsInput").prop("disabled", false);
            return false;
        }
        
	        $('.loader').show("slow",function() {
	        	// Tạo form
		        var form = new FormData();
		        form.append('file', filesInput[0]);
		        
		        $.ajax({
		            url: "uploadFileData",
		            type: "POST",
		            contentType: false,
		            processData: false,
		            dataType: "json",
		            data: form,
		            success: function(result) {
		            
		                // nếu có lỗi validate
		                if (result.status == "-1") {
		                    alert(result.message);
		                }
		                // nếu thành công
		                else {
		                    alert("upload file thành công");
		                    location.reload();
		                }
		                $("#uploadFileGearsInput").prop("disabled", false);
		                
		                $('.loader').hide();
		            }
		        });
		        $("#uploadFileGearsInput").prop("disabled", false);
        });
        

   })

});

