$(document).ready(function() {
    // config việc hiển thị message error
    setValidationForInCareers($("#table4Sub5DataForm"));
    // config cấu hình rule và message validate cho các item
    setValidationForInputInCareers($("#table4Sub5DataForm"));

    $(".changeAny").change(function() {
    
    	// Tổng số cuối kỳ 12 tháng
    	// Nam (Nhân viên hướng dẫn, tổ chức khách du lịch)
    	$("input[name='dataTable4DtoList[1].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=male1]")));
    	// Nữ (Nhân viên hướng dẫn, tổ chức khách du lịch)
    	$("input[name='dataTable4DtoList[1].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=female1]")));
    	
    	// Nam (Nhân viên đầu bếp)
    	$("input[name='dataTable4DtoList[2].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=male2]")));
    	// Nữ (Nhân viên đầu bếp)
    	$("input[name='dataTable4DtoList[2].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=female2]")));
    	
    	// Nam (Người bồi bàn, người phục vụ ở các quầy rượu)
    	$("input[name='dataTable4DtoList[3].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=male3]")));
    	// Nữ (Người bồi bàn, người phục vụ ở các quầy rượu)
    	$("input[name='dataTable4DtoList[3].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=female3]")));
    	
    	// Nam (Thợ làm đầu, nhân viên làm đẹp)
    	$("input[name='dataTable4DtoList[4].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=male4]")));
    	// Nữ (Thợ làm đầu, nhân viên làm đẹp)
    	$("input[name='dataTable4DtoList[4].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=female4]")));

    	// Nam (Người giám sát tòa nhà, quản gia)
    	$("input[name='dataTable4DtoList[5].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=male5]")));
    	// Nữ (Người giám sát tòa nhà, quản gia)
    	$("input[name='dataTable4DtoList[5].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=female5]")));

		// Nam (Nhân viên dịch vụ cá nhân khác)
    	$("input[name='dataTable4DtoList[6].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=male6]")));
    	// Nữ (Nhân viên dịch vụ cá nhân khác)
    	$("input[name='dataTable4DtoList[6].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=female6]")));
    	
    	
    	// Nam (Người bán hàng trên đường phố và tại chợ)
    	$("input[name='dataTable4DtoList[8].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=male8]")));
    	// Nữ (Người bán hàng trên đường phố và tại chợ)
    	$("input[name='dataTable4DtoList[8].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=female8]")));
    	
    	// Nam (Nhân viên bán hàng trong cửa hàng)
    	$("input[name='dataTable4DtoList[9].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=male9]")));
    	// Nữ (Nhân viên bán hàng trong cửa hàng)
    	$("input[name='dataTable4DtoList[9].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=female9]")));
    	
    	// Nam (Thủ quỹ và nhân viên thu tiền và bán vé)
    	$("input[name='dataTable4DtoList[10].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=male10]")));
    	// Nữ (Thủ quỹ và nhân viên thu tiền và bán vé)
    	$("input[name='dataTable4DtoList[10].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=female10]")));
    	
    	// Nam (Nhân viên bán hàng khác)
    	$("input[name='dataTable4DtoList[11].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=male11]")));
    	// Nữ (Nhân viên bán hàng khác)
    	$("input[name='dataTable4DtoList[11].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=female11]")));

		
		// Nam (Nhân viên chăm sóc trẻ em và người phụ tá cho giáo viên)
    	$("input[name='dataTable4DtoList[13].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=male13]")));
    	// Nữ (Nhân viên chăm sóc trẻ em và người phụ tá cho giáo viên)
    	$("input[name='dataTable4DtoList[13].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=female13]")));
    	
    	// Nam (Hộ lý và nhân viên chăm sóc cá nhân trong các dịch vụ về sức khỏe)
    	$("input[name='dataTable4DtoList[14].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=male14]")));
    	// Nữ (Hộ lý và nhân viên chăm sóc cá nhân trong các dịch vụ về sức khỏe)
    	$("input[name='dataTable4DtoList[14].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=female14]")));
    	
    	
    	// Nam (Nhân viên dịch vụ bảo vệ)
    	$("input[name='dataTable4DtoList[16].tableMale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=male16]")));
    	// Nữ (Nhân viên dịch vụ bảo vệ)
    	$("input[name='dataTable4DtoList[16].tableFemale.totalEnd12m']").val(calTotal($("[rowtable4Sub5=female16]")));




		// Nhân viên dịch vụ cá nhân
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub5=maleFullTimeIndefinite51]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub5=maleFullTimeFixedTerm51]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub5=malePartTimeIndefinite51]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[0].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub5=maleTotalEnd12m51]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub5=femaleFullTimeIndefinite51]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub5=femaleFullTimeFixedTerm51]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[0].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub5=femalePartTimeIndefinite51]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[0].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub5=femaleTotalEnd12m51]")));
		
		// Nhân viên bán hàng
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub5=maleFullTimeIndefinite52]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub5=maleFullTimeFixedTerm52]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub5=malePartTimeIndefinite52]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[7].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub5=maleTotalEnd12m52]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub5=femaleFullTimeIndefinite52]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub5=femaleFullTimeFixedTerm52]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[7].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub5=femalePartTimeIndefinite52]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[7].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub5=femaleTotalEnd12m52]")));
		
		// Nhân viên chăm sóc cá nhân
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[12].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub5=maleFullTimeIndefinite53]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[12].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub5=maleFullTimeFixedTerm53]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[12].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub5=malePartTimeIndefinite53]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[12].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub5=maleTotalEnd12m53]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[12].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub5=femaleFullTimeIndefinite53]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[12].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub5=femaleFullTimeFixedTerm53]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[12].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub5=femalePartTimeIndefinite53]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[12].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub5=femaleTotalEnd12m53]")));
		
		// Nhân viên dịch vụ bảo vệ
		// Nam (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[15].tableMale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub5=maleFullTimeIndefinite54]")));
		// Nam (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[15].tableMale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub5=maleFullTimeFixedTerm54]")));
		// Nam (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[15].tableMale.partTimeIndefinite']").val(calTotal($("[coltable4Sub5=malePartTimeIndefinite54]")));
		// Nam (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[15].tableMale.totalEnd12m']").val(calTotal($("[coltable4Sub5=maleTotalEnd12m54]")));
		// Nữ (Toàn thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[15].tableFemale.fullTimeIndefinite']").val(calTotal($("[coltable4Sub5=femaleFullTimeIndefinite54]")));
		// Nữ (Toàn thời gian, có xác định thời hạn)
		$("input[name='dataTable4DtoList[15].tableFemale.fullTimeFixedTerm']").val(calTotal($("[coltable4Sub5=femaleFullTimeFixedTerm54]")));
		// Nữ (Bán thời gian, không xác định thời hạn)
		$("input[name='dataTable4DtoList[15].tableFemale.partTimeIndefinite']").val(calTotal($("[coltable4Sub5=femalePartTimeIndefinite54]")));
		// Nữ (Tổng số cuối kỳ 12 tháng)
		$("input[name='dataTable4DtoList[15].tableFemale.totalEnd12m']").val(calTotal($("[coltable4Sub5=femaleTotalEnd12m54]")));

    });
    
    // tự động save
	setInterval(function() {
	    let statusSave = $("#statusSave").val();
        let statusInCarrer = $("#statusInCarrer").val();
        if (statusSave == '' && statusInCarrer != '1') {
            let formScreen = $("#table4Sub5DataForm");
            formScreen.attr('action', './save');
            formScreen.submit();
        }
    }, $("#autosaveInterval").val());

});

