package com.dtsvn.vbcwe.util;

import java.util.Map;

public class ExcelRow {
	private int rowNum;
	//map co key la thu tu column, bat dau tu 0; value la gia tri cua cell tuong ung
	private Map<Integer, String> rowValues;
	private int rowStyle;
	
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public Map<Integer, String> getRowValues() {
		return rowValues;
	}
	public void setRowValues(Map<Integer, String> cellValues) {
		this.rowValues = cellValues;
	}
	public int getRowStyle() {
		return rowStyle;
	}
	public void setRowStyle(int rowStyle) {
		this.rowStyle = rowStyle;
	}
	
}
