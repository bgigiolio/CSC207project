package Entities;

import java.util.ArrayList;

public class Speaker extends Attendee {
    private ArrayList<String> talks;

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