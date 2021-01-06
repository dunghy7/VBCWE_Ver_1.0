package com.dtsvn.vbcwe.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInfoDTO extends BaseDTO {

	/**
	 * Email
	 */
	private String userId;

	/**
	 * Full name
	 */
	private String fullName;

	/**
	 * Phone
	 */
	private String phone;

	/**
	 * Position
	 */
	private String position;

	/**
	 * Company name
	 */
	private String companyName;

	/**
	 * Company name abbreviation
	 */
	private String companyNameAbbr;

	/**
	 * Company Scale
	 */
	private String companyScale;

	/**
	 * Company business
	 */
	private String companyBusiness;

	/**
	 * Company area
	 */
	private String companyArea;

	/**
	 * User type
	 */
	private String userType;

}
