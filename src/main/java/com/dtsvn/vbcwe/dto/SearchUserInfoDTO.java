package com.dtsvn.vbcwe.dto;

import lombok.Data;

@Data
public class SearchUserInfoDTO {

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
