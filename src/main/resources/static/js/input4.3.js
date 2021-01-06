$(document).ready(function() {
    // config việc hiển thị message error
    setValidationForInCareers($("#table4Sub3DataForm"));
    // config cấu hình rule và message validate cho các item
    setValidationForInputInCareers($("#table4Sub3DataForm"));

    $(".changeAny").change(function() {
    
    	// Tổng số cuối kỳ 12 tháng
    	// Nam (Kỹ thuật viên khoa học vật lý và kỹ thuật)
    	$("input[name='dataTable4DtoList[1].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=male1]")));
    	// Nữ (Kỹ thuật viên khoa học vật lý và kỹ thuật)
    	$("input[name='dataTable4DtoList[1].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=female1]")));
    	
    	// Nam (Giám sát viên khai thác mỏ, chế biến và xây dựng)
    	$("input[name='dataTable4DtoList[2].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=male2]")));
    	// Nữ (Giám sát viên khai thác mỏ, chế biến và xây dựng)
    	$("input[name='dataTable4DtoList[2].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=female2]")));
    	
    	// Nam (Kỹ thuật viên điều khiển quy trình)
    	$("input[name='dataTable4DtoList[3].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=male3]")));
    	// Nữ (Kỹ thuật viên điều khiển quy trình)
    	$("input[name='dataTable4DtoList[3].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=female3]")));
    	
    	// Nam (Kỹ thuật viên kiểm soát, vận hành và điều khiển quy trình)
    	$("input[name='dataTable4DtoList[4].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=male4]")));
    	// Nữ (Kỹ thuật viên kiểm soát, vận hành và điều khiển quy trình)
    	$("input[name='dataTable4DtoList[4].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=female4]")));
    	
    	// Nam (Kỹ thuật viên và kiểm soát viên tàu thuỷ và phương tiện bay)
    	$("input[name='dataTable4DtoList[5].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=male5]")));
    	// Nữ (Kỹ thuật viên và kiểm soát viên tàu thuỷ và phương tiện bay)
    	$("input[name='dataTable4DtoList[5].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=female5]")));


		// Nam (Kỹ thuật viên y tế và dược)
    	$("input[name='dataTable4DtoList[7].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=male7]")));
    	// Nữ (Kỹ thuật viên y tế và dược)
    	$("input[name='dataTable4DtoList[7].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=female7]")));
    	
    	// Nam (Y tá, kỹ thuật viên chăm sóc bệnh nhân và hộ sinh)
    	$("input[name='dataTable4DtoList[8].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=male8]")));
    	// Nữ (Y tá, kỹ thuật viên chăm sóc bệnh nhân và hộ sinh)
    	$("input[name='dataTable4DtoList[8].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=female8]")));
    	
    	// Nam (Kỹ thuật viên y học cổ truyền và bổ trợ)
    	$("input[name='dataTable4DtoList[9].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=male9]")));
    	// Nữ (Kỹ thuật viên y học cổ truyền và bổ trợ)
    	$("input[name='dataTable4DtoList[9].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=female9]")));
    	
    	// Nam (Kỹ thuật viên thú y và phụ tá)
    	$("input[name='dataTable4DtoList[10].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=male10]")));
    	// Nữ (Kỹ thuật viên thú y và phụ tá)
    	$("input[name='dataTable4DtoList[10].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=female10]")));
    	
    	// Nam (Kỹ thuật viên sức khỏe khác)
    	$("input[name='dataTable4DtoList[11].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=male11]")));
    	// Nữ (Kỹ thuật viên sức khỏe khác)
    	$("input[name='dataTable4DtoList[11].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=female11]")));


    	// Nam (Kỹ thuật viên về toán ứng dụng và tài chính)
    	$("input[name='dataTable4DtoList[13].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=male13]")));
    	// Nữ (Kỹ thuật viên về toán ứng dụng và tài chính)
    	$("input[name='dataTable4DtoList[13].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=female13]")));
    	
    	// Nam (Nhà đại lý và môi giới bán hàng và mua, bán)
    	$("input[name='dataTable4DtoList[14].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=male14]")));
    	// Nữ (Nhà đại lý và môi giới bán hàng và mua, bán)
    	$("input[name='dataTable4DtoList[14].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=female14]")));
    	
    	// Nam (Nhân viên/đại lý dịch vụ kinh doanh)
    	$("input[name='dataTable4DtoList[15].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=male15]")));
    	// Nữ (Nhân viên/đại lý dịch vụ kinh doanh)
    	$("input[name='dataTable4DtoList[15].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=female15]")));
    	
    	// Nam (Thư ký hành chính và nhân viên chuyên môn khác)
    	$("input[name='dataTable4DtoList[16].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=male16]")));
    	// Nữ (Thư ký hành chính và nhân viên chuyên môn khác)
    	$("input[name='dataTable4DtoList[16].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=female16]")));
    	
    	// Nam (Kỹ thuật viên điều tiết của Chính phủ)
    	$("input[name='dataTable4DtoList[17].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=male17]")));
    	// Nữ (Kỹ thuật viên điều tiết của Chính phủ)
    	$("input[name='dataTable4DtoList[17].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=female17]")));


    	// Nam (Nhà chuyên môn cấp trung về luật pháp, xã hội và tôn giáo)
    	$("input[name='dataTable4DtoList[19].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=male19]")));
    	// Nữ (Nhà chuyên môn cấp trung về luật pháp, xã hội và tôn giáo)
    	$("input[name='dataTable4DtoList[19].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=female19]")));
    	
    	// Nam (Người làm trong lĩnh vực thể thao và tập luyện)
    	$("input[name='dataTable4DtoList[20].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=male20]")));
    	// Nữ (Người làm trong lĩnh vực thể thao và tập luyện)
    	$("input[name='dataTable4DtoList[20].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=female20]")));
    	
    	// Nam (Nhà chuyên môn cấp trung về mỹ thuật, văn hóa và nấu ăn)
    	$("input[name='dataTable4DtoList[21].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=male21]")));
    	// Nữ (Nhà chuyên môn cấp trung về mỹ thuật, văn hóa và nấu ăn)
    	$("input[name='dataTable4DtoList[21].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=female21]")));
    	
    	
    	// Nam (Kỹ thuật viên hỗ trợ người sử dụng và vận hành công nghệ thông tin và truyền thông)
    	$("input[name='dataTable4DtoList[23].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=male23]")));
    	// Nữ (Kỹ thuật viên hỗ trợ người sử dụng và vận hành công nghệ thông tin và truyền thông)
    	$("input[name='dataTable4DtoList[23].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=female23]")));
    	
    	// Nam (Nhà chuyên môn về phân tích và phát triển phần mềm và các ứng dụng)
    	$("input[name='dataTable4DtoList[24].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=male24]")));
    	// Nữ (Nhà chuyên môn về phân tích và phát triển phần mềm và các ứng dụng)
    	$("input[name='dataTable4DtoList[24].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub3=female24]")));
    	

    	
		// Kỹ thuật viên khoa học và kỹ thuật
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub3=maleFullTimeIndefinite31]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub3=maleFullTimeFixedTerm31]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub3=malePartTimeIndefinite31]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[0].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub3=maleTotalEnd12m31]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub3=femaleFullTimeIndefinite31]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub3=femaleFullTimeFixedTerm31]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub3=femalePartTimeIndefinite31]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[0].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub3=femaleTotalEnd12m31]")));
		
		// Kỹ thuật viên sức khỏe
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[6].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub3=maleFullTimeIndefinite32]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[6].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub3=maleFullTimeFixedTerm32]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[6].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub3=malePartTimeIndefinite32]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[6].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub3=maleTotalEnd12m32]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[6].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub3=femaleFullTimeIndefinite32]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[6].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub3=femaleFullTimeFixedTerm32]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[6].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub3=femalePartTimeIndefinite32]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[6].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub3=femaleTotalEnd12m32]")));
		
		// Kỹ thuật viên về kinh doanh và quản lý
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[12].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub3=maleFullTimeIndefinite33]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[12].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub3=maleFullTimeFixedTerm33]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[12].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub3=malePartTimeIndefinite33]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[12].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub3=maleTotalEnd12m33]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[12].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub3=femaleFullTimeIndefinite33]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[12].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub3=femaleFullTimeFixedTerm33]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[12].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub3=femalePartTimeIndefinite33]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[12].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub3=femaleTotalEnd12m33]")));
		
		// Nhà chuyên môn cấp trung về luật pháp, văn hóa, xã hội
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[18].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub3=maleFullTimeIndefinite34]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[18].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub3=maleFullTimeFixedTerm34]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[18].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub3=malePartTimeIndefinite34]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[18].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub3=maleTotalEnd12m34]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[18].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub3=femaleFullTimeIndefinite34]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[18].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub3=femaleFullTimeFixedTerm34]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[18].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub3=femalePartTimeIndefinite34]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[18].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub3=femaleTotalEnd12m34]")));

		// Kỹ thuật viên thông tin và truyền thông
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[22].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub3=maleFullTimeIndefinite35]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[22].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub3=maleFullTimeFixedTerm35]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[22].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub3=malePartTimeIndefinite35]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[22].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub3=maleTotalEnd12m35]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[22].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub3=femaleFullTimeIndefinite35]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[22].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub3=femaleFullTimeFixedTerm35]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[22].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub3=femalePartTimeIndefinite35]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[22].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub3=femaleTotalEnd12m35]")));

    });
    
    // tự động save
	setInterval(function() {
	    let statusSave = $("#statusSave").val();
        let statusInCarrer = $("#statusInCarrer").val();
        if (statusSave == '' && statusInCarrer != '1') {
            let formScreen = $("#table4Sub3DataForm");
            formScreen.attr('action', './save');
            formScreen.submit();
        }
    }, $("#autosaveInterval").val());

});

