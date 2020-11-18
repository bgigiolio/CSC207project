package Entities;

import java.util.ArrayList;
import java.util.List;

public class Speaker extends Attendee {
    private ArrayList<String> talks;    //holds talk ids
    private String role = "speaker";

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