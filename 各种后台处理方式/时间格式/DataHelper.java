package com.zdsoft.framework.common.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateHelper {
	/** 简单年日期格式 */
	public static final String DATE_YEAR = "yyyy";
	/** 简单周日期格式 */
	public static final String DATE_WEEK = "EE";

	/** 简单年月期格式 */
	public static final String DATE_SHORT_SIMPLE_MONTH_FORMAT = "yyyyMM";
	/** 简单年月期格式 */
	public static final String DATE_SHORT_LINED_MONTH_FORMAT = "yyyy-MM";
	
	/** 简单年月日日期格式 */
	public static final String DATE_SHORT_SIMPLE_FORMAT = "yyyyMMdd";
	/** 简单年月日 时格式 */
	public static final String DATE_SHORT_SIMPLE_FORMAT_WITHHOUR = "yyyyMMddHH";
	/** 简单年月日 时 分格式 */
	public static final String DATE_SHORT_SIMPLE_FORMAT_WITHMINUTE = "yyyyMMddHHmm";
	/** 年月日时分秒格式 */
	public static final String DATE_LONG_SIMPLE_FORMAT = "yyyyMMddHHmmss";
	/** 简单时分秒毫秒 */
	public static final String DATE_SHORT_TIME_FORMAT = "HHmmss.S";
	/** 简单时分钞 **/
	public static final String DATE_TIME_FORMAT = "HHmmss";
	/** 年月日日期格式 */
	public static final String DATE_SHORT_FORMAT = "yyyy-MM-dd";
	/** 中文年月日日期格式 */
	public static final String DATE_SHORT_CHN_FORMAT = "yyyy年MM月dd日";
	/** 年月日时日期格式 */
	public static final String DATE_WITHHOUR_FORMAT = "yyyy-MM-dd HH";
	/** 中文年月日时日期格式 */
	public static final String DATE_WITHHOUR_CHN_FORMAT = "yyyy年MM月dd日 HH";
	/** 年月日时分日期格式 */
	public static final String DATE_WITHMINUTE_FORMAT = "yyyy-MM-dd HH:mm";
	/** 中文年月日时分日期格式 */
	public static final String DATE_WITHMINUTE_CHN_FORMAT = "yyyy年MM月dd日 HH:mm";
	/** 年月日时分秒日期格式 */
	public static final String DATE_WITHSECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/** 中文年月日时分秒日期格式 */
	public static final String DATE_WITHSECOND_CHN_FORMAT = "yyyy年MM月dd日 HH:mm:ss";
	/** 年月日时分秒毫秒日期格式 */
	public static final String DATE_WITHMILLISECOND_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
	/** 中文年月日时分秒毫秒日期格式 */
	public static final String DATE_WITHMILLISECOND_CHN_FORMAT = "yyyy年MM月dd日 HH:mm:ss.S";

	/**
	 * 取得指定月份的最后一天
	 * 
	 * @param strDate
	 *            -指定日期月份的第一天 比如：2011-01-01
	 * @return String -返回转后后的字符串 比如：2011-01-31
	 */
	public static String getMonthEnd(String strDate) {
		Date date = null;
		if (ObjectHelper.isEmpty(strDate))
			return null;
		else
			date = DateHelper.stringToDate(strDate);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);

		return DateHelper.dateToString(calendar.getTime(), DateHelper.DATE_SHORT_FORMAT);
	}

	/**
	 * 取得指定月份的最后一天
	 * 
	 * @param strDate
	 *            -指定日期月份的第一天 比如：2011-01-01
	 * @return String -返回转后后的字符串 比如：2011-01-31
	 */
	public static Date getMonthEnd(Date strDate) {
		Date date = null;
		if (ObjectHelper.isEmpty(strDate))
			return null;
		else
			date = strDate;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);

		return calendar.getTime();
	}

	/**
	 * 将给定应用服务器日期按照给定格式化类型转换成字符串
	 * 
	 * @param date
	 *            -java日期对象
	 * @param format
	 *            -日期格式化类型
	 * @return String -返回转换后的字符串
	 */
	public static String dateToString(Date date, String format) {
		if (ObjectHelper.isEmpty(date))
			return null;

		if (ObjectHelper.isEmpty(format))
			format = DateHelper.DATE_WITHSECOND_FORMAT;

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 将给定应用服务器日期按照默认格式化(yyyy-MM-dd HH:mm:ss)类型转换成字符串
	 * 
	 * @param date
	 *            -java日期对象
	 * @return String -返回转换后的字符串
	 */
	public static String dateToString(Date date) {
		return dateToString(date, DateHelper.DATE_WITHSECOND_FORMAT);
	}

	/**
	 * 将给定应用服务器日期按照默认格式化(HHmmss.S)类型转换成字符串
	 * 
	 * @param date
	 *            -java日期对象
	 * @return String -返回转换后的字符串
	 */
	public static String shortTimeToString(Date date) {
		return dateToString(date, DateHelper.DATE_SHORT_TIME_FORMAT);
	}

	/**
	 * 将给定应用服务器日期按照默认格式化(yyyyMMdd)类型转换成字符串
	 * 
	 * @param date
	 *            -java日期对象
	 * @return String -返回转换后的字符串
	 */
	public static String shortDateToString(Date date) {
		return dateToString(date, DateHelper.DATE_SHORT_SIMPLE_FORMAT);
	}

	/**
	 * 将应用服务器当前日期按照给定格式化类型转换成字符串
	 * 
	 * @param format
	 *            -日期格式化类型
	 * @return String -返回转换后的字符串
	 */
	public static String currentTimeToString(String format) {
		return dateToString(DateHelper.getCurrentDateTime(), format);
	}

	/**
	 * 将字符串转换成日期 注意：一定要选用匹配的格式，否则不能解析，将返回null
	 * 
	 * @param strDate
	 *            - 日期
	 * @param format
	 *            - 格式
	 * @return Date -转换后的日期
	 */
	public static Date stringToDate(String strDate, String format) {
		if (ObjectHelper.isEmpty(strDate))
			return null;
		if (ObjectHelper.isEmpty(format))
			format = DateHelper.DATE_SHORT_FORMAT;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 将字符串转换成日期，默认格式：yyyy-MM-dd
	 * 
	 * @param strDate
	 *            - 日期
	 * @return Date -转换后的日期
	 */
	public static Date stringToDate(String strDate) {
		if (ObjectHelper.isEmpty(strDate))
			return null;
		return stringToDate(strDate, DateHelper.DATE_SHORT_FORMAT);
	}

	/**
	 * 获取指定日期偏移delayDays后的日期
	 * 
	 * @param startDate
	 *            -开始日期
	 * @param delayDays
	 *            -延迟的天数
	 * @return Date -转换后的日期
	 */
	public static Date getDateAfterDays(Date startDate, int delayDays) {
		if (ObjectHelper.isEmpty(startDate))
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.add(Calendar.DAY_OF_MONTH, delayDays);
		return c.getTime();
	}

	/**
	 * 获取当前日期（没转化格式）
	 * 
	 * @return Date -转换后的日期
	 */
	public static Date getCurrentDate() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	/**
	 * 获取当前日期
	 * 
	 * @return Date -转换后的日期
	 */
	public static Date getCurrentDateTime() {
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}

	/**
	 * 获取当前时间，精确到秒（没转化格式）
	 * 
	 * @return Date -转换后的日期
	 */
	public static Date getCurrentTime() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 获取上一年时间，精确到秒（没转化格式）
	 * 
	 * @return Date -转换后的日期
	 */
	public static Date getLastYearTime() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 1);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 设置开始日期时分秒格式 00:00:00，该方法已经过时，请使用getMinLimitDate
	 * 
	 * @param date
	 *            -日期
	 * @return Date -转换后的日期
	 */
	@Deprecated
	public static Date setSatrtDate(Date date) {
		if (ObjectHelper.isEmpty(date))
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	/**
	 * 设置结束日期时分秒格式 23:59:59,该方法已经过时，请使用getMaxLimitDate
	 * 
	 * @param date
	 *            -日期
	 * @return Date -转换后的日期
	 */
	@Deprecated
	public static Date setEndDate(Date date) {
		if (ObjectHelper.isEmpty(date))
			return null;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}

	/**
	 * 获取当前年份-长
	 * 
	 * @return String -当前年份
	 */
	public static String getCurrentLongYear() {
		Calendar c = Calendar.getInstance();
		String year = Integer.toString(c.get(Calendar.YEAR));
		return year;
	}

	/**
	 * 获取当前年份-短
	 * 
	 * @return String -当前年份
	 */
	public static String getCurrentYear() {
		Calendar c = Calendar.getInstance();
		String year = Integer.toString(c.get(Calendar.YEAR));
		year = year.substring(2);
		return year;
	}

	/**
	 * 获取当前月份,1~9月为一位字符长度
	 * 
	 * @return String -当天月份
	 */
	public static String getCurrentMonth() {
		Calendar c = Calendar.getInstance();
		String month = Integer.toString(c.get(Calendar.MONTH) + 1);
		return month;
	}

	/**
	 * 获取当前月份，默认格式：00，1~9月表示为 01~09
	 * 
	 * @return
	 */
	public static String getCurrentMonth2() {
		String month = getCurrentMonth();
		return month.length() < 2 ? "0" + month : month;
	}

	/**
	 * 获取当天日期
	 * 
	 * @return String -日期，默认格式：00
	 */
	public static String getCurrentDay() {
		Calendar c = Calendar.getInstance();
		String date = Integer.toString(c.get(Calendar.DATE));
		if (date.length() < 2)
			date = "0" + date;
		return date;
	}

	/**
	 * 获取当天小时
	 * 
	 * @return String -小时，默认格式：00
	 */
	public static String getCurrentHour() {
		Calendar c = Calendar.getInstance();
		String date = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
		if (date.length() < 2) {
			date = "0" + date;
		}
		return date;
	}

	/**
	 * 获取当天分钟
	 * 
	 * @return String -分，默认格式：00
	 */
	public static String getCurrentMinute() {
		Calendar c = Calendar.getInstance();
		String date = Integer.toString(c.get(Calendar.MINUTE));
		if (date.length() < 2) {
			date = "0" + date;
		}
		return date;
	}

	/**
	 * 获取当天秒
	 * 
	 * @return String -秒, 默认格式：00
	 */
	public static String getCurrentSecond() {
		Calendar c = Calendar.getInstance();
		String date = Integer.toString(c.get(Calendar.SEPTEMBER));
		if (date.length() < 2) {
			date = "0" + date;
		}
		return date;
	}

	/**
	 * 获取指定时间的前一天的指定类型日期。如：20120101
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String getBeforeDay(String date, String format) {
		return getBeforeDay(date, format, -1);
	}

	/**
	 * 获取指定时间的前n天的指定类型日期。如：20120101
	 * 
	 * @param date
	 * @param format
	 * @param delayDays
	 * @return
	 */
	public static String getBeforeDay(String date, String format, int delayDays) {
		if (ObjectHelper.isEmpty(date))
			return null;
		if (ObjectHelper.isEmpty(format))
			format = DateHelper.DATE_SHORT_FORMAT;
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTime(stringToDate(date, format));
		c.add(Calendar.DATE, delayDays);
		SimpleDateFormat myFormatter = new SimpleDateFormat(format);
		return myFormatter.format(c.getTime());
	}

	/**
	 * 获取指定时间上月末日期。如：201201
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getBeforeMonth(String date, String format) {
		return getBeforeMonth(date, format, -1);
	}

	/**
	 * 获取指定时间前n月末日期。如：201201
	 * 
	 * @param date
	 * @param format
	 * @param delayMonths
	 * @return
	 */
	public static String getBeforeMonth(String date, String format, int delayMonths) {
		if (ObjectHelper.isEmpty(date) || date.length() != 8)
			return null;
		if (ObjectHelper.isEmpty(format))
			format = DateHelper.DATE_SHORT_FORMAT;
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTime(stringToDate(date, format));
		c.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - (1 - delayMonths));
		c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
		SimpleDateFormat myFormatter = new SimpleDateFormat(DateHelper.DATE_SHORT_SIMPLE_MONTH_FORMAT);
		return myFormatter.format(c.getTime());

	}

	/**
	 * 获得指定日期的季度最后月份
	 * 
	 * @param dates
	 * @return
	 */

	public static String getQuarterlyLastMonth(String date) {
		int curentQuarterly = 0;
		if (ObjectHelper.isEmpty(date)) {
			return null;
		}
		int quarterly = Integer.parseInt(date.substring(4, 6));
		if (quarterly % 3 == 0) {
			curentQuarterly = (Integer.parseInt(date.substring(0, 4)) * 100) + (quarterly / 3) * 3;
		} else {
			curentQuarterly = (Integer.parseInt(date.substring(0, 4)) * 100) + ((quarterly / 3) + 1) * 3;
		}

		return String.valueOf(curentQuarterly);
	}

	/**
	 * 获得指定时间上季度末日期
	 * 
	 * @param dates
	 * @return
	 */
	public static String getBeforeQuarterly(String date, String format) {
		return getBeforeQuarterly(date, format, -1);

	}

	/**
	 * 获得指定时间前n季度末日期
	 * 
	 * @param date
	 * @param format
	 * @param delayQuarterly
	 * @return
	 */
	public static String getBeforeQuarterly(String date, String format, int delayQuarterly) {
		date = getQuarterlyLastMonth(date) + "01";
		if (ObjectHelper.isEmpty(date) || date.length() != 8)
			return null;
		if (ObjectHelper.isEmpty(format))
			format = DateHelper.DATE_SHORT_FORMAT;
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTime(stringToDate(date, format));
		c.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - (1 - delayQuarterly * 3));
		c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
		SimpleDateFormat myFormatter = new SimpleDateFormat(format);
		String quarterly = myFormatter.format(c.getTime());
		if (!ObjectHelper.isEmpty(quarterly)) {
			return quarterly.substring(0, 4) + "0" + Integer.parseInt(quarterly.substring(4, 6)) / 3;
		}
		return null;

	}

	/**
	 * 获得指定年份的上年末日期
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getBeforeYear(String date, String format) {
		return getBeforeYear(date, format, -1);
	}

	/**
	 * 获得指定年份的前n年末日期
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getBeforeYear(String date, String format, int delayYear) {
		if (ObjectHelper.isEmpty(date) || date.length() != 8)
			return null;
		Calendar c = Calendar.getInstance();
		if (ObjectHelper.isEmpty(format))
			format = DateHelper.DATE_SHORT_FORMAT;
		c.clear();
		c.setTime(stringToDate(date, format));
		int year = c.get(Calendar.YEAR) + delayYear;
		return String.valueOf(year);
	}

	/**
	 * 获取指定时间的前一天的默认类型日期
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String getBeforeDay(String date) {
		return getBeforeDay(date, DateHelper.DATE_WITHMILLISECOND_FORMAT);
	}

	/**
	 * 获取指定时间的前一天日期
	 * @param date
	 * @return
	 */
	public static Date getBeforeDay(Date date) {
		if (ObjectHelper.isEmpty(date))
			return null;
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTime(date);
		c.add(Calendar.DATE, -1);
		return c.getTime();
	}

	public static Date getAfterDay(Date date) {
		if (ObjectHelper.isEmpty(date))
			return null;
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		return c.getTime();
	}

	/**
	 * 获取指定时间的后一天的指定类型日期
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String getAfterDay(String date, String format) {
		if (ObjectHelper.isEmpty(date) || date.length() != 8)
			return null;
		if (ObjectHelper.isEmpty(format))
			format = DateHelper.DATE_SHORT_FORMAT;
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTime(stringToDate(date));
		c.add(Calendar.DATE, 1);
		SimpleDateFormat myFormatter = new SimpleDateFormat(format);
		return myFormatter.format(c.getTime());
	}

	/**
	 * 获取指定时间的后一天的默认类型日期
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String getAfterDay(String date) {
		return getAfterDay(date, DateHelper.DATE_WITHMILLISECOND_FORMAT);
	}

	/**
	 * 获取指定时间前一天的最后时间的固定类型日期 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param String
	 * @return String
	 */
	public static String getBeforeDayLastTime(String date) {
		if (ObjectHelper.isEmpty(date))
			return null;
		return dateToString(setEndDate(stringToDate(getBeforeDay(date))), DateHelper.DATE_WITHSECOND_FORMAT);
	}

	/**
	 * 获取指定时间前一天的最后时间的固定类型日期 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param String
	 * @return Date
	 */
	public static Date getBeforeDayLastTime(Date date) {
		if (ObjectHelper.isEmpty(date))
			return null;
		return setEndDate(stringToDate(getBeforeDay(dateToString(date, DateHelper.DATE_WITHSECOND_FORMAT))));
	}

	/**
	 * 获取指定时间后一天的开始时间的固定类型日期 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param String
	 * @return String
	 */
	public static String getAfterDayFirstTime(String date) {
		if (ObjectHelper.isEmpty(date))
			return null;
		return dateToString(setSatrtDate(stringToDate(getAfterDay(date))), DateHelper.DATE_WITHSECOND_FORMAT);
	}

	/**
	 * 获取指定时间后一天的开始时间的固定类型日期 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param String
	 * @return Date
	 */
	public static Date getAfterDayFirstTime(Date date) {
		if (ObjectHelper.isEmpty(date))
			return null;
		return setSatrtDate(stringToDate(getAfterDay(dateToString(date, DateHelper.DATE_WITHSECOND_FORMAT))));
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 * 
	 * @param sdate
	 * @return String
	 */
	public static String  getWeek(String date) {
		if (ObjectHelper.isEmpty(date))
			return null;
		Date sdate = stringToDate(date, DateHelper.DATE_SHORT_FORMAT);
		Calendar c = Calendar.getInstance();
		c.setTime(sdate);

		return new SimpleDateFormat("EEEE").format(c.getTime());
	}	
	
	/**
	 * 根据一个日期，返回是星期几的int类型(星期一：1；星期二：2， 星期三：3；星期四：4；星期五：5;星期六：6;星期天：0)
	 * 
	 * @param date
	 * @return
	 */
	public static int getIntegerWeek(String date) {
		if (ObjectHelper.isEmpty(date))
			return -1;
		int week = 0;
		Date sdate = stringToDate(date, DateHelper.DATE_SHORT_FORMAT);
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdate);
		week = cal.get(Calendar.DAY_OF_WEEK);
		week = (week - 1 + 7) % 7;
		return week;
	}

	/**
	 * 根据一个日期，返回是星期几的int类型(星期一：1；星期二：2， 星期三：3；星期四：4；星期五：5;星期六：6;星期天：0)
	 * 
	 * @param date
	 * @return
	 */
	public static int getIntegerWeek(Date date) {
		if (ObjectHelper.isEmpty(date))
			return -1;
		
		int week = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		week = cal.get(Calendar.DAY_OF_WEEK);
		week = (week - 1 + 7) % 7;
		return week;
	}

	/**
	 * 获取指定时间段包含的星期
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<String> getWeeksByTimePeriod(String startDate, String endDate) {
		return getWeeksByTimePeriod(startDate, endDate, null);
	}

	/**
	 * 获取指定时间段包含的星期
	 * 
	 * @param startDate
	 * @param endDate
	 * @param format
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getWeeksByTimePeriod(String startDate, String endDate, String format) {
		if (ObjectHelper.isEmpty(startDate) || ObjectHelper.isEmpty(endDate))
			return new ArrayList<String>();
		List<String> weeks = new ArrayList<String>();
		Date start = DateHelper.stringToDate(startDate, ObjectHelper.isEmpty(format) ? DateHelper.DATE_SHORT_FORMAT
				: format);
		Date end = DateHelper.stringToDate(endDate, ObjectHelper.isEmpty(format) ? DateHelper.DATE_SHORT_FORMAT
				: format);
		long startTime = start.getTime();
		long endTime = end.getTime();
		for (long i = startTime; i <= endTime; i += 86400000) {
			Date curDate = new Date(i);
			// GregorianCalendar gc=new GregorianCalendar();
			Calendar cal = Calendar.getInstance();
			cal.setTime(curDate);
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if (weeks.size() >= 7) {
				break;
			}
			weeks.add(String.valueOf(dayOfWeek));
		}
		/**
		 * 指定时间段星期排序
		 * 
		 * @function
		 * @author lenovo
		 * @version 1.0.0
		 * @since 1.0.0
		 * 
		 */
		@SuppressWarnings("rawtypes")
		class WeekComparator implements Comparator {
			public int compare(Object start, Object end) {
				String startStr = start.toString();
				String endStr = end.toString();
				if (startStr.equals("0"))
					startStr = "7";
				if (endStr.equals("0"))
					endStr = "7";
				int flag = startStr.compareTo(endStr);
				return flag;
			}

		}
		WeekComparator comparator = new WeekComparator();
		Collections.sort(weeks, comparator);
		return weeks;

	}

	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static long getDays(String date1, String date2) throws ParseException {
		if (ObjectHelper.isEmpty(date1))
			return 0;
		if (ObjectHelper.isEmpty(date2))
			return 0;
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat(DateHelper.DATE_SHORT_FORMAT);
		Date date = null;
		Date mydate = null;

		date = myFormatter.parse(date1);
		mydate = myFormatter.parse(date2);
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return (new BigDecimal(day).abs()).longValue();
	}

	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static long getDaysBetween(Date date1, Date date2) {
		if (ObjectHelper.isEmpty(date1))
			return 0;
		
		if (ObjectHelper.isEmpty(date2))
			return 0;
		
		Date date = DateHelper.getMinLimitDate(date1);
		Date mydate = DateHelper.getMinLimitDate(date2);
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		
		return (new BigDecimal(day).abs()).longValue();
	}
	
	/**
	 * 给定一个年份判断该年份是否为闰年
	 * 
	 * @param year
	 * @return false:不是闰年 true:闰年
	 */
	public static boolean isLeapYear(int year) {
		GregorianCalendar calendar = new GregorianCalendar();
		return calendar.isLeapYear(year);
	}

	/**
	 * @param int minute 偏移分钟数
	 * @return String 返回经偏移后的时间字符串
	 */

	public static String getOffsetByMinute(int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + minute);
		return dateToString(calendar.getTime(), DateHelper.DATE_WITHSECOND_FORMAT);
	}

	/**
	 * 按照半小时为计数单位
	 * 
	 * 返回当前计数
	 * 
	 * @return int
	 */
	public static int getHalfHourCnt() {
		Calendar c = Calendar.getInstance();
		// 小时
		int hour = c.get(Calendar.HOUR_OF_DAY);
		// 分钟
		int minute = c.get(Calendar.MINUTE);
		// 如果当前时间大于30分钟，则计数加1
		return (minute >= 30) ? (hour * 2 + 1) : (hour * 2);
	}

	/**
	 * @param int halfHour 按照半小时为计数单位
	 * 
	 *        返回当前计数时段的最大时间(格式yyyy-mm-dd hh:mi:sss)
	 * 
	 * @return String
	 */
	public static String getSelfMaxTime(int halfHour) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, halfHour / 2);
		c.set(Calendar.MINUTE, halfHour % 2 == 0 ? 29 : 59);
		c.set(Calendar.SECOND, 59);
		return dateToString(c.getTime(), DateHelper.DATE_WITHSECOND_FORMAT);
	}

	/**
	 * 返回当前小时
	 * 
	 * @return int
	 */
	public static int getHour() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 返回当期日期
	 * 
	 * @return int
	 */
	public static int getDay() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回周内日号
	 * 
	 * @return int
	 */
	public static int getDayOfWeek() {
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_WEEK);
		return (day - 1) > 0 ? (day - 1) : 7;
	}

	/**
	 * 得到当前日的最大时间
	 * 
	 * @return Date
	 */
	public static Date getMaxLimitDate(Date curDate) {
		if (curDate == null)
			return null;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(curDate.getTime());
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}

	/**
	 * 得到当前日的最小时间
	 * 
	 * @return Date
	 */
	public static Date getMinLimitDate(Date curDate) {
		if (curDate == null)
			return null;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(curDate.getTime());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	/**
	 * 当前系统时间减去传入的分钟数
	 * 
	 * @param minute
	 *            分钟数
	 * @return
	 */
	public static String dateToString(Integer minute) {
		int time = 0;
		if (!ObjectHelper.isEmpty(minute)) {
			time = 1000 * 60 * minute;
		}
		return dateToString(new Date(System.currentTimeMillis() - time));
	}

	// /**
	// * 获取CRA服务器时间
	// *
	// * @return 统一资源服务器时间
	// */
	// public static Date getCRAServerTime() {
	// return getCurrentDateTime();
	// }

	/**
	 * 获取结束日期与开始日期之间的月数
	 * 
	 * @param endDate
	 *            结束日期
	 * @param startDate
	 *            开始日期
	 * @return 月数
	 */
	public static Integer getMonthsBetween(Date endDate, Date startDate) {
		Calendar cEnd = Calendar.getInstance();
		Calendar cStart = Calendar.getInstance();
		cEnd.setTime(endDate);
		cStart.setTime(startDate);

		int endYear = cEnd.get(Calendar.YEAR) + 1;
		int startYear = cStart.get(Calendar.YEAR) + 1;

		int endMonth = cEnd.get(Calendar.MONTH) + 1;
		int startMonth = cStart.get(Calendar.MONTH) + 1;

		int shiftYear = endYear - startYear;
		int shiftMonth = endMonth - startMonth;

		if (shiftYear == 0) {
			if (shiftMonth > 0) {
				return shiftMonth;
			} else {
				return 0;
			}
		} else if (shiftYear > 0) {
			if (shiftMonth < 0) {
				shiftYear = shiftYear - 1;
				shiftMonth = 12 + shiftMonth;
			}
			return shiftYear * 12 + shiftMonth;
		} else {
			return 0;
		}
	}

	/**
	 * 返回指定日期，延迟n年的时间
	 * 
	 * @param date
	 * @param delayYear
	 * @return
	 */
	public static Date getAfterYear(Date date, int delayYear) {
		Calendar c = Calendar.getInstance();
		if (!ObjectHelper.isEmpty(date)) {
			c.clear();
			c.setTime(date);
		}
		c.add(Calendar.YEAR, delayYear);
		return c.getTime();

	}

	/**
	 * 获取指定时间年份
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		if (!ObjectHelper.isEmpty(date)) {
			c.clear();
			c.setTime(date);
		}
		return c.get(Calendar.YEAR);
	}

	/**
	 * 获取指定时间月份
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {

		Calendar c = Calendar.getInstance();
		if (!ObjectHelper.isEmpty(date)) {
			c.clear();
			c.setTime(date);
		}
		return c.get(Calendar.MONTH) + 1;

	}

	/**
	 * 根据指定时间获取天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		if (!ObjectHelper.isEmpty(date)) {
			c.clear();
			c.setTime(date);
		}
		return c.get(Calendar.DATE);
	}

	/**
	 * 根据指定时间获取小时数
	 * 
	 * @param date
	 * @return
	 */
	public static int getHour(Date date) {

		Calendar c = Calendar.getInstance();
		if (!ObjectHelper.isEmpty(date)) {
			c.clear();
			c.setTime(date);
		}
		return c.get(Calendar.HOUR_OF_DAY);

	}

	/**
	 * 根据指定时间获取分钟数
	 * 
	 * @param date
	 * @return
	 */
	public static int getMinute(Date date) {

		Calendar c = Calendar.getInstance();
		if (!ObjectHelper.isEmpty(date)) {
			c.clear();
			c.setTime(date);
		}
		return c.get(Calendar.MINUTE);

	}

	/**
	 * 根据指定时间返回每year年的month月day日hour小时minute分钟的日期
	 * 
	 * @param date
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @return
	 */
	public static Date getAfterTime(Date date, int year, int month, int day, int hour, int minute) {
		Calendar c = Calendar.getInstance();
		if (ObjectHelper.isEmpty(date))
			date = new Date();
		c.clear();
		c.set(Calendar.YEAR, getYear(date) + year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DATE, day);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		return c.getTime();

	}

	/**
	 * 根据指定时间返回每month月的day日hour小时minute分钟的日期
	 * 
	 * @param date
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @return
	 */
	public static Date getAfterTime(Date date, int month, int day, int hour, int minute) {
		Calendar c = Calendar.getInstance();
		if (ObjectHelper.isEmpty(date))
			date = new Date();
		c.clear();
		c.set(Calendar.YEAR, getYear(date));
		c.set(Calendar.MONTH, getMonth(date) + month - 1);
		c.set(Calendar.DATE, day);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		return c.getTime();

	}

	/**
	 * 根据指定时间返回每day日的hour小时minute分钟的日期
	 * 
	 * @param date
	 * @param day
	 * @param hour
	 * @param minute
	 * @return
	 */
	public static Date getAfterTime(Date date, int day, int hour, int minute) {

		Calendar c = Calendar.getInstance();
		if (ObjectHelper.isEmpty(date))
			date = new Date();
		c.clear();
		c.set(Calendar.YEAR, getYear(date));
		c.set(Calendar.MONTH, getMonth(date) - 1);
		c.set(Calendar.DATE, getDay(date) + day);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		return c.getTime();

	}

	/**
	 * 根据指定时间返回每hour小时的minute分钟的日期
	 * 
	 * @param date
	 * @param hour
	 * @param minute
	 * @return
	 */
	public static Date getAfterTime(Date date, int hour, int minute) {

		Calendar c = Calendar.getInstance();
		if (ObjectHelper.isEmpty(date))
			date = new Date();
		c.clear();
		c.set(Calendar.YEAR, getYear(date));
		c.set(Calendar.MONTH, getMonth(date) - 1);
		c.set(Calendar.DATE, getDay(date));
		c.set(Calendar.HOUR_OF_DAY, getHour(date) + hour);
		c.set(Calendar.MINUTE, minute);
		return c.getTime();

	}

	/**
	 * 根据指定时间返回每minute分钟的日期
	 * 
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date getAfterTime(Date date, int minute) {

		Calendar c = Calendar.getInstance();
		if (ObjectHelper.isEmpty(date))
			date = new Date();
		c.clear();
		c.set(Calendar.YEAR, getYear(date));
		c.set(Calendar.MONTH, getMonth(date) - 1);
		c.set(Calendar.DATE, getDay(date));
		c.set(Calendar.HOUR_OF_DAY, getHour(date));
		c.set(Calendar.MINUTE, getMinute(date) + minute);
		return c.getTime();
	}

	/**
	 * 取得开始时间与结束时间相差的小时数
	 * 
	 * @param startdDate
	 * @param endDate
	 * @return
	 */
	public static double getHour(Date startdDate, Date endDate) {
		// 取得开始时间与结束时间相差的毫秒数
		long time = endDate.getTime() - startdDate.getTime();
		// 取得开始时间与结束时间相差的小时数（保留二位小数）
		double hour = NumberHelper.formaterNumber((double) (time / (1000 * 60 * 60d)), 2);
		return hour;
	}

	/**
	 * 取得一个时间的小时数
	 * 
	 * @param date
	 * @return
	 */
	public static double getHour_(Date date) {
		// 取得开始时间所占的百分比
		Calendar aCalendar = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		aCalendar.setTime(DateHelper.stringToDate(df.format(date), "yyyy-MM-dd"));
		return getHour(aCalendar.getTime(), date);
	}

	/**
	 * 将Long型表达的时间转换成指定格式的字符串时间
	 * 
	 * @param date
	 *            yyyyMMddHHmmss(yyyyMMddHHmm,yyyyMMddHH,yyyyMMdd)格式的时间
	 * @param format
	 *            返回的时间格式
	 * @return
	 */
	public static String longToDate(Long date, String format) {
		if (date == null) {
			return "";
		}

		String sd = String.valueOf(date);

		Date d = null;
		if (sd.length() == 8) {
			d = DateHelper.stringToDate(sd, DATE_SHORT_SIMPLE_FORMAT);
		} else if (sd.length() == 10) {
			d = DateHelper.stringToDate(sd, DATE_SHORT_SIMPLE_FORMAT_WITHHOUR);
		} else if (sd.length() == 12) {
			d = DateHelper.stringToDate(sd, DATE_SHORT_SIMPLE_FORMAT_WITHMINUTE);
		} else if (sd.length() == 14) {
			d = DateHelper.stringToDate(sd, DATE_LONG_SIMPLE_FORMAT);
		}

		return DateHelper.dateToString(d, format);
	}

	/**
	 * 将Long型表达的时间转换成'yyyy-mm-dd'格式的字符串时间
	 * 
	 * @param date
	 *            yyyyMMddHHmmss(yyyyMMddHHmm,yyyyMMddHH,yyyyMMdd)格式的时间
	 * @param format
	 *            返回的时间格式
	 * @return
	 */
	public static String longToDate(Long date) {
		if (date == null) {
			return "";
		}

		String sd = String.valueOf(date);
		Date d = null;
		if (sd.length() == 8) {
			d = DateHelper.stringToDate(sd, DATE_SHORT_SIMPLE_FORMAT);
		} else if (sd.length() == 10) {
			d = DateHelper.stringToDate(sd, DATE_SHORT_SIMPLE_FORMAT_WITHHOUR);
		} else if (sd.length() == 12) {
			d = DateHelper.stringToDate(sd, DATE_SHORT_SIMPLE_FORMAT_WITHMINUTE);
		} else if (sd.length() == 14) {
			d = DateHelper.stringToDate(sd, DATE_LONG_SIMPLE_FORMAT);
		}

		return DateHelper.dateToString(d, DATE_SHORT_FORMAT);
	}

	/**
	 * 将指定格式的日期字窜转换成指定格式Long
	 * 
	 * @param dateTime
	 *            要转换的日期、时间
	 * @param fromFormat
	 *            传入字窜的格式
	 * @param toFormat
	 *            目标格式
	 * @return
	 */
	public static Long stringDateToLong(String dateTime, String fromFormat, String toFormat) {
		return Long.parseLong(DateHelper.dateToString(DateHelper.stringToDate(dateTime, fromFormat), toFormat));
	}
	
	/**
	 * 将指定格式的日期字窜转换成指定格式Long
	 * 
	 * @param dateTime
	 *            要转换的日期、时间
	 * @param fromFormat
	 *            传入字窜的格式
	 * @param toFormat
	 *            目标格式
	 * @return
	 */
	public static Long dateToLong(Date date, String fromFormat) {
		return DateHelper.stringDateToLong(DateHelper.dateToString(date,fromFormat),fromFormat,fromFormat);
	}
	
	/**
	 * 判断两个日期是否连续月份
	 * @param start 开始年月
	 * @param end 结束年月
	 * @param formater 格式
	 * @return
	 */
	public static boolean checkContinuous(String start,String end){
		//如果没有开始月份设成为连接
		if(ObjectHelper.isEmpty(start)){
			return true;
		}
		Date startDate = DateHelper.stringToDate(start, DateHelper.DATE_SHORT_LINED_MONTH_FORMAT);
		Date endDate = DateHelper.stringToDate(end, DateHelper.DATE_SHORT_LINED_MONTH_FORMAT);
		Integer month = DateHelper.getMonthsBetween(endDate, startDate);
		if(month==1){
			return true;
		}
		return false;
	}
	
	/**
	 * 指定月份前几月月份
	 * @param start 指定月份
	 * @param month 月份
	 * @param formater 格式
	 * @return
	 */
	public static String getAfterMonth(String start,Integer month,String formater){
		Date startDate = DateHelper.stringToDate(start, formater);
		Calendar cStart = Calendar.getInstance();
		cStart.setTime(startDate);
		cStart.add(Calendar.MONTH, month);
		return DateHelper.dateToString(cStart.getTime(),formater);
	}
	/**
	 * 指定月份前几月月份
	 * @param start 指定月份
	 * @param month 月份
	 * @return
	 */
	public static String getAfterMonth(String start,Integer month){
		return DateHelper.getAfterMonth(start, month,DateHelper.DATE_SHORT_LINED_MONTH_FORMAT);
	}
	
	/**
	 * 判断 when 是否不早于start 并且不晚于 end指定的时间
	 * 
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param when 比较时间
	 * @return true start <= when <= end
	 */
	public static Boolean isBetween(Date start, Date end, Date when) {
		return when.after(start) && when.before(end);
	}
	
	/**
	 * 将分钟数转换成 ：XX月XX天XX分钟 的表达形式
	 * 
	 * @param minutes 分钟数
	 * @return
	 */
	public static String getDurationAsString(Long minutes) {
		long days = minutes / 1440;
		minutes = minutes % 1440;
		long hours = minutes / 60;
		minutes = minutes % 60;

		String duration = "";
		if (minutes < 0) {
			return null;
		} else {
			duration = minutes + "分钟";
		}
		
		if (hours > 0) {
			duration = hours + "小时" + duration;
		}
		
		if (days > 0) {
			duration = days + "天" + duration;
		}
		
		return  duration;
	}
	
	public static void main(String[] args) {
		System.out.println(DateHelper.isBetween(DateHelper.getBeforeDay(getCurrentDate()), DateHelper.getAfterDay(getCurrentDate()), getCurrentDate()));
	}
}
