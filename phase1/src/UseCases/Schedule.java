package UseCases;

import Entities.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <h1>Room Schedule</h1>
 * The Schedule class holds a map of events keyed to their corresponding times.
 * This is also equivalent to a 'room' as each schedule represents a room
 *
 * @author Blake Gigiolio
 */
public class Schedule {
    private final HashMap<LocalDateTime, Event> scheduleMap = new HashMap<>();
    private int endHour;
    private int startHour;

    //This has become a use case

    /**
     * The constructor requires a start hour and end hour for how long the room is available
     * @param startHour The first hour (inclusive) that an event can be scheduled
     * @param endHour The hour (exclusive) when an event can not be created in this room
     */
    public Schedule(int startHour, int endHour){
        this.endHour = endHour; // When the room closes
        this.startHour = startHour; // When the room opens
    }

    /**
     * Add an event to this schedule of this room
     *
     * @param event This is the event to be added
     * @return true if the event was successfully added, false if the event couldn't be added
     */
    public boolean addEvent(Event event){
        LocalDateTime eventTime = event.getDatetime();


        if ((scheduleMap.containsKey(eventTime) && event.getLocation().equals(scheduleMap.get(eventTime).getLocation()))
                || eventTime.getHour() >= endHour || eventTime.getHour() < startHour) {
            return false;
        }

        scheduleMap.put(eventTime, event);
        return true;
    }

    /**
     * Get an event object from its title
     *
     * @param event A string that is the name of an event.
     * @return Returns an event if the specified event is within this schedule, returns null if it isnt
     */
    public Event getEvent(String event){
        Iterator<Event> iterator = new EventIterator();
        Event e = null;
        while(iterator.hasNext()){
            Event current = iterator.next();
            if (current.getTitle().equals(event)){
                e = current;
            }
        }
        return e;
    }

    /**
     * Removes an event from this schedule.
     *
     * @param event The event to be removed.
     * @return true if the event was removed, false if the event couldn't be removed
     */
    public boolean removeEvent(Event event){
        if (scheduleMap.containsKey(event.getDatetime())) {
            scheduleMap.remove(event.getDatetime());
            return true;
        }else{
            return false;
        }
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
        for(Map.Entry<LocalDateTime, Event> i : this.scheduleMap.entrySet()){
            String key = i.getKey().toString();
            String event = i.getValue().getTitle();
            String location = i.getValue().getLocation();
            String construct = event + " at " + location + " at " + key + "\n";
            toReturn.append(construct);
        }
        return toReturn.toString();
    }

    /**
     * Gives the iterator for this schedule.
     *
     * @return Returns the iterator for this schedule.
     */
    public Iterator<Event> iterator() {
        return new EventIterator();
    }

    /**
     * This is the Schedule class' implementation of Iterator
     */
    private class EventIterator implements Iterator<Event>{
        private int current = 0;
        private final List<LocalDateTime> keys = new ArrayList<>(scheduleMap.keySet());
        @Override
        public boolean hasNext() {
            return (current < scheduleMap.size());
        }
        @Override
        public Event next() {
            LocalDateTime date;
            Event res;
            try {

                date = keys.get(current);
                res = scheduleMap.get(date);
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
            current += 1;
            return res;
        }
    }
}
