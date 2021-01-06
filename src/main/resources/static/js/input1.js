$(document).ready(function() {

    // config việc hiển thị message error
    setValidationForInCareers($("#table1DataForm"));
    // config cấu hình rule và message validate cho các item
    setValidationForInputInCareers($("#table1DataForm"));

    $(".changeAny").change(function() {

		// Tổng số nhân viên đầu kỳ tham chiếu 12 tháng (Tổng số lao động nam)
		var totalMaleBegin12m = calTotal($("[colTable1=totalBegin12mm]"));
		$("input[name='dataTable1DtoList[10].totalBegin12m']").val(totalMaleBegin12m);
		// Tổng số nhân viên đầu kỳ tham chiếu 12 tháng (Tổng số lao động nữ)
		var totalFemaleBegin12m = calTotal($("[colTable1=totalBegin12mf]"));
		$("input[name='dataTable1DtoList[11].totalBegin12m']").val(totalFemaleBegin12m);
		// Tổng số nhân viên đầu kỳ tham chiếu 12 tháng (Tổng số lao động name nữ)
		$("input[name='dataTable1DtoList[12].totalBegin12m']").val(totalMaleBegin12m + totalFemaleBegin12m);


        // Tuyển dụng vào cấp độ này từ bên ngoài công ty (Tổng số lao động nam)
        var totalMaleRecruitedOutside = calTotal($("[colTable1=recruitedOutsidem]"));
        $("input[name='dataTable1DtoList[10].recruitedOutside']").val(totalMaleRecruitedOutside);
        // Tuyển dụng vào cấp độ này từ bên ngoài công ty (Tổng số lao động nữ)
        var totalFemaleRecruitedOutside = calTotal($("[colTable1=recruitedOutsidef]"));
        $("input[name='dataTable1DtoList[11].recruitedOutside']").val(totalFemaleRecruitedOutside);
        // Tuyển dụng vào cấp độ này từ bên ngoài công ty (Tổng số lao động nam và nữ)
        $("input[name='dataTable1DtoList[12].recruitedOutside']").val(totalMaleRecruitedOutside + totalFemaleRecruitedOutside);

		// Thăng chức lên cấp độ này trong nội bộ công ty (Tổng số lao động nam)
        var totalMalePromotedToLvl = calTotal($("[colTable1=promotedToLvlm]"));
        $("input[name='dataTable1DtoList[10].promotedToLvl']").val(totalMalePromotedToLvl);
        // Thăng chức lên cấp độ này trong nội bộ công ty (Tổng số lao động nữ)
        var totalFemalePromotedToLvl = calTotal($("[colTable1=promotedToLvlf]"));
        $("input[name='dataTable1DtoList[11].promotedToLvl']").val(totalFemalePromotedToLvl);
        // Thăng chức lên cấp độ này trong nội bộ công ty (Tổng số lao động nam và nữ)
        $("input[name='dataTable1DtoList[12].promotedToLvl']").val(totalMalePromotedToLvl + totalFemalePromotedToLvl);


        // Thăng chức từ cấp độ này lên cấp cao hơn (Nam giới giữ vị trí cấp 4)
        $("input[name='dataTable1DtoList[2].promotedFromLvl']").val($("input[name='dataTable1DtoList[0].promotedToLvl']").val());
        // Thăng chức từ cấp độ này lên cấp cao hơn (Nữ giới giữ vị trí cấp 4)
        $("input[name='dataTable1DtoList[3].promotedFromLvl']").val($("input[name='dataTable1DtoList[1].promotedToLvl']").val());
        // Thăng chức từ cấp độ này lên cấp cao hơn (Nam giới giữ vị trí cấp 3)
        $("input[name='dataTable1DtoList[4].promotedFromLvl']").val($("input[name='dataTable1DtoList[2].promotedToLvl']").val());
        // Thăng chức từ cấp độ này lên cấp cao hơn (Nữ giới giữ vị trí cấp 3)
        $("input[name='dataTable1DtoList[5].promotedFromLvl']").val($("input[name='dataTable1DtoList[3].promotedToLvl']").val());
        // Thăng chức từ cấp độ này lên cấp cao hơn (Nam giới giữ vị trí cấp 2)
        $("input[name='dataTable1DtoList[6].promotedFromLvl']").val($("input[name='dataTable1DtoList[4].promotedToLvl']").val());
        // Thăng chức từ cấp độ này lên cấp cao hơn (Nữ giới giữ vị trí cấp 2)
        $("input[name='dataTable1DtoList[7].promotedFromLvl']").val($("input[name='dataTable1DtoList[5].promotedToLvl']").val());
        // Thăng chức từ cấp độ này lên cấp cao hơn (Nam giới giữ vị trí cấp 1)
        $("input[name='dataTable1DtoList[8].promotedFromLvl']").val($("input[name='dataTable1DtoList[6].promotedToLvl']").val());
        // Thăng chức từ cấp độ này lên cấp cao hơn (Nữ giới giữ vị trí cấp 1)
        $("input[name='dataTable1DtoList[9].promotedFromLvl']").val($("input[name='dataTable1DtoList[7].promotedToLvl']").val());
        // Thăng chức từ cấp độ này lên cấp cao hơn (Tổng số lao động nam)
        var totalMalePromotedFromLvl = parseInt($("input[name='dataTable1DtoList[0].promotedFromLvl']").val()) + parseInt($("input[name='dataTable1DtoList[2].promotedFromLvl']").val())
                           + parseInt($("input[name='dataTable1DtoList[4].promotedFromLvl']").val()) + parseInt($("input[name='dataTable1DtoList[6].promotedFromLvl']").val())
                           + parseInt($("input[name='dataTable1DtoList[8].promotedFromLvl']").val());
        $("input[name='dataTable1DtoList[10].promotedFromLvl']").val(totalMalePromotedFromLvl);
        // Thăng chức từ cấp độ này lên cấp cao hơn (Tổng số lao động nữ)
        var totalFemalePromotedFromLvl = parseInt($("input[name='dataTable1DtoList[1].promotedFromLvl']").val()) + parseInt($("input[name='dataTable1DtoList[3].promotedFromLvl']").val())
                           + parseInt($("input[name='dataTable1DtoList[5].promotedFromLvl']").val()) + parseInt($("input[name='dataTable1DtoList[7].promotedFromLvl']").val())
                           + parseInt($("input[name='dataTable1DtoList[9].promotedFromLvl']").val());
        $("input[name='dataTable1DtoList[11].promotedFromLvl']").val(totalFemalePromotedFromLvl);
        // Thăng chức từ cấp độ này lên cấp cao hơn (Tổng số lao động nam và nữ)
        $("input[name='dataTable1DtoList[12].promotedFromLvl']").val(totalMalePromotedFromLvl + totalFemalePromotedFromLvl);


        // Tổng số nhân viên nghỉ việc từ cấp độ này (Tổng số lao động nam)
        var totalMaleLeftCompany = calTotal($("[colTable1=leftCompanym]"));
        $("input[name='dataTable1DtoList[10].leftCompany']").val(totalMaleLeftCompany);
        // Tổng số nhân viên nghỉ việc từ cấp độ này (Tổng số lao động nữ)
        var totalFemaleLeftCompany = calTotal($("[colTable1=leftCompanyf]"));
        $("input[name='dataTable1DtoList[11].leftCompany']").val(totalFemaleLeftCompany);
        // Tổng số nhân viên nghỉ việc từ cấp độ này (Tổng số lao động nam và nữ)
        $("input[name='dataTable1DtoList[12].leftCompany']").val(totalMaleLeftCompany + totalFemaleLeftCompany);


        // Tổng số nhân viên cuối kỳ tham chiếu 12 tháng (Nam giới giữ vị trí cấp 5)
        $("input[name='dataTable1DtoList[0].totalEnd12m']").val(calTotal($("[rowtable1=0]")) - calTotal($("[rowsubtable1=0]")));
        // Tổng số nhân viên cuối kỳ tham chiếu 12 tháng (Nữ giới giữ vị trí cấp 5)
        $("input[name='dataTable1DtoList[1].totalEnd12m']").val(calTotal($("[rowtable1=1]")) - calTotal($("[rowsubtable1=1]")));
        // Tổng số nhân viên cuối kỳ tham chiếu 12 tháng (Nam giới giữ vị trí cấp 4)
        $("input[name='dataTable1DtoList[2].totalEnd12m']").val(calTotal($("[rowtable1=2]")) - calTotal($("[rowsubtable1=2]")));
        // Tổng số nhân viên cuối kỳ tham chiếu 12 tháng (Nữ giới giữ vị trí cấp 4)
        $("input[name='dataTable1DtoList[3].totalEnd12m']").val(calTotal($("[rowtable1=3]")) - calTotal($("[rowsubtable1=3]")));
        // Tổng số nhân viên cuối kỳ tham chiếu 12 tháng (Nam giới giữ vị trí cấp 3)
        $("input[name='dataTable1DtoList[4].totalEnd12m']").val(calTotal($("[rowtable1=4]")) - calTotal($("[rowsubtable1=4]")));
        // Tổng số nhân viên cuối kỳ tham chiếu 12 tháng (Nữ giới giữ vị trí cấp 3)
        $("input[name='dataTable1DtoList[5].totalEnd12m']").val(calTotal($("[rowtable1=5]")) - calTotal($("[rowsubtable1=5]")));
        // Tổng số nhân viên cuối kỳ tham chiếu 12 tháng (Nam giới giữ vị trí cấp 2)
        $("input[name='dataTable1DtoList[6].totalEnd12m']").val(calTotal($("[rowtable1=6]")) - calTotal($("[rowsubtable1=6]")));
        // Tổng số nhân viên cuối kỳ tham chiếu 12 tháng (Nữ giới giữ vị trí cấp 2)
        $("input[name='dataTable1DtoList[7].totalEnd12m']").val(calTotal($("[rowtable1=7]")) - calTotal($("[rowsubtable1=7]")));
        // Tổng số nhân viên cuối kỳ tham chiếu 12 tháng (Nam giới giữ vị trí cấp 1)
        $("input[name='dataTable1DtoList[8].totalEnd12m']").val(calTotal($("[rowtable1=8]")) - calTotal($("[rowsubtable1=8]")));
        // Tổng số nhân viên cuối kỳ tham chiếu 12 tháng (Nữ giới giữ vị trí cấp 1)
        $("input[name='dataTable1DtoList[9].totalEnd12m']").val(calTotal($("[rowtable1=9]")) - calTotal($("[rowsubtable1=9]")));
        // Tổng số nhân viên cuối kỳ tham chiếu 12 tháng (Tổng số lao động nam)
        $("input[name='dataTable1DtoList[10].totalEnd12m']").val(totalMaleBegin12m + totalMaleRecruitedOutside + totalMalePromotedToLvl - totalMalePromotedFromLvl - totalMaleLeftCompany);
        // Tổng số nhân viên cuối kỳ tham chiếu 12 tháng (Tổng số lao động nữ)
        $("input[name='dataTable1DtoList[11].totalEnd12m']").val(totalFemaleBegin12m + totalFemaleRecruitedOutside + totalFemalePromotedToLvl - totalFemalePromotedFromLvl - totalFemaleLeftCompany);
        // Tổng số nhân viên cuối kỳ tham chiếu 12 tháng (Tổng số lao động nam và nữ)
        $("input[name='dataTable1DtoList[12].totalEnd12m']").val(totalMaleBegin12m + totalMaleRecruitedOutside + totalMalePromotedToLvl - totalMalePromotedFromLvl - totalMaleLeftCompany
                                        + totalFemaleBegin12m + totalFemaleRecruitedOutside + totalFemalePromotedToLvl - totalFemalePromotedFromLvl - totalFemaleLeftCompany);

    });

    // tự động save
	setInterval(function() {
	    let statusSave = $("#statusSave").val();
	    let statusInEmplNvmt = $("#statusInEmplNvmt").val();
        if (statusSave == '' && statusInEmplNvmt != '1') {
            let formScreen = $("#table1DataForm");
            formScreen.attr('action', './save');
            formScreen.submit();
        }
    }, $("#autosaveInterval").val());
    
});