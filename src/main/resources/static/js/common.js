$(document).ready(function () {
    $('.language').on('click', function() { 
	    var value = $(this).attr("lang");
	    var posi = window.location.href.indexOf("?");
	    if (posi == -1) {
	       window.location.href = window.location.href + "?lang="+value;
	    } else {
	        var pos = window.location.href.indexOf("lang=");
	        if (pos == -1) {
	           window.location.href = window.location.href + "&lang="+value;
	        } else {
	            var url = window.location.href.split('lang=');
	            window.location.href =url[0]+ "lang="+value;
	        }
	    }
	})
});

//set cấu hình validate cho form cứng
function setValidationForInfoForm(elm) {
    return elm.validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        errorPlacement: function ( error, element ) {
        	if($(element).closest('.survey-area').length > 0) {
        		 $($(element).closest('.row').find('div')[1]).append(error);
        	} else {
        		error.insertAfter( element );
        	}
   	  	},
        rules: {
            "companyName": {
                required: true,
                maxlength: 200
            },
            "companyNameAbbr": {
                required: true,
                maxlength: 10
            },
            "address": {
                required: true,
                maxlength: 500
            },
            "district": {
                required: true,
                maxlength: 50
            },
            "city": {
                required: true,
                maxlength: 50
            },
            "fullName": {
                required: true,
                maxlength: 100
            },
            "position": {
                required: true,
                maxlength: 200
            },
            "phone": {
                required: true,
                maxlength: 15,
                number: true
            },
            "mobile": {
                required: true,
                maxlength: 15,
                number: true
            },
            "fax": {
                required: true,
                maxlength: 15,
                number: true
            },
            "email": {
                required: true,
                maxlength: 50,
                email: true,
            },
            "userId": {
                required: true,
                maxlength: 50,
                email: true,
            },
            "companyScale": {
                required: true
            },
            "companyBusiness": {
                required: true,
                maxlength: 200
            },
            "companyArea": {
                required: true
            },
            "userType": {
                required: true
            },
            "password": {
                required: true,
                minlength: 8,
                maxlength: 50
            },
            "rePassword": {
                required: true,
                minlength: 8,
                maxlength: 50,
                equalTo: "#password"
            },
            "passwordOld": {
                required: true,
                minlength: 8,
                maxlength: 50
            },
            "passwordNew": {
                required: true,
                minlength: 8,
                maxlength: 50
            },
            "rePasswordNew": {
                required: true,
                minlength: 8,
                maxlength: 50,
                equalTo: "#passwordNew"
            }

        },
        messages: {
            "companyName": {
                required: messageError.required,
                maxlength: messageError.maxLength.format("200")
            },
            "companyNameAbbr": {
                required: messageError.required,
                maxlength: messageError.maxLength.format("10")
            },
            "address": {
                required: messageError.required,
                maxlength: messageError.maxLength.format("500")
            },
            "district": {
                required: messageError.required,
                maxlength: messageError.maxLength.format("50")
            },
            "city": {
                required: messageError.required,
                maxlength: messageError.maxLength.format("50")
            },
            "fullName": {
                required: messageError.required,
                maxlength: messageError.maxLength.format("100")
            },
            "position": {
                required: messageError.required,
                maxlength: messageError.maxLength.format("200")
            },
            "phone": {
                required: messageError.required,
                maxlength: messageError.maxLength.format("15"),
                number: messageError.mustBeNumber
            },
            "mobile": {
                required: messageError.required,
                maxlength: messageError.maxLength.format("15"),
                number: messageError.mustBeNumber
            },
            "fax": {
                required: messageError.required,
                maxlength: messageError.maxLength.format("15"),
                number: messageError.mustBeNumber
            },
            "email": {
                required: messageError.required,
                maxlength: messageError.maxLength.format("50"),
                email: messageError.email
            },
            "userId": {
                required: messageError.required,
                maxlength: messageError.maxLength.format("50"),
                email: messageError.email
            },
            "companyScale": {
                required: messageError.required,
            },
            "companyBusiness": {
                required: messageError.required,
                maxlength: messageError.maxLength.format("200")
            },
            "companyArea": {
                required: messageError.required,
            },
            "userType": {
                required: messageError.required
            },
            "password": {
                required: messageError.required,
                minlength: messageError.minLength.format("8"),
                maxlength: messageError.maxLength.format("50")
            },
            "rePassword": {
                required: messageError.required,
                minlength: messageError.minLength.format("8"),
                maxlength: messageError.maxLength.format("50"),
                equalTo: messageError.notEqual.format(messageError.inputPassword, messageError.password)
            },
            "passwordOld": {
                required: messageError.required,
                minlength: messageError.minLength.format("8"),
                maxlength: messageError.maxLength.format("50")
            },
            "passwordNew": {
                required: messageError.required,
                minlength: messageError.minLength.format("8"),
                maxlength: messageError.maxLength.format("50")
            },
            "rePasswordNew": {
                required: messageError.required,
                minlength: messageError.minLength.format("8"),
                maxlength: messageError.maxLength.format("50"),
                equalTo: messageError.notEqual.format(messageError.inputNewPassword, messageError.newPassword)
            }
        }
    });
}
//add động các validate vào cho form theo quy tắc
function setValidationForArea(lstElm) {
    // lặp tất cả các fieldset
    lstElm.each(function(index, value) {
        // lặp tất cả các row
        var allrow = $(value).find(".row");
        allrow.each(function(index1, value1) {
            var radio = $(value1).find("input:radio")
            // nếu có radiobtn
            if (radio.length > 0) {
            	$(radio).rules("add", {
                    required: true,
                    messages: {
                        required: messageError.required
                    }
                }) 
            }
            var checkbox = $(value1).find("input:checkbox")
            // nếu có checkbox
            if (checkbox.length > 0) {
            	$(checkbox).rules("add", {
                    required: function(element){
                    	var name = $(radio).attr('name')
                    	return $('input[name='+name+']:checked').val() == "1"
                    },
                    messages: {
                        required: messageError.required
                    }
                }) 
            }
        })
    })
}



