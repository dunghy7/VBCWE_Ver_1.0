package com.dtsvn.vbcwe.dto;
import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchUserInfoResultDTO {
    
    /**
     * User id
     */
    private String memberId;
    
    /**
     * email
     */
    private String email;
    
    /**
     * Full name
     */
    private String fullName;
    
    /**
     * Register Date
     */
    private LocalDate registerDate;
    
    /**
     * User type
     */
    private String userType;
    
    /**
     * User type
     */
    private String userTypeName;
    
    /**
     * Status
     */
    private String status;
    
    /**
     * Status
     */
    private String statusName;
}

