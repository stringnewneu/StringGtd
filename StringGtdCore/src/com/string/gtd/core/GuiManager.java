package com.string.gtd.core;

import java.util.Timer;
import java.util.TimerTask;

public abstract class GuiManager {
	public static final int UPDATEGUI = 0x0010;

	private CountdownObject countdownObj;

	private String info;

	Timer timer = new Timer();
	TimerTask secondTickTimer = new TimerTask() {
		@Override
		public void run() {
			if (guiAvailable()) {
				updateTimeInfo();
				updateGui();
			} else
				cancel();
		}
	};

	protected abstract boolean guiAvailable();

	protected abstract void updateGui();

	public void setGuiAvailable() {
		if (countdownObj != null && countdownObj.running())
			timer.schedule(secondTickTimer, 1000, 1000);// ��1���ִ�д�����,ÿ�μ��1��
	}

	public void startCountdown(long countdownTime) {
		countdownObj = new CountdownObject(countdownTime);
		countdownObj.start();

		// �����GUI�͸������ĵ���ʱ
		if (guiAvailable()) {
			timer.schedule(secondTickTimer, 1000, 1000);// ��1���ִ�д�����,ÿ�μ��1��
		}
	}

	public void stopCountdown() {
		countdownObj = null;
		timer.cancel();
	}

	private void updateTimeInfo() {
		long remainTime = countdownObj.getRemainTime();
		String strRemainTime = StringTimeUtils.toString_mmss(remainTime);
		String strCurrentTime = StringTimeUtils.currentTimeString_HHmm();

		info = strCurrentTime + " -- " + strRemainTime;
	}

	public String getInfo() {
		return info;
	}
}
