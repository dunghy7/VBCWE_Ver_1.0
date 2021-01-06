package com.dtsvn.vbcwe.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name = "com.dtsvn.vbcwe.entity.SurMembershipAtt")
@Table(name = "sur_membership_att")
public class SurMembershipAtt {

	@Id
	@Column(name = "sur_membership_att_id", columnDefinition = "uuid", updatable = false)
	@GeneratedValue
	private UUID surMembershipAttId;
	@Column(name = "sur_membership_id", columnDefinition = "uuid", updatable = false)
	@GeneratedValue
	private UUID surMembershipId;
	@Column(name = "file_path", nullable = true)
	private String filePath;
}