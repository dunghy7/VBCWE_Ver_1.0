package com.dtsvn.vbcwe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GearsDataTable4SubDTO {
	
	/**
	 * Toàn thời gian, không xác định thời hạn
	 */
	private String fullTimeIndefinite;
	
	/**
	 * Toàn thời gian, có xác định thời hạn
	 */
	private String fullTimeFixedTerm;
	
	/**
	 * Bán thời gian, không xác định thời hạn
	 */
	private String partTimeIndefinite;
	
	/**
	 * Tổng số cuối kỳ 12 tháng
	 */
	private String totalEnd12m;

}