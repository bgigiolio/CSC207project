package main.java.Controllers;

import main.java.Entities.Event;
import main.java.Gateways.EventGateway;
import main.java.UseCases.BuildingManager;
import main.java.UseCases.Schedule;

import java.time.LocalDateTime;

public class ScheduleSystem {
    EventGateway eventGateway = new EventGateway();

    public void updateEventDB(String room, Schedule schedule) throws ClassNotFoundException {
        BuildingManager buildingManager = eventGateway.getEvents();
        buildingManager.updateScheduleOfRoom(room, schedule);
        eventGateway.setEvents(buildingManager);
    }


    public void constructScheduleTxt() throws ClassNotFoundException {
        BuildingManager buildingManager = eventGateway.getEvents();
    }

    public static void main(String[] arg) throws ClassNotFoundException {
        Event createdEvent = new Event("event1", "room1",
                LocalDateTime.of(2020, 12, 15, 1, 0, 0),
                1, 100);
        Schedule schedule = new EventGateway().getEvents().getSchedule("room1");
        schedule.addEvent(createdEvent);
        BuildingManager buildingManager = new EventGateway().getEvents();
        buildingManager.updateBuildingManager("room1", schedule);
        new EventGateway().setEvents(buildingManager);


        BuildingManager bm = new EventGateway().getEvents();
        for (String room : bm.getBuilding().keySet()) {
            Schedule s = bm.getBuilding().get(room);
            System.out.println(room);
            for (LocalDateTime time : s.getScheduleMap().keySet()) {
                Event event = s.getScheduleMap().get(time);
                System.out.println(time + event.getTitle() + event.getLocation());
            }
        }
    }
    }