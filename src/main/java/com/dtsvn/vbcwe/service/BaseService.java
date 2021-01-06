package com.dtsvn.vbcwe.service;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtsvn.vbcwe.common.CommonHelper;
import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.entity.UserEntity;
import com.dtsvn.vbcwe.util.CommonUtils;
import com.google.gson.Gson;

@Service
public class BaseService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected Gson gson;

	/**
	 * HttpSession
	 */
	@Autowired
	HttpSession session;

	@Autowired
	protected CommonHelper commonHelper;

	@Autowired
	protected DozerBeanMapper dozerMapper;

	/**
	 * copy create info
	 * 
	 * @param obj class
	 */
	protected void copyCreateInfo(Object obj, UserEntity loginUser) {
		Timestamp currentTime = new Timestamp(new Date().getTime());
		CommonUtils.invokeSetMethod(obj, currentTime, "setCreatedDate");
		CommonUtils.invokeSetMethod(obj, loginUser.getUserId(), "setCreatedBy");
		CommonUtils.invokeSetMethod(obj, currentTime, "setUpdatedDate");
		CommonUtils.invokeSetMethod(obj, loginUser.getUserId(), "setUpdatedBy");
		CommonUtils.invokeSetMethod(obj, Constant.DB_AVAILABLE, "setDeleteFlg");
	}

	/**
	 * 
	 * <p>
	 * 説明 : 更新クラス情報のコピー
	 * </p>
	 * 
	 * @param obj class
	 */
	protected void copyUpdateInfo(Object obj, UserEntity loginUser) {
		Timestamp currentTime = new Timestamp(new Date().getTime());
		CommonUtils.invokeSetMethod(obj, currentTime, "setUpdatedDate");
		CommonUtils.invokeSetMethod(obj, loginUser.getUserId(), "setUpdatedBy");
	}

	/**
	 * 
	 * <p>
	 * 説明 : 削除情報のコピー
	 * </p>
	 * 
	 * @param obj class
	 */
	protected void copyDeleteInfo(Object obj, UserEntity loginUser) {
		Timestamp currentTime = new Timestamp(new Date().getTime());
		CommonUtils.invokeSetMethod(obj, currentTime, "setUpdatedDate");
		CommonUtils.invokeSetMethod(obj, loginUser.getUserId(), "setUpdatedBy");
		CommonUtils.invokeSetMethod(obj, Constant.DB_DELETED, "setDeleteFlg");
	}

	/**
	 * <p>
	 * 説明 : get login user info
	 * </p>
	 * 
	 * @return UserEntity
	 */
	protected UserEntity getUserLogin() {
		return (UserEntity) session.getAttribute(Constant.LOGIN_USER_KEY);
	}

}
