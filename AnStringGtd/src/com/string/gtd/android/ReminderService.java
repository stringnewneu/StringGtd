package com.string.gtd.android;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Vibrator;

public class ReminderService extends Service {

	private Vibrator mVibrator;
	private static final long[] sVibratePattern = new long[] { 500, 500 };

	@Override
	public void onCreate() {
		mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		viberate(true);
		try {
			Thread.sleep(2 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		viberate(false);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private void viberate(boolean isViberate) {
		if (isViberate) {
			mVibrator.vibrate(sVibratePattern, 0);
		} else {
			mVibrator.cancel();
		}
	}
}
