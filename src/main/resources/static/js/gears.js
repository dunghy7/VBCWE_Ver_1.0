function loadData() {
    var data = $("#surveyInfo").val();
    if (data && data != '') {
        var data = JSON.parse(data);
        $("input[name='companyName']").val(data.companyName);
        $("input[name='address']").val(data.address);
        $("input[name='district']").val(data.district);
        $("input[name='city']").val(data.city);
        $("input[name='fullName']").val(data.fullName);
        $("input[name='position']").val(data.position);
        $("input[name='phone']").val(data.phone);
        $("input[name='mobile']").val(data.mobile);
        $("input[name='fax']").val(data.fax);
        $("input[name='email']").val(data.email);
        var survey = data.survey;
        var idx = -1;
        $(".survey-area").each(function(faIndex) {
            $(this).find('.row:nth-child(n+4)').each(function(index) {
                idx += 1
                if (idx >= survey.length) return;
                if (survey[idx].answer == '1') {
                    $(this).find('input:radio')[0].checked = true;
                } else if (survey[idx].answer == '0') {
                    $(this).find('input:radio')[1].checked = true;
                }
                $(this).find('textarea').val(survey[idx].note);
                $(this).find('input:hidden').each(function(index, value) {
                    var itm = $(this).next();
                    //file
                    if ($(itm).is('.uploadfile')) {
                        if (survey[idx].file != '') {
                        	$(this).val(survey[idx].file);
                            var fileIcon = $(itm).closest(".col-2").find(".fileIcon")[0];
                            //hiển thị icon file
                            showImage($(itm),survey[idx].file)
                            $(itm).hide();
                        } else {
                            if ($('#statusReport').val()) {
                                $(itm).hide();
                            }
                        }
                    }
                });
                $(this).find('input:checkbox').each(function(cIndex) {
                    if (survey[idx].other && survey[idx].other.substr(cIndex, 1) == '1') {
                        this.checked = true;
                    }
                });
            });
        });
        $("#surveyInfo").val('');
    }
}

//
function showImage(inputFile,filePath){
    //hiển thị icon file
    var fileIcon = $(inputFile).closest(".col-2").find(".fileIcon")[0];
    $(fileIcon).removeClass("d-none");
    //set giá trị cho hidden là đường dẫn file
    var path = $(inputFile).closest(".col-2").find("input:hidden")[0]
    var filename = $(inputFile).closest(".col-2").find(".filename")[0]
    $(path).val(filePath)
    // hiển thị tên file vừa upload
    var arrName = filePath.split('/').pop().split("_")
    arrName.shift();
    $(filename).html(arrName.join())
}

//đọc html lấy ra object survey
function getSurveyValue(inputfile) {
    var data = {};
    data.companyName = $("input[name='companyName']").val();
    data.address = $("input[name='address']").val();
    data.district = $("input[name='district']").val();
    data.city = $("input[name='city']").val();
    data.fullName = $("input[name='fullName']").val();
    data.position = $("input[name='position']").val();
    data.phone = $("input[name='phone']").val();
    data.mobile = $("input[name='mobile']").val();
    data.fax = $("input[name='fax']").val();
    data.email = $("input[name='email']").val();
    var survey = [];
    var idx = -1;
    $(".survey-area").each(function(faIndex) {
        $(this).find('.row:nth-child(n+4)').each(function(index) {
            idx += 1;
            survey[idx] = {};
            //fa
            survey[idx].fa = faIndex + 1;
            //no
            survey[idx].no = index + 1;
            //answer
            if ($(this).find('input:radio')[0].checked) {
                survey[idx].answer = '1';
            } else if ($(this).find('input:radio')[1].checked) {
                survey[idx].answer = '0';
            }
            //other
            $(this).find('input:checkbox').each(function() {
                if (!survey[idx].other) {
                    survey[idx].other = '';
                }
                survey[idx].other += this.checked ? '1' : '0';
            });
            //note
            survey[idx].note = $(this).find('textarea').val();
            //level and file
            $(this).find('input:hidden').each(function(index, value) {
                if ($(this).is('.uploadfile')||$(this).is('.form-check-input')) {
                    return;
                }
                var itm = $(this).next();
                //file
                if ($(itm).is('.uploadfile')) {
                    // nếu phần tử đang duyệt là fileinput truyền vào
                    if (inputfile != undefined && $(itm).is($(inputfile))) {
                        survey[idx].file = "{_PATH_FILE_}";
                        // nếu không
                    } else {
                        survey[idx].file = $(this).val();
                    }
                    //level
                } else {
                    survey[idx].level = $(this).val();
                }
            });
        });
        data.survey = survey;
        return data;
    })
    data.survey = survey;
    return data;
}

