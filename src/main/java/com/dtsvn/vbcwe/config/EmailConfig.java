package com.dtsvn.vbcwe.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.common.MessageConstants;
import com.dtsvn.vbcwe.util.LibreOfficeUtil;

@Configuration("emailConfig")
public class EmailConfig {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private DateFormat dateFormat = new SimpleDateFormat(Constant.FORMAT_DATE_TIME_PATTERN);

	@Value("${vbcwe.mail.verify.from}")
	private String mailFrom;

	@Value("${vbcwe.mail.verify.url}")
	private String mailUrl;

	@Value("${spring.mail.username}")
	private String mailUsername;

	@Value("${spring.mail.password}")
	private String mailPassword;

	@Value("${spring.mail.host}")
	private String mailHost;

	@Value("${spring.mail.port}")
	private String mailPort;

	@Value("${spring.mail.properties.mail.transport.protocol}")
	private String mailProtocol;

	@Value("${spring.mail.properties.mail.smtp.auth}")
	private String mailAuth;

	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private String mailStartTls;

	@Value("${spring.mail.properties.mail.smtp.ssl.trust}")
	private String mailTrust;

	@Autowired
	private JavaMailSender sender;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private MessageSource messageSource;

	@Value("classpath:/static/images/logo.png")
	Resource logoFile;
	
	@Value("${vbcwe.gears.address}")
	private String serverAddress;

	private static final String ACTIVE_MAIL_TEMPLATE = "ActiveMailTemplate";
	private static final String ADD_ACCOUNT_TEMPLATE = "AddAccountTemplate";
	private static final String SEND_REPORT_TEMPLATE = "SendReportTemplate";
	private static final String UPGRADE_ACCOUNT_TEMPLATE = "UpgradeAccountTemplate";
	private static final String RESET_PASSWORD_TEMPLATE = "ResetPasswordTemplate";

