package main.java.UseCases;

import main.java.Entities.Event;
import main.java.Entities.PanelDiscussion;
import main.java.Entities.Talk;

import java.time.LocalDateTime;

public class EventFactory {
    public Event getEvent(String title, String loc, LocalDateTime datetime, int dur, int eventCap, String type){
        if(type == null) return null;

        switch(type){
            case "event":
                return new Event(title, loc, datetime, dur, eventCap);
            case "talk":
                return new Talk(title, loc, datetime, dur, eventCap);
            case "panelDiscussion":
                return new PanelDiscussion(title, loc, datetime, dur, eventCap);
        }
        return null;
    }
}