var api = {
    doPost : function(url, postData, onSuccess, onError, onValidateAjax) {
        $.ajax({
            datatype : "json",
            type : "POST",
            url : url,
            data : postData,
            async: false,
            contentType : 'application/json'
        }).success(function(data) {
        	onSuccess(data);
        }).error(function(data, status, jqXHR) {
        	onError(data);
        });
    }
}


var survey_api = {
	// tạo chuỗi json và gửi lên server
	sendSurveyInfo : function(url, onSuccess, setProgressBar) {
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
		$( ".survey-area" ).each(function( faIndex ) {
			$(this).find('.row:nth-child(n+4)').each(function( index ) {
				idx += 1;
				survey[idx] = {};
				survey[idx].fa = faIndex + 1;
				survey[idx].no = index + 1;
				survey[idx].level = $(this).find('input:hidden').val();
				if($(this).find('input:radio')[0].checked) {
					survey[idx].answer = '1';
				} else if($(this).find('input:radio')[1].checked) {
					survey[idx].answer = '0';
				}
				$(this).find('input:checkbox').each(function() {
					if(!survey[idx].other) {
						survey[idx].other = '';
					}
					survey[idx].other += this.checked ? '1' : '0';
				});
				
		 		
		 		index;
		 	});
		});
		
		data.survey = survey;
		
		api.doPost(url,JSON.stringify(data) , function(success) {
			if(success != '') {
				var data = success;
				if(data.errors && data.errors.length > 0) {
					$('fieldset').find('label[id*=-error]').remove();
					$.each(data.errors,function(idx, data) {
						var elm = $('input[name="'+ data.name +'"]');
						if(elm.length > 0) {
							$('#' + data.name +'-error').remove();
							elm.after('<label id="'+ data.name +'-error" class="error" for="'+ data.name +'">'+ data.message +'</label>');
						} else {
							var idx =-1;
							$( ".survey-area" ).each(function( faIndex ) {
	                			$(this).find('.row:nth-child(n+4)').each(function( index ) {
	                				idx += 1
	                				if(data.name.split('_')[1] == idx) {
	                					$(this).find('.col-9').append('<label id="'+ data.name +'-error" class="error" for="'+ data.name +'">'+ data.message +'</label>');
	                				}
	                		 	});
	                		});
						}
					});
					
					survey_api.checkErrorFs(setProgressBar);
					return;
				}
			}
			
			onSuccess(success);
			
		}, function(error) {
			
		});
	},
	
	// dữ liệu sẽ đc spring đổ vào trường ẩn data. sau đó js sẽ đọc và bind lại vào các item tương ứng
	loadData : function() {
		var data = $("input[name='data']").val();
		if(data && data != '') {
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
			$( ".survey-area" ).each(function( faIndex ) {
				$(this).find('.row:nth-child(n+4)').each(function( index ) {
					idx += 1
					if(idx >= survey.length) return;
					if(survey[idx].answer == '1') {
						$(this).find('input:radio')[0].checked = true;
					} else if(survey[idx].answer == '0'){
						$(this).find('input:radio')[1].checked = true;
					}
					
					$(this).find('input:checkbox').each(function(cIndex) {
						
						if(survey[idx].other && survey[idx].other.substr(cIndex,1) == '1') {
							this.checked = true;
						}
					});
			 	});
			});
			
			$("input[name='data']").val('');	
		}
	},
	
	// thực hiện tìm kiếm error trên các màn fieldset. nếu tìm thấy error thì nhảy đến màn lỗi đó
	checkErrorFs : function(setProgressBar){
		var fsIdx = 1;
		$("fieldset").each(function( faIndex ){
			if($(this).find('label[id*=-error]').length > 0) {
				fsIdx = faIndex + 1;
				$("#progressbar li").eq(faIndex + 1).addClass("active");
				setProgressBar(faIndex + 1);
				$(this).show();
				return false;
			}
		});
		
		for (var i = fsIdx; i < $("fieldset").length; i++) {
			//hide the current fieldset with style
	        $($("fieldset")[i]).animate({
	            opacity: 0
	        }, {
	            step: function(now) {
	                // for making fielset appear animation
	                opacity = 1 - now;
	
	                $($("fieldset")[i]).css({
	                    'display': 'none',
	                    'position': 'relative'
	                });
	                $($("fieldset")[fsIdx - 1]).css({
	                    'opacity': opacity
	                });
	            },
	            duration: 500
	        });
			$("#progressbar li").eq(i).removeClass("active");
		}
	}
}

