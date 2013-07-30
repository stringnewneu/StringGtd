package com.string.gtd.core.old;

import java.util.*;

public class EventDatabase {
  private Hashtable<UUID, Event> itsEvent = new Hashtable<UUID, Event>();
  private static final EventDatabase theGlobalInstance = new EventDatabase();
  public static EventDatabase Instance() { return theGlobalInstance; }
  
//  private String events[][] = {
//    //{ "�¼�����", "��ʼʱ��", "����ʱ��" }
//      { "��", "06:30-06:30" },
//      { "˯��", "22:30-22:30" }
//    };
  
  private EventDatabase() 
  {

  }
  

  public Hashtable<UUID, Event> GetEvents() { return itsEvent; }
  public Event GetEvent(UUID uuid) { return itsEvent.get(uuid); }
  public void AddEvent(UUID uuid, Event event) { this.itsEvent.put(uuid, event); }
  public void DeleteEvent(UUID uuid) { this.itsEvent.remove(uuid); }
}
