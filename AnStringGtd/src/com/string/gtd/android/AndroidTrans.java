package com.string.gtd.android;

import android.content.Context;
import android.os.Parcelable;

import com.string.gtd.core.Transaction;

public abstract class AndroidTrans implements Parcelable, Transaction {
	protected Context context = null;

	public void setContext(Context context){
		this.context = context;
	}
}
