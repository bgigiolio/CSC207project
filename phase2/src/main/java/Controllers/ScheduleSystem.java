package main.java.Controllers;

import main.java.Gateways.EventGateway;
import main.java.UseCases.BuildingManager;
import main.java.UseCases.Schedule;

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

//    public static void main(String[] arg) throws ClassNotFoundException {
//        EventGateway eventGateway = new EventGateway();
//        BuildingManager buildingManager = eventGateway.getEvents();
//        System.out.println(buildingManager);
//    }
    }