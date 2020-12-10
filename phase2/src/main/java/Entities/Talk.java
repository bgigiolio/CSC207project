package main.java.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * <h1>Talk</h1>
 * Instantiates Talk Event as an extension of events which enables the setting and getting of speakers.
 * @author Utkarsh Mali
 */
public class Talk extends Event {

    /**
     * The username of speaker as a string.
     */
    private String speaker;
    private final String type;

    /**
     * Constructor for <code>Talk</code>
     * @param title the title of the talk which the speaker is going to present.
     * @param location the location where the talk will happen.
     * @param datetime the date and time at which this talk will happen.
     * @param duration how long this event will be
     * @param capacity max event capacity
     */
    public Talk(String title, String location, LocalDateTime datetime, int duration, int capacity) {
        super(title, location, datetime, duration, capacity);
        this.type = "talk";
    }

    /**
     * Returns the username of the speaker presenting in the talk as a string in an ArrayList
     * @return Speaker's username
     */
    public String changeSpeaker() {
        return this.speaker;
    }

    /**
     * Sets the username of the speaker presenting in the talk as a string
     * @param speaker the username of the speaker in an ArrayList
     */
    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }
}
