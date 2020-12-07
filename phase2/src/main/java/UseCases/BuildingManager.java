package main.java.UseCases;
import main.java.Entities.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <h1>Building Manager</h1>
 * The Building Manager holds a map of Schedules keyed to their rooms.
 * There should likely be only one building manager per program.
 * @author Blake Gigiolio
 * @version Phase1
 */
public class BuildingManager implements Serializable {
    private final HashMap<String, Schedule> building;
    private final String buildingName;

    /**
     * This constructor takes in the desired name for the building and creates an empty
     * map of schedules.
     * @param buildingName The desired name of the building.
     */
    public BuildingManager(String buildingName){
        HashMap<String, Schedule> building = new HashMap<>();
        this.buildingName = buildingName;
        this.building = building;
    }

    public HashMap<String, Schedule> getBuilding() {
        return this.building;
    }

    public void updateBuildingManager(String room, Schedule schedule) {
        this.building.put(room, schedule);
    }

    /**
     * Adds a new (empty) room to this building by creating a new schedule from the parameters.
     * @param name The desired name of the room.
     * @param startHour The desired starting hour for this schedule. (See Schedule for reference)
     * @param endHour The desired ending hour for this schedule. (See Schedule for reference)
     * @return Returns true if the room was successfully added and false if it wasn't.
     */
    public boolean addRoom1(String name, int startHour, int endHour, int roomCapacity){
        Schedule addition = new Schedule(startHour, endHour, roomCapacity);
        return addRoom2(name, addition);
    }

    /**
     * Adds a new (already existing) room to this building by receiving an existing schedule and adding it.
     * @param name The desired name of the room.
     * @param sched The schedule of the room that we want to add.
     * @return Returns true if the room was successfully added and false if it wasn't.
     */
    public boolean addRoom2(String name, Schedule sched){
        if(!building.containsKey(name)){
            building.put(name, sched);
            return true;
        }
        return false;
    }

    /**
     * Removes a room from this building.
     * @param name The name of the room to be removed.
     * @return Returns true if the room was successfully removed and false if it wasnt.
     */
    public boolean removeRoom(String name){
        if(building.containsKey(name)){
            building.remove(name);
            return true;
        }
        return false;
    }

    /**
     * Returns the schedule for the room of the given name.
     * @param name The name of the room for which we want the schedule of.
     * @return If the room exists within this building, we return its schedule. If not, returns null.
     */
    public Schedule getSchedule(String name){
        return building.get(name);
    }

    /**
     * Returns the event object of an event with the given name.
     * @param event The name of the event that we want.
     * @return Returns the Event object with the corresponding name, or null if it does not exist in this building.
     */
    public Event getEvent(String event){
        Iterator<Schedule> iterator = new ScheduleIterator();
        Event e = null;
        while(iterator.hasNext()){
            Schedule sched = iterator.next();
            if(sched.getEvent(event) != null){
                e = sched.getEvent(event);
            }
        }
        return e;
    }

    // Get event in room
    public Event getEvent(String event, String roomname){
        Iterator<Schedule> iterator = new ScheduleIterator();
        Event e = null;
        while(iterator.hasNext()){
            Schedule sched = iterator.next();
            if(sched.getEvent(event) != null && sched.getEvent(event).getLocation().equals(roomname)){
                e = sched.getEvent(event);
            }
        }
        return e;
    }

    // Get event in room at datetime
    public Event getEvent(String event, String roomname, LocalDateTime dt){
        Iterator<Schedule> iterator = new ScheduleIterator();
        Event e = null;
        while(iterator.hasNext()){
            Schedule sched = iterator.next();
            if(sched.getEvent(event) != null &&
                    sched.getEvent(event).getLocation().equals(roomname) &&
                    sched.getEvent(event).getDatetime().equals(dt)){
                e = sched.getEvent(event);
            }
        }
        return e;
    }

    /**
     * Gives the iterator for schedules within this building.
     * @return The given iterator.
     */
    public Iterator<Schedule> iterator() {
        return new ScheduleIterator();
    }

    /**
     * Gets the schedule that contains an event with the given title.
     * @param event The title of the event needed.
     * @return Returns the schedule that contains the needed event, or null if no such schedule exists.
     */
    public Schedule getScheduleWithEvent(String event){
        Schedule s = null;
        Iterator<Schedule> iterator = new ScheduleIterator();
        while(iterator.hasNext()){
            Schedule sched = iterator.next();
            if(sched.getEvent(event) != null){
                s = sched;
            }
        }
        return s;
    }

    /**
     * Return events attended by user in this building
     * @param username user's username
     * @return list of event titles user is attending
     */
    public ArrayList<String> eventsAttending(String username){
        ArrayList<String> events = new ArrayList<>();
        Iterator<Schedule> iterator = new ScheduleIterator();
        while(iterator.hasNext()){
            Schedule sched = iterator.next();
            events.addAll(sched.eventsAttending(username));
        }
        return events;
    }

    /**
     * Returns the string format of this building. Each room takes up two lines in the format of:
     * [Room name]
     * schedule.toString();
     *
     * @return Returns the string format of this building.
     */
    public String toString(){
        StringBuilder toReturn = new StringBuilder();
        toReturn.append("List of Rooms in ").append(this.buildingName).append(": \n");
//        try {
            for (String room : building.keySet()) {
                toReturn.append("[").append(room).append("] \n");
                String schedule = building.get(room).toString();
                toReturn.append(schedule);
                //           }
//            for (Map.Entry<String, Schedule> i : this.building.entrySet()) {
//                if (i != null && i.getValue() != null && i.getKey() != null) {
//                    String room = i.getKey();
//                    String schedule = i.getValue().toString();
//                    toReturn.append("[").append(room).append("] \n");
//                    toReturn.append(schedule);
//                }
            }
//        } catch (NullPointerException e){
//            return "There are no Scheduled events";
//        }
        return toReturn.toString();
    }

    /**
     * The implementation of Iterator for this building.
     */
    private class ScheduleIterator implements Iterator<Schedule>{
        private int current = 0;
        private final List<String> keys = new ArrayList<>(building.keySet());
        @Override
        public boolean hasNext() {
            return (current < building.size());
        }
        @Override
        public Schedule next() {
            String room;
            Schedule res;
            try {

                room = keys.get(current);
                res = building.get(room);
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
            current += 1;
            return res;
        }
    }

    public void updateScheduleOfRoom(String room, Schedule schedule) {
        this.building.put(room, schedule);
    }

}
