package com.string.gtd.core;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class StringTimeUtils {
	private StringTimeUtils() {
	}

	public static long getTime(int year, int mouth, int day, int hour,
			int minute, int second) {
		Calendar time = Calendar.getInstance();
		time.setTimeInMillis(0);
		time.set(year, mouth - 1, day, hour, minute, second);

		return time.getTimeInMillis();
	}

	public static long getLongDate(String dateString) {
		long time = 0;
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
			Date date = df.parse(dateString);
			time = date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	public static long getEpoch() {
		Calendar time = Calendar.getInstance();
		time.setTimeInMillis(0);

		return time.getTimeInMillis();
	}

	public static String toString_mmss(long time) {
		DateFormat df = new SimpleDateFormat("mm:ss", Locale.US);
		String formatedTime = df.format(new Date(time));

		return formatedTime;
	}
	
	public static String toString_HHmm(long time) {
		DateFormat df = new SimpleDateFormat("HH:mm", Locale.US);
		String formatedTime = df.format(new Date(time));

		return formatedTime;
	}

	public static long getCurrentTime() {
		return Calendar.getInstance().getTimeInMillis();
	}

	public static String currentTimeString_HHmm() {
		long currentTime = StringTimeUtils.getCurrentTime();
		return toString_HHmm(currentTime);
	}

}
