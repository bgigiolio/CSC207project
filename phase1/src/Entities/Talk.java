package Entities;

import java.time.LocalDateTime;

public class Talk extends Event{

    protected String speaker;

    public Talk(String title, String location, LocalDateTime datetime) {
        super(title, location, datetime);
        this.speaker = "None";
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }
}
