package com.string.study.java;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import junit.framework.TestCase;

public class JavaStudyTest extends TestCase {
  public void testCalendar() {  
    Calendar startTime = Calendar.getInstance();
    startTime.getTimeInMillis();
    
    int reminderDuration = 1000; //in millisecond
    Calendar endTime = (Calendar) startTime.clone();
    endTime.add(Calendar.MILLISECOND, (int)reminderDuration);
    
    long diff = endTime.getTimeInMillis() - startTime.getTimeInMillis();
    assertEquals(diff, 1000);
    
    //测试0时刻
    Calendar epoct = Calendar.getInstance();
    epoct.set(1, 1, 1, 0, 0, 0); //没有完美的0年0月0日，最多只能是1年1月1日00:00:00.000
    epoct.set(Calendar.MILLISECOND, 0); 
    //System.err.println(epoct.get(Calendar.HOUR) + ":" + epoct.get(Calendar.MINUTE));
    //System.err.println(epoct);
  } 
  
  // 解析时间
  public void testDateFormat()
  {
    DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
    DateFormat df2 = new SimpleDateFormat("HH:mm:ss");   
    try  
    {   
        Date d1 = df1.parse("2004-03-26 13:31:40");   
        Date d2 = df2.parse("11:30:24");   
        long diff = d1.getTime() - d2.getTime();   
        @SuppressWarnings("unused")
        long days = diff / (1000 * 60 * 60 * 24);   
    }   
    catch (Exception e)   
    {   
    } 
  }
  
  public void testUUID() {  
    UUID uuid = UUID.randomUUID();
    System.out.println(uuid);
  } 
  
  public void testIs() {  
    ParentClass pc = new ParentClass();
    ChildClass cc1 = new ChildClass();
    ChildClass cc2 = new ChildClass();
    ParentClass fackParent = cc1;
    
    assertTrue(fackParent instanceof ParentClass);
    assertTrue(fackParent instanceof ChildClass);
    
    assertTrue(fackParent.getClass() != pc.getClass());
    System.out.println("fackParent's class is: " + fackParent.getClass());
    System.out.println("pc's class is: " + pc.getClass());
    System.out.println("cc2's class is: " + cc2.getClass());
  } 
  
  class ParentClass { }
  class ChildClass extends ParentClass { }
  
  public void testTimer()
  {
    Timer timer = new Timer();
    MyTask t1 = new MyTask("Task1");
    MyTask t2 = new MyTask("Task2");
    timer.scheduleAtFixedRate(t1, 10, 20);//在1秒后执行此任务,每次间隔2秒,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.
    timer.scheduleAtFixedRate(t2, 10, 20);//在1秒后执行此任务,每次间隔2秒,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.
    
    try
    {
      Thread.sleep(30);
      t1.cancel();
      Thread.sleep(50);
      timer.cancel();//使用这个方法退出任务c
    } catch (Exception e1)
    {
      e1.printStackTrace();
    }    
  }
  
  static class MyTask extends java.util.TimerTask
  {
    private String itsTaskName;
    
    public MyTask(String taskName)
    {
      this.itsTaskName = taskName;
    }
    
    @Override
    public void run() 
    {
        System.out.println(itsTaskName);
    }
  }

}

