package com.imanage.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {

	private static SecureRandom random = new SecureRandom();

	public String setZeroForNull(String colValue) {
		String value = colValue != null ? colValue : "0";
		return value;
	}

	public static String generateAlphanumericUId() {
		return new BigInteger(160, random).toString(32);
	}

	public static double getDateDifference(Date passwdResetDate) {

		double dateDiff = 0;
		Date currentDate = new Date();
		// HH converts hour in 24 hours format (0-23), day calculation
		try {
			// in milliseconds
			long diff = currentDate.getTime() - passwdResetDate.getTime();
			System.out.println("diff : " + diff);
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;

			long diffDays = diff / (24 * 60 * 60 * 1000);

			/*
			 * System.out.print(diffDays + " days, ");
			 * System.out.print(diffHours + " hours, ");
			 * System.out.print(diffMinutes + " minutes, ");
			 * System.out.print(diffSeconds + " seconds.");
			 */

			BigDecimal a = new BigDecimal(diffHours).divide(new BigDecimal(24),
					4, BigDecimal.ROUND_HALF_UP);
			BigDecimal b = new BigDecimal(diffMinutes).divide(new BigDecimal(
					24 * 60), 4, BigDecimal.ROUND_HALF_UP);
			BigDecimal c = new BigDecimal(diffSeconds).divide(new BigDecimal(
					24 * 60 * 60), 4, BigDecimal.ROUND_HALF_UP);
			dateDiff = diffDays + a.doubleValue() + b.doubleValue()
					+ c.doubleValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateDiff;
	}

	public static boolean isNumeric(String str){
	    for (char c : str.toCharArray()){
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}
	
	public static void main(String[] args) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date passwdResetDate = format.parse("2013-05-14 08:15:35");

		getDateDifference(passwdResetDate);
	}

}
