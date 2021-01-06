package com.dtsvn.vbcwe.service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.common.MessageConstants;
import com.dtsvn.vbcwe.common.exception.RollBackException;
import com.dtsvn.vbcwe.dao.InBrdCompMapper;
import com.dtsvn.vbcwe.dao.InCareerMapper;
import com.dtsvn.vbcwe.dao.InEmplMapper;
import com.dtsvn.vbcwe.dao.InEmplNvmtMapper;
import com.dtsvn.vbcwe.dao.MembershipMapper;
import com.dtsvn.vbcwe.dto.AllInputInfoDTO;
import com.dtsvn.vbcwe.dto.GearsDataTable1DTO;
import com.dtsvn.vbcwe.dto.GearsDataTable2DTO;
import com.dtsvn.vbcwe.dto.GearsDataTable3DTO;
import com.dtsvn.vbcwe.dto.GearsDataTable4DTO;
import com.dtsvn.vbcwe.dto.GearsDataTable4SubDTO;
import com.dtsvn.vbcwe.dto.InCareerDTO;
import com.dtsvn.vbcwe.dto.MemberDTO;
import com.dtsvn.vbcwe.dto.ReportDTO;
import com.dtsvn.vbcwe.entity.InBrdCompEntity;
import com.dtsvn.vbcwe.entity.InEmplEntity;
import com.dtsvn.vbcwe.entity.InEmplNvmtEntity;
import com.dtsvn.vbcwe.entity.ReportEntity;
import com.dtsvn.vbcwe.entity.UserEntity;
import com.dtsvn.vbcwe.util.CommonUtils;
import com.dtsvn.vbcwe.util.ExcelUtils;
import com.google.gson.reflect.TypeToken;

@Service
public class MembershipService extends BaseService {

	@Autowired
	MembershipMapper membershipMapper;
	
	@Autowired
    InEmplNvmtMapper inEmplNvmtMapper;
	
	@Autowired
	InEmplMapper inEmplMapper;
	
	@Autowired
	InBrdCompMapper inBrdCompMapper;
	
	@Autowired
	InCareerMapper inCareerMapper;
	
	//SHEET
	private static final String SHEET_TABLE_1 = "Bảng1_Dịch chuyển lao động";
	private static final String SHEET_TABLE_2 = "Bảng2_Việc làm";
	private static final String SHEET_TABLE_3 = "Bảng3_Ban lãnh đạo";
	private static final String SHEET_TABLE_4_1 = "Bảng4.1_Quản lý";
	private static final String SHEET_TABLE_4_2 = "Bảng4.2_Chuyên gia";
	private static final String SHEET_TABLE_4_3 = "Bảng4.3_Kỹ thuật viên và trợ lý";
	private static final String SHEET_TABLE_4_4 = "Bảng4.4_Nhân viên trợ lý VP";
	private static final String SHEET_TABLE_4_5 = "Bảng4.5_Nhân viên dịch vụ";
	private static final String SHEET_TABLE_4_6 = "Bảng4.6_Lao động Nông,Lâm,TS";
	private static final String SHEET_TABLE_4_7 = "Bảng4.7_Lao động thủ công";
	private static final String SHEET_TABLE_4_8 = "Bảng4.8_Thợ lắp ráp";
	private static final String SHEET_TABLE_4_9 = "Bảng4.9_Lao động giản đơn";
	
	/**
	 * 
	 * @param memberDTO
	 * @return
	 */
	public Integer saveMemberShipSurvey(MemberDTO memberDTO) {
		int result = membershipMapper.insertSurveyInfo(memberDTO);
		return result;
	}

	/**
	 * lấy survey theo memid
	 * 
	 * @return
	 */
	public MemberDTO loadSurveyInfo() {
		// lay thong tin user
		UserEntity userEntity = commonHelper.getLoginUser();
		MemberDTO memberDTO = membershipMapper.loadSurveyInfo(userEntity.getUserId());
		return memberDTO;
	}
	
	/**
	 * Lấy thông tin report
	 * 
	 * @return
	 */
	public ReportDTO loadReportInfo() {
		ReportEntity reportEntity = membershipMapper.findOneReportById(commonHelper.getLoginUser().getUserId());
		ReportDTO reportDTO = new ReportDTO();
		if (reportEntity != null) {
			reportDTO = dozerMapper.map(reportEntity, ReportDTO.class);
		}
		return reportDTO;
	}

