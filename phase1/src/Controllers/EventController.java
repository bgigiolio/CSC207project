package Controllers;

import UseCases.*;
import java.time.LocalDateTime;

public class EventController {
    public EventManager manager;
    public EventController(String title, String location, LocalDateTime datetime, Schedule sched){
        this.manager = new EventManager(title, location, datetime, sched);
    }
//    public EventController(String event){
//        this.manager = new EventManager();
//    }
    public boolean createEvent(){
        return this.manager.addToSched();
    }
    public boolean removeEvent(){
        return this.manager.removeEvent();
    }
}
