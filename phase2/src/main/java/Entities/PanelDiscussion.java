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
                           LocalDateTime datetime, int duration, int capacity, String type) {
        super(title, location, datetime, duration, capacity, type);
    }

    public ArrayList<String> getSpeakers() {return this.speakers;}

    public void addSpeakers(ArrayList<String> speakers) {this.speakers.addAll(speakers);}

    public void removeSpeakers(ArrayList<String> speakers) {this.speakers.removeAll(speakers);}

    public boolean containSpeaker(String username) {
        for (String speaker : this.speakers) {
            if (speaker.equals(username)) {
                return true;
            }
        } return false;
    }
}
