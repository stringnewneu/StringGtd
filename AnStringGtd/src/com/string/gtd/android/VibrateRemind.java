package com.string.gtd.android;

import android.content.Context;
import android.os.Parcel;
import android.os.Vibrator;

public class VibrateRemind extends AndroidTrans {

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() {
		final long[] sVibratePattern = new long[] { 500, 500 };

		if (context == null) {
			StringGtdLog
					.e("AndroidTrans.execute() must execute after AndroidTrans.setContext()");
			return;
		}
		Vibrator mVibrator = (Vibrator) this.context
				.getSystemService(Context.VIBRATOR_SERVICE);
		mVibrator.vibrate(sVibratePattern, 1);

	}

}
