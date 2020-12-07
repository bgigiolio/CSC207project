package main.java.Controllers;

import main.java.Entities.Event;
import main.java.Gateways.EventGateway;
import main.java.Presenters.UserMenu;
import main.java.UseCases.BuildingManager;
import main.java.UseCases.EventManager;
import main.java.UseCases.Schedule;
import main.java.UseCases.UserManager;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * <h1>AttendeeMenuController</h1>
 * This controller takes in input from an attendee when they are presented with a menu of options
 *
 * @author Blake Gigiolio
 * @version phase1
 */
public class AttendeeMenuController {
    private final String username;
    private final String role;
    private final UserMenu menu;
    private final BuildingManager building;
    private final EventStatusChanger eventStatusChanger;
    private final UserManager userManager;

    /**
     * This constructor takes in the parameters needed to operate the menu.
     *
     * @param username    This is the username of the user who this menu is for.
     * @param building    This is the Building Manager for the building that the user is interested in.
     * @param userManager This is the user manager for the user that this menu is for.
     */
    public AttendeeMenuController(String username, String role, BuildingManager building, UserManager userManager) {
        this.username = username;
        this.role = role;
        this.menu = new UserMenu();
        this.building = building;
        this.userManager = userManager;
        this.eventStatusChanger = new EventStatusChanger();
    }

    /**
     * Displays options for each specific kind of user
     */
    public void homepage() {
        if (role.equals("Attendee")) {
            menu.optionsAttendee();
        }
        if (role.equals("Organizer")) {
            menu.optionsAttendee();
            menu.optionsOrganizer();
        }
        if (role.equals("Speaker")) {
            menu.optionsAttendee();
            menu.optionsSpeaker();
        }
    }

    /**
     * This is what the user should see if they choose to sign up for an event.
     */
    public void signUpEvent() {
        menu.eventPrompt("sign up");
        String eventTitle = new Scanner(System.in).nextLine();
        int signUpSuccessful = eventStatusChanger.signUpChanger(this.username, eventTitle, this.building);
        if (signUpSuccessful == 0) {
            menu.signUpEventStatus(eventTitle, "1");
        } else {
            if (signUpSuccessful == 1) {
                menu.signUpEventStatus(eventTitle, "0");
            } else {
                menu.signUpEventStatus(eventTitle, "2");
            }
            String response = new Scanner(System.in).nextLine();
            if (response.equals("1")) {
                homepage();
            } else if (response.equals("2")) {
                signUpEvent();
            }
        }
    }

    public void cancelEnrolEvent() {
        menu.eventPrompt("cancel");
        String eventTitle = new Scanner(System.in).nextLine();
        EventStatusChanger eventStatusChanger = new EventStatusChanger();
        if (eventStatusChanger.cancelChanger(this.username, eventTitle)) {
            menu.cancelEnrolStatus(eventTitle, "1");
        } else {
            menu.cancelEnrolStatus(eventTitle, "0");
            String response = new Scanner(System.in).nextLine();
            if (response.equals("1")) {
                homepage();
            } else if (response.equals("2")) {
                cancelEnrolEvent();
            }
        }
    }

    public void sendMessage() {
        this.menu.sendMessageUser();
        String user = new Scanner(System.in).nextLine();
        this.menu.sendMessageContent();
        String content = new Scanner(System.in).nextLine();
        MessageController message = new MessageController(this.username, user, content);
        message.sendMessage();
    }

    public boolean addRoom() {
        this.menu.createRoomName();
        String name = new Scanner(System.in).nextLine();
        this.menu.createRoomStart();
        String startString = new Scanner(System.in).nextLine();
        int start;

        try {
            start = Integer.parseInt(startString);
        } catch (NumberFormatException e) {
            return false;
        }

        this.menu.createRoomEnd(start);
        String endString = new Scanner(System.in).nextLine();
        int end;

        try {
            end = Integer.parseInt(endString);
        } catch (NumberFormatException e) {
            return false;
        }

        this.menu.createRoomCapacity();
        String roomCapacityString = new Scanner(System.in).nextLine();
        int roomCapacity;

        try {
            roomCapacity = Integer.parseInt(roomCapacityString);
        } catch (NumberFormatException e) {
            return false;
        }

        return building.addRoom1(name, start, end, roomCapacity);
    }

    // Merged with createUser() method

    /*
    public void addSpeaker() throws IOException {
        LoginSystem log = new LoginSystem();
        this.menu.createSpeakerName();
        Scanner sname = new Scanner(System.in);
        NewUserController newUser = new NewUserController();
        if (log.register(sname.nextLine(), "password", "Speaker")) {
            this.menu.speakerMade();
        }else{
            this.menu.invalidResponse();
        }
    }
     */

