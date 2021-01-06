$(document).ready(function() {
	var canFinish = false;
	var current_fs, next_fs, previous_fs; //fieldsets
    var opacity;
    var steps = $("fieldset").length;
    setProgressBar(1);
	$(".next").click(function() {	
		if(!$('#msform').valid()) return;
		if(!canFinish && $(this).hasClass('finished-button')) {return};

        current_fs = $(this).parent();
        
        
        next_fs = $(this).parent().next();
        
        var index  = $("fieldset").index(next_fs);
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

        var index  = $("fieldset").index(current_fs);
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
        $(".progress-bar")
            .css("width", percent + "%")
    }
    
    $.validator.setDefaults({
	    ignore: ":hidden:not(.validate)"
	});
	setValidationForInfoForm($('#msform'))
	// config cấu hình validate cho tab survey
	setValidationForArea($(".survey-area"));
	
	survey_api.loadData();
	$('.loader').hide();
	$(".finished-button").click(function(){
		if(!$('#msform').valid()) return;
		if(canFinish) {
			return
		};
		$('.loader').show("slow",function() {
			canFinish = true;
			survey_api.sendSurveyInfo($('#msform').attr('action'), function(data) {
			    $('#pdfView').attr('src', $('#pdfView').attr('action') + '/files/' + data.fileName)
			    $('#btn-download').click(function(){
			        window.location.href = $(this).attr('action') + '?fileName=' + data.fileName;
			    });
			    canFinish = true;
			    $('.loader').hide();
		        $("fieldset:visible").find('.next').click();
			}, setProgressBar);
		});
		
	});
	
	$('#register').click(function(){
        window.location.href = $(this).attr('action');
    });
});