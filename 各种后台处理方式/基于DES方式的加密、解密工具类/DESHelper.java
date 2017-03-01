package com.zdsoft.framework.common.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @���ܣ�����DES��ʽ�ļ��ܡ����ܹ�����
 * @���ߣ�ӡ�ʸ�
 * @�������ڣ�2004-4-26
 */
public class DESHelper {
	Logger logger = LoggerFactory.getLogger(DESHelper.class);

	// DES��Կ�ַ���
	private static final String strDefaultKey = "Copyright By ZDSOFT Corp. @2012";

	// ���ܶ���
	private Cipher encryptCipher = null;

	// ���ܶ���
	private Cipher decryptCipher = null;

	/**
	 * DES���췽��
	 * 
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("restriction")
	private DESHelper(String strkey) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		String strKey = strkey;
		Key key = getKey(strKey.getBytes());
		encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);
		decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
	}

	/**
	 * ����Ĭ����Կ��ȡһ��DESPlus����
	 * 
	 * @return DESPlus -DESPlus����
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws Exception
	 */
	public static DESHelper getInstance() throws InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException {
		return new DESHelper(strDefaultKey);
	}

	/**
	 * ʹ���ⲿ��Կ����DESPlus����
	 * 
	 * @param key
	 *            ��Կ
	 * @return DESPlus����
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws Exception
	 */
	public static DESHelper getInstance(String key) throws InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException {
		return new DESHelper(key);
	}

	/**
	 * ��ָ���ַ���������Կ����Կ������ֽ����鳤��Ϊ8λ ����8λʱ���油0������8λֻȡǰ8λ
	 * 
	 * @param arrBTmp
	 *            -���ɸ��ַ������ֽ�����
	 * @return Key -���ɵ���Կ
	 * @throws Exception
	 */
	private Key getKey(byte[] arrBTmp) {
		// ����һ���յ�8λ�ֽ����飨Ĭ��ֵΪ0��
		byte[] arrB = new byte[8];
		// ��ԭʼ�ֽ�����ת��Ϊ8λ
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		// ������Կ
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
		return key;
	}

	/**
	 * ��byte����ת��Ϊ��ʾ16����ֵ���ַ����� �磺byte[]{8,18}ת��Ϊ��0813�� ��private byte[]
	 * hexStr2ByteArr(String strIn) ��Ϊ�����ת������
	 * 
	 * @param arrB
	 *            -��Ҫת����byte����
	 * @return String -ת������ַ���
	 * @throws Exception
	 */
	private String byteArr2HexStr(byte[] arrB) {
		int iLen = arrB.length;
		// ÿ��byte�������ַ����ܱ�ʾ�������ַ����ĳ��������鳤�ȵ�����
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// �Ѹ���ת��Ϊ����
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// С��0F������Ҫ��ǰ�油0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	/**
	 * ����ʾ16����ֵ���ַ���ת��Ϊbyte���飬 ��private String byteArr2HexStr(byte[] arrB)
	 * ��Ϊ�����ת������
	 * 
	 * @param strIn
	 *            -��Ҫת�����ַ���
	 * @return byte[] -ת�����byte����
	 * @throws Exception
	 */
	private byte[] hexStr2ByteArr(String strIn) {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		// �����ַ���ʾһ���ֽڣ������ֽ����鳤�����ַ������ȳ���2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	/**
	 * �����ֽ�����
	 * 
	 * @param arrB
	 *            -����ܵ��ֽ�����
	 * @return byte[] -���ܺ���ֽ�����
	 * @throws BadPaddingException
	 * @throw IllegalBlockSizeException
	 * @throws Exception
	 */
	public byte[] encrypt(byte[] arrB) throws IllegalBlockSizeException,
			BadPaddingException {
		return encryptCipher.doFinal(arrB);
	}

	/**
	 * �����ַ���
	 * 
	 * @param strIn
	 *            -����ܵ��ַ���
	 * @return String -���ܺ���ַ���
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws Exception
	 */
	public String encrypt(String strIn) throws IllegalBlockSizeException,
			BadPaddingException {
		
		if (ObjectHelper.isEmpty(strIn))
			return null;
		return byteArr2HexStr(encrypt(strIn.getBytes()));
	}

	/**
	 * �����ֽ�����
	 * 
	 * @param arrB
	 *            -����ܵ��ֽ�����
	 * @return byte[] -���ܺ���ֽ�����
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws Exception
	 */
	public byte[] decrypt(byte[] arrB) throws IllegalBlockSizeException,
			BadPaddingException {
		return decryptCipher.doFinal(arrB);
	}

	/**
	 * �����ַ���
	 * 
	 * @param strIn
	 *            -����ܵ��ַ���
	 * @return String -���ܺ���ַ���
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws Exception
	 */
	public String decrypt(String strIn) throws IllegalBlockSizeException,
			BadPaddingException {
		if (ObjectHelper.isEmpty(strIn))
			return null;
		return new String(decrypt(hexStr2ByteArr(strIn)));
	}
//	
//	public static void main(String[] args) throws Exception {
//		System.out.println(DESHelper.getInstance("488").decrypt("504107e1544fa1fa"));
//	}

}