import java.io.Serializable;
import java.util.*;
import java.time.*;

public class Event implements Serializable {
    String title;
    String location;
    LocalDateTime datetime;
    // Ex. LocalDateTime d = LocalDateTime.of(int YYYY, int MM, int DD, int hh, int mm, int ss)
    // The ss (seconds) parameter is optional.
    ArrayList<String> usernames;

    public Event(String title, String location, LocalDateTime datetime){
        this.title = title;
        this.location = location;
        this.datetime = datetime;
        this.usernames = new ArrayList<>();
    }
    public String getTitle() {
        return this.title;
    }

    public String getLocation() {
        return this.location;
    }

    public LocalDateTime getDatetime() {
        return this.datetime;
    }

    public ArrayList<String> getAttendees() {
        return this.usernames;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void setDatetime(LocalDateTime datetime){
        this.datetime = datetime;
    }

    public void setAttendees(ArrayList<String> usernames) {
        this.usernames = usernames;
    }

    public void addAttendee(String username) {
        this.usernames.add(username);
    }
    public void addAttendee(ArrayList<String> usernames) {
        this.usernames.addAll(usernames);
    }
    public void removeAttendee(String username) {
        this.usernames.remove(username);
    }
    public void removeAttendee(ArrayList<String> usernames) {
        this.usernames.removeAll(usernames);
    }

    public Object[] getEventInfo(){
        return new Object[]{this.title, this.location, this.datetime, this.usernames};
    }

}
