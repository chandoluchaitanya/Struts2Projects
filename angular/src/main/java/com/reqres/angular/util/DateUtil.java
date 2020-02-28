package com.reqres.angular.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public final static String DATE_FORMAT_DDMMMYYYY_HHMMSS = "dd-MMM-yyyy hh:mm:ss";

	/**
	 * Get the date and return it as a String.
	 * 
	 * @param : date [Date]
	 * @return : date [String]
	 */
	public static String getDate(Date date) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_DDMMMYYYY_HHMMSS);
		return dateFormat.format(date);
	}

	/**
	 * Get the date as a Date datatype and output the date as a String in the
	 * specified format.
	 * 
	 * @param : date [Date], format [String]
	 * @return : date [String]
	 */
	public static String getDate(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * Accept the date as a string and output the date as a Date data type of the
	 * specified format.
	 * 
	 * @param : date [String], format [String]
	 * @return : date [Date]
	 */
	public static Date getDate(String date, String format) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			Date newDate = dateFormat.parse(date);
			return newDate;
		} catch (ParseException er) {
			return new Date(0);
		}
	}

}
