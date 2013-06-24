package com.string.gtd.core;

import java.util.*;

public class ChangeStartTimeTransaction extends ChangeEventTransaction {

  public ChangeStartTimeTransaction(UUID uuid, Calendar startTime)
  {
    super(uuid);
    this.itsStartTime = startTime;
  }

  @Override
  protected void Change(Event e)
  {
    e.SetStartTime(itsStartTime);
  }
  
  private Calendar itsStartTime;
}
