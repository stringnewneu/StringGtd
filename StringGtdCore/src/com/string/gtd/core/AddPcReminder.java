package com.string.gtd.core;

public class AddPcReminder extends AddReminderTransaction
{
  private String itsEvnetName;
  public AddPcReminder(Event e)
  {
    super(e);
    this.itsEvnetName = e.GetName();
  }

  @Override
  protected PcReminder CreateRemder()
  {
    return new PcReminder(itsEvnetName);
  }
 
}
