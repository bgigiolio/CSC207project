public class EventStatusChanger {

    public boolean joinEvent(Event e, Attendee user){
        EventStatus eventStatus = new EventStatus();
        return eventStatus.addUser(e, user);
    }

    public boolean leaveEvent(Event e, Attendee user){
        String username = user.getUsername();
        if (e.getAttendees().contains(username) &&
                e.getAttendees().get(e.getAttendees().indexOf(username)).equals(username)) {
            e.removeAttendees(username);
            return true;
        } else {
            return false; }

    }
}
