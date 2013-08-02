package com.string.gtd.core;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class STimeUtils {
	public static final int s2ms = 1000;
	public static final int min2mm = 60 * s2ms;
	
	private STimeUtils() {
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

	public static long curTime() {
		return Calendar.getInstance().getTimeInMillis();
	}

	public static String currentTimeString_HHmm() {
		long currentTime = STimeUtils.curTime();
		return toString_HHmm(currentTime);
	}

	public static long min2mm(int min) {
		return min * min2mm;
	}

}
