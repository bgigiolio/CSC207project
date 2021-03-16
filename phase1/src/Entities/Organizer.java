package Entities;

import java.io.Serializable;
import java.util.List;

/**
 * <h1>Organizer</h1>
 * Represents an Organizer in the system.
 *
 * @author Morgan Chang
 */
public class Organizer extends Attendee implements Serializable {

    /**
     * The role of the organizer.
     */
    private final String role;

    /**
     * Construct an Organizer object when signed up.
     * Initialized with a username, a password, an empty list of friends, and an empty list of
     * inbox messages.
     * loggedIn is initialized to be false and role is initialized to be "organizer".
     *
     * @param username the username of the user.
     * @param password the password of the user.
     */
    public Organizer(String username, String password) {
        super(username, password);
        this.role = "organizer";
    }

//    public Speaker createSpeakerAccount (String username, String password) {
//        return new Speaker(username, password); }

//    public boolean assignSpeaker (String speakerUsername, Talk talk){
//        if (talk.getSpeaker().equals("None")) {
//        talk.setSpeaker(speakerUsername);
//        return true;
//        }
//        return false;
//    }

}

