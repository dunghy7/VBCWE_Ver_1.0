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
@Entity(name = "com.dtsvn.vbcwe.entity.InCareerExp")
@Table(name = "in_career_exp")
public class InCareerExpEntity extends BaseEntity {

	private static final long serialVersionUID = 3540400075030155304L;
	@Id
	@Column(name = "in_career_exp_id", columnDefinition = "uuid", updatable = false)
	@GeneratedValue
	private UUID inCareerExpId;
	@Column(name = "user_id", nullable = false)
	private String userId;
	@Column(name = "input_data", nullable = true)
	private String inputData;
	@Column(name = "status", nullable = true)
	private String status;
}