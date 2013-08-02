package com.string.gtd.core;

import java.util.UUID;
import java.util.Vector;

public class Item {
	public final UUID uuid = UUID.randomUUID();
	public String name;
	public long plannedTime;
	private Vector<Item> children = new Vector<Item>();
	public long duration = 0;	

	public Item() {
	}

	public Item(String name) {
		this.name = name;
	}

	public Item(String plannedTime, String content) {
		this.plannedTime = STimeUtils.getLongDate(plannedTime);
		this.name = content;
	}

	public void addItem(Item item) {
		children.add(item);
	}

	public String toString(){
		return name;
	}
}