    public void scheduleSpeaker() {
        this.menu.createSpeakerName();
        String speakerName = new Scanner(System.in).nextLine();
        this.menu.enterEvent();
        String eventName = new Scanner(System.in).nextLine();
        EventManager manager = new EventManager(building.getEvent(eventName), building);
        //manager.addSpeaker(speakerName);

    }

    public void modifyCapacity() {
        this.menu.enterEvent();
        String eventName = new Scanner(System.in).nextLine();
        this.menu.modifyEventCapacity();
        int newCapacity = new Scanner(System.in).nextInt();
        EventManager manager = new EventManager(building.getEvent(eventName), building);
        manager.modifyCapacity(newCapacity);
    }

    public boolean removeEvent() {
        this.menu.manageEvent();
        String event = new Scanner(System.in).nextLine();
        if (building.getEvent(event) != null) {
            EventManager manager = new EventManager(building.getEvent(event), this.building);
            manager.removeEvent();
            return true;
        } else {
            return false;
        }
    }

    public boolean messageAttendees() {
        OrganizerMessageController messenger = new OrganizerMessageController(this.username);
        String event = new Scanner(System.in).nextLine();
        return true;
    }

    public void manageFriendsList() {
        this.menu.friendsList();
        String choice = new Scanner(System.in).nextLine();
        String user2;
        if (choice.equals("A")) {
            this.menu.friendsListUsername();
            user2 = new Scanner(System.in).nextLine();
            userManager.addFriend(this.username, user2);
        } else if (choice.equals("R")) {
            this.menu.friendsListUsername();
            user2 = new Scanner(System.in).nextLine();
            userManager.removeFriend(this.username, user2);
        } else {
            this.menu.invalidResponse();
        }

    }

    public void createUser(){
        this.menu.createUserType();
        String userType = new Scanner(System.in).nextLine();
        this.menu.createUserName();
        String userName = new Scanner(System.in).nextLine();

        if (userType.equals("O") || userType.equals("o")) {
            if (userManager.registerUser(userName, "password", "Organizer")) {
                this.menu.organizerMade(userName);
            } else {
                this.menu.invalidResponse();
            }
        }
        if (userType.equals("A") || userType.equals("a")) {
            if (userManager.registerUser(userName, "password", "Attendee")) {
                this.menu.attendeeMade(userName);
            } else {
                this.menu.invalidResponse();
            }
        }
        if (userType.equals("S") || userType.equals("s")) {
            if (userManager.registerUser(userName, "password", "Speaker")) {
                this.menu.speakerMade(userName);
            } else {
                this.menu.invalidResponse();
            }
        }
    }

    // TODO: this method is getting error saving event, i'll fix later!
    public boolean createEvent() throws ClassNotFoundException {
        this.menu.createEventName();
        String eventName = new Scanner(System.in).nextLine();
        System.out.println(eventName);
        this.menu.createEventRoom();
        String roomName = new Scanner(System.in).nextLine();
        System.out.println(roomName);
        this.menu.createEventCapacity();
        String tempEventCapacity = new Scanner(System.in).nextLine();
        int eventCapacity;

//        try {
            eventCapacity = Integer.parseInt(tempEventCapacity);
//        } catch (NumberFormatException e) {
//            return false;
//        }
//        if (eventCapacity > building.getSchedule(roomName).getRoomCapacity()) {
//            System.out.println("Event capacity cannot be greater than room capacity.");
//            return false;
//        }
        System.out.println(eventCapacity);

        this.menu.createEventYear();
        String yearString = new Scanner(System.in).nextLine();
        this.menu.createEventMonth();
        String monthString = new Scanner(System.in).nextLine();
        this.menu.createEventDay();
        String dayString = new Scanner(System.in).nextLine();
        this.menu.createEventHour();
        String hourString = new Scanner(System.in).nextLine();
        this.menu.createEventSpeaker();
        String response = new Scanner(System.in).nextLine();
        String speaker = "John";
        if (response.equals("Y") || response.equals("y")) {
            this.menu.createEventSpeakerName();
            speaker = new Scanner(System.in).nextLine();
        }
        int year;
        int month;
        int day;
        int hour;
//        try {
            year = Integer.parseInt(yearString);
//        } catch (NumberFormatException e) {
//            return false;
//        }
//        try {
            month = Integer.parseInt(monthString);
//        } catch (NumberFormatException e) {
//            return false;
//        }
//        try {
            day = Integer.parseInt(dayString);
//        } catch (NumberFormatException e) {
//            return false;
//        }
//        try {
            hour = Integer.parseInt(hourString);
//        } catch (NumberFormatException e) {
//            return false;
//        }
        LocalDateTime d = LocalDateTime.of(year, month, day, hour, 0, 0);
        System.out.println(d);

//        EventController event = new EventController(eventName, speaker, roomName, d,
//                new EventGateway().getEvents().getSchedule(roomName), eventCapacity);
//
//        EventManager eventManager = new EventManager(eventName, speaker, roomName, d,
//                new EventGateway().getEvents().getSchedule(roomName), eventCapacity);
//        eventManager.addToSched();

        Event createdEvent = new Event(eventName, roomName, d, 1, eventCapacity);
        Schedule schedule = new EventGateway().getEvents().getSchedule(roomName);
        schedule.addEvent(createdEvent);
        BuildingManager buildingManager = new EventGateway().getEvents();
        buildingManager.updateBuildingManager(roomName, schedule);
        new EventGateway().setEvents(buildingManager);

//        boolean returnValue = event.createEvent();
        // new ScheduleSystem().updateEventDB(roomName, schedule);
        return true;
    }

