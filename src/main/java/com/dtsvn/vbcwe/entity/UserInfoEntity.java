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
@Entity(name = "com.dtsvn.vbcwe.entity.UserInfo")
@Table(name = "user_info")
public class UserInfoEntity extends BaseEntity {

	private static final long serialVersionUID = 5263410691742678690L;
	@Id
	@Column(name = "user_info_id", columnDefinition = "uuid", updatable = false)
	@GeneratedValue
	private UUID userInfoId;
	@Column(name = "user_id", nullable = false)
	private String userId;
	@Column(name = "full_name", nullable = true)
	private String fullName;
	@Column(name = "phone", nullable = true)
	private String phone;
	@Column(name = "position", nullable = true)
	private String position;
	@Column(name = "company_name", nullable = true)
	private String companyName;
	@Column(name = "company_scale", nullable = true)
	private String companyScale;
	@Column(name = "company_business", nullable = true)
	private String companyBusiness;
	@Column(name = "company_area", nullable = true)
	private String companyArea;
	@Column(name = "user_type", nullable = false)
	private String userType;
	@Column(name = "company_name_abbr", nullable = true)
	private String companyNameAbbr;
	@Column(name = "delete_flg", nullable = true)
	protected String deleteFlg;
}