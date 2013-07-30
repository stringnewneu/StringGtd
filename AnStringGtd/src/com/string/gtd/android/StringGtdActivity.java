package com.string.gtd.android;

import java.io.FileOutputStream;
import java.io.IOException;


import com.string.gtd.core.GuiManager;
import com.string.gtd.core.PromodoroSchedule;
import com.string.gtd.core.StringTimeUtils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StringGtdActivity extends Activity {

	AnGtdManager gtdManager;
	AnGuiManager guiManager;
	static Bundle extras = new Bundle();

	private TextView tvInfo; 	// 显示时间等信息
	private Button btChange; 	// 改变状态按钮
	private Button btStop; 	// 停止计时按钮

	private Handler guiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			if (msg.what == GuiManager.UPDATEGUI) { // 更新GUI
				tvInfo.setText(guiManager.getInfo());
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_string_gtd);

		gtdManager = AnGtdManager.getGlobalGtdManager(this
				.getApplicationContext());

		initGui();
	}

	@Override
	protected void onResume() {
		super.onResume();
//		String bbb = extras.getString("aaa", null);
//		bbb = "ccc";

		guiManager = (AnGuiManager) gtdManager.getGuiManager();
		gtdManager.updateContext(getApplicationContext()); // 更新gtdManager及其guiManager的context
		guiManager.registerHandler(guiHandler);
		guiManager.setGuiAvailable();
	}

	// 写数据到SD中的文件
	public void writeFileSdcardFile(String fileName, String write_str)
			throws IOException {
		try {

			FileOutputStream fout = new FileOutputStream(fileName);
			byte[] bytes = write_str.getBytes();

			fout.write(bytes);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initGui() {
		tvInfo = (TextView) findViewById(R.id.tvInfo);
		long currentTime = StringTimeUtils.getCurrentTime();
		String strCurrentTime = StringTimeUtils.toString_HHmm(currentTime);
		tvInfo.setText(strCurrentTime);

		btChange = (Button) findViewById(R.id.btChange);
		btChange.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (gtdManager.getState() != PromodoroSchedule.WORKING)
					gtdManager.startWork();
			}
		});

		btStop = (Button) findViewById(R.id.btStop);
		btStop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.string_gtd, menu);
		return true;
	}

	@SuppressWarnings("unused")
	private void vibrateTry() {
		ViberateReminder r = new ViberateReminder();
		Intent viberate = new Intent("com.string.gtd.android.REMIND");
		viberate.putExtra(ViberateReminder.RAW_DATA, r.marshall());

		PendingIntent sendReminderToAlarmManager = PendingIntent.getBroadcast(
				this, 0, viberate, PendingIntent.FLAG_CANCEL_CURRENT);
		AlarmManager am = (AlarmManager) this
				.getSystemService(Context.ALARM_SERVICE);
		Long triggerAtMillis = android.os.SystemClock.elapsedRealtime() + 1 * 1000;
		am.set(AlarmManager.RTC_WAKEUP, triggerAtMillis,
				sendReminderToAlarmManager);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void updateElapsedTime() {

	}

}
