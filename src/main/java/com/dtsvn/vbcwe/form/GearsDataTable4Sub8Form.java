package com.dtsvn.vbcwe.form;

import java.util.List;

import com.dtsvn.vbcwe.dto.GearsDataTable4DTO;

import lombok.Getter;
import lombok.Setter;

/**
 * Form của màn hình DANH MỤC NGHỀ NGHIỆP - THỢ LẮP RÁP VÀ VẬN HÀNH MÁY MÓC, THIẾT BỊ
 */
@Getter
@Setter
public class GearsDataTable4Sub8Form {

	private List<GearsDataTable4DTO> dataTable4DtoList;
	
	private String status;
	
	private String statusInCarrer;
}
