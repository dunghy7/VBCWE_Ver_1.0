package com.dtsvn.vbcwe.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dtsvn.vbcwe.common.CommonHelper;
import com.dtsvn.vbcwe.common.MessageConstants;
import com.dtsvn.vbcwe.entity.UserEntity;
import com.dtsvn.vbcwe.form.LoginForm;
import com.dtsvn.vbcwe.form.ResetPasswordForm;
import com.dtsvn.vbcwe.service.UserDetailsServiceImpl;
import com.dtsvn.vbcwe.util.CommonUtils;

@Controller
public class LoginController {

//	@Autowired
//	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
    @Autowired
    protected CommonHelper commonHelper;

	@Resource(name = "authenticationManager")
	private AuthenticationManager authManager;

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("loginForm", new LoginForm());
	}

	/**
	 * Load Form
	 * 
	 * @param model
	 * @param table1Form
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, LoginForm loginForm) {
		return "login";
	}

	/**
	 * Logout
	 * 
	 * @param model
	 * @param table1Form
	 * @return
	 */
	@RequestMapping(value = "/?logout", method = RequestMethod.GET)
	public String logout(Model model, LoginForm loginForm, HttpSession session) {
		session.invalidate();
		return "redirect:/login?status=logout";
	}

	/**
	 * Đăng nhập hệ thống
	 * 
	 * @param model
	 * @param table1Form
	 * @return
	 */
	@RequestMapping(value = "/login_check", method = RequestMethod.POST)
	public String loginCheck(@Validated LoginForm loginForm, BindingResult bindingResult,
			final HttpServletRequest request, final HttpServletResponse response) {
		if (bindingResult.hasErrors()) {
			return "login";
		} else {
			try {
				UserDetails userValid = userDetailsServiceImpl.loadUserByUsername(loginForm.getEmail());
				if (userValid != null) {
					UserEntity user = (UserEntity) userValid;
					String passwordMd5 = CommonUtils.toMd5Encode(loginForm.getPassword());
					if (StringUtils.equals(user.getPassword(), passwordMd5)) {
						UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
								loginForm.getEmail(), passwordMd5);
						Authentication auth = authManager.authenticate(authReq);
						SecurityContext sc = SecurityContextHolder.getContext();
						sc.setAuthentication(auth);
						HttpSession session = request.getSession(true);
						session.setAttribute("SPRING_SECURITY_CONTEXT", sc);
						authenticationSuccessHandler.onAuthenticationSuccess(request, response, auth);
					} else {
						bindingResult.rejectValue("email", MessageConstants.MSG_ACCOUNT_INVALID, "Tài khoản hoặc mật khẩu không hợp lệ");
					}
				} else {
					bindingResult.rejectValue("email", MessageConstants.MSG_ACCOUNT_INVALID, "Tài khoản hoặc mật khẩu không hợp lệ");
				}
			} catch (UsernameNotFoundException | BadCredentialsException e) {
				bindingResult.rejectValue("email", MessageConstants.MSG_ACCOUNT_INVALID, "Tài khoản hoặc mật khẩu không hợp lệ");
			} catch (Exception e) {
				bindingResult.rejectValue("email", MessageConstants.MSG_ERROR_SYSTEM, "Lỗi hệ thống");
			}
		}
		return "login";
	}

	/**
	 * Load Form
	 * 
	 * @param model
	 * @param table1Form
	 * @return
	 */
	@RequestMapping(value = "/forgot_password", method = RequestMethod.GET)
	public String initResetPassword(Model model, ResetPasswordForm resetPasswordForm) {
		return "reset_password";
	}
	
	/**
	 * Đăng nhập hệ thống
	 * 
	 * @param model
	 * @param table1Form
	 * @return
	 */
	@RequestMapping(value = "/reset_password", method = RequestMethod.POST)
	public String resetPassword(Model model, @Validated ResetPasswordForm resetPasswordForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "reset_password";
		}
		
		try {
			userDetailsServiceImpl.resetPassword(resetPasswordForm.getEmail());
		} catch (Exception e) {
			model.addAttribute("messageError", commonHelper.getMessage(e.getMessage()));
            return "reset_password";
		}
			
		return "reset_password_success";
	}
}
