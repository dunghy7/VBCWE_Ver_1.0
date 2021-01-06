package com.dtsvn.vbcwe.common;

public final class Constant {

	/**
	 * regex định dạng ngày tháng mm/MM/yyyy
	 */
	public static final String REGEX_DATE_DD_MM_YYYY = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$";

	/**
	 * regex định dạng ngày tháng mm/MM/yyyy
	 */
	public static final String REGEX_EMAIL = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

	/**
	 * regex chỉ được phép nhập số
	 */
	public static final String REGEX_NUMBER = "^([1-9][0-9]*)?$";

	/**
	 * REGEX_ONLY_NUMBER
	 */
	public static final String REGEX_ONLY_NUMBER = "^[0-9]*$";

	/**
	 * REGEX_CONTRACTCODE
	 */
	public static final String REGEX_CONTRACTCODE = "^(220){1}?[123]\\-(2){1}?[0-9]{4}\\-[0-9]{2}$";

	// version default value
	public static final int VERSION_DEFAULT_VALUE = 1;

	public static final String UTF_8_CHARSET = "UTF-8";

	public static final String LOGIN_USER_KEY = "LOGIN_USER_SESSION_KEY";

	public static final String REPORT_STATUS = "reportStatus";

	// DATABASE DELETE FLAG
	/** DB_AVAILABLE */
	public static final String DB_AVAILABLE = "0";

	/** DB_DELETED */
	public static final String DB_DELETED = "1";

	// ROLE
	public static final String ROLE_FREEMEMBER = "02";
	public static final String ROLE_MEMBERSHIP = "01";
	public static final String ROLE_ADMIN = "00";

	/**
	 * message
	 */
	public static final String MESSAGE_KEY = "message";

	/**
	 * message error key
	 */
	public static final String MESSAGE_ERR_KEY = "messageErr";

	/**
	 * all option
	 */
	public static final String OPTION_ALL = "Tất Cả";

	// BOOLEAN VALUE
	/** TRUE flag */
	public static final String TRUE_FLG = "1";

	/** FALSE flag */
	public static final String FALSE_FLG = "0";

	// DATE TIME FORMAT
	/** YYYYMMDDの日付フォーマット */
	public static final String DATE_FORMAT_YYYYMMDD = "yyyy/MM/dd";

	/** 時間 */
	public static final String TIME = "HH:mm:ss";

	/** YYYYMMDDHHSSの日時フォーマット */
	public static final String DATE_TIME_FORMAT_YYYYMMDDHHSS = "yyyy/MM/dd HH:mm:ss";

	/** DATE_TIME_PATTERN */
	public static final String DATE_TIME_PATTERN = "yyyy/MM/dd HH:mm:ss";

	/** FORMAT_DATE_TIME_PATTERN */
	public static final String FORMAT_DATE_TIME_PATTERN = "yyyyMMddHHmmssSSS";

	/** FORMAT_DATE_TIME_PATTERN */
	public static final String FORMAT_CSV_TIME_PATTERN = "yyyyMMddHHmmss";

	public static final String FORMAT_TIME_PATTERN = "HHmmss";

	/**
	 * FORMAT_DATE_TIME_JP_PATTERN
	 */
	public static final String FORMAT_DATE_TIME_JP_PATTERN = "yyyy年MM月dd日";

	public static final String DATE_FORMAT_DDMMYYYY = "dd/MM/yyyy";

	public static final String DATE_FORMAT_MMYYYY = "MM/yyyy";

	public static final String DATE_FORMAT_YYYYMMDD_MINUS = "yyyy-MM-dd";

	public static final String LOCAL_DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";

	public static final String LOCAL_DATE_FORMAT = "yyyy-MM-dd";

	// FILE EXTENTION
	/** csv 拡張子 */
	public static final String CSV_EXTENTION = ".csv";

	/** Zip 拡張子 */
	public static final String ZIP_EXTENTION = ".zip";

	/** pdf 拡張子 */
	public static final String PDF_EXT = ".pdf";

	/**
	 * extention excel
	 */
	public static final String EXCEL_EXTENTION = ".xlsx";

	/** not exist index */
	public static final Integer NOT_EXIST_INDEX = -1;

	/** COMMA */
	public static final String COMMA = ",";

