package com.string.gtd.core.old;

import java.util.*;

public class DeleteEventTransaction implements Transaction {
  public DeleteEventTransaction(UUID uuid)
  {
    this.itsUuid = uuid;
  }
  
  public void Execute() {
    // TODO Auto-generated method stub
    EventDatabase.Instance().DeleteEvent(itsUuid);
  }
  
  private UUID itsUuid;
}
