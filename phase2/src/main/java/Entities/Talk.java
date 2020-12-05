package main.java.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * <h1>Talk</h1>
 * Instantiates Talk Event as an extension of events which enables the setting and getting of speakers.
 * @author Utkarsh Mali
 */

public class Talk extends Event implements EventWithSpeaker{
    /**
     * The username of speaker as a string.
     */
    private String speaker;

    /**
     * Constructor for <code>Talk</code>
     * @param title the title of the talk which the speaker is going to present.
     * @param speaker who hosts the talk.
     * @param location the location where the talk will happen.
     * @param datetime the date and time at which this talk will happen.
     * @param duration how long this event will be
     * @param capacity max event capacity
     */
    public Talk(String title, String speaker, String location, LocalDateTime datetime, int duration, int capacity) {
        super(title, location, datetime, duration, capacity);
        this.speaker = speaker;
    }
    /**
     * Returns the username of the speaker presenting in the talk as a string in an ArrayList
     * @return ArrayList containing speaker username
     */
    @Override
    public ArrayList<String> getSpeakers() {
        ArrayList<String> sp = new ArrayList<>(1);
        sp.add(this.speaker);
        return sp;
    }
    /**
     * Sets the username of the speaker presenting in the talk as a string
     * @param speaker the username of the speaker in an ArrayList
     */
    @Override
    public void setSpeakers(ArrayList<String> speaker) {
        this.speaker = speaker.get(0);
    }
}
