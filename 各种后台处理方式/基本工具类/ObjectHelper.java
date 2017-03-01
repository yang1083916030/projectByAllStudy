package com.zdsoft.framework.common.util;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;

import org.hibernate.proxy.HibernateProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.zdsoft.framework.common.exception.InvalidArgumentException;

/**
 * ����������
 * 
 * @author ӡ�ʸ�
 * @version 1.0.0
 * @since 1.0.0
 */
public class ObjectHelper {

	static Logger logger = LoggerFactory.getLogger(ObjectHelper.class);

	public static void main(String[] p) {
		StringBuffer sb = new StringBuffer();
		System.out.println(sb.length());
		System.out.println(ObjectHelper.isEmpty(sb));
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("d", "dddddddddddd");
		map.put("s", "dsdddddddddddd");
		map.put("b", "aadddddddddddd");
		JSONArray js = JSONArray.fromObject(map);
		System.out.println(js.toString());
		System.out.println(objectToJson(map, "ddd"));
	}

	public static boolean isEquals(Object object1, Object object2) {
		boolean ret = false;
		try {
			if (object1 == null && object2 == null) {
				ret = true;
				return ret;
			}
			ret = object1.equals(object2);
		} catch (NullPointerException e) {
			ret = false;
		}
		return ret;

	}

