package com.string.gtd.core;

import java.util.*;

/**
 * Singleton ItemDB
 * 
 * @author Administrator
 * 
 */
public class ItemDB {
	private static ItemDB db = new ItemDB();
	private Hashtable<UUID, Item> items = new Hashtable<UUID, Item>();

	private ItemDB() {
	}

	public static void AddItem(Item item) {
		db.items.put(item.uuid, item);
	}

	public static Item getNextItem() {
		Item nearestItem = null;
		for (Item item : db.items.values()) {
			if (nearestItem != null) {
				nearestItem = item.plannedTime < nearestItem.plannedTime ? item
						: nearestItem;
			} else
				nearestItem = item;
		}
		return nearestItem;
	}


}
