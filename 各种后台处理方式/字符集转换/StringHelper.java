package com.zdsoft.framework.common.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;

public class StringHelper {

	/**
	 * �ַ���ת��(utf-8--->iso-8859-1)
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
	 * �ַ���ת��(utf-8--->code)
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
	 * �ַ���ת��(iso-8859-1--->code)
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
	 * �ַ���ת��(iso-8859-1--->utf-8)
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
	 * ��ָ���ָ����ָ���ִ�ת��List
	 * 
	 * @param str
	 *            ��������ַ���
	 * @param delimiter
	 *            �ָ���
	 * @return List<String>
	 */
	public static List<String> delimitedStringToList(String str,
			String delimiter) {
		String[] s = StringUtils.delimitedListToStringArray(str, delimiter);

		return Arrays.asList(s);
	}

	/**
	 * ʹ��ģ��������ʱ��ģ������vo�����ַ�������
	 * 
	 * @param obj
	 *            ��ת���vo
	 * @throws IllegalArgumentException
	 *             �������󣡲�������Ϊ�ա�����
	 * @throws IllegalAccessException
	 *             ��������
	 * @throws UnsupportedEncodingException
	 *             ��֧�ֵı��뷽ʽ
	 */
	public static void decodeObject(Object obj)
			throws IllegalArgumentException, IllegalAccessException,
			UnsupportedEncodingException {
		if (ObjectHelper.isEmpty(obj)) {
			return;
		}
		// ȡ�øö����������ж�����ֶ�,����ÿ���ֶν���ת��
		for (Field field : obj.getClass().getDeclaredFields()) {
			// ���˶���� accessible ��־����Ϊָʾ�Ĳ���ֵ��(��,�����ֶ�Ϊprivateʱ,Ҳ���Է���)
			field.setAccessible(true);
			// ��ָ�������ϴ� Field ��ʾ���ֶε�ֵ��(��,ȡ�ô�������и��ֶε�ֵ)
			Object fieldObj = field.get(obj);
			if (!ObjectHelper.isEmpty(fieldObj)) {
				// ֻ�����ֶ�ΪString���͵�ʱ�������������,��Ϊ������������͵Ļ�,������ת����ʱ��ͳ�����
				if (field.getType() == String.class) {
					// ��ָ����������ϴ� Field �����ʾ���ֶ�����Ϊָ������ֵ��(��,������Ķ������������ֶ�����Ϊת����ֵ)
					field.set(
							obj,
							!fieldObj.toString().trim().isEmpty() ? decodeFromISOToCode(fieldObj
									.toString()) : null);
				}
			}
		}
	}

	/**
	 * ���ַ���ת��������
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
	 * ��String����ת���ɶ��ŷָ���ַ���,��'abc','efg'
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
	 * ��String����ת���ɶ��ŷָ���ַ���
	 * 
	 * @param strAry
	 * @return
	 */
	public static String stringArrayToDelimitedString(String[] strAry) {
		return StringUtils.arrayToCommaDelimitedString(strAry);
	}

	/**
	 * ��String����ת����ָ���ַ��ָ���ַ���
	 * 
	 * @param strAry
	 *            ��ת�����ַ�������
	 * @param delimtor
	 *            �ָ���
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
	 * ���ַ����滻������������
	 * @param str ԭ����
	 * @param index Ҫ�滻����
	 * @param rep ֵ
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
	 * ��ʽ��javascript�ַ���
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
	 * �ж������ִ��Ƿ���ȣ�"" �� null��Ϊ��ȡ�
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
