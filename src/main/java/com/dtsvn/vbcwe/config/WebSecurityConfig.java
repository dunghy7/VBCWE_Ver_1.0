package com.dtsvn.vbcwe.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration(exclude = ErrorMvcAutoConfiguration.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// config security
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// csrf disable
		http.csrf().disable();

		// non login request
		http.authorizeRequests().antMatchers("/static/**", "/css/**", "/js/**").permitAll();
		http.authorizeRequests()
				.antMatchers("/", "/login_check", "/?logout", "/error", "/freemember/signup", "/freemember/verify", "/forgot_password", "/reset_password")
				.permitAll();

		// redirect to login page
		http.authorizeRequests().anyRequest().authenticated();

		// Khi cố tình truy cập vào màn hình không thuộc quyền hạn sẽ chuyển đến màn
		// hình 403
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		// login form config
		// Cấu hình cho Login Form.
		http.authorizeRequests().and().formLogin().loginPage("/").usernameParameter("email")
				.successHandler(authenticationSuccessHandler());

		// logout page config
		http.authorizeRequests().and().logout().logoutUrl("/?logout") // Logout URL
				.permitAll(); // sau khi logout thành côn
		
		http.headers().frameOptions().disable();
		

	}

	@Bean("authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new LoginAuthenticationSuccessHandler();
	}

}