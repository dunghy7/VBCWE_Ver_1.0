package com.dtsvn.vbcwe.entity;

import java.sql.*;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity(name = "com.dtsvn.vbcwe.entity.SurFreeMember")
@Table(name = "sur_free_member")
public class SurFreeMemberEntity {

	@Id
	@Column(name = "sur_free_member_id", nullable = false)
	private String surFreeMemberId;
	@Column(name = "user_id", nullable = false)
	private String userId;
	@Column(name = "input_data", nullable = true)
	private String inputData;
	@Column(name = "output_filepath", nullable = true)
	private String outputFilepath;
	@Column(name = "created_date", nullable = true)
	private Timestamp createdDate;
	@Column(name = "created_by", nullable = true)
	private String createdBy;
}