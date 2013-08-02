package com.string.gtd.core.test;


import com.string.gtd.core.Item;
import com.string.gtd.core.STimeUtils;
import junit.framework.TestCase;

public class GtdCoreTest extends TestCase {

	public void testItem_toString(){
		Item item = new Item("2013-7-1 21:30", "完善Item");
		
		long expectTime = STimeUtils.getTime(2013, 7-1, 1, 21, 30, 00);
		
		assertEquals(expectTime, item.plannedTime);
		assertEquals("完善Item", item.name);
	}

	public void testSimpleItem() {
//		Item work = new Item();
//		Calendar executeTime = Calendar.getInstance();
//		executeTime.set(2013, 2, 7, 10, 45, 10);
//		work.plannedTime = executeTime;

		// ItemDB.AddItem(work);

		// 工作开始
		// MockTime.setTime("2013-01-01 08:00");

		// 设置经过25min
		// MockTime.elapse("25:00");

		// 工作间休检测
		// 设置经过5min

	}
}
