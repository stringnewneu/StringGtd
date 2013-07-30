package com.string.gtd.core;

import java.util.UUID;

public class Item {
	public final UUID uuid = UUID.randomUUID();
	public String content;
	public long plannedTime;

	public Item() {
	}

	public Item(String plannedTime, String content) {
		this.plannedTime = StringTimeUtils.getLongDate(plannedTime);
		this.content = content;
	}


}
