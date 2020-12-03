package UseCases;

import Entities.*;

import java.time.LocalDateTime;

/**
 * <h1>Event Manager</h1>
 * The event manager is a class that holds an event and its schedule and allows us to add or remove this
 * event from the given schedule
 * @author Blake Gigiolio
 * @version Phase1
 */
public class EventManager {
    private Schedule schedule;
    private final Event event;

    /**
     * This specific constructor takes in all of the parameters to make an event and an existing schedule
     * to create a new event.
     * @param title Title of the event we want to make.
     * @param speaker Speaker that will host the event.
     * @param location Location of the event.
     * @param datetime Time the event will occur.
     * @param sched The schedule this event should be a part of.
     */
    public EventManager(String title, String speaker,String location, LocalDateTime datetime, Schedule sched, int eventcapacity){
        this.event= new Event(title, speaker,location, datetime, eventcapacity);
        this.schedule = sched;
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
    public EventManager(Event event, BuildingManager building){
        this.event = event;
        this.schedule = building.getScheduleWithEvent(this.event.getTitle());
    }
    public void addSpeaker(String speaker){
        this.event.setSpeaker(speaker);
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
