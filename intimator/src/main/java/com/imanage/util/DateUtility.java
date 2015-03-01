package com.imanage.util;

import java.sql.Timestamp;
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
	
	public static void main(String[] args) {
		System.out.println("date:"+getSQLCurrentTime());
	}
}
