package com.zdsoft.framework.common.util;

import java.math.BigDecimal;

public class AmountUtil {
	
	private static BigDecimal amountUtil = new BigDecimal(10000);
	private AmountUtil(){}
	/**
	 * Ԫת��Ԫ(��ֵת��Ϊnull)
	 * @param d
	 */
	public static String doubleToString(Double d){
		return doubleToString(d,true);
	}
	/**
	 * Ԫת��Ԫ(��ֵת��Ϊ0.00��null�ɲ���nullableȷ��)
	 * @param d
	 * @param nullable �Ƿ�ɿ�,false:��ֵ����0.00,true:��ֵ����:null
	 */
	public static String doubleToString(Double d,boolean nullable){
		if(ObjectHelper.isNotEmpty(d)){
			BigDecimal bigRate = new BigDecimal(d.toString());
			return bigRate.divide(amountUtil).toString();
		}else{
			return nullable?null:"0.00";
		}
	}
	/**
	 * ��ԪתԪ(��ֵת��Ϊnull)
	 * @param s
	 */
	public static Double stringToDouble(String s){
		return stringToDouble(s,true);
	}
	/**
	 * ��ԪתԪ(��ֵת��Ϊ0.00��null�ɲ���nullableȷ��)
	 * @param s
	 * @param nullable �Ƿ�ɿ�,false:��ֵ����0.00,true:��ֵ����:null
	 */
	public static Double stringToDouble(String s,boolean nullable){
		if(ObjectHelper.isNotEmpty(s)){
			BigDecimal bigRate = new BigDecimal(s);
			return bigRate.movePointRight(4).doubleValue();
		}else{
			return nullable?null:new Double(0.00);
		}
	}
	/**
	 * Ԫת��Ԫ(��ֵת��Ϊnull)
	 * @param d
	 */
	public static Double doubleEnlarge(Double d){
		return doubleEnlarge(d,true);
	}
	/**
	 * Ԫת��Ԫ(��ֵת��Ϊ0.00��null�ɲ���nullableȷ��)
	 * @param d
	 * @param nullable �Ƿ�ɿ�,false:��ֵ����0.00,true:��ֵ����:null
	 */
	public static Double doubleEnlarge(Double d,boolean nullable){
		if(ObjectHelper.isNotEmpty(d)){
			BigDecimal bigRate = new BigDecimal(d.toString());
			return bigRate.divide(amountUtil).doubleValue();
		}else{
			return nullable?null:new Double(0.00);
		}
	}
	/**
	 * ��ԪתԪ(��ֵת��Ϊnull)
	 * @param s
	 */
	public static Double doubleMicrify(Double d){
		return doubleMicrify(d,true);
	}
	/**
	 * ��ԪתԪ(��ֵת��Ϊ0.00��null�ɲ���nullableȷ��)
	 * @param s
	 * @param nullable �Ƿ�ɿ�,false:��ֵ����0.00,true:��ֵ����:null
	 */
	public static Double doubleMicrify(Double d,boolean nullable){
		if(ObjectHelper.isNotEmpty(d)){
			BigDecimal bigRate = new BigDecimal(d.toString());
			return bigRate.movePointRight(4).doubleValue();
		}else{
			return nullable?null:new Double(0.00);
		}
	}
	/**
	 * Ԫת��Ԫ(��ֵת��Ϊnull)
	 * @param d
	 */
	public static BigDecimal bigDecimalEnlarge(Double d){
		return bigDecimalEnlarge(d,true);
	}
	/**
	 * Ԫת��Ԫ(��ֵת��Ϊ0.00��null�ɲ���nullableȷ��)
	 * @param d
	 * @param nullable �Ƿ�ɿ�,false:��ֵ����0.00,true:��ֵ����:null
	 */
	public static BigDecimal bigDecimalEnlarge(Double d,boolean nullable){
		if(ObjectHelper.isNotEmpty(d)){
			BigDecimal bigRate = new BigDecimal(d.toString());
			return bigRate.divide(amountUtil);
		}else{
			return nullable?null:new BigDecimal(0.00);
		}
	}
	/**
	 * ��ԪתԪ(��ֵת��Ϊnull)
	 * @param s
	 * @return
	 */
	public static BigDecimal bigDecimalMicrify(Double d){
		return bigDecimalMicrify(d,true);
	}
	/**
	 * ��ԪתԪ(��ֵת��Ϊ0.00��null�ɲ���nullableȷ��)
	 * @param s
	 * @param nullable �Ƿ�ɿ�,false:��ֵ����0.00,true:��ֵ����:null
	 */
	public static BigDecimal bigDecimalMicrify(Double d,boolean nullable){
		if(ObjectHelper.isNotEmpty(d)){
			BigDecimal bigRate = new BigDecimal(d.toString());
			return bigRate.movePointRight(4);
		}else{
			return nullable?null:new BigDecimal(0.00);
		}
	}
	/**
	 * Ԫת��Ԫ(��ֵת��Ϊnull)
	 * @param d
	 * @return
	 */
	public static String bigDecimalToString(BigDecimal b){
		return bigDecimalToString(b,true);
	}
	/**
	 * Ԫת��Ԫ(��ֵת��Ϊ0.00��null�ɲ���nullableȷ��)
	 * @param d
	 * @param nullable �Ƿ�ɿ�,false:��ֵ����0.00,true:��ֵ����:null
	 */
	public static String bigDecimalToString(BigDecimal b,boolean nullable){
		if(ObjectHelper.isNotEmpty(b)){
			return b.divide(amountUtil).toString();
		}else{
			return nullable?null:"0.00";
		}
	}
	/**
	 * ��ԪתԪ(��ֵת��Ϊnull)
	 * @param s
	 * @return
	 */
	public static BigDecimal stringToBigDecimal(String s){
		return stringToBigDecimal(s,true);
	}
	/**
	 * ��ԪתԪ(��ֵת��Ϊ0.00��null�ɲ���nullableȷ��)
	 * @param s
	 * @param nullable �Ƿ�ɿ�,false:��ֵ����0.00,true:��ֵ����:null
	 */
	public static BigDecimal stringToBigDecimal(String s,boolean nullable){
		if(ObjectHelper.isNotEmpty(s)){
			BigDecimal bigRate = new BigDecimal(s);
			return bigRate.movePointRight(4);
		}else{
			return nullable?null:new BigDecimal(0.00);
		}
	}
	/**
	 * Ԫת��Ԫ(��ֵת��Ϊnull)
	 * @param b
	 * @return
	 */
	public static Double bigDecimalToDoulbeEnlarge(BigDecimal b){
		return bigDecimalToDoulbeEnlarge(b,true);
	}
	/**
	 * Ԫת��Ԫ(��ֵ���ݲ���nullable�ж��Ƿ񷵻�null����0.00)
	 * @param b
	 * @param nullable �Ƿ�ɿ�,false:��ֵ����0.00,true:��ֵ����:null
	 */
	public static Double bigDecimalToDoulbeEnlarge(BigDecimal b,boolean nullable){
		if(ObjectHelper.isNotEmpty(b)){
			return b.divide(amountUtil).doubleValue();
		}else{
			return nullable?null:new Double(0.00);
		}
	}
	/**
	 * ��ԪתԪ(��ֵת��Ϊnull)
	 * @param b
	 */
	public static Double bigDecimalToDoulbeMicrify(BigDecimal b){
		return bigDecimalToDoulbeMicrify(b,true);
	}
	/**
	 * ��ԪתԪ(��ֵ���ݲ���nullable�ж��Ƿ񷵻�null����0.00)
	 * @param b
	 * @param nullable �Ƿ�ɿ�,false:��ֵ����0.00,true:��ֵ����:null
	 */
	public static Double bigDecimalToDoulbeMicrify(BigDecimal b,boolean nullable){
		if(ObjectHelper.isNotEmpty(b)){
			return b.movePointRight(4).doubleValue();
		}else{
			return nullable?null:new Double(0.00);
		}
	}
	/**
	 * Ԫת��Ԫ(��ֵת��Ϊnull)
	 * @param d ��Ҫת����Ԫ
	 * @return
	 */
	public static BigDecimal bigDecimalEnlarge(BigDecimal b){
		return bigDecimalEnlarge(b,true);
	}
	/***
	 * * Ԫת��Ԫ(��ֵ���ݲ���nullable�ж��Ƿ񷵻�null����0.00)
	 * @param b
	 * @param nullable �Ƿ�ɿ�,false:��ֵ����0.00,true:��ֵ����:null
	 */
	public static BigDecimal bigDecimalEnlarge(BigDecimal b,boolean nullable){
		if(ObjectHelper.isNotEmpty(b)){
			return b.divide(amountUtil);
		}else{
			return nullable?null:new BigDecimal(0.00);
		}
	}
	/**
	 * ��ԪתԪ(��ֵת��Ϊnull)
	 * @param s
	 * @return
	 */
	public static BigDecimal bigDecimalMicrify(BigDecimal b){
		return bigDecimalMicrify(b,true);
	}
	/**
	 * ��ԪתԪ(��ֵ���ݲ���nullable�ж��Ƿ񷵻�null����0.00)
	 * @param s
	 * @param nullable �Ƿ�ɿ�,false:��ֵ����0.00,true:��ֵ����:null
	 */
	public static BigDecimal bigDecimalMicrify(BigDecimal b,boolean nullable){
		if(ObjectHelper.isNotEmpty(b)){
			return b.movePointRight(4);
		}else{
			return nullable?null:new BigDecimal(0.00);
		}
	}
}