	// ajax status request
	public static final int STATUS_SUCCESS = 0;
	public static final int STATUS_ERROR = 1;
	public static final int STATUS_SYSTEM_ERROR = -1;
	public static final int STATUS_ACCESS_DENY = -2;
	public static final int STATUS_EXCLUSIVE_ERROR = -3;
	public static final int STATUS_VALID_ERROR = -4;

	public static final String SYM_MINUS = "-";
	public static final String SYM_SLASH = "/";
	public static final String BLANK = "";

	public static final String URL_PRIOR_LOGIN = "url_prior_login";
	public static final String IS_ACCESS_URL = "1";
	public static final String IS_NOT_ACCESS_URL = "0";
	
	//Report status
	public enum MEMBER_REPORT_STATUS {
		REPORT_PENDING_PROCESS("0"),
		REPORT_PENDING_APPROVE("1"),
		REPORT_APPROVED("2"),
		REPORT_DENIED("3");

		public String value;

		private MEMBER_REPORT_STATUS(String value) {
			this.value = value;
		}
	}

	public static final String LEFT_BRACES = "{";
	public static final String RIGHT_BRACES = "}";

	//Email verify status
	public static final String EMAIL_NOT_VERIFY = "0";
	public static final String EMAIL_VERIFIED = "1";

	//User type
	public static final String USER_TYPE_ADMIN = "00";
	public static final String USER_TYPE_MEMBER = "01";
	public static final String USER_TYPE_FREE = "02";

	//Data input gender
	public static final String GENDER_MALE = "m";
	public static final String GENDER_FEMALE = "f";

	//Data input status
	public static final String DATA_INPUT_SAVED = "0";
	public static final String DATA_INPUT_COMPLETED = "1";
	
	//Output folder path name
	public static final String OUTPUT_FOLDER_PATH_NAME = "report";

    /**
     * Tên bảng vật lý chứa dữ liệu của màn hình DANH MỤC NGHỀ NGHIỆP - QUẢN LÝ
     */
    public static final String TABLE4_SUB1_NAME = "IN_CAREER_MNG";

    /**
     * Tên bảng vật lý chứa dữ liệu của màn hình DANH MỤC NGHỀ NGHIỆP - CHUYÊN GIA
     */
    public static final String TABLE4_SUB2_NAME = "IN_CAREER_EXP";

    /**
     * Tên bảng vật lý chứa dữ liệu của màn hình DANH MỤC NGHỀ NGHIỆP - KỸ THUẬT
     * VIÊN VÀ TRỢ LÝ CHUYÊN GIA
     */
    public static final String TABLE4_SUB3_NAME = "IN_CAREER_TECH";

    /**
     * Tên bảng vật lý chứa dữ liệu của màn hình DANH MỤC NGHỀ NGHIỆP - NHÂN VIÊN
     * TRỢ LÝ VĂN PHÒNG
     */
    public static final String TABLE4_SUB4_NAME = "IN_CAREER_OFFICE";

    /**
     * Tên bảng vật lý chứa dữ liệu của màn hình DANH MỤC NGHỀ NGHIỆP - NHÂN VIÊN
     * TRỢ LÝ VĂN PHÒNG
     */
    public static final String TABLE4_SUB5_NAME = "IN_CAREER_SERVICE";

    /**
     * Tên bảng vật lý chứa dữ liệu của màn hình DANH MỤC NGHỀ NGHIỆP - NHÂN VIÊN
     * TRỢ LÝ VĂN PHÒNG
     */
    public static final String TABLE4_SUB6_NAME = "IN_CAREER_AGR";

    /**
     * Tên bảng vật lý chứa dữ liệu của màn hình DANH MỤC NGHỀ NGHIỆP - LAO ĐỘNG THỦ
     * CÔNG VÀ CÁC NGHỀ NGHIỆP CÓ LIÊN QUAN KHÁC
     */
    public static final String TABLE4_SUB7_NAME = "IN_CAREER_MANUAL";

    /**
     * Tên bảng vật lý chứa dữ liệu của màn hình DANH MỤC NGHỀ NGHIỆP - THỢ LẮP RÁP
     * VÀ VẬN HÀNH MÁY MÓC, THIẾT BỊ
     */
    public static final String TABLE4_SUB8_NAME = "IN_CAREER_ASS";

    /**
     * Tên bảng vật lý chứa dữ liệu của màn hình DANH MỤC NGHỀ NGHIỆP - LAO ĐỘNG
     * GIẢN ĐƠN
     */
    public static final String TABLE4_SUB9_NAME = "IN_CAREER_SIMPLE";

