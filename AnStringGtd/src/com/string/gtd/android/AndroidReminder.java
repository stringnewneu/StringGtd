package com.string.gtd.android;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class AndroidReminder extends Reminder implements Parcelable {

	/**
	 * 获取当前类的类名
	 */
	protected static final String getThisClassName() {
		String thisClassName = new Object() {
			public String getClassName() {
				String clazzName = this.getClass().getName();
				return clazzName.substring(0, clazzName.lastIndexOf('$'));
			}
		}.getClassName();
		return thisClassName;
	}

	public static final Parcelable.Creator<AndroidReminder> CREATOR = 
			new Parcelable.Creator<AndroidReminder>() {
		public AndroidReminder createFromParcel(Parcel p) {
			return null;
			// return new AndroidReminder(p);
		}

		public AndroidReminder[] newArray(int size) {
			return new AndroidReminder[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public abstract void writeToParcel(Parcel dest, int flags);

	@Override
	public void Remind() {
		// TODO Auto-generated method stub

	}
}
