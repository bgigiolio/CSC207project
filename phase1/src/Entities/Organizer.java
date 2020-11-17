package Entities;

import java.util.List;

public class Organizer extends Attendee {

    /**
     * Construct an Attendee object when signed up
     * Initialized with a username, a password, an empty list of friends, and an empty list of
     * inbox messages.
     * loggedIn is initialized to be false and role is initialized to be "attendee".
     *
     * @param username the username of the user.
     * @param password the password of the user.
     */
    public Organizer(String username, String password) {
        super(username, password);
        this.role = "organizer";
    }

    public Speaker createSpeakerAccount (String username, String password) {
        return new Speaker(username, password); }

    public boolean assignSpeaker (String speakerUsername, Talk talk){
        if (talk.getSpeaker().equals("None")) {
        talk.setSpeaker(speakerUsername);
        return true;
        } else { return false; }
    }

}

