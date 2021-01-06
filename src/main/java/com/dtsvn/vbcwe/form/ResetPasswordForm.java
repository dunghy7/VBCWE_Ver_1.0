package com.dtsvn.vbcwe.form;

import org.hibernate.validator.constraints.NotBlank;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.common.MessageConstants;

import lombok.Data;

@Data
public class ResetPasswordForm {

	@NotBlank(message = Constant.LEFT_BRACES + MessageConstants.MSG_ERROR_EMPTY + Constant.RIGHT_BRACES)
	private String email;
}
