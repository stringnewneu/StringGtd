package com.string.gtd.core.old;

import java.util.Calendar;

public class PcReminder extends Reminder
{
  private String itsEventName;
  private Calendar itsActuralBeginTime = Calendar.getInstance();
  private Calendar itsActuralEndTime = itsActuralBeginTime;
  private long itsActualDuration = 0;
  
  public PcReminder(String eventName)
  {
    itsEventName = eventName;    
  }
  
  @Override
  protected void StartRemind()
  {
    System.out.println(itsEventName + " begins.");
    itsActuralBeginTime = Calendar.getInstance();
  }
  
  @Override
  protected void EndRemind()
  {
    System.out.println(itsEventName + " ends.");
    itsActuralEndTime = Calendar.getInstance();
    itsActualDuration = itsActuralEndTime.getTimeInMillis()
        - itsActuralBeginTime.getTimeInMillis();
  }

  public long GetActualDuration()
  {
    return itsActualDuration;
  }

}
