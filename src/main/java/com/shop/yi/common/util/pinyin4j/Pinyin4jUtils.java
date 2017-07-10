package com.shop.yi.common.util.pinyin4j;

import java.util.HashSet;
import java.util.Set;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @ClassName: pinyin4j
 * @Description: TODO 中文转汉语拼音类库
 * @author: yanguo(****@email.com)
 * @date: 2017年5月29日 下午8:58:40
 * @Version: 1.0.0
 */
public class Pinyin4jUtils {
	/**
	 * 字符串集合转换字符串(逗号分隔)
	 * @author wyh
	 * @param stringSet
	 * @return
	 */
	public static String makeStringByStringSet(Set<String> stringSet) {
		StringBuilder str = new StringBuilder();
		int i = 0;
		for (String s : stringSet) {
			if (i == stringSet.size() - 1) {
				str.append(s);
			} else {
				str.append(s + ",");
			}
			i++;
		}
		return str.toString().toLowerCase();
	}
	/**
	 * 获取拼音集合
	 * @author wyh
	 * @param src
	 * @return Set<String>
	 */
	public static Set<String> getPinyin(String src) {
		if (src != null && !src.trim().equalsIgnoreCase("")) {
			char[] srcChar;
			srcChar = src.toCharArray();
			// 汉语拼音格式输出类
			HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();
			// 输出设置，大小写，音标方式等
			hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
			hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
			String[][] temp = new String[src.length()][];
			for (int i = 0; i < srcChar.length; i++) {
				char c = srcChar[i];
				// 是中文或者a-z或者A-Z转换拼音(我的需求，是保留中文或者a-z或者A-Z)
				if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")) {
					try {
						temp[i] = PinyinHelper.toHanyuPinyinStringArray(srcChar[i], hanYuPinOutputFormat);
					} catch (BadHanyuPinyinOutputFormatCombination e) {
						e.printStackTrace();
					}
				} else if (((int) c >= 65 && (int) c <= 90) || ((int) c >= 97 && (int) c <= 122)) {
					temp[i] = new String[] { String.valueOf(srcChar[i]) };
				} else {
					temp[i] = new String[] { "" };
				}
			}
			String[] pingyinArray = Exchange(temp);
			Set<String> pinyinSet = new HashSet<String>();
			for (int i = 0; i < pingyinArray.length; i++) {
				pinyinSet.add(pingyinArray[i]);
			}
			return pinyinSet;
		}
		return null;
	}
	/**
	 * 递归
	 * @author wyh
	 * @param strJaggedArray
	 * @return
	 */
	public static String[] Exchange(String[][] strJaggedArray) {
		String[][] temp = DoExchange(strJaggedArray);
		return temp[0];
	}
	/**
	 * 递归
	 * @author wyh
	 * @param strJaggedArray
	 * @return
	 */
	private static String[][] DoExchange(String[][] strJaggedArray) {
		int len = strJaggedArray.length;
		if (len >= 2) {
			int len1 = strJaggedArray[0].length;
			int len2 = strJaggedArray[1].length;
			int newlen = len1 * len2;
			String[] temp = new String[newlen];
			int Index = 0;
			for (int i = 0; i < len1; i++) {
				for (int j = 0; j < len2; j++) {
					temp[Index] = strJaggedArray[0][i] + strJaggedArray[1][j];
					Index++;
				}
			}
			String[][] newArray = new String[len - 1][];
			for (int i = 2; i < len; i++) {
				newArray[i - 1] = strJaggedArray[i];
			}
			newArray[0] = temp;
			return DoExchange(newArray);
		} else {
			return strJaggedArray;
		}
	}
	/**
	 * 得到 全拼
	 * @param src
	 * @return
	 */
	public static String getFullSpell(String src) {
		char[] t1 = null;
		t1 = src.toCharArray();
		String[] t2 = new String[t1.length];
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		String t4 = "";
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; i++) {
				// 判断是否为汉字字符
				if (java.lang.Character.toString(t1[i]).matches("[\u4e00-\u9fa5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
					t4 += t2[0];
				} else {
					t4 += java.lang.Character.toString(t1[i]);
				}
			}
			return t4;
		} catch (BadHanyuPinyinOutputFormatCombination e1) {
			e1.printStackTrace();
		}
		return t4;
	}
	/**
	 * 得到中文首字母
	 * @param str
	 * @return
	 */
	public static String getPinYinHeadChar(String str) {
		String convert = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert += pinyinArray[0].charAt(0);
			} else {
				convert += word;
			}
		}
		return convert;
	}
	/**
	 * 将字符串转移为ASCII码
	 * @param cnStr
	 * @return
	 */
	public static String getCnASCII(String cnStr) {
		StringBuffer strBuf = new StringBuffer();
		byte[] bGBK = cnStr.getBytes();
		for (int i = 0; i < bGBK.length; i++) {
			// System.out.println(Integer.toHexString(bGBK[i]&0xff));
			strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
		}
		return strBuf.toString();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "单田芳";
		System.out.println(makeStringByStringSet(getPinyin(str)));
		// long currentTime=System.currentTimeMillis();
		// String cnStr = "丁叉基酜内酯";
		// System.out.println(getPingYin(cnStr));
		// System.out.println(getPinYinHeadChar(cnStr));
		// System.out.println(System.currentTimeMillis()-currentTime);
	}
}
