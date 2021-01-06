package com.dtsvn.vbcwe.common;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.dtsvn.vbcwe.entity.UserEntity;

@Component
public class CommonHelper {

    /**
     * MessageSource
     */
    @Autowired
    MessageSource messageSource;
    
    /**
     * Session
     */
    @Autowired
    HttpSession session;

    /**
     * get message
     * @param messageId メッセージID
     * @param params    パラメータ
     * @return
     */
    public String getMessage(String messageId, String... params) {
        String[] paramArr = new String[0];
        if (params != null) {
            paramArr = new String[params.length + 1];
            paramArr[0] = "";
            for (int i = 1; i <= params.length; i++) {
                paramArr[i] = params[i - 1];
            }
        }
        return messageSource.getMessage(messageId, paramArr, LocaleContextHolder.getLocale());
    }
    
    /**
     * <p>説明 : get login user info</p>
     * @return UserEntity
     */
    public UserEntity getLoginUser() {
        return (UserEntity) session.getAttribute(Constant.LOGIN_USER_KEY);
    }
}
