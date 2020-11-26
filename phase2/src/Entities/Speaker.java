package Entities;

import java.util.ArrayList;

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
    private ArrayList<String> talks;

    /**
     * set the role to "speaker"
     */
    private final String role;

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
        this.talks = new ArrayList<>();
        this.role = "Speaker";
    }

    /**
     * Adds a talk's id to the list of talks attending.
     * @param talkId the talk's id
     */
    public void addTalk(String talkId){
        this.talks.add(talkId);
    }

    /**
     * Returns a shallow copy of the list containing talk IDs
     * @return shallow copy of <code>talks</code>
     */
    public ArrayList<String> getTalks(){
        return this.talks;
    }
}