package com.string.gtd.core;

public class Countup implements TextMaker {
	private long startTime = 0;

	public Countup(long startTime) {
		this.startTime = startTime;
	}

	private long getElapsedTime() {
		long curTime = STimeUtils.curTime();
		return curTime - startTime;
	}

	@Override
	public String getText() {
		long elapsedTime = getElapsedTime();
		return "+" + STimeUtils.toString_mmss(elapsedTime);
	}
}
