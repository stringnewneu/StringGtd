package com.string.gtd.core;

import java.util.*;

public abstract class Reminder
{
  private ReminderBeginTask itsReminderBeginTask = new ReminderBeginTask(this);
  private ReminderEndTask itsReminderEndTask = new ReminderEndTask(this);

  public void RegisterReminder(Calendar startTime, int duartion)
  {
    Calendar endTime = (Calendar) startTime.clone();
    endTime.add(Calendar.MILLISECOND, duartion);
    
    ReminderTimer.Instance().schedule(itsReminderBeginTask, startTime.getTime());
    ReminderTimer.Instance().schedule(itsReminderEndTask, endTime.getTime());    
  }
  
  protected abstract void StartRemind();

  protected abstract void EndRemind();
  
  static abstract class ReminderTask extends TimerTask
  {
    protected Reminder itsReminder;
    
    public ReminderTask(Reminder rmd)
    {
      this.itsReminder = rmd;
    }
  }
  
  static class ReminderBeginTask extends ReminderTask
  {    
    public ReminderBeginTask(Reminder rmd)
    {
      super(rmd);
    }
    
    @Override
    public void run() 
    {
      this.itsReminder.StartRemind();
    }
  }
  
  static class ReminderEndTask extends ReminderTask
  {    
    public ReminderEndTask(Reminder rmd)
    {
      super(rmd);
    }
    
    @Override
    public void run() 
    {
      this.itsReminder.EndRemind();
    }
  }

}