//hàm save survey
function saveSurvey(isManual, elm) {
    // lấy dữ liệu survey
    var survey = getSurveyValue()
    //callback khi xử lý thành công
    var onSucess = function() {
        //nếu không phải save tự động thì gọi next step 
        if (isManual) {
            $($(elm).closest("fieldset").find(".next")[0]).trigger("click");
        }
    }
    //callback khi có lỗi
    var onError = function() {
        //bật lên thông báo: lỗi cmnr liên hệ với thằng code đi
        alert(messageError.generalException)
    }
    // post lên server để save survey
    api.doPost($("#msform").attr('action'), JSON.stringify(survey), onSucess, onError)

}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
$(document).ready(function() {
    var current_fs, next_fs, previous_fs; //fieldsets
    var opacity;
    var current = 1;
    var steps = $("fieldset").length;
    setProgressBar(1);
    $(".next").click(function() {
        //if(!$('#msform').valid()) return;
        current_fs = $(this).parent();


        next_fs = $(this).parent().next();

        var index = $("fieldset").index(next_fs);
        //Add Class Active
        $("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");
        //show the next fieldset
        next_fs.show();
        //hide the current fieldset with style
        current_fs.animate({
            opacity: 0
        }, {
            step: function(now) {
                // for making fielset appear animation
                opacity = 1 - now;

                current_fs.css({
                    'display': 'none',
                    'position': 'relative'
                });
                next_fs.css({
                    'opacity': opacity
                });
            },
            duration: 500
        });
        setProgressBar(++index);
    });

    $(".previous").click(function() {

        current_fs = $(this).parent();
        previous_fs = $(this).parent().prev();

        var index = $("fieldset").index(current_fs);
        //Remove class active
        $("#progressbar li").eq(index).removeClass("active");

        //show the previous fieldset
        previous_fs.show();

        //hide the current fieldset with style
        current_fs.animate({
            opacity: 0
        }, {
            step: function(now) {
                // for making fielset appear animation
                opacity = 1 - now;

                current_fs.css({
                    'display': 'none',
                    'position': 'relative'
                });
                previous_fs.css({
                    'opacity': opacity
                });
            },
            duration: 500
        });
        setProgressBar(index);
    });

    function setProgressBar(curStep) {
        var percent = parseFloat(100 / steps) * curStep;
        percent = percent.toFixed();
        $(".main-progress-bar")
            .css("width", percent + "%")
    }
    $(".submit").click(function() {
        return false;
    })
    //overwrite lại thuộc tính ignore của jquery validate để validate cả những field có display:none
    $.validator.setDefaults({
        ignore: []
    });
    $(".continue").click(function() {
        //var validator = $("#msform").validate()
        //console.log(validator.errorList)
    })
    // config cấu hình validate cho tab thông tin cá nhân
    setValidationForInfoForm($("#msform"));
    // config cấu hình validate cho tab survey
    setValidationForArea($(".survey-area"));

    //khi chọn file thì đầy thẳng lên server kèm với thông tin các survey rồi save
    $(".uploadfile").change(function() {
        var maxFileSize = $("#maxFileSize").val();
        var lstExtension = $("#lstExtension").val();
        var inputFile = this;
        
        var files = this.files;
        if (files.length <= 0)
            return false;
        //lấy file thứ 0
        var file = files[0];
        // kiểm tra dung lượng file
        var size = file.size
        if (size > maxFileSize * 1024 * 1024) {
            alert(messageError.maxFileSize);
            $(this).val('');
            return false;
        }
        // kiểm tra đuôi mở rộng của file
        var extension = file.name.split('.').pop();
        if (lstExtension.indexOf(extension) == -1) {
            alert(messageError.notAcceptFile);
            $(this).val('');
            return false;
        }
        
        // ẩn select file
        $(this).hide();
        // tìm đến progressbar gần nhất và hiển thị lên
        var progressContainer = $(this).closest(".col-2").find(".progress")[0];
        var progressbar = $(progressContainer).find(".progress-bar")[0];
        $(progressContainer).removeClass("d-none")
        //set giá trị cho progressbar về 0
        $(progressbar).width(0 + "%")
        
        // lấy dữ liệu survey
        var survey = getSurveyValue(inputFile)
        //upload file và dữ liệu
        var fd = new FormData();
        fd.append('file', file);
        fd.append('survey', JSON.stringify(survey));
        $.ajax({
            xhr: function() {
                var xhr = new window.XMLHttpRequest();
                xhr.upload.addEventListener("progress", function(evt) {
                    if (evt.lengthComputable) {
                        var percentComplete = evt.loaded / evt.total;
                        percentComplete = parseInt(percentComplete * 100);
                        // gán giá trị cho progressbar
                        $(progressbar).width(percentComplete + "%")
                    }
                }, false);
                return xhr;
            },
            url: "uploadevidence",
            type: "POST",
            contentType: false,
            processData: false,
            dataType: "json",
            data: fd,
            success: function(result) {
                // nếu có lỗi validate
                if (result.status == "-1") {
                    alert(result.message);
                }
                // nếu thành công
                else {
                    // ẩn process
                    $(progressContainer).addClass("d-none");
                	showImage(inputFile,result.path);
                }
            }
        });

    })
    // lưu thông tin survey
    $("[name='save']").click(function() {
        // nếu là tab cuối cùng thì không next
        if ($(this).hasClass('stoped'))
            saveSurvey();
        else
            saveSurvey(true, this);
    })


    //tự động save survey
    setInterval(function() {
        if ($("#statusReport").val() == '' && $("#statusGears").val() != '1') {
            saveSurvey();
        }
    }, $("#autosaveInterval").val());

    //lấy thông tin survey
    loadData();
    
    //xóa file
    $(".deleteFile").on("click", function(){
	  	var elm = this;
		// lấy thông tin đường dẫn file
		var hidePath = $(elm).closest(".col-2").find("input:hidden").not('.uploadfile')[0];
		var inputFile = $(elm).closest(".col-2").find(".uploadfile")[0];
		var fileIcon = $(elm).closest(".col-2").find(".fileIcon")
		var filename = $(elm).closest(".col-2").find(".filename")
		console.log(inputFile)
		var path = $(hidePath).val();
        //lấy dữ liệu survey
        var survey = getSurveyValue();
        //tạo object truyền lên server
        var data = {};
        data.path = path;
        data.survey= survey;
        //callback khi xử lý thành công
        var onSucess = function(data) {
			$(hidePath).val("");
			$(inputFile).show();
			$(inputFile).val("");
			$(fileIcon).addClass("d-none");
			$(filename).html("");
        }
        //callback khi có lỗi
        var onError = function() {
            //bật lên thông báo: lỗi cmnr liên hệ với thằng code đi
            alert(messageError.generalException)
        }
        // post lên server để save survey
        api.doPost($("#urlDeleteEvidence").val(), JSON.stringify(data), onSucess, onError)   
	});
    

    //completeSurvey
    $("#completeSurvey").click(function() {
        var elm = this;
        // check dữ liệu trên form
        // nếu có lỗi hiển thị lỗi và dừng xử lý
        $('label[id*=-error]').remove();
        if (!$("#msform").valid()){
            //quay lại tab có field có lỗi
            survey_api.checkErrorFs(setProgressBar);
        	return false;
        }
        // nếu không lỗi tiến hàng  save survey
        //lấy dữ liệu
        var survey = getSurveyValue();
        //callback khi xử lý thành công
        var onSucess = function(data) {
            if (data.errors && data.errors.length > 0) {
                $.each(data.errors, function(idx, data) {
                    var elm = $('input[name="' + data.name + '"]');
                    if (elm.length > 0) {
                        $('#' + data.name + '-error').remove();
                        elm.after('<label id="' + data.name + '-error" class="error" for="' + data.name + '">' + data.message + '</label>');
                    } else {
                        var idx = -1;
                        $(".survey-area").each(function(faIndex) {
                            $(this).find('.row:nth-child(n+4)').each(function(index) {
                                idx += 1
                                if (data.name.split('_')[1] == idx) {
                                    $(this).find('.col-5').append('<label id="' + data.name + '-error" class="error" for="' + data.name + '">' + data.message + '</label>');
                                }
                            });
                        });
                    }
                });
                survey_api.checkErrorFs(setProgressBar);
            } else {
                $($(elm).closest("fieldset").find(".next")[0]).trigger("click");
            };
        }
        //callback khi có lỗi
        var onError = function() {
            //bật lên thông báo: lỗi cmnr liên hệ với thằng code đi
            alert(messageError.generalException)
        }
        // post lên server để save survey
        api.doPost($("#urlCompleteSurvey").val(), JSON.stringify(survey), onSucess, onError)
    })
});