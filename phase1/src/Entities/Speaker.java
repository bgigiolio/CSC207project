package Entities;

import java.util.ArrayList;
import java.util.List;

public class Speaker extends Attendee {

    private ArrayList<String> talks;    //holds talk ids
    private final String role = "speaker";

    /**
     * Construct an Speaker object when signed up.
     * Initialized with a username, a password, an empty list of friends, and an empty list of
     * inbox messages.
     * loggedIn is initialized to be false and role is initialized to be "speaker".
     *
     * @param username the username of the user.
     * @param password the password of the user.
     */
    public Speaker(String username, String password) {
        super(username, password);
        this.talks = new ArrayList<>();
    }

    public void addTalk(String talkId){
        this.talks.add(talkId);
    }

    public Object getTalks(){
        return this.talks.clone();
    }
}