package com.string.testhelper;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.string.testhelper.aidl.*;

public class TestHelperService extends Service {
	private static final String TAG = "TestHelperService";

	public class TestHelperServiceImpl extends ITestHelperService.Stub {
		@Override
		public boolean setSystemTime(long time) throws RemoteException {
			return TestHelper.setSystemTime(time);
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.v(TAG, "onCreate called");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.v(TAG, "onDestory() called");
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.v(TAG, "onStart() called");
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.v(TAG, "onBind() called");
		return new TestHelperServiceImpl();
	}

}
