package com.string.gtd.core.test;

import com.string.gtd.core.StringTimeUtils;

import junit.framework.TestCase;

public class TestTimeUtil extends TestCase {

	public void test_getLongDate() {
		long time = StringTimeUtils.getLongDate("2013-7-1 23:30");
		long timeExpected = StringTimeUtils.getTime(2013, 7, 1, 23, 30, 00);

		assertEquals(timeExpected, time);
	}
	
	
}
