package main.java.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PanelDiscussion extends Event {

    /**
     * the usernames of the speakers (multiple)
     */
    private final ArrayList<String> speakers = new ArrayList<>();

    /**
     * Constructor for <code>PanelDiscussion</code> class
     * @param title is the title of the event
     * @param location is the location where the event will be held.
     * @param datetime tells when the event is happening.
     * @param duration how long this event will be
     * @param capacity max event capacity
     */
    public PanelDiscussion(String title, String location,
                           LocalDateTime datetime, int duration, int capacity) {
        super(title, location, datetime, duration, capacity, "panelDiscussion");
    }

    public ArrayList<String> getSpeakers() {return new ArrayList<>(speakers);}

    public boolean addSpeakers(ArrayList<String> speakers) {
        ArrayList<String> toAdd = new ArrayList<>();

        for(String s: speakers){
            if(!this.speakers.contains(s))
                toAdd.add(s);
        }

        return speakers.addAll(toAdd);
    }

    public boolean addSpeaker(String username){
        if(!speakers.contains(username)){
            speakers.add(username);
            return true;
        }
        return false;
    }

    public boolean removeSpeakers(ArrayList<String> speakers) {return this.speakers.removeAll(speakers);}
}
