package com.dtsvn.vbcwe.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReportEntity {
	
	private String userId;
	
	private String fullName;
	
	private String inputData;
	
	private String filePath;
	
	private Timestamp createdDate;
	
	private String createdBy;
	
	private Timestamp updatedDate;
	
	private String updatedBy;
	
	private String status;
}
