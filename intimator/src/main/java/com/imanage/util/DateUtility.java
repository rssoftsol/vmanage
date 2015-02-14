package com.imanage.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {
	public static String getTodaysDate(String format){
		if(format == null){
			return new Date().toString();
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(new Date());
	}
}
