package com.dtsvn.vbcwe.form;

import java.util.List;

import com.dtsvn.vbcwe.dto.GearsDataTable2DTO;

import lombok.Getter;
import lombok.Setter;

/**
 * Form của màn hình VIỆC LÀM
 */
@Getter
@Setter
public class GearsDataTable2Form {

	private List<GearsDataTable2DTO> dataTable2DtoList;
	
	private String status;
	
	private String statusInEmpl;
}
