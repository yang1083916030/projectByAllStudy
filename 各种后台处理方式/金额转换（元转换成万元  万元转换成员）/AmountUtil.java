package com.zdsoft.framework.common.util;

import java.math.BigDecimal;

public class AmountUtil {
	
	private static BigDecimal amountUtil = new BigDecimal(10000);
	private AmountUtil(){}
	/**
	 * 元转万元(空值转换为null)
	 * @param d
	 */
	public static String doubleToString(Double d){
		return doubleToString(d,true);
	}
	/**
	 * 元转万元(空值转换为0.00或null由参数nullable确定)
	 * @param d
	 * @param nullable 是否可空,false:空值返回0.00,true:空值返回:null
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
	 * 万元转元(空值转换为null)
	 * @param s
	 */
	public static Double stringToDouble(String s){
		return stringToDouble(s,true);
	}
	/**
	 * 万元转元(空值转换为0.00或null由参数nullable确定)
	 * @param s
	 * @param nullable 是否可空,false:空值返回0.00,true:空值返回:null
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
	 * 元转万元(空值转换为null)
	 * @param d
	 */
	public static Double doubleEnlarge(Double d){
		return doubleEnlarge(d,true);
	}
	/**
	 * 元转万元(空值转换为0.00或null由参数nullable确定)
	 * @param d
	 * @param nullable 是否可空,false:空值返回0.00,true:空值返回:null
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
	 * 万元转元(空值转换为null)
	 * @param s
	 */
	public static Double doubleMicrify(Double d){
		return doubleMicrify(d,true);
	}
	/**
	 * 万元转元(空值转换为0.00或null由参数nullable确定)
	 * @param s
	 * @param nullable 是否可空,false:空值返回0.00,true:空值返回:null
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
	 * 元转万元(空值转换为null)
	 * @param d
	 */
	public static BigDecimal bigDecimalEnlarge(Double d){
		return bigDecimalEnlarge(d,true);
	}
	/**
	 * 元转万元(空值转换为0.00或null由参数nullable确定)
	 * @param d
	 * @param nullable 是否可空,false:空值返回0.00,true:空值返回:null
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
	 * 万元转元(空值转换为null)
	 * @param s
	 * @return
	 */
	public static BigDecimal bigDecimalMicrify(Double d){
		return bigDecimalMicrify(d,true);
	}
	/**
	 * 万元转元(空值转换为0.00或null由参数nullable确定)
	 * @param s
	 * @param nullable 是否可空,false:空值返回0.00,true:空值返回:null
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
	 * 元转万元(空值转换为null)
	 * @param d
	 * @return
	 */
	public static String bigDecimalToString(BigDecimal b){
		return bigDecimalToString(b,true);
	}
	/**
	 * 元转万元(空值转换为0.00或null由参数nullable确定)
	 * @param d
	 * @param nullable 是否可空,false:空值返回0.00,true:空值返回:null
	 */
	public static String bigDecimalToString(BigDecimal b,boolean nullable){
		if(ObjectHelper.isNotEmpty(b)){
			return b.divide(amountUtil).toString();
		}else{
			return nullable?null:"0.00";
		}
	}
	/**
	 * 万元转元(空值转换为null)
	 * @param s
	 * @return
	 */
	public static BigDecimal stringToBigDecimal(String s){
		return stringToBigDecimal(s,true);
	}
	/**
	 * 万元转元(空值转换为0.00或null由参数nullable确定)
	 * @param s
	 * @param nullable 是否可空,false:空值返回0.00,true:空值返回:null
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
	 * 元转万元(空值转换为null)
	 * @param b
	 * @return
	 */
	public static Double bigDecimalToDoulbeEnlarge(BigDecimal b){
		return bigDecimalToDoulbeEnlarge(b,true);
	}
	/**
	 * 元转万元(空值根据参数nullable判断是否返回null或者0.00)
	 * @param b
	 * @param nullable 是否可空,false:空值返回0.00,true:空值返回:null
	 */
	public static Double bigDecimalToDoulbeEnlarge(BigDecimal b,boolean nullable){
		if(ObjectHelper.isNotEmpty(b)){
			return b.divide(amountUtil).doubleValue();
		}else{
			return nullable?null:new Double(0.00);
		}
	}
	/**
	 * 万元转元(空值转换为null)
	 * @param b
	 */
	public static Double bigDecimalToDoulbeMicrify(BigDecimal b){
		return bigDecimalToDoulbeMicrify(b,true);
	}
	/**
	 * 万元转元(空值根据参数nullable判断是否返回null或者0.00)
	 * @param b
	 * @param nullable 是否可空,false:空值返回0.00,true:空值返回:null
	 */
	public static Double bigDecimalToDoulbeMicrify(BigDecimal b,boolean nullable){
		if(ObjectHelper.isNotEmpty(b)){
			return b.movePointRight(4).doubleValue();
		}else{
			return nullable?null:new Double(0.00);
		}
	}
	/**
	 * 元转万元(空值转换为null)
	 * @param d 需要转换的元
	 * @return
	 */
	public static BigDecimal bigDecimalEnlarge(BigDecimal b){
		return bigDecimalEnlarge(b,true);
	}
	/***
	 * * 元转万元(空值根据参数nullable判断是否返回null或者0.00)
	 * @param b
	 * @param nullable 是否可空,false:空值返回0.00,true:空值返回:null
	 */
	public static BigDecimal bigDecimalEnlarge(BigDecimal b,boolean nullable){
		if(ObjectHelper.isNotEmpty(b)){
			return b.divide(amountUtil);
		}else{
			return nullable?null:new BigDecimal(0.00);
		}
	}
	/**
	 * 万元转元(空值转换为null)
	 * @param s
	 * @return
	 */
	public static BigDecimal bigDecimalMicrify(BigDecimal b){
		return bigDecimalMicrify(b,true);
	}
	/**
	 * 万元转元(空值根据参数nullable判断是否返回null或者0.00)
	 * @param s
	 * @param nullable 是否可空,false:空值返回0.00,true:空值返回:null
	 */
	public static BigDecimal bigDecimalMicrify(BigDecimal b,boolean nullable){
		if(ObjectHelper.isNotEmpty(b)){
			return b.movePointRight(4);
		}else{
			return nullable?null:new BigDecimal(0.00);
		}
	}
}
