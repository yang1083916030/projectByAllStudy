package com.zdsoft.framework.common.util;

import java.io.Reader;
import java.sql.Clob;

import javax.sql.rowset.serial.SerialClob;



public class ClobUtil {
	/**
	 * ��Clobת��String
	 * 
	 * @param clob
	 * @return
	 */
	public static String clobToString(Clob clob) {
		if (ObjectHelper.isEmpty(clob))
			return null;
		StringBuffer sb = new StringBuffer();
		Reader clobStream = null;
		try {
			clobStream = clob.getCharacterStream();
			char[] b = new char[1024];// ÿ�λ�ȡ1K
			int i = 0;
			while ((i = clobStream.read(b)) != -1) {
				sb.append(b, 0, i);
			}
		} catch (Exception ex) {
			sb = null;
		} finally {
			try {
				if (!ObjectHelper.isEmpty(clobStream)) {
					clobStream.close();
				}
			} catch (Exception e) {
			}
		}
		if (ObjectHelper.isEmpty(sb))
			return null;
		else
			return sb.toString();
	}

	/**
	 * ��Stringת��Clob
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static Clob stringToClob(String str) throws Exception {
		if (ObjectHelper.isEmpty(str))
			return null;

		return new SerialClob(str.toCharArray());
	}
}
