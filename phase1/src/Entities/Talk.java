package Entities;

import java.time.LocalDateTime;

/**
 * <h1>Talk</h1>
 * Instantiates Talk Event as an extension of events which enables the setting and getting of speakers.
 * @author Utkarsh Mali
 */

public class Talk extends Event{
    /**
     * The username of speaker as a string.
     */
    protected String speaker;

    /**
     * Constructor for <code>Talk</code>
     * @param title the title of the talk which the speaker is going to present.
     * @param speaker who hosts the talk.
     * @param location the location where the talk will happen.
     * @param datetime the date and time at which this talk will happen.
     */
    public Talk(String title, String speaker,String location, LocalDateTime datetime) {
        super(title, speaker, location, datetime);
    }
    /**
     * Gets the username of the speaker presenting in the talk as a string
     * @return speaker username
     */
    public String getSpeaker() {
        return speaker;
    }
    /**
     * Sets the username of the speaker presenting in the talk as a string
     * @param speaker the username of the speaker
     */
    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }
}
