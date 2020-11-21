package UseCases;

import Entities.Attendee;

import java.lang.*;
import java.util.*;

public class FriendsListUseCase {

    private Attendee attendee;

    public FriendsListUseCase(Attendee attendee){
        this.attendee = attendee;
    }

    public Attendee getAttendee(){
        return this.attendee;
    }
    public void setAttendee(Attendee attendee){
        this.attendee = attendee;
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

}
