package com.string.gtd.android;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.string.gtd.core.GtdManager;
import com.string.gtd.core.PromodoroSchedule;
import com.string.gtd.core.StringTimeUtils;

public class AnGtdManager extends GtdManager{
	private static AnGtdManager theGlobalGtdManager = null;
	private Context context;
	
	public static AnGtdManager getGlobalGtdManager(Context context)
	{
		if (theGlobalGtdManager == null)
			theGlobalGtdManager = new AnGtdManager();
		theGlobalGtdManager.context = context;
		
		return theGlobalGtdManager;
	}
	
	public static AnGuiManager getGlobalGuiManager(Context context){
		AnGtdManager theGlobalAnGtdManager = getGlobalGtdManager(context);
		return (AnGuiManager)theGlobalAnGtdManager.getGuiManager();
	}
	
	private AnGtdManager(){
		gui = new AnGuiManager();
	}
	
	public void setNextReminder(long remindTime, Context context) {
		AlarmManager am = (AlarmManager) context.getSystemService(
				Context.ALARM_SERVICE);

		Intent intent = new Intent(GtdAction.REMIND);
		PendingIntent sender = PendingIntent.getBroadcast(context, 0,
				intent, PendingIntent.FLAG_CANCEL_CURRENT);

		am.cancel(sender); // 取消以往所有GtdAction.REMIND的闹钟
		am.set(AlarmManager.RTC_WAKEUP, remindTime, sender);	// 设置闹钟
	}

	public void startWork() {
		setState(PromodoroSchedule.WORKING);
		long remindTime = getNextRemindTime();
		
		setNextReminder(remindTime, context);
		
		long countdownTime = promodoroSchedule.getNextRemindTime() - StringTimeUtils.getCurrentTime();
		// 发出GUI开始计时命令
		gui.startCountdown(countdownTime);
	}

	public void updateContext(Context context) {
		this.context = context;
		AnGuiManager anGui = (AnGuiManager) this.gui;
		anGui.updateContext(context);
	}

}
