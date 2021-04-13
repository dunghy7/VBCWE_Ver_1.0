package com.dtsvn.vbcwe.dto;

import java.util.List;

import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class MemberSurveyDTO {
	
	@NotEmpty(message = "{msg_error_common_empty}")
	@Max(value = 200, message = "{msg_error_maxlength}")
	private String companyName;
	@NotEmpty(message = "{msg_error_common_empty}")
	@Max(value = 500, message = "{msg_error_maxlength}")
	private String address;
	@NotEmpty(message = "{msg_error_common_empty}")
	@Max(value = 50, message = "{msg_error_maxlength}")
	private String district;
	@NotEmpty(message = "{msg_error_common_empty}")
	@Max(value = 50, message = "{msg_error_maxlength}")
	private String city;
	@NotEmpty(message = "{msg_error_common_empty}")
	@Max(value = 100, message = "{msg_error_maxlength}")
	private String fullName;
	@NotEmpty(message = "{msg_error_common_empty}")
	@Max(value = 200, message = "{msg_error_maxlength}")
	private String position;
	@NotEmpty(message = "{msg_error_common_empty}")
	@Max(value = 15, message = "{msg_error_maxlength}")
	private String phone;
	@NotEmpty(message = "{msg_error_common_empty}")
	@Max(value = 15, message = "{msg_error_maxlength}")
	private String mobile;
	@NotEmpty(message = "{msg_error_common_empty}")
	@Max(value = 15, message = "{msg_error_maxlength}")
	private String fax;
	@NotEmpty(message = "{msg_error_common_empty}")
	@Max(value = 50, message = "{msg_error_maxlength}")
	private String email;

	private List<SurveyDTO> survey;


}
