package com.zdsoft.framework.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberHelper {
	/**
	 * 判断str是否数值类型
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
	 * 小数四舍五入
	 * 
	 * @param number
	 * @param decimalPlaces
	 *            小数位数
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
	 * 把num转成指定长度的字符串
	 * 
	 * @param num
	 *            要转换的数字
	 * @param length
	 *            转换后的字符串长度
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
	 * 把num转成指定长度的字符串
	 * 
	 * @param num
	 *            要转换的数字
	 * @param length
	 *            转换后的字符串长度
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
	 * 返回精度为小数点后两位的double数据
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
	 * 判断str是否数字字符
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDigit(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();

	}
	
	/**
	 * 将元转换成万元
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
	 * 将元转换成万元
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
	 * 将元转换成万元
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
	 *将万元转成元
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
	 *将万元转成元
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
	 *将万元转成元
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
