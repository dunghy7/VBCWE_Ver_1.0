package com.dtsvn.vbcwe.config;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.util.AntPathMatcher;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.StandardTemplateModeHandlers;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.service.AnalysisService;
import com.google.gson.Gson;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
@EnableWebMvc
@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
@EnableJpaRepositories("com.dtsvn.vbcwe.repository")
@ComponentScan("com.dtsvn.vbcwe.config")
@EntityScan("com.dtsvn.vbcwe.entity")
@PropertySource(value = { "classpath:mybatis.properties" }, encoding = Constant.UTF_8_CHARSET)
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	// Page size display on table
	@Value("${admin.table.maxPageSize}")
	private int maxPageSize;

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		AntPathMatcher matcher = new AntPathMatcher();
		matcher.setCaseSensitive(false);
		configurer.setPathMatcher(matcher);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/").setCachePeriod(3600)
				.resourceChain(true).addResolver(new PathResourceResolver());
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
	
	@Autowired
	private ApplicationContext _applicationContext;
	
	@EventListener(ApplicationReadyEvent.class)
	public void startApp() {
		AnalysisService service = (AnalysisService) _applicationContext.getBean(AnalysisService.class);
		service.init();
	}

//	@Bean
//	public AnalysisService analysService() {
//		return new AnalysisService();
//	}
	
	@Bean(name = "org.dozer.Mapper")
	public DozerBeanMapper dozerBean() {
		List<String> mappingFiles = Arrays.asList("dozer-configuration.xml");
		DozerBeanMapper dozerBean = new DozerBeanMapper();
		dozerBean.setMappingFiles(mappingFiles);
		return dozerBean;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.addTemplateResolver(webTemplateResolver());
		engine.addTemplateResolver(mailTemplateResolver());
		engine.setTemplateEngineMessageSource(messageSource());
		engine.addDialect(new LayoutDialect());
		engine.addDialect(new SpringSecurityDialect());
		return engine;
	}

	// web layout config
	@Bean
	public ClassLoaderTemplateResolver webTemplateResolver() {
		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
		resolver.setPrefix("public/");
		resolver.setSuffix(".html");
		resolver.setCharacterEncoding(Constant.UTF_8_CHARSET);
		resolver.setTemplateMode(StandardTemplateModeHandlers.HTML5.getTemplateModeName());
		resolver.setCacheable(false);
		resolver.setOrder(1);
		return resolver;
	}

	@Bean
	public ClassLoaderTemplateResolver mailTemplateResolver() {
		ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
		emailTemplateResolver.setPrefix("templ/");
		emailTemplateResolver.setSuffix(".html");
		emailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
		emailTemplateResolver.setTemplateMode(StandardTemplateModeHandlers.HTML5.getTemplateModeName());
		emailTemplateResolver.setCacheable(false);
		emailTemplateResolver.setOrder(2);
		return emailTemplateResolver;
	}

	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setOrder(1);
		viewResolver.setCharacterEncoding(Constant.UTF_8_CHARSET);
		viewResolver.setCache(false);
		return viewResolver;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("vi"));
		return localeResolver;
	}

	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:i18n/message", "classpath:i18n/validate-message");
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding(Constant.UTF_8_CHARSET);
		messageSource.setCacheSeconds(0);
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(validMessageSource());
		return bean;
	}

	@Bean
	public MessageSource validMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:i18n/validate-message");
		messageSource.setDefaultEncoding(Constant.UTF_8_CHARSET);
		messageSource.setCacheSeconds(0);
		return messageSource;
	}

	@Bean
	public Gson getGson() {
		return new Gson();
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
		resolver.setFallbackPageable(new PageRequest(1, maxPageSize));
		argumentResolvers.add(resolver);
		super.addArgumentResolvers(argumentResolvers);
	}

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
        localeInterceptor.setParamName("lang");

        registry.addInterceptor(localeInterceptor).addPathPatterns("/**");
    }
}
