package com.string.gtd.core;

import java.util.UUID;


public class ChangeDuratrionTransaction extends ChangeEventTransaction
{

  public ChangeDuratrionTransaction(UUID uuid, int duration)
  {
    super(uuid);
    this.itsDuration = duration;
  }

  @Override
  protected void Change(Event e)
  {
    e.SetDuration(itsDuration);

  }

  int itsDuration;
}
