package com.dtsvn.vbcwe.form;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ReportForm {

    private String fullName;
    
    private String userId;
    
    private Timestamp createdDate;
    
    private String filePath;
    
    @NotNull
    private MultipartFile file;
}
