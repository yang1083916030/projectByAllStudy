package com.zdsoft.framework.common.util;

import java.math.BigDecimal;

/**
 * 利率/比例转换Util
 * 将利率/比例转为页面显示数据 x%
 * 或者将利率/比例转为数据库存储数据x.xxxx
 * @author hubenlai
 *
 */
public class RateUtil {

	private RateUtil() {
	}

	/**
	 * 将利率/比例转换为 x%;即所传入数字*100(空值返回null)
	 * @param rate
	 */
	public static Double percentRate(Double rate) {
		return percentRate(rate, true);
	}

	/**将利率/比例转换为 x%;即所传入数字*100
	 * @param nullable 是否可空,false:空值返回0.00,true:空值返回:null
	 **/
	public static Double percentRate(Double rate, boolean nullable) {
		if (ObjectHelper.isEmpty(rate)) {
			return nullable ? null : 0.00;
		} else {
			BigDecimal bigRate = new BigDecimal(rate.toString());
			BigDecimal percent = new BigDecimal("100");
			return bigRate.multiply(percent).doubleValue();
		}
	}

	/**
	 * 将利率/比例转换为 x.xxxx;即所传入数字/100.(空值返回null)
	 * @param percentRate
	 */
	public static Double rate(Double percentRate) {
		return rate(percentRate, true);
	}

	/**
	 * 将利率/比例转换为 x.xxxx;即所传入数字/100.
	 * @param percentRate
	 * @param nullable 是否可空,false:空值返回0.00,true:空值返回:null
	 */
	public static Double rate(Double percentRate, boolean nullable) {
		if (ObjectHelper.isEmpty(percentRate)) {
			return nullable ? null : 0.00;
		} else {
			BigDecimal bigRate = new BigDecimal(percentRate.toString());
			BigDecimal percent = new BigDecimal("100");
			return bigRate.divide(percent).doubleValue();
		}
	}

	/**
	 * 将利率/比例转换为 x%;即所传入数字*10000(空值返回null)
	 * @param rate
	 * @return
	 */
	public static Double percentRate1(Double rate) {
		return percentRate1(rate, true);
	}

	/**
	 * 将利率/比例转换为 x%;即所传入数字*10000
	 * @param rate
	 * @param nullable 是否可空,false:空值返回0.00,true:空值返回:null
	 */
	public static Double percentRate1(Double rate, boolean nullable) {
		if (ObjectHelper.isEmpty(rate)) {
			return nullable ? null : 0.00;
		} else {
			BigDecimal bigRate = new BigDecimal(rate.toString());
			BigDecimal percent = new BigDecimal("10000");
			return bigRate.multiply(percent).doubleValue();
		}
	}

	/**
	 * 将利率/比例转换为 x.xxxx;即所传入数字/10000(空值返回null)
	 * @param percentRate
	 * @return
	 */
	public static Double rate1(Double percentRate) {
		return rate1(percentRate, true);
	}

	/**
	 * 将利率/比例转换为 x.xxxx;即所传入数字/10000
	 * @param percentRate
	 * @param nullable 是否可空,false:空值返回0.00,true:空值返回:null
	 */
	public static Double rate1(Double percentRate, boolean nullable) {
		if (ObjectHelper.isEmpty(percentRate)) {
			return nullable ? null : 0.00;
		} else {
			BigDecimal bigRate = new BigDecimal(percentRate.toString());
			BigDecimal percent = new BigDecimal("10000");
			return bigRate.divide(percent).doubleValue();
		}
	}

	/**
	 * 将利率/比例千分比转换为 x.xxxx;即所传入数字/1000(空值返回null)
	 * @param percentRate
	 * @return
	 */
	public static Double rate2(Double percentRate) {
		return rate1(percentRate, true);
	}

	/**
	 * 千分比将千分比利率/比例转换为 x.xxxx;即所传入数字/1000
	 * @param percentRate
	 * @param nullable 是否可空,false:空值返回0.00,true:空值返回:null
	 */
	public static Double rate2(Double percentRate, boolean nullable) {
		if (ObjectHelper.isEmpty(percentRate)) {
			return nullable ? null : 0.00;
		} else {
			BigDecimal bigRate = new BigDecimal(percentRate.toString());
			BigDecimal percent = new BigDecimal("1000");
			return bigRate.divide(percent).doubleValue();
		}
	}

	/**
	 * 将利率/比例千分比小数转换为 x%;即所传入数字*1000(空值返回null)
	 * @param rate
	 * @return
	 */
	public static Double percentRate2(Double rate) {
		return percentRate1(rate, true);
	}

	/**
	 * 将利率/比例千分比小数转换为 x%;即所传入数字*1000
	 * @param rate
	 * @param nullable 是否可空,false:空值返回0.00,true:空值返回:null
	 */
	public static Double percentRate2(Double rate, boolean nullable) {
		if (ObjectHelper.isEmpty(rate)) {
			return nullable ? null : 0.00;
		} else {
			BigDecimal bigRate = new BigDecimal(rate.toString());
			BigDecimal percent = new BigDecimal("1000");
			return bigRate.multiply(percent).doubleValue();
		}
	}
}