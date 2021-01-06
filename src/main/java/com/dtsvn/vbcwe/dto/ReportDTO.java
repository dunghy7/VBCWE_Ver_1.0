package com.dtsvn.vbcwe.dto;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dtsvn.vbcwe.form.SurveyForm;

import lombok.Data;

@Data
public class ReportDTO {
    
    private String userId;
    
    private String fullName;
    
    private String inputData;
    
    private String filePath;
    
    private Timestamp createdDate;
    
    private String createdBy;
    
    private Timestamp updatedDate;
    
    private String updatedBy;
    
    private String status;
    
    private MultipartFile file;
    
    private List<SurveyForm> lstEvidenceFile;
}

