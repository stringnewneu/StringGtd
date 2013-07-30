package com.string.gtd.android.test;

import java.util.Calendar;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.string.gtd.core.MockTime;
import com.string.gtd.core.StringTimeUtils;
import com.string.testhelper.aidl.ITestHelperService;

public class AnMockTime extends MockTime {
	
	private ITestHelperService testHelperService = null;
	private final int min2ms = 60 * 1000;

	private Context context;
	private ServiceConnection serConn = new ServiceConnection() {
		// 此方法在系统建立服务连接时调用
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			testHelperService = ITestHelperService.Stub.asInterface(service);
			Log.v(StringGtdTest.TAG, "serConn testHelperService = "
					+ (testHelperService != null));
		}

		// 此方法在销毁服务连接时调用
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.v(StringGtdTest.TAG, "onServiceDisconnected()");
			testHelperService = null;
		}
	};

	public AnMockTime(Context context) {
		this.context = context;
		bindService();
	}

	public void bindService() {
		context.bindService(new Intent(ITestHelperService.class.getName()),
				serConn, Context.BIND_AUTO_CREATE);
		if (testHelperService != null)
			return;
		else {
			int count = 100;
			try {
				for (count = 0; count < 100; count++) {
					if (testHelperService == null)
						Thread.sleep(10);
					else
						break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Log.v(StringGtdTest.TAG, "setUp() wait service for " + count * 10
					+ "ms");
		}
	}

	@Override
	public void set(long systemTime) {
		try {
			testHelperService.setSystemTime(systemTime);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void set(String time) {
		long timeToSet = StringTimeUtils.getLongDate(time);
		set(timeToSet);
	}

	public void elapseInMinutes(int minutes) {
		long elapseTime = minutes * min2ms;
		long currentTime = Calendar.getInstance().getTimeInMillis();
		set(currentTime+elapseTime);
	}

}
