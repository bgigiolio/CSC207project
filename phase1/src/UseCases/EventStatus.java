package UseCases;

import Entities.*;
import java.util.ArrayList;
import java.util.List;

public class EventStatus {

    private Attendee user;
    private List<Event> eventsRegistered;

    public EventStatus(Attendee user){
        this.user = user;
        this.eventsRegistered = new ArrayList<>();
    }

    public boolean signUp(Event e) {
        if (!this.eventsRegistered.contains(e)) {
            this.eventsRegistered.add(e);
            return true;
        } else { return false; }
    }

    public boolean cancelEnrolment(Event e) {
        if (this.eventsRegistered.contains(e)) {
            this.eventsRegistered.remove(e);
            return true;
        } else { return false; }
    }

}
