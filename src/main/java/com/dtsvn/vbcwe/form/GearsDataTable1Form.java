package com.dtsvn.vbcwe.form;

import java.util.List;

import com.dtsvn.vbcwe.dto.GearsDataTable1DTO;

import lombok.Getter;
import lombok.Setter;

/**
 * Form của màn hình DỊCH CHUYỂN LAO ĐỘNG
 */
@Getter
@Setter
public class GearsDataTable1Form {

	private List<GearsDataTable1DTO> dataTable1DtoList;
	
	private String status;
	
	private String statusInEmplNvmt;
}
