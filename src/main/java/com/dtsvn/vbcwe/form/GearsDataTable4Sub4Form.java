package com.dtsvn.vbcwe.form;

import java.util.List;

import com.dtsvn.vbcwe.dto.GearsDataTable4DTO;

import lombok.Getter;
import lombok.Setter;

/**
 * Form của màn hình DANH MỤC NGHỀ NGHIỆP - NHÂN VIÊN TRỢ LÝ VĂN PHÒNG
 */
@Getter
@Setter
public class GearsDataTable4Sub4Form {

	private List<GearsDataTable4DTO> dataTable4DtoList;
	
	private String status;
	
	private String statusInCarrer;
}
