package com.zdsoft.framework.common.util;

import java.math.BigDecimal;

/**
 * ����/����ת��Util
 * ������/����תΪҳ����ʾ���� x%
 * ���߽�����/����תΪ���ݿ�洢����x.xxxx
 * @author hubenlai
 *
 */
public class RateUtil {

	private RateUtil() {
	}

	/**
	 * ������/����ת��Ϊ x%;������������*100(��ֵ����null)
	 * @param rate
	 */
	public static Double percentRate(Double rate) {
		return percentRate(rate, true);
	}

	/**������/����ת��Ϊ x%;������������*100
	 * @param nullable �Ƿ�ɿ�,false:��ֵ����0.00,true:��ֵ����:null
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
	 * ������/����ת��Ϊ x.xxxx;������������/100.(��ֵ����null)
	 * @param percentRate
	 */
	public static Double rate(Double percentRate) {
		return rate(percentRate, true);
	}

	/**
	 * ������/����ת��Ϊ x.xxxx;������������/100.
	 * @param percentRate
	 * @param nullable �Ƿ�ɿ�,false:��ֵ����0.00,true:��ֵ����:null
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
	 * ������/����ת��Ϊ x%;������������*10000(��ֵ����null)
	 * @param rate
	 * @return
	 */
	public static Double percentRate1(Double rate) {
		return percentRate1(rate, true);
	}

	/**
	 * ������/����ת��Ϊ x%;������������*10000
	 * @param rate
	 * @param nullable �Ƿ�ɿ�,false:��ֵ����0.00,true:��ֵ����:null
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
	 * ������/����ת��Ϊ x.xxxx;������������/10000(��ֵ����null)
	 * @param percentRate
	 * @return
	 */
	public static Double rate1(Double percentRate) {
		return rate1(percentRate, true);
	}

	/**
	 * ������/����ת��Ϊ x.xxxx;������������/10000
	 * @param percentRate
	 * @param nullable �Ƿ�ɿ�,false:��ֵ����0.00,true:��ֵ����:null
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
	 * ������/����ǧ�ֱ�ת��Ϊ x.xxxx;������������/1000(��ֵ����null)
	 * @param percentRate
	 * @return
	 */
	public static Double rate2(Double percentRate) {
		return rate1(percentRate, true);
	}

	/**
	 * ǧ�ֱȽ�ǧ�ֱ�����/����ת��Ϊ x.xxxx;������������/1000
	 * @param percentRate
	 * @param nullable �Ƿ�ɿ�,false:��ֵ����0.00,true:��ֵ����:null
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
	 * ������/����ǧ�ֱ�С��ת��Ϊ x%;������������*1000(��ֵ����null)
	 * @param rate
	 * @return
	 */
	public static Double percentRate2(Double rate) {
		return percentRate1(rate, true);
	}

	/**
	 * ������/����ǧ�ֱ�С��ת��Ϊ x%;������������*1000
	 * @param rate
	 * @param nullable �Ƿ�ɿ�,false:��ֵ����0.00,true:��ֵ����:null
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