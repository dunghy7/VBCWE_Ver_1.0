package com.dtsvn.vbcwe.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "com.dtsvn.vbcwe.entity.SurMembership")
@Table(name = "sur_membership_rpt")
public class SurMembershipRptEntity extends BaseEntity {

	private static final long serialVersionUID = -5070924521140701597L;
	@Id
	@Column(name = "sur_membership_rpt_id", columnDefinition = "uuid", updatable = false)
	@GeneratedValue
	private UUID surMembershipId;
	@Column(name = "user_id", nullable = false)
	private String userId;
	@Column(name = "output_filepath", nullable = true)
	private String outputFilepath;
	@Column(name = "status", nullable = true)
	private String status;
}