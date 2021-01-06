package com.dtsvn.vbcwe.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.docx4j.XmlUtils;
import org.docx4j.com.google.common.base.Strings;
import org.docx4j.openpackaging.packages.PresentationMLPackage;
import org.docx4j.openpackaging.parts.Part;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.DrawingML.Chart;
import org.docx4j.openpackaging.parts.DrawingML.DiagramDataPart;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.dto.SurveyDTO;

public class CommonUtils {

	/**
	 * convert string to LocalDateTime
	 * 
	 * @param datetime
	 * @param formatString
	 * @return LocalDateTime
	 */
	public static LocalDateTime stringToLocalDateTime(String datetime, String formatString) {
		if (StringUtils.isEmpty(datetime)) {
			return null;
		}

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
			return LocalDateTime.parse(datetime, formatter);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * convert string to LocalDate
	 * 
	 * @param datetime
	 * @param formatString
	 * @return LocalDate
	 */
	public static LocalDate stringToLocalDate(String datetime, String formatString) {
		if (StringUtils.isEmpty(datetime)) {
			return null;
		}

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
			return LocalDate.parse(datetime, formatter);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * <p>
	 * 説明 : LocalDateTime to string
	 * </p>
	 * 
	 * @param date   LocalDateTime
	 * @param format format date
	 * @return String str date format
	 */
	public static String localDateTimeToString(LocalDateTime date, String format) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
			return date.format(formatter);
		} catch (Exception e) {
			return StringUtils.EMPTY;
		}
	}

