package com.dtsvn.vbcwe.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginDTO extends BaseDTO {

	/**
	 * Email
	 */
	private String userId;

	/**
	 * Password
	 */
	private String password;

	/**
	 * Email verify
	 */
	private String emailVerify;

}
