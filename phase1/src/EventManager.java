public class EventManager {
    private Schedule schedule;
    private final Event event;
    public EventManager(String title, String location, String datetime, Schedule sched){
        this.event= new Event(title, location, datetime);
        addToSched(this.event);
    }
    public EventManager(Event e){
        this.event = e;
    }
    public boolean createEvent(String title, String location, String datetime, Schedule sched){

        return addToSched(event);
    }
    private boolean addToSched(Event e){
        return this.schedule.addEvent(e);
    }

    private boolean removeEvent(){
        return schedule.removeEvent(this.event);
    }
}
