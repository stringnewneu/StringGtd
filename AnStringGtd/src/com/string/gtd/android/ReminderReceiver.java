package com.string.gtd.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ReminderReceiver extends BroadcastReceiver {
	public ReminderReceiver() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// 启动ReminderService,提醒开始
		Intent vibrate = new Intent(GtdAction.REMIND);
		context.startService(vibrate);

		// 弹出StringGtdActivity
		// 响铃两声
		// 
		
		AnGuiManager gui = AnGtdManager.getGlobalGuiManager(context);
		gui.stopCountdown();
		
		// TODO 记录等操作
		Intent change = new Intent(context, StringGtdActivity.class);
		change.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		StringGtdActivity.extras.putString("aaa", "bbb");
		context.startActivity(change);
		
		Log.v("AnStringGtd", "onReceive()");
	}
}
