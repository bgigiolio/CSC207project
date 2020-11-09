import java.util.List;

public class Organizer extends Attendee {

    String username;
    String password;
    String userid;
    List<Attendee> friendList; // list of username?
    List<Message> messages;
    List<Event> registration;
    String role;

    public Organizer(String username, String password) {
        super(username, password);
        this.role = "organizer";
    }

    // public boolean createRoom;
    // DO LATER: not sure about how to create room since we're only having one room ?

    public Speaker createSpeakerAccount (String username, String password) {
        return new Speaker(username, password); }

    public void assignSpeaker (String speakerUsername, Talk talk){
        talk.setSpeaker(speakerUsername); // implement Speaker first, change later
    }

}

