package Entities;

import java.util.ArrayList;

public class Speaker extends Attendee {
    private String username;
    private String password;
    //String userid;
    private List<Attendee> friendList;  //shouldn't contain entities,
    private List<Message> messages;     //shouldn't contain entities
    private List<Event> eventsRegistered;   //shouldn't contain entities
    private ArrayList<String> talks;    //holds talk ids
    private boolean loggedIn;
    private String role;

    public Speaker(String username, String password) {
        super(username, password);
        this.role = "speaker";
        this.talks = new ArrayList<>();
    }

    public void addTalk(String talkId){
        this.talks.add(talkId);
    }

    public Object getTalks(){
        return this.talks.clone();
    }
}