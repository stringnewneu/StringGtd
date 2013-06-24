package com.string.gtd.core.test;
import com.string.gtd.core.*;
import java.util.*;

import junit.framework.TestCase;

public class GtdCoreTest extends TestCase {
  // 添加事件操作测试
  public void testAddEventTransaction() {
    UUID uuid = UUID.randomUUID();
    Calendar startTime = Calendar.getInstance(); 
    int duration = 1000; // in millisecond
    
    AddEventTransaction t = new AddEventTransaction(uuid, "Event1", startTime, duration);
    t.Execute();
    Event e = EventDatabase.Instance().GetEvent(uuid);

    assertEquals("Event1", e.GetName());
    assertEquals(startTime.getTimeInMillis(), e.GetStartTime().getTimeInMillis());
    assertEquals(duration, e.GetDuration());      
  }     

  // 删除事件操作测试
  public void testDeleteEventTransaction()
  {
    UUID uuid = UUID.randomUUID();
    Calendar startTime = Calendar.getInstance(); 
    int duration = 1000; // in millisecond
    
    AddEventTransaction t = new AddEventTransaction(uuid, "Event2", startTime, duration);
    t.Execute();
    {
      DeleteEventTransaction dt = new DeleteEventTransaction(uuid);
      dt.Execute();
      assertNull(EventDatabase.Instance().GetEvent(uuid));
    }       
  }
  
  // 改变事件名称测试
  public void testChangeNameTransaction() {
    UUID uuid = UUID.randomUUID();
    Calendar startTime = Calendar.getInstance(); 
    int duration = 1000; // in millisecond
    
    AddEventTransaction t = new AddEventTransaction(uuid, "Event3", startTime, duration);
    t.Execute();
    Event e = EventDatabase.Instance().GetEvent(uuid);

    ChangeNameTransaction cnt = new ChangeNameTransaction(uuid, "Event3 New");
    cnt.Execute();
    
    assertEquals("Event3 New", e.GetName());
  }     

  // 改变事件开始时间测试
  public void testChangeStartTimeTransaction() {
    UUID uuid = UUID.randomUUID();
    Calendar startTime = Calendar.getInstance(); 
    int duration = 1000; // in millisecond
    
    AddEventTransaction t = new AddEventTransaction(uuid, "Event4", startTime, duration);
    t.Execute();
    Event e = EventDatabase.Instance().GetEvent(uuid);

    Calendar newStartTime = Calendar.getInstance();
    newStartTime.set(2012, 12, 23);
    ChangeStartTimeTransaction cnt = new ChangeStartTimeTransaction(uuid, newStartTime);
    cnt.Execute();
    
    assertEquals(newStartTime, e.GetStartTime());
  }     

  // 改变事件开始时间测试
  public void testChangeDuratrionTransaction() {
    UUID uuid = UUID.randomUUID();
    Calendar startTime = Calendar.getInstance(); 
    int duration = 1000; // in millisecond
    
    AddEventTransaction t = new AddEventTransaction(uuid, "Event5", startTime, duration);
    t.Execute();
    Event e = EventDatabase.Instance().GetEvent(uuid);

    int newDuration = 2000;
    ChangeDuratrionTransaction cnt = new ChangeDuratrionTransaction(uuid, newDuration);
    cnt.Execute();
    
    assertEquals(newDuration, e.GetDuration());
  }     

  // 添加事件提醒测试
  public void testPcRemider() {
    UUID uuid = UUID.randomUUID();
    Calendar startTime = Calendar.getInstance(); 
    int duration = 1000; // in millisecond
    
    // 添加事件
    AddEventTransaction t = new AddEventTransaction(uuid, "Event6", startTime, duration);
    t.Execute();
    Event e = EventDatabase.Instance().GetEvent(uuid);
    
    // 添加提醒
    AddPcReminder PcRmd = new AddPcReminder(e);
    PcRmd.Execute();

    assertTrue(EventHasRemindTask(e, PcReminder.class));
  } 
  
  // 添加事件开始与结束测试
  public void testPcRemiderBeginAndEnd()
  {
    UUID uuid = UUID.randomUUID();
    Calendar startTime = Calendar.getInstance(); 
    int duration = 1000; // in millisecond
    int reminderDuration = 50;
    
    // 添加事件
    AddEventTransaction t = new AddEventTransaction(uuid, "Event6", startTime, duration);
    t.Execute();
    Event e = EventDatabase.Instance().GetEvent(uuid);
    e.SetReminderDuration(reminderDuration );
    
    // 添加提醒
    AddPcReminder PcRmd = new AddPcReminder(e);
    PcRmd.Execute();
    assertTrue(EventHasRemindTask(e, PcReminder.class));
    e.RegisterReminders();
    
    int timerErrorTolerance = 30; // 计时器误差
    try
    {
      Thread.sleep(e.GetReminderDuration() + timerErrorTolerance);
    } catch (InterruptedException e1)
    {
      e1.printStackTrace();
    }
    PcReminder mr = (PcReminder) e.GetReminder(PcReminder.class);
    assertTrue(Math	.abs(mr.GetActualDuration() - reminderDuration) < timerErrorTolerance);
  } 
  
  @SuppressWarnings("rawtypes")
  public boolean EventHasRemindTask(Event e, Class rmdClass)
  {
    ArrayList<Reminder> rmdList = e.GetReminders();
    for(Reminder r : rmdList)
    {
      if(r.getClass() == rmdClass)
        return true;
    }
    return false;
  }

}
