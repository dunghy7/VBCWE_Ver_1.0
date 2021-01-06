package com.dtsvn.vbcwe.form;

import java.util.List;

import com.dtsvn.vbcwe.dto.GearsDataTable3DTO;

import lombok.Getter;
import lombok.Setter;

/**
 * Form của màn hình BAN LÃNH ĐẠO
 */
@Getter
@Setter
public class GearsDataTable3Form {

	private List<GearsDataTable3DTO> dataTable3DtoList;
	
	private String status;
	
	private String statusInBrdComp;
}
