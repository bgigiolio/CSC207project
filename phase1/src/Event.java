import java.util.ArrayList;
import java.util.List;


public class Event {
    String title;
    String location;
    String datetime; // "In the format DD.MM.YYYY - HH.MM"
    List<Attendee> attendees;


    public Event(String title, String location, String datetime) {
        this.title = title;
        this.location = location;
        this.datetime = datetime;
        this.attendees = new ArrayList<Attendee>();
    }
    public List<Attendee> getAttendees() {
        return this.attendees;
    }

    public void setAttendees(List<Attendee> attendees) {
        this.attendees = attendees;
    }

    public Object[] getEventInfo(){
        return new Object[]{this.title, this.location, this.datetime, this.attendees};
    }

}
