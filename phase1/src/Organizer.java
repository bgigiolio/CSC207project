import java.util.List;

public class Organizer extends Attendee {

    private String username;
    private String password;
    //String userid;
    private List<Attendee> friendList;  //shouldn't contain entities,
    private List<Message> messages;     //shouldn't contain entities
    private List<Event> eventsRegistered;   //shouldn't contain entities
    private boolean loggedIn;
    private String role;

    public Organizer(String username, String password) {
        super(username, password);
        this.role = "organizer";
    }

    // public boolean createRoom;
    // DO LATER: not sure about how to create room since we're only having one room ?

    public Speaker createSpeakerAccount (String username, String password) {
        return new Speaker(username, password); }

    public boolean assignSpeaker (String speakerUsername, Talk talk){
        if (talk.getSpeaker().equals("None")) {
        talk.setSpeaker(speakerUsername);
        return true;
        } else { return false; }
    }

}

