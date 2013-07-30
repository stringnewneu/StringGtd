package com.string.study.java;

import junit.framework.TestCase;
import java.util.Queue;
import java.util.LinkedList;

public class DataStuctrue_Test extends TestCase {

	public void testQueue() {
		Queue<String> queue = new LinkedList<String>();
		queue.offer("Hello");
		queue.offer("World!");
		queue.offer("ÄãºÃ£¡");
		System.out.println(queue.size());
		String str;
		while ((str = queue.poll()) != null) {
			System.out.print(str);
		}
		System.out.println();
		System.out.println(queue.size());
	}
}
