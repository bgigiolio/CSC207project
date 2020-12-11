package main.java.Controllers;

import main.java.Entities.Event;
import main.java.Entities.PanelDiscussion;
import main.java.Entities.Talk;
import main.java.Gateways.EventGateway;
import main.java.Presenters.UserMenu;
import main.java.UseCases.BuildingManager;
import main.java.UseCases.UserManager;

import java.io.IOException;
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
        else if(role.equalsIgnoreCase("admin"))
            menu.optionsAdmin();
    }

    /**
     * This is what the user should see if they choose to sign up for an event.
     */
    public boolean signUpEvent() {
        menu.eventPrompt("sign up");
        String inp = new Scanner(System.in).nextLine();
        UUID id;

        try{
            id = UUID.fromString(inp);
        }catch(IllegalArgumentException e){
            System.out.println("Wrong format!");
            return false;
        }

        return userManager.signUpForEvent(username, id) & building.addAttendee(username, id);
    }

    public boolean cancelEnrolEvent() {
        menu.eventPrompt("cancel");
        String inp = new Scanner(System.in).nextLine();
        UUID id;

        try{
            id = UUID.fromString(inp);
        }catch(IllegalArgumentException e){
            System.out.println("Wrong format!");
            return false;
        }

        return userManager.cancelEnrollment(username, id) & building.removeAttendee(username, id);
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

        if(startH > 23 || startH < 0 || startM < 0 || startM > 59)
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

        if(endH > 23 || endH < 0 || endM < 0 || endM > 59)
            return false;

        this.menu.createRoomCapacity();
        String roomCapacityString = new Scanner(System.in).nextLine();
        int roomCapacity;

        try {
            roomCapacity = Integer.parseInt(roomCapacityString);
        } catch (NumberFormatException e) {
            return false;
        }

        if(roomCapacity < 0)
            return false;

        return building.addRoom(name, LocalTime.of(startH, startM), LocalTime.of(endH, endM), roomCapacity);
    }

    public boolean scheduleSpeaker() {
        this.menu.createSpeakerName();
        Scanner cin = new Scanner(System.in);

        String speakerName = cin.nextLine();
        if(!userManager.checkUsername(speakerName) || !userManager.getUserRole(speakerName).equals("speaker"))
            return false;

        this.menu.enterEventID();
        String eventID = cin.nextLine();
        UUID id;

        try{
            id = UUID.fromString(eventID);
        }catch (IllegalArgumentException e){
            System.out.println("Invalid id format");
            return false;
        }

        return building.changeSpeaker(id, username);
    }

    public boolean getListOfAttendees(){
        this.menu.enterEventID();
        Scanner cin = new Scanner(System.in);
        String eventID = cin.nextLine();
        UUID id;
        ArrayList<String> attendees;

        try{
            id = UUID.fromString(eventID);
        }catch (IllegalArgumentException e){
            this.menu.invalidID();
            return false;
        }

        this.menu.printAttendees(this.building.getEventAttendees(id));
        return true;

    }

    public boolean modifyCapacity() {
        Scanner cin = new Scanner(System.in);
        int newCapacity;
        String eventID;
        UUID id;

        this.menu.enterEvent();
        eventID = cin.nextLine();

        try{
            id = UUID.fromString(eventID);
        }catch (IllegalArgumentException e){
            return false;
        }

        this.menu.modifyEventCapacity();
        String tempNewCapacity = cin.nextLine();

        try {
            newCapacity = Integer.parseInt(tempNewCapacity);
        }catch(NumberFormatException e){
            return false;
        }
        if(newCapacity < 0)
            return false;
        return building.modifyCapacity(newCapacity, id);
    }

    public boolean removeEvent() {
        this.menu.manageEvent();
        String eventID = new Scanner(System.in).nextLine();
        UUID id;

        try{
            id = UUID.fromString(eventID);
        }catch (IllegalArgumentException e){
            return false;
        }

        return building.deleteEvent(id);
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

            if(!userManager.checkUsername(user2))
                menu.invalidResponse();
            else
                userManager.addFriend(this.username, user2);
        } else if (choice.equalsIgnoreCase("R")) {
            this.menu.friendsListUsername();
            user2 = new Scanner(System.in).nextLine();

            if(!userManager.checkUsername(user2))
                menu.invalidResponse();
            else
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
            if (userManager.registerUser(userName, "password", "organizer")) {
                this.menu.organizerMade(userName);
            } else {
                this.menu.invalidResponse();
            }
        }
        if (userType.equals("A") || userType.equals("a")) {
            if (userManager.registerUser(userName, "password", "attendee")) {
                this.menu.attendeeMade(userName);
            } else {
                this.menu.invalidResponse();
            }
        }
        if (userType.equals("S") || userType.equals("s")) {
            if (userManager.registerUser(userName, "password", "speaker")) {
                this.menu.speakerMade(userName);
            } else {
                this.menu.invalidResponse();
            }
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

        LocalDateTime d = LocalDateTime.of(year, month, day, hour, minute, 0);
        System.out.println(d);

        if(choice==1){
            if(!building.addEvent(new Event(eventName, roomName, d, duration, eventCapacity)))
                return false;
        }else if(choice == 2){
            if(!building.addEvent(new Talk(eventName, roomName, d, duration, eventCapacity)))
                return false;
        }else if(choice == 3){
            if(!building.addEvent(new PanelDiscussion(eventName, roomName, d, duration, eventCapacity)))
                return false;
        }

        new EventGateway().save(building);
        return true;
    }

    public boolean organizerMessageAll() throws IOException {
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

    public void downloadScheduleTxt() throws IOException, ClassNotFoundException {
        menu.scheduleDownload();
        String option = new Scanner(System.in).nextLine();
        if (option.equals("1")) {
            new ScheduleSystem().constructScheduleTxt();
            new ScheduleSystem().downloadSchedule();
            menuSelection();
        } else if (!option.equals("2")){
            menu.invalidResponse();
            downloadScheduleTxt();
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
    public boolean menuSelection() throws IOException, ClassNotFoundException {
        Scanner uname = new Scanner(System.in);
        int choice;
        String response;

        while (true) {
            response = uname.nextLine();

            //quit
            if(response.equalsIgnoreCase("q")) return true;

            //display homepage
            else if(response.equalsIgnoreCase("a")){
                homepage();
                continue;
            }

            try{
                choice = Integer.parseInt(response);
            }catch (NumberFormatException e){
                menu.invalidResponse();
                choice = -1;
            }

            if(choice == 8) {
                menu.logoutSuccess();
                return false;
            }

            if(!attendeeSwitch(choice)) {
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
        }
    }

    private boolean attendeeSwitch(int choice) throws IOException, ClassNotFoundException {
        switch(choice) {
            case 1:
                this.menu.printBuildingSchedule(this.building);
                downloadScheduleTxt();

            case 2:
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
                this.menu.promptAgain();
                break;

            case 3:
                if (signUpEvent()) {
                    this.menu.promptAgain();
                } else {
                    this.menu.promptagainonly();
                }
                break;

            case 4:
                if (cancelEnrolEvent()) {
                    this.menu.promptAgain();
                } else {
                    this.menu.promptagainonly();
                }
                break;

            case 5: //send message
                sendMessage(); //TODO: Add case where receiver user doesn't exist
                this.menu.promptAgain();
                break;

            case 6: //review messages
                MessageController message = new MessageController();
                try {
                    this.menu.printMessages(message.getMessageForMe(this.username));
                } catch (NullPointerException e) {
                    this.menu.printSomething("You have no messages");
                }
                this.menu.promptAgain();
                break;

            case 7: //Manage Friends List
                manageFriendsList();
                this.menu.promptAgain();
                break;

            default:
                return false;
        }
        return true;
    }

    private void organizerSwitch(int choice) throws IOException {
        switch (choice) {
            case 9: //create user account
                createUser();
                this.menu.promptagainonly();
                break;

            case 10: //add room
                if (!addRoom()) this.menu.invalidResponse();
                else this.menu.promptAgain();
                break;

            case 11: //schedule speaker
                if(!scheduleSpeaker()) this.menu.invalidResponse();
                else this.menu.promptAgain();
                break;

            case 12: //Remove Event
                if (!removeEvent()) {
                    this.menu.invalidResponse();
                    this.menu.promptagainonly();
                }
                else
                    this.menu.promptAgain();
                //Not really sure whats happening here
                break;

            case 13: //Message All Attendees
                if (organizerMessageAll())
                    this.menu.promptAgain();
                else
                    this.menu.printSomething("Something went wrong, please try again!");
                break;

            case 14: //add event
                if (!createEvent())
                    this.menu.invalidResponse();
                else
                    this.menu.promptAgain();
                break;

            case 15: // modify event capacity
                if(!modifyCapacity())
                    this.menu.invalidResponse();
                else
                    this.menu.promptAgain();
                break;

            case 16: // get List of Attendees for Event
                if(!getListOfAttendees())
                    this.menu.invalidResponse();
                else
                    this.menu.promptAgain();
                break;

            default:
                this.menu.invalidResponse();
                this.menu.promptagainonly();
                break;
        }
    }

    private void adminSwitch(int choice){
        switch (choice){
            case 9:     //delete messages
                break;
            case 10:    //delete event with no attendees
                break;
            default:
                this.menu.invalidResponse();
                this.menu.promptagainonly();
                break;
        }
    }

    private void speakerSwitch(int choice){
        switch (choice){
            case 9:     //view list of my events
                break;
            case 10:    //send message
                break;
            default:
                this.menu.invalidResponse();
                this.menu.promptagainonly();
                break;
        }
    }
}
