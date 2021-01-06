package com.dtsvn.vbcwe.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dtsvn.vbcwe.dto.SearchUserInfoDTO;
import com.dtsvn.vbcwe.dto.SearchReportDTO;
import com.dtsvn.vbcwe.common.CommonHelper;
import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.common.MessageConstants;
import com.dtsvn.vbcwe.dto.SearchUserInfoResultDTO;
import com.dtsvn.vbcwe.dto.SearchReportResultDTO;
import com.dtsvn.vbcwe.dto.UserInfoDTO;
import com.dtsvn.vbcwe.dto.ReportDTO;
import com.dtsvn.vbcwe.form.UserInfoForm;
import com.dtsvn.vbcwe.form.ReportForm;
import com.dtsvn.vbcwe.form.SearchUserInfoForm;
import com.dtsvn.vbcwe.form.SurveyForm;
import com.dtsvn.vbcwe.form.SearchReportForm;
import com.dtsvn.vbcwe.service.AdminService;
import com.dtsvn.vbcwe.util.CommonUtils;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    AdminService adminService;
    
    @Autowired
    protected DozerBeanMapper dozerMapper;
    
    @Autowired
    protected CommonHelper commonHelper;
    
    // Đường dẫn lưu file
    @Value("${user.evidence.path}")
    private String evidencePath;
    
    @GetMapping(value = "/initSearchUserInfo")
    public String initSearchUserInfo(Model model, @ModelAttribute("searchUserInfoForm") SearchUserInfoForm searchUserInfoForm, Pageable pageable) {
        
        searchMember(model, searchUserInfoForm, pageable);
        return "admin/member";
    }
    
    @PostMapping(value = "/doSearchUserInfo")
    public String doSearchUserInfo(Model model, @ModelAttribute("searchUserInfoForm") SearchUserInfoForm searchUserInfoForm, Pageable pageable) {
	
        searchMember(model, searchUserInfoForm, pageable);
        return "admin/member";
    }
    @GetMapping(value = "/initUpdateMember")
    public String initUpdateMember(Model model, @ModelAttribute("userInfoForm") UserInfoForm userInfoForm) {
        
        UserInfoDTO userInfoDTO = adminService.initUpdateMember(userInfoForm.getUserId());
        
        UserInfoForm form = dozerMapper.map(userInfoDTO, UserInfoForm.class);
        
        model.addAttribute("userInfoForm", form);
        
        return "admin/member_update";
    }
    
    @PostMapping(value = "/doUpdateUserInfo")
    public String doUpdateUserInfo(Model model, @ModelAttribute("userInfoForm") @Validated UserInfoForm userInfoForm,
                BindingResult bindingResult, RedirectAttributes redirectAttr) {
        
        if (bindingResult.hasErrors()) {
              model.addAttribute("userInfoForm", userInfoForm);
              return "admin/member_update";
        }
         
        UserInfoDTO userInfoDTO = dozerMapper.map(userInfoForm, UserInfoDTO.class);
        
        if (adminService.doUpdateUserInfo(userInfoDTO)) {
            redirectAttr.addFlashAttribute("messageSuccess", commonHelper.getMessage(MessageConstants.MSG_UPDATE_SUCCESS));
        } else {
            redirectAttr.addFlashAttribute("messageError", commonHelper.getMessage(MessageConstants.MSG_UPDATE_ERROR));
            return "admin/member_update";
        }
        
         return "redirect:/admin/initSearchUserInfo";
    }
    
    @PostMapping(value = "/deleteMember")
    public String deleteMember(Model model, @ModelAttribute("userInfoForm") UserInfoForm userInfoForm,
                BindingResult bindingResult, RedirectAttributes redirectAttr) {
         
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserId(userInfoForm.getUserId());
        userInfoDTO.setDeleteFlg(userInfoForm.getDeleteFlg().equals("1") ? "0" : "1");
        
        if (adminService.doUpdateUserInfo(userInfoDTO)) {
            redirectAttr.addFlashAttribute("messageSuccess", commonHelper.getMessage(MessageConstants.MSG_UPDATE_SUCCESS));
        } else {
            redirectAttr.addFlashAttribute("messageError", commonHelper.getMessage(MessageConstants.MSG_UPDATE_ERROR));
            return "admin/member_update";
        }
        
         return "redirect:/admin/initSearchUserInfo";
    }
    
    @GetMapping(value = "/initAddUserInfo")
    public String initAddUserInfo(Model model, @ModelAttribute("userInfoForm") UserInfoForm userInfoForm) {
        return "admin/member_add";
    }
    
    @PostMapping(value = "/doAddUserInfo")
    public String doInsert(Model model, @ModelAttribute("userInfoForm") @Validated UserInfoForm userInfoForm,
                BindingResult bindingResult, RedirectAttributes redirectAttr) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("userInfoForm", userInfoForm);
                return "admin/member_add";
            }
           
            UserInfoDTO userInfoDTO = dozerMapper.map(userInfoForm, UserInfoDTO.class);
            adminService.doAddUserInfo(userInfoDTO);
        } catch (Exception e) {
            model.addAttribute("messageError", commonHelper.getMessage(e.getMessage()));
            return "admin/member_add";
        }
        redirectAttr.addFlashAttribute("messageSuccess", commonHelper.getMessage(MessageConstants.MSG_ADD_SUCCESS));
        return "redirect:/admin/initSearchUserInfo";
    }
    
    @GetMapping(value = "/initSearchReport")
    public String init(Model model, @ModelAttribute("searchReportForm") SearchReportForm searchReportForm, Pageable pageable) {
        
        searchReport(model, searchReportForm, pageable);
        return "admin/report";
    }
    
    @PostMapping(value = "/doSearchReport")
    public String doSearchReport(Model model, @ModelAttribute("searchReportForm") SearchReportForm searchReportForm, Pageable pageable) {

        searchReport(model, searchReportForm, pageable);
        return "admin/report";
    }
    
    @PostMapping(value = "/downloadFileReport")
    public ResponseEntity<InputStreamResource> downloadFileReport(@RequestParam(value = "fileName", defaultValue = "") String fileName) {

        return adminService.downloadFileReport(fileName);
    }

    @GetMapping(value = "/initUpdateReport")
    public String initUpdateReport(Model model, @ModelAttribute("reportForm") ReportForm reportForm, Pageable pageable, HttpSession session) {
        
        ReportDTO reportDTO = adminService.initUpdateReport(reportForm.getUserId());
        
        ReportForm form = dozerMapper.map(reportDTO, ReportForm.class);
        
        model.addAttribute("reportForm", form);
        
        //Paging result search evidence
        int currentPage = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        List<SurveyForm> content = reportDTO.getLstEvidenceFile().stream()
                                                                .skip((currentPage - 1) * pageSize)  // Equivalent to SQL's offset
                                                                .limit(pageSize) // Equivalent to SQL's limit
                                                                .collect(Collectors.toList());
        Page<SurveyForm> lstEvidenceFile = new PageImpl<>(content, new PageRequest(currentPage - 1, pageSize), reportDTO.getLstEvidenceFile().size()); 
        int totalPages = lstEvidenceFile.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("listEvidence", lstEvidenceFile);
        session.setAttribute("listEvidence", lstEvidenceFile);
        
        return "admin/report_update";
    }
    
    @PostMapping(value = "/doApproveReport")
    public String doApproveReport(Model model, @ModelAttribute("reportForm") ReportForm reportForm, 
            BindingResult bindingResult, RedirectAttributes redirectAttr, HttpSession session) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("reportForm", reportForm);
                return "admin/report_update";
            }
            
            ReportDTO reportDTO = dozerMapper.map(reportForm, ReportDTO.class);
            
            String result = adminService.doApproveReport(reportDTO);
            if (result == null) {
                redirectAttr.addFlashAttribute("messageSuccess", commonHelper.getMessage(MessageConstants.MSG_UPDATE_SUCCESS));
            } else {
                model.addAttribute("messageError", commonHelper.getMessage(result));
                model.addAttribute("listEvidence", session.getAttribute("listEvidence"));
                return "admin/report_update";
            }
        } catch (Exception e) {
            model.addAttribute("messageError", commonHelper.getMessage(e.getMessage()));
            model.addAttribute("listEvidence", session.getAttribute("listEvidence"));
            return "admin/report_update";
        }
        
        return "redirect:/admin/initSearchReport";
    }
    
    @PostMapping(value = "/doRejectReport")
    public String doRejectReport(Model model, @ModelAttribute("reportForm") ReportForm reportForm, RedirectAttributes redirectAttr, HttpSession session) {
        
        ReportDTO reportDTO = dozerMapper.map(reportForm, ReportDTO.class);
        
        String result = adminService.doRejectReport(reportDTO);
        if (result == null) {
            redirectAttr.addFlashAttribute("messageSuccess", commonHelper.getMessage(MessageConstants.MSG_DELETE_SUCCESS));
        } else {
            model.addAttribute("messageError", commonHelper.getMessage(result));
            model.addAttribute("listEvidence", session.getAttribute("listEvidence"));
            return "admin/report_update";
        }
        
        return "redirect:/admin/initSearchReport";
    }
    
    /**
     * Phương thức download templa GEARS_HR_Data_Tables_Template.xlsx
     * 
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/downloadEvidence")
    public ResponseEntity<InputStreamResource> downloadEvidence(@RequestParam(value = "fileName", defaultValue = "") String fileName) throws IOException {

        // Thuc hien download file
        File filePath = new File(System.getProperty("user.dir"), evidencePath);
        return CommonUtils.downloadFile(filePath.getPath(), fileName);
    }
    
    /**
     * Load mmber list
     * @param model
     * @param searchUserInfoForm
     */
    private void searchMember(Model model, SearchUserInfoForm searchUserInfoForm, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        
        SearchUserInfoDTO searchUserInfoDTO = dozerMapper.map(searchUserInfoForm, SearchUserInfoDTO.class);
        
        Page<SearchUserInfoResultDTO> lstMembersResultDTO = adminService.findAllUserInfo(searchUserInfoDTO, new PageRequest(currentPage - 1, pageSize));
        model.addAttribute("resultMember", lstMembersResultDTO);
        
        int totalPages = lstMembersResultDTO.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
    }
    
    /**
     * Load report list
     * @param model
     * @param searchReportForm
     */
    private void searchReport(Model model, SearchReportForm searchReportForm, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        
        SearchReportDTO searchReportDTO = dozerMapper.map(searchReportForm, SearchReportDTO.class);
        
        Page<SearchReportResultDTO> lstReportsResultDTO = adminService.findAllReports(searchReportDTO, new PageRequest(currentPage - 1, pageSize));
        model.addAttribute("resultReport", lstReportsResultDTO);
        
        int totalPages = lstReportsResultDTO.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
    }
}
