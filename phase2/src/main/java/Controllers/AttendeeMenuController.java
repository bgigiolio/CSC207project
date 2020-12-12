package main.java.Controllers;

import main.java.Gateways.BuildingGateway;
import main.java.Gateways.EventGateway;
import main.java.Presenters.UserMenu;
import main.java.UseCases.BuildingManager;
import main.java.UseCases.EventManager;
import main.java.UseCases.UserManager;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

/**
 * <h1>AttendeeMenuController</h1>
 * This controller takes in input from an attendee when they are presented with a menu of options
 *
 * @author Blake Gigiolio
 * @version phase2
 */
public class AttendeeMenuController {
    private final String username;
    private final String role;
    private final UserMenu menu;
    private final BuildingManager building;
    private final UserManager userManager;
    private final EventManager eventManager;
    private final AccessibilityOptionsController accessibility;

    /**
     * This constructor takes in the parameters needed to operate the menu.
     *
     * @param username    This is the username of the user who this menu is for.
     * @param building    This is the Building Manager for the building that the user is interested in.
     * @param userManager This is the user manager for the user that this menu is for.
     */
    public AttendeeMenuController(String username, String role, BuildingManager building, UserManager userManager, EventManager eventManager, AccessibilityOptionsController accessibility) {
        this.username = username;
        this.role = role;
        this.menu = new UserMenu();
        this.building = building;
        this.userManager = userManager;
        this.eventManager = eventManager;
        this.accessibility = accessibility;
    }

    /**
     * Displays options for each specific kind of user
     */
    public void homepage() {
        menu.optionsAttendee();
        if (role.equalsIgnoreCase("organizer"))
            menu.optionsOrganizer();
        else if (role.equalsIgnoreCase("speaker"))
            menu.optionsSpeaker();
        else if (role.equalsIgnoreCase("admin"))
            menu.optionsAdmin();
    }

    /**
     * This is what the user should see if they choose to sign up for an event.
     */
    public boolean signUpEvent() {
        menu.eventPrompt("sign up");
        String inp = new Scanner(System.in).nextLine();
        UUID id;

        try {
            id = UUID.fromString(inp);
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong format!");
            return false;
        }

        return userManager.signUpForEvent(username, id) & eventManager.addAttendee(id, username);
    }

    public boolean cancelEnrolEvent() {
        menu.eventPrompt("cancel");
        String inp = new Scanner(System.in).nextLine();
        UUID id;

        try {
            id = UUID.fromString(inp);
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong format!");
            return false;
        }

        return userManager.cancelEnrollment(username, id) & eventManager.removeAttendee(id, username);
    }

    public void sendMessage() {
        this.menu.sendMessageUser();
        String user = new Scanner(System.in).nextLine();
        if (!userManager.checkUsername(user)) return;
        this.menu.sendMessageContent();
        String content = new Scanner(System.in).nextLine();
        MessageController message = new MessageController(this.username, user, content);
        message.sendMessage();
    }

    public boolean addRoom() {
        int startH, startM;
        int endH, endM;
        String inpStr1, inpStr2;
        Scanner cin = new Scanner(System.in);

        this.menu.createRoomName();

        String name = cin.nextLine();

        this.menu.createRoomStart();
        inpStr1 = cin.nextLine();
        inpStr2 = cin.nextLine();

        try {
            startH = Integer.parseInt(inpStr1);
            startM = Integer.parseInt(inpStr2);
        } catch (NumberFormatException e) {
            return false;
        }

        if (startH > 23 || startH < 0 || startM < 0 || startM > 59)
            return false;

        this.menu.createRoomEnd();
        inpStr1 = cin.nextLine();
        inpStr2 = cin.nextLine();

        try {
            endH = Integer.parseInt(inpStr1);
            endM = Integer.parseInt(inpStr2);
        } catch (NumberFormatException e) {
            return false;
        }

        if (endH > 23 || endH < 0 || endM < 0 || endM > 59)
            return false;

        this.menu.createRoomCapacity();
        String roomCapacityString = new Scanner(System.in).nextLine();
        int roomCapacity;

        try {
            roomCapacity = Integer.parseInt(roomCapacityString);
        } catch (NumberFormatException e) {
            return false;
        }

        if (roomCapacity < 0)
            return false;

        return building.addRoom(name, LocalTime.of(startH, startM), LocalTime.of(endH, endM), roomCapacity);
    }

    public boolean scheduleSpeaker() {
        this.menu.createSpeakerName();
        Scanner cin = new Scanner(System.in);

        String speakerName = cin.nextLine();
        if (!userManager.checkUsername(speakerName) || !userManager.getUserRole(speakerName).equals("speaker"))
            return false;

        this.menu.enterEventID();
        String eventID = cin.nextLine();
        UUID id;

        try {
            id = UUID.fromString(eventID);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid id format");
            return false;
        }

        return eventManager.setSpeaker(id, speakerName) & userManager.addTalk(speakerName, id);
    }

