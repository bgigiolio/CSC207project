import java.util.*;
public class EventController {
    public EventManager manager;
    public EventController(String title, String location, Calendar datetime, Schedule sched){
        this.manager = new EventManager(title, location, datetime, sched);
    }
    public EventController(Event e){
        this.manager = new EventManager(e);
    }
    public boolean createEvent(){
        return this.manager.addToSched();
    }
    public boolean removeEvent(){
        return this.manager.removeEvent();
    }
}
