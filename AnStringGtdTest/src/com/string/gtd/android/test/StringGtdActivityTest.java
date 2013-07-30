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
		// ����MockTime
		if (mockTime == null)
			mockTime = new AnMockTime(getInstrumentation().getContext());

		// ���ݵ�ǰʱ��
		mockTime.backupTime();

		mActivity = getActivity();
		btChange = (Button) mActivity
				.findViewById(com.string.gtd.android.R.id.btChange);
	}

	@Override
	public void tearDown() throws Exception {
		// �ָ���ǰʱ��,���ܷ���teardown֮�󣬻ᱻ���ٵġ�
		mockTime.restoreTime();

		super.tearDown();
	}

	@UiThreadTest
	public void testChange() {
		// �趨ʱ��
		mockTime.set("2013-7-1 08:00");

		// ���"-->"��ť
		btChange.requestFocus();
		btChange.performClick();

		// ����25min
		mockTime.elapseInMinutes(25);

		// ʱ�䵽����
		MediaPlayer player = MediaPlayer.create(
				mActivity.getApplicationContext(),
				com.string.gtd.android.R.raw.fallbackring);
		player.setLooping(true);
		player.start();
		// ������Ϣ����

		// ʱ�䵽����
		// ���빤������
	}
}
