package main.java.UseCases;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.scene.layout.Pane;
import main.java.Entities.Event;
import main.java.Entities.PanelDiscussion;
import main.java.Entities.Talk;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    public void addEvent(String title, String loc, LocalDateTime datetime, int dur, int eventCap, String type){
        switch (type) {
            case "event": {
                Event e = new Event(title, loc, datetime, dur, eventCap, "event");
                events.put(e.getUUID(), e);
                break;
            }
            case "talk": {
                Talk e = new Talk(title, loc, datetime, dur, eventCap, "talk");
                talks.put(e.getUUID(), e);
                break;
            }
            case "panelDiscussion": {
                PanelDiscussion e = new PanelDiscussion(title, loc, datetime, dur, eventCap, "panelDiscussion");
                panelDiscussions.put(e.getUUID(), e);
                break;
            }
        }
    }

    public boolean deleteEvent(UUID eventId){
        if(events.remove(eventId) != null) return true;
        if(talks.remove(eventId) != null) return true;
        return panelDiscussions.remove(eventId) != null;
    }

    public boolean changeEventCapacity(UUID eventId, int newCap){
        Event e;
        e = events.get(eventId);

        if(e != null) {
            e.setCapacity(newCap);
            return true;
        }
        e = talks.get(eventId);
        if(e != null) {
            e.setCapacity(newCap);
            return true;
        }
        e = panelDiscussions.get(eventId);
        if(e != null) {
            e.setCapacity(newCap);
            return true;
        }
        return false;
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
}