    public boolean getListOfAttendees() {
        menu.enterEventID();
        String eventID = new Scanner(System.in).nextLine();
        UUID id;
        ArrayList<String> attendees;

        try {
            id = UUID.fromString(eventID);
        } catch (IllegalArgumentException e) {
            menu.invalidID();
            return false;
        }
        attendees = eventManager.getAttendees(id);

        StringBuilder printout = new StringBuilder("List of Attendees: ");

        for (String s : attendees)
            printout.append(s).append(", ");

        menu.printAttendees(printout.toString());
        return true;

    }

    public boolean addRequest() {
        this.menu.request();
        Scanner scan = new Scanner(System.in);
        String request = scan.nextLine();
        if (request.equals("food") || request.equals("transportation") || request.equals("vision")) {
            accessibility.sendRequest(this.username, request);
            accessibility.save();
            return true;
        }
        return false;
    }

    public boolean getRequests() {
        ArrayList<String> requests = accessibility.getAllRequest();
        StringBuilder printout = new StringBuilder("List of Requests:\n");

        for (String r : requests) {
            printout.append("*").append(r).append("\n");
        }

        this.menu.printAttendees(printout.toString());
        this.menu.requestAction();

        Scanner scan = new Scanner(System.in);
        String action = scan.nextLine();

        if (action.equalsIgnoreCase("Leave")) {
            return true;
        }

        this.menu.enterUsername();
        String user = scan.nextLine();
        this.menu.enterRequestNum();
        String tempNum = scan.nextLine();

        int num;

        try {
            num = Integer.parseInt(tempNum);
        } catch (NumberFormatException e) {
            return false;
        }

        if (action.equalsIgnoreCase("Address")) {
            accessibility.addressRequest(user, num);
        } else if (action.equalsIgnoreCase("Reject")) {
            accessibility.rejectRequest(user, num);
        }
        accessibility.save();
        return true;
    }

    public boolean modifyCapacity() {
        Scanner cin = new Scanner(System.in);
        int newCapacity;
        String eventID;
        UUID id;

        this.menu.enterEvent();
        eventID = cin.nextLine();

        try {
            id = UUID.fromString(eventID);
        } catch (IllegalArgumentException e) {
            return false;
        }

        this.menu.modifyEventCapacity();
        String tempNewCapacity = cin.nextLine();

        try {
            newCapacity = Integer.parseInt(tempNewCapacity);
        } catch (NumberFormatException e) {
            return false;
        }
        if (newCapacity < 0)
            return false;
        return eventManager.changeCapacity(id, newCapacity);
    }

    public boolean removeEvent() {
        this.menu.manageEvent();
        String eventID = new Scanner(System.in).nextLine();
        UUID id;

        try {
            id = UUID.fromString(eventID);
        } catch (IllegalArgumentException e) {
            return false;
        }
        boolean returnVal = eventManager.deleteEvent(id) & building.deleteEvent(id);
        new EventGateway().save(eventManager);
        new BuildingGateway().save(building);
        return returnVal;
    }

    public boolean messageAttendees() {
        OrganizerMessageController messenger = new OrganizerMessageController(this.username);
        String event = new Scanner(System.in).nextLine();
        return true;
    }

    public void manageFriendsList() {
        menu.friendsList();
        String choice = new Scanner(System.in).nextLine();
        String user2;

        if (choice.equalsIgnoreCase("A")) {
            menu.friendsListUsername();
            user2 = new Scanner(System.in).nextLine();

            if (!userManager.checkUsername(user2))
                menu.invalidResponse();
            else
                userManager.addFriend(this.username, user2);
        } else if (choice.equalsIgnoreCase("R")) {
            this.menu.friendsListUsername();
            user2 = new Scanner(System.in).nextLine();

            if (!userManager.checkUsername(user2))
                menu.invalidResponse();
            else
                userManager.removeFriend(this.username, user2);
        } else {
            this.menu.invalidResponse();
        }
    }

    public void createUser() {
        menu.createUserType();
        String userType = new Scanner(System.in).nextLine();
        menu.createUserName();
        String userName = new Scanner(System.in).nextLine();

        if (userType.equalsIgnoreCase("o")) {
            if (userManager.registerUser(userName, "password", "organizer"))
                menu.organizerMade(userName);
            else
                menu.invalidResponse();
        } else if (userType.equalsIgnoreCase("u")) {
            if (userManager.registerUser(userName, "password", "attendee"))
                menu.attendeeMade(userName);
            else
                menu.invalidResponse();
        } else if (userType.equalsIgnoreCase("a")) {
            if (userManager.registerUser(userName, "password", "admin"))
                menu.adminMade(userName);
            else
                this.menu.invalidResponse();
        } else if (userType.equalsIgnoreCase("s")) {
            if (userManager.registerUser(userName, "password", "speaker"))
                menu.speakerMade(userName);
            else
                this.menu.invalidResponse();
        }
    }

