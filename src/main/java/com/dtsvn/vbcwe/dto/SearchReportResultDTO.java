package com.dtsvn.vbcwe.dto;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class SearchReportResultDTO {
    
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
    private Timestamp registerDate;
    
    /**
     * Status
     */
    private String status;
    
    /**
     * Status
     */
    private String statusName;
    
    /**
     * File path
     */
    private String filePath;
}

