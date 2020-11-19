package UseCases;

import Entities.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventStatus implements Serializable {

    private HashMap<String, List<String>> usernameToEvents;

    public EventStatus() {
        this.usernameToEvents = new HashMap<>();
    }

    public boolean signUpEvent(String username, String eventTitle) {
        List<String> eventsRegistered = usernameToEvents.get(username);
        if (!eventsRegistered.contains(eventTitle)) {
            eventsRegistered.add(eventTitle);
            usernameToEvents.put(username, eventsRegistered);
            return true;
        } else { return false; }
    }

    public boolean cancelEventEnrolment(String username, String eventTitle) {
        List<String> eventsRegistered = usernameToEvents.get(username);
        if (eventsRegistered.contains(eventTitle)) {
            eventsRegistered.remove(eventTitle);
            usernameToEvents.put(username, eventsRegistered);
            return true;
        } else { return false; }
    }

}
