package com.string.gtd.core;
import java.util.*;  

public class Event {
  private final UUID itsUuid;
  private String itsName;
  private Calendar itsStartTime;
  private int itsDuartion;
  private int itsReminderDuration = 30000; // in millisecond
  private ArrayList<Reminder> itsReminders = new ArrayList<Reminder>();  

  public Event(UUID uuid, String eventName, Calendar startTime,
      int duration)
  {
    this.itsName = eventName;
    this.itsUuid = uuid;
    this.itsStartTime = startTime;
    this.itsDuartion = duration;
  }

  public UUID GetUuid()
  { 
    return itsUuid; 
  }
  
  public String GetName() 
  {
    return itsName; 
  }
  
  public void SetName(String itsName) 
  {
    this.itsName = itsName;
  }

  public Calendar GetStartTime()
  {
    return itsStartTime;
  }

  public void SetStartTime(Calendar itsStartTime)
  {
    this.itsStartTime = itsStartTime;
  }
  
  public int GetDuration()
  {
    return itsDuartion;
  }

  public void SetDuration(int duration)
  {
    this.itsDuartion = duration;    
  }
  
  public long GetReminderDuration() 
  { 
    return itsReminderDuration; 
  }
  
  public void SetReminderDuration(int itsReminderDuration) 
  { 
    this.itsReminderDuration = itsReminderDuration; 
  }

  public void AddReminder(Reminder rmd)
  {
    Reminder r = GetReminder(rmd.getClass());
    if(r != null)
    {
      String errInfo = "Cannot add same type reminder in one event";
      assert false : errInfo;
      itsReminders.remove(r);
      //Logger.getGlobal().log(Level.SEVERE, errInfo);
    }
    
    itsReminders.add(rmd);
    //rmd.Register();
  }
  
  public ArrayList<Reminder> GetReminders() 
  { 
    return itsReminders; 
  }

  public void RegisterReminders()
  {
    for(Reminder reminder : itsReminders)
      reminder.RegisterReminder(itsStartTime, itsReminderDuration);
  }

  @SuppressWarnings("rawtypes")
  public Reminder GetReminder(Class rmdClass)
  {
    for(Reminder r : itsReminders)
    {
      if(r.getClass() == rmdClass)
        return r;
    }    
    return null;
  }

}