	/**
	 * �Ƚ����ַ����Ƿ���ȣ����Դ�Сд
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean equalsIgnorecase(String s1, String s2) {
		if (s1 == null && s2 == null)
			return true;
		if (s1 != null && s2 != null) {
			if (s1.toLowerCase().equals(s2.toLowerCase()))
				return true;
		}
		return false;
	}

	/**
	 * �ж϶����Ƿ�Ϊ��
	 * 
	 * @param obj
	 *            -��������
	 * @return boolean -true:��ʾ����Ϊ��;false:��ʾ����Ϊ�ǿ� ���ϣ� Collection.isEmpty()
	 *         ���飺�ж�����ÿ��Ԫ�أ�����Ԫ�ض�Ϊ�գ����ж�����Ϊ��
	 *         �ַ������ж��ַ�������"null"����ȥ������""�ִܺ󷵻�String.isEmpty()�Ľ�� �������ͷ��� false
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null)
			return true;

		if (obj instanceof Map) {
			return ((Map<?, ?>) obj).entrySet().isEmpty();
		}

		if (obj instanceof Collection) {
			return ((Collection<?>) obj).isEmpty();
		}

		if (obj instanceof String) {
			return ((String) obj).equalsIgnoreCase("null") | ((String) obj).trim().isEmpty();
		}

		if (obj instanceof StringBuffer) {
			return ((StringBuffer) obj).length() == 0;
		}

		if (obj.getClass().isArray()) {
			try {
				Object[] a = (Object[]) obj;

				boolean b = true;
				for (Object o : a) {
					b = b & ObjectHelper.isEmpty(o);

					if (!b)
						break;
				}

				return b;
			} catch (ClassCastException e) {
			}
		}

		return false;
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	/**
	 * У�������Ƿ�Ϲ�
	 * 
	 * @param email
	 *            ����
	 * @return true �Ϲ� false ���Ϲ�
	 */
	public static boolean isEmailFormat(String email) {

		Pattern pattern = Pattern
				.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean isEmailAdressFormat(String email) {
		boolean isExist = false;
		if (isEmpty(email)) {
			return isExist;
		}
		Pattern p = Pattern.compile("\\w+@(\\w+\\.)+[a-z]{2,3}");
		Matcher m = p.matcher(email);
		boolean b = m.matches();
		if (b) {
			isExist = true;
		}
		return isExist;
	}

	/**
	 * ��obj�ϻ�ȡָ������(expr, ���� field.attr)��ֵ��filed��obj�ϵ����ԣ�����Ӧ��getField()����
	 * 
	 * @param obj
	 * @param expr
	 * @return
	 */
	public static Object getFieldValue(Object obj, String expr) throws InvalidArgumentException {
		if (obj == null) {
			throw new InvalidArgumentException(expr);
		}

		Object value = null;
		if (ObjectHelper.isEmpty(expr)) {
			value = obj;
		} else {
			value = resolveVariableEL(obj, expr);
		}

		return value;
	}

	/**
	 * ��objs�л�ȡָ������(expr,����
	 * arg.field.attr)��ֵ��arg��ӦMap�е�key,field��key��Ӧ��value�����ϵ�����
	 * ��value�ϲ���Ӧ��getField()����
	 * 
	 * @param obj
	 * @param expr
	 * @return
	 */
	public static Object getFieldValue(Map<String, Object> objs, String expr) throws InvalidArgumentException {
		// ���ҵ�һ��ֵ����
		String targetNm = null;
		if (ObjectHelper.isNotEmpty(expr)) {
			int pos = expr.indexOf(".");
			if (pos > -1) {
				targetNm = expr.substring(0, pos);
				expr = expr.substring(pos + 1);
			} else {
				targetNm = expr;
				expr = null;
			}
		}

		Object targetObject = objs.get(targetNm);
		if (targetObject == null) {
			throw new InvalidArgumentException(targetNm);
		}

		Object value = null;
		if (ObjectHelper.isEmpty(expr)) {
			value = targetObject;
		} else {
			value = resolveVariableEL(targetObject, expr);
		}

		return value;
	}

	private static Object resolveVariableEL(Object target, String el) {
		int pos = el.indexOf(".");

		if (pos > -1) {
			String field = el.substring(0, pos);
			logger.debug("field" + field);
			el = el.substring(pos + 1);
			logger.debug("el = " + el);

			Object value = getValue(target, field);
			if (ObjectHelper.isNotEmpty(el)) {
				value = resolveVariableEL(value, el);
			}

			return value;
		} else {
			return getValue(target, el);
		}
	}

	private static Object getValue(Object target, String field) {
		Method m;
		try {
			m = target.getClass().getMethod("get" + StringUtils.capitalize(field));

			return m.invoke(target, new Object[] {});
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	// /**
	// * �ж�Map<String,Object>��ָ��key��valueֵ�Ƿ�Ϊ��
	// *
	// * @param params
	// * @param key
	// * @return boolean -true:��ʾvalueΪ��;false:��ʾvalueΪ�ǿ�
	// */
	// public static boolean isEmpty(Map<String, Object> params, String key) {
	// if (isEmpty(params))
	// return true;
	// else {
	// if (params.containsKey(key) && !isEmpty(params.get(key)))
	// return false;
	// return true;
	// }
	// }
	//
	//
	// /**
	// * ʹ��ģ��������ʱ��ģ������vo�����ַ�������
	// *
	// * @param obj
	// * ��ת���vo
	// * @throws IllegalArgumentException
	// * �������󣡲�������Ϊ�ա�����
	// * @throws IllegalAccessException
	// * ��������
	// * @throws UnsupportedEncodingException
	// * ��֧�ֵı��뷽ʽ
	// */
	// public static void decodeObject(Object obj)
	// throws IllegalArgumentException, IllegalAccessException,
	// UnsupportedEncodingException {
	// if (StringUtil.isEmpty(obj)) {
	// return;
	// }
	// // ȡ�øö����������ж�����ֶ�,����ÿ���ֶν���ת��
	// for (Field field : obj.getClass().getDeclaredFields()) {
	// // ���˶���� accessible ��־����Ϊָʾ�Ĳ���ֵ��(��,�����ֶ�Ϊprivateʱ,Ҳ���Է���)
	// field.setAccessible(true);
	// // ��ָ�������ϴ� Field ��ʾ���ֶε�ֵ��(��,ȡ�ô�������и��ֶε�ֵ)
	// Object fieldObj = field.get(obj);
	// if (!StringUtil.isEmpty(fieldObj)) {
	// // ֻ�����ֶ�ΪString���͵�ʱ�������������,��Ϊ������������͵Ļ�,������ת����ʱ��ͳ�����
	// if (field.getType() == String.class) {
	// // ��ָ����������ϴ� Field �����ʾ���ֶ�����Ϊָ������ֵ��(��,������Ķ������������ֶ�����Ϊת����ֵ)
	// field.set(
	// obj,
	// !fieldObj.toString().trim().isEmpty() ? URLDecoder
	// .decode(fieldObj.toString(),
	// CommonConstant.UTF8) : null);
	// }
	// }
	// }
	// }
	//
	// /**
	// * �ַ�������
	// *
	// * @param str
	// * ��ת����ַ���
	// * @throws UnsupportedEncodingException
	// * ��֧�ֵı��뷽ʽ
	// */
	// public static void decodeString(String str)
	// throws UnsupportedEncodingException {
	// str = URLDecoder.decode(str, CommonConstant.UTF8);
	// }
	//
	// /**
	// * ��ָ���ַ����ĺ�4���ַ��滻��*
	// *
	// * @param source
	// * @param len
	// * @return
	// */
	// public static String replacementPartString(String source) {
	// return StringUtil.replacementPartString(source, 4, '*');
	// }
	//
	// /**
	// * ��ָ���ַ����ĺ�Len���ַ��滻��*
	// *
	// * @param source
	// * @param len
	// * @return
	// */
	// public static String replacementPartString(String source, int len) {
	// return StringUtil.replacementPartString(source, len, '*');
	// }
	//
	// /**
	// * ��ָ���ַ����ĺ�Len���ַ��滻��replaceStr
	// *
	// * @param source
	// * @param len
	// * @param replaceStr
	// * @return
	// */
	// public static String replacementPartString(String source, int len,
	// char replaceStr) {
	// if (StringUtil.isEmpty(source))
	// return "";
	// else {
	// if (source.length() < 4)
	// return source;
	// else {
	// String dest = source.substring(0, source.length() - 4);
	// for (int i = 0; i < len; i++) {
	// dest += replaceStr;
	// }
	// return dest;
	// }
	// }
	//
	// }
	//
	// /**
	// * ���ڴ����ţ�Ӧ���빫�ĵȣ�
	// *
	// */
	// public static String idCode(String profix, String type, String year,
	// String seq, int len) {
	// String idCode = profix + type + year;
	// for (int i = seq.length(); i < len; i++) {
	// seq = "0" + seq;
	// }
	// return idCode + seq;
	// }
	//
	// /**
	// * ��һ���յ�object ת����""
	// *
	// * @param obj
	// * @return
	// */
	// public static String toChangeString(Object obj) {
	// if (obj == null) {
	// obj = "";
	// }
	// return String.valueOf(obj);
	// }
	//
	// /**
	// * ����֤��ת����Сд
	// *
	// * @param str
	// * @return
	// */
	// public static String validateToLow(Object str) {
	// String validate = "";
	// if (str != null) {
	// validate = str.toString().toLowerCase();
	// }
	// return validate;
	// }
	//
	//
	// /**
	// * ��֤������ַ����Ƿ�������
	// *
	// * @Title: PartternValidateNm
	// * @Description:
	// * @param @return
	// * @return boolean
	// * @throws
	// */
	// public static boolean PartternValidateNm(String str) {
	// String pattern = "[0-9]*";
	// Pattern p = Pattern.compile(pattern);
	// Matcher m = p.matcher(str);
	// boolean b = m.matches();
	// return b;
	// }
	//
	// /**
	// * ��һ��int����ת����String
	// *
	// * @param count
	// * @return
	// */
	// public static String intsToString(int[] count) {
	// String str = "";
	// for (int value : count) {
	// str = str + String.valueOf(value) + "@";
	// }
	// return str;
	// }

	/**
	 * ��string ת���� �ص�����+����
	 * 
	 * @param str
	 * @param jsonCallBack
	 * @return
	 */
	public static String objectToJson(String str, String jsonCallBack) {
		return jsonCallBack + "('" + str + "')";
	}

	/**
	 * ��string ת���� �ص�����+����
	 * 
	 * @param str
	 * @param jsonCallBack
	 * @return
	 */
	public static String objectToJson(Boolean flag, String jsonCallBack) {
		return jsonCallBack + "(" + flag + ")";
	}

	/**
	 * ��list ת���� �ص�����+����
	 * 
	 * @param list
	 * @param jsonCallBack
	 * @return
	 */
	public static String objectToJson(List<?> list, String jsonCallBack) {
		JSONArray json = JSONArray.fromObject(list);
		String temp = json.toString();
		return jsonCallBack + "(" + temp + ")";
	}

	/**
	 * ��list ת���� �ص�����+����
	 * 
	 * @param set
	 * @param jsonCallBack
	 * @return
	 */
	public static String objectToJson(List<?> set) {
		JSONArray json = JSONArray.fromObject(set);
		String temp = json.toString();
		return temp;
	}

	/**
	 * ��collectionת���� �ص�����+����
	 * 
	 * @param coll
	 * @param jsonCallBack
	 * @return
	 */
	public static String objectToJson(Set<?> coll) {
		JSONArray json = JSONArray.fromObject(coll);
		String temp = json.toString();
		return temp;
	}

	/**
	 * ��hashmap ת���� �ص�����+����
	 * 
	 * @param list
	 * @param jsonCallBack
	 * @return
	 */
	public static String objectToJson(Map<?, ?> map, String jsonCallBack) {
		JSONArray json = JSONArray.fromObject(map);
		String res = "";
		res = json.get(0).toString();
		return jsonCallBack + "(" + res + ")";
	}

	/**
	 * ��hashmap ת���� �ص�����+����
	 * 
	 * @param list
	 * @param jsonCallBack
	 * @return
	 */
	public static String objectToJson(Object obj, String jsonCallBack) {
		JSONArray json = JSONArray.fromObject(obj);
		String res = "";
		res = json.get(0).toString();
		return jsonCallBack + "(" + res + ")";
	}

	/**
	 * ������ת����json
	 * 
	 * @param obj
	 * @param jsonCallBack
	 * @return
	 */
	public static String objectToJson(Object obj) {
		JSONArray json = JSONArray.fromObject(obj);
		String res = "";
		res = json.get(0).toString();
		return res;
	}

	/**
	 * ʵ����HibernateProxy�������ʵ�����
	 * 
	 * @param obj
	 * @return
	 */
	public static Object initializeProxy(Object obj) {
		if (obj instanceof HibernateProxy) {
			obj = ((HibernateProxy) obj).getHibernateLazyInitializer().getImplementation();
		}

		return obj;
	}

	/**
	 * �ж�obj�Ƿ�ʵ����type�ӿ�,���type���ǽӿ����ͣ�ֱ�ӷ���false
	 * 
	 * @param obj
	 *            Ҫ���Ķ���
	 * @param type
	 *            �ӿڵ�����
	 * @return
	 */
	public static boolean isImplement(Object obj, Class<?> type) {
		if (!type.isInterface()) {
			return false;
		}

		Class<?>[] clzz = obj.getClass().getInterfaces();

		for (Class<?> clz : clzz) {
			if (clz.getName().equals(type.getName())) {
				return true;
			}
		}

		return false;
	}
}
