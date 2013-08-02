package com.string.gtd.core;

public abstract class GuiManager {
	public static final int UPDATEGUI = 0x0010;

	@SuppressWarnings("unused")
	private GtdManager gtdManager = null;
	private TextManager textManager = null;
	private String info;

	public GuiManager(GtdManager gtdManager) {
		this.gtdManager = gtdManager;
	}

	protected abstract boolean guiAvailable();

	protected abstract void updateGui();

	public void setGuiAvailable() {
		if (textManager != null)
			textManager.execute();
	}

	public String getInfo() {
		return info;
	}

	public void setTextManager(TextManager tm) {
		if(this.textManager != null)
			this.textManager.stop();
		this.textManager = tm;
	}

	public void setText(String text) {
		info = text;
		updateGui();
	}
}
