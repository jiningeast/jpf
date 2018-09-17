package com.joiest.jpf.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

	/**
	 * 工作日，用,分隔；示例：20171130,20171201
	 */
	private static final String workDayStr = "";

	/**
	 * 节假日，用,分隔；示例：20180101,20180102,20180103
	 */
	private static final String holidayStr = "";


	public static final String DATEFORMATLONG = "yyyy-MM-dd HH:mm:ss";
	public static final String DATEFORMATMEDIUM = "yyyy-MM-dd HH:mm";
	public static final String DATE_FORMAT_YMDH = "yyyyMMddHH";
	public static final String DATEFORMATSHORT = "yyyy-MM-dd";
	public static final String DATE_SHORT_YEARMONTH = "yyyy-MM";
	public static final String DATENUMBERFORMAT = "yyyyMMdd";
	public static final String DATEHOURNUMBERFORMAT = "yyyyMMddHH";
	public static final String DATE_FORMAT_HOUR = "H";
	public static final String Date_FORMAT_YMDHMS = "yyyyMMddHHmmss";
	public static final String DATE_FORMAT_YMDHMSSS = "yyyyMMddHHmmssSSS";
	public static final String TIME_FORMAT_HMS = "HH:mnowm:ss";

	private DateUtils() {
	}

	public static Date getCurrentDate() {
		return new Date();
	}

	public static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * @return String YYYY-MM
	 */
	public static String getCurrentYearMonthDate() {
		//return getCurrentFormatDate(DATE_SHORT_YEARMONTH);
		return format(new Date(), DATE_SHORT_YEARMONTH);
	}

	public static String getCurrentFormatDateLong() {
		//return getCurrentFormatDate(DATEFORMATLONG);
		return format(new Date(), DATEFORMATLONG);
	}

	public static String getCurrentFormatDateMedium() {
		//return getCurrentFormatDate(DATEFORMATMEDIUM);
		return format(new Date(), DATEFORMATMEDIUM);
	}

	public static String getCurrentFormatDateShort() {
		//return getCurrentFormatDate(DATEFORMATSHORT);
		return format(new Date(), DATEFORMATSHORT);
	}


	
	public static  String getCurTimeString() {
		//simpleDateFormat.applyPattern(TIME_FORMAT_HMS);
		//return simpleDateFormat.format(new Date());
		//return format(new Date(),TIME_FORMAT_HMS);
		return format(new Date(), TIME_FORMAT_HMS);
	}
	
	public static String getDate2LongString(Date date) {
		//return getDate2String(DATEFORMATLONG, date);
		return format(date, DATEFORMATLONG);
	}

	public static String getDate2MediumString(Date date) {
		//return getDate2String(DATEFORMATMEDIUM, date);
		return format(date, DATEFORMATMEDIUM);
	}

	public static String getDate2ShortString(Date date) {
		//return getDate2String(DATEFORMATSHORT, date);
		return format(date, DATEFORMATSHORT);
	}

	public static String getDate2HourString(Date date) {
		//return getDate2String(DATE_FORMAT_HOUR, date);
		return format(date, DATE_FORMAT_HOUR);
	}

	public static String getDate2NumberString(Date date) {
		//return getDate2String(DATENUMBERFORMAT, date);
		return format(date, DATENUMBERFORMAT);
	}

	public static String getDate2YmdhmsString(Date date){
		return format(date, Date_FORMAT_YMDHMS);
	}
	
	public static String getDate2YmdhmsssString(Date date){
		return format(date, DATE_FORMAT_YMDHMSSS);
	}

	public static Date getString2LongDate(String str) {
		//return getString2Date(DATEFORMATLONG, str);
		return parseDate(str,DATEFORMATLONG);
		
	}

	public static Date getString2MediumDate(String str) {
		//return getString2Date(DATEFORMATMEDIUM, str);
		return parseDate(str,DATEFORMATMEDIUM);
	}

	public static Date getString2YmdhDate(String str) {
		return parseDate(str,DATE_FORMAT_YMDH);
	}

	public static Date getString2ShortDate(String str) {
		//return getString2Date(DATEFORMATSHORT, str);
		return parseDate(str,DATEFORMATSHORT);
	}

	
	public static Date parseDate(String str,String parsePatterns){
		try
		{
			return org.apache.commons.lang3.time.DateUtils.parseDate(str, parsePatterns);
		} catch (ParseException e)
		{
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static String format(Date date,String pattern){
		return DateFormatUtils.format(date, pattern);
	}
	public static Date getString2YmdDate(String str) {
		//return getString2Date(DATENUMBERFORMAT, str);
		return parseDate(str,DATENUMBERFORMAT);
	}

	public static Date getString2YmdhmsDate(String str) {
		return parseDate(str,Date_FORMAT_YMDHMS);
	}
	
	
	public static Date getEmptyDate() {
		return getString2ShortDate("1971-01-01");
	}

	public static String getEmptyDateString() {
		return "1971-01-01";
	}

	public static Date getLong2Date(long l) {
		return new Date(l);
	}

	public static int getOffMinutes(long l) {
		return getOffMinutes(l, getCurrentTimeMillis());
	}

	public static int getOffMinutes(long from, long to) {
		return (int) ((to - from) / 60000L);
	}

	public static int getYear() {
		return Calendar.getInstance().get(1);
	}

	public static int getMonth() {
		return Calendar.getInstance().get(2) + 1;
	}

	// DAY_OF_WEEK
	public static int getDayOfWeek() {
		int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

		if (dayOfWeek == 1) {
			dayOfWeek = 7;
		} else {
			dayOfWeek -= 1;
		}
		return dayOfWeek;
	}

	public static int getDay() {
		return Calendar.getInstance().get(5);
	}

	public static int getHour() {
		return Calendar.getInstance().get(11);
	}

	public static int getMinute() {
		return Calendar.getInstance().get(12);
	}

	public static int getSecond() {
		return Calendar.getInstance().get(13);
	}




	public static String getlastMonth() {

		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		GregorianCalendar gc = new GregorianCalendar();
		gc.roll(Calendar.MONTH, false);
		return sdf.format(gc.getTime());

	}

	/**
	 * @return String
	 */
	public static String getCurrentLastMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		return format.format(cal.getTime());
	}

	/**
	 * @return String[]
	 * 
	 */
	public static String[] getCurrentLastWeek() {
		String[] weeks = new String[2];
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -cal.get(Calendar.DAY_OF_WEEK));
		weeks[1] = format.format(cal.getTime());
		cal.add(Calendar.DAY_OF_YEAR, -6);
		weeks[0] = format.format(cal.getTime());
		return weeks;
	}

	/**
	 * if(?1?7始日?1?7结束日期)，return TURE;else return FALSE;
	 * 
	 * @return
	 */
	public static boolean isStartLessEndTime(String start, String end)
			throws ParseException {
		if (start.equals("") || end.equals(""))
			return false;
		Date startDate = DateFormat.getDateInstance().parse(start);
		Date endDate = DateFormat.getDateInstance().parse(end);
		if (startDate.compareTo(endDate) < 0) {
			return true;
		} else {
			return false;
		}
	}

	public static Date getToday() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	public static Date getNextToday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	public static Date getYesterday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}


	public static boolean isWorkday(Date date) {
		String dateStr = getDate2NumberString(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		if (workDayStr.contains(dateStr)) {
			return true;
		} else if (holidayStr.contains(dateStr)) {
			return false;
		} else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
				calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			return false;
		}

		return true;
	}

	/**
	 * 获取距离指定日期最近的下一个工作日
	 *
	 * @param date 指定日期
	 * @return 最近的下一个工作日
	 */
	public static Date getNextWorkday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		while (!isWorkday(calendar.getTime())) {
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			continue;
		}
		return calendar.getTime();
	}

	/**
	 * 获取指定日期的结算日期, 格式: yyyyMMdd
	 * @param date
	 * @param settleCycle
	 * @return
	 */
	public static String getSettleDate(Date date, String settleCycle){
		if(settleCycle ==null || "".equals(settleCycle)){
			return null;
		}
		String cycleType = settleCycle.substring(0, 1);
		String cycleValue = settleCycle.substring(1);
		if(!cycleValue.matches("\\d+")){
			return null;
		}
		int intValue = Integer.parseInt(cycleValue);

		if("D".equalsIgnoreCase(cycleType)){
			if(intValue >= 0) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.DAY_OF_MONTH, intValue);
				return getDate2NumberString(calendar.getTime());
			}
		}
		if("T".equalsIgnoreCase(cycleType)){
			if(intValue == 0){
				if(isWorkday(date)){
					return getDate2NumberString(date);
				}else{
					Date nextDate = getNextWorkday(date);
					return getDate2NumberString(nextDate);
				}
			}
			if(intValue > 0) {
				Date nextDate = date;
				for (int i = 0; i < intValue; i++) {
					nextDate = getNextWorkday(nextDate);
				}
				return getDate2NumberString(nextDate);
			}
		}
		return null;
	}


	/**
	 * 增加日期中某类型的某数值。如增加日期
	 * @param date 日期
	 * @param dateType 类型
	 * @param amount 数值
	 * @return 计算后日期
	 */
	public static Date addInteger(String date, int dateType, int amount,String pattern)
	{

		Date date1 = getFdate(date,pattern);
		if(date1==null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		calendar.add(dateType, amount);
		return calendar.getTime();
	}


	public static Date getFdate(String date,String pattern)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date fDate = null;
		try
		{
			fDate = sdf.parse(date);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return fDate;
	}


	/*
	 * 将时间戳转换为时间
	 */
	public static String stampToDate(String seconds){

		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMATLONG);
		return sdf.format(new Date(Long.valueOf(seconds+"000")));
	}

	/*
	 * 将时间戳转换为时间  date
	 */
	public static Date stampToDateRe(String seconds){

		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMATLONG);
		return new Date(Long.valueOf(seconds));
	}
	/*
	 * 将时间转换为时间戳
	 */
	public static String dateToStamp(String date_str) throws ParseException{

		try {

			SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMATLONG);
			return String.valueOf(sdf.parse(date_str).getTime()/1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 时间字符串转换为date类型   年月日格式
	 * */
	public static Date stringToDate(String dateString){

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATEFORMATSHORT);
		Date time2 = new Date();
		if(StringUtils.isNotBlank(dateString)){
			try {

				time2 = simpleDateFormat.parse(dateString);//date类型
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return time2;
	}
	/**
	 * date类型转换为时间字符串   年月日格式
	 * */
	public static String dateToString(Date date){

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATEFORMATLONG);
		String timeStr = simpleDateFormat.format(date);
		return timeStr;
	}
	/**
	 * 获取当前时间
	 * */
	public static String getCurDate(){

		Date date = new Date();
		SimpleDateFormat myfmt1 = new SimpleDateFormat(DATEFORMATLONG);
		return myfmt1.format(date);
	}

	public static void main(String[] args) {
//		System.out.println(DateUtils.getString2YmdDate("20120101").getTime());
//		System.out.println(DateUtils.getCurrentTimeMillis());
//		System.out.println(DateUtils.getCurrentFormatDate(DATEFORMATLONG));
		//System.out.println(DateUtils.getBeforeDay("20140301"));
		System.out.println(getSettleDate(new Date(), "T0"));
		//System.out.println(getDate2NumberString(getNextWorkday(new Date())));
		System.out.print(getString2YmdDate("20180113").getTime());
	}
}