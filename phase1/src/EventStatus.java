import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class EventStatus {

    public boolean addUser(Event e, Attendee user) {
        if (e.getAttendees().contains(user.getUsername())) {
            return false;
        } else {
            e.addUsername(user.getUsername());
            return true;
        }
     }

    public boolean removeUser(Event e, Attendee user){
        if (e.getAttendees().contains(user.getUsername())) {
            e.removeUsername(user.getUsername());
            return true;
        } else {
            return false; }
     }

     // Question: do we have to make the attribute username in event class private?
    // if so, we will not be able to access it in this class, and these methods will have to be
    // under event class
}
