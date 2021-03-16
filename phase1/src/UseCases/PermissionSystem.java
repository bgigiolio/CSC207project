package UseCases;

import Entities.*;

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
