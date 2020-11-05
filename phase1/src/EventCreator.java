public class EventCreator {
    private Schedule schedule;
    public EventCreator(){
    }
    public boolean createEvent(String title, String location, String datetime, Schedule sched){
        Event event = new Event(title, location, datetime);
        return addToSched(event);
    }
    private boolean addToSched(Event e){
        return this.schedule.addEvent(e);
    }
}