	/** Json templ GEARS HR Data Table */
	public interface JSON_TEMPL {
		/** table DỊCH CHUYỂN LAO ĐỘNG */
		public static final String TABLE1 = "[{\"level\":\"5\",\"gender\":\"m\",\"totalBegin12m\":\"0\",\"recruitedOutside\":\"0\",\"promotedToLvl\":\"0\",\"promotedFromLvl\":\"0\",\"leftCompany\":\"0\",\"totalEnd12m\":\"0\"},"
				+ "{\"level\":\"5\",\"gender\":\"f\",\"totalBegin12m\":\"0\",\"recruitedOutside\":\"0\",\"promotedToLvl\":\"0\",\"promotedFromLvl\":\"0\",\"leftCompany\":\"0\",\"totalEnd12m\":\"0\"},"
				+ "{\"level\":\"4\",\"gender\":\"m\",\"totalBegin12m\":\"0\",\"recruitedOutside\":\"0\",\"promotedToLvl\":\"0\",\"promotedFromLvl\":\"0\",\"leftCompany\":\"0\",\"totalEnd12m\":\"0\"},"
				+ "{\"level\":\"4\",\"gender\":\"f\",\"totalBegin12m\":\"0\",\"recruitedOutside\":\"0\",\"promotedToLvl\":\"0\",\"promotedFromLvl\":\"0\",\"leftCompany\":\"0\",\"totalEnd12m\":\"0\"},"
				+ "{\"level\":\"3\",\"gender\":\"m\",\"totalBegin12m\":\"0\",\"recruitedOutside\":\"0\",\"promotedToLvl\":\"0\",\"promotedFromLvl\":\"0\",\"leftCompany\":\"0\",\"totalEnd12m\":\"0\"},"
				+ "{\"level\":\"3\",\"gender\":\"f\",\"totalBegin12m\":\"0\",\"recruitedOutside\":\"0\",\"promotedToLvl\":\"0\",\"promotedFromLvl\":\"0\",\"leftCompany\":\"0\",\"totalEnd12m\":\"0\"},"
				+ "{\"level\":\"2\",\"gender\":\"m\",\"totalBegin12m\":\"0\",\"recruitedOutside\":\"0\",\"promotedToLvl\":\"0\",\"promotedFromLvl\":\"0\",\"leftCompany\":\"0\",\"totalEnd12m\":\"0\"},"
				+ "{\"level\":\"2\",\"gender\":\"f\",\"totalBegin12m\":\"0\",\"recruitedOutside\":\"0\",\"promotedToLvl\":\"0\",\"promotedFromLvl\":\"0\",\"leftCompany\":\"0\",\"totalEnd12m\":\"0\"},"
				+ "{\"level\":\"1\",\"gender\":\"m\",\"totalBegin12m\":\"0\",\"recruitedOutside\":\"0\",\"promotedToLvl\":\"0\",\"promotedFromLvl\":\"0\",\"leftCompany\":\"0\",\"totalEnd12m\":\"0\"},"
				+ "{\"level\":\"1\",\"gender\":\"f\",\"totalBegin12m\":\"0\",\"recruitedOutside\":\"0\",\"promotedToLvl\":\"0\",\"promotedFromLvl\":\"0\",\"leftCompany\":\"0\",\"totalEnd12m\":\"0\"},"
				+ "{\"level\":\"0\",\"gender\":\"m\",\"totalBegin12m\":\"0\",\"recruitedOutside\":\"0\",\"promotedToLvl\":\"0\",\"promotedFromLvl\":\"0\",\"leftCompany\":\"0\",\"totalEnd12m\":\"0\"},"
				+ "{\"level\":\"0\",\"gender\":\"f\",\"totalBegin12m\":\"0\",\"recruitedOutside\":\"0\",\"promotedToLvl\":\"0\",\"promotedFromLvl\":\"0\",\"leftCompany\":\"0\",\"totalEnd12m\":\"0\"},"
				+ "{\"level\":\"0\",\"gender\":\"mf\",\"totalBegin12m\":\"0\",\"recruitedOutside\":\"0\",\"promotedToLvl\":\"0\",\"promotedFromLvl\":\"0\",\"leftCompany\":\"0\",\"totalEnd12m\":\"0\"}]";

