package com.string.gtd.core;
import java.util.Timer;

public class ReminderTimer extends Timer
{
  private static final ReminderTimer theGlobalInstance = new ReminderTimer();
  public static ReminderTimer Instance() { return theGlobalInstance; }
  
  private ReminderTimer() { }
}
