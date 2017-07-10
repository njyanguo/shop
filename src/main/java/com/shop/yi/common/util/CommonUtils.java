package com.shop.yi.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class CommonUtils {
	/**
	 * 防止sql注入
	 * @param sql
	 * @return
	 */
	public final static String filterSQLInjection(String sql) {
		if (sql == null || "".equals(sql)) {
			return "";
		}
		try {
			sql = sql.trim().replaceAll("</?[s,S][c,C][r,R][i,I][p,P][t,T]>?", "");// script
			sql = sql.trim().replaceAll("[a,A][l,L][e,E][r,R][t,T]\\(", "").replace("\"", "");// alert
			sql = sql.trim().replace("\\.swf", "").replaceAll("\\.htc", "");
			sql = sql.trim().replace("\\.php\\b", "").replaceAll("\\.asp\\b", "");
			sql = sql.trim().replace("document\\.", "").replaceAll("[e,E][v,V][a,A][l,L]\\(", "");
			sql = sql.trim().replaceAll("'", "").replaceAll(">", "");
			sql = sql.trim().replaceAll("<", "").replaceAll("=", "");
			sql = sql.trim().replaceAll(" [o,O][r,R]", "");
			sql = sql.trim().replaceAll("etc/", "").replaceAll("cat ", "");
			sql = sql.trim().replaceAll("/passwd ", "");
			sql = sql.trim().replaceAll("sleep\\(", "").replaceAll("limit ", "").replaceAll("LIMIT ", "");
			sql = sql.trim().replaceAll("[d,D][e,E][l,L][e,E][t,T][e,E] ", "");// delete
			sql = sql.trim().replaceAll("[s,S][e,E][l,L][e,E][c,C][t,T] ", "");// select;
			sql = sql.trim().replaceAll("[u,U][p,P][d,D][a,A][t,T][e,E] ", "");// update
			sql = sql.trim().replaceAll("[d,D][e,E][l,L][a,A][y,Y] ", "").replaceAll("waitfor ", "");
			sql = sql.trim().replaceAll("print\\(", "").replaceAll("md5\\(", "");
			sql = sql.trim().replaceAll("cookie\\(", "").replaceAll("send\\(", "");
			sql = sql.trim().replaceAll("response\\.", "").replaceAll("write\\(", "").replaceAll("&", "");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return sql;
	}
}
/**
 * 
 * @ClassName: Arith 
 * @Description: TODO BigDecimal的操作
 * @author: yanguo(****@email.com)
 * @date: 2017年5月29日 下午9:12:14  
 * @Version: 1.0.0
 */
class Arith {
	/**
	 * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精 确的浮点数运算，包括加减乘除和四舍五入。
	 */
	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;

	// 这个类不能实例化
	private Arith() {
	}
	/**
	 * 提供精确的加法运算。
	 * @param v1 被加数
	 * @param v2 加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	/**
	 * 提供精确的减法运算。
	 * @param v1 被减数
	 * @param v2 减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	/**
	 * 提供精确的乘法运算。
	 * @param v1 被乘数
	 * @param v2 乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * @param v1 被除数
	 * @param v2 除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}
	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * @param v1 被除数
	 * @param v2 除数
	 * @param scale 表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 提供精确的小数位四舍五入处理。
	 * @param v 需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 提供精确的类型转换(Float)
	 * @param v 需要被转换的数字
	 * @return 返回转换结果
	 */
	public static float convertsToFloat(double v) {
		BigDecimal b = new BigDecimal(v);
		return b.floatValue();
	}
	/**
	 * 提供精确的类型转换(Int)不进行四舍五入
	 * @param v 需要被转换的数字
	 * @return 返回转换结果
	 */
	public static int convertsToInt(double v) {
		BigDecimal b = new BigDecimal(v);
		return b.intValue();
	}
	/**
	 * 提供精确的类型转换(Long)
	 * @param v 需要被转换的数字
	 * @return 返回转换结果
	 */
	public static long convertsToLong(double v) {
		BigDecimal b = new BigDecimal(v);
		return b.longValue();
	}
	/**
	 * 返回两个数中大的一个值
	 * @param v1 需要被对比的第一个数
	 * @param v2 需要被对比的第二个数
	 * @return 返回两个数中大的一个值
	 */
	public static double returnMax(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.max(b2).doubleValue();
	}
	/**
	 * 返回两个数中小的一个值
	 * @param v1 需要被对比的第一个数
	 * @param v2 需要被对比的第二个数
	 * @return 返回两个数中小的一个值
	 */
	public static double returnMin(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.min(b2).doubleValue();
	}
	/**
	 * 精确对比两个数字
	 * @param v1 需要被对比的第一个数
	 * @param v2 需要被对比的第二个数
	 * @return 如果两个数一样则返回0，如果第一个数比第二个数大则返回1，反之返回-1
	 */
	public static int compareTo(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.compareTo(b2);
	}
}

/**
 * 
 * @ClassName: ChinaNumber 
 * @Description: TODO  金额转中文大写
 * @author: yanguo(****@email.com)
 * @date: 2017年5月29日 下午9:11:59  
 * @Version: 1.0.0
 */
class ChinaNumber {
	private static String[] CH = { "", "", "拾", "佰", "仟" };
	private static String[] CHS_NUMBER = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
	private static String[] CHS = { "万", "亿", "兆" };
	private static DecimalFormat df = new DecimalFormat("#########################0.0#");

	/**
	 * 传入数字金额双精度型值，返回数字金额对应的中文大字与读法
	 * @param money 金额
	 * @return 金额中文大写
	 */
	public static String transFormMoney(double money) {
		return transFormMoney(df.format(money));
	}
	/**
	 * 传入数字金额浮点型值，返回数字金额对应的中文大字与读法
	 * @param money 金额
	 * @return 金额中文大写
	 */
	public static String transFormMoney(float money) {
		return transFormMoney(df.format(money));
	}
	/**
	 * 传入数字金额字符串，返回数字金额对应的中文大字与读法
	 * @param money 金额字符串
	 * @return 金额中文大写
	 */
	public static String transFormMoney(String money) {
		String result = "";
		try {
			BigDecimal big = new BigDecimal(money);
			String[] t = null;
			try {
				t = big.toString().replace(".", ";").split(";");
			} catch (Exception e) {
				// 金额如果没有小数位时,也要加上小数位
				t = (big.toString() + ".0").replace(".", ";").split(";");
			}
			String[] intString = splitMoney(t[0]);
			String tmp_down = t[1];
			for (int i = 0; i < intString.length; i++) {
				result = result + count(intString[i]);
				if (i == intString.length - 2 || i == intString.length - 3)
					continue;
				if (i != intString.length - 1) {
					result = result + CHS[intString.length - 2 - i];
				}
			}
			if (Integer.parseInt(tmp_down) == 0) {
				result = result + (intString[0].equals("0") ? "零元" : "元整");
			} else {
				result = result + (intString[0].equals("0") ? "" : tmp_down.startsWith("0") ? "元零" : "元")
						+ getFloat(tmp_down);
			}
		} catch (Exception e) {
			return "你輸入的不是數字符串";
		}
		return result;
	}
	/**
	 * 对整数部分字符串进行每四位分割放置分割符
	 * @param money 整数部分字符串
	 * @return 放置分割符后的字符串
	 */
	public static String[] splitMoney(String money) {
		StringBuffer tmp_int = new StringBuffer();
		tmp_int.append(money);
		// 先對整數位進行分割，每四位爲一組。
		int i = tmp_int.length();
		do {
			try {
				// 進行try..catch是因爲當位數不能滿足每四位放分割符時，就退出循環
				i = i - 4;
				if (i == 0)
					break;
				tmp_int.insert(i, ';');
			} catch (Exception e) {
				break;
			}
		} while (true);
		return tmp_int.toString().split(";");
	}
	/**
	 * 转换整数部分
	 * @param money 整数部分金额
	 * @return 整数部分大写
	 */
	public static String count(String money) {
		String tmp = "";
		Integer money_int = 0;
		char[] money_char;
		// 如果數字開始是零時
		if (money.startsWith("0")) {
			money_int = Integer.parseInt(money);
			if (money_int == 0)
				return tmp;
			else
				tmp = "零";
			money_char = money_int.toString().toCharArray();
		} else {
			money_char = money.toCharArray();
		}
		for (int i = 0; i < money_char.length; i++) {
			if (money_char[i] != '0') {
				// 如果當前位不爲“0”，才可以進行數字和位數轉換
				tmp = tmp + CHS_NUMBER[Integer.parseInt(money_char[i] + "")] + CH[money_char.length - i];
			} else {
				// 要想該位轉換爲零，要滿足三個條件
				// 1.上一位沒有轉換成零，2.該位不是最後一位，3.該位的下一位不能爲零
				if (!tmp.endsWith("零") && i != money_char.length - 1 && money_char[i + 1] != '0') {
					tmp = tmp + CHS_NUMBER[Integer.parseInt(money_char[i] + "")];
				}
			}
		}
		return tmp;
	}
	/**
	 * 转换小数部分
	 * @param fl 小数
	 * @return 小数大写
	 */
	private static String getFloat(String fl) {
		String f = "";
		char[] ch = fl.toCharArray();
		switch (ch.length) {
			case 1: {
				f = f + CHS_NUMBER[Integer.parseInt(ch[0] + "")] + "角整";
				break;
			}
			case 2: {
				if (ch[0] != '0')
					f = f + CHS_NUMBER[Integer.parseInt(ch[0] + "")] + "角" + CHS_NUMBER[Integer.parseInt(ch[1] + "")]
							+ "分";
				else
					f = f + CHS_NUMBER[Integer.parseInt(ch[1] + "")] + "分";
				break;
			}
		}
		return f;
	}
	public static void main(String[] args) {
		System.out.println(transFormMoney(1000000000000.0232));
		System.out.println(transFormMoney(18493847575.0232));
		System.out.println(transFormMoney(1844237575.02f));
		System.out.println(transFormMoney("18493475.02"));
		System.out.println(transFormMoney("0.02"));
		System.out.println(transFormMoney("0.2"));
	}
}
