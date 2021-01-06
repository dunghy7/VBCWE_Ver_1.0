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
@Entity(name = "com.dtsvn.vbcwe.entity.InCareerMng")
@Table(name = "in_career_mng")
public class InCareerMngEntity extends BaseEntity {

	private static final long serialVersionUID = 9031617843623106104L;
	@Id
	@Column(name = "in_career_mng_id", columnDefinition = "uuid", updatable = false)
	@GeneratedValue
	private UUID inCareerMngId;
	@Column(name = "user_id", nullable = false)
	private String userId;
	@Column(name = "input_data", nullable = true)
	private String inputData;
	@Column(name = "status", nullable = true)
	private String status;
}