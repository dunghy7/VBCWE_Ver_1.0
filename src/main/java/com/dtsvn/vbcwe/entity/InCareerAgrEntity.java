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
@Entity(name = "com.dtsvn.vbcwe.entity.InCareerAgr")
@Table(name = "in_career_agr")
public class InCareerAgrEntity extends BaseEntity {

	private static final long serialVersionUID = 1704643897560444221L;
	@Id
	@Column(name = "in_career_agr_id", columnDefinition = "uuid", updatable = false)
	@GeneratedValue
	private UUID inCareerAgrId;
	@Column(name = "user_id", nullable = false)
	private String userId;
	@Column(name = "input_data", nullable = true)
	private String inputData;
	@Column(name = "status", nullable = true)
	private String status;
}