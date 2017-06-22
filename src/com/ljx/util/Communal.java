package com.ljx.util;

public final class Communal {
	/**
	 * �ж��ַ����Ƿ�������
	 */
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * �ж��ַ����Ƿ��Ǹ�����
	 */
	public static boolean isDouble(String value) {
		try {
			Double.parseDouble(value);
			if (value.contains("."))
				return true;
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * �ж��ַ����Ƿ�������
	 */
	public static boolean isNumber(String value) {
		return isInteger(value) || isDouble(value);
	}
}
