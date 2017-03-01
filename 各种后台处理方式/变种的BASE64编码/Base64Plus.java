package com.zdsoft.framework.common.util;
import java.io.ByteArrayOutputStream;

/**
 * ���ֵ�BASE64����(��Ϊurl�ṩר��BASE64���롢����)
 * @author zhaoke
 * 
 */
public class Base64Plus {

	private static final char[] base64EncodeChars = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };

	private static byte[] base64DecodeChars = new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
			41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1 };

	private static byte f1 = (byte) '+';

	private static byte f1_re = (byte) '!';

	private static byte f2 = (byte) '/';

	private static byte f2_re = (byte) '-';

	private Base64Plus() {
	}

	/**
	 * ��׼BASE64����
	 * 
	 * @param data
	 * @return
	 */
	public static String encode(byte[] data) {
		return innerEncode(data, false);
	}

	/**
	 * <p>
	 * ����BASE64���ܣ�������HTTP�����´��ݱ�ʶ��Ϣ��
	 * </p>
	 * <p style="color:red">
	 * ���������õķ���Ϊ��ĩβ������"="ռλ������"+"�滻��"!",��"/"�滻��"-"
	 * </p>
	 * <p>
	 * ��Ӧ�ó����У�������Ҫ�Ѷ��������ݱ���Ϊ�ʺϷ���URL(�������ر���)�е���ʽ����ʱ������Base64���벻���Ƚϼ�̣�ͬʱҲ���в��ɶ��ԣ�
	 * ������������ݲ��ᱻ����������ֱ�ӿ�����
	 * ����Ȼ������׼��Base64�����ʺ�ֱ�ӷ���URL�ﴫ�䣬��ΪURL��������ѱ�׼Base64�еġ�/����
	 * ��+���ַ���Ϊ���硰%XX������ʽ������Щ��%�����ڴ������ݿ�ʱ����Ҫ�ٽ���ת������ΪANSI SQL���ѽ���%��������ͨ�����
	 * </p>
	 * <p>
	 * Ϊ��������⣬����һ������URL�ĸĽ�Base64���룬������ĩβ���'='�ţ�������׼Base64�еġ�+���͡�/���ֱ�ĳ��ˡ�*���͡�-��
	 * ����������ȥ����URL���������ݿ�洢ʱ��Ҫ����ת���������˱�����Ϣ�����ڴ˹����е����ӣ���ͳһ�����ݿ⡢���ȴ������ʶ���ĸ�ʽ��
	 * ��������һ������������ʽ�ĸĽ�Base64����
	 * ��������+���͡�/���ĳ��ˡ�!���͡�-������Ϊ��+��,��*���Լ�ǰ����IRCu���õ��ġ�[���͡�]����������ʽ�ж����ܾ������⺬�塣
	 * �������⻹��һЩ���֣����ǽ���+/����Ϊ��_-����._����������������еı�ʶ�����ƣ���.-��������XML�е�Nmtoken��������_:����
	 * ����XML�е�Name��
	 * </p>
	 * ��
	 * 
	 * @param data
	 * @return
	 */
	public static String encodeForUrl(byte[] data) {
		return innerEncode(data, true);
	}

	private static String innerEncode(byte[] data, boolean forUrl) {

		StringBuilder sb = new StringBuilder();
		int len = data.length;
		int i = 0;
		int b1, b2, b3;
		// add by zhaoke
		int equalFlagCount = 0;
		// add by zhaoke

		while (i < len) {
			b1 = data[i++] & 0xff;
			if (i == len) {
				sb.append(base64EncodeChars[b1 >>> 2]);
				sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
				// modify by zhaoke
				if (forUrl) {
					equalFlagCount = 2;
				} else {
					sb.append("==");
				}
				// modify by zhaoke
				break;
			}
			b2 = data[i++] & 0xff;
			if (i == len) {
				sb.append(base64EncodeChars[b1 >>> 2]);
				sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
				sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
				// modify by zhaoke
				if (forUrl) {
					equalFlagCount = 1;
				} else {
					sb.append("=");
				}
				// modify by zhaoke
				break;
			}
			b3 = data[i++] & 0xff;
			sb.append(base64EncodeChars[b1 >>> 2]);
			sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
			sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
			sb.append(base64EncodeChars[b3 & 0x3f]);
		}

		// modify by zhaoke
		if (!forUrl) {
			return sb.toString();
		} else {
			sb.append("!");
			sb.append(equalFlagCount);
			String ret = sb.toString();
			ret = ret.replaceAll("=", "!");
			ret = ret.replaceAll("/", "-");
			return ret;
		}
		// modify by zhaoke
	}

	/**
	 * ��׼BASE64����<br/>
	 * <br/>
	 * <b style="color:red">ע�⣺�˺�����Ҫ��encode����ƥ��ʹ��</b>
	 * 
	 * @param str
	 *            �������ַ���
	 * @return
	 */
	public static byte[] decode(String str) {
		return innerDecode(str, false);
	}

	/**
	 * ר����url��BASE64����<br/>
	 * <br/>
	 * <b style="color:red">ע�⣺�˺�����Ҫ��encodeForUrl����ƥ��ʹ��</b>
	 * 
	 * @param str
	 *            �������ַ���
	 * @return
	 */
	public static byte[] decodeForUrl(String str) {
		return innerDecode(str, true);
	}

	private static byte[] innerDecode(String str, boolean forUrl) {
		byte[] data = null;
		if (forUrl) {
			int strLength = str.length();
			int equalFlagCount = Integer.parseInt("" + str.charAt(strLength - 1));
			StringBuilder cStr = new StringBuilder(str.substring(0, strLength - 2));
			for (int k = 0; k < equalFlagCount; ++k) {
				cStr.append("=");
			}

			data = cStr.toString().getBytes();
			int len = data.length;

			// �Ƚ�����λ����
			// ��ѭ��һ�齫"!","-"����"=","/"
			for (int j = 0; j < len; ++j) {
				if (f1_re == data[j]) {
					data[j] = f1;
					continue;
				}
				if (f2_re == data[j]) {
					data[j] = f2;
					continue;
				}
			}

		} else {
			data = str.getBytes();
		}

		int len = data.length;

		ByteArrayOutputStream buf = new ByteArrayOutputStream(len);
		int i = 0;
		int b1, b2, b3, b4;

		while (i < len) {

			/* b1 */
			do {
				b1 = base64DecodeChars[data[i++]];
			} while (i < len && b1 == -1);
			if (b1 == -1) {
				break;
			}

			/* b2 */
			do {
				b2 = base64DecodeChars[data[i++]];
			} while (i < len && b2 == -1);
			if (b2 == -1) {
				break;
			}
			buf.write((int) ((b1 << 2) | ((b2 & 0x30) >>> 4)));

			/* b3 */
			do {
				b3 = data[i++];
				if (b3 == 61) {
					return buf.toByteArray();
				}
				b3 = base64DecodeChars[b3];
			} while (i < len && b3 == -1);
			if (b3 == -1) {
				break;
			}
			buf.write((int) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));

			/* b4 */
			do {
				b4 = data[i++];
				if (b4 == 61) {
					return buf.toByteArray();
				}
				b4 = base64DecodeChars[b4];
			} while (i < len && b4 == -1);
			if (b4 == -1) {
				break;
			}
			buf.write((int) (((b3 & 0x03) << 6) | b4));
		}
		return buf.toByteArray();
	}
	
	/**
	 * ���ַ�����������ִ�
	 * 
	 * @param str
	 * @return
	 */
	public static String encodeDigit(String str) {
		byte[] by = str.getBytes();
		StringBuffer buffer = new StringBuffer(by.length);
		for (int i = 0; i < by.length; i++) {
			buffer.append((int) by[i] + 256);
		}
		return buffer.toString();

	}

	/**
	 * �����ִ���������ַ���
	 * 
	 * @param key
	 * @return
	 */
	public static String decodeDigit(String key) {
		String[] strs = new String[key.length() / 3];
		for (int i = 0; i < strs.length; i++) {
			strs[i] = key.substring(i * 3, i * 3 + 3);
		}
		int[] ints = new int[strs.length];
		for (int i = 0; i < ints.length; i++) {
			ints[i] = Integer.parseInt(strs[i]) - 256;
		}
		byte[] bys = new byte[ints.length];
		for (int i = 0; i < bys.length; i++) {
			bys[i] = (byte) ints[i];
		}
		return new String(bys);

	}

}
