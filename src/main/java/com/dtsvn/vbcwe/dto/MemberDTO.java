package com.dtsvn.vbcwe.dto;

import lombok.Data;

@Data
public class MemberDTO {
	private String userId;
	private String inputData;
	private String createBy;
	private String status;
	private String outputFilePath;
	
	public MemberDTO() {
	}

	public MemberDTO(String userId, String survey, String createBy, String status) {
		this.userId = userId;
		this.inputData = survey;
		this.createBy = createBy;
		this.status = status;
	}
}
