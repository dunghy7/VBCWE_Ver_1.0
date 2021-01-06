package com.dtsvn.vbcwe.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BenchmarkEntity {
	
	private String inputData;
	
	private Timestamp createdDate;
	
	private String createdBy;
	
	private Timestamp updatedDate;
	
	private String updatedBy;
}
