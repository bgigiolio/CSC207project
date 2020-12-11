package main.java.UseCases;

import main.java.Entities.Event;
import main.java.Entities.PanelDiscussion;
import main.java.Entities.Talk;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * <h1>Event Manager</h1>
 * Stores all the events created
 * @author Konstantinos Papaspyridis
 * @version phase2
 */
public class EventManager implements Serializable {

    private final HashMap<UUID, Event> events = new HashMap<>();
    private final HashMap<UUID, Talk> talks = new HashMap<>();
    private final HashMap<UUID, PanelDiscussion> panelDiscussions = new HashMap<>();

    public boolean addEvent(String title, String loc, LocalDateTime datetime, int dur, int eventCap, String type, BuildingManager bm){
        EventFactory eventFactory = new EventFactory();
        Event e = eventFactory.getEvent(title, loc, datetime, dur, eventCap, type);
        if(e == null) return false;
        if(!bm.addEvent(e, this)) return false;

        switch (type) {
            case "event":
                events.put(e.getUUID(), e);
                break;

            case "talk":
                talks.put(e.getUUID(), (Talk) e);
                break;

            case "panelDiscussion":
                panelDiscussions.put(e.getUUID(), (PanelDiscussion) e);
                break;
            default:
                return false;
        }
        return true;
    }

    protected Event getEvent(UUID id){
        Event e = events.get(id);
        if(e!=null) return e;
        e = talks.get(id);
        if(e!=null) return e;
        e = panelDiscussions.get(id);
        return e;
    }

    public ArrayList<Event> getEvents(){
        ArrayList<Event> toReturn = new ArrayList<>();

        for(UUID id:events.keySet())
            toReturn.add(events.get(id));

        for(UUID id:talks.keySet())
            toReturn.add(talks.get(id));

        for(UUID id:panelDiscussions.keySet())
            toReturn.add(panelDiscussions.get(id));

        return toReturn;
    }

    public boolean deleteEvent(UUID eventId){
        if(events.remove(eventId) != null) return true;
        if(talks.remove(eventId) != null) return true;
        return panelDiscussions.remove(eventId) != null;
    }

    public boolean changeCapacity(UUID eventId, int newCap){
        Event e;

        e = events.get(eventId);
        if(e==null) e = talks.get(eventId);
        if(e==null) e = panelDiscussions.get(eventId);
        if(e==null) return false;

        if(e.getAttendees().size()>newCap)
            e.removeAttendeesUntilCap(newCap);
        e.setCapacity(newCap);
        return true;
    }

    public LocalDateTime getEventStartTime(UUID id){
        Event e = events.get(id);
        if(e != null) return e.getDatetime();
        e = talks.get(id);
        if(e != null) return e.getDatetime();
        e = panelDiscussions.get(id);
        if(e != null) return e.getDatetime();
        return null;
    }

    public int getEventDuration(UUID id){
        Event e = events.get(id);
        if(e != null) return e.getDuration();
        e = talks.get(id);
        if(e != null) return e.getDuration();
        e = panelDiscussions.get(id);
        if(e != null) return e.getDuration();
        return -1;
    }

    public int getEventCapacity(UUID id){
        Event e = events.get(id);
        if(e != null) return e.getCapacity();
        e = talks.get(id);
        if(e != null) return e.getCapacity();
        e = panelDiscussions.get(id);
        if(e != null) return e.getCapacity();
        return -1;
    }

    public String getEventTitle(UUID id){
        Event e = events.get(id);
        if(e != null) return e.getTitle();
        e = talks.get(id);
        if(e != null) return e.getTitle();
        e = panelDiscussions.get(id);
        if(e != null) return e.getTitle();
        return null;
    }

    public String getEventLocation(UUID id){
        Event e = events.get(id);
        if(e != null) return e.getLocation();
        e = talks.get(id);
        if(e != null) return e.getLocation();
        e = panelDiscussions.get(id);
        if(e != null) return e.getLocation();
        return null;
    }

    public ArrayList<String> eventsAttending(String username){
        ArrayList<String> e = new ArrayList<>();

        for(UUID id : events.keySet()){
            if(events.get(id).getAttendees().contains(username))
                e.add(events.get(id).getTitle());
        }
        for(UUID id : talks.keySet()){
            if(talks.get(id).getAttendees().contains(username))
                e.add(talks.get(id).getTitle());
        }
        for(UUID id : panelDiscussions.keySet()){
            if(panelDiscussions.get(id).getAttendees().contains(username))
                e.add(panelDiscussions.get(id).getTitle());
        }
        return e;
    }

    public boolean addAttendee(UUID id, String username){
        Event e = events.get(id);
        if(e != null) return e.addAttendees(username);

        e = talks.get(id);
        if(e != null) return e.addAttendees(username);

        e = panelDiscussions.get(id);
        if(e != null) return e.addAttendees(username);

        return false;
    }

    public boolean removeAttendee(UUID id, String username){
        Event e = events.get(id);
        if(e != null) return e.removeAttendees(username);

        e = talks.get(id);
        if(e != null) return e.removeAttendees(username);

        e = panelDiscussions.get(id);
        if(e != null) return e.removeAttendees(username);

        return false;
    }

    public boolean setSpeaker(UUID id, String username){
        Talk talk = talks.get(id);

        if(talk != null){
            talk.setSpeaker(username);
            return true;
        }

        PanelDiscussion p = panelDiscussions.get(id);
        if(p != null){
            p.addSpeaker(username);
            return true;
        }
        return false;
    }

    public String getEventsOfSpeakerUsernameToString(String username){
        StringBuilder eventsString = new StringBuilder();

        for(UUID id : talks.keySet()){
            if(talks.get(id).getSpeaker().equals(username))
                eventsString.append(talks.get(id).toString());
        }
        for(UUID id : panelDiscussions.keySet()){
            if(panelDiscussions.get(id).getAttendees().contains(username))
                eventsString.append(panelDiscussions.get(id).toString());
        }
        return eventsString.toString();
    }

    public ArrayList<String> getAttendees(UUID id){
        Event e = events.get(id);
        if(e!=null) return e.getAttendees();
        e = talks.get(id);
        if(e!=null) return e.getAttendees();
        e = panelDiscussions.get(id);
        if(e!=null) return e.getAttendees();
        return null;
    }

    public ArrayList<UUID> getEventIDNoAttendees(){
        ArrayList<UUID> toReturn = new ArrayList<>();

        for(UUID id : events.keySet()){
            if(events.get(id).getAttendees().size() == 0)
                toReturn.add(id);
        }
        for(UUID id : talks.keySet()){
            if(talks.get(id).getAttendees().size() == 0)
                toReturn.add(id);
        }
        for(UUID id : panelDiscussions.keySet()){
            if(panelDiscussions.get(id).getAttendees().size() == 0)
                toReturn.add(id);
        }

        return toReturn;
    }
}