		/** table VIỆC LÀM */
		public static final String TABLE2 = "[{\"level\":\"5\",\"gender\":\"m\",\"indefinitelyContract\":\"0\",\"LimitedContract\":\"0\",\"partTime\":\"0\",\"total\":\"0\",\"checkAgain\":\"0\"},"
				+ "{\"level\":\"5\",\"gender\":\"f\",\"indefinitelyContract\":\"0\",\"LimitedContract\":\"0\",\"partTime\":\"0\",\"total\":\"0\",\"checkAgain\":\"0\"},"
				+ "{\"level\":\"4\",\"gender\":\"m\",\"indefinitelyContract\":\"0\",\"LimitedContract\":\"0\",\"partTime\":\"0\",\"total\":\"0\",\"checkAgain\":\"0\"},"
				+ "{\"level\":\"4\",\"gender\":\"f\",\"indefinitelyContract\":\"0\",\"LimitedContract\":\"0\",\"partTime\":\"0\",\"total\":\"0\",\"checkAgain\":\"0\"},"
				+ "{\"level\":\"3\",\"gender\":\"m\",\"indefinitelyContract\":\"0\",\"LimitedContract\":\"0\",\"partTime\":\"0\",\"total\":\"0\",\"checkAgain\":\"0\"},"
				+ "{\"level\":\"3\",\"gender\":\"f\",\"indefinitelyContract\":\"0\",\"LimitedContract\":\"0\",\"partTime\":\"0\",\"total\":\"0\",\"checkAgain\":\"0\"},"
				+ "{\"level\":\"2\",\"gender\":\"m\",\"indefinitelyContract\":\"0\",\"LimitedContract\":\"0\",\"partTime\":\"0\",\"total\":\"0\",\"checkAgain\":\"0\"},"
				+ "{\"level\":\"2\",\"gender\":\"f\",\"indefinitelyContract\":\"0\",\"LimitedContract\":\"0\",\"partTime\":\"0\",\"total\":\"0\",\"checkAgain\":\"0\"},"
				+ "{\"level\":\"1\",\"gender\":\"m\",\"indefinitelyContract\":\"0\",\"LimitedContract\":\"0\",\"partTime\":\"0\",\"total\":\"0\",\"checkAgain\":\"0\"},"
				+ "{\"level\":\"1\",\"gender\":\"f\",\"indefinitelyContract\":\"0\",\"LimitedContract\":\"0\",\"partTime\":\"0\",\"total\":\"0\",\"checkAgain\":\"0\"},"
				+ "{\"level\":\"0\",\"gender\":\"m\",\"indefinitelyContract\":\"0\",\"LimitedContract\":\"0\",\"partTime\":\"0\",\"total\":\"0\",\"checkAgain\":\"0\"},"
				+ "{\"level\":\"0\",\"gender\":\"f\",\"indefinitelyContract\":\"0\",\"LimitedContract\":\"0\",\"partTime\":\"0\",\"total\":\"0\",\"checkAgain\":\"0\"}]";

		/** table BAN LÃNH ĐẠO */
		public static final String TABLE3 = "[{\"gender\":\"m\",\"total\":\"0\"},{\"gender\":\"f\",\"total\":\"0\"},{\"gender\":\"mf\",\"total\":\"0\"}]";

		/** table DANH MỤC NGHỀ NGHIỆP - QUẢN LÝ */
		public static final String TABLE4_SUB1 = "[{\"level\":\"11\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"11.1\",\"parentLevel\":\"11\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"11.2\",\"parentLevel\":\"11\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"12\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"12.1\",\"parentLevel\":\"12\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"12.2\",\"parentLevel\":\"12\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"13\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"13.1\",\"parentLevel\":\"13\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"13.2\",\"parentLevel\":\"13\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"13.3\",\"parentLevel\":\"13\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"13.4\",\"parentLevel\":\"13\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"14\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"14.1\",\"parentLevel\":\"14\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"14.2\",\"parentLevel\":\"14\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"14.3\",\"parentLevel\":\"14\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}}]";

