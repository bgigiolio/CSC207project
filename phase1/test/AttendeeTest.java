import Entities.Attendee;

import static org.junit.Assert.assertEquals;


public class AttendeeTest {

    public void testAttendee(){
        Attendee a = new Attendee("a", "a123");
    }
    public void testAttedeeGetters(){
        Attendee a = new Attendee("a", "a123");
        assertEquals("incorrect username getter", "a", a.getUsername());
        assertEquals("incorrect password getter", "a123", a.getPassword());
        assertEquals("incorrect friendlist getter", "a123", a.getPassword());
        assertEquals("incorrect password getter", "a123", a.getPassword());

    }

    public void testAttendeeSetters(){
        Attendee a = new Attendee("b", "b123");

    }
}
