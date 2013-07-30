package com.string.testhelper;

import java.util.Calendar;

import android.os.SystemClock;

public class TestHelper {
	private TestHelper() {
	}

	public static boolean setSystemTime(int hours, int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hours);
		calendar.set(Calendar.MINUTE, minutes);
		long when = calendar.getTimeInMillis();
		if (when / 1000 < Integer.MAX_VALUE) {
			return SystemClock.setCurrentTimeMillis(when);
		}
		return false;
	}
	
	public static boolean setSystemTime(long time) {
		if (time / 1000 < Integer.MAX_VALUE) {
			return SystemClock.setCurrentTimeMillis(time);
		}
		return false;
	}
}
