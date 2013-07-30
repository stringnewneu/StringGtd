package com.string.gtd.core;

public class AddItemTrans implements Transaction {
	private Item itsItem;

	public AddItemTrans(Item item) {
		itsItem = item;
	}

	public AddItemTrans(String plannedTime, String content) {
		itsItem = new Item(plannedTime, content);
	}

	public void execute() {
		ItemDB.AddItem(itsItem);
	}
}
