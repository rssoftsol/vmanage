package com.imanage.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtility {
	
	public static String getTodaysDate(String format){
		if(format == null){
			return new Date().toString();
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(new Date());
	}
	
	public static Timestamp getSQLCurrentTime(){
		Calendar currenttime = Calendar.getInstance();
	    return new Timestamp((currenttime.getTime()).getTime());
	}
	
	public static Date getUtilCurrentTime(){
		Calendar currenttime = Calendar.getInstance();
	    return (currenttime.getTime());
	}
	/**
	 * without considering hours,minutes and seconds
	 * */
	public static Long getTodaysDate(){
		long time = 0;
		String date = getTodaysDate("yyyy-MM-dd");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			time = dateFormat.parse(date).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}
	
	public static int getHourOfTheDay(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	public static void main(String[] args) {
		System.out.println("date:"+getSQLCurrentTime());
	}
}
