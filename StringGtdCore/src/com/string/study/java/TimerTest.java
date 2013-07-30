package com.string.study.java;

import java.util.Timer;

import junit.framework.TestCase;

public class TimerTest extends TestCase {
	public void testTimer() {
		Timer timer = new Timer();
		MyTask t1 = new MyTask("Task1");
		MyTask t2 = new MyTask("Task2");
		timer.scheduleAtFixedRate(t1, 10, 20);// ��1���ִ�д�����,ÿ�μ��2��,�������һ��Data����,�Ϳ�����ĳ���̶���ʱ��ִ���������.
		timer.scheduleAtFixedRate(t2, 10, 20);// ��1���ִ�д�����,ÿ�μ��2��,�������һ��Data����,�Ϳ�����ĳ���̶���ʱ��ִ���������.

		try {
			Thread.sleep(30);
			t1.cancel();
			Thread.sleep(50);
			timer.cancel();// ʹ����������˳�����c
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	static class MyTask extends java.util.TimerTask {
		private String itsTaskName;

		public MyTask(String taskName) {
			this.itsTaskName = taskName;
		}

		@Override
		public void run() {
			System.out.println(itsTaskName);
		}
	}
}
