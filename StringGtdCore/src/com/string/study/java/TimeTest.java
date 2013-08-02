package com.string.study.java;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import junit.framework.TestCase;

public class TimeTest extends TestCase {
	public void testCalendar() {
		Calendar startTime = Calendar.getInstance();
		startTime.getTimeInMillis();

		int reminderDuration = 1000; // in millisecond
		Calendar endTime = (Calendar) startTime.clone();
		endTime.add(Calendar.MILLISECOND, (int) reminderDuration);

		long diff = endTime.getTimeInMillis() - startTime.getTimeInMillis();
		assertEquals(diff, 1000);

		// 测试0时刻
		Calendar epoct = Calendar.getInstance();
		epoct.set(1, 1, 1, 0, 0, 0); // 没有完美的0年0月0日，最多只能是1年1月1日00:00:00.000
		epoct.set(Calendar.MILLISECOND, 0);
		// System.err.println(epoct.get(Calendar.HOUR) + ":" +
		// epoct.get(Calendar.MINUTE));
		// System.err.println(epoct);
	}

	// 时间差例子
	public void testExample_DiffOfTime() {
		Date d1 = getLongDate("2004-03-26 13:31:40");
		Date d2 = getShortDate("11:30:24");
		long diff = d1.getTime() - d2.getTime();
		@SuppressWarnings("unused")
		long days = diff / (1000 * 60 * 60 * 24);

	}

	// 解析日期 "yyyy-MM-dd HH:mm:ss" -- eg: "2004-03-26 13:31:40"
	private Date getLongDate(String dateString) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		Date date = null;
		try {
			date = df.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	// 解析时间 "HH:mm:ss" -- eg: "11:30:24"
	private Date getShortDate(String dateString) {
		DateFormat df = new SimpleDateFormat("HH:mm:ss", Locale.US);
		Date date = null;
		try {
			date = df.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

}
