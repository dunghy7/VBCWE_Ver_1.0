package com.dtsvn.vbcwe.form;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class SurveyForm {
	private String fa;
	private String no;
	private String level;
	private String file;
	private String note;
	
	
	@NotBlank(message = "Thông tin không được để trống")
	private String answer;
	private String other;
}
