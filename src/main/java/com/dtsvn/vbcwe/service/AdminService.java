package com.dtsvn.vbcwe.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.common.MessageConstants;
import com.dtsvn.vbcwe.common.exception.RollBackException;
import com.dtsvn.vbcwe.config.EmailConfig;
import com.dtsvn.vbcwe.dao.MembershipMapper;
import com.dtsvn.vbcwe.dao.UserInfoMapper;
import com.dtsvn.vbcwe.dao.UserMapper;
import com.dtsvn.vbcwe.dto.ReportDTO;
import com.dtsvn.vbcwe.dto.SearchReportDTO;
import com.dtsvn.vbcwe.dto.SearchReportResultDTO;
import com.dtsvn.vbcwe.dto.SearchUserInfoDTO;
import com.dtsvn.vbcwe.dto.SearchUserInfoResultDTO;
import com.dtsvn.vbcwe.dto.UserInfoDTO;
import com.dtsvn.vbcwe.entity.ReportEntity;
import com.dtsvn.vbcwe.entity.UserEntity;
import com.dtsvn.vbcwe.entity.UserInfoEntity;
import com.dtsvn.vbcwe.form.MemberForm;
import com.dtsvn.vbcwe.form.SurveyForm;
import com.dtsvn.vbcwe.util.CommonUtils;

@Service
public class AdminService extends BaseService {
    
    @Autowired
    UserInfoMapper userInfoMapper;
    
    @Autowired
    UserMapper userMapper;
    
    @Autowired
    MembershipMapper membershipMapper;
    
    @Autowired
    private EmailConfig emailConfig;
    
    /**
     * List member
     * @param SearchUserInfoDTO
     * @return
     */
    public Page<SearchUserInfoResultDTO> findAllUserInfo(SearchUserInfoDTO searchUserInfoDTO, Pageable pageable) {
        List<SearchUserInfoResultDTO> content =  userInfoMapper.findAllUserInfo(searchUserInfoDTO, pageable);
        Integer countResult = userInfoMapper.countUserInfo(searchUserInfoDTO);
        return new PageImpl<>(content, pageable, countResult);
    }
    
    /**
     * Init update member
     * @param email
     * @return
     */
    public UserInfoDTO initUpdateMember(String email) {
        UserInfoEntity userInfoEntity = userInfoMapper.findOneUserInfoById(email);
        UserInfoDTO userInfoDTO = dozerMapper.map(userInfoEntity, UserInfoDTO.class);
        return userInfoDTO;
    }
    
    /**
     * update user info
     * @param UserInfoDTO
     * @return
     */
    @Transactional
    public Boolean doUpdateUserInfo(UserInfoDTO userInfoDTO) {
            UserInfoEntity userInfoEntity = dozerMapper.map(userInfoDTO, UserInfoEntity.class);
            userInfoEntity.setUpdatedBy(commonHelper.getLoginUser().getUserId());
            
            int result =  userInfoMapper.updateUserInfo(userInfoEntity);
            if (result == 0) {
            return false;
        }
        
        return true;
    }

    /**
     * insert user
     * @param UserInfoDTO
     * @return
     * @throws Exception 
     */
    @Transactional(rollbackFor = RollBackException.class)
    public void doAddUserInfo(UserInfoDTO userInfoDTO) throws RollBackException {
        UserInfoEntity userInfoEntity = userInfoMapper.findOneUserInfoById(userInfoDTO.getUserId());
        if (userInfoEntity != null) {
            //return MessageConstants.MSG_ERROR_EXIST;
            throw new RollBackException(MessageConstants.MSG_ERROR_EXIST);
        } 
        //Insert user info
        userInfoEntity = dozerMapper.map(userInfoDTO, UserInfoEntity.class);
        String userLogin = commonHelper.getLoginUser().getUserId();
        userInfoEntity.setCreatedBy(userLogin);
        
        int result =  userInfoMapper.insertUserInfo(userInfoEntity);
        if (result == 0) {
            //return MessageConstants.MSG_ADD_ERROR;
            throw new RollBackException(MessageConstants.MSG_ADD_ERROR);
        }
        
        //Insert user login
        String password = CommonUtils.generateRandomBase64Token(8);
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userInfoDTO.getUserId());
        userEntity.setPassword(CommonUtils.convert2MD5(password));
        
        result = userMapper.insertUser(userEntity);
        if (result == 0) {
            //return MessageConstants.MSG_ADD_ERROR;
            throw new RollBackException(MessageConstants.MSG_ADD_ERROR);
        }
        