	public boolean sendUpgradeAccount(String emailTo, String fullName, String phone, String companyName) {
        try {
			// Prepare the evaluation context
			final Context ctx = new Context(Locale.getDefault());
            ctx.setVariable("Logo", getImageLogo());
			ctx.setVariable("FullName", fullName);
			ctx.setVariable("Email", emailTo);
			ctx.setVariable("Phone", phone);
			ctx.setVariable("CompanyName", companyName);
			final String htmlContent = templateEngine.process(UPGRADE_ACCOUNT_TEMPLATE, ctx);
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, Constant.UTF_8_CHARSET);
			helper.setSubject(MimeUtility.encodeText(
					messageSource.getMessage(MessageConstants.MAIL_TITLE_UPGRADE_ACCOUNT, null, Locale.getDefault()),
					Constant.UTF_8_CHARSET, "Q"));
			helper.setFrom(new InternetAddress(mailFrom,
					messageSource.getMessage(MessageConstants.MAIL_FROM_ALIAS, null, Locale.getDefault())));
			helper.setTo(emailTo);
			helper.setText(htmlContent, true);
			sender.send(message);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean sendReport(String emailTo, String fullName, String attachFileName) {
        try {
            // Convert file to pdf
            String folderPath = System.getProperty("user.dir") + File.separatorChar + Constant.OUTPUT_FOLDER_PATH_NAME;
            File attachFile = new File(folderPath + File.separatorChar + attachFileName);
            File attachFilePdf = new File(folderPath + File.separatorChar + attachFileName.replace(".pptx", ".pdf"));
            LibreOfficeUtil.convertOffice2PDFSyncIsSuccess(attachFile, attachFilePdf);
			// Prepare the evaluation context
			final Context ctx = new Context(new Locale("vi"));
            ctx.setVariable("Logo", getImageLogo());
			ctx.setVariable("FullName", fullName);
			ctx.setVariable("Email", emailTo);
			final String htmlContent = templateEngine.process(SEND_REPORT_TEMPLATE, ctx);
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, Constant.UTF_8_CHARSET);
			helper.setSubject(MimeUtility.encodeText(
					messageSource.getMessage(MessageConstants.MAIL_TITLE_REPORT, null, Locale.getDefault()),
					Constant.UTF_8_CHARSET, "Q"));
			helper.setFrom(new InternetAddress(mailFrom,
					messageSource.getMessage(MessageConstants.MAIL_FROM_ALIAS, null, Locale.getDefault())));
			helper.setTo(emailTo);
			helper.setText(htmlContent, true);
            helper.addAttachment(attachFilePdf.getName(), attachFilePdf);
			sender.send(message);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean sendAddAccount(String emailTo, String fullName, String password, String accountType) {
        try {
			// Prepare the evaluation context
			final Context ctx = new Context(new Locale("vi"));
            ctx.setVariable("Logo", getImageLogo());
			ctx.setVariable("FullName", fullName);
			ctx.setVariable("Email", emailTo);
			ctx.setVariable("Password", password);
			//ctx.setVariable("AccountType", accountType);
			ctx.setVariable("VerifyUrl", serverAddress);
			final String htmlContent = templateEngine.process(ADD_ACCOUNT_TEMPLATE, ctx);
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, Constant.UTF_8_CHARSET);
			helper.setSubject(MimeUtility.encodeText(
					messageSource.getMessage(MessageConstants.MAIL_TITLE_ADD_ACCOUNT, null, Locale.getDefault()),
					Constant.UTF_8_CHARSET, "Q"));
			helper.setFrom(new InternetAddress(mailFrom,
					messageSource.getMessage(MessageConstants.MAIL_FROM_ALIAS, null, Locale.getDefault())));
			helper.setTo(emailTo);
			helper.setText(htmlContent, true);
			sender.send(message);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean sendResetPassword(String emailTo, String fullName, String password) {
        try {
			// Prepare the evaluation context
			final Context ctx = new Context(new Locale("vi"));
            ctx.setVariable("Logo", getImageLogo());
			ctx.setVariable("FullName", fullName);
			ctx.setVariable("Email", emailTo);
			ctx.setVariable("Password", password);
			final String htmlContent = templateEngine.process(RESET_PASSWORD_TEMPLATE, ctx);
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, Constant.UTF_8_CHARSET);
			helper.setSubject(MimeUtility.encodeText(
					messageSource.getMessage(MessageConstants.MAIL_TITLE_RESET_PASSWORD, null, Locale.getDefault()),
					Constant.UTF_8_CHARSET, "Q"));
			helper.setFrom(new InternetAddress(mailFrom,
					messageSource.getMessage(MessageConstants.MAIL_FROM_ALIAS, null, Locale.getDefault())));
			helper.setTo(emailTo);
			helper.setText(htmlContent, true);
			sender.send(message);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean sendMailAccountVerify(String emailTo, String fullName) {
        try {
			// Thời gian hiệu lực của URL là 10'
			Date date = DateUtils.addMinutes(new Date(), 10);
			String currentTime = dateFormat.format(date);
			String encryptedTimestamp = Base64.getEncoder().encodeToString(currentTime.getBytes());
			String encryptedEmail = Base64.getEncoder().encodeToString(emailTo.getBytes());
			mailUrl = mailUrl.replace("<<EncryptedEmail>>", encryptedEmail);
			mailUrl = mailUrl.replace("<<EncryptedTimestamp>>", encryptedTimestamp);
			// Prepare the evaluation context
			final Context ctx = new Context(new Locale("vi"));
            ctx.setVariable("Logo", getImageLogo());
			ctx.setVariable("FullName", fullName);
			ctx.setVariable("VerifyUrl", mailUrl);
			final String htmlContent = templateEngine.process(ACTIVE_MAIL_TEMPLATE, ctx);
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, Constant.UTF_8_CHARSET);
			helper.setSubject(MimeUtility.encodeText(
					messageSource.getMessage(MessageConstants.MAIL_TITLE_ACTIVE_ACCOUNT, null, Locale.getDefault()),
					Constant.UTF_8_CHARSET, "Q"));
			helper.setFrom(new InternetAddress(mailFrom,
					messageSource.getMessage(MessageConstants.MAIL_FROM_ALIAS, null, Locale.getDefault())));
			helper.setTo(emailTo);
			helper.setText(htmlContent, true);
			sender.send(message);
			return true;
		} catch (MailException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setDefaultEncoding(Constant.UTF_8_CHARSET);
		javaMailSender.setUsername(mailUsername);
		javaMailSender.setPassword(mailPassword);
		javaMailSender.setHost(mailHost);
		javaMailSender.setPort(Integer.parseInt(mailPort));

		Properties properties = javaMailSender.getJavaMailProperties();
		properties.put("mail.transport.protocol", mailProtocol);
		properties.put("mail.smtp.auth", mailAuth);
		properties.put("mail.smtp.starttls.enable", mailStartTls);
		properties.put("mail.smtp.ssl.trust", mailTrust);
		return javaMailSender;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer ppc = new PropertySourcesPlaceholderConfigurer();
		Resource[] resources = new ClassPathResource[] { new ClassPathResource("application.properties") };
		ppc.setLocations(resources);
		ppc.setIgnoreUnresolvablePlaceholders(true);
		return ppc;
	}

    private String getImageLogo() {
        String result = "";
        try (InputStream in = logoFile.getURL().openStream()) {
            result = Base64.getEncoder().encodeToString(IOUtils.toByteArray(in));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return result;
    }
}
