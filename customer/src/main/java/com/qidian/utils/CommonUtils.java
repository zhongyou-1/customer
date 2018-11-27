package com.qidian.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class CommonUtils {
	public static String RESULT = "result";
	public static String ENCODING = "UTF-8";
	public static final String FILE_SEPARATOR = "/";
	public static final String SYS_AVAILABLE = "1";
	public static final String SYS_YES = "1";
	public static final String SYS_NO = "0";
	public static final int ROWS_IN_PAGE = 10;

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}

	public static boolean isYes(String value) {
		return value != null && "1".equals(value);
	}

	public static boolean isAvailable(String value) {
		return value != null && "1".equals(value);
	}

	public static boolean isEmpty(String value) {
		return value == null || value.isEmpty();
	}

	public static boolean notEmpty(String value) {
		return value != null && !value.isEmpty();
	}

	public static String getCurDateTime(String strFormat) {
		String strReturn = "";

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
			Calendar cldr = Calendar.getInstance();
			strReturn = sdf.format(cldr.getTime());
		} catch (Exception arg3) {
			;
		}

		return strReturn;
	}
}