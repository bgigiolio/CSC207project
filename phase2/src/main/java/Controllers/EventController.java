package main.java.Controllers;

import main.java.UseCases.*;
import java.time.LocalDateTime;

/**
 * <h1>EventController</h1>
 * This Controller class is responsible for calling the necessary methods in EventManager to manage the information related to events.
 * This should only call the EventManager and AttendeeMenuController classes.
 * @author Zachary Werle
 * @version Phase 1
 */
public class EventController {
    public EventManager manager;

    /**
     * This controller is responsible for creating an instance of EventController.
     * @param title The name of the EventManager.
     * @param speaker The speaker of the EventManager.
     * @param location The location of the EventManager.
     * @param datetime The date and time of the EventManager.
     * @param sched The schedule for the EventManager.
     */
    public EventController(String title, String speaker,String location, LocalDateTime datetime, Schedule sched, int eventcapacity){
        this.manager = new EventManager(title, speaker,location, datetime, sched, eventcapacity);
    }
//    public EventController(String event){
//        this.manager = new EventManager();
//    }

    /**
     * This method is responsible for adding an event to the Schedule of the building.
     */
    public boolean createEvent(){
        return this.manager.addToSched();
    }
    /**
     * This method is responsible for removing an event from the Schedule of the building.
     */
    public boolean removeEvent(){
        return this.manager.removeEvent();
    }
}
