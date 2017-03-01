package com.zdsoft.framework.common.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;

public class StringHelper {

	/**
	 * 字符集转换(utf-8--->iso-8859-1)
	 * 
	 * @param source
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decodeFromUtfToCode(String source)
			throws UnsupportedEncodingException {
		return decodeFromUtfToCode(source, "iso-8859-1");
	}

	/**
	 * 字符集转换(utf-8--->code)
	 * 
	 * @param source
	 * @param code
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decodeFromUtfToCode(String source, String code)
			throws UnsupportedEncodingException {
		if (ObjectHelper.isEmpty(source))
			return null;
		return new String(source.getBytes("utf-8"), code);
	}

	/**
	 * 字符集转换(iso-8859-1--->code)
	 * 
	 * @param source
	 * @param code
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decodeFromISOToCode(String source, String code)
			throws UnsupportedEncodingException {
		if (ObjectHelper.isEmpty(source))
			return null;
		return new String(source.getBytes("iso-8859-1"), code);
	}

	/**
	 * 字符集转换(iso-8859-1--->utf-8)
	 * 
	 * @param source
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decodeFromISOToCode(String source)
			throws UnsupportedEncodingException {
		return decodeFromISOToCode(source, "utf-8");
	}

	/**
	 * 把指定分隔符分割的字窜转成List
	 * 
	 * @param str
	 *            待处理的字符串
	 * @param delimiter
	 *            分隔符
	 * @return List<String>
	 */
	public static List<String> delimitedStringToList(String str,
			String delimiter) {
		String[] s = StringUtils.delimitedListToStringArray(str, delimiter);

		return Arrays.asList(s);
	}

	/**
	 * 使用模型驱动的时候将模型驱动vo进行字符串解码
	 * 
	 * @param obj
	 *            待转码的vo
	 * @throws IllegalArgumentException
	 *             参数错误！参数不能为空。。。
	 * @throws IllegalAccessException
	 *             参数错误！
	 * @throws UnsupportedEncodingException
	 *             不支持的编码方式
	 */
	public static void decodeObject(Object obj)
			throws IllegalArgumentException, IllegalAccessException,
			UnsupportedEncodingException {
		if (ObjectHelper.isEmpty(obj)) {
			return;
		}
		// 取得该对象里面所有定义的字段,并对每个字段进行转码
		for (Field field : obj.getClass().getDeclaredFields()) {
			// 将此对象的 accessible 标志设置为指示的布尔值。(即,当该字段为private时,也可以访问)
			field.setAccessible(true);
			// 回指定对象上此 Field 表示的字段的值。(即,取得传入对象中改字段的值)
			Object fieldObj = field.get(obj);
			if (!ObjectHelper.isEmpty(fieldObj)) {
				// 只有在字段为String类型的时候才有中文乱码,因为如果是其他类型的话,在类型转换的时候就出错了
				if (field.getType() == String.class) {
					// 将指定对象变量上此 Field 对象表示的字段设置为指定的新值。(即,将传入的对象里面的这个字段设置为转码后的值)
					field.set(
							obj,
							!fieldObj.toString().trim().isEmpty() ? decodeFromISOToCode(fieldObj
									.toString()) : null);
				}
			}
		}
	}

	/**
	 * 将字符串转换成数组
	 * 
	 * @param source
	 * @param reg
	 * @return
	 */
	public static String[] split(String source, String reg) {
		if (ObjectHelper.isEmpty(source))
			return null;
		return source.split(",");
	}

	/**
	 * 将String数组转换成逗号分割的字符串,如'abc','efg'
	 * 
	 * @param strAry
	 * @return
	 */
	public static String stringArrayToDelimitedStringWithQuotation(
			String[] strAry) {
		List<String> strList = new ArrayList<String>();
		for (String str : strAry) {
			str = "'" + str + "'";
			strList.add(str);
		}

		return StringUtils.arrayToCommaDelimitedString(StringUtils
				.toStringArray(strList));
	}

	/**
	 * 将String数组转换成逗号分割的字符串
	 * 
	 * @param strAry
	 * @return
	 */
	public static String stringArrayToDelimitedString(String[] strAry) {
		return StringUtils.arrayToCommaDelimitedString(strAry);
	}

	/**
	 * 将String数组转换成指定字符分割的字符串
	 * 
	 * @param strAry
	 *            待转换的字符窜数组
	 * @param delimtor
	 *            分隔符
	 * @return
	 */
	public static String stringArrayToDelimitedString(String[] strAry,
			String delimtor) {
		StringBuffer sb = new StringBuffer();

		for (String s : strAry) {
			sb.append(s).append(delimtor);
		}

		if (sb.length() > 0) {
			return sb.substring(0, sb.length() - 1);
		} else {
			return "";
		}
	}

	/**
	 * 将字符串替换成批定的数据
	 * @param str 原数据
	 * @param index 要替换数据
	 * @param rep 值
	 * @return
	 */
	public static String replaceStr(String str,String index,String rep){
		while (str.indexOf(index)>=0) {
			int indexs = str.indexOf(index);
			str = str.substring(0,indexs)+rep+str.substring(indexs+index.length());
		}
		
		return str;
	}
	
	/**
	 * 格式化javascript字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String formatJsString(String str) {
		if (str != null && str.length() > 1) {
			str = str.replaceAll("\\\\", "\\\\\\\\");
			str = str.replaceAll("\\\n", "\\\\n");
			str = str.replaceAll("\\\r", "\\\\r");
			str = str.replaceAll("\\\"", "\\\\\"");

			/*
			 * str = str.replaceAll("(\r|\n|\r\n|\n\r)", " "); str =
			 * str.replaceAll("\"", "\\\\" + "\""); str = str.replaceAll("\'",
			 * "\\\\" + "\'");
			 */
		}
		return str;
	}
	
	/**
	 * 判断两个字窜是否相等，"" 与 null视为相等。
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean compare(String str1, String str2) {
		if (ObjectHelper.isEmpty(str1) && ObjectHelper.isEmpty(str2)) {
			return true;
		} else if (ObjectHelper.isNotEmpty(str1) && ObjectHelper.isNotEmpty(str2)) {
			return str1.equals(str2);
		} else {
			return false;
		}
	}
}
