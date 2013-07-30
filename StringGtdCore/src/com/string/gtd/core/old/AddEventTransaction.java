package com.string.gtd.core.old;

import java.util.*;

public class AddEventTransaction implements Transaction {
  private UUID itsUuid;
  private String itsName;
  private Calendar itsStartTime;
  private int itsDuartion;

  public AddEventTransaction(UUID uuid, String eventName, Calendar startTime,
      int duration)
  {
    this.itsUuid = uuid;
    this.itsName = eventName;
    this.itsStartTime = startTime;
    this.itsDuartion = duration;
  }

  public void Execute() {
    Event e = new Event(itsUuid, itsName, itsStartTime, itsDuartion);
    EventDatabase.Instance().AddEvent(e.GetUuid(), e);
  }
}
