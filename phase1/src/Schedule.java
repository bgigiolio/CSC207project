import java.util.*;
public class Schedule {
    private final HashMap<Object, Event> scheduleMap = new HashMap<>();
    private int endHour;
    private int startHour;

    public Schedule(int startHour, int endHour){
        this.endHour = endHour;
        this.startHour = startHour;
    }
    public boolean addEvent(Event e){
        String eventTime = e.getDatetime();
        String eventHour = eventTime.substring(13, 15);
        int hour = Integer.parseInt(eventHour);

        if ((scheduleMap.containsKey(eventTime) && e.getLocation().equals(scheduleMap.get(eventTime).location))
                || hour >= endHour || hour < startHour) {
            return false;
        }

        scheduleMap.put(eventTime, e);
        return true;
    }
    //TODO: Consider cutting out creating event
    public boolean addEvent(String title, String location, String datetime){
        Event e = new Event(title, location, datetime);
        return this.addEvent(e);
    }

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
}
