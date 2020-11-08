public class EventManager {
    private Schedule schedule;
    private final Event event;
    public EventManager(String title, String location, String datetime, Schedule sched){
        this.event= new Event(title, location, datetime);
    }
    public EventManager(Event e){
        this.event = e;
    }

    public boolean addToSched(){
        return this.schedule.addEvent(this.event);
    }

    public boolean removeEvent(){
        return schedule.removeEvent(this.event);
    }
}
