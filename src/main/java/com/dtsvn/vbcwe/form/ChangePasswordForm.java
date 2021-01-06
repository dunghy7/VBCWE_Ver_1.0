package com.dtsvn.vbcwe.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.common.MessageConstants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordForm {

	@NotBlank(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_EMPTY + Constant.RIGHT_BRACES)
	@Email(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_EMAIL + Constant.RIGHT_BRACES)
	@Size(max = 50, message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_MAX_LENGTH + Constant.RIGHT_BRACES)
	private String email;

	@NotBlank(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_EMPTY + Constant.RIGHT_BRACES)
	@Size(min = 8, max = 50, message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_LENGTH + Constant.RIGHT_BRACES)
	private String passwordOld;

	@NotBlank(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_EMPTY + Constant.RIGHT_BRACES)
	@Size(min = 8, max = 50, message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_LENGTH + Constant.RIGHT_BRACES)
	private String passwordNew;

	@NotBlank(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_EMPTY + Constant.RIGHT_BRACES)
	@Size(min = 8, max = 50, message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_LENGTH + Constant.RIGHT_BRACES)
	private String rePasswordNew;

	private boolean loggedIn;

}
