package com.dtsvn.vbcwe.form;

import lombok.Data;

@Data
public class SearchUserInfoForm {

    /**
     * email
     */
    private String email;
    
    /**
     * Full name
     */
    private String fullName;

    /**
     * Status
     */
    private String userType;
}
