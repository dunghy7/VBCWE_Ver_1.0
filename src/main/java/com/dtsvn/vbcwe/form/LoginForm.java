package com.dtsvn.vbcwe.form;

import org.hibernate.validator.constraints.NotBlank;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.common.MessageConstants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {

	@NotBlank(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_EMPTY + Constant.RIGHT_BRACES)
	private String email;
	@NotBlank(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_EMPTY + Constant.RIGHT_BRACES)
	private String password;

}
