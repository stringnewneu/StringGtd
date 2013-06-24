package com.string.gtd.core;

public abstract class AddReminderTransaction implements Transaction
{
  protected Event itsEvent;

  public AddReminderTransaction(Event e)
  {
    this.itsEvent = e;
  }

  public void Execute()
  {
    Reminder rmd = CreateRemder();
    itsEvent.AddReminder(rmd);    
  }

  protected abstract Reminder CreateRemder();
  
}
