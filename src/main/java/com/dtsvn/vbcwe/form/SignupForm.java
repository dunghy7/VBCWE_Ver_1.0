package com.dtsvn.vbcwe.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.common.MessageConstants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupForm {

	@NotBlank(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_EMPTY + Constant.RIGHT_BRACES)
	@Email(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_EMAIL + Constant.RIGHT_BRACES)
	@Size(max = 50, message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_MAX_LENGTH + Constant.RIGHT_BRACES)
	private String email;

	@NotBlank(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_EMPTY + Constant.RIGHT_BRACES)
	@Size(min = 8, max = 50, message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_LENGTH + Constant.RIGHT_BRACES)
	private String password;

	@NotBlank(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_EMPTY + Constant.RIGHT_BRACES)
	@Size(min = 8, max = 50, message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_LENGTH + Constant.RIGHT_BRACES)
	private String rePassword;

	@NotBlank(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_EMPTY + Constant.RIGHT_BRACES)
	@Size(max = 100, message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_MAX_LENGTH + Constant.RIGHT_BRACES)
	private String fullName;

	@NotBlank(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_EMPTY + Constant.RIGHT_BRACES)
	@Size(max = 15, message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_MAX_LENGTH + Constant.RIGHT_BRACES)
	@Pattern(regexp = "\\d+", message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_NUMBER
			+ Constant.RIGHT_BRACES)
	private String phone;

	@NotBlank(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_EMPTY + Constant.RIGHT_BRACES)
	@Size(max = 200, message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_MAX_LENGTH + Constant.RIGHT_BRACES)
	private String position;

	@NotBlank(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_EMPTY + Constant.RIGHT_BRACES)
	@Size(max = 200, message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_MAX_LENGTH + Constant.RIGHT_BRACES)
	private String companyName;

	@NotBlank(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_EMPTY + Constant.RIGHT_BRACES)
	@Size(max = 10, message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_MAX_LENGTH + Constant.RIGHT_BRACES)
	private String companyNameAbbr;

	@NotBlank(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_EMPTY + Constant.RIGHT_BRACES)
	@Size(max = 50, message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_MAX_LENGTH + Constant.RIGHT_BRACES)
	private String companyScale;

	@NotBlank(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_EMPTY + Constant.RIGHT_BRACES)
	@Size(max = 200, message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_MAX_LENGTH + Constant.RIGHT_BRACES)
	private String companyBusiness;

	@NotBlank(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_EMPTY + Constant.RIGHT_BRACES)
	@Size(max = 50, message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_MAX_LENGTH + Constant.RIGHT_BRACES)
	private String companyArea;
}
