package UseCases;

import Entities.*;

public class EventStatus {

    public boolean addUser(Event e, Attendee user) {
        if (e.getAttendees().contains(user.getUsername())) {
            return false;
        } else {
            e.addAttendees(user.getUsername());
            return true;
        }
     }

    public boolean removeUser(Event e, Attendee user){
        if (e.getAttendees().contains(user.getUsername())) {
            e.removeAttendees(user.getUsername());
            return true;
        } else {
            return false; }
     }

}
