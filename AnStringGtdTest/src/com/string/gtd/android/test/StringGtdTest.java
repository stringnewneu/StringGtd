package com.string.gtd.android.test;

import java.util.Calendar;

import com.string.gtd.android.AnGtdManager;
import com.string.gtd.android.GtdAction;
import com.string.gtd.core.*;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.test.AndroidTestCase;
import android.util.Log;

public class StringGtdTest extends AndroidTestCase {
	public static final String TAG = "StringGtdTest";
	AnMockTime mockTime = null;

	protected void setUp() throws Exception {
		super.setUp();

		// 启动MockTime
		if (mockTime == null)
			mockTime = new AnMockTime(getContext());

		// 备份当前时间
		mockTime.backupTime();
	}

	protected void tearDown() throws Exception {
		// 恢复当前时间
		mockTime.restoreTime();
		
		super.tearDown();
	}

	private void assertTimeStampEqual(long expectTime, long actualTime) {
		long delta = expectTime - actualTime;
		long threshold = 100;
		
		if (Math.abs(delta) >= threshold)
			Log.e(TAG,
					"Assert timeStamps equal failed : expectTime - actualTime = "
							+ delta);

		assertTrue(Math.abs(delta) < threshold);
	}

	public void testMockTime_setStringTime() {
		mockTime.set("2012-1-1 00:00");

		long actualTime = Calendar.getInstance().getTimeInMillis();
		long expectedTime = StringTimeUtils.getTime(2012, 1, 1, 0, 0, 0);

		assertTimeStampEqual(expectedTime, actualTime);
	}

	public void testSimpleItemRemind() {
		// StartPomodoroTrans startPomodoro = new StartPomodoroTrans();
		// startPomodoro.execute();
		//
		//startPomodoroWork();
	}

	@SuppressWarnings("unused")
	private void startPomodoroWork() {
		// 准备环境,设置当前时间为"2013-7-1 08:00"
		mockTime.set("2013-7-1 08:00");

		// 情景正在进行"完善Item"的工作,休息时间要"喝水"和"做眼操"
		AddItemTrans addItem = new AddItemTrans("2013-7-1 21:30", "完善Item");
		addItem.execute();

		Item work = new Item("2013-7-1 21:30", "完善Item");

		// work.execute();
		// start trace
		// send reminder
		// recieve reminder
		// end trace and save to disk
		// execute rest

		AlarmManager am = (AlarmManager) getContext().getSystemService(
				Context.ALARM_SERVICE);

		Intent intent = new Intent(GtdAction.REMIND);
		PendingIntent sender = PendingIntent.getBroadcast(getContext(), 0,
				intent, PendingIntent.FLAG_CANCEL_CURRENT);

		// TODO: TimeUtil.getCurrentTime():long
		long remindTime = Calendar.getInstance().getTimeInMillis() + 25 * 60 * 1000;
		am.set(AlarmManager.RTC_WAKEUP, remindTime, sender);

		mockTime.set("2013-7-1 08:25");
		// 检查reminder执行情况

	}

	public void testInitPromodoroRemind() {
		// 准备环境,设置当前时间为"2013-7-1 08:00"
		mockTime.set("2013-7-1 08:00");
		
		AnGtdManager gtdManager = AnGtdManager.getGlobalGtdManager(getContext());
		gtdManager.reset();
		
		gtdManager.setState(PromodoroSchedule.WORKING);
		long remindTime = gtdManager.getNextRemindTime();
		
		gtdManager.setNextReminder(remindTime, getContext());
		
		mockTime.set("2013-7-1 08:25");

	}
	
	public void testChangeWhenStop(){
		// 准备环境,设置当前时间为"2013-7-1 08:00"
		mockTime.set("2013-7-1 08:00");
		
		AnGtdManager gtdManager = AnGtdManager.getGlobalGtdManager(getContext());
		gtdManager.reset();
		
		gtdManager.startWork();
	}
}
