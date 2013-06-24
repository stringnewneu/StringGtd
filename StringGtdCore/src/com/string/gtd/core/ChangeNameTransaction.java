package com.string.gtd.core;

import java.util.UUID;


public class ChangeNameTransaction extends ChangeEventTransaction {

  public ChangeNameTransaction(UUID uuid, String name) 
  {
    super(uuid);
    this.itsName = name;
  }

  protected void Change(Event e) 
  {
    e.SetName(itsName);  
  }

  private String itsName;
}
