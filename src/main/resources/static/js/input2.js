$(document).ready(function() {
    // config việc hiển thị message error
    setValidationForInCareers($("#table2DataForm"));
    // config cấu hình rule và message validate cho các item
    setValidationForInputInCareers($("#table2DataForm"));

    $(".changeAny").change(function() {

		// Nhân viên toàn thời gian, có hợp đồng không xác định thời hạn tại cuối kỳ tham chiếu (Tổng số nam giới)
		var totalMaleIndefinitelyContract = calTotal($("[colTable2=indefinitelyContractm]"));
		$("input[name='dataTable2DtoList[10].indefinitelyContract']").val(totalMaleIndefinitelyContract);
		// Nhân viên toàn thời gian, có hợp đồng không xác định thời hạn tại cuối kỳ tham chiếu (Tổng số nữ giới)
		var totalFemaleIndefinitelyContract = calTotal($("[colTable2=indefinitelyContractf]"));
		$("input[name='dataTable2DtoList[11].indefinitelyContract']").val(totalFemaleIndefinitelyContract);

		// Nhân viên toàn thời gian, hợp đồng có xác định thời gian tại cuối kỳ tham chiếu (Tổng số nam giới)
        var totalMaleLimitedContract = calTotal($("[colTable2=limitedContractm]"));
        $("input[name='dataTable2DtoList[10].limitedContract']").val(totalMaleLimitedContract);
        // Nhân viên toàn thời gian, hợp đồng có xác định thời gian tại cuối kỳ tham chiếu (Tổng số nữ giới)
        var totalFemaleLimitedContract = calTotal($("[colTable2=limitedContractf]"));
        $("input[name='dataTable2DtoList[11].limitedContract']").val(totalFemaleLimitedContract);

		// Nhân viên làm việc bán thời gian tại cuối kỳ tham chiếu (Tổng số nam giới)
        var totalMalePromotedToLvl = calTotal($("[colTable2=partTimem]"));
        $("input[name='dataTable2DtoList[10].partTime']").val(totalMalePromotedToLvl);
        // Nhân viên làm việc bán thời gian tại cuối kỳ tham chiếu (Tổng số nữ giới)
        var totalFemalePromotedToLvl = calTotal($("[colTable2=partTimef]"));
        $("input[name='dataTable2DtoList[11].partTime']").val(totalFemalePromotedToLvl);

        // Tổng số nhân viên tại cuối kỳ tham chiếu (Nam giới giữ vị trí cấp 5)
        $("input[name='dataTable2DtoList[0].total']").val(calTotal($("[rowtable2=0]")));
        // Tổng số nhân viên tại cuối kỳ tham chiếu (Nữ giới giữ vị trí cấp 5)
        $("input[name='dataTable2DtoList[1].total']").val(calTotal($("[rowtable2=1]")));
        // Tổng số nhân viên tại cuối kỳ tham chiếu (Nam giới giữ vị trí cấp 4)
        $("input[name='dataTable2DtoList[2].total']").val(calTotal($("[rowtable2=2]")));
        // Tổng số nhân viên tại cuối kỳ tham chiếu (Nữ giới giữ vị trí cấp 4)
        $("input[name='dataTable2DtoList[3].total']").val(calTotal($("[rowtable2=3]")));
        // Tổng số nhân viên tại cuối kỳ tham chiếu (Nam giới giữ vị trí cấp 3)
        $("input[name='dataTable2DtoList[4].total']").val(calTotal($("[rowtable2=4]")));
        // Tổng số nhân viên tại cuối kỳ tham chiếu (Nữ giới giữ vị trí cấp 3)
        $("input[name='dataTable2DtoList[5].total']").val(calTotal($("[rowtable2=5]")));
        // Tổng số nhân viên tại cuối kỳ tham chiếu (Nam giới giữ vị trí cấp 2)
        $("input[name='dataTable2DtoList[6].total']").val(calTotal($("[rowtable2=6]")));
        // Tổng số nhân viên tại cuối kỳ tham chiếu (Nữ giới giữ vị trí cấp 2)
        $("input[name='dataTable2DtoList[7].total']").val(calTotal($("[rowtable2=7]")));
        // Tổng số nhân viên tại cuối kỳ tham chiếu (Nam giới giữ vị trí cấp 1)
        $("input[name='dataTable2DtoList[8].total']").val(calTotal($("[rowtable2=8]")));
        // Tổng số nhân viên tại cuối kỳ tham chiếu (Nữ giới giữ vị trí cấp 1)
        $("input[name='dataTable2DtoList[9].total']").val(calTotal($("[rowtable2=9]")));
        // Tổng số nhân viên tại cuối kỳ tham chiếu (Tổng số lao động nam)
        $("input[name='dataTable2DtoList[10].total']").val(totalMaleIndefinitelyContract + totalMaleLimitedContract + totalMalePromotedToLvl);
        // Tổng số nhân viên tại cuối kỳ tham chiếu (Tổng số lao động nữ)
        $("input[name='dataTable2DtoList[11].total']").val(totalFemaleIndefinitelyContract + totalFemaleLimitedContract + totalFemalePromotedToLvl);

    });
    
    // tự động save
	setInterval(function() {
	    let statusSave = $("#statusSave").val();
	    let statusInEmpl = $("#statusInEmpl").val();
        if (statusSave == '' && statusInEmpl != '1') {
            let formScreen = $("#table2DataForm");
            formScreen.attr('action', './save');
            formScreen.submit();
        }
    }, $("#autosaveInterval").val());

});

