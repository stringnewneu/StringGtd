package com.string.gtd.android;

import android.content.Intent;
import android.os.Parcel;
//import android.os.Parcelable;

public class ViberateReminder extends AndroidReminder{
	public static final String RAW_DATA = getThisClassName() + ".RAW_DATA";
	public static String INSTANCE  = getThisClassName() + ".INSTANCE";
	
	public long duration; // in milliseconds

	public ViberateReminder() {
		duration = 2 * 1000;
	}

	public ViberateReminder(Parcel p) {
		duration = p.readLong();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel p, int flags) {
		p.writeLong(duration);
	}

	public byte[] marshall() {
		Parcel out = Parcel.obtain();
		writeToParcel(out, 0);
		out.setDataPosition(0);
		byte[] result = out.marshall().clone();
		out.recycle();
		return result;
	}

	public static ViberateReminder getReminder(Intent intent) {
		ViberateReminder reminder = null;

		final byte[] rawReminderData = intent.getByteArrayExtra(RAW_DATA);
		if (rawReminderData != null) {
			Parcel in = Parcel.obtain();
			in.unmarshall(rawReminderData, 0, rawReminderData.length);
			in.setDataPosition(0);
//			reminder = ViberateReminder.CREATOR.createFromParcel(in);
			in.recycle();
		}

		return reminder;
	}
}
