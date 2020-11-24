package Controllers;

import Presenters.UserMenu;
import UseCases.BuildingManager;
import UseCases.EventManager;
import UseCases.ListUseCase;
import UseCases.LoginUserManager;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

//These UI classes are just thrown together to make running the program a bit easier.
// PLEASE dont be afraid to delete these and change them a bunch!!!

/**
 * <h1>Attendee Menu Controller</h1>
 * This controller takes in input from an attendee when they are presented with a menu of options
 * @author Blake Gigiolio
 * @version phase1
 */
public class AttendeeMenuController {
    private final String username;
    private String role;
    private final UserMenu menu;
    private final BuildingManager building;
    private EventStatusChanger eventStatusChanger = new EventStatusChanger();
    private final LoginUserManager manager;

    /**
     * This constructor takes in the parameters needed to operate the menu.
     * @param username This is the username of the user who this menu is for.
     * @param building This is the Building Manager for the building that the user is interested in.
     * @param userManager This is the user manager for the user that this menu is for.
     */
    public AttendeeMenuController(String username, String role, BuildingManager building, LoginUserManager userManager) {
        this.username = username;
        this.role = role;
        this.menu = new UserMenu(this.username);
        this.building = building;
        this.manager = userManager;

    }

    public void homepage() throws IOException, ClassNotFoundException {
        if (role.equals("Attendee")) {
            menu.optionsAttendee();
        }
        if (role.equals("Organizer")) {
            menu.optionsAttendee();
            menu.optionsOrganizer();
        }
        if (role.equals("Speaker")){
            menu.optionsAttendee();
            menu.optionsSpeaker();
        }
    }

    /**
     * This is what the user should see if they choose to sign up for an event.
     */
    public void signUpEvent() throws IOException, ClassCastException, ClassNotFoundException {
        menu.eventPrompt("sign up");
        String eventTitle = new Scanner(System.in).nextLine();
        if (eventStatusChanger.signUpChanger(this.username, eventTitle)) {
            menu.signUpEventStatus(eventTitle, "1");
        } else {
            menu.signUpEventStatus(eventTitle, "0");
            String response = new Scanner(System.in).nextLine();
            if (response.equals("1")) {
                homepage();
            } else if (response.equals("2")) {
                signUpEvent();
            }
        }
    }

