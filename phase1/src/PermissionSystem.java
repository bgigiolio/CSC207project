public class PermissionSystem {

    public boolean isOrganizer(Attendee user){
        return user.role.equals("organizer");
    }

    public boolean isAttendee(Attendee user){
        return user.role.equals("attendee");
    }

    public boolean isSpeaker(Attendee user){
        return user.role.equals("speaker");
    }
}
