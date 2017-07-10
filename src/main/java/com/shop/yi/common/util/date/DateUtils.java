package com.shop.yi.common.util.date;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

public final class DateUtils {
	/**
	 * yyyy-MM-dd
	 */
	public static final String FORMAT1 = "yyyy-MM-dd";
	/**
	 * yyyy.MM.dd
	 */
	public static final String FORMAT2 = "yyyy.MM.dd";
	/**
	 * yyyy/MM/dd
	 */
	public static final String FORMAT3 = "yyyy/MM/dd";
	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static final String FORMAT4 = "yyyy-MM-dd HH:mm";
	/**
	 * yyyy.MM.dd HH:mm
	 */
	public static final String FORMAT5 = "yyyy.MM.dd HH:mm";
	/**
	 * yyyy/MM/dd HH:mm
	 */
	public static final String FORMAT6 = "yyyy/MM/dd HH:mm";
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String FORMAT7 = "yyyy-MM-dd HH:mm:ss";
	/**
	 * YYYY-MM-dd HH-mm-ss
	 */
	public static final String FORMAT15 = "YYYY-MM-dd HH-mm-ss";
	/**
	 * yyyy.MM.dd HH:mm:ss
	 */
	public static final String FORMAT8 = "yyyy.MM.dd HH:mm:ss";
	/**
	 * yyyy/MM/dd HH:mm:ss
	 */
	public static final String FORMAT9 = "yyyy/MM/dd HH:mm:ss";
	/**
	 * yyyy_MM_dd_HH_mm_ss
	 */
	public static final String FORMAT10 = "yyyy_MM_dd_HH_mm_ss";
	/**
	 * yy-MM-dd
	 */
	public static final String FORMAT11 = "yy-MM-dd";
	/**
	 * yyyyMMdd
	 */
	public static final String FORMAT12 = "yyyyMMdd";
	/**
	 * yyyyMMddHHmmss
	 */
	public static final String FORMAT13 = "yyyyMMddHHmmss";
	/**
	 * yyyyMM
	 */
	public static final String FORMAT14 = "yyyyMM";

	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}
	public static Date getYesterDay() {
		return addDay(new Date(System.currentTimeMillis()), -1);
	}
	public static String getYesterDayString() {
		return parseDateToString(addDay(new Date(System.currentTimeMillis()), -1), FORMAT1);
	}
	/**
	 * 得到年月日的路径
	 * @return
	 */
	public static String getThisYearMonthDay(String dateString) {
		Date date = parseStringToDate(dateString, FORMAT12);
		return DateUtils.getYear(date) + "/" + DateUtils.getMonth(date) + "/" + DateUtils.getDay(date) + "/";
	}
	/**
	 * 返回当前日期或时间
	 * @param format
	 * @return
	 */
	public static String getCurrentDate(String format) {
		if (StringUtils.isBlank(format)) {
			format = FORMAT1;
		}
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String currentTime = formatter.format(date);
		return currentTime;
	}
	/**
	 * 返回当前时间
	 * @return
	 */
	public static String getCurrentTime() {
		String datetime = getCurrentDate(FORMAT7);
		String time = datetime.substring(datetime.indexOf(" ") + 1);
		return time;
	}
	/**
	 * 返回当前时间加随机数
	 * @return
	 */
	public static String getCurrentDateTimeRandom() {
		String datetime = getCurrentDate(DateUtils.FORMAT10) + "_" + Math.random();
		return datetime;
	}
	/**
	 * 返回当前时间和日期
	 * @return
	 */
	public static String getCurrentDateTimeString() {
		return getCurrentDate(DateUtils.FORMAT7);
	}
	/**
	 * 返回当前日期
	 * @return
	 */
	public static Date getCurrentDateTime() {
		String datetime = getCurrentDate(FORMAT7);
		return parseStringToDate(datetime, "");
	}
	public static Timestamp getCurrentTimestamp() {
		String datetime = getCurrentDate(FORMAT7);
		return parseStringToTimestamp(datetime, "");
	}
	public static Timestamp getCurrentTimestamp(String format) {
		String datetime = getCurrentDate(format);
		return parseStringToTimestamp(datetime, "");
	}
	/**
	 * 日期转换为字符串
	 * @param date 日期
	 * @param format 格式
	 * @return 返回字符型日期
	 */
	public static String parseDateToString(Date date, String format) {
		String result = "";
		DateFormat formatter = null;
		try {
			if (date != null) {
				if (StringUtils.isBlank(format)) {
					formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				} else {
					formatter = new SimpleDateFormat(format);
				}
				result = formatter.format(date);
			}
		} catch (Exception e) {
		}
		return result;
	}
	/**
	 * 时间1-时间2的毫秒
	 * @param t1
	 * @param t2
	 * @return
	 */
	public static long between(Timestamp t1, Timestamp t2) {
		if ((t1 != null) && (t2 != null)) {
			return t1.getTime() - t2.getTime();
		}
		return 0;
	}
	/**
	 * 两个日期date1-date2相减，相差的天数
	 * @param date1 日期
	 * @param date2 日期
	 * @return 返回相减后的日期
	 */
	public static int betweenTwoDates(Date date1, Date date2) {
		return (int) ((getMillis(date1) - getMillis(date2)) / (24 * 3600 * 1000));
	}
	/**
	 * 将字符串转换为日期
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date parseStringToDate(String str, String format) {
		DateFormat formatter = null;
		Date date = null;
		if (StringUtils.isNotBlank(str)) {
			if (StringUtils.isBlank(format)) {
				formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			} else {
				formatter = new SimpleDateFormat(format);
			}
			try {
				date = formatter.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}
	/**
	 * 返回2007到今年的年份
	 * @return
	 */
	public static List<Integer> get2007ToThisYear() {
		// 当前时间
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		// 返回的年份
		List<Integer> the2007ToThisYearList = new ArrayList<Integer>();
		// 当前年
		int thisYear = c.get(Calendar.YEAR);
		for (int i = thisYear; i >= 2007; i--) {
			the2007ToThisYearList.add(i);
		}
		return the2007ToThisYearList;
	}
	/**
	 * 获取当前月的前几个月份的时间
	 * @param months
	 * @return
	 */
	public static Date getDateBeforeMonths(int months) {
		// 当前时间
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -months);
		return c.getTime();
	}
	/**
	 * 获取当前日期的前几天的时间
	 * @param days
	 * @return
	 */
	public static Date getDateBeforeDays(int days) {
		// 当前时间
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -days);
		return c.getTime();
	}
	/**
	 * 返回1-12月份
	 * @return
	 */
	public static List<String> getAllMonth() {
		List<String> theMonthList = new ArrayList<String>();
		for (int i = 1; i < 13; i++) {
			if (i < 10) {
				theMonthList.add("0" + i);
			} else {
				theMonthList.add("" + i);
			}
		}
		return theMonthList;
	}
	/**
	 * 返回日期中的年份
	 * @param date 日期
	 * @return 返回年份
	 */
	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}
	/**
	 * 返回日期中的月份
	 * @param date 日期
	 * @return 返回月份
	 */
	public static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}
	/**
	 * 返回日期中的日
	 * @param date 日期
	 * @return 返回日
	 */
	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 返回日期中的小时
	 * @param date 日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}
	/**
	 * 返回日期中的分钟
	 * @param date 日期
	 * @return 返回分钟
	 */
	public static int getMinute(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MINUTE);
	}
	/**
	 * 返回日期中的秒钟
	 * @param date 日期
	 * @return 返回秒钟
	 */
	public static int getSecond(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.SECOND);
	}
	/**
	 * 返回日期代表的毫秒
	 * @param date 日期
	 * @return 返回毫秒
	 */
	public static long getMillis(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}
	/**
	 * 返回当前日期代表的毫秒
	 * @return
	 */
	public static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}
	public static Date addMonth(Date date, int month) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(c.MONTH, month);
		return c.getTime();
	}
	/**
	 * 日期加天数,可以向前加，向后加
	 * @param date 日期
	 * @param day 天数
	 * @return 返回相加后的日期
	 */
	public static Date addDay(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}
	/**
	 * 日期加秒数,可以向前加，向后加
	 * @param date 日期
	 * @param second 秒数
	 * @return 返回相加后的日期
	 */
	public static Date addSecond(Date date, long second) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + second * 1000);
		return c.getTime();
	}
	/**
	 * 根据一个日期，返回是星期几
	 * @param sdate
	 * @return
	 */
	public static String getWeekByDate(String sdate) {
		// 再转换为时间
		Date date = parseStringToDate(sdate, "");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DAY_OF_WEEK);
		// day中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return day + "";
	}
	/**
	 * 根据一个日期，返回是星期几 1=星期日 7=星期六，其他类推
	 * @param sdate
	 * @return
	 */
	public static int getWeekByDate(Date date) {
		// 再转换为时间
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// day中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return c.get(Calendar.DAY_OF_WEEK);
	}
	/**
	 * 日期为一年中的第几周
	 * @return
	 */
	public static String getWeekNum(Date date) {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTime(date);
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		return week;
	}
	public static java.sql.Date parseUtilDateToSqlDate(Date date) {
		if (date != null) {
			return new java.sql.Date(date.getTime());
		} else {
			return null;
		}
	}
	public static java.sql.Date parseStringToSqlDate(String dateStr, String format) {
		Date date = null;
		if (StringUtils.isBlank(format)) {
			date = parseStringToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
		} else {
			date = parseStringToDate(dateStr, format);
		}
		return parseUtilDateToSqlDate(date);
	}
	public static Timestamp parseUtilDateToTimestamp(Date date, String format) {
		return parseStringToTimestamp(parseDateToString(date, format), format);
	}
	public static Date parseTimestampToUtilDate(Timestamp date, String format) {
		return parseStringToDate(parseDateToString(date, format), format);
	}
	public static Timestamp parseStringToTimestamp(String dateStr, String format) {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		Date date = null;
		if (StringUtils.isBlank(format)) {
			date = parseStringToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
		} else {
			date = parseStringToDate(dateStr, format);
		}
		if (date != null) {
			long t = date.getTime();
			return new Timestamp(t);
		} else {
			return null;
		}
	}
	/**
	 * 获取时间2099-12-31，23:59:59
	 * @return
	 */
	public static Timestamp getMaxTimestamp() {
		return DateUtils.parseStringToTimestamp("2099-12-31 23:59:59", DateUtils.FORMAT7);
	}
	/**
	 * 返回日期中的年月日，格式化成yyyyMMdd
	 * @param date
	 * @return
	 */
	public static String getYearMonthDay(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT12);
		String currentTime = formatter.format(date);
		return currentTime;
	}
	/**
	 * 取得指定月份的第一天
	 * @param strdate String
	 * @return String
	 */
	public static String getMonthBegin(String strdate) {
		return parseDateToString(parseStringToDate(strdate, "yyyy-MM"), "");
	}
	/**
	 * 取得指定月份的最后一天
	 * @param strdate String
	 * @return String
	 */
	public static String getMonthEnd(String strdate) {
		Date date = parseStringToDate(getMonthBegin(strdate), "");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return parseDateToString(calendar.getTime(), "");
	}
	public static String getPreviousMonthBegin() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
		Date date = new Date(cal.getTimeInMillis());
		return parseDateToString(parseStringToDate(parseDateToString(date, FORMAT1), "yyyy-MM"), FORMAT1);
	}
	public static String getPreviousMonthEnd() {
		Date date = parseStringToDate(getPreviousMonthBegin(), FORMAT1);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return parseDateToString(calendar.getTime(), FORMAT1);
	}
	/**
	 * 上个月
	 * @return
	 */
	public static String getPreviousMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
		Date date = new Date(cal.getTimeInMillis());
		return parseDateToString(parseStringToDate(parseDateToString(date, FORMAT1), "yyyy-MM"), FORMAT14);
	}
	/**
	 * 上两个月
	 * @return
	 */
	public static String getPreviousTwoMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 2);
		Date date = new Date(cal.getTimeInMillis());
		return parseDateToString(parseStringToDate(parseDateToString(date, FORMAT1), "yyyy-MM"), FORMAT14);
	}
	/**
	 * 判断两个日期是否在同一周
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
				return true;
			}
		} else if ((1 == subYear) && (11 == cal2.get(Calendar.MONTH))) {
			// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
				return true;
			}
		} else if ((-1 == subYear) && (11 == cal1.get(Calendar.MONTH))) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
				return true;
			}
		}
		return false;
	}
	public static String addOneDay(String stopTime) {
		String finishTime = stopTime;
		Date finishDate = null;
		if (stopTime != null && !"".equals(stopTime)) {
			if (stopTime.length() < 19) {
				finishTime = stopTime.substring(0, 10) + " 00:00:00";
			}
			finishDate = DateUtils.parseStringToDate(finishTime, DateUtils.FORMAT7);
			finishDate = DateUtils.addDay(finishDate, 1);
			return DateUtils.parseDateToString(finishDate, DateUtils.FORMAT7);
		} else {
			return null;
		}
	}
	/**
	 * 给date加1天
	 * @param date
	 * @return
	 */
	public static Date addOneDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}
	/**
	 * 描述:此类用于取得当前日期相对应的月初，月末，季初，季末，年初，年末，返回值均为String字符串 1、得到当前日期 today() 2、得到当前月份月初 thisMonth()
	 * 3、得到当前月份月底 thisMonthEnd() 4、得到当前季度季初 thisSeason() 5、得到当前季度季末 thisSeasonEnd() 6、得到当前年份年初
	 * thisYear() 7、得到当前年份年底 thisYearEnd() 8、判断输入年份是否为闰年 leapYear 注意事项: 日期格式为：xxxx-yy-zz (eg:
	 * 2007-12-05) 实例:
	 * @author pure
	 */
	/**
	 * 功能：得到当前日期 格式为：xxxx-yy-zz (eg: 2007-12-05)<br>
	 * @return String
	 * @author pure
	 */
	public String today() {
		String strY = null;
		String strZ = null;
		x = localTime.get(Calendar.YEAR);
		y = localTime.get(Calendar.MONTH) + 1;
		z = localTime.get(Calendar.DATE);
		strY = y >= 10 ? String.valueOf(y) : ("0" + y);
		strZ = z >= 10 ? String.valueOf(z) : ("0" + z);
		return x + "-" + strY + "-" + strZ;
	}
	/**
	 * 功能：得到当前月份月初 格式为：xxxx-yy-zz (eg: 2007-12-01)<br>
	 * @return String
	 * @author pure
	 */
	public String thisMonth() {
		String strY = null;
		x = localTime.get(Calendar.YEAR);
		y = localTime.get(Calendar.MONTH) + 1;
		strY = y >= 10 ? String.valueOf(y) : ("0" + y);
		return x + "-" + strY + "-01";
	}
	/**
	 * 功能：得到当前月份月底 格式为：xxxx-yy-zz (eg: 2007-12-31)<br>
	 * @return String
	 * @author pure
	 */
	public String thisMonthEnd() {
		String strY = null;
		String strZ = null;
		boolean leap = false;
		x = localTime.get(Calendar.YEAR);
		y = localTime.get(Calendar.MONTH) + 1;
		if (y == 1 || y == 3 || y == 5 || y == 7 || y == 8 || y == 10 || y == 12) {
			strZ = "31";
		}
		if (y == 4 || y == 6 || y == 9 || y == 11) {
			strZ = "30";
		}
		if (y == 2) {
			leap = leapYear(x);
			if (leap) {
				strZ = "29";
			} else {
				strZ = "28";
			}
		}
		strY = y >= 10 ? String.valueOf(y) : ("0" + y);
		return x + "-" + strY + "-" + strZ;
	}
	/**
	 * 功能：得到当前季度季初 格式为：xxxx-yy-zz (eg: 2007-10-01)<br>
	 * @return String
	 * @author pure
	 */
	public String thisSeason() {
		String dateString = "";
		x = localTime.get(Calendar.YEAR);
		y = localTime.get(Calendar.MONTH) + 1;
		if (y >= 1 && y <= 3) {
			dateString = x + "-" + "01" + "-" + "01";
		}
		if (y >= 4 && y <= 6) {
			dateString = x + "-" + "04" + "-" + "01";
		}
		if (y >= 7 && y <= 9) {
			dateString = x + "-" + "07" + "-" + "01";
		}
		if (y >= 10 && y <= 12) {
			dateString = x + "-" + "10" + "-" + "01";
		}
		return dateString;
	}
	/**
	 * 功能：得到当前季度季末 格式为：xxxx-yy-zz (eg: 2007-12-31)<br>
	 * @return String
	 * @author pure
	 */
	public String thisSeasonEnd() {
		String dateString = "";
		x = localTime.get(Calendar.YEAR);
		y = localTime.get(Calendar.MONTH) + 1;
		if (y >= 1 && y <= 3) {
			dateString = x + "-" + "03" + "-" + "31";
		}
		if (y >= 4 && y <= 6) {
			dateString = x + "-" + "06" + "-" + "30";
		}
		if (y >= 7 && y <= 9) {
			dateString = x + "-" + "09" + "-" + "30";
		}
		if (y >= 10 && y <= 12) {
			dateString = x + "-" + "12" + "-" + "31";
		}
		return dateString;
	}
	/**
	 * 功能：得到当前年份年初 格式为：xxxx-yy-zz (eg: 2007-01-01)<br>
	 * @return String
	 * @author pure
	 */
	public String thisYear() {
		x = localTime.get(Calendar.YEAR);
		return x + "-01" + "-01";
	}
	/**
	 * 功能：得到当前年份年底 格式为：xxxx-yy-zz (eg: 2007-12-31)<br>
	 * @return String
	 * @author pure
	 */
	public String thisYearEnd() {
		x = localTime.get(Calendar.YEAR);
		return x + "-12" + "-31";
	}
	/**
	 * 功能：判断输入年份是否为闰年<br>
	 * @param year
	 * @return 是：true 否：false
	 * @author pure
	 */
	public boolean leapYear(int year) {
		boolean leap;
		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0)
					leap = true;
				else
					leap = false;
			} else
				leap = true;
		} else
			leap = false;
		return leap;
	}

	private int x; // 日期属性：年
	private int y; // 日期属性：月
	private int z; // 日期属性：日
	private Calendar localTime; // 当前日期

	public DateUtils() {
		localTime = Calendar.getInstance();
	}
	
}
