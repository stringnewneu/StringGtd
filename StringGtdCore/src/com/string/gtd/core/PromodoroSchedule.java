package com.string.gtd.core;

import java.util.Calendar;

public class PromodoroSchedule {
	public static int NONE = 0;
	public static int WORKING = 1;
	public static int RESTING = 2;
	private int state = NONE;
	
	private final int min2ms = 60 * 1000;
	@SuppressWarnings("unused")
	private final int s2ms = 1000;
	
//	private final int min2ms = 500; //测试时间缩短用500毫秒代替1分钟
	
	private long workTime = 25 * min2ms;
	private long restTime = 5 * min2ms;
	
//	private long workTime = 5 * s2ms;
//	private long restTime = 3 * s2ms;

	private long startTime;
	
	
	public long getNextRemindTime() {
		if(state == WORKING)
			return startTime + workTime; 
		if(state == RESTING)
			return startTime + restTime;
		else
			// TODO log the error
			return 0;
	}


	public void setState(int state) {
		this.state = state;
		startTime = Calendar.getInstance().getTimeInMillis();
	}

	public int getState() {
		return state;
	}

	public long getPromodoroRemainTime() {
		if(state == WORKING)
		return workTime - (Calendar.getInstance().getTimeInMillis() - startTime);
		else if(state == RESTING)
			return restTime - (Calendar.getInstance().getTimeInMillis() - startTime);
		else // TODO log this error
			return 0;
	}
	
}
