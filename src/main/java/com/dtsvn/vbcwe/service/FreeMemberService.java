package com.dtsvn.vbcwe.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.docx4j.com.google.common.base.Objects;
import org.docx4j.dml.chart.CTBarChart;
import org.docx4j.dml.chart.CTBarSer;
import org.docx4j.dml.chart.CTNumVal;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.OpcPackage;
import org.docx4j.openpackaging.packages.PresentationMLPackage;
import org.docx4j.openpackaging.packages.SpreadsheetMLPackage;
import org.docx4j.openpackaging.parts.Part;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.DrawingML.Chart;
import org.docx4j.openpackaging.parts.PresentationML.SlidePart;
import org.docx4j.openpackaging.parts.SpreadsheetML.WorksheetPart;
import org.docx4j.openpackaging.parts.WordprocessingML.EmbeddedPackagePart;
import org.docx4j.utils.BufferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xlsx4j.sml.Cell;
import org.xlsx4j.sml.Row;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.common.MessageConstants;
import com.dtsvn.vbcwe.common.exception.RollBackException;
import com.dtsvn.vbcwe.config.EmailConfig;
import com.dtsvn.vbcwe.dao.FreeMemberMapper;
import com.dtsvn.vbcwe.dto.MemberDTO;
import com.dtsvn.vbcwe.dto.MemberSurveyDTO;
import com.dtsvn.vbcwe.dto.SurveyDTO;
import com.dtsvn.vbcwe.dto.UserInfoDTO;
import com.dtsvn.vbcwe.entity.UserEntity;
import com.dtsvn.vbcwe.entity.UserInfoEntity;
import com.dtsvn.vbcwe.entity.UserLoginEntity;
import com.dtsvn.vbcwe.form.ChangePasswordForm;
import com.dtsvn.vbcwe.repository.UserInfoRepository;
import com.dtsvn.vbcwe.repository.UserLoginRepository;
import com.dtsvn.vbcwe.util.CommonUtils;
import com.dtsvn.vbcwe.util.LibreOfficeUtil;

@Service
public class FreeMemberService extends BaseService {

	@Autowired
	private EmailConfig emailConfig;

	@Autowired
	UserInfoRepository userInfoRepository;

	@Autowired
	UserLoginRepository userLoginRepository;

	@Autowired
	FreeMemberMapper freeMemberMapper;

	public boolean sendMailAccountVerify(String email, String fullName) {
		return emailConfig.sendMailAccountVerify(email, fullName);
	}

	@Transactional(rollbackFor = RollBackException.class)
	public boolean registerAccount(UserInfoDTO userInfoDTO, String password) {
		Timestamp currentTime = new Timestamp(new Date().getTime());
		UserInfoEntity infoEntity = userInfoRepository.findByUserId(userInfoDTO.getUserId());
		if (infoEntity != null) {
			throw new RollBackException(MessageConstants.MSG_ERROR_EXIST);
		}
		// Insert user info
		infoEntity = dozerMapper.map(userInfoDTO, UserInfoEntity.class);
		// Loại người dùng miễn phí
		infoEntity.setUserType(Constant.USER_TYPE_FREE);
		infoEntity.setCreatedDate(currentTime);
		infoEntity.setUpdatedDate(currentTime);
		infoEntity.setDeleteFlg(Constant.DB_AVAILABLE);
		infoEntity = userInfoRepository.save(infoEntity);
		if (infoEntity == null || infoEntity.getUserInfoId() == null) {
			throw new RollBackException(MessageConstants.MSG_ADD_ERROR);
		}

		// Insert user login
		UserLoginEntity loginEntity = new UserLoginEntity();
		loginEntity.setCreatedDate(currentTime);
		loginEntity.setUpdatedDate(currentTime);
		loginEntity.setUserId(userInfoDTO.getUserId());
		loginEntity.setDeleteFlg(Constant.DB_AVAILABLE);
		loginEntity.setEmailVerify(Constant.EMAIL_NOT_VERIFY);
		loginEntity.setPassword(CommonUtils.convert2MD5(password));

		loginEntity = userLoginRepository.save(loginEntity);
		if (loginEntity == null || loginEntity.getUserLoginId() == null) {
			throw new RollBackException(MessageConstants.MSG_ADD_ERROR);
		}

		if (sendMailAccountVerify(infoEntity.getUserId(), infoEntity.getFullName())) {
			return true;
		} else {
			throw new RollBackException(MessageConstants.MSG_ERROR_EMAIL);
		}
	}

