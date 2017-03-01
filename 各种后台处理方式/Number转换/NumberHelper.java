package com.zdsoft.framework.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberHelper {
	/**
	 * �ж�str�Ƿ���ֵ����
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[+,-]?[\\d.]+");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * С����������
	 * 
	 * @param number
	 * @param decimalPlaces
	 *            С��λ��
	 * @return
	 */
	public static double formaterNumber(double number, int decimalPlaces) {
		if (ObjectHelper.isNotEmpty(number)) {
			BigDecimal bigDecimal = new BigDecimal(number);
			double res = Double.parseDouble(bigDecimal.setScale(decimalPlaces,
					BigDecimal.ROUND_HALF_UP).toString());
			return res;
		}
		return 0;
	}

	/**
	 * ��numת��ָ�����ȵ��ַ���
	 * 
	 * @param num
	 *            Ҫת��������
	 * @param length
	 *            ת������ַ�������
	 * 
	 * @return
	 */
	public static String numericToString(Integer num, Integer length) {
		String fmt = "0";
		
		for (int i = 0 ; i < length - 1; i ++) {
			fmt += "0";
		}

		DecimalFormat df = new DecimalFormat(fmt);
		
		return df.format(num);
	}

	/**
	 * ��numת��ָ�����ȵ��ַ���
	 * 
	 * @param num
	 *            Ҫת��������
	 * @param length
	 *            ת������ַ�������
	 * 
	 * @return
	 */
	public static String numericToString(Long num, Integer length) {
		String fmt = "0";
		
		for (int i = 0 ; i < length - 1; i ++) {
			fmt += "0";
		}

		DecimalFormat df = new DecimalFormat(fmt);
		
		return df.format(num);
	}

	/**
	 * ���ؾ���ΪС�������λ��double����
	 * 
	 * @param divisor
	 * @param dividend
	 * @return
	 */
	public static String getDivision(String divisor, int dividend) {
		if (dividend == 0) {
			return "0.00";
		}
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(Double.parseDouble(divisor) / dividend);
	}

	/**
	 * �ж�str�Ƿ������ַ�
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDigit(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();

	}
	
	/**
	 * ��Ԫת������Ԫ
	 * @param obj
	 * @return
	 */
	public static String numberEnlarge(double d){
		if(ObjectHelper.isNotEmpty(d)){
			BigDecimal b = new BigDecimal(d/10000); 
			return b.toPlainString();
		}else{
			return "";
		}
		
	}
	
	/**
	 * ��Ԫת������Ԫ
	 * @param obj
	 * @return
	 */
	public static String numberEnlarge(Double d){
		if(ObjectHelper.isNotEmpty(d)){
			BigDecimal b = new BigDecimal(d/10000); 
			return b.toPlainString();
		}else{
			return "";
		}

	}
	/**
	 * ��Ԫת������Ԫ
	 * @param obj
	 * @return
	 */
	public static String numberEnlarge(BigDecimal d){
		if(ObjectHelper.isNotEmpty(d)){
			return d.movePointLeft(4).toPlainString();
		}else{
			return "";
		}
	}
	/**
	 *����Ԫת��Ԫ
	 * @param d
	 * @return
	 */
	public static Double numberMicrify(double d){
		if(ObjectHelper.isNotEmpty(d)){
			return d*10000;
		}else{
			return null;
		}
	}
	/**
	 *����Ԫת��Ԫ
	 * @param d
	 * @return
	 */
	public static Double numberMicrify(Double d){
		if(ObjectHelper.isNotEmpty(d)){
			return d*10000;
		}else{
			return null;
		}
	}
	/**
	 *����Ԫת��Ԫ
	 * @param d
	 * @return
	 */
	public static Double numberMicrify(BigDecimal d){
		if(ObjectHelper.isNotEmpty(d)){
			return d.movePointRight(4).doubleValue();
		}else{
			return null;
		}
		
	}
}
