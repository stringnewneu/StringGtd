package com.string.gtd.core;

import java.util.Calendar;

public abstract class MockTime {
	protected long startCalendarTime = System.nanoTime();
	protected long startNanoTime = 0;

	public MockTime() {
	}

	public void backupTime() {
		startCalendarTime = Calendar.getInstance().getTimeInMillis();
		startNanoTime = System.nanoTime();
	}
	
	public void restoreTime(){
		long endNanoTime = System.nanoTime();
		long delta = (endNanoTime - startNanoTime) / (1000 * 1000); // 1ms = 1000 us = 1000 * 1000 ns
		long endCalendarTime = startCalendarTime + delta;
		set(endCalendarTime);
	}
	
	public abstract void set(String time);
	protected abstract void set(long time);
}