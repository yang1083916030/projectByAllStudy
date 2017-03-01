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
	/** �������ڸ�ʽ */
	public static final String DATE_YEAR = "yyyy";
	/** �������ڸ�ʽ */
	public static final String DATE_WEEK = "EE";

	/** �������ڸ�ʽ */
	public static final String DATE_SHORT_SIMPLE_MONTH_FORMAT = "yyyyMM";
	/** �������ڸ�ʽ */
	public static final String DATE_SHORT_LINED_MONTH_FORMAT = "yyyy-MM";
	
	/** �����������ڸ�ʽ */
	public static final String DATE_SHORT_SIMPLE_FORMAT = "yyyyMMdd";
	/** �������� ʱ��ʽ */
	public static final String DATE_SHORT_SIMPLE_FORMAT_WITHHOUR = "yyyyMMddHH";
	/** �������� ʱ �ָ�ʽ */
	public static final String DATE_SHORT_SIMPLE_FORMAT_WITHMINUTE = "yyyyMMddHHmm";
	/** ������ʱ�����ʽ */
	public static final String DATE_LONG_SIMPLE_FORMAT = "yyyyMMddHHmmss";
	/** ��ʱ������� */
	public static final String DATE_SHORT_TIME_FORMAT = "HHmmss.S";
	/** ��ʱ�ֳ� **/
	public static final String DATE_TIME_FORMAT = "HHmmss";
	/** ���������ڸ�ʽ */
	public static final String DATE_SHORT_FORMAT = "yyyy-MM-dd";
	/** �������������ڸ�ʽ */
	public static final String DATE_SHORT_CHN_FORMAT = "yyyy��MM��dd��";
	/** ������ʱ���ڸ�ʽ */
	public static final String DATE_WITHHOUR_FORMAT = "yyyy-MM-dd HH";
	/** ����������ʱ���ڸ�ʽ */
	public static final String DATE_WITHHOUR_CHN_FORMAT = "yyyy��MM��dd�� HH";
	/** ������ʱ�����ڸ�ʽ */
	public static final String DATE_WITHMINUTE_FORMAT = "yyyy-MM-dd HH:mm";
	/** ����������ʱ�����ڸ�ʽ */
	public static final String DATE_WITHMINUTE_CHN_FORMAT = "yyyy��MM��dd�� HH:mm";
	/** ������ʱ�������ڸ�ʽ */
	public static final String DATE_WITHSECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/** ����������ʱ�������ڸ�ʽ */
	public static final String DATE_WITHSECOND_CHN_FORMAT = "yyyy��MM��dd�� HH:mm:ss";
	/** ������ʱ����������ڸ�ʽ */
	public static final String DATE_WITHMILLISECOND_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
	/** ����������ʱ����������ڸ�ʽ */
	public static final String DATE_WITHMILLISECOND_CHN_FORMAT = "yyyy��MM��dd�� HH:mm:ss.S";

	/**
	 * ȡ��ָ���·ݵ����һ��
	 * 
	 * @param strDate
	 *            -ָ�������·ݵĵ�һ�� ���磺2011-01-01
	 * @return String -����ת�����ַ��� ���磺2011-01-31
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
	 * ȡ��ָ���·ݵ����һ��
	 * 
	 * @param strDate
	 *            -ָ�������·ݵĵ�һ�� ���磺2011-01-01
	 * @return String -����ת�����ַ��� ���磺2011-01-31
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
	 * ������Ӧ�÷��������ڰ��ո�����ʽ������ת�����ַ���
	 * 
	 * @param date
	 *            -java���ڶ���
	 * @param format
	 *            -���ڸ�ʽ������
	 * @return String -����ת������ַ���
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
	 * ������Ӧ�÷��������ڰ���Ĭ�ϸ�ʽ��(yyyy-MM-dd HH:mm:ss)����ת�����ַ���
	 * 
	 * @param date
	 *            -java���ڶ���
	 * @return String -����ת������ַ���
	 */
	public static String dateToString(Date date) {
		return dateToString(date, DateHelper.DATE_WITHSECOND_FORMAT);
	}

	/**
	 * ������Ӧ�÷��������ڰ���Ĭ�ϸ�ʽ��(HHmmss.S)����ת�����ַ���
	 * 
	 * @param date
	 *            -java���ڶ���
	 * @return String -����ת������ַ���
	 */
	public static String shortTimeToString(Date date) {
		return dateToString(date, DateHelper.DATE_SHORT_TIME_FORMAT);
	}

	/**
	 * ������Ӧ�÷��������ڰ���Ĭ�ϸ�ʽ��(yyyyMMdd)����ת�����ַ���
	 * 
	 * @param date
	 *            -java���ڶ���
	 * @return String -����ת������ַ���
	 */
	public static String shortDateToString(Date date) {
		return dateToString(date, DateHelper.DATE_SHORT_SIMPLE_FORMAT);
	}

	/**
	 * ��Ӧ�÷�������ǰ���ڰ��ո�����ʽ������ת�����ַ���
	 * 
	 * @param format
	 *            -���ڸ�ʽ������
	 * @return String -����ת������ַ���
	 */
	public static String currentTimeToString(String format) {
		return dateToString(DateHelper.getCurrentDateTime(), format);
	}

	/**
	 * ���ַ���ת�������� ע�⣺һ��Ҫѡ��ƥ��ĸ�ʽ�������ܽ�����������null
	 * 
	 * @param strDate
	 *            - ����
	 * @param format
	 *            - ��ʽ
	 * @return Date -ת���������
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
	 * ���ַ���ת�������ڣ�Ĭ�ϸ�ʽ��yyyy-MM-dd
	 * 
	 * @param strDate
	 *            - ����
	 * @return Date -ת���������
	 */
	public static Date stringToDate(String strDate) {
		if (ObjectHelper.isEmpty(strDate))
			return null;
		return stringToDate(strDate, DateHelper.DATE_SHORT_FORMAT);
	}

	/**
	 * ��ȡָ������ƫ��delayDays�������
	 * 
	 * @param startDate
	 *            -��ʼ����
	 * @param delayDays
	 *            -�ӳٵ�����
	 * @return Date -ת���������
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
	 * ��ȡ��ǰ���ڣ�ûת����ʽ��
	 * 
	 * @return Date -ת���������
	 */
	public static Date getCurrentDate() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	/**
	 * ��ȡ��ǰ����
	 * 
	 * @return Date -ת���������
	 */
	public static Date getCurrentDateTime() {
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}

	/**
	 * ��ȡ��ǰʱ�䣬��ȷ���루ûת����ʽ��
	 * 
	 * @return Date -ת���������
	 */
	public static Date getCurrentTime() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * ��ȡ��һ��ʱ�䣬��ȷ���루ûת����ʽ��
	 * 
	 * @return Date -ת���������
	 */
	public static Date getLastYearTime() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 1);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * ���ÿ�ʼ����ʱ�����ʽ 00:00:00���÷����Ѿ���ʱ����ʹ��getMinLimitDate
	 * 
	 * @param date
	 *            -����
	 * @return Date -ת���������
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
	 * ���ý�������ʱ�����ʽ 23:59:59,�÷����Ѿ���ʱ����ʹ��getMaxLimitDate
	 * 
	 * @param date
	 *            -����
	 * @return Date -ת���������
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
	 * ��ȡ��ǰ���-��
	 * 
	 * @return String -��ǰ���
	 */
	public static String getCurrentLongYear() {
		Calendar c = Calendar.getInstance();
		String year = Integer.toString(c.get(Calendar.YEAR));
		return year;
	}

	/**
	 * ��ȡ��ǰ���-��
	 * 
	 * @return String -��ǰ���
	 */
	public static String getCurrentYear() {
		Calendar c = Calendar.getInstance();
		String year = Integer.toString(c.get(Calendar.YEAR));
		year = year.substring(2);
		return year;
	}

	/**
	 * ��ȡ��ǰ�·�,1~9��Ϊһλ�ַ�����
	 * 
	 * @return String -�����·�
	 */
	public static String getCurrentMonth() {
		Calendar c = Calendar.getInstance();
		String month = Integer.toString(c.get(Calendar.MONTH) + 1);
		return month;
	}

	/**
	 * ��ȡ��ǰ�·ݣ�Ĭ�ϸ�ʽ��00��1~9�±�ʾΪ 01~09
	 * 
	 * @return
	 */
	public static String getCurrentMonth2() {
		String month = getCurrentMonth();
		return month.length() < 2 ? "0" + month : month;
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return String -���ڣ�Ĭ�ϸ�ʽ��00
	 */
	public static String getCurrentDay() {
		Calendar c = Calendar.getInstance();
		String date = Integer.toString(c.get(Calendar.DATE));
		if (date.length() < 2)
			date = "0" + date;
		return date;
	}

	/**
	 * ��ȡ����Сʱ
	 * 
	 * @return String -Сʱ��Ĭ�ϸ�ʽ��00
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
	 * ��ȡ�������
	 * 
	 * @return String -�֣�Ĭ�ϸ�ʽ��00
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
	 * ��ȡ������
	 * 
	 * @return String -��, Ĭ�ϸ�ʽ��00
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
	 * ��ȡָ��ʱ���ǰһ���ָ���������ڡ��磺20120101
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String getBeforeDay(String date, String format) {
		return getBeforeDay(date, format, -1);
	}

	/**
	 * ��ȡָ��ʱ���ǰn���ָ���������ڡ��磺20120101
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
	 * ��ȡָ��ʱ������ĩ���ڡ��磺201201
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getBeforeMonth(String date, String format) {
		return getBeforeMonth(date, format, -1);
	}

	/**
	 * ��ȡָ��ʱ��ǰn��ĩ���ڡ��磺201201
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
	 * ���ָ�����ڵļ�������·�
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
	 * ���ָ��ʱ���ϼ���ĩ����
	 * 
	 * @param dates
	 * @return
	 */
	public static String getBeforeQuarterly(String date, String format) {
		return getBeforeQuarterly(date, format, -1);

	}

	/**
	 * ���ָ��ʱ��ǰn����ĩ����
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
	 * ���ָ����ݵ�����ĩ����
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getBeforeYear(String date, String format) {
		return getBeforeYear(date, format, -1);
	}

	/**
	 * ���ָ����ݵ�ǰn��ĩ����
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
	 * ��ȡָ��ʱ���ǰһ���Ĭ����������
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String getBeforeDay(String date) {
		return getBeforeDay(date, DateHelper.DATE_WITHMILLISECOND_FORMAT);
	}

	/**
	 * ��ȡָ��ʱ���ǰһ������
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
	 * ��ȡָ��ʱ��ĺ�һ���ָ����������
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
	 * ��ȡָ��ʱ��ĺ�һ���Ĭ����������
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String getAfterDay(String date) {
		return getAfterDay(date, DateHelper.DATE_WITHMILLISECOND_FORMAT);
	}

	/**
	 * ��ȡָ��ʱ��ǰһ������ʱ��Ĺ̶��������� yyyy-MM-dd HH:mm:ss
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
	 * ��ȡָ��ʱ��ǰһ������ʱ��Ĺ̶��������� yyyy-MM-dd HH:mm:ss
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
	 * ��ȡָ��ʱ���һ��Ŀ�ʼʱ��Ĺ̶��������� yyyy-MM-dd HH:mm:ss
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
	 * ��ȡָ��ʱ���һ��Ŀ�ʼʱ��Ĺ̶��������� yyyy-MM-dd HH:mm:ss
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
	 * ����һ�����ڣ����������ڼ����ַ���
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
	 * ����һ�����ڣ����������ڼ���int����(����һ��1�����ڶ���2�� ��������3�������ģ�4�������壺5;��������6;�����죺0)
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
	 * ����һ�����ڣ����������ڼ���int����(����һ��1�����ڶ���2�� ��������3�������ģ�4�������壺5;��������6;�����죺0)
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
	 * ��ȡָ��ʱ��ΰ���������
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<String> getWeeksByTimePeriod(String startDate, String endDate) {
		return getWeeksByTimePeriod(startDate, endDate, null);
	}

	/**
	 * ��ȡָ��ʱ��ΰ���������
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
		 * ָ��ʱ�����������
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
	 * ����ʱ��֮�������
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
		// ת��Ϊ��׼ʱ��
		SimpleDateFormat myFormatter = new SimpleDateFormat(DateHelper.DATE_SHORT_FORMAT);
		Date date = null;
		Date mydate = null;

		date = myFormatter.parse(date1);
		mydate = myFormatter.parse(date2);
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return (new BigDecimal(day).abs()).longValue();
	}

	/**
	 * ����ʱ��֮�������
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
	 * ����һ������жϸ�����Ƿ�Ϊ����
	 * 
	 * @param year
	 * @return false:�������� true:����
	 */
	public static boolean isLeapYear(int year) {
		GregorianCalendar calendar = new GregorianCalendar();
		return calendar.isLeapYear(year);
	}

	/**
	 * @param int minute ƫ�Ʒ�����
	 * @return String ���ؾ�ƫ�ƺ��ʱ���ַ���
	 */

	public static String getOffsetByMinute(int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + minute);
		return dateToString(calendar.getTime(), DateHelper.DATE_WITHSECOND_FORMAT);
	}

	/**
	 * ���հ�СʱΪ������λ
	 * 
	 * ���ص�ǰ����
	 * 
	 * @return int
	 */
	public static int getHalfHourCnt() {
		Calendar c = Calendar.getInstance();
		// Сʱ
		int hour = c.get(Calendar.HOUR_OF_DAY);
		// ����
		int minute = c.get(Calendar.MINUTE);
		// �����ǰʱ�����30���ӣ��������1
		return (minute >= 30) ? (hour * 2 + 1) : (hour * 2);
	}

	/**
	 * @param int halfHour ���հ�СʱΪ������λ
	 * 
	 *        ���ص�ǰ����ʱ�ε����ʱ��(��ʽyyyy-mm-dd hh:mi:sss)
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
	 * ���ص�ǰСʱ
	 * 
	 * @return int
	 */
	public static int getHour() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * ���ص�������
	 * 
	 * @return int
	 */
	public static int getDay() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * ���������պ�
	 * 
	 * @return int
	 */
	public static int getDayOfWeek() {
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_WEEK);
		return (day - 1) > 0 ? (day - 1) : 7;
	}

	/**
	 * �õ���ǰ�յ����ʱ��
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
	 * �õ���ǰ�յ���Сʱ��
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
	 * ��ǰϵͳʱ���ȥ����ķ�����
	 * 
	 * @param minute
	 *            ������
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
	// * ��ȡCRA������ʱ��
	// *
	// * @return ͳһ��Դ������ʱ��
	// */
	// public static Date getCRAServerTime() {
	// return getCurrentDateTime();
	// }

	/**
	 * ��ȡ���������뿪ʼ����֮�������
	 * 
	 * @param endDate
	 *            ��������
	 * @param startDate
	 *            ��ʼ����
	 * @return ����
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
	 * ����ָ�����ڣ��ӳ�n���ʱ��
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
	 * ��ȡָ��ʱ�����
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
	 * ��ȡָ��ʱ���·�
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
	 * ����ָ��ʱ���ȡ����
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
	 * ����ָ��ʱ���ȡСʱ��
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
	 * ����ָ��ʱ���ȡ������
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
	 * ����ָ��ʱ�䷵��ÿyear���month��day��hourСʱminute���ӵ�����
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
	 * ����ָ��ʱ�䷵��ÿmonth�µ�day��hourСʱminute���ӵ�����
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
	 * ����ָ��ʱ�䷵��ÿday�յ�hourСʱminute���ӵ�����
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
	 * ����ָ��ʱ�䷵��ÿhourСʱ��minute���ӵ�����
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
	 * ����ָ��ʱ�䷵��ÿminute���ӵ�����
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
	 * ȡ�ÿ�ʼʱ�������ʱ������Сʱ��
	 * 
	 * @param startdDate
	 * @param endDate
	 * @return
	 */
	public static double getHour(Date startdDate, Date endDate) {
		// ȡ�ÿ�ʼʱ�������ʱ�����ĺ�����
		long time = endDate.getTime() - startdDate.getTime();
		// ȡ�ÿ�ʼʱ�������ʱ������Сʱ����������λС����
		double hour = NumberHelper.formaterNumber((double) (time / (1000 * 60 * 60d)), 2);
		return hour;
	}

	/**
	 * ȡ��һ��ʱ���Сʱ��
	 * 
	 * @param date
	 * @return
	 */
	public static double getHour_(Date date) {
		// ȡ�ÿ�ʼʱ����ռ�İٷֱ�
		Calendar aCalendar = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		aCalendar.setTime(DateHelper.stringToDate(df.format(date), "yyyy-MM-dd"));
		return getHour(aCalendar.getTime(), date);
	}

	/**
	 * ��Long�ͱ���ʱ��ת����ָ����ʽ���ַ���ʱ��
	 * 
	 * @param date
	 *            yyyyMMddHHmmss(yyyyMMddHHmm,yyyyMMddHH,yyyyMMdd)��ʽ��ʱ��
	 * @param format
	 *            ���ص�ʱ���ʽ
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
	 * ��Long�ͱ���ʱ��ת����'yyyy-mm-dd'��ʽ���ַ���ʱ��
	 * 
	 * @param date
	 *            yyyyMMddHHmmss(yyyyMMddHHmm,yyyyMMddHH,yyyyMMdd)��ʽ��ʱ��
	 * @param format
	 *            ���ص�ʱ���ʽ
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
	 * ��ָ����ʽ�������ִ�ת����ָ����ʽLong
	 * 
	 * @param dateTime
	 *            Ҫת�������ڡ�ʱ��
	 * @param fromFormat
	 *            �����ִܵĸ�ʽ
	 * @param toFormat
	 *            Ŀ���ʽ
	 * @return
	 */
	public static Long stringDateToLong(String dateTime, String fromFormat, String toFormat) {
		return Long.parseLong(DateHelper.dateToString(DateHelper.stringToDate(dateTime, fromFormat), toFormat));
	}
	
	/**
	 * ��ָ����ʽ�������ִ�ת����ָ����ʽLong
	 * 
	 * @param dateTime
	 *            Ҫת�������ڡ�ʱ��
	 * @param fromFormat
	 *            �����ִܵĸ�ʽ
	 * @param toFormat
	 *            Ŀ���ʽ
	 * @return
	 */
	public static Long dateToLong(Date date, String fromFormat) {
		return DateHelper.stringDateToLong(DateHelper.dateToString(date,fromFormat),fromFormat,fromFormat);
	}
	
	/**
	 * �ж����������Ƿ������·�
	 * @param start ��ʼ����
	 * @param end ��������
	 * @param formater ��ʽ
	 * @return
	 */
	public static boolean checkContinuous(String start,String end){
		//���û�п�ʼ�·����Ϊ����
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
	 * ָ���·�ǰ�����·�
	 * @param start ָ���·�
	 * @param month �·�
	 * @param formater ��ʽ
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
	 * ָ���·�ǰ�����·�
	 * @param start ָ���·�
	 * @param month �·�
	 * @return
	 */
	public static String getAfterMonth(String start,Integer month){
		return DateHelper.getAfterMonth(start, month,DateHelper.DATE_SHORT_LINED_MONTH_FORMAT);
	}
	
	/**
	 * �ж� when �Ƿ�����start ���Ҳ����� endָ����ʱ��
	 * 
	 * @param start ��ʼʱ��
	 * @param end ����ʱ��
	 * @param when �Ƚ�ʱ��
	 * @return true start <= when <= end
	 */
	public static Boolean isBetween(Date start, Date end, Date when) {
		return when.after(start) && when.before(end);
	}
	
	/**
	 * ��������ת���� ��XX��XX��XX���� �ı����ʽ
	 * 
	 * @param minutes ������
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
			duration = minutes + "����";
		}
		
		if (hours > 0) {
			duration = hours + "Сʱ" + duration;
		}
		
		if (days > 0) {
			duration = days + "��" + duration;
		}
		
		return  duration;
	}
	
	public static void main(String[] args) {
		System.out.println(DateHelper.isBetween(DateHelper.getBeforeDay(getCurrentDate()), DateHelper.getAfterDay(getCurrentDate()), getCurrentDate()));
	}
}
