package main.java.Entities;

import java.util.ArrayList;
import java.util.UUID;

/**
 * <h1>Speaker</h1>
 * Represents an Speaker in the system. Inherits from Attendee.
 *
 * @author Konstantinos Papaspyridis
 */
public class Speaker extends Attendee {

    /**
     * holds talks IDs
     */
    private final ArrayList<UUID> talks;

    /**
     * Construct an Speaker object when signed up.
     * Initialized with a username, a password, an empty list of friends, and an empty list of
     * inbox messages.
     * loggedIn is initialized to be false and role is initialized to be "speaker".
     * talks is initialized to an empty array list
     *
     * @param username the username of the user.
     * @param password the password of the user.
     */
    public Speaker(String username, String password, String role) {
        super(username, password, role);
        talks = new ArrayList<>();
    }

    /**
     * Adds a talk's id to the list of talks attending.
     * @param talkId the talk's id
     */
    public void addTalk(UUID talkId){
        talks.add(talkId);
    }

    public void removeTalk(UUID talkId) {talks.remove(talkId); }

    /**
     * Returns a shallow copy of the list containing talk IDs
     * @return shallow copy of <code>talks</code>
     */
    public ArrayList<UUID> getTalks(){
        return new ArrayList<>(talks);
    }
}