function calTotal(lst){
    var total = 0;
	$(lst).each(function(index,value){
		var numInput = 0;
		if ($.isNumeric($(value).val())) {
			numInput = parseInt($(value).val());
		}
		total = total + numInput;
	})
	return total;
}

function actionSubmitForm(ele, action, formId) {

	$(ele).prop('disabled', true);
	if (action == './complete') {
	    $(ele).closest('form').find("#validateClientText").html("");
        if ($(formId).valid() == false) {
        	$(ele).prop('disabled', false);
        	return false;
        }
	}

	let $form = ele.closest('form');
	$form.setAttribute('action', action);
	$form.submit();
}


//set cấu hình validate cho form cứng
function setValidationForInCareers(elm) {
    return elm.validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        errorPlacement: function ( error, element ) {
            var div = $(elm).find("#validateClientText")
            if($.trim($(div).html()) == "")
            {
                $(elm).find("#validateClientText").html(error);
            }
        },
        rules: {
        },
        messages: {
        }
    });
}

//add động các validate vào cho form tại các màn hình Danh mục nghề nghiệp
function setValidationForInputInCareers(lstElm) {
	lstElm.find('input[type=text].validation').each(function(idex, value) {
        $(value).rules("add", {
            required: true,
            maxlength: 7,
            number: true,
            min: 0,
            messages: {
                required: messageError.required,
                maxlength: messageError.maxLength.format("7"),
                number: messageError.mustBeNumber,
                min: messageError.min.format("0")
            }
        })
    })
}