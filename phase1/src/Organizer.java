import java.util.List;

public class Organizer extends Attendee {

    String username;
    String password;
    String userid;
    List<Attendee> friendlist; // list of username?
    List<Message> messages;
    List<Event> registration;

    public Organizer(String username, String password) {
        super(username, password);
    }

    // public boolean createRoom;
    // DO LATER: not sure about how to create room since we're only having one room ?

    // public Speaker createSpeakerAccount (String username, String password) {
        // return Speaker(username, password) } // implement Speaker first

    public void assignSpeaker (String speakerUsername, Talk talk){
        talk.setSpeaker(speakerUsername); // implement Speaker first, change later
    }

}