	public String verifyAccount(String encryptedEmail, String encryptedTimestamp) {
		try {
			String decodedEmail = new String(Base64.getDecoder().decode(encryptedEmail));
			String decodedTimestamp = new String(Base64.getDecoder().decode(encryptedTimestamp));
			SimpleDateFormat sdf = new SimpleDateFormat(Constant.FORMAT_DATE_TIME_PATTERN);
			Date timestamp = sdf.parse(decodedTimestamp);
			if (timestamp.compareTo(new Date()) >= 0) {
				// Còn thời hạn active thì kiểm tra tài khoản có tồn tại và ở trạng thái chờ xác
				// thực hay không
				UserLoginEntity loginEntity = userLoginRepository.findByUserId(decodedEmail);
				if (loginEntity != null) {
					if (StringUtils.equals(Constant.EMAIL_NOT_VERIFY, loginEntity.getEmailVerify())) {
						UserInfoEntity infoEntity = userInfoRepository.findByUserId(decodedEmail);
						if (infoEntity != null) {
							if (StringUtils.equals(Constant.DB_AVAILABLE, infoEntity.getDeleteFlg())) {
								// Tồn tại tài khoản, chưa bị xóa
								loginEntity.setEmailVerify(Constant.EMAIL_VERIFIED);
								loginEntity.setUpdatedDate(new Timestamp(new Date().getTime()));
								userLoginRepository.save(loginEntity);
								return null;
							} else {
								// Thông tin tài khoản đã bị xóa logic
								return MessageConstants.MSG_ERROR_INFO_DELETED;
							}
						} else {
							// Không có thông tin tài khoản
							return MessageConstants.MSG_ERROR_INFO_NOT_FOUND;
						}
					} else {
						// Tài khoản đã được kích hoạt
						return MessageConstants.MSG_ERROR_LOGIN_VERIFIED;
					}
				} else {
					// Không có thông tin đăng nhập
					return MessageConstants.MSG_ERROR_LOGIN_NOT_FOUND;
				}
			} else {
				// Hết hạn active
				return MessageConstants.MSG_ERROR_TIMESTAMP_EXPIRED;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Lỗi hệ thống
		return MessageConstants.MSG_ERROR_EXCEPTION;
	}

	public MemberDTO loadSurveyInfo() {
		// lay thong tin user
		UserEntity userEntity = commonHelper.getLoginUser();

		return freeMemberMapper.loadFreeMember(userEntity.getUserId());
	}

	@Transactional
	public boolean insertUpdateSurveyInfo(MemberDTO freeMemberDTO) {
		// lay thong tin user
		UserEntity userEntity = commonHelper.getLoginUser();
		freeMemberDTO.setUserId(userEntity.getUserId());

		int result = 0;

		if (freeMemberMapper.loadFreeMember(userEntity.getUserId()) != null) {
			result = freeMemberMapper.updateSurveyInfo(freeMemberDTO);
		} else {
			result = freeMemberMapper.insertSurveyInfo(freeMemberDTO);
		}

		return result > 0;
	}

	public String changePassword(ChangePasswordForm changePassForm) {
		try {
			// Kiểm tra mật khẩu mới có trùng khớp không
			if (StringUtils.equals(changePassForm.getPasswordNew(), changePassForm.getRePasswordNew())) {
				// Kiểm tra email xem có tồn tại tài khoản trong hệ thống không
				UserInfoEntity userInfo = userInfoRepository.findByUserId(changePassForm.getEmail());
				UserLoginEntity userLogin = userLoginRepository.findByUserId(changePassForm.getEmail());
				if (userInfo != null && userLogin != null
						&& StringUtils.equals(Constant.DB_AVAILABLE, userInfo.getDeleteFlg())) {
					// Kiểm tra mật khẩu cũ có trùng khớp hay không
					String encryptUserPass = CommonUtils.convert2MD5(changePassForm.getPasswordOld());
					if (StringUtils.equals(encryptUserPass, userLogin.getPassword())) {
						// Mật khẩu cũ trùng khớp
						// Tiến hành cập nhật mật khẩu mới vào hệ thống
						String encryptNewPass = CommonUtils.convert2MD5(changePassForm.getPasswordNew());
						userLogin.setPassword(encryptNewPass);
						UserEntity userSignIn = getUserLogin();
						copyUpdateInfo(userLogin, userSignIn);
						userLoginRepository.save(userLogin);
						return null;
					} else {
						// Mật khẩu cũ không trùng khớp
						return MessageConstants.MSG_ERROR_OLD_PASSWORD_NOT_MATCH;
					}
				} else {
					// Tài khoản không tồn tại hoặc đã bị xóa logic trên hệ thống
					return MessageConstants.MSG_ERROR_USER_DELETED;
				}
			} else {
				// Mật khẩu mới không trùng khớp
				return MessageConstants.MSG_ERROR_NEW_PASSWORD_NOT_MATCH;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MessageConstants.MSG_ERROR_EXCEPTION;
	}

	/**
	 * Chỉnh sửa lại file template theo survey . sau đó trả về đường dẫn file pptx
	 * đã chỉnh sửa
	 * 
	 * @return đường dẫn file report
	 * @throws Exception
	 */
	public String processReportFile(MemberSurveyDTO form) throws Exception {
		// Get file template
        InputStream templateFile = new ClassPathResource("/templ/FreeMemberTemplate.pptx").getInputStream();

		// Khởi tạo đối tượng Presentation làm việc với powerpoint
		PresentationMLPackage presentationMLPackage = (PresentationMLPackage) OpcPackage.load(templateFile);

		// mapping các biến và value
		HashMap<String, String> variableReplaces = new HashMap<String, String>();
		variableReplaces.put("comName", form.getCompanyName());

		// thay thế giá trị trong slide
		List<SlidePart> slideParts = presentationMLPackage.getMainPresentationPart().getSlideParts();

		int fa = 1;
		for (int i = 0; i < slideParts.size(); i++) {
			// thực hiện replace biến thành giá trị trả lời trong vùng kết quả
			if (i == 9 || i == 10) {
				HashMap<String, String> valueReplaces = new HashMap<String, String>();
				// duyệt tất cả các câu trả lời
				for (SurveyDTO survey : form.getSurvey()) {
					if (!Objects.equal(survey.getFa(), String.valueOf(fa))) {
						continue;
					}

					valueReplaces.put("ans" + survey.getFa() + survey.getNo(),
							survey.getAnswer().equals("1") ? "Có" : "Không");
					for (int j = 0; survey.getOther() != null && j < survey.getOther().length(); j++) {
						valueReplaces.put("ans" + survey.getFa() + survey.getNo() + (j + 1),
								survey.getOther().substring(j, j + 1).equals("1") ? "Có" : "Không");
					}

					valueReplaces.putAll(variableReplaces);
				}

				slideParts.get(i).setJaxbElement(CommonUtils.variableReplace(slideParts.get(i).getJaxbElement(),
						slideParts.get(i).getJAXBContext(), null, valueReplaces));
				fa++;

			} else {
				slideParts.get(i).setJaxbElement(CommonUtils.variableReplace(slideParts.get(i).getJaxbElement(),
						slideParts.get(i).getJAXBContext(), null, variableReplaces));
			}
		}

		// lay thong tin user
		UserEntity userEntity = commonHelper.getLoginUser();

		// thay thế giá trị trong DiagramData , Chart
		CommonUtils.variableReplaceInData(presentationMLPackage, variableReplaces);

		String folderPath = System.getProperty("user.dir") + File.separatorChar + "report";

		File folder = new java.io.File(folderPath);
		if (!folder.exists()) {
			folder.mkdir();
		}

		updateDataChart(presentationMLPackage, "/ppt/charts/chart1.xml",
				"/ppt/embeddings/Microsoft_Excel_Worksheet1.xlsx",
				CommonUtils.calSurveyScore(form.getSurvey(), userEntity.getUserType()));

		System.out.println("\n\n saving .. \n\n");

		String tmpFilePath = Paths.get(folderPath, MessageFormat.format("temp_{0}.pptx", userEntity.getUserId()))
				.toString();
		File tmpFile = new File(tmpFilePath);

		presentationMLPackage.save(tmpFile);

		System.out.println("\n\n done .. \n\n");

		String fileName = "{0}_{1}.pdf";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		fileName = MessageFormat.format(fileName, simpleDateFormat.format(new Date()), userEntity.getUserId());

		String outputfilepath = Paths.get(folderPath, fileName).toString();

		System.out.println(outputfilepath);

		System.out.println("\n\n convert pdf .. \n\n");

		LibreOfficeUtil.convertOffice2PDFSyncIsSuccess(tmpFile, new File(outputfilepath));

		System.out.println("\n\n done convertr pdf .. \n\n");
		if (tmpFile.delete()) {
			System.out.println("Deleted the folder: " + tmpFile.getName());
		} else {
			System.out.println("Failed to delete the folder.");
		}

		return fileName;
	}

	private void updateDataChart(PresentationMLPackage ppt, String chartPartName, String xlsPartName,
			HashMap<String, Long> surveyScore) throws Docx4JException {
		if (surveyScore == null)
			return;

		String fa5 = surveyScore.get("1").toString();
		String fa6 = surveyScore.get("2").toString();
		/*
		 * Get the Chart object and update the values. Afterwards, we'll update the
		 * associated spreadsheet so that the data is synchronized.
		 */
		Chart chart = (Chart) ppt.getParts().get(new PartName(chartPartName));

		List<Object> objects = chart.getJaxbElement().getChart().getPlotArea().getAreaChartOrArea3DChartOrLineChart();

		for (Object object : objects) {

			if (object instanceof CTBarChart) {

				List<CTBarSer> ctBarSers = ((CTBarChart) object).getSer();

				for (CTBarSer ctBarSer : ctBarSers) {
					List<CTNumVal> ctNumVals = ctBarSer.getVal().getNumRef().getNumCache().getPt();
					for (CTNumVal ctNumVal : ctNumVals) {
						if (ctNumVal.getIdx() == 0) {
							ctNumVal.setV(fa5);
						} else if (ctNumVal.getIdx() == 1) {
							ctNumVal.setV(fa6);
						}
					}
				}
			}
		}

		/*
		 * Get the spreadsheet and find the cell values that need to be updated
		 */

		EmbeddedPackagePart epp = (EmbeddedPackagePart) ppt.getParts().get(new PartName(xlsPartName));

		if (epp == null) {
			throw new Docx4JException("Could find EmbeddedPackagePart: " + xlsPartName);
		}

		InputStream is = BufferUtil.newInputStream(epp.getBuffer());

		SpreadsheetMLPackage spreadSheet = (SpreadsheetMLPackage) SpreadsheetMLPackage.load(is);

		Map<PartName, Part> partsMap = spreadSheet.getParts().getParts();
		Iterator<Entry<PartName, Part>> it = partsMap.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry<PartName, Part> pairs = it.next();

			if (partsMap.get(pairs.getKey()) instanceof WorksheetPart) {

				WorksheetPart wsp = (WorksheetPart) partsMap.get(pairs.getKey());

				List<Row> rows = wsp.getJaxbElement().getSheetData().getRow();

				for (Row row : rows) {
					List<Cell> cells = row.getC();
					for (Cell cell : cells) {
						if (cell.getR().equals("B2") && cell.getV() != null) {
							// change the B2 cell value
							cell.setV(fa5);
						} else if (cell.getR().equals("B3") && cell.getV() != null) {
							// Change the B3 cell value
							cell.setV(fa6);
						}
					}
				}
			}
		}

		/*
		 * Convert the Spreadsheet to a binary format, set it on the
		 * EmbeddedPackagePart, add it back onto the deck and save to a file.
		 * 
		 */
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		SaveToZipFile saver = new SaveToZipFile(spreadSheet);

		saver.save(baos);
		epp.setBinaryData(baos.toByteArray());

	}
}
