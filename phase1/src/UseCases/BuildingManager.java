package UseCases;
import Entities.*;

import java.time.LocalDateTime;
import java.util.*;


public class BuildingManager {
    private HashMap<String, Schedule> building;
    private final String buildingName;
    public BuildingManager(String buildingName){
        HashMap<String, Schedule> building = new HashMap<>();
        this.buildingName = buildingName;
    }
    public boolean addRoom(String name, int startHour, int endHour){
        Schedule addition = new Schedule(startHour, endHour);
        return addRoom(name, addition);
    }
    public boolean addRoom(String name, Schedule sched){
        if(building.containsKey(name)){
            building.put(name, sched);
            return true;
        }
        return false;
    }
    public boolean removeRoom(String name){
        if(building.containsKey(name)){
            building.remove(name);
            return true;
        }
        return false;
    }
    public Schedule getSchedule(String name){
        return building.get(name);
    }

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
    public Iterator<Schedule> iterator() {
        return new ScheduleIterator();
    }

    public Schedule getScheduleWithEvent(String event){
        Schedule s = null;
        Iterator<Schedule> iterator = new ScheduleIterator();
        while(iterator.hasNext()){
            Schedule sched = iterator().next();
            if(sched.getEvent(event) != null){
                s = sched;
            }
        }
        return s;
    }


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

    public String toString(){
        StringBuilder toReturn = new StringBuilder();
        toReturn.append("List of Rooms in ").append(this.buildingName).append(": \n");
        for(Map.Entry<String, Schedule> i : this.building.entrySet()){
            String room = i.getKey();
            String schedule = i.getValue().toString();
            toReturn.append("[").append(room).append("] \n");
            toReturn.append(schedule);
        }
        return toReturn.toString();
    }
}
