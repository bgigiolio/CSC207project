package UseCases;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Entities.Event;


/**
 * <h1>Room Schedule</h1>
 * The Schedule class holds a list of events in chronologically ascending order based on starting time.
 * This is also equivalent to a 'room' as each schedule represents a room.
 *
 * @author Konstantinos Papaspyridis
 * @version phase2
 */
public class Schedule2 implements Serializable {

    private ArrayList<Event> schedule;

    /**
     * time when first event starts
     */
    private int startHour;

    /**
     * time when last event finishes
     */
    private int endHour;

    public Schedule2(int startHour, int endHour){
        this.schedule = new ArrayList<>();
        this.endHour = endHour; // When the room closes
        this.startHour = startHour; // When the room opens
    }

    /**
     * Get an event object from its title
     * @param event A string that is the name of an event.
     * @return Returns an event if the specified event is within this schedule, returns null if it isn't
     */
    public Event getEvent(String event){
        for (Event e : this.schedule) {
            if(e.getTitle().equals(event))
                return e;
        }
        return null;
    }

    /**
     * Add an event to this schedule of this room
     * @param e This is the event to be added
     * @return true if the event was successfully added, false if the event couldn't be added
     */
    public boolean addEvent(Event e){
        int index = getIndex(0, this.schedule.size()-1, e.getDatetime());
        Event ev2 = this.schedule.get(index);
        LocalDateTime endTime = e.getDatetime().plusMinutes(e.getDuration());

        if(endTime.isBefore(ev2.getDatetime()) || endTime.isEqual(ev2.getDatetime())) {
            this.schedule.add(e);
            return true;
        }
        return false;
    }

    /**
     * Removes an event from this schedule.
     *
     * @param event The event to be removed.
     * @return true if the event was removed, false if the event couldn't be removed
     */
    public boolean removeEvent(Event event){
        return this.schedule.remove(event);
    }

    /**
     * Get all events the user with <code>username</code> is attending
     * @param username user's username
     * @return list with the IDs of the events the user is attending
     */
    public ArrayList<String> eventsAttending(String username) {
        ArrayList<String> events = new ArrayList<>();

        for (Event e:this.schedule) {
            if(e.getAttendees().contains(username))
                events.add(e.getTitle());
        }

        return events;
    }

    /**
     * Changes the start hour and end hour of this room
     *
     * @param startHour The first hour (inclusive) that an event can be scheduled
     * @param endHour The hour (exclusive) when an event can not be created in this room
     */
    public void editHours(int startHour, int endHour){
        this.startHour = startHour;
        this.endHour = endHour;
    }

    /**
     * Returns the schedule in string format. Each event takes up a line in the format of:
     * [Title] at [Location] at [DateTime]
     *
     * @return returns the string format of the schedule
     */
    public String toString(){
        StringBuilder toReturn = new StringBuilder();

        for(Event e:schedule)
            toReturn.append(e.getTitle() + " at " +e.getLocation() + ", " + e.getDatetime().toString() + "\n");

        return toReturn.toString();
    }

    /**
     * Binary search to get the index of the event that starts after the event we want to add
     * @param l left index
     * @param r right index
     * @param k date/time of event we are interested in
     * @return index of event starting after our event
     */
    private int getIndex(int l, int r, LocalDateTime k){
        int mid;

        while(l <= r){
            mid = l + ((r - l) >>> 1);
            if(this.schedule.get(mid).getDatetime().isEqual(k))
                return mid+1;
            else if(this.schedule.get(mid).getDatetime().isBefore(k))
                return getIndex(mid+1, r, k);
            return getIndex(l, mid-1, k);
        }
        return l;
    }
}
