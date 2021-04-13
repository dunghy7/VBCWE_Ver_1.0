package com.dtsvn.vbcwe.form;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class MemberForm {
	
    @NotBlank(message = "{msg_error_empty_company}")
	@Max(value = 200, message = "{msg_error_maxlength}")
	private String companyName;
	
    @NotBlank(message = "{msg_error_empty_address}")
	@Max(value = 500, message = "{msg_error_maxlength}")
	private String address;
	
    @NotBlank(message = "{msg_error_empty_district}")
	@Max(value = 50, message = "{msg_error_maxlength}")
	private String district;
	
    @NotBlank(message = "{msg_error_empty_city}")
	@Max(value = 50, message = "{msg_error_maxlength}")
	private String city;
	
    @NotBlank(message = "{msg_error_empty_fullname}")
	@Max(value = 100, message = "{msg_error_maxlength}")
	private String fullName;
	
    @NotBlank(message = "{msg_error_empty_position}")
	@Max(value = 200, message = "{msg_error_maxlength}")
	private String position;
	
    @NotBlank(message = "{msg_error_empty_desk_phone}")
	@Max(value = 15, message = "{msg_error_maxlength}")
    @Pattern(regexp = "[0-9]+", message = "{msg_error_number}")
	private String phone;
	
    @NotBlank(message = "{msg_error_empty_cell_phone}")
	@Max(value = 15, message = "{msg_error_maxlength}")
    @Pattern(regexp = "[0-9]+", message = "{msg_error_number}")
	private String mobile;
	
    @NotBlank(message = "{msg_error_empty_fax}")
	@Max(value = 15, message = "{msg_error_maxlength}")
    @Pattern(regexp = "[0-9]+", message = "{msg_error_number}")
	private String fax;
	
    @NotBlank(message = "{msg_error_empty_email}")
	@Max(value = 50, message = "{msg_error_maxlength}")
	@Email(message = "{msg_error_email_struct_invalid}")
	private String email;

	private List<SurveyForm> survey;


}
