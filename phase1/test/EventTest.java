//import Entities.Event;
//
//import java.time.LocalDateTime;
//import java.util.*;
//
//import static org.junit.Assert.*;
//
//
//public class EventTest {
//
//    public void testEvent(){
//        LocalDateTime ldt = LocalDateTime.of(2020, 11, 10, 13,30);
//        Event e = new Event("Entities.Talk", "A300", ldt);
//    }
//
//    public void testEventGetters(){
//        LocalDateTime ldt = LocalDateTime.of(2020, 11, 10, 13,30);
//        Event e = new Event("Entities.Talk", "A300", ldt);
//
//        assertEquals("incorrect title getter", "Entities.Talk", e.getTitle());
//        assertEquals("incorrect location getter", "A300", e.getLocation());
//        assertEquals("incorrect datetime getter",
//                LocalDateTime.of(2020, 11, 10, 13,30), e.getDatetime());
//        assertEquals("incorrect usernames getter", new ArrayList<>(), e.getAttendees());
//        assertArrayEquals("incorrect eventinfo getter", new Object[] {"Entities.Talk", "A300",ldt,new ArrayList<>()}, e.getEventInfo());
//
//    }
//
//    public void testEventSetters(){
//        LocalDateTime ldt = LocalDateTime.of(2020, 11, 10, 13,30);
//        Event e = new Event("Entities.Talk", "A300", ldt);
//
//        e.setTitle("Speech");
//        assertEquals("incorrect title setter", "Speech", e.getTitle());
//        e.setLocation("A400");
//        assertEquals("incorrect location setter", "A400", e.setLocation());
//        LocalDateTime ldt2 = LocalDateTime.of(2020, 11, 10, 15,30);
//        e.setDatetime(ldt2);
//        assertEquals("incorrect datetime setter", ldt2, e.getDatetime());
//
//        ArrayList<String> usernames = new ArrayList<String>(Arrays.asList("a","b","c","d"));
//        e.setAttendees(usernames);
//        assertEquals("incorrect usernames setter", usernames, e.getAttendees());
//
//    }
//
//}
