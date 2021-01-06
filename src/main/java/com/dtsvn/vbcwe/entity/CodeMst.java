package com.dtsvn.vbcwe.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "code_mst")
public class CodeMst extends BaseEntity {

	private static final long serialVersionUID = 4020088337893793764L;
	@Column(name = "code_id", nullable = false)
	private String codeId;
	@Column(name = "code_value", nullable = false)
	private String codeValue;
	@Column(name = "code_name", nullable = true)
	private String codeName;
	@Column(name = "sort", nullable = true)
	private Integer sort;
	@Column(name = "delete_flg", nullable = true)
	protected String deleteFlg;
}