	/**
	 * save file to server
	 * 
	 * @param localtion
	 * @param file
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	public String saveEvidence(String localtion, MultipartFile file, String userId) throws IOException {
		// tiến hành save file
		byte[] bytes = file.getBytes();
		String directory = String.format("%s%s%s%s", localtion, "/", userId, "/");
		File theDir = new File(String.format("%s%s", System.getProperty("user.dir"), directory));
		// kiểm tra nếu chưa có thư mục thì tạo thư mục
		if (!theDir.exists()) {
			theDir.mkdirs();
		}
		Long crTime = System.currentTimeMillis();
		String releativePath = String.format("%s%s%s%s", directory, crTime, "_",
				file.getOriginalFilename());
		Path path = Paths.get(System.getProperty("user.dir") + releativePath);
		Files.write(path, bytes);
		return String.format("%s%s%s%s%s", userId, "/", crTime, "_", file.getOriginalFilename());
	}

	/**
	 * xóa file nếu tồn tại
	 * 
	 * @param dir
	 */
	public void deleteFile(String dir, String location) {
		// kiểm tra file có tồn tại không nếu có tồn tại thì tiến hành xóa
		File file = new File(String.format("%s%s%s%s", System.getProperty("user.dir"),location,"/", dir));
		if(file.exists()) {
			file.delete();
		}
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public AllInputInfoDTO getAllInputInfo(String userId) {
		return membershipMapper.getAllInputInfo(userId);
	}

	/**
	 * upload file
	 * 
	 * @param dir
	 * @throws IOException 
	 */
	public int uploadFile(MultipartFile fileUpload) throws IOException {

		int checkProcessSheet = 0;
		// lấy thông tin user
		UserEntity userEntity = commonHelper.getLoginUser();
		// lấy định dạng file excel
		String extension = FilenameUtils.getExtension(fileUpload.getOriginalFilename());
		Workbook workbook = null;
        if (StringUtils.equals(extension, "xlsx")) {
            // Trương hợp file là dịnh dạng ".xlsx"
            workbook = new XSSFWorkbook(fileUpload.getInputStream());
        } else if (StringUtils.equals(extension, "xls")) {
            // Trường hợp file là định dạng ".xls"
            workbook = new HSSFWorkbook(fileUpload.getInputStream());
        }
        // Đọc thông tin tại sheet [Bảng1_Dịch chuyển lao động]
		Sheet sheetTable1 = (Sheet) workbook.getSheet(SHEET_TABLE_1);
		if (sheetTable1 != null) {
			processSheetTable1(workbook, sheetTable1, userEntity);
			checkProcessSheet++;
		}
		// Đọc thông tin tại sheet [Bảng2_Việc làm]
        Sheet sheetTable2 = (Sheet) workbook.getSheet(SHEET_TABLE_2);
        if (sheetTable2 != null) {
        	processSheetTable2(workbook, sheetTable2, userEntity);
        	checkProcessSheet++;
        }
        // Đọc thông tin tại sheet [Bảng3_Ban lãnh đạo]
        Sheet sheetTable3 = (Sheet) workbook.getSheet(SHEET_TABLE_3);
        if (sheetTable3 != null) {
        	processSheetTable3(workbook, sheetTable3, userEntity);
        	checkProcessSheet++;
        }
        // Đọc thông tin tại sheet [Bảng4.1_Quản lý]
        Sheet sheetTable4Sub1 = (Sheet) workbook.getSheet(SHEET_TABLE_4_1);
        if (sheetTable4Sub1 != null) {
        	processSheetTable4Sub1(workbook, sheetTable4Sub1, userEntity);
        	checkProcessSheet++;
        }
        // Đọc thông tin tại sheet [Bảng4.2_Chuyên gia]
        Sheet sheetTable4Sub2 = (Sheet) workbook.getSheet(SHEET_TABLE_4_2);
        if (sheetTable4Sub2 != null) {
        	processSheetTable4Sub2(workbook, sheetTable4Sub2, userEntity);
        	checkProcessSheet++;
        }
        // Đọc thông tin tại sheet [Bảng4.3_Kỹ thuật viên và trợ lý]
        Sheet sheetTable4Sub3 = (Sheet) workbook.getSheet(SHEET_TABLE_4_3);
        if (sheetTable4Sub3 != null) {
        	processSheetTable4Sub3(workbook, sheetTable4Sub3, userEntity);
        	checkProcessSheet++;
        }
        // Đọc thông tin tại sheet [Bảng4.4_Nhân viên trợ lý VP]
        Sheet sheetTable4Sub4 = (Sheet) workbook.getSheet(SHEET_TABLE_4_4);
        if (sheetTable4Sub4 != null) {
        	processSheetTable4Sub4(workbook, sheetTable4Sub4, userEntity);
        	checkProcessSheet++;
        }
        // Đọc thông tin tại sheet [Bảng4.5_Nhân viên dịch vụ]
        Sheet sheetTable4Sub5 = (Sheet) workbook.getSheet(SHEET_TABLE_4_5);
        if (sheetTable4Sub5 != null) {
        	processSheetTable4Sub5(workbook, sheetTable4Sub5, userEntity);
        	checkProcessSheet++;
        }
        // Đọc thông tin tại sheet [Bảng4.6_Lao động Nông,Lâm,TS]
        Sheet sheetTable4Sub6 = (Sheet) workbook.getSheet(SHEET_TABLE_4_6);
        if (sheetTable4Sub6 != null) {
        	processSheetTable4Sub6(workbook, sheetTable4Sub6, userEntity);
        	checkProcessSheet++;
        }
        // Đọc thông tin tại sheet [Bảng4.7_Lao động thủ công]
        Sheet sheetTable4Sub7 = (Sheet) workbook.getSheet(SHEET_TABLE_4_7);
        if (sheetTable4Sub7 != null) {
        	processSheetTable4Sub7(workbook, sheetTable4Sub7, userEntity);
        	checkProcessSheet++;
        }
        // Đọc thông tin tại sheet [Bảng4.8_Thợ lắp ráp]
        Sheet sheetTable4Sub8 = (Sheet) workbook.getSheet(SHEET_TABLE_4_8);
        if (sheetTable4Sub8 != null) {
        	processSheetTable4Sub8(workbook, sheetTable4Sub8, userEntity);
        	checkProcessSheet++;
        }
        // Đọc thông tin tại sheet [Bảng4.9_Lao động giản đơn]
        Sheet sheetTable4Sub9 = (Sheet) workbook.getSheet(SHEET_TABLE_4_9);
        if (sheetTable4Sub9 != null) {
        	processSheetTable4Sub9(workbook, sheetTable4Sub9, userEntity);
        	checkProcessSheet++;
        }
        
        return checkProcessSheet;
	}
	
	/**
	 * Xử lý đọc dữ liệu của sheet [Bảng1_Dịch chuyển lao động]
	 * @param workbook Workbook
	 * @param sheet Sheet
	 * @param userEntity UserEntity
	 */
	private void processSheetTable1(Workbook workbook, Sheet sheet, UserEntity userEntity) {

		// Get all rows
		Iterator<Row> rowIterator = sheet.iterator();
		GearsDataTable1DTO gearsDataTable1DTO = new GearsDataTable1DTO();
		List<GearsDataTable1DTO> dataTable = new ArrayList<GearsDataTable1DTO>();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			int rowNum = row.getRowNum();
			// Đọc dữ liệu từ dòng 4 đến dòng 16 trong sheet Bảng1_Dịch chuyển lao động
            if (2 < rowNum && row.getRowNum() < 17) {
            	gearsDataTable1DTO = new GearsDataTable1DTO();
                switch(row.getRowNum()) {
    	            case 3:
    	            	gearsDataTable1DTO.setLevel("5");
    	            	gearsDataTable1DTO.setGender("m");
    	            	break;
    	            case 4:
    	            	gearsDataTable1DTO.setLevel("5");
    	            	gearsDataTable1DTO.setGender("f");
    	            	break;
    	            case 5:
    	            	gearsDataTable1DTO.setLevel("4");
    	            	gearsDataTable1DTO.setGender("m");
    	            	break;
    	            case 6:
    	            	gearsDataTable1DTO.setLevel("4");
    	            	gearsDataTable1DTO.setGender("f");
    	            	break;
    	            case 7:
    	            	gearsDataTable1DTO.setLevel("3");
    	            	gearsDataTable1DTO.setGender("m");
    	            	break;
    	            case 8:
    	            	gearsDataTable1DTO.setLevel("3");
    	            	gearsDataTable1DTO.setGender("f");
    	            	break;
    	            case 9:
    	            	gearsDataTable1DTO.setLevel("2");
    	            	gearsDataTable1DTO.setGender("m");
    	            	break;
    	            case 10:
    	            	gearsDataTable1DTO.setLevel("2");
    	            	gearsDataTable1DTO.setGender("f");
    	            	break;
    	            case 11:
    	            	gearsDataTable1DTO.setLevel("1");
    	            	gearsDataTable1DTO.setGender("m");
    	            	break;
    	            case 12:
    	            	gearsDataTable1DTO.setLevel("1");
    	            	gearsDataTable1DTO.setGender("f");
    	            	break;
    	            case 13:
    	            	gearsDataTable1DTO.setLevel("0");
    	            	gearsDataTable1DTO.setGender("m");
    	            	break;
    	            case 14:
    	            	gearsDataTable1DTO.setLevel("0");
    	            	gearsDataTable1DTO.setGender("f");
    	            	break;
    	            case 15:
    	            	gearsDataTable1DTO.setLevel("0");
    	            	gearsDataTable1DTO.setGender("mf");
    	            	break;
    	            default:
    	            	break;
                }
                
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    //Read cell
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    // Đọc dữ liệu từ cột C đến cột H trong sheet Bảng1_Dịch chuyển lao động
                    if (1 < columnIndex && columnIndex < 8) {
                    	String result = subAfterDots(ExcelUtils.getCellDataValue(workbook, cell));
                        switch (columnIndex) {
        	            	case 2:
        	            		gearsDataTable1DTO.setTotalBegin12m(result);
        	            		break;
        	            	case 3:
        	            		gearsDataTable1DTO.setRecruitedOutside(result);
        	            		break;
        	            	case 4:
        	            		gearsDataTable1DTO.setPromotedToLvl(result);
        	            		break;
        	            	case 5:
        	            		gearsDataTable1DTO.setPromotedFromLvl(result);
        	            		break;
        	            	case 6:
        	            		gearsDataTable1DTO.setLeftCompany(result);
        	            		break;
        	            	case 7:
        	            		gearsDataTable1DTO.setTotalEnd12m(result);
        	            		break;
        	            	default:
        		            	break;
        	            }
                    }
                    
                }
                dataTable.add(gearsDataTable1DTO);
            }
		}
		Type dataListType = new TypeToken<ArrayList<GearsDataTable1DTO>>(){}.getType();
		String results = gson.toJson(dataTable, dataListType);
		
