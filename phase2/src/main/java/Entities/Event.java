package main.java.Entities;

import java.io.Serializable;
import java.util.*;
import java.time.*;
import java.util.UUID;

/**
 * <h1>Event</h1>
 * Event is an entity class which stores information about an event at the conference
 * It represents a no-speaker event
 * @author Qi Zheng
 * @version Phase2
 */
public class Event implements Serializable {

    protected final UUID uuid;

    /**
     * Title of event
     */
    protected String title;

    private final String type;

    /**
     * where this event will take place (room number)
     */
    protected String location;

    /**
     * Time and date of event
     */
    protected LocalDateTime datetime;
    // Ex. LocalDateTime d = LocalDateTime.of(int YYYY, int MM, int DD, int hh, int mm, int ss)
    // The ss (seconds) parameter is optional.

    /**
     * List of usernames of people attending this event
     */
    protected ArrayList<String> usernames;

    /**
     * The duration of the event in minutes
     */
    protected int duration;

    /**
     * The maximum number of people that can attend this event
     */
    protected int eventCapacity;

    public Event(String title, String location,
                 LocalDateTime datetime, int duration, int eventCapacity){
        this.title = title;
        this.location = location;
        this.datetime = datetime;
        this.usernames = new ArrayList<>();
        this.duration = duration;
        this.eventCapacity = eventCapacity;
        uuid = UUID.randomUUID();
        this.type = "event";
    }

    /**
     * Instantiates an Event object by taking the inputs title, location and datetime.
     * @param title is the title of the event
     //* @param speaker is the username of the speaker who hosts the event.
     * @param location is the location where the event will be held.
     * @param datetime tells when the event is happening.
     * @param duration how long this event will be
     */
    protected Event(String title, String location,
                 LocalDateTime datetime, int duration, int eventCapacity, String type){
        this.title = title;
        this.location = location;
        this.datetime = datetime;
        this.usernames = new ArrayList<>();
        this.duration = duration;
        this.eventCapacity = eventCapacity;
        uuid = UUID.randomUUID();
        this.type = type;
    }

    /**
     * Returns the title of the event.
     * @return this.title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns the location of the event.
     * @return this.location
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Returns the starting date and time of the event.
     * @return this.datetime
     */
    public LocalDateTime getDatetime() {
        return this.datetime;
    }

    /**
     * Returns the list of usernames of attendees that attend the event.
     * @return this.usernames
     */
    public ArrayList<String> getAttendees() {
        return new ArrayList<>(this.usernames);
    }

    /**
     * Get this event's duration
     * @return this.duration
     */
    public int getDuration() {return this.duration;}

    /**
     * Change this event's duration
     * @param val the new duration value
     */
    public void setDuration(int val){this.duration = val;}

    /**
     * Changes the title of the event. Takes title as an input and replaces with the class attribute this.title.
     * @param title is the title of the event.
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * Changes the location of the event by replacing the existing location attribute with the input.
     * @param location is a String that tells where the event is held.
     */
    public void setLocation(String location){
        this.location = location;
    }

    /**
     * Changes the date and time of the event by replacing the existing datetime with the input.
     * @param datetime is the date and time information for when the event will happen.
     */
    public void setDatetime(LocalDateTime datetime){
        this.datetime = datetime;
    }

    /**
     * Adds one attendee to the existing list of attendees that is assigned to usernames.
     * @param username is an attendee's username that would like to attend the event.
     */
    public void addAttendees(String username) {
        this.usernames.add(username);
    }

    /**
     * Adds a list of attendees to the existing list of attendees that is assigned to usernames.
     * @param usernames is the list of usernames of attendees that would like to attend the event.
     */
    public void addAttendees(ArrayList<String> usernames) {
        this.usernames.addAll(usernames);
    }

    /**
     * Removes one attendee from the existing list of attendees that is assigned to usernames.
     * @param username is an attendee's username that would like to attend the event.
     */
    public void removeAttendees(String username) {
        this.usernames.remove(username);
    }

    /**
     * Removes a list of attendees from the existing list of attendees that is assigned to usernames.
     * @param usernames is the list of usernames of attendees that would like to attend the event.
     */
    public void removeAttendees(ArrayList<String> usernames) {
        this.usernames.removeAll(usernames);
    }

    public int getCapacity(){
        return this.eventCapacity;
    }

    public UUID getUUID() { return uuid; }

    public void setCapacity(int newCapacity){
        this.eventCapacity = newCapacity;
    }

    public String getType() { return this.type; }

    @Override
    public boolean equals(Object obj){
        if(obj==null) return false;
        if(this == obj) return true;
        if(this.getClass() != obj.getClass()) return false;
        Event e = (Event) obj;
        if(this.uuid.equals(e.uuid)) return true;
        return this.title.equals(e.title) && this.datetime == e.datetime && this.type.equals(e.type) &&
                this.location.equals(e.location);
    }

    @Override
    public String toString() {
        String eventString = "\nID: " + this.getUUID().toString() + " \n" +
                this.getTitle() + " at " +
                this.getLocation() + ", " + this.getDatetime().toString() + "\n";
        return eventString;
    }
}
