package com.dtsvn.vbcwe.dto;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class SurveyDTO {
	private String fa;
	private String no;
	private String level;
	
	@NotEmpty(message = "{msg_error_common_empty}")
	private String answer;
	private String other;
}
