import java.util.List;

// Basic Implimentation of speaker, please edit.

public class Speaker extends Attendee {

    String username;
    String password;
    String userid;
    List<Attendee> friendlist; // list of username?
    List<Message> messages;
    List<Event> registration;
    String role;

    public Speaker(String username, String password) {
        super(username, password);
        this.role = "speaker";
    }
}