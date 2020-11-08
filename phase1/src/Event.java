import java.util.*;

public class Event {
    String title;
    String location;
    Calendar datetime; // Calendar.set(year + 1900, month, date, hrs, min)
    List<String> username;

    public Event(String title, String location, Calendar datetime) {
        this.title = title;
        this.location = location;
        this.datetime = datetime;
        this.username = new ArrayList<String>();
    }
    public String getTitle() {
        return this.title;
    }

    public String getLocation() {
        return this.location;
    }

    public Calendar getDatetime() {
        return this.datetime;
    }

    public List<String> getAttendees() {
        return this.username;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void setDatetime(Calendar datetime){
        this.datetime = datetime;
    }

    public void setAttendees(List<String> username) {
        this.username = username;
    }

    public Object[] getEventInfo(){
        return new Object[]{this.title, this.location, this.datetime, this.username};
    }

}
