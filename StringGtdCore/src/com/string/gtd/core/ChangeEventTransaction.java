package com.string.gtd.core;

import java.util.UUID;

public abstract class ChangeEventTransaction implements Transaction {
  public ChangeEventTransaction(UUID uuid)
  {
    this.itsUuid = uuid;
  }
  
  public void Execute() {
    Event e = EventDatabase.Instance().GetEvent(itsUuid);
    if(e != null)
      Change(e);
//    else
//      throw new Exception("Cannot find event: " + itsUuid.toString());
  }

  protected abstract void Change(Event e);

  private UUID itsUuid;
}
