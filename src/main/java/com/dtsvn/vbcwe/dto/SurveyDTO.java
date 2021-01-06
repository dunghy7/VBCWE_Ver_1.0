package com.dtsvn.vbcwe.dto;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class SurveyDTO {
	private String fa;
	private String no;
	private String level;
	
	@NotEmpty(message = "Thông tin không được để trống")
	private String answer;
	private String other;
}