    public boolean createEvent() {
        Scanner cin = new Scanner(System.in);

        this.menu.createEventName();
        String eventName = cin.nextLine();

        this.menu.createEventRoom();
        String roomName = cin.nextLine();

        this.menu.createEventCapacity();
        String tempEventCapacity = cin.nextLine();

        this.menu.createEventYear();
        String yearString = cin.nextLine();

        this.menu.createEventMonth();
        String monthString = cin.nextLine();

        this.menu.createEventDay();
        String dayString = cin.nextLine();

        this.menu.createEventHour();
        String hourString = cin.nextLine();

        this.menu.createEventMinute();
        String minuteString = cin.nextLine();

        this.menu.createEventDuration();
        String durationStr = cin.nextLine();

        System.out.println("Enter <1> for no-speaker event, <2> talk, <3> panel discussion:");
        String type = cin.nextLine();

        int year;
        int month;
        int day;
        int hour;
        int minute;
        int duration;
        int eventCapacity;
        int choice;

        try {
            year = Integer.parseInt(yearString);
            month = Integer.parseInt(monthString);
            day = Integer.parseInt(dayString);
            hour = Integer.parseInt(hourString);
            minute = Integer.parseInt(minuteString);
            duration = Integer.parseInt(durationStr);
            eventCapacity = Integer.parseInt(tempEventCapacity);
            choice = Integer.parseInt(type);
        } catch (NumberFormatException e) {
            return false;
        }
        LocalDateTime d;
        try {
            d = LocalDateTime.of(year, month, day, hour, minute, 0);
        } catch (java.time.DateTimeException e) {
            return false;
        }

        System.out.println(d);

        if (choice == 1) {
            if (!eventManager.addEvent(eventName, roomName, d, duration, eventCapacity, "event", building))
                return false;
        } else if (choice == 2) {
            if (!eventManager.addEvent(eventName, roomName, d, duration, eventCapacity, "talk", building))
                return false;
        } else if (choice == 3) {
            if (!eventManager.addEvent(eventName, roomName, d, duration, eventCapacity, "panelDiscussion", building))
                return false;
        }

        new EventGateway().save(eventManager);
        new BuildingGateway().save(building);
        return true;
    }

    public boolean organizerMessageAll() {
        if (this.role.equals("organizer")) {
            menu.sendMessageContent();
            String content = new Scanner(System.in).nextLine();
            new OrganizerMessageController(this.username).toAllAttendee(content, userManager);
            return true;
        } else {
            this.menu.invalidResponse();
            return false;
        }
    }

    public void downloadScheduleTxt() {
        menu.scheduleDownload();
        String option = new Scanner(System.in).nextLine();
        if (option.equals("1")) {
            new ScheduleSystem().constructScheduleTxt();
            new ScheduleSystem().downloadSchedule();
        } else if (!option.equals("2")) {
            menu.invalidResponse();
            downloadScheduleTxt();
        }
    }

    /**
     * This is where the user will decide what they want to do. The possible options are:
     * [1] See Event Schedule | added to GUI
     * [2] Review Your Events Schedule | added to GUI
     * [3] Sign Up For Event | added to GUI
     * [4] Cancel Event | added to GUI
     * [5] Send Message
     * [6] Review Messages
     * [7] Manage Friends List
     * [8] Logout | added to GUI
     * [q] Quit
     * ---AVAILABLE FOR ORGANIZERS ONLY---
     * [9] Create User Account | added to GUI
     * [10] Add Room | added to GUI
     * [11] Schedule Speaker
     * [12] Remove Event
     * [13] Message Event Attendees
     * [14] Create Event | added to GUI
     *
     * @return true if user chose to exit program, false if user just logged out
     */
    public boolean menuSelection() {
        Scanner uname = new Scanner(System.in);
        int choice;
        String response;

        while (true) {
            response = uname.nextLine();

            //quit
            if (response.equalsIgnoreCase("q")) return true;

                //display homepage
            else if (response.equalsIgnoreCase("a")) {
                homepage();
                continue;
            }

            try {
                choice = Integer.parseInt(response);
            } catch (NumberFormatException e) {
                choice = -1;
            }

            if (choice == 8) {
                menu.logoutSuccess();
                return false;
            }

            if (!attendeeSwitch(choice)) {
                switch (role) {
                    case "speaker":
                        speakerSwitch(choice);
                        break;
                    case "organizer":
                        organizerSwitch(choice);
                        break;
                    case "admin":
                        adminSwitch(choice);
                        break;
                }
            }
            menu.promptagainonly();
        }
    }

