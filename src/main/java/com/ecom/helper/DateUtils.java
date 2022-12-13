package com.ecom.helper;

/**
 * @author ADNAN
 * @version 1.0
 * @since 2022-05-20
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

	public static String getEndTimestamp(int month, int year) {
		YearMonth yearMonth = YearMonth.of(year, month + 1);
		LocalDate lastDate = yearMonth.atEndOfMonth();
		return lastDate.toString();
	}

	public static LocalDate stringToDateFormat(String date, String datePattern) throws ParseException {
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern(datePattern);
		LocalDate localDate = LocalDate.parse(date, sdf);
		return  localDate;
	}

	public static String dateToStringFormat(Date date, String datePattern) {
		SimpleDateFormat format = new SimpleDateFormat(datePattern);
		return format.format(date);

	}

	public static String timeStamp(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(new Date());

	}

	public static LocalDateTime timeStampInDate() throws ParseException {
		return LocalDateTime.now();
	}

	private DateUtils() {

	}
}