	/**
	 * <p>
	 * 説明 : LocalDate to string
	 * </p>
	 * 
	 * @param date   LocalDate
	 * @param format format date
	 * @return String str date format
	 */
	public static String localDateToString(LocalDate date, String format) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
			return date.format(formatter);
		} catch (Exception e) {
			return StringUtils.EMPTY;
		}
	}

	/**
	 * String to Integer
	 * 
	 * @param str String
	 * @return Integer
	 */
	public static Integer toInt(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}

		try {
			str = removeComma(str);
			return Integer.valueOf(str);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * String to long
	 * 
	 * @param str String
	 * @return Long
	 */
	public static Long toLong(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}

		try {
			str = removeComma(str);
			return Long.valueOf(str);
		} catch (Exception e) {
			return 0L;
		}
	}

	/**
	 * String to Double
	 * 
	 * @param String
	 * @return Double
	 */
	public static Double toDouble(String str) {
		if (StringUtils.isEmpty(str)) {
			return 0D;
		}

		try {
			str = removeComma(str);
			return Double.parseDouble(str);
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * <p>
	 * 説明 : カンマで数値をフォーマット
	 * </p>
	 * 
	 * @param value : Integer
	 * @return "#,###"
	 */
	public static String formatNumberWithCommas(Integer value) {
		try {
			DecimalFormat formatter = new DecimalFormat("#,###");
			return formatter.format(value);
		} catch (Exception e) {
			return StringUtils.EMPTY;
		}
	}

	/**
	 * <p>
	 * 説明 : カンマで数値をフォーマット
	 * </p>
	 * 
	 * @param value : Integer
	 * @return "#,###"
	 */
	public static String formatNumberWithCommas(Long value) {
		try {
			DecimalFormat formatter = new DecimalFormat("#,###");
			return formatter.format(value);
		} catch (Exception e) {
			return StringUtils.EMPTY;
		}
	}

	/**
	 * <p>
	 * 説明 : カンマで数値をフォーマット
	 * </p>
	 * 
	 * @param value : double
	 * @return "#.###"
	 */
	public static String formatNumberWithCommas(double value) {
		try {
			DecimalFormat formatter = new DecimalFormat("#0.00");
			return formatter.format(value);
		} catch (Exception e) {
			return StringUtils.EMPTY;
		}
	}

	/**
	 * <p>
	 * 説明 : convert money format
	 * </p>
	 * 
	 * @param String
	 * @return str money format
	 */
	public static String toMoneyFormat(String str) {
		if (StringUtils.isEmpty(str)) {
			return StringUtils.EMPTY;
		} else {
			double amount = Double.parseDouble(str);
			DecimalFormat formatter = new DecimalFormat("###,###,###");
			return formatter.format(amount).toString();
		}
	}

	/**
	 * <p>
	 * 説明 : データ設定メソッドを呼び出す
	 * </p>
	 * 
	 * @param targetObj  Target Object
	 * @param paramObj   Param Object
	 * @param methodName method Name
	 */
	public static void invokeSetMethod(Object targetObj, Object paramObj, String methodName, Class<?>... target) {
		try {
			if (paramObj != null) {
				Method setMethod = targetObj.getClass().getMethod(methodName,
						paramObj instanceof ArrayList ? List.class : paramObj.getClass());
				setMethod.invoke(targetObj, paramObj);
			} else {
				Method method = targetObj.getClass().getMethod(methodName, target);
				method.invoke(targetObj, paramObj);
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return;
		} catch (SecurityException e) {
			e.printStackTrace();
			return;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * 入力文字列は数字であるかのチェック
	 *
	 * @param number 文字列
	 * @return true: 入力文字列は数字 false: 入力文字列は数字以外
	 */
	public static boolean isSignNumber(String number) {
		if (StringUtils.isEmpty(number)) {
			return true;
		}

		Pattern p = Pattern.compile("^[-]?[0-9]+$");
		Matcher m = p.matcher(number);

		return m.find();
	}

	public static boolean isNumberOrDecimal(String number) {
		if (StringUtils.isEmpty(number)) {
			return true;
		}

		Pattern p = Pattern.compile("^[1-9]\\d*(\\.\\d+)?$");
		Matcher m = p.matcher(number);

		return m.find();
	}

	/**
	 * 日付フォーアットをチェック
	 * 
	 * @param dateString   Date string value
	 * @param formatString Format date
	 * @return boolean true : 有効の日付フォーマットである場合 false : 無効の日付フォーマットである場合
	 */
	public static boolean isDateFormat(String dateString, String formatString) {
		if (StringUtils.isEmpty(dateString)) {
			return false;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
		try {
			LocalDate.parse(dateString, formatter);
			return true;
		} catch (DateTimeException e) {
			return false;
		}
	}

	/**
	 * String to long
	 * 
	 * @param String
	 * @return Long
	 */
	public static Long toLongHasNull(String str) {
		try {
			if (str != null && str.length() > 0) {
				return Long.valueOf(str);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * String to Integer
	 * 
	 * @param String
	 * @return Integer
	 */
	public static Integer toIntHasNull(String str) {
		try {
			if (str != null && str.length() > 0) {
				return Integer.valueOf(str);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * <p>
	 * 説明 : 新しいモデルマッパーの作成
	 * </p>
	 * 
	 * @author : phuc.nh
	 * @since : 2018/05/31
	 * @return ModelMapper
	 */
	public static ModelMapper createNewModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		modelMapper.getConfiguration().setPropertyCondition(new Condition<Object, Object>() {
			public boolean applies(MappingContext<Object, Object> pContext) {
				if (null == pContext.getSource() || null == pContext.getDestinationType()) {
					return false;
				}

				if (pContext.getSourceType().equals(String.class)
						&& (Integer.class.equals(pContext.getDestinationType())
								|| int.class.equals(pContext.getDestinationType()))) {
					if (isSignNumber(pContext.getSource().toString())) {
						return true;
					}
					return false;
				}
				return true;
			}
		});

		// List<T> -> List<T>に変換
//        Converter<List<T>, List<T>> listToList = new Converter<List<T>, List<T>>() {
//            @Override
//            public List<T> convert(MappingContext<List<T>, List<T>> context) {
//                List<T> source = context.getSource();
//                if (source == null || source.size() == 0) {
//                    return new ArrayList<T>();
//                }
//
//                TypeMap<List<T>, List<T>> typeMap = modelMapper.createTypeMap(context.getSourceType(), context.getDestinationType());
//                return typeMap.map(context.getSource());
//            }
//        };
//        modelMapper.addConverter(listToList);

		// List<String> -> Stringに変換
		Converter<List<String>, String> stringListToString = new Converter<List<String>, String>() {
			@Override
			public String convert(MappingContext<List<String>, String> context) {
				List<String> source = context.getSource();
				if (source == null || source.size() == 0) {
					return StringUtils.EMPTY;
				}
				return String.join(Constant.COMMA, source);
			}
		};
		modelMapper.addConverter(stringListToString);

		// String -> List<String>に変換
		Converter<String, List<String>> stringToStringList = new Converter<String, List<String>>() {
			@Override
			public List<String> convert(MappingContext<String, List<String>> context) {
				String source = context.getSource();
				if (StringUtils.isEmpty(source)) {
					return new ArrayList<String>();
				}
				return Arrays.asList(source.split(Constant.COMMA));
			}
		};
		modelMapper.addConverter(stringToStringList);

		// String -> LocalDateに変換
		Converter<String, LocalDate> localDateConverter = new Converter<String, LocalDate>() {
			@Override
			public LocalDate convert(MappingContext<String, LocalDate> context) {
				String source = context.getSource();
				if (StringUtils.isEmpty(source) || !isDateFormat(source, Constant.DATE_FORMAT_DDMMYYYY)) {
					return null;
				}
				return stringToLocalDate(source, Constant.DATE_FORMAT_DDMMYYYY);
			}
		};
		modelMapper.addConverter(localDateConverter);

		// LocalDate -> Stringに変換
		Converter<LocalDate, String> stringConverter = new Converter<LocalDate, String>() {
			@Override
			public String convert(MappingContext<LocalDate, String> context) {
				LocalDate source = context.getSource();
				if (StringUtils.isEmpty(source.toString())) {
					return null;
				}

				return localDateToString(source, Constant.DATE_FORMAT_DDMMYYYY);
			}
		};
		modelMapper.addConverter(stringConverter);

		// String -> LocalDateTimeに変換
		Converter<String, LocalDateTime> localDateTimeConverter = new Converter<String, LocalDateTime>() {
			@Override
			public LocalDateTime convert(MappingContext<String, LocalDateTime> context) {
				String source = context.getSource();
				if (StringUtils.isEmpty(source) || !isDateFormat(source, Constant.LOCAL_DATE_TIME_FORMAT)) {
					return null;
				}
				return stringToLocalDateTime(source, Constant.LOCAL_DATE_TIME_FORMAT);
			}

		};
		modelMapper.addConverter(localDateTimeConverter);

		// LocalDateTime -> Stringに変換
		Converter<LocalDateTime, String> stringTimeConverter = new Converter<LocalDateTime, String>() {
			@Override
			public String convert(MappingContext<LocalDateTime, String> context) {
				LocalDateTime source = context.getSource();
				if (StringUtils.isEmpty(source.toString())) {
					return null;
				}
				return localDateTimeToString(source, Constant.LOCAL_DATE_TIME_FORMAT);
			}
		};
		modelMapper.addConverter(stringTimeConverter);

		// Long -> Stringに変換
		Converter<Long, String> longStringConverter = new Converter<Long, String>() {
			@Override
			public String convert(MappingContext<Long, String> context) {
				Long source = context.getSource();
				if (source == null) {
					return null;
				}
				return String.valueOf(source);
			}
		};
		modelMapper.addConverter(longStringConverter);

		// String -> Longに変換
		Converter<String, Long> stringLongConverter = new Converter<String, Long>() {
			@Override
			public Long convert(MappingContext<String, Long> context) {
				String source = removeComma(context.getSource());
				if (StringUtils.isEmpty(source)) {
					return null;
				}
				return toLongHasNull(source);
			}
		};
		modelMapper.addConverter(stringLongConverter);

		// Integer -> Stringに変換
		Converter<Integer, String> integerStringConverter = new Converter<Integer, String>() {
			@Override
			public String convert(MappingContext<Integer, String> context) {
				Integer source = context.getSource();
				if (source == null) {
					return null;
				}
				return String.valueOf(source);
			}
		};
		modelMapper.addConverter(integerStringConverter);

		// String -> Integerに変換
		Converter<String, Integer> stringIntegerConverter = new Converter<String, Integer>() {
			@Override
			public Integer convert(MappingContext<String, Integer> context) {
				String source = removeComma(context.getSource());
				if (StringUtils.isEmpty(source)) {
					return null;
				}
				return toIntHasNull(source);
			}
		};
		modelMapper.addConverter(stringIntegerConverter);

		// Boolean -> Stringに変換
		Converter<Boolean, String> booleanSringConverter = new Converter<Boolean, String>() {
			@Override
			public String convert(MappingContext<Boolean, String> context) {
				Boolean source = context.getSource();
				if (BooleanUtils.isTrue(source)) {
					return Constant.TRUE_FLG;
				} else {
					return Constant.FALSE_FLG;
				}
			}
		};
		modelMapper.addConverter(booleanSringConverter);

		// Boolean -> Stringに変換
		Converter<String, Boolean> stringBooleanConverter = new Converter<String, Boolean>() {
			@Override
			public Boolean convert(MappingContext<String, Boolean> context) {
				String source = context.getSource();
				if (StringUtils.isBlank(source)) {
					return Boolean.FALSE;
				} else if (Constant.FALSE_FLG.equals(source)) {
					return Boolean.FALSE;
				}
				return Boolean.TRUE;
			}
		};
		modelMapper.addConverter(stringBooleanConverter);
		return modelMapper;
	}

	/**
	 * remove all comma
	 * 
	 * @param str
	 * @return
	 */
	public static String removeComma(String str) {
		if (str != null) {
			return str.replaceAll(Constant.COMMA, StringUtils.EMPTY);
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * 説明 : convert string to password encode
	 * </p>
	 * 
	 * @author minh.ls
	 * @param password String password
	 * @return password encode
	 */
	public static String toMd5Encode(String password) {
		if (StringUtils.isEmpty(password)) {
			return null;
		}

		Md5PasswordEncoder md5PE = new Md5PasswordEncoder();
		return md5PE.encodePassword(password, null);
	}

	/**
	 * get first date from month
	 * 
	 * @param monthStr
	 * @param dateFormat
	 * @return
	 */
	public static LocalDate getFirstDayOfMonth(String monthStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constant.DATE_FORMAT_DDMMYYYY);
		LocalDate date = LocalDate.parse("01/" + monthStr, formatter);
		return date.withDayOfMonth(1);
	}

	/**
	 * get last date from month
	 * 
	 * @param monthStr
	 * @param dateFormat
	 * @return
	 */
	public static LocalDate getLastDayOfMonth(String monthStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constant.DATE_FORMAT_DDMMYYYY);
		LocalDate date = LocalDate.parse("01/" + monthStr, formatter);
		return date.withDayOfMonth(date.lengthOfMonth());
	}

	/**
	 * get first date from month
	 * 
	 * @param monthStr
	 * @param dateFormat
	 * @return
	 */
	public static String getFirstDayOfMonthStr(String monthStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constant.DATE_FORMAT_DDMMYYYY);
		LocalDate date = LocalDate.parse("01/" + monthStr, formatter);
		return localDateToString(date.withDayOfMonth(1), Constant.DATE_FORMAT_DDMMYYYY);
	}

	/**
	 * get last date from month
	 * 
	 * @param monthStr
	 * @param dateFormat
	 * @return
	 */
	public static String getLastDayOfMonthStr(String monthStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constant.DATE_FORMAT_DDMMYYYY);
		LocalDate date = LocalDate.parse("01/" + monthStr, formatter);
		return localDateToString(date.withDayOfMonth(date.lengthOfMonth()), Constant.DATE_FORMAT_DDMMYYYY);
	}

	/**
	 * format money
	 * 
	 * @param number
	 * @return
	 */
	public static String moneyFormat(String number) {
		try {
			double amount = Double.parseDouble(number);
			DecimalFormat formatter = new DecimalFormat("#,###.##");
			return formatter.format(amount);
		} catch (Exception e) {
			return StringUtils.EMPTY;
		}
	}

	/**
	 * String to Float
	 * 
	 * @param String
	 * @return Float
	 */
	public static Float toFloatWithComma(String str) {
		if (StringUtils.isEmpty(str)) {
			return 0F;
		}

		try {
			str = removeComma(str);
			return Float.parseFloat(str);
		} catch (Exception e) {
			return 0F;
		}
	}

	/**
	 * convert to big decimal value
	 * 
	 * @param value
	 * @return
	 */
	public static BigDecimal toBigDecimal(String value) {
		try {
			return new BigDecimal(value);
		} catch (Exception e) {
			return new BigDecimal(0);
		}
	}

	/**
	 * Base64 Token Generator
	 * 
	 * @param byteLength
	 * @return
	 */
	public static String generateRandomBase64Token(int byteLength) {
		SecureRandom secureRandom = new SecureRandom();
		byte[] token = new byte[byteLength];
		secureRandom.nextBytes(token);
		return Base64.getUrlEncoder().withoutPadding().encodeToString(token); // base64 encoding
	}

	public static String convert2MD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			return number.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    /**
     * Download File from resouces Folder
     * @param path
     * @param fileName
     * @return
     */
    public static ResponseEntity<InputStreamResource> downloadFile(String path, String fileName) {
        try {
            // Lấy đường dẫn File templ trong resource
            String urlFile = new StringBuilder(path)
                    .append(File.separator)
                    .append(fileName).toString();
            // download File
            InputStream file = new FileInputStream(urlFile);
            InputStreamResource resource = new InputStreamResource(file);
            return ResponseEntity.ok()
                    // Content-Disposition
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "inline;filename=" + URLEncoder.encode(fileName, "UTF-8"))
                    // Content-Type
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    // Contet-Length
                    // .contentLength(file.available())
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
    
    /**
     * calculate score from answer survey
     * @param focusArea
     * @param answer[0]
     * @param answer[1]
     * @param answer[2]
     * @param answer[3]
     * @return
     */
    public static HashMap<String, Long> calSurveyScore(List<SurveyDTO> jsonData, String userType) {
        HashMap<String, Long> result = new HashMap<>();
        if (jsonData == null || jsonData.size() == 0)
            return null;
        
        HashMap<String, int[]> lstValue = new HashMap<String, int[]>();
        for (SurveyDTO survey : jsonData) {
            int[] arr = lstValue.get(survey.getFa());
            if (arr == null) {
            	arr = new int[] {0,0,0,0};
                lstValue.put(survey.getFa(), arr);
            }
            
            if(survey.getAnswer().equals("1")) {
            	switch (survey.getLevel()) {
                case "A":
                    arr[0] += 1;
                    break;
                case "B":
                    arr[1] += 1;
                    break;
                case "C":
                    arr[2] += 1;
                    break;
                case "D":
                    arr[3] += 1;
                    break;
                default:
                    break;
                }
            }
        }
        
        double resultAnswer = 0.0;
        for (Map.Entry<String, int[]> entry : lstValue.entrySet()) {
            int[] answer = entry.getValue();
            switch (entry.getKey()) {
            case "1":
                if (Constant.USER_TYPE_MEMBER.equals(userType)) {
                    resultAnswer = answer[0] * 3 + answer[1] * 3 + answer[2] * 1 + answer[3] * 1.5;
                } else if (Constant.USER_TYPE_FREE.equals(userType)) {
                    resultAnswer = answer[0] * 1.5 + answer[1] * 1.5 + answer[2] * 0.6 + answer[3] * 1.5;
                }
                break;
            case "2":
                if (Constant.USER_TYPE_MEMBER.equals(userType)) {
                    resultAnswer = answer[0] * 3 + answer[1] * 3 + answer[2] * 3 + answer[3] * 3;
                } else if (Constant.USER_TYPE_FREE.equals(userType)) {
                    resultAnswer = answer[0] * 1.5 + answer[1] * 1 + answer[2] * 3 + answer[3] * 0.75;
                }
                break;
            case "3":
                resultAnswer = answer[0] * 3 + answer[1] * 1.5 + answer[2] * 3 + answer[3] * 1.5;
                break;
            case "4":
                resultAnswer = answer[0] * 3 + answer[1] * 3 + answer[2] * 1.5 + answer[3] * 1.5;
                break;
            case "5":
                resultAnswer = answer[0] * 1.5 + answer[1] * 1.5 + answer[2] * 0.6 + answer[3] * 1.5;
                break;
            case "6":
                resultAnswer = answer[0] * 1.5 + answer[1] * 1 + answer[2] * 3 + answer[3] * 0.75;
                break;
            case "7":
                resultAnswer = answer[0] * 3 + answer[1] * 1 + answer[2] * 1.5 + answer[3] * 1.5;
                break;
            case "8":
                resultAnswer = answer[0] * 1 + answer[1] * 1 + answer[2] * 1 + answer[3] * 1;
                break;
            case "9":
                resultAnswer = answer[0] * 3 + answer[1] * 1 + answer[2] * 1.5 + answer[3] * 1.5;
                break;
            case "10":
                resultAnswer = answer[0] * 3 + answer[1] * 3 + answer[2] * 3 + answer[3] * 3;
                break;
            default:
                break;
            }
            result.put(entry.getKey(), Math.round(resultAnswer/12 * 100));
        }
        return result;
    }
	
	public static void variableReplaceInData(PresentationMLPackage ppt, HashMap<String, String> variableReplaces)
			throws JAXBException {
		for (Map.Entry<PartName, Part> part : ppt.getParts().getParts().entrySet()) {
			if ((Object) part.getValue() instanceof DiagramDataPart) {
				DiagramDataPart diagramDataPart = ((DiagramDataPart) part.getValue());
				String wmlTemplateString = XmlUtils.marshaltoString(diagramDataPart.getJaxbElement(), true, false,
						diagramDataPart.getJAXBContext());

				((DiagramDataPart) part.getValue()).setJaxbElement(variableReplace(diagramDataPart.getJaxbElement(),
						diagramDataPart.getJAXBContext(), wmlTemplateString, variableReplaces));
			}

			if ((Object) part.getValue() instanceof Chart) {
				Chart chart = ((Chart) part.getValue());

				String wmlTemplateString = XmlUtils.marshaltoString(chart.getJaxbElement(), true, false,
						chart.getJAXBContext());

				((Chart) part.getValue()).setJaxbElement(variableReplace(chart.getJaxbElement(), chart.getJAXBContext(),
						wmlTemplateString, variableReplaces));
			}
		}
	}
	
	public static <T> T variableReplace(T t, JAXBContext jc, String wmlTemplateString, Map<String, String> mappings)
			throws JAXBException {
		if (wmlTemplateString == null) {
			wmlTemplateString = XmlUtils.marshaltoString(t, true, false, jc);
		}

		String wmlString = replace(wmlTemplateString, new StringBuilder(), mappings).toString();

		return (T) XmlUtils.unwrap(XmlUtils.unmarshalString(wmlString, jc));
	}

	private static StringBuilder replace(String wmlTemplateString, StringBuilder strB, java.util.Map<String, ?> mappings) {
		int offset = 0;
		while (true) {

			int startKey = wmlTemplateString.indexOf("${", offset);
			if (startKey == -1)
				return strB.append(wmlTemplateString.substring(offset));
			else {
				strB.append(wmlTemplateString.substring(offset, startKey));
				int keyEnd = wmlTemplateString.indexOf('}', startKey);
				String key = wmlTemplateString.substring(startKey + 2, keyEnd);

				String str = key;
				int idx = str.indexOf("<a:t>");
				if (idx == -1 || idx > 1) {
					str = "<a:t>" + str;
				}

				if (str.indexOf("</a:t>", str.length() - 6) == -1) {
					str += "</a:t>";
				}

				Pattern pattern = Pattern.compile("<a:t>(.*?)</a:t>");
				Matcher matcher = pattern.matcher(str);

				List<String> lstValues = new ArrayList<String>();
				String s = "";
				while (matcher.find()) {
					if (!Strings.isNullOrEmpty(matcher.group(1))) {
						s += matcher.group(1);
						lstValues.add(matcher.group(1));
					}
				}

				if (lstValues.size() > 0) {
					Object val = mappings.get(s);
					if (val == null) {
						strB.append(key);
					} else {
						key = key.replace(lstValues.get(0), val.toString());
						for (int i = 1; i < lstValues.size(); i++) {
							key = key.replace(lstValues.get(i), "");
						}
						strB.append(key);
					}
				}
				offset = keyEnd + 1;
			}
		}
	}
}
