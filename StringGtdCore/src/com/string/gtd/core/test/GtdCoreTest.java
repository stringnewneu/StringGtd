package com.string.gtd.core.test;
import com.string.gtd.core.*;
import java.util.*;

import junit.framework.TestCase;

public class GtdCoreTest extends TestCase {
  // ����¼���������
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

  // ɾ���¼���������
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
  
  // �ı��¼����Ʋ���
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

  // �ı��¼���ʼʱ�����
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

  // �ı��¼���ʼʱ�����
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

  // ����¼����Ѳ���
  public void testPcRemider() {
    UUID uuid = UUID.randomUUID();
    Calendar startTime = Calendar.getInstance(); 
    int duration = 1000; // in millisecond
    
    // ����¼�
    AddEventTransaction t = new AddEventTransaction(uuid, "Event6", startTime, duration);
    t.Execute();
    Event e = EventDatabase.Instance().GetEvent(uuid);
    
    // �������
    AddPcReminder PcRmd = new AddPcReminder(e);
    PcRmd.Execute();

    assertTrue(EventHasRemindTask(e, PcReminder.class));
  } 
  
  // ����¼���ʼ���������
  public void testPcRemiderBeginAndEnd()
  {
    UUID uuid = UUID.randomUUID();
    Calendar startTime = Calendar.getInstance(); 
    int duration = 1000; // in millisecond
    int reminderDuration = 50;
    
    // ����¼�
    AddEventTransaction t = new AddEventTransaction(uuid, "Event6", startTime, duration);
    t.Execute();
    Event e = EventDatabase.Instance().GetEvent(uuid);
    e.SetReminderDuration(reminderDuration );
    
    // �������
    AddPcReminder PcRmd = new AddPcReminder(e);
    PcRmd.Execute();
    assertTrue(EventHasRemindTask(e, PcReminder.class));
    e.RegisterReminders();
    
    int timerErrorTolerance = 30; // ��ʱ�����
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
