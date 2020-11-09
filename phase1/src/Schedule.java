import java.time.LocalDateTime;
import java.util.*;
public class Schedule {
    private final HashMap<Object, Event> scheduleMap = new HashMap<>();
    private int endHour;
    private int startHour;

    //This has become a use case

    public Schedule(int startHour, int endHour){
        this.endHour = endHour; // When the room closes
        this.startHour = startHour; // When the room opens
    }
    public boolean addEvent(Event e){
        LocalDateTime eventTime = e.getDatetime();


        if ((scheduleMap.containsKey(eventTime) && e.getLocation().equals(scheduleMap.get(eventTime).location))
                || eventTime.getHour() >= endHour || eventTime.getHour() < startHour) {
            return false;
        }

        scheduleMap.put(eventTime, e);
        return true;
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
    public boolean isOpen(String datetime){
        return !scheduleMap.containsKey(datetime);
    }
}
