package UseCases;

import Entities.*;
import java.util.ArrayList;
import java.util.List;

public class EventStatus {

    private Event event;
    private List<Attendee> attendees;

    public EventStatus(Event e) {
        event = e;
        this.attendees = new ArrayList<>();
    }

    public boolean addUser(Attendee user) {
        if (this.event.getAttendees().contains(user.getUsername())) {
            return false;
        } else {
            this.event.addAttendees(user.getUsername());
            this.attendees.add(user);
            return true;
        }
     }

    public boolean removeUser(Attendee user){
        if (this.event.getAttendees().contains(user.getUsername())) {
            this.event.removeAttendees(user.getUsername());
            this.attendees.add(user);
            return true;
        } else {
            return false; }
     }

}
