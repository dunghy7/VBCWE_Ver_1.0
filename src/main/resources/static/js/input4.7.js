$(document).ready(function() {
    // config việc hiển thị message error
    setValidationForInCareers($("#table4Sub7DataForm"));
    // config cấu hình rule và message validate cho các item
    setValidationForInputInCareers($("#table4Sub7DataForm"));

    $(".changeAny").change(function() {
    
    	// Tổng số cuối kỳ 12 tháng
    	// Nam (Thợ xây dựng khung nhà và lao động có liên quan)
    	$("input[name='dataTable4DtoList[1].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=male1]")));
    	// Nữ (Thợ xây dựng khung nhà và lao động có liên quan)
    	$("input[name='dataTable4DtoList[1].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=female1]")));
    	
    	// Nam (Thợ hoàn thiện và lao động có liên quan)
    	$("input[name='dataTable4DtoList[2].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=male2]")));
    	// Nữ (Thợ hoàn thiện và lao động có liên quan)
    	$("input[name='dataTable4DtoList[2].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=female2]")));
    	
    	// Nam (Thợ sơn, người lau dọn tòa nhà và lao động có liên quan)
    	$("input[name='dataTable4DtoList[3].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=male3]")));
    	// Nữ (Thợ sơn, người lau dọn tòa nhà và lao động có liên quan)
    	$("input[name='dataTable4DtoList[3].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=female3]")));


    	// Nam (Thợ dát kim loại, thợ đúc và thợ hàn và thợ có liên quan)
    	$("input[name='dataTable4DtoList[5].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=male5]")));
    	// Nữ (Thợ dát kim loại, thợ đúc và thợ hàn và thợ có liên quan)
    	$("input[name='dataTable4DtoList[5].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=female5]")));

		// Nam (Thợ rèn, thợ chế tạo các dụng cụ và thợ có liên quan)
    	$("input[name='dataTable4DtoList[6].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=male6]")));
    	// Nữ (Thợ rèn, thợ chế tạo các dụng cụ và thợ có liên quan)
    	$("input[name='dataTable4DtoList[6].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=female6]")));
    	
    	// Nam (Thợ cơ khí và sửa chữa máy móc)
    	$("input[name='dataTable4DtoList[7].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=male7]")));
    	// Nữ (Thợ cơ khí và sửa chữa máy móc)
    	$("input[name='dataTable4DtoList[7].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=female7]")));
    	
    	
    	// Nam (Thợ thủ công)
    	$("input[name='dataTable4DtoList[9].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=male9]")));
    	// Nữ (Thợ thủ công)
    	$("input[name='dataTable4DtoList[9].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=female9]")));
    	
    	// Nam (Thợ liên quan đến in)
    	$("input[name='dataTable4DtoList[10].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=male10]")));
    	// Nữ (Thợ liên quan đến in)
    	$("input[name='dataTable4DtoList[10].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=female10]")));
    	
    	
    	// Nam (Thợ lắp đặt và sửa chữa thiết bị điện)
    	$("input[name='dataTable4DtoList[12].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=male12]")));
    	// Nữ (Thợ lắp đặt và sửa chữa thiết bị điện)
    	$("input[name='dataTable4DtoList[12].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=female12]")));

		// Nam (Thợ lắp đặt và thợ sửa chữa điện tử viễn thông)
    	$("input[name='dataTable4DtoList[13].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=male13]")));
    	// Nữ (Thợ lắp đặt và thợ sửa chữa điện tử viễn thông)
    	$("input[name='dataTable4DtoList[13].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=female13]")));
    	
    	
    	// Nam (Thợ chế biến thực phẩm và các thợ khác có liên quan)
    	$("input[name='dataTable4DtoList[15].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=male15]")));
    	// Nữ (Thợ chế biến thực phẩm và các thợ khác có liên quan)
    	$("input[name='dataTable4DtoList[15].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=female15]")));
    	
    	// Nam (Thợ xử lý gỗ, thợ sản xuất đồ gỗ và các thợ có liên quan)
    	$("input[name='dataTable4DtoList[16].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=male16]")));
    	// Nữ (Thợ xử lý gỗ, thợ sản xuất đồ gỗ và các thợ có liên quan)
    	$("input[name='dataTable4DtoList[16].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=female16]")));
    	
    	// Nam (Thợ may mặc và các thợ có liên quan)
    	$("input[name='dataTable4DtoList[17].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=male17]")));
    	// Nữ (Thợ may mặc và các thợ có liên quan)
    	$("input[name='dataTable4DtoList[17].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=female17]")));
    	
    	// Nam (Thợ thủ công khác và các thợ có liên quan)
    	$("input[name='dataTable4DtoList[18].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=male18]")));
    	// Nữ (Thợ thủ công khác và các thợ có liên quan)
    	$("input[name='dataTable4DtoList[18].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub7=female18]")));



		// Lao động xây dựng và lao động các ngành nghề có liên quan, không bao gồm thợ điện
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub7=maleFullTimeIndefinite71]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub7=maleFullTimeFixedTerm71]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub7=malePartTimeIndefinite71]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[0].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub7=maleTotalEnd12m71]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub7=femaleFullTimeIndefinite71]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub7=femaleFullTimeFixedTerm71]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub7=femalePartTimeIndefinite71]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[0].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub7=femaleTotalEnd12m71]")));
		
		// Thợ kim loại, thợ máy và thợ có liên quan
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[4].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub7=maleFullTimeIndefinite72]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[4].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub7=maleFullTimeFixedTerm72]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[4].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub7=malePartTimeIndefinite72]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[4].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub7=maleTotalEnd12m72]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[4].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub7=femaleFullTimeIndefinite72]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[4].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub7=femaleFullTimeFixedTerm72]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[4].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub7=femalePartTimeIndefinite72]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[4].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub7=femaleTotalEnd12m72]")));
		
		// Thợ thủ công và thợ liên quan đến in ấn
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[8].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub7=maleFullTimeIndefinite73]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[8].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub7=maleFullTimeFixedTerm73]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[8].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub7=malePartTimeIndefinite73]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[8].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub7=maleTotalEnd12m73]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[8].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub7=femaleFullTimeIndefinite73]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[8].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub7=femaleFullTimeFixedTerm73]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[8].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub7=femalePartTimeIndefinite73]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[8].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub7=femaleTotalEnd12m73]")));
		
		// Thợ điện và thợ điện tử
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub7=maleFullTimeIndefinite74]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub7=maleFullTimeFixedTerm74]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub7=malePartTimeIndefinite74]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[11].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub7=maleTotalEnd12m74]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub7=femaleFullTimeIndefinite74]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub7=femaleFullTimeFixedTerm74]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[11].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub7=femalePartTimeIndefinite74]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[11].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub7=femaleTotalEnd12m74]")));
		
		// Thợ chế biến thực phẩm, công việc đồ gỗ, may mặc và nghề thủ công khác và thợ khác có liên quan
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[14].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub7=maleFullTimeIndefinite75]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[14].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub7=maleFullTimeFixedTerm75]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[14].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub7=malePartTimeIndefinite75]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[14].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub7=maleTotalEnd12m75]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[14].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub7=femaleFullTimeIndefinite75]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[14].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub7=femaleFullTimeFixedTerm75]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[14].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub7=femalePartTimeIndefinite75]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[14].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub7=femaleTotalEnd12m75]")));

    });
    
    // tự động save
	setInterval(function() {
	    let statusSave = $("#statusSave").val();
        let statusInCarrer = $("#statusInCarrer").val();
        if (statusSave == '' && statusInCarrer != '1') {
            let formScreen = $("#table4Sub7DataForm");
            formScreen.attr('action', './save');
            formScreen.submit();
        }
    }, $("#autosaveInterval").val());

});

