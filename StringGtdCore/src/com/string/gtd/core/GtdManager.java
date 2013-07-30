package com.string.gtd.core;

public abstract class GtdManager {
	protected PromodoroSchedule promodoroSchedule = new PromodoroSchedule();
	protected GuiManager gui = null;
	

	protected GtdManager(){
	}
	
	public void reset() {
		// TODO Auto-generated method stub

	}

	public void setState(int state) {
		promodoroSchedule.setState(state);
	}
	
	public int getState() {
		return promodoroSchedule.getState();
	}

	public long getNextRemindTime() {
		return promodoroSchedule.getNextRemindTime();
	}

	public long getPromodoroRemainTime() {
		return promodoroSchedule.getPromodoroRemainTime();
	}
	
	public GuiManager getGuiManager(){
		return gui;
	}

}
