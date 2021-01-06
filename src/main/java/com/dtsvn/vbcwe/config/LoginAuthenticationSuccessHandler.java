package com.dtsvn.vbcwe.config;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.entity.UserEntity;

public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {
		if (response.isCommitted()) {
			return;
		}
		// get user login from principal
		UserEntity loginUser = (UserEntity) authentication.getPrincipal();
		if (loginUser.userHasAuthority(Constant.ROLE_FREEMEMBER)) {
			redirectStrategy.sendRedirect(request, response, "/freemember/");
		} else if (loginUser.userHasAuthority(Constant.ROLE_MEMBERSHIP)) {
			redirectStrategy.sendRedirect(request, response, "/membership/");
		} else {
			redirectStrategy.sendRedirect(request, response, "/admin/initSearchReport");
		}
		clearAuthenticationAttributes(request);

		// add user login info to session
		HttpSession session = request.getSession();
		if (loginUser instanceof UserEntity) {
			session.setAttribute(Constant.LOGIN_USER_KEY, loginUser);
		}
	}

	protected String getScreenRedirect(Map<String, String> roleMap, final Authentication authentication) {
		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (final GrantedAuthority grantedAuthority : authorities) {
			String authorityName = grantedAuthority.getAuthority();
			if (roleMap.containsKey(authorityName)) {
				return roleMap.get(authorityName);
			}
		}

		throw new IllegalStateException();
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}
