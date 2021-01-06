$(document).ready(function() {
    // config việc hiển thị message error
    setValidationForInCareers($("#table4Sub9DataForm"));
    // config cấu hình rule và message validate cho các item
    setValidationForInputInCareers($("#table4Sub9DataForm"));

    $(".changeAny").change(function() {
    
    	// Tổng số cuối kỳ 12 tháng
    	// Nam (Người quét dọn và giúp việc gia đình, khách sạn và văn phòng)
    	$("input[name='dataTable4DtoList[1].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub9=male1]")));
    	// Nữ (Người quét dọn và giúp việc gia đình, khách sạn và văn phòng)
    	$("input[name='dataTable4DtoList[1].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub9=female1]")));
    	
    	// Nam (Thợ lau chùi xe cộ, cửa sổ, giặt là và những người làm công việc dọn dẹp bằng tay khác)
    	$("input[name='dataTable4DtoList[2].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub9=male2]")));
    	// Nữ (Thợ lau chùi xe cộ, cửa sổ, giặt là và những người làm công việc dọn dẹp bằng tay khác)
    	$("input[name='dataTable4DtoList[2].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub9=female2]")));


		// Nam (Lao động giản đơn trong nông nghiệp, lâm nghiệp và thủy sản)
    	$("input[name='dataTable4DtoList[4].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub9=male4]")));
    	// Nữ (Lao động giản đơn trong nông nghiệp, lâm nghiệp và thủy sản)
    	$("input[name='dataTable4DtoList[4].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub9=female4]")));


		// Nam (Lao động trong khai thác mỏ và xây dựng)
    	$("input[name='dataTable4DtoList[6].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub9=male6]")));
    	// Nữ (Lao động trong khai thác mỏ và xây dựng)
    	$("input[name='dataTable4DtoList[6].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub9=female6]")));
    	
    	// Nam (Lao động trong công nghiệp sản xuất)
    	$("input[name='dataTable4DtoList[7].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub9=male7]")));
    	// Nữ (Lao động trong công nghiệp sản xuất)
    	$("input[name='dataTable4DtoList[7].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub9=female7]")));
    	
    	// Nam (Lao động giao thông vận tải và kho hàng)
    	$("input[name='dataTable4DtoList[8].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub9=male8]")));
    	// Nữ (Lao động giao thông vận tải và kho hàng)
    	$("input[name='dataTable4DtoList[8].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub9=female8]")));
    	
    	
    	// Nam (Người phụ giúp chuẩn bị thực phẩm)
    	$("input[name='dataTable4DtoList[10].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub9=male10]")));
    	// Nữ (Người phụ giúp chuẩn bị thực phẩm)
    	$("input[name='dataTable4DtoList[10].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub9=female10]")));
    	
    	
    	// Nam (Lao động trên đường phố và lao động có liên quan)
    	$("input[name='dataTable4DtoList[12].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub9=male12]")));
    	// Nữ (Lao động trên đường phố và lao động có liên quan)
    	$("input[name='dataTable4DtoList[12].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub9=female12]")));

		// Nam (Người bán hàng vặt trên đường phố (trừ đồ ăn))
    	$("input[name='dataTable4DtoList[13].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub9=male13]")));
    	// Nữ (Người bán hàng vặt trên đường phố (trừ đồ ăn))
    	$("input[name='dataTable4DtoList[13].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub9=female13]")));

    	
    	// Nam (Người thu dọn vật thải)
    	$("input[name='dataTable4DtoList[15].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub9=male15]")));
    	// Nữ (Người thu dọn vật thải)
    	$("input[name='dataTable4DtoList[15].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub9=female15]")));
    	
    	// Nam (Lao động giản đơn khác)
    	$("input[name='dataTable4DtoList[16].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub9=male16]")));
    	// Nữ (Lao động giản đơn khác)
    	$("input[name='dataTable4DtoList[16].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub9=female16]")));



		// Người quét dọn và giúp việc
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub9=maleFullTimeIndefinite91]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub9=maleFullTimeFixedTerm91]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub9=malePartTimeIndefinite91]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[0].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub9=maleTotalEnd12m91]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub9=femaleFullTimeIndefinite91]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub9=femaleFullTimeFixedTerm91]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub9=femalePartTimeIndefinite91]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[0].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub9=femaleTotalEnd12m91]")));
		
		// Lao động giản đơn trong nông nghiệp, lâm nghiệp và thủy sản
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[3].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub9=maleFullTimeIndefinite92]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[3].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub9=maleFullTimeFixedTerm92]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[3].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub9=malePartTimeIndefinite92]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[3].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub9=maleTotalEnd12m92]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[3].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub9=femaleFullTimeIndefinite92]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[3].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub9=femaleFullTimeFixedTerm92]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[3].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub9=femalePartTimeIndefinite92]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[3].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub9=femaleTotalEnd12m92]")));
		
		// Lao động trong khai thác mỏ, xây dựng, công nghiệp và giao thông vận tải
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[5].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub9=maleFullTimeIndefinite93]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[5].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub9=maleFullTimeFixedTerm93]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[5].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub9=malePartTimeIndefinite93]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[5].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub9=maleTotalEnd12m93]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[5].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub9=femaleFullTimeIndefinite93]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[5].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub9=femaleFullTimeFixedTerm93]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[5].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub9=femalePartTimeIndefinite93]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[5].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub9=femaleTotalEnd12m93]")));
		
		
		// Người phụ giúp chuẩn bị thực phẩm
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[9].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub9=maleFullTimeIndefinite94]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[9].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub9=maleFullTimeFixedTerm94]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[9].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub9=malePartTimeIndefinite94]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[9].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub9=maleTotalEnd12m94]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[9].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub9=femaleFullTimeIndefinite94]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[9].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub9=femaleFullTimeFixedTerm94]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[9].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub9=femalePartTimeIndefinite94]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[9].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub9=femaleTotalEnd12m94]")));
		
		// Lao động trên đường phố và lao động có liên quan đến bán hàng
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub9=maleFullTimeIndefinite95]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub9=maleFullTimeFixedTerm95]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub9=malePartTimeIndefinite95]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[11].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub9=maleTotalEnd12m95]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub9=femaleFullTimeIndefinite95]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub9=femaleFullTimeFixedTerm95]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub9=femalePartTimeIndefinite95]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[11].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub9=femaleTotalEnd12m95]")));
		
		// Người thu dọn vật thải và lao động giản đơn khác
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[14].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub9=maleFullTimeIndefinite96]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[14].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub9=maleFullTimeFixedTerm96]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[14].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub9=malePartTimeIndefinite96]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[14].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub9=maleTotalEnd12m96]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[14].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub9=femaleFullTimeIndefinite96]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[14].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub9=femaleFullTimeFixedTerm96]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[14].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub9=femalePartTimeIndefinite96]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[14].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub9=femaleTotalEnd12m96]")));

    });
    
    // tự động save
	setInterval(function() {
	    let statusSave = $("#statusSave").val();
        let statusInCarrer = $("#statusInCarrer").val();
        if (statusSave == '' && statusInCarrer != '1') {
            let formScreen = $("#table4Sub9DataForm");
            formScreen.attr('action', './save');
            formScreen.submit();
        }
    }, $("#autosaveInterval").val());

});

