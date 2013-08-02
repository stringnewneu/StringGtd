package com.string.gtd.core;

import java.util.Timer;
import java.util.TimerTask;

public class TextManager {
	private GuiManager gui;
	private TextMaker tm;
	private long interval = 0;		// 更新频率（默认为0，即不更新）
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
		// 如果有GUI就更新它的倒计时
		if (gui.guiAvailable()) {
			timer.schedule(secondTickTimer, interval, interval);// 在1秒后执行此任务,每次间隔1秒
		}
	}
	
	public void stop() {
		timer.cancel();
	}
}