    public void cancelEnrolEvent() throws IOException, ClassNotFoundException {
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
    public void sendMessage() throws IOException {
        this.menu.sendMessageUser();
        String user = new Scanner(System.in).nextLine();
        this.menu.sendMessageContent();
        String content = new Scanner(System.in).nextLine();
        MessageController message = new MessageController(this.username, user, content);
        message.sendMessage();
    }
    public boolean addRoom(){
        this.menu.createRoomName();
        String name = new Scanner(System.in).nextLine();
        this.menu.createRoomStart();
        String startString = new Scanner(System.in).nextLine();
        int start = 0;
        try {
            start = Integer.parseInt(startString);
        }catch(NumberFormatException e){
            return false;
        }
        this.menu.createRoomEnd(start);
        String endString = new Scanner(System.in).nextLine();
        int end = 0;
        try {
            end = Integer.parseInt(endString);
        }catch(NumberFormatException e){
            return false;
        }
        return building.addRoom1(name, start, end);
    }
    public void addSpeaker() throws IOException {
        this.menu.createSpeakerName();
        Scanner sname = new Scanner(System.in);
        NewUserController newUser = new NewUserController();
        if (newUser.logReg(sname.nextLine(), "password", "Speaker")) {
            this.menu.speakerMade();
        }else{
            this.menu.invalidResponse();
        }
    }
    public void scheduleSpeaker(){
        this.menu.createSpeakerName();
        String speakername = new Scanner(System.in).nextLine();
        this.menu.enterEvent();
        String eventname = new Scanner(System.in).nextLine();
        EventManager manager = new EventManager(building.getEvent(eventname), building);
        manager.addSpeaker(speakername);

    }
    public boolean removeEvent(){
        this.menu.manageEvent();
        String event = new Scanner(System.in).nextLine();
        if(building.getEvent(event) != null){
            EventManager manager = new EventManager(building.getEvent(event), this.building);
            manager.removeEvent();
            return true;
        }else{
            return false;
        }
    }
    public boolean messageAttendees(){
        OrganizerMessageController messager = new OrganizerMessageController(this.manager.getAttendee(this.username));
        String event = new Scanner(System.in).nextLine();
        return true;
    }
    public void manageFriendsList(){
        this.menu.friendsList();
        ListUseCase friendsList = new ListUseCase(manager.getAttendee(this.username));
        String choice = new Scanner(System.in).nextLine();
        if(choice.equals("A")){
            this.menu.friendsListUsername();
            String username = new Scanner(System.in).nextLine();
            friendsList.addFriend(username);
        }else if(choice.equals("R")){
            this.menu.friendsListUsername();
            String username = new Scanner(System.in).nextLine();
            friendsList.removeFriend(username);
        }else{
            this.menu.invalidResponse();
        }

    }
    public boolean createEvent() {
        this.menu.createEventName();
        String eventName = new Scanner(System.in).nextLine();
        this.menu.createEventRoom();
        String roomName = new Scanner(System.in).nextLine();
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
        String speaker = "None";
        if (response.equals("Y") || response.equals("y")) {
            this.menu.createEventSpeakerName();
        }
            speaker = new Scanner(System.in).nextLine();
            int year;
            int month;
            int day;
            int hour;
            try {
                year = Integer.parseInt(yearString);
            } catch (NumberFormatException e) {
                return false;
            }
            try {
                month = Integer.parseInt(monthString);
            } catch (NumberFormatException e) {
                return false;
            }
            try {
                day = Integer.parseInt(dayString);
            } catch (NumberFormatException e) {
                return false;
            }
            try {
                hour = Integer.parseInt(hourString);
            } catch (NumberFormatException e) {
                return false;
            }
            LocalDateTime d = LocalDateTime.of(year, month, day, hour, 0, 0);
            EventController event = new EventController(eventName, speaker, roomName, d, building.getSchedule(roomName));
            return event.createEvent();
        }

        public boolean organizerMessageAll() throws IOException {
            if(this.role.equals("Organizer")) {
                menu.sendMessageContent();
                String content = new Scanner(System.in).nextLine();
                new OrganizerMessageController(this.manager.getAttendee(this.username)).
                        toAllAttendee(content, manager);
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
     * ---AVAILABLE FOR ORGANIZERS ONLY---
     * [9] Create Speaker Account
     * [10] Add Room
     * [11] Schedule Speaker
     * [12] Remove Event
     * [13] Message Event Attendees
     * [14] Create Event
     * @throws IOException Handles the Scanner.
     */
    public void menuSelection() throws IOException, ClassNotFoundException {
        homepage();
        Scanner uname = new Scanner(System.in);
        boolean loggedOut = false;
        while (!loggedOut) {
            String response = uname.nextLine();
            switch (response) {
                case "1":
                    this.menu.printBuildingSchedule(this.building);
                    break;
                case "2":
                    StringBuilder toPrint = new StringBuilder();
                    toPrint.append("Events you are attending: \n");
                    try{
                        for (String i : this.building.eventsAttending(this.username)){
                            toPrint.append(i).append("\n");
                        }
                    }catch(NullPointerException e){
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
                    }catch(NullPointerException e){
                        this.menu.printSomething("You have no messages");
                }
                    break;
                case "7": //Manage Friends List
                    manageFriendsList();
                    break;
                case "8": //logout
                    menu.logoutSuccess();
                    new InitialPrompt(new BuildingController("Building").getBuilding()).startProgram();
                    loggedOut = true;
                    break;
                case "9": //create speaker account
                    if(this.role.equals("Organizer")) {
                        addSpeaker();
                    }else{
                        this.menu.invalidRole();
                    }
                    break;
                case "10": //add room
                    if(this.role.equals("Organizer")) {
                        if(!addRoom()){
                            this.menu.invalidResponse();
                        }
                    }else{
                        this.menu.invalidRole();
                    }
                    break;
                case "11": //schedule speaker
                    if(this.role.equals("Organizer")) {
                        scheduleSpeaker();
                    }else{
                        this.menu.invalidRole();
                    }
                    break;
                case "12": //Remove Event
                    if(this.role.equals("Organizer")) {
                        if(!removeEvent()){
                            this.menu.invalidResponse();
                        }
                    }else{
                        this.menu.invalidRole();
                    }
                    //Not really sure whats happening here
                    break;
                case "13": //Message All Attendees
                    organizerMessageAll();
                    break;
                case "14": //add event
                    if(this.role.equals("Organizer")) {
                        if(!createEvent()){
                            this.menu.invalidResponse();
                        }
                    }else{
                        this.menu.invalidRole();
                    }
                    break;
                case"a":
                case"A":
                    homepage();
                    break;
                default:
                    this.menu.invalidResponse();
                    break;
            }
            ImportExportEventController saveBuilding = new ImportExportEventController();
            saveBuilding.exportEvents(this.building);
            this.menu.promptAgain();

        }
    }
}
