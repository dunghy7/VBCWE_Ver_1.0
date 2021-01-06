$(document).ready(function() {
    // config việc hiển thị message error
    setValidationForInCareers($("#table4Sub2DataForm"));
    // config cấu hình rule và message validate cho các item
    setValidationForInputInCareers($("#table4Sub2DataForm"));

    $(".changeAny").change(function() {
    
    	// Tổng số cuối kỳ 12 tháng
    	// Nam (Nhà chuyên môn về khoa học trái đất và vật lý)
    	$("input[name='dataTable4DtoList[1].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male1]")));
    	// Nữ (Nhà chuyên môn về khoa học trái đất và vật lý)
    	$("input[name='dataTable4DtoList[1].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female1]")));
    	
    	// Nam (Nhà toán học và thống kê học)
    	$("input[name='dataTable4DtoList[2].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male2]")));
    	// Nữ (Nhà toán học và thống kê học)
    	$("input[name='dataTable4DtoList[2].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female2]")));
    	
    	// Nam (Nhà chuyên môn về khoa học sự sống)
    	$("input[name='dataTable4DtoList[3].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male3]")));
    	// Nữ (Nhà chuyên môn về khoa học sự sống)
    	$("input[name='dataTable4DtoList[3].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female3]")));
    	
    	// Nam (Nhà chuyên môn về kỹ thuật (trừ kỹ thuật điện))
    	$("input[name='dataTable4DtoList[4].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male4]")));
    	// Nữ (Nhà chuyên môn về kỹ thuật (trừ kỹ thuật điện))
    	$("input[name='dataTable4DtoList[4].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female4]")));
    	
    	// Nam (Kỹ sư kỹ thuật điện)
    	$("input[name='dataTable4DtoList[5].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male5]")));
    	// Nữ (Kỹ sư kỹ thuật điện)
    	$("input[name='dataTable4DtoList[5].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female5]")));
    	
    	// Nam (Kiến trúc sư, nhà lập quy hoạch, kiểm soát viên và nhà thiết kế)
    	$("input[name='dataTable4DtoList[6].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male6]")));
    	// Nữ (Kiến trúc sư, nhà lập quy hoạch, kiểm soát viên và nhà thiết kế)
    	$("input[name='dataTable4DtoList[6].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female6]")));
    	

    	// Nam (Bác sỹ y khoa)
    	$("input[name='dataTable4DtoList[8].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male8]")));
    	// Nữ (Bác sỹ y khoa)
    	$("input[name='dataTable4DtoList[8].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female8]")));
    	
    	// Nam (Y tá và hộ sinh)
    	$("input[name='dataTable4DtoList[9].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male9]")));
    	// Nữ (Y tá và hộ sinh)
    	$("input[name='dataTable4DtoList[9].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female9]")));
    	
    	// Nam (Nhà chuyên môn về thuốc cổ truyền và thuốc bổ trợ)
    	$("input[name='dataTable4DtoList[10].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male10]")));
    	// Nữ (Nhà chuyên môn về thuốc cổ truyền và thuốc bổ trợ)
    	$("input[name='dataTable4DtoList[10].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female10]")));
    	
    	// Nam (Bác sỹ phụ tá)
    	$("input[name='dataTable4DtoList[11].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male11]")));
    	// Nữ (Bác sỹ phụ tá)
    	$("input[name='dataTable4DtoList[11].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female11]")));
    	
    	// Nam (Bác sỹ thú y)
    	$("input[name='dataTable4DtoList[12].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male12]")));
    	// Nữ (Bác sỹ thú y)
    	$("input[name='dataTable4DtoList[12].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female12]")));
    	
    	// Nam (Nhà chuyên môn về sức khỏe khác)
    	$("input[name='dataTable4DtoList[13].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male13]")));
    	// Nữ (Nhà chuyên môn về sức khỏe khác)
    	$("input[name='dataTable4DtoList[13].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female13]")));
    	
    	
    	// Nam (Giáo viên cao đẳng, đại học và cao học)
    	$("input[name='dataTable4DtoList[15].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male15]")));
    	// Nữ (Giáo viên cao đẳng, đại học và cao học)
    	$("input[name='dataTable4DtoList[15].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female15]")));
    	// Nam (Giáo viên dạy nghề)
    	$("input[name='dataTable4DtoList[16].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male16]")));
    	// Nữ (Giáo viên dạy nghề)
    	$("input[name='dataTable4DtoList[16].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female16]")));
    	// Nam (Giáo viên trung học)
    	$("input[name='dataTable4DtoList[17].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male17]")));
    	// Nữ (Giáo viên trung học)
    	$("input[name='dataTable4DtoList[17].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female17]")));
    	// Nam (Giáo viên tiểu học và mầm non)
    	$("input[name='dataTable4DtoList[18].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male18]")));
    	// Nữ (Giáo viên tiểu học và mầm non)
    	$("input[name='dataTable4DtoList[18].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female18]")));
    	// Nam (Nhà chuyên môn giáo dục khác chưa được phân loại)
    	$("input[name='dataTable4DtoList[19].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male19]")));
    	// Nữ (Nhà chuyên môn giáo dục khác chưa được phân loại)
    	$("input[name='dataTable4DtoList[19].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female19]")));
    	
    	
    	// Nam (Nhà chuyên môn về tài chính)
    	$("input[name='dataTable4DtoList[21].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male21]")));
    	// Nữ (Nhà chuyên môn về tài chính)
    	$("input[name='dataTable4DtoList[21].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female21]")));
    	// Nam (Nhà chuyên môn về quản trị)
    	$("input[name='dataTable4DtoList[22].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male22]")));
    	// Nữ (Nhà chuyên môn về quản trị)
    	$("input[name='dataTable4DtoList[22].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female22]")));
    	// Nam (Nhà chuyên môn về bán hàng, marketing và quan hệ công chúng)
    	$("input[name='dataTable4DtoList[23].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male23]")));
    	// Nữ (Nhà chuyên môn về bán hàng, marketing và quan hệ công chúng)
    	$("input[name='dataTable4DtoList[23].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female23]")));
    	
    	
    	// Nam (Nhà chuyên môn về phân tích và phát triển phần mềm và các ứng dụng)
    	$("input[name='dataTable4DtoList[25].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male25]")));
    	// Nữ (Nhà chuyên môn về phân tích và phát triển phần mềm và các ứng dụng)
    	$("input[name='dataTable4DtoList[25].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female25]")));
    	// Nam (Nhà chuyên môn về cơ sở dữ liệu và mạng)
    	$("input[name='dataTable4DtoList[26].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male26]")));
    	// Nữ (Nhà chuyên môn về cơ sở dữ liệu và mạng)
    	$("input[name='dataTable4DtoList[26].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female26]")));
    	
    	
    	// Nam (Nhà chuyên môn về luật)
    	$("input[name='dataTable4DtoList[28].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male28]")));
    	// Nữ (Nhà chuyên môn về luật)
    	$("input[name='dataTable4DtoList[28].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female28]")));
    	// Nam (Thủ thư, chuyên viên lưu trữ văn thư và người quản lý)
    	$("input[name='dataTable4DtoList[29].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male29]")));
    	// Nữ (Thủ thư, chuyên viên lưu trữ văn thư và người quản lý)
    	$("input[name='dataTable4DtoList[29].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female29]")));
    	// Nam (Nhà chuyên môn về xã hội và tôn giáo)
    	$("input[name='dataTable4DtoList[30].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male30]")));
    	// Nữ (Nhà chuyên môn về xã hội và tôn giáo)
    	$("input[name='dataTable4DtoList[30].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female30]")));
    	// Nam (Nhà văn, nhà báo và nhà ngôn ngữ học)
    	$("input[name='dataTable4DtoList[31].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male31]")));
    	// Nữ (Nhà văn, nhà báo và nhà ngôn ngữ học)
    	$("input[name='dataTable4DtoList[31].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female31]")));
    	// Nam (Nghệ sỹ sáng tạo và trình diễn)
    	$("input[name='dataTable4DtoList[32].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=male32]")));
    	// Nữ (Nghệ sỹ sáng tạo và trình diễn)
    	$("input[name='dataTable4DtoList[32].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub2=female32]")));
    	
    	
		// Nhà chuyên môn trong lĩnh vực khoa học và kỹ thuật
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub2=maleFullTimeIndefinite21]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub2=maleFullTimeFixedTerm21]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub2=malePartTimeIndefinite21]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[0].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub2=maleTotalEnd12m21]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub2=femaleFullTimeIndefinite21]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub2=femaleFullTimeFixedTerm21]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub2=femalePartTimeIndefinite21]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[0].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub2=femaleTotalEnd12m21]")));
		
		// Nhà chuyên môn về sức khỏe
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub2=maleFullTimeIndefinite22]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub2=maleFullTimeFixedTerm22]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub2=malePartTimeIndefinite22]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[7].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub2=maleTotalEnd12m22]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub2=femaleFullTimeIndefinite22]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub2=femaleFullTimeFixedTerm22]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub2=femalePartTimeIndefinite22]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[7].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub2=femaleTotalEnd12m22]")));
		
		// Nhà chuyên môn về giáo dục
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[14].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub2=maleFullTimeIndefinite23]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[14].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub2=maleFullTimeFixedTerm23]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[14].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub2=malePartTimeIndefinite23]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[14].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub2=maleTotalEnd12m23]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[14].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub2=femaleFullTimeIndefinite23]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[14].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub2=femaleFullTimeFixedTerm23]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[14].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub2=femalePartTimeIndefinite23]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[14].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub2=femaleTotalEnd12m23]")));
		
		// Nhà chuyên môn về kinh doanh và quản lý
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[20].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub2=maleFullTimeIndefinite24]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[20].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub2=maleFullTimeFixedTerm24]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[20].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub2=malePartTimeIndefinite24]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[20].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub2=maleTotalEnd12m24]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[20].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub2=femaleFullTimeIndefinite24]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[20].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub2=femaleFullTimeFixedTerm24]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[20].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub2=femalePartTimeIndefinite24]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[20].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub2=femaleTotalEnd12m24]")));

		// Nhà chuyên môn trong lĩnh vực CNTT và truyền thông
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[24].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub2=maleFullTimeIndefinite25]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[24].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub2=maleFullTimeFixedTerm25]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[24].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub2=malePartTimeIndefinite25]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[24].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub2=maleTotalEnd12m25]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[24].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub2=femaleFullTimeIndefinite25]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[24].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub2=femaleFullTimeFixedTerm25]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[24].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub2=femalePartTimeIndefinite25]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[24].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub2=femaleTotalEnd12m25]")));
		
		// Nhà chuyên môn về luật pháp, văn hóa, xã hội
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[27].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub2=maleFullTimeIndefinite26]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[27].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub2=maleFullTimeFixedTerm26]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[27].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub2=malePartTimeIndefinite26]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[27].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub2=maleTotalEnd12m26]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[27].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub2=femaleFullTimeIndefinite26]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[27].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub2=femaleFullTimeFixedTerm26]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[27].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub2=femalePartTimeIndefinite26]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[27].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub2=femaleTotalEnd12m26]")));

    });
    
    // tự động save
	setInterval(function() {
	    let statusSave = $("#statusSave").val();
        let statusInCarrer = $("#statusInCarrer").val();
        if (statusSave == '' && statusInCarrer != '1') {
            let formScreen = $("#table4Sub2DataForm");
            formScreen.attr('action', './save');
            formScreen.submit();
        }
    }, $("#autosaveInterval").val());

});

