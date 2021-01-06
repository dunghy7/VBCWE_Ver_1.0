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
@Entity(name = "com.dtsvn.vbcwe.entity.UserLogin")
@Table(name = "user_login")
public class UserLoginEntity extends BaseEntity {

	private static final long serialVersionUID = 8186349389771305517L;
	@Id
	@Column(name = "user_login_id", columnDefinition = "uuid", updatable = false)
	@GeneratedValue
	private UUID userLoginId;
	@Column(name = "user_id", nullable = false)
	private String userId;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "email_verify", nullable = true)
	private String emailVerify;
	@Column(name = "delete_flg", nullable = true)
	protected String deleteFlg;
}