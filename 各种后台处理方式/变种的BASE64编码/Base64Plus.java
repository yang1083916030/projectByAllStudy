package com.zdsoft.framework.common.util;
import java.io.ByteArrayOutputStream;

/**
 * 变种的BASE64编码(如为url提供专门BASE64编码、解码)
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
	 * 标准BASE64加密
	 * 
	 * @param data
	 * @return
	 */
	public static String encode(byte[] data) {
		return innerEncode(data, false);
	}

	/**
	 * <p>
	 * 变种BASE64加密，用于在HTTP环境下传递标识信息。
	 * </p>
	 * <p style="color:red">
	 * 本函数采用的方法为：末尾不添置"="占位符，将"+"替换成"!",将"/"替换成"-"
	 * </p>
	 * <p>
	 * 在应用程序中，常常需要把二进制数据编码为适合放在URL(包括隐藏表单域)中的形式。此时，采用Base64编码不仅比较简短，同时也具有不可读性，
	 * 即所编码的数据不会被人用肉眼所直接看到。
	 * 　　然而，标准的Base64并不适合直接放在URL里传输，因为URL编码器会把标准Base64中的“/”和
	 * “+”字符变为形如“%XX”的形式，而这些“%”号在存入数据库时还需要再进行转换，因为ANSI SQL中已将“%”号用作通配符。
	 * </p>
	 * <p>
	 * 为解决此问题，采用一种用于URL的改进Base64编码，它不在末尾填充'='号，并将标准Base64中的“+”和“/”分别改成了“*”和“-”
	 * ，这样就免去了在URL编解码和数据库存储时所要作的转换，避免了编码信息长度在此过程中的增加，并统一了数据库、表单等处对象标识符的格式。
	 * 　　另有一种用于正则表达式的改进Base64变种
	 * ，它将“+”和“/”改成了“!”和“-”，因为“+”,“*”以及前面在IRCu中用到的“[”和“]”在正则表达式中都可能具有特殊含义。
	 * 　　此外还有一些变种，它们将“+/”改为“_-”或“._”（用作编程语言中的标识符名称）或“.-”（用于XML中的Nmtoken）甚至“_:”（
	 * 用于XML中的Name）
	 * </p>
	 * 　
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
	 * 标准BASE64解密<br/>
	 * <br/>
	 * <b style="color:red">注意：此函数需要与encode函数匹配使用</b>
	 * 
	 * @param str
	 *            待解密字符串
	 * @return
	 */
	public static byte[] decode(String str) {
		return innerDecode(str, false);
	}

	/**
	 * 专用于url的BASE64解密<br/>
	 * <br/>
	 * <b style="color:red">注意：此函数需要与encodeForUrl函数匹配使用</b>
	 * 
	 * @param str
	 *            待解密字符串
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

			// 先将后两位换成
			// 先循环一遍将"!","-"换成"=","/"
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
	 * 将字符串编码成数字窜
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
	 * 将数字串反编码成字符串
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
