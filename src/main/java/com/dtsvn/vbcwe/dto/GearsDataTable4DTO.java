package com.dtsvn.vbcwe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GearsDataTable4DTO {
	
	/**
	 * Chức danh trong công ty
	 */
	private String level;

	/**
	 * Thuộc loại kiểu nào của chức danh trong công ty
	 */
	private String parentLevel;

	/**
	 * Nam
	 */
	private GearsDataTable4SubDTO tableMale;

	/**
	 * Nữ
	 */
	private GearsDataTable4SubDTO tableFemale;

}
