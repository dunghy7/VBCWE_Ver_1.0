package com.dtsvn.vbcwe.service;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dtsvn.vbcwe.common.MessageConstants;
import com.dtsvn.vbcwe.common.exception.RollBackException;
import com.dtsvn.vbcwe.config.EmailConfig;
import com.dtsvn.vbcwe.dao.UserMapper;
import com.dtsvn.vbcwe.entity.UserEntity;
import com.dtsvn.vbcwe.util.CommonUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserMapper userMapper;
	
    @Autowired
    private EmailConfig emailConfig;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserEntity loginUser = null;

		try {
			if (StringUtils.isEmpty(userId)) {
				throw new UsernameNotFoundException(MessageConstants.MSG_ACCOUNT_INVALID);
			}

			loginUser = userMapper.doLogin(userId);
			if (Objects.isNull(loginUser)) {
				throw new UsernameNotFoundException(MessageConstants.MSG_ACCOUNT_INVALID);
			}

			// create Authority List
			List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(loginUser.getUserType());

			// create UserDetails
			loginUser.initLoginInfo(loginUser.getUsername(), authorities);
		} catch (Exception e) {
			throw e;
		}
		return loginUser;
	}

	/**
	 * reset password
	 * @param userId
	 */
	@Transactional(rollbackFor = RollBackException.class)
	public void resetPassword(String userId) {
		
		UserEntity loginUser = userMapper.doLogin(userId);
		if (Objects.isNull(loginUser)) {
			throw new RollBackException(MessageConstants.MSG_ERROR_EMAIL_INVALID);
		}
		
		//Gen password
		String password = CommonUtils.generateRandomBase64Token(8);
		String passwordMD5 = CommonUtils.convert2MD5(password);
		
		//Set password to user entity
		loginUser.setPassword(passwordMD5);
		Integer result = userMapper.updatePassword(loginUser);
		if (result == 0) {
            throw new RollBackException(MessageConstants.MSG_UPDATE_ERROR);
        }
        
		//Send email
        boolean resultSendEmail = emailConfig.sendResetPassword(loginUser.getUserId(), loginUser.getFullName(), password);
        if (!resultSendEmail) {
            throw new RollBackException(MessageConstants.MSG_ERROR_EMAIL);
        }
	}
}
