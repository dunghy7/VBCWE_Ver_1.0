$(document).ready(function() {
    // config việc hiển thị message error
    setValidationForInCareers($("#table4Sub1DataForm"));
    // config cấu hình rule và message validate cho các item
    setValidationForInputInCareers($("#table4Sub1DataForm"));

    $(".changeAny").change(function() {
    
    	// Tổng số cuối kỳ 12 tháng
    	// Nam (Các nhà lập pháp và quan chức cấp cao)
    	$("input[name='dataTable4DtoList[1].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub1=male1]")));
    	// Nữ (Các nhà lập pháp và quan chức cấp cao)
    	$("input[name='dataTable4DtoList[1].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub1=female1]")));
    	
    	// Nam (Giám đốc điều hành)
    	$("input[name='dataTable4DtoList[2].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub1=male2]")));
    	// Nữ (Giám đốc điều hành)
    	$("input[name='dataTable4DtoList[2].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub1=female2]")));
    	
    	// Nam (Giám đốc/Quản lý phát triển dịch vụ kinh doanh và hành chính)
    	$("input[name='dataTable4DtoList[4].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub1=male4]")));
    	// Nữ (Giám đốc/Quản lý phát triển dịch vụ kinh doanh và hành chính)
    	$("input[name='dataTable4DtoList[4].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub1=female4]")));
    	
    	// Nam (Giám đốc/Quản lý kinh doanh, marketing và phát triển)
    	$("input[name='dataTable4DtoList[5].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub1=male5]")));
    	// Nữ (Giám đốc/Quản lý kinh doanh, marketing và phát triển)
    	$("input[name='dataTable4DtoList[5].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub1=female5]")));
    	
    	// Nam (Giám đốc/Quản lý nông nghiệp, lâm nghiệp và thủy sản)
    	$("input[name='dataTable4DtoList[7].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub1=male7]")));
    	// Nữ (Giám đốc/Quản lý nông nghiệp, lâm nghiệp và thủy sản	)
    	$("input[name='dataTable4DtoList[7].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub1=female7]")));
    	
    	// Nam (Giám đốc/Quản lý sản xuất, khai thác mỏ, xây dựng, và phân phối)
    	$("input[name='dataTable4DtoList[8].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub1=male8]")));
    	// Nữ (Giám đốc/Quản lý sản xuất, khai thác mỏ, xây dựng, và phân phối)
    	$("input[name='dataTable4DtoList[8].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub1=female8]")));
    	
    	// Nam (Giám đốc/Quản lý thông tin và truyền thông)
    	$("input[name='dataTable4DtoList[9].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub1=male9]")));
    	// Nữ (Giám đốc/Quản lý thông tin và truyền thông)
    	$("input[name='dataTable4DtoList[9].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub1=female9]")));
    	
    	// Nam (Giám đốc/Quản lý các dịch vụ chuyên nghiệp)
    	$("input[name='dataTable4DtoList[10].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub1=male10]")));
    	// Nữ (Giám đốc/Quản lý các dịch vụ chuyên nghiệp)
    	$("input[name='dataTable4DtoList[10].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub1=female10]")));
    	
    	// Nam (Giám đốc/Quản lý khách sạn, nhà hàng)
    	$("input[name='dataTable4DtoList[12].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub1=male12]")));
    	// Nữ (Giám đốc/Quản lý khách sạn, nhà hàng)
    	$("input[name='dataTable4DtoList[12].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub1=female12]")));
    	
    	// Nam (Bán buôn, bán lẻ)
    	$("input[name='dataTable4DtoList[13].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub1=male13]")));
    	// Nữ (Bán buôn, bán lẻ)
    	$("input[name='dataTable4DtoList[13].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub1=female13]")));
    	
    	// Nam (Các đơn vị sản xuất và dịch vụ còn lại chưa được phân vào đâu)
    	$("input[name='dataTable4DtoList[14].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub1=male14]")));
    	// Nữ (Các đơn vị sản xuất và dịch vụ còn lại chưa được phân vào đâu)
    	$("input[name='dataTable4DtoList[14].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub1=female14]")));


		// Giám đốc điều hành, quan chức cấp cao và các nhà lập pháp
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub1=maleFullTimeIndefinite11]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub1=maleFullTimeFixedTerm11]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub1=malePartTimeIndefinite11]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[0].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub1=maleTotalEnd12m11]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub1=femaleFullTimeIndefinite11]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub1=femaleFullTimeFixedTerm11]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub1=femalePartTimeIndefinite11]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[0].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub1=femaleTotalEnd12m11]")));
		
		// Giám đốc/Quản lý hành chính và thương mại
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[3].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub1=maleFullTimeIndefinite12]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[3].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub1=maleFullTimeFixedTerm12]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[3].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub1=malePartTimeIndefinite12]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[3].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub1=maleTotalEnd12m12]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[3].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub1=femaleFullTimeIndefinite12]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[3].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub1=femaleFullTimeFixedTerm12]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[3].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub1=femalePartTimeIndefinite12]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[3].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub1=femaleTotalEnd12m12]")));
		
		// Giám đốc/Quản lý sản xuất và các dịch vụ chuyên môn
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[6].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub1=maleFullTimeIndefinite13]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[6].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub1=maleFullTimeFixedTerm13]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[6].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub1=malePartTimeIndefinite13]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[6].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub1=maleTotalEnd12m13]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[6].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub1=femaleFullTimeIndefinite13]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[6].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub1=femaleFullTimeFixedTerm13]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[6].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub1=femalePartTimeIndefinite13]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[6].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub1=femaleTotalEnd12m13]")));
		
		// Giám đốc/Quản lý khách sạn, bán lẻ và dịch vụ khác
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub1=maleFullTimeIndefinite14]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub1=maleFullTimeFixedTerm14]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub1=malePartTimeIndefinite14]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[11].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub1=maleTotalEnd12m14]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub1=femaleFullTimeIndefinite14]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub1=femaleFullTimeFixedTerm14]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub1=femalePartTimeIndefinite14]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[11].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub1=femaleTotalEnd12m14]")));

    });
    
    // tự động save
	setInterval(function() {
	    let statusSave = $("#statusSave").val();
	    let statusInCarrer = $("#statusInCarrer").val();
        if (statusSave == '' && statusInCarrer != '1') {
            let formScreen = $("#table4Sub1DataForm");
            formScreen.attr('action', './save');
            formScreen.submit();
        }
    }, $("#autosaveInterval").val());

});

