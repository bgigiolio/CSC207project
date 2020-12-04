package Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PanelDiscussion extends Event implements EventWithSpeaker{
    /**
     * the usernames of the speakers (multiple)
     */
    private ArrayList<String> speakers;

    /**
     * Constructor for <code>PanelDiscussion</code> class
     * @param title is the title of the event
     * @param speakers is the list containing the usernames of the speakers who participate.
     * @param location is the location where the event will be held.
     * @param datetime tells when the event is happening.
     * @param duration how long this event will be
     */
    public PanelDiscussion(String title, ArrayList<String> speakers, String location, LocalDateTime datetime, int duration, int capacity) {
        super(title, location, datetime, duration, capacity);
        this.speakers = new ArrayList<>(speakers);
    }
    @Override
    public ArrayList<String> getSpeakers() {return new ArrayList<>(this.speakers);}

    @Override
    public void setSpeakers(ArrayList<String> s) {this.speakers = new ArrayList<>(s);}
}