		// Update data table IN_EMPL_NVMT
		InEmplNvmtEntity inEmplNvmtEntity = new InEmplNvmtEntity();
		inEmplNvmtEntity.setUserId(userEntity.getUserId());
		inEmplNvmtEntity.setStatus(Constant.DATA_INPUT_SAVED);
		inEmplNvmtEntity.setInputData(results);
		inEmplNvmtMapper.processInEmplNvmtByUserId(inEmplNvmtEntity);
	}
	
	/**
     * Xử lý đọc dữ liệu của sheet [Bảng2_Việc làm]
     * @param workbook Workbook
     * @param sheet Sheet
     * @param userEntity UserEntity
     */
    private void processSheetTable2(Workbook workbook, Sheet sheet, UserEntity userEntity) {

        // Get all rows
        Iterator<Row> rowIterator = sheet.iterator();
        GearsDataTable2DTO gearsDataTable2DTO = new GearsDataTable2DTO();
        List<GearsDataTable2DTO> dataTable = new ArrayList<GearsDataTable2DTO>();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            int rowNum = row.getRowNum();
            // Đọc dữ liệu từ dòng 4 đến dòng 15 trong sheet Bảng2_Việc làm
            if (2 < rowNum && rowNum < 15) {
            	gearsDataTable2DTO = new GearsDataTable2DTO();
                switch(row.getRowNum()) {
                    case 3:
                        gearsDataTable2DTO.setLevel("5");
                        gearsDataTable2DTO.setGender("m");
                        break;
                    case 4:
                        gearsDataTable2DTO.setLevel("5");
                        gearsDataTable2DTO.setGender("f");
                        break;
                    case 5:
                        gearsDataTable2DTO.setLevel("4");
                        gearsDataTable2DTO.setGender("m");
                        break;
                    case 6:
                        gearsDataTable2DTO.setLevel("4");
                        gearsDataTable2DTO.setGender("f");
                        break;
                    case 7:
                        gearsDataTable2DTO.setLevel("3");
                        gearsDataTable2DTO.setGender("m");
                        break;
                    case 8:
                        gearsDataTable2DTO.setLevel("3");
                        gearsDataTable2DTO.setGender("f");
                        break;
                    case 9:
                        gearsDataTable2DTO.setLevel("2");
                        gearsDataTable2DTO.setGender("m");
                        break;
                    case 10:
                        gearsDataTable2DTO.setLevel("2");
                        gearsDataTable2DTO.setGender("f");
                        break;
                    case 11:
                        gearsDataTable2DTO.setLevel("1");
                        gearsDataTable2DTO.setGender("m");
                        break;
                    case 12:
                        gearsDataTable2DTO.setLevel("1");
                        gearsDataTable2DTO.setGender("f");
                        break;
                    case 13:
                        gearsDataTable2DTO.setLevel("0");
                        gearsDataTable2DTO.setGender("m");
                        break;
                    case 14:
                        gearsDataTable2DTO.setLevel("0");
                        gearsDataTable2DTO.setGender("f");
                        break;
                    default:
                        break;
                }
                
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    //Read cell
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    // Đọc dữ liệu từ cột C đến cột G trong sheet Bảng2_Việc làm
                    if (1 < columnIndex && columnIndex < 7) {
                    	String result = subAfterDots(ExcelUtils.getCellDataValue(workbook, cell));
                        switch (columnIndex) {
                            case 2:
                                gearsDataTable2DTO.setIndefinitelyContract(result);
                                break;
                            case 3:
                                gearsDataTable2DTO.setLimitedContract(result);
                                break;
                            case 4:
                                gearsDataTable2DTO.setPartTime(result);
                                break;
                            case 5:
                                gearsDataTable2DTO.setTotal(result);
                                break;
                            case 6:
                                gearsDataTable2DTO.setCheckAgain(result);
                                break;
                            default:
                                break;
                        }
                    }
                }
                dataTable.add(gearsDataTable2DTO);
            }
            
        }
        Type dataListType = new TypeToken<ArrayList<GearsDataTable2DTO>>(){}.getType();
        String results = gson.toJson(dataTable, dataListType);
        
        // Update data table IN_EMPL
        InEmplEntity inEmplEntity = new InEmplEntity();
        inEmplEntity.setUserId(userEntity.getUserId());
        inEmplEntity.setStatus(Constant.DATA_INPUT_SAVED);
        inEmplEntity.setInputData(results);
        inEmplMapper.processInEmplByUserId(inEmplEntity);
    }
    
    /**
     * Xử lý đọc dữ liệu của sheet [Bảng3_Ban lãnh đạo]
     * @param workbook Workbook
     * @param sheet Sheet
     * @param userEntity UserEntity
     */
    private void processSheetTable3(Workbook workbook, Sheet sheet, UserEntity userEntity) {

        // Get all rows
        Iterator<Row> rowIterator = sheet.iterator();
        GearsDataTable3DTO gearsDataTable3DTO = new GearsDataTable3DTO();
        List<GearsDataTable3DTO> dataTable = new ArrayList<GearsDataTable3DTO>();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            int rowNum = row.getRowNum();
            // Đọc dữ liệu từ dòng 4 đến dòng 6 trong sheet Bảng3_Ban lãnh đạo
            if (2 < rowNum && rowNum < 7) {
            	gearsDataTable3DTO = new GearsDataTable3DTO();
                switch(row.getRowNum()) {
                    case 3:
                        gearsDataTable3DTO.setGender("m");
                        break;
                    case 4:
                        gearsDataTable3DTO.setGender("f");
                        break;
                    case 5:
                        gearsDataTable3DTO.setGender("mf");
                        break;
                    default:
                        break;
                }
                
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    //Read cell
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    // Đọc dữ liệu từ cột C đến cột D trong sheet Bảng3_Ban lãnh đạo
                    if (1 < columnIndex && columnIndex < 4) {
                        String result = subAfterDots(ExcelUtils.getCellDataValue(workbook, cell));
                        switch (columnIndex) {
                            case 2:
                                gearsDataTable3DTO.setTotal(result);
                                break;
                            default:
                                break;
                        }
                    }
                }
                dataTable.add(gearsDataTable3DTO);
            }
        }
        Type dataListType = new TypeToken<ArrayList<GearsDataTable3DTO>>(){}.getType();
        String results = gson.toJson(dataTable, dataListType);
        
        // Update data table in_brd_comp
        InBrdCompEntity inBrdCompEntity = new InBrdCompEntity();
        inBrdCompEntity.setUserId(userEntity.getUserId());
        inBrdCompEntity.setStatus(Constant.DATA_INPUT_SAVED);
        inBrdCompEntity.setInputData(results);
        inBrdCompMapper.processInBrdCompByUserId(inBrdCompEntity);
    }
    
    /**
	 * Xử lý đọc dữ liệu của sheet [Bảng4.1_Quản lý]
	 * @param workbook Workbook
	 * @param sheet Sheet
	 * @param userEntity UserEntity
	 */
	private void processSheetTable4Sub1(Workbook workbook, Sheet sheet, UserEntity userEntity) {

		// Get all rows
		Iterator<Row> rowIterator = sheet.iterator();
		GearsDataTable4DTO gearsDataTable4DTO = new GearsDataTable4DTO();
		GearsDataTable4SubDTO tableMale = new GearsDataTable4SubDTO();
		GearsDataTable4SubDTO tableFemale = new GearsDataTable4SubDTO();
		List<GearsDataTable4DTO> dataTable = new ArrayList<GearsDataTable4DTO>();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			int rowNum = row.getRowNum();
			// Đọc dữ liệu từ dòng 5 đến dòng 18 trong sheet Bảng4.1_Quản lý
            if (3 < rowNum && rowNum < 19) {
            	gearsDataTable4DTO = new GearsDataTable4DTO();
                switch(row.getRowNum()) {
    	            case 4:
    	            	gearsDataTable4DTO.setLevel("11");
    	            	break;
    	            case 5:
    	            	gearsDataTable4DTO.setLevel("11.1");
    	            	gearsDataTable4DTO.setParentLevel("11");
    	            	break;
    	            case 6:
    	            	gearsDataTable4DTO.setLevel("11.2");
    	            	gearsDataTable4DTO.setParentLevel("11");
    	            	break;
    	            case 7:
    	            	gearsDataTable4DTO.setLevel("12");
    	            	break;
    	            case 8:
    	            	gearsDataTable4DTO.setLevel("12.1");
    	            	gearsDataTable4DTO.setParentLevel("12");
    	            	break;
    	            case 9:
    	            	gearsDataTable4DTO.setLevel("12.2");
    	            	gearsDataTable4DTO.setParentLevel("12");
    	            	break;
    	            case 10:
    	            	gearsDataTable4DTO.setLevel("13");
    	            	break;
    	            case 11:
    	            	gearsDataTable4DTO.setLevel("13.1");
    	            	gearsDataTable4DTO.setParentLevel("13");
    	            	break;
    	            case 12:
    	            	gearsDataTable4DTO.setLevel("13.2");
    	            	gearsDataTable4DTO.setParentLevel("13");
    	            	break;
    	            case 13:
    	            	gearsDataTable4DTO.setLevel("13.3");
    	            	gearsDataTable4DTO.setParentLevel("13");
    	            	break;
    	            case 14:
    	            	gearsDataTable4DTO.setLevel("13.4");
    	            	gearsDataTable4DTO.setParentLevel("13");
    	            	break;
    	            case 15:
    	            	gearsDataTable4DTO.setLevel("14");
    	            	break;
    	            case 16:
    	            	gearsDataTable4DTO.setLevel("14.1");
    	            	gearsDataTable4DTO.setParentLevel("14");
    	            	break;
    	            case 17:
    	            	gearsDataTable4DTO.setLevel("14.2");
    	            	gearsDataTable4DTO.setParentLevel("14");
    	            	break;
    	            case 18:
    	            	gearsDataTable4DTO.setLevel("14.3");
    	            	gearsDataTable4DTO.setParentLevel("14");
    	            	break;
    	            default:
    	            	break;
                }
                
                Iterator<Cell> cellIterator = row.cellIterator();
                tableMale = new GearsDataTable4SubDTO();
                tableFemale = new GearsDataTable4SubDTO();
                while (cellIterator.hasNext()) {
                    //Read cell
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    // Đọc dữ liệu từ cột C đến cột J trong sheet Bảng4.1_Quản lý
                    if (1 < columnIndex && columnIndex < 10) {
                    	String result = subAfterDots(ExcelUtils.getCellDataValue(workbook, cell));
                        switch (columnIndex) {
        	            	case 2:
        	            		tableMale.setFullTimeIndefinite(result);
        	            		break;
        	            	case 3:
        	            		tableMale.setFullTimeFixedTerm(result);
        	            		break;
        	            	case 4:
        	            		tableMale.setPartTimeIndefinite(result);
        	            		break;
        	            	case 5:
        	            		tableMale.setTotalEnd12m(result);
        	            		break;
        	            	case 6:
        	            		tableFemale.setFullTimeIndefinite(result);
        	            		break;
        	            	case 7:
        	            		tableFemale.setFullTimeFixedTerm(result);
        	            		break;
        	            	case 8:
        	            		tableFemale.setPartTimeIndefinite(result);
        	            		break;
        	            	case 9:
        	            		tableFemale.setTotalEnd12m(result);
        	            		break;
        	            	default:
        		            	break;
        	            }
                    }
                }
                gearsDataTable4DTO.setTableMale(tableMale);
                gearsDataTable4DTO.setTableFemale(tableFemale);
                dataTable.add(gearsDataTable4DTO);
            }
		}
		Type dataListType = new TypeToken<ArrayList<GearsDataTable4DTO>>(){}.getType();
		String results = gson.toJson(dataTable, dataListType);
		
		// Update data table IN_CAREER_MNG
		updateDataTableInCareer("IN_CAREER_MNG", userEntity.getUserId(), results);
	}
	
	/**
	 * Xử lý đọc dữ liệu của sheet [Bảng4.2_Chuyên gia]
	 * @param workbook Workbook
	 * @param sheet Sheet
	 * @param userEntity UserEntity
	 */
	private void processSheetTable4Sub2(Workbook workbook, Sheet sheet, UserEntity userEntity) {

		// Get all rows
		Iterator<Row> rowIterator = sheet.iterator();
		GearsDataTable4DTO gearsDataTable4DTO = new GearsDataTable4DTO();
		GearsDataTable4SubDTO tableMale = new GearsDataTable4SubDTO();
		GearsDataTable4SubDTO tableFemale = new GearsDataTable4SubDTO();
		List<GearsDataTable4DTO> dataTable = new ArrayList<GearsDataTable4DTO>();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			int rowNum = row.getRowNum();
			// Đọc dữ liệu từ dòng 5 đến dòng 37 trong sheet Bảng4.2_Chuyên gia
            if (3 < rowNum && rowNum < 37) {
            	gearsDataTable4DTO = new GearsDataTable4DTO();
                switch(row.getRowNum()) {
    	            case 4:
    	            	gearsDataTable4DTO.setLevel("21");
    	            	break;
    	            case 5:
    	            	gearsDataTable4DTO.setLevel("21.1");
    	            	gearsDataTable4DTO.setParentLevel("21");
    	            	break;
    	            case 6:
    	            	gearsDataTable4DTO.setLevel("21.2");
    	            	gearsDataTable4DTO.setParentLevel("21");
    	            	break;
    	            case 7:
    	            	gearsDataTable4DTO.setLevel("21.3");
    	            	gearsDataTable4DTO.setParentLevel("21");
    	            	break;
    	            case 8:
    	            	gearsDataTable4DTO.setLevel("21.4");
    	            	gearsDataTable4DTO.setParentLevel("21");
    	            	break;
    	            case 9:
    	            	gearsDataTable4DTO.setLevel("21.5");
    	            	gearsDataTable4DTO.setParentLevel("21");
    	            	break;
    	            case 10:
    	            	gearsDataTable4DTO.setLevel("21.6");
    	            	gearsDataTable4DTO.setParentLevel("21");
    	            	break;

    	            case 11:
    	            	gearsDataTable4DTO.setLevel("22");
    	            	break;
    	            case 12:
    	            	gearsDataTable4DTO.setLevel("22.1");
    	            	gearsDataTable4DTO.setParentLevel("22");
    	            	break;
    	            case 13:
    	            	gearsDataTable4DTO.setLevel("22.2");
    	            	gearsDataTable4DTO.setParentLevel("22");
    	            	break;
    	            case 14:
    	            	gearsDataTable4DTO.setLevel("22.3");
    	            	gearsDataTable4DTO.setParentLevel("22");
    	            	break;
    	            case 15:
    	            	gearsDataTable4DTO.setLevel("22.4");
    	            	gearsDataTable4DTO.setParentLevel("22");
    	            	break;
    	            case 16:
    	            	gearsDataTable4DTO.setLevel("22.5");
    	            	gearsDataTable4DTO.setParentLevel("22");
    	            	break;
    	            case 17:
    	            	gearsDataTable4DTO.setLevel("22.6");
    	            	gearsDataTable4DTO.setParentLevel("22");
    	            	break;

    	            case 18:
    	            	gearsDataTable4DTO.setLevel("23");
    	            	break;
    	            case 19:
    	            	gearsDataTable4DTO.setLevel("23.1");
    	            	gearsDataTable4DTO.setParentLevel("23");
    	            	break;
    	            case 20:
    	            	gearsDataTable4DTO.setLevel("23.2");
    	            	gearsDataTable4DTO.setParentLevel("23");
    	            	break;
    	            case 21:
    	            	gearsDataTable4DTO.setLevel("23.3");
    	            	gearsDataTable4DTO.setParentLevel("23");
    	            	break;
    	            case 22:
    	            	gearsDataTable4DTO.setLevel("23.4");
    	            	gearsDataTable4DTO.setParentLevel("23");
    	            	break;
    	            case 23:
    	            	gearsDataTable4DTO.setLevel("23.5");
    	            	gearsDataTable4DTO.setParentLevel("23");
    	            	break;
    	            	
    	            case 24:
    	            	gearsDataTable4DTO.setLevel("24");
    	            	break;
    	            case 25:
    	            	gearsDataTable4DTO.setLevel("24.1");
    	            	gearsDataTable4DTO.setParentLevel("24");
    	            	break;
    	            case 26:
    	            	gearsDataTable4DTO.setLevel("24.2");
    	            	gearsDataTable4DTO.setParentLevel("24");
    	            	break;
    	            case 27:
    	            	gearsDataTable4DTO.setLevel("24.3");
    	            	gearsDataTable4DTO.setParentLevel("24");
    	            	break;
    	            	
    	            case 28:
    	            	gearsDataTable4DTO.setLevel("25");
    	            	break;
    	            case 29:
    	            	gearsDataTable4DTO.setLevel("25.1");
    	            	gearsDataTable4DTO.setParentLevel("25");
    	            	break;
    	            case 30:
    	            	gearsDataTable4DTO.setLevel("25.2");
    	            	gearsDataTable4DTO.setParentLevel("25");
    	            	break;

    	            case 31:
    	            	gearsDataTable4DTO.setLevel("26");
    	            	break;
    	            case 32:
    	            	gearsDataTable4DTO.setLevel("26.1");
    	            	gearsDataTable4DTO.setParentLevel("26");
    	            	break;
    	            case 33:
    	            	gearsDataTable4DTO.setLevel("26.2");
    	            	gearsDataTable4DTO.setParentLevel("26");
    	            	break;
    	            case 34:
    	            	gearsDataTable4DTO.setLevel("26.3");
    	            	gearsDataTable4DTO.setParentLevel("26");
    	            	break;
    	            case 35:
    	            	gearsDataTable4DTO.setLevel("26.4");
    	            	gearsDataTable4DTO.setParentLevel("26");
    	            	break;
    	            case 36:
    	            	gearsDataTable4DTO.setLevel("26.5");
    	            	gearsDataTable4DTO.setParentLevel("26");
    	            	break;
    	            default:
    	            	break;
                }
                
                Iterator<Cell> cellIterator = row.cellIterator();
                tableMale = new GearsDataTable4SubDTO();
                tableFemale = new GearsDataTable4SubDTO();
                while (cellIterator.hasNext()) {
                    //Read cell
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    // Đọc dữ liệu từ cột C đến cột J trong sheet Bảng4.2_Chuyên gia
                    if (1 < columnIndex && columnIndex < 10) {
                    	String result = subAfterDots(ExcelUtils.getCellDataValue(workbook, cell));
                        switch (columnIndex) {
        	            	case 2:
        	            		tableMale.setFullTimeIndefinite(result);
        	            		break;
        	            	case 3:
        	            		tableMale.setFullTimeFixedTerm(result);
        	            		break;
        	            	case 4:
        	            		tableMale.setPartTimeIndefinite(result);
        	            		break;
        	            	case 5:
        	            		tableMale.setTotalEnd12m(result);
        	            		break;
        	            	case 6:
        	            		tableFemale.setFullTimeIndefinite(result);
        	            		break;
        	            	case 7:
        	            		tableFemale.setFullTimeFixedTerm(result);
        	            		break;
        	            	case 8:
        	            		tableFemale.setPartTimeIndefinite(result);
        	            		break;
        	            	case 9:
        	            		tableFemale.setTotalEnd12m(result);
        	            		break;
        	            	default:
        		            	break;
        	            }
                    }
                }
                gearsDataTable4DTO.setTableMale(tableMale);
                gearsDataTable4DTO.setTableFemale(tableFemale);
                dataTable.add(gearsDataTable4DTO);
            }
            
		}
		Type dataListType = new TypeToken<ArrayList<GearsDataTable4DTO>>(){}.getType();
		String results = gson.toJson(dataTable, dataListType);
		
		// Update data table IN_CAREER_EXP
		updateDataTableInCareer("IN_CAREER_EXP", userEntity.getUserId(), results);
	}
	
	/**
	 * Xử lý đọc dữ liệu của sheet [Bảng4.3_Kỹ thuật viên và trợ lý]
	 * @param workbook Workbook
	 * @param sheet Sheet
	 * @param userEntity UserEntity
	 */
	private void processSheetTable4Sub3(Workbook workbook, Sheet sheet, UserEntity userEntity) {

		// Get all rows
		Iterator<Row> rowIterator = sheet.iterator();
		GearsDataTable4DTO gearsDataTable4DTO = new GearsDataTable4DTO();
		GearsDataTable4SubDTO tableMale = new GearsDataTable4SubDTO();
		GearsDataTable4SubDTO tableFemale = new GearsDataTable4SubDTO();
		List<GearsDataTable4DTO> dataTable = new ArrayList<GearsDataTable4DTO>();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			int rowNum = row.getRowNum();
			// Đọc dữ liệu từ dòng 5 đến dòng 29 trong sheet Bảng4.3_Kỹ thuật viên và trợ lý
			if (3 < rowNum && rowNum < 29) {
				gearsDataTable4DTO = new GearsDataTable4DTO();
	            switch(row.getRowNum()) {
		            case 4:
		            	gearsDataTable4DTO.setLevel("31");
		            	break;
		            case 5:
		            	gearsDataTable4DTO.setLevel("31.1");
		            	gearsDataTable4DTO.setParentLevel("31");
		            	break;
		            case 6:
		            	gearsDataTable4DTO.setLevel("31.2");
		            	gearsDataTable4DTO.setParentLevel("31");
		            	break;
		            case 7:
		            	gearsDataTable4DTO.setLevel("31.3");
		            	gearsDataTable4DTO.setParentLevel("31");
		            	break;
		            case 8:
		            	gearsDataTable4DTO.setLevel("31.4");
		            	gearsDataTable4DTO.setParentLevel("31");
		            	break;
		            case 9:
		            	gearsDataTable4DTO.setLevel("31.5");
		            	gearsDataTable4DTO.setParentLevel("31");
		            	break;

		            case 10:
		            	gearsDataTable4DTO.setLevel("32");
		            	break;
		            case 11:
		            	gearsDataTable4DTO.setLevel("32.1");
		            	gearsDataTable4DTO.setParentLevel("32");
		            	break;
		            case 12:
		            	gearsDataTable4DTO.setLevel("32.2");
		            	gearsDataTable4DTO.setParentLevel("32");
		            	break;
		            case 13:
		            	gearsDataTable4DTO.setLevel("32.3");
		            	gearsDataTable4DTO.setParentLevel("32");
		            	break;
		            case 14:
		            	gearsDataTable4DTO.setLevel("32.4");
		            	gearsDataTable4DTO.setParentLevel("32");
		            	break;
		            case 15:
		            	gearsDataTable4DTO.setLevel("32.5");
		            	gearsDataTable4DTO.setParentLevel("32");
		            	break;

		            case 16:
		            	gearsDataTable4DTO.setLevel("33");
		            	break;
		            case 17:
		            	gearsDataTable4DTO.setLevel("33.1");
		            	gearsDataTable4DTO.setParentLevel("33");
		            	break;
		            case 18:
		            	gearsDataTable4DTO.setLevel("33.2");
		            	gearsDataTable4DTO.setParentLevel("33");
		            	break;
		            case 19:
		            	gearsDataTable4DTO.setLevel("33.3");
		            	gearsDataTable4DTO.setParentLevel("33");
		            	break;
		            case 20:
		            	gearsDataTable4DTO.setLevel("33.4");
		            	gearsDataTable4DTO.setParentLevel("33");
		            	break;
		            case 21:
		            	gearsDataTable4DTO.setLevel("33.5");
		            	gearsDataTable4DTO.setParentLevel("33");
		            	break;

		            case 22:
		            	gearsDataTable4DTO.setLevel("34");
		            	break;
		            case 23:
		            	gearsDataTable4DTO.setLevel("34.1");
		            	gearsDataTable4DTO.setParentLevel("34");
		            	break;
		            case 24:
		            	gearsDataTable4DTO.setLevel("34.2");
		            	gearsDataTable4DTO.setParentLevel("34");
		            	break;
		            case 25:
		            	gearsDataTable4DTO.setLevel("34.3");
		            	gearsDataTable4DTO.setParentLevel("34");
		            	break;

		            case 26:
		            	gearsDataTable4DTO.setLevel("35");
		            	break;
		            case 27:
		            	gearsDataTable4DTO.setLevel("35.1");
		            	gearsDataTable4DTO.setParentLevel("35");
		            	break;
		            case 28:
		            	gearsDataTable4DTO.setLevel("35.2");
		            	gearsDataTable4DTO.setParentLevel("35");
		            	break;

		            default:
		            	break;
	            }
	            
	            Iterator<Cell> cellIterator = row.cellIterator();
	            tableMale = new GearsDataTable4SubDTO();
	            tableFemale = new GearsDataTable4SubDTO();
	            while (cellIterator.hasNext()) {
	                //Read cell
	                Cell cell = cellIterator.next();
	                int columnIndex = cell.getColumnIndex();
	                // Đọc dữ liệu từ cột C đến cột J trong sheet Bảng4.3_Kỹ thuật viên và trợ lý
	                if (1 < columnIndex && columnIndex < 10) {
	                	String result = subAfterDots(ExcelUtils.getCellDataValue(workbook, cell));
		                switch (columnIndex) {
			            	case 2:
			            		tableMale.setFullTimeIndefinite(result);
			            		break;
			            	case 3:
			            		tableMale.setFullTimeFixedTerm(result);
			            		break;
			            	case 4:
			            		tableMale.setPartTimeIndefinite(result);
			            		break;
			            	case 5:
			            		tableMale.setTotalEnd12m(result);
			            		break;
			            	case 6:
			            		tableFemale.setFullTimeIndefinite(result);
			            		break;
			            	case 7:
			            		tableFemale.setFullTimeFixedTerm(result);
			            		break;
			            	case 8:
			            		tableFemale.setPartTimeIndefinite(result);
			            		break;
			            	case 9:
			            		tableFemale.setTotalEnd12m(result);
			            		break;
			            	default:
				            	break;
			            }
	                }
	            }
	            gearsDataTable4DTO.setTableMale(tableMale);
	            gearsDataTable4DTO.setTableFemale(tableFemale);
	            dataTable.add(gearsDataTable4DTO);
            }
            
		}
		Type dataListType = new TypeToken<ArrayList<GearsDataTable4DTO>>(){}.getType();
		String results = gson.toJson(dataTable, dataListType);

		// Update data table IN_CAREER_TECH
		updateDataTableInCareer("IN_CAREER_TECH", userEntity.getUserId(), results);
	}
	
	/**
	 * Xử lý đọc dữ liệu của sheet [Bảng4.4_Nhân viên trợ lý VP]
	 * @param workbook Workbook
	 * @param sheet Sheet
	 * @param userEntity UserEntity
	 */
	private void processSheetTable4Sub4(Workbook workbook, Sheet sheet, UserEntity userEntity) {

		// Get all rows
		Iterator<Row> rowIterator = sheet.iterator();
		GearsDataTable4DTO gearsDataTable4DTO = new GearsDataTable4DTO();
		GearsDataTable4SubDTO tableMale = new GearsDataTable4SubDTO();
		GearsDataTable4SubDTO tableFemale = new GearsDataTable4SubDTO();
		List<GearsDataTable4DTO> dataTable = new ArrayList<GearsDataTable4DTO>();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			int rowNum = row.getRowNum();
			// Đọc dữ liệu từ dòng 5 đến dòng 16 trong sheet Bảng4.4_Nhân viên trợ lý VP
			if (3 < rowNum && rowNum < 16) {
				gearsDataTable4DTO = new GearsDataTable4DTO();
	            switch(row.getRowNum()) {
		            case 4:
		            	gearsDataTable4DTO.setLevel("41");
		            	break;
		            case 5:
		            	gearsDataTable4DTO.setLevel("41.1");
		            	gearsDataTable4DTO.setParentLevel("41");
		            	break;
		            case 6:
		            	gearsDataTable4DTO.setLevel("41.2");
		            	gearsDataTable4DTO.setParentLevel("41");
		            	break;
		            case 7:
		            	gearsDataTable4DTO.setLevel("41.3");
		            	gearsDataTable4DTO.setParentLevel("41");
		            	break;

		            case 8:
		            	gearsDataTable4DTO.setLevel("42");
		            	break;
		            case 9:
		            	gearsDataTable4DTO.setLevel("42.1");
		            	gearsDataTable4DTO.setParentLevel("42");
		            	break;
		            case 10:
		            	gearsDataTable4DTO.setLevel("42.2");
		            	gearsDataTable4DTO.setParentLevel("42");
		            	break;

		            case 11:
		            	gearsDataTable4DTO.setLevel("43");
		            	break;
		            case 12:
		            	gearsDataTable4DTO.setLevel("43.1");
		            	gearsDataTable4DTO.setParentLevel("43");
		            	break;
		            case 13:
		            	gearsDataTable4DTO.setLevel("43.2");
		            	gearsDataTable4DTO.setParentLevel("43");
		            	break;

		            case 14:
		            	gearsDataTable4DTO.setLevel("44");
		            	break;
		            case 15:
		            	gearsDataTable4DTO.setLevel("44.1");
		            	gearsDataTable4DTO.setParentLevel("44");
		            	break;

		            default:
		            	break;
	            }
	            
	            Iterator<Cell> cellIterator = row.cellIterator();
	            tableMale = new GearsDataTable4SubDTO();
	            tableFemale = new GearsDataTable4SubDTO();
	            while (cellIterator.hasNext()) {
	                //Read cell
	                Cell cell = cellIterator.next();
	                int columnIndex = cell.getColumnIndex();
	                // Đọc dữ liệu từ cột C đến cột J trong sheet Bảng4.4_Nhân viên trợ lý VP
	                if (1 < columnIndex && columnIndex < 10) {
	                	String result = subAfterDots(ExcelUtils.getCellDataValue(workbook, cell));
		                switch (columnIndex) {
			            	case 2:
			            		tableMale.setFullTimeIndefinite(result);
			            		break;
			            	case 3:
			            		tableMale.setFullTimeFixedTerm(result);
			            		break;
			            	case 4:
			            		tableMale.setPartTimeIndefinite(result);
			            		break;
			            	case 5:
			            		tableMale.setTotalEnd12m(result);
			            		break;
			            	case 6:
			            		tableFemale.setFullTimeIndefinite(result);
			            		break;
			            	case 7:
			            		tableFemale.setFullTimeFixedTerm(result);
			            		break;
			            	case 8:
			            		tableFemale.setPartTimeIndefinite(result);
			            		break;
			            	case 9:
			            		tableFemale.setTotalEnd12m(result);
			            		break;
			            	default:
				            	break;
			            }
	                }
	            }
	            gearsDataTable4DTO.setTableMale(tableMale);
	            gearsDataTable4DTO.setTableFemale(tableFemale);
	            dataTable.add(gearsDataTable4DTO);
            }
            
		}
		Type dataListType = new TypeToken<ArrayList<GearsDataTable4DTO>>(){}.getType();
		String results = gson.toJson(dataTable, dataListType);

		// Update data table IN_CAREER_OFFICE
		updateDataTableInCareer("IN_CAREER_OFFICE", userEntity.getUserId(), results);
	}
	
	/**
	 * Xử lý đọc dữ liệu của sheet [Bảng4.5_Nhân viên dịch vụ]
	 * @param workbook Workbook
	 * @param sheet Sheet
	 * @param userEntity UserEntity
	 */
	private void processSheetTable4Sub5(Workbook workbook, Sheet sheet, UserEntity userEntity) {

		// Get all rows
		Iterator<Row> rowIterator = sheet.iterator();
		GearsDataTable4DTO gearsDataTable4DTO = new GearsDataTable4DTO();
		GearsDataTable4SubDTO tableMale = new GearsDataTable4SubDTO();
		GearsDataTable4SubDTO tableFemale = new GearsDataTable4SubDTO();
		List<GearsDataTable4DTO> dataTable = new ArrayList<GearsDataTable4DTO>();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			int rowNum = row.getRowNum();
			// Đọc dữ liệu từ dòng 5 đến dòng 21 trong sheet Bảng4.5_Nhân viên dịch vụ
			if (3 < rowNum && rowNum < 21) {
				gearsDataTable4DTO = new GearsDataTable4DTO();
	            switch(row.getRowNum()) {
		            case 4:
		            	gearsDataTable4DTO.setLevel("51");
		            	break;
		            case 5:
		            	gearsDataTable4DTO.setLevel("51.1");
		            	gearsDataTable4DTO.setParentLevel("51");
		            	break;
		            case 6:
		            	gearsDataTable4DTO.setLevel("51.2");
		            	gearsDataTable4DTO.setParentLevel("51");
		            	break;
		            case 7:
		            	gearsDataTable4DTO.setLevel("51.3");
		            	gearsDataTable4DTO.setParentLevel("51");
		            	break;
		            case 8:
		            	gearsDataTable4DTO.setLevel("51.4");
		            	gearsDataTable4DTO.setParentLevel("51");
		            	break;
		            case 9:
		            	gearsDataTable4DTO.setLevel("51.5");
		            	gearsDataTable4DTO.setParentLevel("51");
		            	break;
		            case 10:
		            	gearsDataTable4DTO.setLevel("51.6");
		            	gearsDataTable4DTO.setParentLevel("51");
		            	break;

		            case 11:
		            	gearsDataTable4DTO.setLevel("52");
		            	break;
		            case 12:
		            	gearsDataTable4DTO.setLevel("52.1");
		            	gearsDataTable4DTO.setParentLevel("52");
		            	break;
		            case 13:
		            	gearsDataTable4DTO.setLevel("52.2");
		            	gearsDataTable4DTO.setParentLevel("52");
		            	break;
		            case 14:
		            	gearsDataTable4DTO.setLevel("52.3");
		            	gearsDataTable4DTO.setParentLevel("52");
		            	break;
		            case 15:
		            	gearsDataTable4DTO.setLevel("52.4");
		            	gearsDataTable4DTO.setParentLevel("52");
		            	break;
		            	
		            case 16:
		            	gearsDataTable4DTO.setLevel("53");
		            	break;
		            case 17:
		            	gearsDataTable4DTO.setLevel("53.1");
		            	gearsDataTable4DTO.setParentLevel("53");
		            	break;
		            case 18:
		            	gearsDataTable4DTO.setLevel("53.2");
		            	gearsDataTable4DTO.setParentLevel("53");
		            	break;
		            	
		            case 19:
		            	gearsDataTable4DTO.setLevel("54");
		            	break;
		            case 20:
		            	gearsDataTable4DTO.setLevel("54.1");
		            	gearsDataTable4DTO.setParentLevel("54");
		            	break;

		            default:
		            	break;
	            }
	            
	            Iterator<Cell> cellIterator = row.cellIterator();
	            tableMale = new GearsDataTable4SubDTO();
	            tableFemale = new GearsDataTable4SubDTO();
	            while (cellIterator.hasNext()) {
	                //Read cell
	                Cell cell = cellIterator.next();
	                int columnIndex = cell.getColumnIndex();
	                // Đọc dữ liệu từ cột C đến cột J trong sheet Bảng4.5_Nhân viên dịch vụ
	                if (1 < columnIndex && columnIndex < 10) {
	                	String result = subAfterDots(ExcelUtils.getCellDataValue(workbook, cell));
		                switch (columnIndex) {
			            	case 2:
			            		tableMale.setFullTimeIndefinite(result);
			            		break;
			            	case 3:
			            		tableMale.setFullTimeFixedTerm(result);
			            		break;
			            	case 4:
			            		tableMale.setPartTimeIndefinite(result);
			            		break;
			            	case 5:
			            		tableMale.setTotalEnd12m(result);
			            		break;
			            	case 6:
			            		tableFemale.setFullTimeIndefinite(result);
			            		break;
			            	case 7:
			            		tableFemale.setFullTimeFixedTerm(result);
			            		break;
			            	case 8:
			            		tableFemale.setPartTimeIndefinite(result);
			            		break;
			            	case 9:
			            		tableFemale.setTotalEnd12m(result);
			            		break;
			            	default:
				            	break;
			            }
	                }
	            }
	            gearsDataTable4DTO.setTableMale(tableMale);
	            gearsDataTable4DTO.setTableFemale(tableFemale);
	            dataTable.add(gearsDataTable4DTO);
            }
		}
		Type dataListType = new TypeToken<ArrayList<GearsDataTable4DTO>>(){}.getType();
		String results = gson.toJson(dataTable, dataListType);

		// Update data table IN_CAREER_SERVICE
		updateDataTableInCareer("IN_CAREER_SERVICE", userEntity.getUserId(), results);
	}
	
	/**
	 * Xử lý đọc dữ liệu của sheet [Bảng4.6_Lao động Nông,Lâm,TS]
	 * @param workbook Workbook
	 * @param sheet Sheet
	 * @param userEntity UserEntity
	 */
	private void processSheetTable4Sub6(Workbook workbook, Sheet sheet, UserEntity userEntity) {

		// Get all rows
		Iterator<Row> rowIterator = sheet.iterator();
		GearsDataTable4DTO gearsDataTable4DTO = new GearsDataTable4DTO();
		GearsDataTable4SubDTO tableMale = new GearsDataTable4SubDTO();
		GearsDataTable4SubDTO tableFemale = new GearsDataTable4SubDTO();
		List<GearsDataTable4DTO> dataTable = new ArrayList<GearsDataTable4DTO>();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			int rowNum = row.getRowNum();
			// Đọc dữ liệu từ dòng 5 đến dòng 16 trong sheet Bảng4.6_Lao động Nông,Lâm,TS
			if (3 < rowNum && rowNum < 16) {
				gearsDataTable4DTO = new GearsDataTable4DTO();
	            switch(row.getRowNum()) {
		            case 4:
		            	gearsDataTable4DTO.setLevel("61");
		            	break;
		            case 5:
		            	gearsDataTable4DTO.setLevel("61.1");
		            	gearsDataTable4DTO.setParentLevel("61");
		            	break;
		            case 6:
		            	gearsDataTable4DTO.setLevel("61.2");
		            	gearsDataTable4DTO.setParentLevel("61");
		            	break;
		            case 7:
		            	gearsDataTable4DTO.setLevel("61.3");
		            	gearsDataTable4DTO.setParentLevel("61");
		            	break;

		            case 8:
		            	gearsDataTable4DTO.setLevel("62");
		            	break;
		            case 9:
		            	gearsDataTable4DTO.setLevel("62.1");
		            	gearsDataTable4DTO.setParentLevel("62");
		            	break;
		            case 10:
		            	gearsDataTable4DTO.setLevel("62.2");
		            	gearsDataTable4DTO.setParentLevel("62");
		            	break;

		            case 11:
		            	gearsDataTable4DTO.setLevel("63");
		            	break;
		            case 12:
		            	gearsDataTable4DTO.setLevel("63.1");
		            	gearsDataTable4DTO.setParentLevel("63");
		            	break;
		            case 13:
		            	gearsDataTable4DTO.setLevel("63.2");
		            	gearsDataTable4DTO.setParentLevel("63");
		            	break;
		            case 14:
		            	gearsDataTable4DTO.setLevel("63.3");
		            	gearsDataTable4DTO.setParentLevel("63");
		            	break;
		            case 15:
		            	gearsDataTable4DTO.setLevel("63.4");
		            	gearsDataTable4DTO.setParentLevel("63");
		            	break;

		            default:
		            	break;
	            }
	            
	            Iterator<Cell> cellIterator = row.cellIterator();
	            tableMale = new GearsDataTable4SubDTO();
	            tableFemale = new GearsDataTable4SubDTO();
	            while (cellIterator.hasNext()) {
	                //Read cell
	                Cell cell = cellIterator.next();
	                int columnIndex = cell.getColumnIndex();
	                // Đọc dữ liệu từ cột C đến cột J trong sheet Bảng4.6_Lao động Nông,Lâm,TS
	                if (1 < columnIndex && columnIndex < 10) {
	                	String result = subAfterDots(ExcelUtils.getCellDataValue(workbook, cell));
		                switch (columnIndex) {
			            	case 2:
			            		tableMale.setFullTimeIndefinite(result);
			            		break;
			            	case 3:
			            		tableMale.setFullTimeFixedTerm(result);
			            		break;
			            	case 4:
			            		tableMale.setPartTimeIndefinite(result);
			            		break;
			            	case 5:
			            		tableMale.setTotalEnd12m(result);
			            		break;
			            	case 6:
			            		tableFemale.setFullTimeIndefinite(result);
			            		break;
			            	case 7:
			            		tableFemale.setFullTimeFixedTerm(result);
			            		break;
			            	case 8:
			            		tableFemale.setPartTimeIndefinite(result);
			            		break;
			            	case 9:
			            		tableFemale.setTotalEnd12m(result);
			            		break;
			            	default:
				            	break;
			            }
	                }
	            }
	            gearsDataTable4DTO.setTableMale(tableMale);
	            gearsDataTable4DTO.setTableFemale(tableFemale);
	            dataTable.add(gearsDataTable4DTO);
            }
		}
		Type dataListType = new TypeToken<ArrayList<GearsDataTable4DTO>>(){}.getType();
		String results = gson.toJson(dataTable, dataListType);

		// Update data table IN_CAREER_AGR
		updateDataTableInCareer("IN_CAREER_AGR", userEntity.getUserId(), results);
	}
	
	/**
	 * Xử lý đọc dữ liệu của sheet [Bảng4.7_Lao động thủ công]
	 * @param workbook Workbook
	 * @param sheet Sheet
	 * @param userEntity UserEntity
	 */
	private void processSheetTable4Sub7(Workbook workbook, Sheet sheet, UserEntity userEntity) {

		// Get all rows
		Iterator<Row> rowIterator = sheet.iterator();
		GearsDataTable4DTO gearsDataTable4DTO = new GearsDataTable4DTO();
		GearsDataTable4SubDTO tableMale = new GearsDataTable4SubDTO();
		GearsDataTable4SubDTO tableFemale = new GearsDataTable4SubDTO();
		List<GearsDataTable4DTO> dataTable = new ArrayList<GearsDataTable4DTO>();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			int rowNum = row.getRowNum();
			// Đọc dữ liệu từ dòng 5 đến dòng 23 trong sheet Bảng4.7_Lao động thủ công
			if (3 < rowNum && rowNum < 23) {
				gearsDataTable4DTO = new GearsDataTable4DTO();
	            switch(row.getRowNum()) {
		            case 4:
		            	gearsDataTable4DTO.setLevel("71");
		            	break;
		            case 5:
		            	gearsDataTable4DTO.setLevel("71.1");
		            	gearsDataTable4DTO.setParentLevel("71");
		            	break;
		            case 6:
		            	gearsDataTable4DTO.setLevel("71.2");
		            	gearsDataTable4DTO.setParentLevel("71");
		            	break;
		            case 7:
		            	gearsDataTable4DTO.setLevel("71.3");
		            	gearsDataTable4DTO.setParentLevel("71");
		            	break;

		            case 8:
		            	gearsDataTable4DTO.setLevel("72");
		            	break;
		            case 9:
		            	gearsDataTable4DTO.setLevel("72.1");
		            	gearsDataTable4DTO.setParentLevel("72");
		            	break;
		            case 10:
		            	gearsDataTable4DTO.setLevel("72.2");
		            	gearsDataTable4DTO.setParentLevel("72");
		            	break;
		            case 11:
		            	gearsDataTable4DTO.setLevel("72.3");
		            	gearsDataTable4DTO.setParentLevel("72");
		            	break;

		            case 12:
		            	gearsDataTable4DTO.setLevel("73");
		            	break;
		            case 13:
		            	gearsDataTable4DTO.setLevel("73.1");
		            	gearsDataTable4DTO.setParentLevel("73");
		            	break;
		            case 14:
		            	gearsDataTable4DTO.setLevel("73.2");
		            	gearsDataTable4DTO.setParentLevel("73");
		            	break;

		            case 15:
		            	gearsDataTable4DTO.setLevel("74");
		            	break;
		            case 16:
		            	gearsDataTable4DTO.setLevel("74.1");
		            	gearsDataTable4DTO.setParentLevel("74");
		            	break;
		            case 17:
		            	gearsDataTable4DTO.setLevel("74.2");
		            	gearsDataTable4DTO.setParentLevel("74");
		            	break;
		            	
		            case 18:
		            	gearsDataTable4DTO.setLevel("75");
		            	break;
		            case 19:
		            	gearsDataTable4DTO.setLevel("75.1");
		            	gearsDataTable4DTO.setParentLevel("75");
		            	break;
		            case 20:
		            	gearsDataTable4DTO.setLevel("75.2");
		            	gearsDataTable4DTO.setParentLevel("75");
		            	break;
		            case 21:
		            	gearsDataTable4DTO.setLevel("75.3");
		            	gearsDataTable4DTO.setParentLevel("75");
		            	break;
		            case 22:
		            	gearsDataTable4DTO.setLevel("75.4");
		            	gearsDataTable4DTO.setParentLevel("75");
		            	break;

		            default:
		            	break;
	            }
	            
	            Iterator<Cell> cellIterator = row.cellIterator();
	            tableMale = new GearsDataTable4SubDTO();
	            tableFemale = new GearsDataTable4SubDTO();
	            while (cellIterator.hasNext()) {
	                //Read cell
	                Cell cell = cellIterator.next();
	                int columnIndex = cell.getColumnIndex();
	                // Đọc dữ liệu từ cột C đến cột J trong sheet Bảng4.7_Lao động thủ công
	                if (1 < columnIndex && columnIndex < 10) {
	                	String result = subAfterDots(ExcelUtils.getCellDataValue(workbook, cell));
		                switch (columnIndex) {
			            	case 2:
			            		tableMale.setFullTimeIndefinite(result);
			            		break;
			            	case 3:
			            		tableMale.setFullTimeFixedTerm(result);
			            		break;
			            	case 4:
			            		tableMale.setPartTimeIndefinite(result);
			            		break;
			            	case 5:
			            		tableMale.setTotalEnd12m(result);
			            		break;
			            	case 6:
			            		tableFemale.setFullTimeIndefinite(result);
			            		break;
			            	case 7:
			            		tableFemale.setFullTimeFixedTerm(result);
			            		break;
			            	case 8:
			            		tableFemale.setPartTimeIndefinite(result);
			            		break;
			            	case 9:
			            		tableFemale.setTotalEnd12m(result);
			            		break;
			            	default:
				            	break;
			            }
	                }
	            }
	            gearsDataTable4DTO.setTableMale(tableMale);
	            gearsDataTable4DTO.setTableFemale(tableFemale);
	            dataTable.add(gearsDataTable4DTO);
            }
		}
		Type dataListType = new TypeToken<ArrayList<GearsDataTable4DTO>>(){}.getType();
		String results = gson.toJson(dataTable, dataListType);

		// Update data table IN_CAREER_MANUAL
		updateDataTableInCareer("IN_CAREER_MANUAL", userEntity.getUserId(), results);
	}
	
	/**
	 * Xử lý đọc dữ liệu của sheet [Bảng4.8_Thợ lắp ráp]
	 * @param workbook Workbook
	 * @param sheet Sheet
	 * @param userEntity UserEntity
	 */
	private void processSheetTable4Sub8(Workbook workbook, Sheet sheet, UserEntity userEntity) {

		// Get all rows
		Iterator<Row> rowIterator = sheet.iterator();
		GearsDataTable4DTO gearsDataTable4DTO = new GearsDataTable4DTO();
		GearsDataTable4SubDTO tableMale = new GearsDataTable4SubDTO();
		GearsDataTable4SubDTO tableFemale = new GearsDataTable4SubDTO();
		List<GearsDataTable4DTO> dataTable = new ArrayList<GearsDataTable4DTO>();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			int rowNum = row.getRowNum();
			// Đọc dữ liệu từ dòng 5 đến dòng 21 trong sheet Bảng4.8_Thợ lắp ráp
			if (3 < rowNum && rowNum < 21) {
				gearsDataTable4DTO = new GearsDataTable4DTO();
	            switch(row.getRowNum()) {
		            case 4:
		            	gearsDataTable4DTO.setLevel("81");
		            	break;
		            case 5:
		            	gearsDataTable4DTO.setLevel("81.1");
		            	gearsDataTable4DTO.setParentLevel("81");
		            	break;
		            case 6:
		            	gearsDataTable4DTO.setLevel("81.2");
		            	gearsDataTable4DTO.setParentLevel("81");
		            	break;
		            case 7:
		            	gearsDataTable4DTO.setLevel("81.3");
		            	gearsDataTable4DTO.setParentLevel("81");
		            	break;
		            case 8:
		            	gearsDataTable4DTO.setLevel("81.4");
		            	gearsDataTable4DTO.setParentLevel("81");
		            	break;
		            case 9:
		            	gearsDataTable4DTO.setLevel("81.5");
		            	gearsDataTable4DTO.setParentLevel("81");
		            	break;
		            case 10:
		            	gearsDataTable4DTO.setLevel("81.6");
		            	gearsDataTable4DTO.setParentLevel("81");
		            	break;
		            case 11:
		            	gearsDataTable4DTO.setLevel("81.7");
		            	gearsDataTable4DTO.setParentLevel("81");
		            	break;
		            case 12:
		            	gearsDataTable4DTO.setLevel("81.8");
		            	gearsDataTable4DTO.setParentLevel("81");
		            	break;

		            case 13:
		            	gearsDataTable4DTO.setLevel("82");
		            	break;
		            case 14:
		            	gearsDataTable4DTO.setLevel("82.1");
		            	gearsDataTable4DTO.setParentLevel("82");
		            	break;

		            case 15:
		            	gearsDataTable4DTO.setLevel("83");
		            	break;
		            case 16:
		            	gearsDataTable4DTO.setLevel("83.1");
		            	gearsDataTable4DTO.setParentLevel("83");
		            	break;
		            case 17:
		            	gearsDataTable4DTO.setLevel("83.2");
		            	gearsDataTable4DTO.setParentLevel("83");
		            	break;
		            case 18:
		            	gearsDataTable4DTO.setLevel("83.3");
		            	gearsDataTable4DTO.setParentLevel("83");
		            	break;
		            case 19:
		            	gearsDataTable4DTO.setLevel("83.4");
		            	gearsDataTable4DTO.setParentLevel("83");
		            	break;
		            case 20:
		            	gearsDataTable4DTO.setLevel("83.5");
		            	gearsDataTable4DTO.setParentLevel("83");
		            	break;
		            default:
		            	break;
	            }
	            
	            Iterator<Cell> cellIterator = row.cellIterator();
	            tableMale = new GearsDataTable4SubDTO();
	            tableFemale = new GearsDataTable4SubDTO();
	            while (cellIterator.hasNext()) {
	                //Read cell
	                Cell cell = cellIterator.next();
	                int columnIndex = cell.getColumnIndex();
	                // Đọc dữ liệu từ cột C đến cột J trong sheet Bảng4.8_Thợ lắp ráp
	                if (1 < columnIndex && columnIndex < 10) {
	                	String result = subAfterDots(ExcelUtils.getCellDataValue(workbook, cell));
		                switch (columnIndex) {
			            	case 2:
			            		tableMale.setFullTimeIndefinite(result);
			            		break;
			            	case 3:
			            		tableMale.setFullTimeFixedTerm(result);
			            		break;
			            	case 4:
			            		tableMale.setPartTimeIndefinite(result);
			            		break;
			            	case 5:
			            		tableMale.setTotalEnd12m(result);
			            		break;
			            	case 6:
			            		tableFemale.setFullTimeIndefinite(result);
			            		break;
			            	case 7:
			            		tableFemale.setFullTimeFixedTerm(result);
			            		break;
			            	case 8:
			            		tableFemale.setPartTimeIndefinite(result);
			            		break;
			            	case 9:
			            		tableFemale.setTotalEnd12m(result);
			            		break;
			            	default:
				            	break;
			            }
	                }
	            }
	            gearsDataTable4DTO.setTableMale(tableMale);
	            gearsDataTable4DTO.setTableFemale(tableFemale);
	            dataTable.add(gearsDataTable4DTO);
            }
		}
		Type dataListType = new TypeToken<ArrayList<GearsDataTable4DTO>>(){}.getType();
		String results = gson.toJson(dataTable, dataListType);

		// Update data table IN_CAREER_ASS
		updateDataTableInCareer("IN_CAREER_ASS", userEntity.getUserId(), results);
	}
	
	/**
	 * Xử lý đọc dữ liệu của sheet [Bảng4.9_Lao động giản đơn]
	 * @param workbook Workbook
	 * @param sheet Sheet
	 * @param userEntity UserEntity
	 */
	private void processSheetTable4Sub9(Workbook workbook, Sheet sheet, UserEntity userEntity) {

		// Get all rows
		Iterator<Row> rowIterator = sheet.iterator();
		GearsDataTable4DTO gearsDataTable4DTO = new GearsDataTable4DTO();
		GearsDataTable4SubDTO tableMale = new GearsDataTable4SubDTO();
		GearsDataTable4SubDTO tableFemale = new GearsDataTable4SubDTO();
		List<GearsDataTable4DTO> dataTable = new ArrayList<GearsDataTable4DTO>();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			int rowNum = row.getRowNum();
			// Đọc dữ liệu từ dòng 5 đến dòng 21 trong sheet Bảng4.9_Lao động giản đơn
			if (3 < rowNum && rowNum < 21) {
				gearsDataTable4DTO = new GearsDataTable4DTO();
	            switch(row.getRowNum()) {
		            case 4:
		            	gearsDataTable4DTO.setLevel("91");
		            	break;
		            case 5:
		            	gearsDataTable4DTO.setLevel("91.1");
		            	gearsDataTable4DTO.setParentLevel("91");
		            	break;
		            case 6:
		            	gearsDataTable4DTO.setLevel("91.2");
		            	gearsDataTable4DTO.setParentLevel("91");
		            	break;

		            case 7:
		            	gearsDataTable4DTO.setLevel("92");
		            	break;
		            case 8:
		            	gearsDataTable4DTO.setLevel("92.1");
		            	gearsDataTable4DTO.setParentLevel("92");
		            	break;

		            case 9:
		            	gearsDataTable4DTO.setLevel("93");
		            	break;
		            case 10:
		            	gearsDataTable4DTO.setLevel("93.1");
		            	gearsDataTable4DTO.setParentLevel("93");
		            	break;
		            case 11:
		            	gearsDataTable4DTO.setLevel("93.2");
		            	gearsDataTable4DTO.setParentLevel("93");
		            	break;
		            case 12:
		            	gearsDataTable4DTO.setLevel("93.3");
		            	gearsDataTable4DTO.setParentLevel("93");
		            	break;

		            case 13:
		            	gearsDataTable4DTO.setLevel("94");
		            	break;
		            case 14:
		            	gearsDataTable4DTO.setLevel("94.1");
		            	gearsDataTable4DTO.setParentLevel("94");
		            	break;

		            case 15:
		            	gearsDataTable4DTO.setLevel("95");
		            	break;
		            case 16:
		            	gearsDataTable4DTO.setLevel("95.1");
		            	gearsDataTable4DTO.setParentLevel("95");
		            	break;
		            case 17:
		            	gearsDataTable4DTO.setLevel("95.2");
		            	gearsDataTable4DTO.setParentLevel("95");
		            	break;

		            case 18:
		            	gearsDataTable4DTO.setLevel("96");
		            	break;
		            case 19:
		            	gearsDataTable4DTO.setLevel("96.1");
		            	gearsDataTable4DTO.setParentLevel("96");
		            	break;
		            case 20:
		            	gearsDataTable4DTO.setLevel("96.2");
		            	gearsDataTable4DTO.setParentLevel("96");
		            	break;
		            default:
		            	break;
	            }
	            
	            Iterator<Cell> cellIterator = row.cellIterator();
	            tableMale = new GearsDataTable4SubDTO();
	            tableFemale = new GearsDataTable4SubDTO();
	            while (cellIterator.hasNext()) {
	                //Read cell
	                Cell cell = cellIterator.next();
	                int columnIndex = cell.getColumnIndex();
	                // Đọc dữ liệu từ cột C đến cột J trong sheet Bảng4.9_Lao động giản đơn
	                if (1 < columnIndex && columnIndex < 10) {
	                	String result = subAfterDots(ExcelUtils.getCellDataValue(workbook, cell));
		                switch (columnIndex) {
			            	case 2:
			            		tableMale.setFullTimeIndefinite(result);
			            		break;
			            	case 3:
			            		tableMale.setFullTimeFixedTerm(result);
			            		break;
			            	case 4:
			            		tableMale.setPartTimeIndefinite(result);
			            		break;
			            	case 5:
			            		tableMale.setTotalEnd12m(result);
			            		break;
			            	case 6:
			            		tableFemale.setFullTimeIndefinite(result);
			            		break;
			            	case 7:
			            		tableFemale.setFullTimeFixedTerm(result);
			            		break;
			            	case 8:
			            		tableFemale.setPartTimeIndefinite(result);
			            		break;
			            	case 9:
			            		tableFemale.setTotalEnd12m(result);
			            		break;
			            	default:
				            	break;
			            }
	                }
	            }
	            gearsDataTable4DTO.setTableMale(tableMale);
	            gearsDataTable4DTO.setTableFemale(tableFemale);
	            dataTable.add(gearsDataTable4DTO);
            }
		}
		Type dataListType = new TypeToken<ArrayList<GearsDataTable4DTO>>(){}.getType();
		String results = gson.toJson(dataTable, dataListType);

		// Update data table IN_CAREER_SIMPLE
		updateDataTableInCareer("IN_CAREER_SIMPLE", userEntity.getUserId(), results);
	}
	
	
	/**
	 * Xử lý cập nhật dữ liệu tại các bảng IN_CAREER
	 * @param tableName
	 * @param userId
	 * @param inputData
	 */
	private void updateDataTableInCareer(String tableName, String userId, String inputData) {
		// Update data
		InCareerDTO inCareerDto = new InCareerDTO();
		inCareerDto.setTableName(tableName);
		inCareerDto.setUserId(userId);
		inCareerDto.setInputData(inputData);
		inCareerDto.setStatus(Constant.DATA_INPUT_SAVED);
		inCareerMapper.processTableByUserId(inCareerDto);
	}
	
	/**
	 * Xử lý xóa ký tự tính từ dấu [.]
	 * @param str
	 * @return
	 */
	private static String subAfterDots(String str) {
		try {
			str = CommonUtils.removeComma(str);
			return str.substring(0, str.indexOf("."));
		} catch (Exception e) {
			return StringUtils.EMPTY;
		}
	}

	/**
     * insert user
     * @param UserInfoDTO
     * @return
     * @throws Exception 
     */
    @Transactional(rollbackFor = RollBackException.class)
    public void insertReport(MemberDTO memberDTO) throws RollBackException {
        ReportEntity reportEntity = membershipMapper.findOneReportById(memberDTO.getUserId());
        if (reportEntity != null) {
            throw new RollBackException(MessageConstants.MSG_ERROR_EXIST);
        }
        
        //Insert report
        reportEntity = dozerMapper.map(memberDTO, ReportEntity.class);
        
        int result =  membershipMapper.updateReport(reportEntity);
        if (result == 0) {
            //return MessageConstants.MSG_ADD_ERROR;
            throw new RollBackException(MessageConstants.MSG_ADD_ERROR);
        }
    }
}