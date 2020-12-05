package UseCases;

import Entities.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventWithSpeakerFactory{
    public EventWithSpeaker getEvent(String eventType, String title, ArrayList<String> speakers, String location,
                          LocalDateTime datetime, int duration, int capacity){

        if(eventType==null)
            return null;

        else if(eventType.equalsIgnoreCase("TALK"))
            return new Talk(title, speakers.get(0), location, datetime, duration, capacity);

        else if(eventType.equalsIgnoreCase("PANELDISCUSSION"))
            return new PanelDiscussion(title, speakers, location, datetime, duration, capacity);

        else {
            System.out.println("Invalid event type: " + eventType);
            return null;
        }
    }
}
