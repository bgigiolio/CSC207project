package UseCases;

import Entities.*;

import java.time.LocalDateTime;
import java.util.*;
public class Schedule {
    private final HashMap<LocalDateTime, Event> scheduleMap = new HashMap<>();
    private int endHour;
    private int startHour;

    //This has become a use case

    public Schedule(int startHour, int endHour){
        this.endHour = endHour; // When the room closes
        this.startHour = startHour; // When the room opens
    }
    public boolean addEvent(Event e){
        LocalDateTime eventTime = e.getDatetime();


        if ((scheduleMap.containsKey(eventTime) && e.getLocation().equals(scheduleMap.get(eventTime).getLocation()))
                || eventTime.getHour() >= endHour || eventTime.getHour() < startHour) {
            return false;
        }

        scheduleMap.put(eventTime, e);
        return true;
    }
//    public Event getEvent(String event){
//        if(scheduleMap.containsValue())
//    }

    public boolean removeEvent(Event e){
        if (scheduleMap.containsKey(e.getDatetime())) {
            scheduleMap.remove(e.getDatetime());
            return true;
        }else{
            return false;
        }
    }

    public void editHours(int startHour, int endHour){
        this.startHour = startHour;
        this.endHour = endHour;
    }
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

    public Iterator<Event> iterator() {
        return new EventIterator();
    }

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
