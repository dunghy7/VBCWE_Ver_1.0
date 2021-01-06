package com.dtsvn.vbcwe.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dtsvn.vbcwe.common.CommonHelper;
import com.dtsvn.vbcwe.common.exception.RollBackException;
import com.dtsvn.vbcwe.controller.validator.FormSurveyValidator;
import com.dtsvn.vbcwe.dto.MemberDTO;
import com.dtsvn.vbcwe.dto.MemberSurveyDTO;
import com.dtsvn.vbcwe.dto.UserInfoDTO;
import com.dtsvn.vbcwe.entity.UserEntity;
import com.dtsvn.vbcwe.form.ChangePasswordForm;
import com.dtsvn.vbcwe.form.MemberForm;
import com.dtsvn.vbcwe.form.SignupForm;
import com.dtsvn.vbcwe.service.FreeMemberService;
import com.dtsvn.vbcwe.util.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Controller
@RequestMapping("/freemember")
public class FreeMemberController {

	@Autowired
	protected DozerBeanMapper dozerMapper;

	@Autowired
	protected CommonHelper commonHelper;
	
	@Autowired
	private FreeMemberService freememberService;
	
	@Value("${vbcwe.url.upgrade.account}")
	private String urlUpgradeAccount;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, Authentication authentication) {

		MemberDTO freeMemberDTO = freememberService.loadSurveyInfo();

		if (freeMemberDTO != null) {
			model.addAttribute("data", freeMemberDTO.getInputData());
		}

		return "free-member/index";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(@ModelAttribute(name = "signupForm") SignupForm signupForm) {
		return "free-member/signup";
	}

	@RequestMapping(value = "/changepass", method = RequestMethod.GET)
	public String changePassword(@ModelAttribute(name = "changePasswordForm") ChangePasswordForm changePasswordForm,
			Authentication userAuth) {
		if (userAuth != null) {
			UserEntity userLog = (UserEntity) userAuth.getPrincipal();
			if (userLog != null) {
				changePasswordForm.setLoggedIn(true);
				changePasswordForm.setEmail(userLog.getUserId());
			}
		}
        return "change_password";
	}

	@RequestMapping(value = "/changepass", method = RequestMethod.POST)
	public String doChangePassword(Model model,
			@ModelAttribute(name = "changePasswordForm") @Validated ChangePasswordForm changePasswordForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
            return "change_password";
		}
		String changeResult = freememberService.changePassword(changePasswordForm);
		if (changeResult == null) {
            return "change-password-success";
		} else {
			model.addAttribute("messageError", changeResult);
            return "change_password";
		}
	}

	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	public String verify(Model model, @RequestParam("email") String email,
			@RequestParam("timestamp") String timestamp) {
		if (StringUtils.isNotBlank(email) && StringUtils.isNotBlank(timestamp)) {
			// Check email và timestamp hợp lệ không
			String verifyResult = freememberService.verifyAccount(email, timestamp);
			// Xác thực email lỗi
			if (verifyResult != null) {
				model.addAttribute("messageError", verifyResult);
				model.addAttribute("signupForm", new SignupForm());
				return "free-member/signup";
			}
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String doSignup(Model model, @ModelAttribute(name = "signupForm") @Validated SignupForm signupForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "free-member/signup";
		}
		UserInfoDTO userInfo = dozerMapper.map(signupForm, UserInfoDTO.class);
		userInfo.setUserId(signupForm.getEmail());
		try {
			if (freememberService.registerAccount(userInfo, signupForm.getPassword())) {
				return "free-member/sign-success";
			}
		} catch (RollBackException e) {
			model.addAttribute("messageError", e.getMessage());
		}
		model.addAttribute("signupForm", signupForm);
		return "free-member/signup";
	}

	@RequestMapping(value = "/submitSurvey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String submitSurvey(Model model, @RequestBody String data) throws MappingException, Exception {
		JsonObject res = new JsonObject();
		Gson gson = new Gson();
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

		String fileOutputName = freememberService.processReportFile(dozerMapper.map(form, MemberSurveyDTO.class));

		MemberDTO freeMemberDTO = new MemberDTO();
		freeMemberDTO.setInputData(data);

		freememberService.insertUpdateSurveyInfo(freeMemberDTO);

		res.addProperty("fileName", fileOutputName);
		return res.toString();
	}

	@RequestMapping(value = "/files/{filename:.+}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<org.springframework.core.io.Resource> serveFile(@PathVariable String filename)
			throws Exception {
		File file = new File(
				System.getProperty("user.dir") + File.separatorChar + "Report" + File.separatorChar + filename);
		if (!file.exists()) {
			throw new Exception("File not found");
		}
		org.springframework.core.io.Resource resource = new UrlResource(file.toURI());

		HttpHeaders headers = new HttpHeaders();
		headers.remove("X-Frame-Options");
		headers.add("X-Frame-Options", "AllowAll");
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/downloadFileReport", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> downloadFileReport(
			@RequestParam(value = "fileName", defaultValue = "") String fileName) {

		return CommonUtils.downloadFile(System.getProperty("user.dir") + File.separatorChar + "Report", fileName);
	}

	@RequestMapping(value = "/upgradeAccount", method = RequestMethod.GET)
	public String upgradeAccount() {
		return "redirect:" + urlUpgradeAccount;
	}
}
