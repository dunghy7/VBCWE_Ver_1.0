package com.dtsvn.vbcwe.dto;

import lombok.Data;

@Data
public class SearchReportDTO {

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
    private String status;
}
