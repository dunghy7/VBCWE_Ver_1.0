package com.dtsvn.vbcwe.form;

import java.util.List;

import com.dtsvn.vbcwe.dto.GearsDataTable4DTO;

import lombok.Getter;
import lombok.Setter;

/**
 * Form của màn hình DANH MỤC NGHỀ NGHIỆP - LAO ĐỘNG CÓ KỸ NĂNG TRONG NÔNG NGHIỆP, LÂM NGHIỆP VÀ THỦY SẢN
 */
@Getter
@Setter
public class GearsDataTable4Sub6Form {

	private List<GearsDataTable4DTO> dataTable4DtoList;
	
	private String status;
	
	private String statusInCarrer;
}
