package com.string.gtd.core;

public class CountdownObject {
	private long startTime = 0;
	private long countdownTime = 0;
	
	public CountdownObject(long countdownTime) {
		this.countdownTime = countdownTime;
	}

	public void start() {
		startTime = StringTimeUtils.getCurrentTime();
	}

	public long getRemainTime() {
		long currentTime = StringTimeUtils.getCurrentTime();
		return countdownTime - (currentTime - startTime);
	}

	public boolean running() {
		boolean running = getRemainTime() > 0;
		return running;
	}
}
