package com.string.gtd.android;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;

import com.string.gtd.core.GuiManager;

public class AnGuiManager extends GuiManager {
	private Context context = null;
	private Handler guiHandler;

	public AnGuiManager() {
	}

	public void updateContext(Context context) {
		this.context = context;
	}

	@Override
	protected boolean guiAvailable() {
		if (context == null)
			return false;
		
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
		String topActivityName = cn.getClassName();
		String guiActivityName = StringGtdActivity.class.getCanonicalName();

		if (topActivityName.equals(guiActivityName))
			return true;
		else
			return false;
	}

	@Override
	protected void updateGui() {
		guiHandler.sendEmptyMessage(GuiManager.UPDATEGUI);

	}

	public void registerHandler(Handler guiHandler) {
		this.guiHandler = guiHandler;
	}


}
