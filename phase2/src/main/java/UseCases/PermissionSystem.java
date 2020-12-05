package main.java.UseCases;

import main.java.Entities.*;

public class PermissionSystem {

    public boolean isOrganizer(Attendee user){
        return user.getRole().equals("organizer");
    }
    public boolean isAttendee(Attendee user){
        return user.getRole().equals("attendee");
    }
    public boolean isSpeaker(Attendee user){
        return user.getRole().equals("speaker");
    }

}