    public boolean organizerMessageAll() {
        if (this.role.equals("Organizer")) {
            menu.sendMessageContent();
            String content = new Scanner(System.in).nextLine();
            new OrganizerMessageController(this.username).toAllAttendee(content, userManager);
            return true;
        } else {
            this.menu.invalidResponse();
            return false;
        }
    }

    /**
     * This is where the user will decide what they want to do. The possible options are:
     * [1] See Event Schedule
     * [2] Review Your Events Schedule
     * [3] Sign Up For Event
     * [4] Cancel Event
     * [5] Send Message
     * [6] Review Messages
     * [7] Manage Friends List
     * [8] Logout
     * [q] Quit
     * ---AVAILABLE FOR ORGANIZERS ONLY---
     * [9] Create User Account
     * [10] Add Room
     * [11] Schedule Speaker
     * [12] Remove Event
     * [13] Message Event Attendees
     * [14] Create Event
     *
     * @return true if user chose to exit program, false if user just logged out
     */
    public boolean menuSelection() throws ClassNotFoundException {
        Scanner uname = new Scanner(System.in);

        homepage();

        while (true) {
            String response = uname.nextLine();
            switch (response) {
                case "1":
                    this.menu.printBuildingSchedule(this.building);
                    break;
                case "2":
                    StringBuilder toPrint = new StringBuilder();
                    toPrint.append("Events you are attending: \n");
                    try {
                        for (String i : this.building.eventsAttending(this.username)) {
                            toPrint.append(i).append("\n");
                        }
                    } catch (NullPointerException e) {
                        toPrint.replace(0, toPrint.length(), "You are not attending any events");
                    }
                    String sPrint = toPrint.toString();
                    this.menu.printSomething(sPrint);
                    break;
                case "3":
                    signUpEvent();
                    break;
                case "4":
                    cancelEnrolEvent();
                    break;
                case "5": //send message
                    sendMessage();
                    break;
                case "6": //review messages
                    MessageController message = new MessageController();
                    try {
                        this.menu.printMessages(message.getMessageForMe(this.username));
                    } catch (NullPointerException e) {
                        this.menu.printSomething("You have no messages");
                    }
                    break;
                case "7": //Manage Friends List
                    manageFriendsList();
                    break;
                case "8": //logout

                    menu.logoutSuccess();
                    return false;
                case "q": //quit program
                    return true;
                case "9": //create user account
                    if (this.role.equals("Organizer")) {
                        createUser();
                    } else {
                        this.menu.invalidRole();
                    }
                    break;
                case "10": //add room
                    if (this.role.equals("Organizer")) {
                        if (!addRoom()) {
                            this.menu.invalidResponse();
                        }
                    } else {
                        this.menu.invalidRole();
                    }
                    break;
                case "11": //schedule speaker
                    if (this.role.equals("Organizer")) {
                        scheduleSpeaker();
                    } else {
                        this.menu.invalidRole();
                    }
                    break;
                case "12": //Remove Event
                    if (this.role.equals("Organizer")) {
                        if (!removeEvent()) {
                            this.menu.invalidResponse();
                        }
                    } else {
                        this.menu.invalidRole();
                    }
                    //Not really sure whats happening here
                    break;
                case "13": //Message All Attendees
                    organizerMessageAll();
                    break;
                case "14": //add event
                    if (this.role.equals("Organizer")) {
                        if (!createEvent()) {
                            this.menu.invalidResponse();
                        }
                    } else {
                        this.menu.invalidRole();
                    }
                    break;
                case "15":
                    modifyCapacity();
                    break;
                case "a":
                case "A":
                    homepage();
                    break;
                default:
                    this.menu.invalidResponse();
                    break;
            }
//            ImportExportEventController saveBuilding = new ImportExportEventController();
//            saveBuilding.exportEvents(this.building);
            this.menu.promptAgain();
        }
    }
}
