package com.dtsvn.vbcwe.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dtsvn.vbcwe.common.CommonHelper;
import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.common.MessageConstants;
import com.dtsvn.vbcwe.controller.validator.FormSurveyValidator;
import com.dtsvn.vbcwe.dto.AllInputInfoDTO;
import com.dtsvn.vbcwe.dto.MemberDTO;
import com.dtsvn.vbcwe.dto.ReportDTO;
import com.dtsvn.vbcwe.entity.UserEntity;
import com.dtsvn.vbcwe.form.AllInputInfoForm;
import com.dtsvn.vbcwe.form.DeleteEvidenceForm;
import com.dtsvn.vbcwe.form.MemberForm;
import com.dtsvn.vbcwe.service.MembershipService;
import com.dtsvn.vbcwe.util.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Controller
@RequestMapping("/membership")
public class MembershipController {

    // Đường dẫn thư mục file templ trong resources
    @Value("${url.resources.templ}")
    private String urlResourcesTempl;

    @Autowired
    protected DozerBeanMapper dozerMapper;

    @Autowired
    protected CommonHelper commonHelper;

    @Autowired
    private MembershipService membershipService;

    @Value("${user.survey.path}")
    private String surveyPath;

    // Tên File templ GEARS HR Data Table Template.xlsx trong resources
    @Value("${name.gears.hr.data.table.templ}")
    private String nameGearsHrDataTableTempl;

    // key dùng để replace đường dẫn ảnh
    @Value("${user.path_file_key}")
    private String pathFileKey;

    // giá trị status khi survey đã hoàn thành
    @Value("${user.survey.status.complete}")
    private String statuscomplete;

    // giá trị status khi survey chưa được duyệt
    @Value("${user.survey.status.pending}")
    private String statusPending;

    @Autowired
    private MessageSource messageSource;

    // Đường dẫn lưu file
    @Value("${user.evidence.path}")
    private String localtion;

    // dung lượng tối đa
    @Value("${user.evidence.max-file-size}")
    private int maxFileSize;

    // danh sách các đuôi mở rộng được cho phép
    @Value("${user.file.accept}")
    private String lstExtension;

