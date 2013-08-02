package com.string.gtd.core;

import java.util.Timer;
import java.util.TimerTask;

public class TextManager {
	private GuiManager gui;
	private TextMaker tm;
	private long interval = 0;		// ����Ƶ�ʣ�Ĭ��Ϊ0���������£�
	Timer timer = new Timer();
	TimerTask secondTickTimer = new TimerTask() {
		@Override
		public void run() {
			if (gui.guiAvailable()) {
				String text = tm.getText();
				gui.setText(text);
			} else
				cancel();
		}
	};
	
	public TextManager(GuiManager guiManager) {
		this.gui = guiManager;
	}
	
	public void setTextMaker(TextMaker tm) {
		this.tm = tm;
	}
	
	public void setInterval(float intervalInSecond) {
		this.interval = (long)(1000*intervalInSecond);
	}

	public void execute() {
		// �����GUI�͸������ĵ���ʱ
		if (gui.guiAvailable()) {
			timer.schedule(secondTickTimer, interval, interval);// ��1���ִ�д�����,ÿ�μ��1��
		}
	}
	
	public void stop() {
		timer.cancel();
	}
}
