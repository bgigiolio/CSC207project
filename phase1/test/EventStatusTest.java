import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

//INCOMPLETE
public class EventStatusTest {

    public void testEventStatus(){
        Event e = new Event("Event", "B500", LocalDateTime.of(2020,10,20,13,30));
        EventStatus eventstatus = EventStatus(e);
    }

    public void testEventStatusMethods(){
        Event e = new Event("Event", "B500", LocalDateTime.of(2020,10,20,13,30))
        EventStatus eventstatus = EventStatus(e);

        eventstatus.addUser("a");
        eventstatus.addUser("b");
        eventstatus.addUser("a");
        ArrayList<String> usernames = new ArrayList<>(Arrays.asList("a","b"));
        assertEquals("incorrect addUser implementation", usernames, e.getAttendees());

        eventstatus.removeUser("a");
        eventstatus.removeUser("a");
        ArrayList<String> usernames1 = new ArrayList<>(Collections.singletonList("b"));
        assertEquals("incorrect removeUser implementation", usernames1, e.getAttendees());

    }
}
