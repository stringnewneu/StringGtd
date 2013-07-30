package com.string.gtd.core.old;

import java.util.*;

public class EventDatabase {
  private Hashtable<UUID, Event> itsEvent = new Hashtable<UUID, Event>();
  private static final EventDatabase theGlobalInstance = new EventDatabase();
  public static EventDatabase Instance() { return theGlobalInstance; }
  
//  private String events[][] = {
//    //{ "事件名称", "开始时间", "结束时间" }
//      { "起床", "06:30-06:30" },
//      { "睡觉", "22:30-22:30" }
//    };
  
  private EventDatabase() 
  {

  }
  

  public Hashtable<UUID, Event> GetEvents() { return itsEvent; }
  public Event GetEvent(UUID uuid) { return itsEvent.get(uuid); }
  public void AddEvent(UUID uuid, Event event) { this.itsEvent.put(uuid, event); }
  public void DeleteEvent(UUID uuid) { this.itsEvent.remove(uuid); }
}
