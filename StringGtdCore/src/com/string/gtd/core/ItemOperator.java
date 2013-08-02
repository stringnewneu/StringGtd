package com.string.gtd.core;

public class ItemOperator {
	private long startTime = 0; // ��ʼʱ�̣�ʱ��㣩
	private long preEndTime = 0; // Ԥ��������ʱ�̣�ʱ��㣩
	private long plannedEndTime = 0; // �ƻ�����ʱ�̣�ʱ��㣩

	private long preEndDuration = 60 * STimeUtils.s2ms; // Ԥ����ʱ�䣨ʱ��Σ�
	private Item item;
	private int state = 0; // �¼�ִ��״̬
	private GtdManager gtdManager;
	private GuiManager gui;
	
	public static final int MainState = 1; // �¼���Ҫִ��״̬
	public static final int PreEndState = 2; // Ԥ����״̬
	public static final int DelayState = 3; // ��ʱ״̬

	public ItemOperator(Item item, GtdManager gtdManager) {
		this.item = item;
		this.gtdManager = gtdManager;
		this.gui = gtdManager.getGuiManager();
	}

	public void init() {
		startTime = STimeUtils.curTime();
		plannedEndTime = startTime + item.duration;
		if(preEndTime < 10)
			preEndDuration = 0;
		preEndTime = plannedEndTime - preEndDuration;
		
		state = ItemOperator.MainState;
	}

	public void execute() {
		// ������һ�ε�����
		long remindTime = getNextRemindTime();
		gtdManager.setNextRemind(remindTime);
		
		TextManager tm = new TextManager(gui);
		tm.setInterval(1.0f);
		
		if (state == MainState || state == PreEndState){
			TextMaker countdown = new Countdown(plannedEndTime);
			tm.setTextMaker(countdown);
		}
		else if (state == DelayState){
			TextMaker countup = new Countup(plannedEndTime);
			tm.setTextMaker(countup);
		}
			
			
		tm.execute();

		gui.setTextManager(tm);
	}

	public long getNextRemindTime() {
		long getNextRemindTime = 0;
		switch (state) {
		case MainState:
			if (preEndDuration == 0)
				getNextRemindTime = plannedEndTime;
			else
				getNextRemindTime = preEndTime;
			break;
		case PreEndState:
			getNextRemindTime = plannedEndTime;
			break;
		}
		return getNextRemindTime;
	}
}
