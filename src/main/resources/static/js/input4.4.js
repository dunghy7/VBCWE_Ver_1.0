$(document).ready(function() {
    // config việc hiển thị message error
    setValidationForInCareers($("#table4Sub4DataForm"));
    // config cấu hình rule và message validate cho các item
    setValidationForInputInCareers($("#table4Sub4DataForm"));

    $(".changeAny").change(function() {
    
    	// Tổng số cuối kỳ 12 tháng
    	// Nam (Nhân viên tổng hợp)
    	$("input[name='dataTable4DtoList[1].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub4=male1]")));
    	// Nữ (Nhân viên tổng hợp)
    	$("input[name='dataTable4DtoList[1].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub4=female1]")));
    	
    	// Nam (Thư ký (tổng hợp))
    	$("input[name='dataTable4DtoList[2].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub4=male2]")));
    	// Nữ (Thư ký (tổng hợp))
    	$("input[name='dataTable4DtoList[2].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub4=female2]")));
    	
    	// Nam (Nhân viên làm công việc bàn giấy)
    	$("input[name='dataTable4DtoList[3].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub4=male3]")));
    	// Nữ (Nhân viên làm công việc bàn giấy)
    	$("input[name='dataTable4DtoList[3].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub4=female3]")));
    	

    	// Nam (Nhân viên thu ngân, thu tiền và các nghề liên quan)
    	$("input[name='dataTable4DtoList[5].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub4=male5]")));
    	// Nữ (Nhân viên thu ngân, thu tiền và các nghề liên quan)
    	$("input[name='dataTable4DtoList[5].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub4=female5]")));

		// Nam (Nhân viên thông tin khách hàng)
    	$("input[name='dataTable4DtoList[6].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub4=male6]")));
    	// Nữ (Nhân viên thông tin khách hàng)
    	$("input[name='dataTable4DtoList[6].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub4=female6]")));
    	
    	
    	// Nam (Nhân viên làm công việc liên quan đến số liệu)
    	$("input[name='dataTable4DtoList[8].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub4=male8]")));
    	// Nữ (Nhân viên làm công việc liên quan đến số liệu)
    	$("input[name='dataTable4DtoList[8].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub4=female8]")));
    	
    	// Nam (Nhân viên ghi chép nguyên vật liệu và phương tiện)
    	$("input[name='dataTable4DtoList[9].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub4=male9]")));
    	// Nữ (Nhân viên ghi chép nguyên vật liệu và phương tiện)
    	$("input[name='dataTable4DtoList[9].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub4=female9]")));
    	
    	// Nam (Nhân viên hỗ trợ văn phòng khác)
    	$("input[name='dataTable4DtoList[11].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub4=male11]")));
    	// Nữ (Nhân viên hỗ trợ văn phòng khác)
    	$("input[name='dataTable4DtoList[11].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub4=female11]")));



		// Nhân viên tổng hợp và nhân viên làm các công việc bàn giấy
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub4=maleFullTimeIndefinite41]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub4=maleFullTimeFixedTerm41]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub4=malePartTimeIndefinite41]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[0].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub4=maleTotalEnd12m41]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub4=femaleFullTimeIndefinite41]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub4=femaleFullTimeFixedTerm41]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub4=femalePartTimeIndefinite41]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[0].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub4=femaleTotalEnd12m41]")));
		
		// Nhân viên dịch vụ khách hàng
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[4].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub4=maleFullTimeIndefinite42]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[4].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub4=maleFullTimeFixedTerm42]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[4].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub4=malePartTimeIndefinite42]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[4].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub4=maleTotalEnd12m42]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[4].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub4=femaleFullTimeIndefinite42]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[4].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub4=femaleFullTimeFixedTerm42]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[4].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub4=femalePartTimeIndefinite42]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[4].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub4=femaleTotalEnd12m42]")));
		
		// Nhân viên ghi chép số liệu và vật liệu
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub4=maleFullTimeIndefinite43]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub4=maleFullTimeFixedTerm43]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub4=malePartTimeIndefinite43]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[7].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub4=maleTotalEnd12m43]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub4=femaleFullTimeIndefinite43]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub4=femaleFullTimeFixedTerm43]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub4=femalePartTimeIndefinite43]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[7].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub4=femaleTotalEnd12m43]")));
		
		// Nhà chuyên môn cấp trung về luật pháp, văn hóa, xã hội
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[10].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub4=maleFullTimeIndefinite44]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[10].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub4=maleFullTimeFixedTerm44]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[10].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub4=malePartTimeIndefinite44]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[10].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub4=maleTotalEnd12m44]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[10].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub4=femaleFullTimeIndefinite44]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[10].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub4=femaleFullTimeFixedTerm44]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[10].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub4=femalePartTimeIndefinite44]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[10].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub4=femaleTotalEnd12m44]")));

    });
    
    // tự động save
	setInterval(function() {
	    let statusSave = $("#statusSave").val();
        let statusInCarrer = $("#statusInCarrer").val();
        if (statusSave == '' && statusInCarrer != '1') {
            let formScreen = $("#table4Sub4DataForm");
            formScreen.attr('action', './save');
            formScreen.submit();
        }
    }, $("#autosaveInterval").val());

});

