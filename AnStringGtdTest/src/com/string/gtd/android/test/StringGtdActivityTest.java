package com.string.gtd.android.test;

import com.string.gtd.android.StringGtdActivity;

import android.media.MediaPlayer;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;

public class StringGtdActivityTest extends
		ActivityInstrumentationTestCase2<StringGtdActivity> {

	AnMockTime mockTime = null;

	private StringGtdActivity mActivity;
	private Button btChange;

	public StringGtdActivityTest() {
		super("com.string.gtd.android", StringGtdActivity.class);
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
		// 启动MockTime
		if (mockTime == null)
			mockTime = new AnMockTime(getInstrumentation().getContext());

		// 备份当前时间
		mockTime.backupTime();

		mActivity = getActivity();
		btChange = (Button) mActivity
				.findViewById(com.string.gtd.android.R.id.btChange);
	}

	@Override
	public void tearDown() throws Exception {
		// 恢复当前时间,不能放在teardown之后，会被销毁的。
		mockTime.restoreTime();

		super.tearDown();
	}

	@UiThreadTest
	public void testChange() {
		// 设定时间
		mockTime.set("2013-7-1 08:00");

		// 点击"-->"按钮
		btChange.requestFocus();
		btChange.performClick();

		// 经过25min
		mockTime.elapseInMinutes(25);

		// 时间到铃响
		MediaPlayer player = MediaPlayer.create(
				mActivity.getApplicationContext(),
				com.string.gtd.android.R.raw.fallbackring);
		player.setLooping(true);
		player.start();
		// 进入休息周期

		// 时间到铃响
		// 进入工作周期
	}
}
