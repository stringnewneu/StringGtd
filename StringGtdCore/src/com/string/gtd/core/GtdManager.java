package com.string.gtd.core;

import java.util.Vector;

public abstract class GtdManager {
//	public static final int RemindAction = 0x0001;
//	public static final int ExecuteItemAction = 0x0002;
	
	protected PromodoroSchedule promodoroSchedule = new PromodoroSchedule();
	protected GuiManager gui = null;
	protected GtdState state = new GtdState();
	protected Item itemTree = new Item("RootItem"); // ������״�ṹ���¼�����ÿһ��Item����parent��ʾ��𣬶���StateTag��ʾ��������������ݿ��У�����д��org�ļ�
	// ��ͬ��Tag�����в�ͬ��break���ã�pomo����(4P+1R)��Ҳ��������break��ʱ��
	protected Item activeItem = null; // ��ǰ�ĻItem
	protected Vector<ItemOperator> activeItemOperators = new Vector<ItemOperator>();
	
	public GtdManager(){
		initializeItemTree();
		state.addStateTag(GtdStateTag.Working);
	}
	
	public void initializeItemTree(){
		Item anonymousPomoItem = new Item("anonymousPomoItem");
		itemTree.addItem(anonymousPomoItem);
		
		Item item1 = new Item("Design StringGtd");
		itemTree.addItem(item1);
		
		Item item2 = new Item("Wash clothes");
		item2.duration = STimeUtils.min2mm(30);
		itemTree.addItem(item2);
		
		activeItem = item2; // ����anonymousPomoItemΪĬ�ϵ�
	}
	
	public Item getActiveItem(){
		return activeItem;
	}

	public void startItem(){
		ItemOperator io = new ItemOperator(activeItem, this);
		activeItemOperators.add(io);
		io.init();
		io.execute();
	}
	
	protected abstract void setNextRemind(long remindTime);

	public void reset() {
		// TODO Auto-generated method stub

	}

	public void setState(int state) {
		promodoroSchedule.setState(state);
	}
	
	public GtdState getState() {
		return state;
	}

	public GuiManager getGuiManager(){
		return gui;
	}

}
