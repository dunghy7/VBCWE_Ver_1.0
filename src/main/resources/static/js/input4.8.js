$(document).ready(function() {
    // config việc hiển thị message error
    setValidationForInCareers($("#table4Sub8DataForm"));
    // config cấu hình rule và message validate cho các item
    setValidationForInputInCareers($("#table4Sub8DataForm"));

    $(".changeAny").change(function() {
    
    	// Tổng số cuối kỳ 12 tháng
    	// Nam (Thợ vận hành thiết bị xử lý mỏ và khoáng)
    	$("input[name='dataTable4DtoList[1].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=male1]")));
    	// Nữ (Thợ vận hành thiết bị xử lý mỏ và khoáng)
    	$("input[name='dataTable4DtoList[1].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=female1]")));
    	
    	// Nam (Thợ vận hành thiết bị xử lý và hoàn thiện kim loại)
    	$("input[name='dataTable4DtoList[2].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=male2]")));
    	// Nữ (Thợ vận hành thiết bị xử lý và hoàn thiện kim loại)
    	$("input[name='dataTable4DtoList[2].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=female2]")));
    	
    	// Nam (Thợ vận hành máy móc, thiết bị sản xuất hóa học và sản xuất sản phẩm phim ảnh)
    	$("input[name='dataTable4DtoList[3].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=male3]")));
    	// Nữ (Thợ vận hành máy móc, thiết bị sản xuất hóa học và sản xuất sản phẩm phim ảnh)
    	$("input[name='dataTable4DtoList[3].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=female3]")));

		// Nam (Thợ vận hành máy sản xuất sản phẩm giấy, nhựa và cao su)
    	$("input[name='dataTable4DtoList[4].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=male4]")));
    	// Nữ (Thợ vận hành máy sản xuất sản phẩm giấy, nhựa và cao su)
    	$("input[name='dataTable4DtoList[4].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=female4]")));

    	// Nam (Thợ vận hành máy sản xuất nguyên liệu dệt, da lông thú và da thuộc)
    	$("input[name='dataTable4DtoList[5].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=male5]")));
    	// Nữ (Thợ vận hành máy sản xuất nguyên liệu dệt, da lông thú và da thuộc)
    	$("input[name='dataTable4DtoList[5].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=female5]")));

		// Nam (Thợ vận hành máy sản xuất thực phẩm và các thợ có liên quan)
    	$("input[name='dataTable4DtoList[6].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=male6]")));
    	// Nữ (Thợ vận hành máy sản xuất thực phẩm và các thợ có liên quan)
    	$("input[name='dataTable4DtoList[6].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=female6]")));
    	
    	// Nam (Thợ vận hành thiết bị chế biến gỗ và chế tạo giấy)
    	$("input[name='dataTable4DtoList[7].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=male7]")));
    	// Nữ (Thợ vận hành thiết bị chế biến gỗ và chế tạo giấy)
    	$("input[name='dataTable4DtoList[7].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=female7]")));
    	
    	// Nam (Thợ vận hành máy móc thiết bị khác)
    	$("input[name='dataTable4DtoList[8].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=male8]")));
    	// Nữ (Thợ vận hành máy móc thiết bị khác)
    	$("input[name='dataTable4DtoList[8].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=female8]")));
    	
    	
    	// Nam (Thợ lắp ráp)
    	$("input[name='dataTable4DtoList[10].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=male10]")));
    	// Nữ (Thợ lắp ráp
    	$("input[name='dataTable4DtoList[10].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=female10]")));
    	
    	
    	// Nam (Lái các phương tiện vận chuyển trên đường ray và các công nhân có liên quan)
    	$("input[name='dataTable4DtoList[12].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=male12]")));
    	// Nữ (Lái các phương tiện vận chuyển trên đường ray và các công nhân có liên quan)
    	$("input[name='dataTable4DtoList[12].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=female12]")));

		// Nam (Lái xe khách, xe tải và xe máy)
    	$("input[name='dataTable4DtoList[13].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=male13]")));
    	// Nữ (Lái xe khách, xe tải và xe máy)
    	$("input[name='dataTable4DtoList[13].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=female13]")));
    	
    	// Nam (Lái xe tải hạng vừa, hạng nặng và xe buýt)
    	$("input[name='dataTable4DtoList[14].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=male14]")));
    	// Nữ (Lái xe tải hạng vừa, hạng nặng và xe buýt)
    	$("input[name='dataTable4DtoList[14].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=female14]")));
    	
    	// Nam (Thợ vận hành thiết bị chuyển động)
    	$("input[name='dataTable4DtoList[15].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=male15]")));
    	// Nữ (Thợ vận hành thiết bị chuyển động)
    	$("input[name='dataTable4DtoList[15].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=female15]")));
    	
    	// Nam (Thủy thủ trên tàu và những thợ có liên quan)
    	$("input[name='dataTable4DtoList[16].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=male16]")));
    	// Nữ (Thủy thủ trên tàu và những thợ có liên quan)
    	$("input[name='dataTable4DtoList[16].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub8=female16]")));



		// Thợ vận hành máy móc và thiết bị cố định
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub8=maleFullTimeIndefinite81]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub8=maleFullTimeFixedTerm81]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub8=malePartTimeIndefinite81]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[0].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub8=maleTotalEnd12m81]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub8=femaleFullTimeIndefinite81]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub8=femaleFullTimeFixedTerm81]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub8=femalePartTimeIndefinite81]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[0].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub8=femaleTotalEnd12m81]")));
		
		// Thợ lắp ráp
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[9].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub8=maleFullTimeIndefinite82]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[9].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub8=maleFullTimeFixedTerm82]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[9].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub8=malePartTimeIndefinite82]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[9].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub8=maleTotalEnd12m82]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[9].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub8=femaleFullTimeIndefinite82]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[9].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub8=femaleFullTimeFixedTerm82]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[9].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub8=femalePartTimeIndefinite82]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[9].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub8=femaleTotalEnd12m82]")));
		
		// Lái xe và thợ vận hành thiết bị chuyển động
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub8=maleFullTimeIndefinite83]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub8=maleFullTimeFixedTerm83]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub8=malePartTimeIndefinite83]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[11].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub8=maleTotalEnd12m83]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub8=femaleFullTimeIndefinite83]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub8=femaleFullTimeFixedTerm83]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub8=femalePartTimeIndefinite83]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[11].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub8=femaleTotalEnd12m83]")));

    });
    
    // tự động save
	setInterval(function() {
	    let statusSave = $("#statusSave").val();
        let statusInCarrer = $("#statusInCarrer").val();
        if (statusSave == '' && statusInCarrer != '1') {
            let formScreen = $("#table4Sub8DataForm");
            formScreen.attr('action', './save');
            formScreen.submit();
        }
    }, $("#autosaveInterval").val());

});

