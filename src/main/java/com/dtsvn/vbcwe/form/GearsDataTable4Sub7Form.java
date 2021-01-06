package com.dtsvn.vbcwe.form;

import java.util.List;

import com.dtsvn.vbcwe.dto.GearsDataTable4DTO;

import lombok.Getter;
import lombok.Setter;

/**
 * Form của màn hình DANH MỤC NGHỀ NGHIỆP - LAO ĐỘNG THỦ CÔNG VÀ CÁC NGHỀ NGHIỆP CÓ LIÊN QUAN KHÁC
 */
@Getter
@Setter
public class GearsDataTable4Sub7Form {

	private List<GearsDataTable4DTO> dataTable4DtoList;
	
	private String status;
	
	private String statusInCarrer;
}