    private boolean attendeeSwitch(int choice) {
        switch (choice) {
            case 1:
                menu.printBuildingSchedule(building, eventManager);
                downloadScheduleTxt();

            case 2:
                StringBuilder toPrint = new StringBuilder();
                toPrint.append("Events you are attending: \n");
                try {
                    for (String i : eventManager.eventsAttending(username)) {
                        toPrint.append(i).append("\n");
                    }
                } catch (NullPointerException e) {
                    toPrint.replace(0, toPrint.length(), "You are not attending any events");
                }
                String sPrint = toPrint.toString();
                menu.printSomething(sPrint);
                menu.operationComplete();
                break;

            case 3:
                if (signUpEvent())
                    menu.operationComplete();
                else
                    menu.invalidResponse();
                break;

            case 4:
                if (cancelEnrolEvent())
                    menu.operationComplete();
                else
                    menu.invalidResponse();
                break;

            case 5: //send message
                sendMessage(); //TODO: Add case where receiver user doesn't exist
                break;

            case 6: //review messages
                MessageController message = new MessageController();
                try {
                    menu.printMessages(message.getMessageForMe(username));
                } catch (NullPointerException e) {
                    menu.printSomething("You have no messages");
                }
                break;

            case 7: //Manage Friends List
                manageFriendsList();
                menu.operationComplete();
                break;
            case 20:
                if (!addRequest()) {
                    this.menu.invalidResponse();
                } else {
                    this.menu.operationComplete();
                }
                break;
            default:
                return false;
        }
        return true;
    }

    private void organizerSwitch(int choice) {
        switch (choice) {
            case 9: //create user account
                createUser();
                menu.operationComplete();
                break;

            case 10: //add room
                if (!addRoom()) menu.invalidResponse();
                else menu.operationComplete();
                break;

            case 11: //schedule speaker
                if (!scheduleSpeaker()) this.menu.invalidResponse();
                else menu.operationComplete();
                break;

            case 12: //Remove Event
                if (!removeEvent())
                    this.menu.invalidResponse();
                else
                    menu.operationComplete();
                break;

            case 13: //Message All Attendees
                if (organizerMessageAll())
                    menu.operationComplete();
                else
                    menu.printSomething("Something went wrong, please try again!");
                break;

            case 14: //add event
                if (createEvent())
                    menu.operationComplete();
                else
                    menu.invalidResponse();
                break;

            case 15: // modify event capacity
                if (modifyCapacity())
                    menu.operationComplete();
                else
                    menu.invalidResponse();
                break;

            case 16: // get List of Attendees for Event
                if (getListOfAttendees())
                    menu.operationComplete();
                else
                    menu.invalidResponse();
                break;
            case 17:
                if (getRequests())
                    menu.operationComplete();
                else
                    menu.invalidResponse();
                break;
            default:
                this.menu.invalidResponse();
                break;
        }
    }

    private void adminSwitch(int choice) {
        switch (choice) {
            case 9:     //delete messages
                MessageController mc = new MessageController();
                String un, pw, content;
                Scanner cin = new Scanner(System.in);
                menu.viewAllMessages(mc);
                menu.deleteMessagePrompt();
                un = cin.nextLine();
                pw = cin.nextLine();
                content = cin.nextLine();
                mc = new MessageController(un, pw, content);
                if (mc.deleteMessage())
                    menu.operationComplete();
                else
                    menu.invalidResponse();
                break;
            case 10:    //delete event with no attendees
                menu.displayEventsWithNoAttendees(eventManager);
                String id = new Scanner(System.in).nextLine();
                if (id.equals("q"))
                    return;

                UUID uuid;
                try {
                    uuid = UUID.fromString(id);
                } catch (IllegalArgumentException e) {
                    menu.invalidResponse();
                    return;
                }
                if (!eventManager.deleteEvent(uuid)) {
                    building.deleteEvent(uuid);
                    menu.operationComplete();
                    return;
                }
                menu.invalidResponse();
                break;
            default:
                this.menu.invalidResponse();
                break;
        }
    }

    //TODO: Add speaker send message functionality
    private void speakerSwitch(int choice) {
        switch (choice) {
            case 9:     //view list of my events
                menu.viewSpeakerEvents(eventManager.getEventsOfSpeakerUsernameToString(username));
                menu.operationComplete();
                break;
            case 10:    //send message
                menu.operationComplete();
                break;
            default:
                this.menu.invalidResponse();
                break;
        }
    }
}
