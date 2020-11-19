package UseCases;

import Entities.*;

import java.time.LocalDateTime;

/**
 * <h1>Event Manager</h1>
 * The event manager is a class that holds an event and its schedule and allows us to add or remove this
 * event from the given schedule
 */
public class EventManager {
    private Schedule schedule;
    private final Event event;

    /**
     * This specific constructor takes in all of the parameters to make an event and an existing schedule
     * to create a new event.
     * @param title Title of the event we want to make.
     * @param location Location of the event.
     * @param datetime Time the event will occur.
     * @param sched The schedule this event should be a part of.
     */
    public EventManager(String title, String location, LocalDateTime datetime, Schedule sched){
        this.event= new Event(title, location, datetime);
    }

    /**
     * This specific constructor takes in an already existing event and a schedule
     * @param event The event we want to manage.
     * @param sched The schedule this event should be a part of.
     */
    public EventManager(Event event, Schedule sched){
        this.event = event;
        this.schedule = sched;
    }

    /**
     * Adds the event being managed to its given schedule.
     * @return Returns true if the event was added and false if it wasn't.
     */
    public boolean addToSched(){
        return this.schedule.addEvent(this.event);
    }

    /**
     * Removes the event being managed from the given schedule.
     * @return Returns true if the event was removed and false if it wasnt.
     */
    public boolean removeEvent(){
        return schedule.removeEvent(this.event);
    }
}
