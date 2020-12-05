package main.java.UseCases;

import main.java.Entities.Attendee;
import main.java.Entities.Speaker;

import java.util.ArrayList;
import java.util.List;

public class ListUseCase {

    private Attendee attendee;
    private Speaker speaker;

    public ListUseCase(Attendee attendee){
        this.attendee = attendee;
    }
    public ListUseCase(Speaker speaker){
        this.speaker = speaker;
    }


    public Attendee getAttendee(){
        return this.attendee;
    }
    public void setAttendee(Attendee attendee){
        this.attendee = attendee;
    }
    public Attendee getSpeaker(){
        return this.speaker;
    }
    public void setSpeaker(Speaker speaker){
        this.speaker = speaker;
    }


    public void addFriend(String attendee){
        List<String> friendlist = this.attendee.getFriendList();
        if (!friendlist.contains(attendee)){
            friendlist.add(attendee);
        }
        this.attendee.setFriendList(friendlist);
    }
    public void removeFriend(String attendee){
        List<String> friendlist = this.attendee.getFriendList();
        friendlist.remove(attendee);
        this.attendee.setFriendList(friendlist);

    }

    public ArrayList<String> getSpeakerEvents(){
        return speaker.getTalks();
    }

}
