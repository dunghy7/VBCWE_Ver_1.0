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
@Entity(name = "com.dtsvn.vbcwe.entity.InBrdCompEntity")
@Table(name = "in_brd_comp")
public class InBrdCompEntity extends BaseEntity {

	private static final long serialVersionUID = 8195172034165976882L;
	@Id
	@Column(name = "in_brd_comp_id", columnDefinition = "uuid", updatable = false)
	@GeneratedValue
	private UUID inBrdCompId;
	@Column(name = "user_id", nullable = false)
	private String userId;
	@Column(name = "input_data", nullable = true)
	private String inputData;
	@Column(name = "status", nullable = true)
	private String status;
}