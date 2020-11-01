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
    public String getTitle() {
        return this.title;
    }

    public String getLocation() {
        return this.location;
    }

    public String getDatetime() {
        return this.datetime;
    }

    public List<Attendee> getAttendees() {
        return this.attendees;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void setDatetime(String datetime){
        this.datetime = datetime;
    }

    public void setAttendees(List<Attendee> attendees) {
        this.attendees = attendees;
    }

    public Object[] getEventInfo(){
        return new Object[]{this.title, this.location, this.datetime, this.attendees};
    }

}
