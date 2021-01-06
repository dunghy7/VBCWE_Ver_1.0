package com.dtsvn.vbcwe.dto;

import lombok.Data;

@Data
public class SurveyResultRatingDTO {
	private String focusArea;
	private Long leadingPratice;
	private Long stategic;
	private Long active;
	private Long minStandard;
	
	public SurveyResultRatingDTO(String fa) {
		focusArea = fa;
		leadingPratice = 0L;
		stategic = 0L;
		active = 0L;
		minStandard = 0L;
	}
}