        boolean resultSendEmail = emailConfig.sendAddAccount(userInfoEntity.getUserId(), userInfoEntity.getFullName(), password, userInfoEntity.getUserType());
        if (!resultSendEmail) {
            //return MessageConstants.MSG_ADD_ERROR;
            throw new RollBackException(MessageConstants.MSG_ERROR_EMAIL);
        }
    }
    
    /**
     * List reports
     * @param searchReportDTO
     * @return
     */
    public Page<SearchReportResultDTO> findAllReports(SearchReportDTO searchReportDTO, Pageable pageable) {
        List<SearchReportResultDTO> content = membershipMapper.findAllReports(searchReportDTO, pageable);
        Integer countResult = membershipMapper.countReport(searchReportDTO);
        return new PageImpl<>(content, pageable, countResult);
    }
    
    /**
     * List reports
     * @param searchReportDTO
     * @return
     */
    public ResponseEntity<InputStreamResource> downloadFileReport(String fileName) {
        return CommonUtils.downloadFile(System.getProperty("user.dir") + File.separatorChar + Constant.OUTPUT_FOLDER_PATH_NAME, fileName);
    }
    
    /**
     * Init update report
     * @param email
     * @return
     */
    public ReportDTO initUpdateReport(String email) {
        ReportEntity reportEntity = membershipMapper.findOneReportById(email);
        ReportDTO reportDTO = dozerMapper.map(reportEntity, ReportDTO.class);
        
        MemberForm form = gson.fromJson(reportDTO.getInputData(), MemberForm.class);
        if (form != null && form.getSurvey() != null && form.getSurvey().size() > 0) {
            List<SurveyForm> lstSurveyForm = form.getSurvey().stream().filter(x -> (!x.getFile().isEmpty() || !x.getNote().isEmpty())).collect(Collectors.toList());
            reportDTO.setLstEvidenceFile(lstSurveyForm);
        }
        return reportDTO;
    }
    
    /**
     * Approve report
     * @param reportDTO
     * @return
     */
    @Transactional(rollbackFor = RollBackException.class)
    public String doApproveReport(ReportDTO reportDTO) throws RollBackException {
        // Get file name
        String fileName = reportDTO.getFile().getOriginalFilename(); 
        if (!"ppt, pptx".contains(FilenameUtils.getExtension(fileName))) {
            return MessageConstants.MSG_ERROR_FILE_EXTENSION;
        }
        // Get đường dẫn folder attach file từ file config
        String attachFileDirectory = System.getProperty("user.dir") + File.separatorChar + Constant.OUTPUT_FOLDER_PATH_NAME;

        // create folder path ringi
        StringBuilder fileLocation = new StringBuilder(attachFileDirectory).append("\\");

        // kiểm tra tồn tại folder, nếu chưa tồn tại thì tạo folder
        File ringiFolder = new File(fileLocation.toString());
        if(!ringiFolder.exists()) {
            ringiFolder.mkdirs();
        }

        fileLocation.append(fileName);

        try (FileOutputStream fileOuputStream = new FileOutputStream(fileLocation.toString())){
            fileOuputStream.write(reportDTO.getFile().getBytes());
        } catch (IOException e) {
            logger.error(e.getMessage());
            return MessageConstants.MSG_ERROR_FILE_NOT_FOUND;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return MessageConstants.MSG_ERROR_SYSTEM;
        }

        // set thông tin update
        ReportEntity reportEntity = dozerMapper.map(reportDTO, ReportEntity.class);
        reportEntity.setUpdatedBy(commonHelper.getLoginUser().getUserId());;
        reportEntity.setFilePath(fileName);
        reportEntity.setStatus(Constant.MEMBER_REPORT_STATUS.REPORT_APPROVED.value);

        //Update report status
        int record = membershipMapper.updateReport(reportEntity);
        if(record == 0) {
            throw new RollBackException(MessageConstants.MSG_UPDATE_ERROR);
        }

        //Send email
        boolean resultSendEmail = emailConfig.sendReport(reportEntity.getUserId(), reportEntity.getFullName(), fileName);
        if (!resultSendEmail) {
            throw new RollBackException(MessageConstants.MSG_ERROR_SYSTEM);
        }
        return null;
    }
    
    /**
     * delete report
     * @param reportDTO
     * @return
     */
    @Transactional
    public String doRejectReport(ReportDTO reportDTO) {

        Integer record = membershipMapper.deleteReport(reportDTO.getUserId());
        if(record == 0) {
            return MessageConstants.MSG_DELETE_ERROR;
        }

        return null;
    }
}
