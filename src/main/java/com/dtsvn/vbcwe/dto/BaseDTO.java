package com.dtsvn.vbcwe.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.dtsvn.vbcwe.common.Constant;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * ファイル名 : BaseDTO
 * </p>
 * <p>
 * 説明 : Base DTO
 * </p>
 */
@Setter
@Getter
public class BaseDTO {

	/**
	 * createdBy
	 */
	protected String createdBy;

	/**
	 * createDate
	 */
	@DateTimeFormat(pattern = Constant.DATE_TIME_PATTERN)
	protected LocalDateTime createdDate;

	/**
	 * updateBy
	 */
	protected String updatedBy;

	/**
	 * updateTime
	 */
	@DateTimeFormat(pattern = Constant.DATE_TIME_PATTERN)
	protected LocalDateTime updatedDate;

	/**
	 * deleteFlag
	 */
	protected String deleteFlg;

	/**
	 * BaseDTO
	 */
	public BaseDTO() {
		super();
	}

}
