$(document).ready(function() {
	percentCal();
    // config việc hiển thị message error
    setValidationForInCareers($("#table3DataForm"));
    // config cấu hình rule và message validate cho các item
    setValidationForInputInCareers($("#table3DataForm"));

    $(".changeAny").change(function() {

		// Tổng cộng Số người
		var totalPeople = calTotal($("[colTable3=numPeople]"));
		$("input[name='dataTable3DtoList[2].total']").val(totalPeople);

		percentCal();
    });
    
    // tự động save
	setInterval(function() {
	    let statusSave = $("#statusSave").val();
	    let statusInBrdComp = $("#statusInBrdComp").val();
        if (statusSave == '' && statusInBrdComp != '1') {
            let formScreen = $("#table3DataForm");
            formScreen.attr('action', './save');
            formScreen.submit();
        }
    }, $("#autosaveInterval").val());

});

function percentCal() {

	// Số Thành viên ban lãnh đạo là nam giới
	var malePeople = $("input[name='dataTable3DtoList[0].total']").val();
	// Số Thành viên ban lãnh đạo là nữ giới
	var femalePeople = $("input[name='dataTable3DtoList[1].total']").val();
	// Tổng số Thành viên ban lãnh đạo
	var totalPeople = $("input[name='dataTable3DtoList[2].total']").val();
	
	var percentm = 0;
	var percentf = 0;
	if (malePeople == 0 && femalePeople == 0) {
		percentm = 100;
		percentf = 100;
	} else {
		if ($.isNumeric($("input[name='dataTable3DtoList[0].total']").val())) {
			percentm = malePeople/totalPeople * 100;
		}
		if ($.isNumeric($("input[name='dataTable3DtoList[1].total']").val())) {
			percentf = femalePeople/totalPeople * 100;
		}
	}
	$("input[name='percentm']").val(percentm + '%');
	$("input[name='percentf']").val(percentf + '%');
	$("input[name='percentmf']").val('100%');
}
