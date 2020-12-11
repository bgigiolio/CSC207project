package main.java.UseCases;

import main.java.Entities.Attendee;
import main.java.Entities.Event;
import main.java.Entities.PanelDiscussion;
import main.java.Entities.Talk;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;


/**
 * <h1>Building Manager</h1>
 * The Building Manager holds a map of Schedules keyed to their rooms.
 * There should likely be only one building manager per program.
 * @author Blake Gigiolio
 * @version Phase2
 */
public class BuildingManager implements Serializable {
    private final HashMap<String, Schedule2> building;
    private final String buildingName;

    /**
     * This constructor takes in the desired name for the building and creates an empty
     * map of schedules.
     * @param buildingName The desired name of the building.
     */
    public BuildingManager(String buildingName){
        this.buildingName = buildingName;
        this.building = new HashMap<>();
    }

    public HashMap<String, Schedule2> getBuilding() {
        return this.building;
    }

    /**
     * Adds a new (empty) room to this building by creating a new schedule from the parameters.
     * @param name The desired name of the room.
     * @param startHour The desired starting hour for this schedule. (See Schedule for reference)
     * @param endHour The desired ending hour for this schedule. (See Schedule for reference)
     * @return True if the room was successfully added and false if it wasn't.
     */
    public boolean addRoom(String name, LocalTime startHour, LocalTime endHour, int roomCapacity){
        Schedule2 addition = new Schedule2(startHour, endHour, roomCapacity);
        return addRoom(name, addition);
    }

    /**
     * Adds a new (already existing) room to this building by receiving an existing schedule and adding it.
     * @param name The desired name of the room.
     * @param sched The schedule of the room that we want to add.
     * @return True if the room was successfully added and false if it wasn't.
     */
    public boolean addRoom(String name, Schedule2 sched){
        if(!building.containsKey(name)){
            building.put(name, sched);
            return true;
        }
        return false;
    }

    /**
     * Removes a room from this building.
     * @param name The name of the room to be removed.
     */
    public void removeRoom(String name){
        building.remove(name);
    }

    /**
     * Add an event to this building
     * @param e the event
     * @return True if the event was added successfully, False otherwise
     */
    public boolean addEvent(Event e){
        String loc = e.getLocation();
        if((!building.containsKey(loc)) || building.get(loc).getRoomCapacity() < e.getCapacity()) {
            return false;
        }
        return building.get(loc).addEvent(e);
    }

    /**
     * Return events attended by user in this building
     * @param username user's username
     * @return list of event titles user is attending
     */
    public ArrayList<String> eventsAttending(String username){
        ArrayList<String> events = new ArrayList<>();
        Iterator<Schedule2> iterator = new ScheduleIterator();
        while(iterator.hasNext()){
            Schedule2 sched = iterator.next();
            events.addAll(sched.eventsAttending(username));
        }
        return events;
    }

    /**
     * Returns the schedule for the room of the given name.
     * @param name The name of the room for which we want the schedule of.
     * @return If the room exists within this building, we return its schedule. If not, returns null.
     */
    public Schedule2 getSchedule(String name){
        return building.get(name);
    }

    /**
     * Gets the schedule that contains an event with the given title.
     * @param event The title of the event needed.
     * @return Returns the schedule that contains the needed event, or null if no such schedule exists.
     */
    public Schedule2 getScheduleWithEvent(String event){
        Iterator<Schedule2> iterator = new ScheduleIterator();
        Schedule2 sched = null;

        while(iterator.hasNext()){
            sched = iterator.next();
            if(sched.getEvent(event) != null){
                return sched;
            }
        }
        return sched;
    }

    /**
     * Returns the string format of this building. Each room takes up two lines in the format of:
     * [Room name]
     * schedule.toString();
     *
     * @return Returns the string format of this building.
     */
    @Override
    public String toString(){
        StringBuilder toReturn = new StringBuilder();
        toReturn.append("List of Rooms in ").append(this.buildingName).append(": \n");

        for (String room : building.keySet()) {
            toReturn.append("\n");
            toReturn.append("[").append(room).append("] \n");
            String schedule = building.get(room).toString();
            toReturn.append(schedule);
        }

        return toReturn.toString();
    }

    /**
     * The implementation of Iterator for this building.
     */
    private class ScheduleIterator implements Iterator<Schedule2>{
        private int current = 0;
        private final ArrayList<String> keys = new ArrayList<>(building.keySet());

        @Override
        public boolean hasNext() {
            return (current < building.size());
        }

        @Override
        public Schedule2 next() {
            String room;
            Schedule2 res;
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

    public void updateScheduleOfRoom(String room, Schedule2 schedule) {
        this.building.put(room, schedule);
    }

    public UUID getUUID(String title, LocalDateTime dt, String type, String room){
        Event temp;
        switch (type) {
            case "event":
                temp = new Event(title, room, dt, 0, 0);
                break;
            case "talk":
                temp = new Talk(title, room, dt, 0, 0);
                break;
            case "panelDiscussion":
                temp = new PanelDiscussion(title, room, dt, 0, 0);
                break;
            default:
                return null;
        }

        Schedule2 sched = building.get(room);

        if(sched == null) return null;

        return sched.getEventUUID(temp);
    }

    public boolean addAttendee(String username, UUID id){
        for(String room:building.keySet()){
            Schedule2 sched = building.get(room);
            if(sched.addAttendee(id, username))
                return true;
        }
        return false;
    }

    public boolean removeAttendee(String username, UUID id) {
        for (String room : building.keySet()) {
            Schedule2 sched = building.get(room);
            if (sched.removeAttendee(id, username))
                return true;
        }
        return false;
    }

    public boolean modifyCapacity(int newCap, UUID id){
        for(String room:building.keySet()){
            Schedule2 sched = building.get(room);
            if(sched.changeCapacity(id, newCap))
                return true;
        }
        return false;
    }

    public boolean deleteEvent(UUID id){
        for(String room:building.keySet()){
            Schedule2 sched = building.get(room);
            if(sched.removeEvent(id))
                return true;
        }
        return false;
    }

    public boolean changeSpeaker(UUID id, String username){
        for(String room:building.keySet()){
            Schedule2 sched = building.get(room);
            if(sched.setSpeaker(id, username))
                return true;
        }
        return false;
    }

    public String getEventAttendees(UUID id){
        String printout = "List of Attendees: ";
        ArrayList<String> attendees = new ArrayList<>();
        for(String room:building.keySet()){
            Schedule2 sched = building.get(room);
            if(sched.getEvent(id) != null){
                attendees = sched.getEvent(id).getAttendees();
            }
        }
        for (String a: attendees){
            printout += a + ", ";
        }
        return printout;
    }
}
