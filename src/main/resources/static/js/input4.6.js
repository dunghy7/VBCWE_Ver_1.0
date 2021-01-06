$(document).ready(function() {
    // config việc hiển thị message error
    setValidationForInCareers($("#table4Sub6DataForm"));
    // config cấu hình rule và message validate cho các item
    setValidationForInputInCareers($("#table4Sub6DataForm"));

    $(".changeAny").change(function() {
    
    	// Tổng số cuối kỳ 12 tháng
    	// Nam (Lao động trồng trọt và làm vườn theo định hướng thị trường)
    	$("input[name='dataTable4DtoList[1].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub6=male1]")));
    	// Nữ (Lao động trồng trọt và làm vườn theo định hướng thị trường)
    	$("input[name='dataTable4DtoList[1].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub6=female1]")));
    	
    	// Nam (Lao động chăn nuôi gia súc)
    	$("input[name='dataTable4DtoList[2].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub6=male2]")));
    	// Nữ (Lao động chăn nuôi gia súc)
    	$("input[name='dataTable4DtoList[2].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub6=female2]")));
    	
    	// Nam (Lao động trồng trọt và chăn nuôi hỗn hợp)
    	$("input[name='dataTable4DtoList[3].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub6=male3]")));
    	// Nữ (Lao động trồng trọt và chăn nuôi hỗn hợp)
    	$("input[name='dataTable4DtoList[3].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub6=female3]")));


    	// Nam (Lao động trong lâm nghiệp và trong lĩnh vực có liên quan)
    	$("input[name='dataTable4DtoList[5].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub6=male5]")));
    	// Nữ (Lao động trong lâm nghiệp và trong lĩnh vực có liên quan)
    	$("input[name='dataTable4DtoList[5].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub6=female5]")));

		// Nam (Lao động thủy sản, săn bắn)
    	$("input[name='dataTable4DtoList[6].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub6=male6]")));
    	// Nữ (Lao động thủy sản, săn bắn)
    	$("input[name='dataTable4DtoList[6].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub6=female6]")));
    	
    	
    	// Nam (Lao động trồng trọt tự cung tự cấp)
    	$("input[name='dataTable4DtoList[8].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub6=male8]")));
    	// Nữ (Lao động trồng trọt tự cung tự cấp)
    	$("input[name='dataTable4DtoList[8].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub6=female8]")));
    	
    	// Nam (Lao động chăn nuôi tự cung tự cấp)
    	$("input[name='dataTable4DtoList[9].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub6=male9]")));
    	// Nữ (Lao động chăn nuôi tự cung tự cấp)
    	$("input[name='dataTable4DtoList[9].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub6=female9]")));
    	
    	// Nam (Lao động trồng trọt & chăn nuôi tự cung tự cấp hỗn hợp)
    	$("input[name='dataTable4DtoList[10].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub6=male10]")));
    	// Nữ (Lao động trồng trọt & chăn nuôi tự cung tự cấp hỗn hợp)
    	$("input[name='dataTable4DtoList[10].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub6=female10]")));
    	
    	// Nam (Lao động đánh cá, săn bắn, đánh bẫy và thu hái tự cung tự cấp)
    	$("input[name='dataTable4DtoList[11].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub6=male11]")));
    	// Nữ (Lao động đánh cá, săn bắn, đánh bẫy và thu hái tự cung tự cấp)
    	$("input[name='dataTable4DtoList[11].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub6=female11]")));



		// Lao động có kỹ năng trong nông nghiệp theo định hướng thị trường
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub6=maleFullTimeIndefinite61]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub6=maleFullTimeFixedTerm61]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub6=malePartTimeIndefinite61]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[0].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub6=maleTotalEnd12m61]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub6=femaleFullTimeIndefinite61]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub6=femaleFullTimeFixedTerm61]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub6=femalePartTimeIndefinite61]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[0].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub6=femaleTotalEnd12m61]")));
		
		// Lao động có kỹ năng trong lâm nghiệp, thủy sản và săn bắn theo định hướng thị trường
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[4].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub6=maleFullTimeIndefinite62]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[4].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub6=maleFullTimeFixedTerm62]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[4].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub6=malePartTimeIndefinite62]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[4].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub6=maleTotalEnd12m62]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[4].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub6=femaleFullTimeIndefinite62]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[4].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub6=femaleFullTimeFixedTerm62]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[4].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub6=femalePartTimeIndefinite62]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[4].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub6=femaleTotalEnd12m62]")));
		
		// Lao động nông nghiệp, đánh cá, săn bắt và thu hái tự cung tự cấp
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub6=maleFullTimeIndefinite63]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub6=maleFullTimeFixedTerm63]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub6=malePartTimeIndefinite63]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[7].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub6=maleTotalEnd12m63]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub6=femaleFullTimeIndefinite63]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub6=femaleFullTimeFixedTerm63]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub6=femalePartTimeIndefinite63]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[7].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub6=femaleTotalEnd12m63]")));

    });
    
    // tự động save
	setInterval(function() {
	    let statusSave = $("#statusSave").val();
        let statusInCarrer = $("#statusInCarrer").val();
        if (statusSave == '' && statusInCarrer != '1') {
            let formScreen = $("#table4Sub6DataForm");
            formScreen.attr('action', './save');
            formScreen.submit();
        }
    }, $("#autosaveInterval").val());

});