		/** table DANH MỤC NGHỀ NGHIỆP - CHUYÊN GIA */
		public static final String TABLE4_SUB2 = "[{\"level\":\"21\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"21.1\",\"parentLevel\":\"21\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"21.2\",\"parentLevel\":\"21\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"21.3\",\"parentLevel\":\"21\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"21.4\",\"parentLevel\":\"21\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"21.5\",\"parentLevel\":\"21\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"21.6\",\"parentLevel\":\"21\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"22\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"22.1\",\"parentLevel\":\"22\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"22.2\",\"parentLevel\":\"22\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"22.3\",\"parentLevel\":\"22\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"22.4\",\"parentLevel\":\"22\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"22.5\",\"parentLevel\":\"22\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"22.6\",\"parentLevel\":\"22\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"23\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"23.1\",\"parentLevel\":\"23\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"23.2\",\"parentLevel\":\"23\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"23.3\",\"parentLevel\":\"23\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"23.4\",\"parentLevel\":\"23\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"23.5\",\"parentLevel\":\"23\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"24\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"24.1\",\"parentLevel\":\"24\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"24.2\",\"parentLevel\":\"24\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"24.3\",\"parentLevel\":\"24\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"25\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"25.1\",\"parentLevel\":\"25\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"25.2\",\"parentLevel\":\"25\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"26\",\"parentLevel\":\"\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"26.1\",\"parentLevel\":\"26\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"26.2\",\"parentLevel\":\"26\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"26.3\",\"parentLevel\":\"26\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"26.4\",\"parentLevel\":\"26\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"26.5\",\"parentLevel\":\"26\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}}]";

		/** table DANH MỤC NGHỀ NGHIỆP - KỸ THUẬT VIÊN VÀ TRỢ LÝ CHUYÊN GIA */
		public static final String TABLE4_SUB3 = "[{\"level\":\"31\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"31.1\",\"parentLevel\":\"31\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"31.2\",\"parentLevel\":\"31\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"31.3\",\"parentLevel\":\"31\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"31.4\",\"parentLevel\":\"31\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"31.5\",\"parentLevel\":\"31\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"32\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"32.1\",\"parentLevel\":\"32\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"32.2\",\"parentLevel\":\"32\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"32.3\",\"parentLevel\":\"32\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"32.4\",\"parentLevel\":\"32\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"32.5\",\"parentLevel\":\"32\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"33\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"33.1\",\"parentLevel\":\"33\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"33.2\",\"parentLevel\":\"33\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"33.3\",\"parentLevel\":\"33\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"33.4\",\"parentLevel\":\"33\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"33.5\",\"parentLevel\":\"33\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"34\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"34.1\",\"parentLevel\":\"34\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"34.2\",\"parentLevel\":\"34\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"34.3\",\"parentLevel\":\"34\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"35\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"35.1\",\"parentLevel\":\"35\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"35.2\",\"parentLevel\":\"35\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}}]";

		/** table DANH MỤC NGHỀ NGHIỆP - NHÂN VIÊN TRỢ LÝ VĂN PHÒNG */
		public static final String TABLE4_SUB4 = "[{\"level\":\"41\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"41.1\",\"parentLevel\":\"41\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"41.2\",\"parentLevel\":\"41\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"41.3\",\"parentLevel\":\"41\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"42\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"42.1\",\"parentLevel\":\"42\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"42.2\",\"parentLevel\":\"42\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"43\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"43.1\",\"parentLevel\":\"43\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"43.2\",\"parentLevel\":\"43\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"44\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"44.1\",\"parentLevel\":\"44\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}}]";

		/** table DANH MỤC NGHỀ NGHIỆP - NHÂN VIÊN DỊCH VỤ VÀ BÁN HÀNG */
		public static final String TABLE4_SUB5 = "[{\"level\":\"51\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"51.1\",\"parentLevel\":\"51\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"51.2\",\"parentLevel\":\"51\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"51.3\",\"parentLevel\":\"51\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"51.4\",\"parentLevel\":\"51\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"51.5\",\"parentLevel\":\"51\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"51.6\",\"parentLevel\":\"51\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"52\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"52.1\",\"parentLevel\":\"52\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"52.2\",\"parentLevel\":\"52\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"52.3\",\"parentLevel\":\"52\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"52.4\",\"parentLevel\":\"52\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"53\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"53.1\",\"parentLevel\":\"53\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"53.2\",\"parentLevel\":\"53\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"54\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"54.1\",\"parentLevel\":\"54\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}}]";

		/**
		 * table DANH MỤC NGHỀ NGHIỆP - LAO ĐỘNG CÓ KỸ NĂNG TRONG NÔNG NGHIỆP, LÂM
		 * NGHIỆP VÀ THỦY SẢN
		 */
		public static final String TABLE4_SUB6 = "[{\"level\":\"61\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"61.1\",\"parentLevel\":\"61\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"61.2\",\"parentLevel\":\"61\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"61.3\",\"parentLevel\":\"61\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"62\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"62.1\",\"parentLevel\":\"62\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"62.2\",\"parentLevel\":\"62\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"63\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"63.1\",\"parentLevel\":\"63\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"63.2\",\"parentLevel\":\"63\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"63.3\",\"parentLevel\":\"63\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"63.4\",\"parentLevel\":\"63\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}}]";

