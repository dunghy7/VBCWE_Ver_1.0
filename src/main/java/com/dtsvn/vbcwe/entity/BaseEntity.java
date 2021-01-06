package com.dtsvn.vbcwe.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * ファイル名 : BaseEntity
 * </p>
 * <p>
 * 説明 : Base Entity
 * </p>
 */
@Setter
@Getter
@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -3883351801989215611L;

	@Column(name = "created_by")
	protected String createdBy;

	@Column(name = "created_date")
	protected Timestamp createdDate;

	@Column(name = "updated_by")
	protected String updatedBy;

	@Column(name = "updated_date")
	protected Timestamp updatedDate;

}
