package com.string.gtd.core;

public class Countdown implements TextMaker {
	private long endTime = 0;

	public Countdown(long endTime) {
		this.endTime = endTime;
	}

	private long getRemainTime() {
		long curTime = STimeUtils.curTime();
		return endTime - curTime;
	}

	@Override
	public String getText() {
		long remainTime = getRemainTime();
		return STimeUtils.toString_mmss(remainTime);
	}
}