		/**
		 * table DANH MỤC NGHỀ NGHIỆP - LAO ĐỘNG THỦ CÔNG VÀ CÁC NGHỀ NGHIỆP CÓ LIÊN
		 * QUAN KHÁC
		 */
		public static final String TABLE4_SUB7 = "[{\"level\":\"71\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"71.1\",\"parentLevel\":\"71\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"71.2\",\"parentLevel\":\"71\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"71.3\",\"parentLevel\":\"71\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"72\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"72.1\",\"parentLevel\":\"72\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"72.2\",\"parentLevel\":\"72\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"72.3\",\"parentLevel\":\"72\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"73\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"73.1\",\"parentLevel\":\"73\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"73.2\",\"parentLevel\":\"73\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"74\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"74.1\",\"parentLevel\":\"74\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"74.2\",\"parentLevel\":\"74\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"75\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"75.1\",\"parentLevel\":\"75\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"75.2\",\"parentLevel\":\"75\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"75.3\",\"parentLevel\":\"75\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"75.4\",\"parentLevel\":\"75\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}}]";

		/** table DANH MỤC NGHỀ NGHIỆP - THỢ LẮP RÁP VÀ VẬN HÀNH MÁY MÓC, THIẾT BỊ */
		public static final String TABLE4_SUB8 = "[{\"level\":\"81\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"81.1\",\"parentLevel\":\"81\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"81.2\",\"parentLevel\":\"81\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"81.3\",\"parentLevel\":\"81\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"81.4\",\"parentLevel\":\"81\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"81.5\",\"parentLevel\":\"81\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"81.6\",\"parentLevel\":\"81\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"81.7\",\"parentLevel\":\"81\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"81.8\",\"parentLevel\":\"81\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"82\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"82.1\",\"parentLevel\":\"82\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"83\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"83.1\",\"parentLevel\":\"83\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"83.2\",\"parentLevel\":\"83\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"83.3\",\"parentLevel\":\"83\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"83.4\",\"parentLevel\":\"83\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"83.5\",\"parentLevel\":\"83\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}}]";

		/** table DANH MỤC NGHỀ NGHIỆP - LAO ĐỘNG GIẢN ĐƠN */
		public static final String TABLE4_SUB9 = "[{\"level\":\"91\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"91.1\",\"parentLevel\":\"91\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"91.2\",\"parentLevel\":\"91\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"92\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"92.1\",\"parentLevel\":\"92\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"93\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"93.1\",\"parentLevel\":\"93\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"93.2\",\"parentLevel\":\"93\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"93.3\",\"parentLevel\":\"93\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"94\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"94.1\",\"parentLevel\":\"94\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"95\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"95.1\",\"parentLevel\":\"95\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"95.2\",\"parentLevel\":\"95\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"96\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"96.1\",\"parentLevel\":\"96\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}},"
				+ "{\"level\":\"96.2\",\"parentLevel\":\"96\",\"tableMale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"},\"tableFemale\":{\"fullTimeIndefinite\":\"0\",\"fullTimeFixedTerm\":\"0\",\"partTimeIndefinite\":\"0\",\"totalEnd12m\":\"0\"}}]";

		public static final String BENCHMARK = "[{\"level\":\"0\",\"averageWomen\":\"0\",\"minDiff\":\"0\",\"maxDiff\":\"0\"},"
				+ "{\"level\":\"1\",\"averageWomen\":\"0\",\"minDiff\":\"0\",\"maxDiff\":\"0\"},"
				+ "{\"level\":\"2\",\"averageWomen\":\"0\",\"minDiff\":\"0\",\"maxDiff\":\"0\"},"
				+ "{\"level\":\"3\",\"averageWomen\":\"0\",\"minDiff\":\"0\",\"maxDiff\":\"0\"},"
				+ "{\"level\":\"4\",\"averageWomen\":\"0\",\"minDiff\":\"0\",\"maxDiff\":\"0\"},"
				+ "{\"level\":\"5\",\"averageWomen\":\"0\",\"minDiff\":\"0\",\"maxDiff\":\"0\"}]";
	}
}