    // thời gian tự động save survey
    @Value("${system.autosave.interval}")
    private String autosaveInterval;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, Authentication authentication, HttpSession session) {
        // Lấy thông tin user đăng nhập
        UserEntity userEntity = commonHelper.getLoginUser();
        ReportDTO reportDto = membershipService.loadReportInfo();
        AllInputInfoDTO allInputInfoDTO = membershipService.getAllInputInfo(userEntity.getUserId());
        AllInputInfoForm form = new AllInputInfoForm(allInputInfoDTO, reportDto);
        model.addAttribute("inputinfo", form);
        session.setAttribute(Constant.REPORT_STATUS, reportDto.getStatus());
        return "membership/index";
    }

    /**
     * 
     * @param model
     * @param authentication
     * @return
     */
    @RequestMapping(value = "/gears", method = RequestMethod.GET)
    public String gears(Model model, Authentication authentication) {
        // lấy ra thông tin survey của member
        MemberDTO memberDto = membershipService.loadSurveyInfo();
        if (memberDto != null) {
            model.addAttribute("data", memberDto.getInputData());
            model.addAttribute("status", memberDto.getStatus());
        }
        // add các thông số config
        model.addAttribute("autosaveInterval", autosaveInterval);
        model.addAttribute("lstExtension", lstExtension);
        model.addAttribute("maxFileSize", maxFileSize);
        return "membership/gears";
    }

    /**
     * xử lý lưu file khi người dùng upload file lên server
     * 
     * @param file
     * @param authentication
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploadevidence", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    @ResponseBody
    public String uploadfile(@RequestBody MultipartFile file, @RequestParam(name = "survey") String survey,
            Authentication authentication, HttpServletResponse response) throws IOException {
        JsonObject res = new JsonObject();
        // nếu file không tồn tại
        if (file.isEmpty()) {
            res.addProperty("message",
                    messageSource.getMessage(MessageConstants.MSG_ERROR_EMPTY_FILE, null, new Locale("vi")));
            res.addProperty("status", "-1");
            return res.toString();
        }
        // nếu file vượt quá dung lượng
        long fileSize = file.getSize();
        if (fileSize > maxFileSize * 1024 * 1024) {
            res.addProperty("message",
                    messageSource.getMessage(MessageConstants.MSG_ERROR_MAX_FILE_SIZE, null, new Locale("vi")));
            res.addProperty("status", "-1");
            return res.toString();
        }
        // kiểm tra đuôi mở rộng của file lstExtension
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!lstExtension.contains(extension)) {
            res.addProperty("message",
                    messageSource.getMessage(MessageConstants.MSG_ERROR_WRONG_EXTENSION_FILE, null, new Locale("vi")));
            res.addProperty("status", "-1");
            return res.toString();
        }

        // Lấy thông tin user đăng nhập
        UserEntity userEntity = commonHelper.getLoginUser();

        // tiến hành save file
        String releativePath = membershipService.saveEvidence(localtion, file, userEntity.getUserId());

        // save survey
        survey = survey.replace(pathFileKey, releativePath);
        MemberDTO memberDTO = new MemberDTO(userEntity.getUserId(), survey, userEntity.getUserId(), statusPending);
        membershipService.saveMemberShipSurvey(memberDTO);

        // xử lý thành công
        res.addProperty("status", "1");
        res.addProperty("path", releativePath);
        return res.toString();
    }

    /**
     * lưu survey
     * 
     * @param model
     * @param authentication
     * @return
     */
    @RequestMapping(value = "/savesurvey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String saveSurvey(Model model, @RequestBody String data, Authentication authentication) {
        JsonObject res = new JsonObject();
        // Lấy thông tin user đăng nhập
        UserEntity userEntity = commonHelper.getLoginUser();
        // save survey
        MemberDTO memberDTO = new MemberDTO(userEntity.getUserId(), data, userEntity.getUserId(), statusPending);
        membershipService.saveMemberShipSurvey(memberDTO);
        return res.toString();
    }

    /**
     * delete file
     * 
     * @param model
     * @param data
     * @param authentication
     * @return
     */
    @RequestMapping(value = "/deleteevidence", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String deleteEvidence(Model model, @RequestBody String data, Authentication authentication) {
        JsonObject res = new JsonObject();
        // tạp object biến truyền lên
        // dùng GsonBuilder().disableHtmlEscaping() để xử lý được utf8
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        DeleteEvidenceForm form = gson.fromJson(data, DeleteEvidenceForm.class);
        // xóa file
        membershipService.deleteFile(form.getPath(),localtion);
        // replace lại file path
        String survey = gson.toJson(form.getSurvey()).replace(form.getPath(), StringUtils.EMPTY);

        // Lấy thông tin user đăng nhập
        UserEntity userEntity = commonHelper.getLoginUser();
        // save survey
        MemberDTO memberDTO = new MemberDTO(userEntity.getUserId(), survey, userEntity.getUserId(), statusPending);
        membershipService.saveMemberShipSurvey(memberDTO);
        return res.toString();
    }

    /**
     * complete survey
     * 
     * @param model
     * @param authentication
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    @RequestMapping(value = "/completesurvey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String completeSurvey(Model model, @RequestBody String data, Authentication authentication)
            throws IllegalArgumentException, IllegalAccessException {
        JsonObject res = new JsonObject();
        // Lấy thông tin user đăng nhập
        UserEntity userEntity = commonHelper.getLoginUser();

        // kiểm tra tính hợp lệ của form
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        MemberForm form = gson.fromJson(data, MemberForm.class);
        FormSurveyValidator<MemberForm> validator = new FormSurveyValidator<MemberForm>();
        HashMap<String, String> errors = validator.validator(form);
        if (errors != null && errors.size() > 0) {
            JsonArray jsonArray = new JsonArray();
            for (Map.Entry<String, String> entry : errors.entrySet()) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", entry.getKey());
                jsonObject.addProperty("message", entry.getValue());
                jsonArray.add(jsonObject);
            }
            res.add("errors", jsonArray);
            return res.toString();
        }
        // save survey
        MemberDTO memberDTO = new MemberDTO(userEntity.getUserId(), data, userEntity.getUserId(), statuscomplete);
        membershipService.saveMemberShipSurvey(memberDTO);
        return res.toString();
    }

    /**
     * Phương thức download templa GEARS_HR_Data_Tables_Template.xlsx
     * 
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/downloadTempl")
    public ResponseEntity<InputStreamResource> getCVFile() throws IOException {

        // Lay absolute path File
        // String absolutePath =
        // this.getClass().getClassLoader().getResource(urlResourcesTempl).getPath();
        // absolutePath = URLDecoder.decode(absolutePath, "UTF-8");
        // absolutePath = absolutePath.substring(absolutePath.indexOf("/") + 1);
        // System.out.println("Path template: " + absolutePath + "\n\n");
        // Thuc hien download file
        // return CommonUtils.downloadFile(absolutePath, nameGearsHrDataTableTempl);
        // Lấy đường dẫn File templ trong resource
        // String urlFile = new
        // StringBuilder(absolutePath).append(File.separator).append(nameGearsHrDataTableTempl).toString();
        // download File
        // InputStream file = new FileInputStream(urlFile);

        // Download file templ from jar file
        InputStream file = this.getClass().getClassLoader().getResourceAsStream(urlResourcesTempl + "/" + nameGearsHrDataTableTempl);
        System.out.println("Path template: " + this.getClass().getClassLoader().getResource(urlResourcesTempl + "/" + nameGearsHrDataTableTempl).getPath() + "\n\n");
        InputStreamResource resource = new InputStreamResource(file);

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + URLEncoder.encode(nameGearsHrDataTableTempl, "UTF-8"))
                // Content-Type
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                // Contet-Length
                .body(resource);
    }

    /**
     * Submit all
     * 
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/downloadSurvey", method = RequestMethod.POST)
    public ResponseEntity<InputStreamResource> downloadSurvey(Model model,  @RequestParam(name = "surveyPath") String data,
            Authentication authentication) throws IOException {
        File theDir = new File(String.format("%s%s%s", System.getProperty("user.dir"), surveyPath,data.split("/")[0]));
        // Thuc hien download file
        return CommonUtils.downloadFile(theDir.getAbsolutePath(), data.split("/")[1]);
    }
    
    @PostMapping(value = "/submitAll")
    public String submitAll(Model model, @ModelAttribute("inputinfo") @Validated AllInputInfoForm allInputInfoForm,
                BindingResult bindingResult, RedirectAttributes redirectAttr) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("inputinfo", allInputInfoForm);
                return "membership/index";
            }
            
            //Insert report
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setUserId(commonHelper.getLoginUser().getUserId());
            memberDTO.setStatus("0");
            
            membershipService.insertReport(memberDTO);
        } catch (Exception e) {
            model.addAttribute("messageError", commonHelper.getMessage(e.getMessage()));
            return "membership/index";
        }

        return "membership/submit_success";
    }
    
    /**
     * Upload File GEARS HR Data Table Template.xlsx
     * @param file MultipartFile
     * @return
     */
    @RequestMapping(value = "/uploadFileData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    @ResponseBody
    public String doUoloadFile(@RequestBody MultipartFile file) throws IOException {

        JsonObject res = new JsonObject();
        // Nếu file chưa được nhập
        if (file == null || file.isEmpty()) {
            res.addProperty("message",
                    messageSource.getMessage(MessageConstants.MSG_ERROR_EMPTY_FILE, null, new Locale("vi")));
            res.addProperty("status", "-1");
            return res.toString();
        }
        
        // kiểm tra đuôi mở rộng của file lstExtension
        String lstExtensionInput = "xls,xlsx";
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!lstExtensionInput.contains(extension)) {
            res.addProperty("message",
                    messageSource.getMessage(MessageConstants.MSG_ERROR_FILE_EXTENSION, null, new Locale("vi")));
            res.addProperty("status", "-1");
            return res.toString();
        }

        try {
        	// Import file template
            int results = membershipService.uploadFile(file);
            if (results == 0) {
            	res.addProperty("message",
                        messageSource.getMessage(MessageConstants.MSG_ERROR_WRONG_EXTENSION_FILE, null, new Locale("vi")));
                res.addProperty("status", "-1");
                return res.toString();
            }
            
        } catch (Exception e) {
        	res.addProperty("message",
                    messageSource.getMessage(MessageConstants.MSG_UPDATE_ERROR, null, new Locale("vi")));
            res.addProperty("status", "-1");
            return res.toString();
		}
        
        // xử lý thành công
        res.addProperty("status", "1");
        return res.toString();
    }
    
    /**
     * Download report result
     * 
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/downloadReport")
    public ResponseEntity<InputStreamResource> downloadReport(Model model,  @RequestParam(name = "reportPath") String data,
            Authentication authentication) throws IOException {
        // Thuc hien download file
        return CommonUtils.downloadFile(System.getProperty("user.dir") + File.separatorChar + Constant.OUTPUT_FOLDER_PATH_NAME, data);
    }
}
