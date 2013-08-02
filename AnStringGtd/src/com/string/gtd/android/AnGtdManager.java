package com.string.gtd.android;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.string.gtd.core.GtdManager;

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
	
	public AnGtdManager(){
		gui = new AnGuiManager(this);
	}
	
	@Override
	public void setNextRemind(long remindTime) {
		AlarmManager am = (AlarmManager) context.getSystemService(
				Context.ALARM_SERVICE);

		Intent intent = new Intent(GtdAction.REMIND);
		PendingIntent sender = PendingIntent.getBroadcast(context, 0,
				intent, PendingIntent.FLAG_CANCEL_CURRENT);

		am.cancel(sender); // 取消以往所有GtdAction.REMIND的闹钟
		am.set(AlarmManager.RTC_WAKEUP, remindTime, sender);	// 设置闹钟
	}


	public void updateContext(Context context) {
		this.context = context;
		AnGuiManager anGui = (AnGuiManager) this.gui;
		anGui.updateContext(context);
	}

}
