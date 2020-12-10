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
        if (role.equalsIgnoreCase("Attendee")) {
            menu.optionsAttendee();
        }
        if (role.equalsIgnoreCase("Organizer")) {
            menu.optionsAttendee();
            menu.optionsOrganizer();
        }
        if (role.equalsIgnoreCase("Speaker")) {
            menu.optionsAttendee();
            menu.optionsSpeaker();
        }
    }

    /**
     * This is what the user should see if they choose to sign up for an event.
     */
    public void signUpEvent() {
        menu.eventPrompt("sign up");
        String inp = new Scanner(System.in).nextLine();
        UUID id;

        try{
            id = UUID.fromString(inp);
        }catch(IllegalArgumentException e){
            System.out.println("Wrong format!");
            return;
        }

        userManager.signUpForEvent(username, id);
        building.addAttendee(username, id);
    }

    public void cancelEnrolEvent() {
        menu.eventPrompt("cancel");
        String inp = new Scanner(System.in).nextLine();
        UUID id;

        try{
            id = UUID.fromString(inp);
        }catch(IllegalArgumentException e){
            System.out.println("Wrong format!");
            return;
        }

        userManager.cancelEnrollment(username, id);
        building.removeAttendee(username, id);
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

    public boolean scheduleSpeaker() {
        this.menu.createSpeakerName();
        Scanner cin = new Scanner(System.in);

        String speakerName = cin.nextLine();
        if(!userManager.checkUsername(speakerName) || !userManager.getUserRole(username).equals("Speaker"))
            return false;

        this.menu.enterEvent();
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

    public void downloadScheduleTxt() throws IOException, ClassNotFoundException {
        menu.scheduleDownload();
        String option = new Scanner(System.in).nextLine();
        if (option.equals("1")) {
            new ScheduleSystem().constructScheduleTxt();
            new ScheduleSystem().downloadSchedule();
            menuSelection();
        } else if (option.equals("2")) {
            homepage();
            menuSelection();
        } else {
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
        while (true) {
            String response = uname.nextLine();
            switch (response) {
                case "1":
                    this.menu.printBuildingSchedule(this.building);
                    downloadScheduleTxt();
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
                case "15": // modify event capacity
                    if(!modifyCapacity()){
                        this.menu.invalidResponse();
                    }
                    break;
                case "a":
                case "A":
                    homepage();
                    break;
                default:
                    this.menu.invalidResponse();
                    break;
            }
            this.menu.promptAgain();
        }
    }
}
