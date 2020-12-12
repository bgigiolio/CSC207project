package main.java.Presenters;

import main.java.UseCases.BuildingManager;
import main.java.UseCases.EventManager;

import java.util.ArrayList;
import java.util.UUID;

/**
 * <h1>User Menu</h1>
 * This presenter contains the possible print statements the user should see in the user menu.
 * This should only be seen by the user after they have successfully logged in.
 * @author Blake Gigiolio
 */
public class UserMenu {

    /**
     * Once the user logs in to the program this is the first thing they should see, listing their
     * possible actions.
     */
    public void optionsAttendee() {
        System.out.println("---General Actions---");
        System.out.println("[1] See Event Schedule\n" +
                "[2] Review Your Events Schedule\n" +
                "[3] Sign Up For Event\n" +
                "[4] Cancel Event Attendance\n" +
                "[5] Send Message\n" +
                "[6] Review Messages\n" +
                "[7] Manage Friends List\n" +
                "[8] Logout\n"+
                "[20] Make Request\n" +
                "[q] Quit\n");
    }

    /**
     * If the user is an organizer, this should also be displayed along with optionsAttendee()
     */
    public void optionsOrganizer() {
        System.out.println("---Organizer Specific Actions---");
        System.out.println("[9] Create User\n" +
                "[10] Add Room\n" +
                "[11] Schedule Speaker\n" +
                "[12] Remove Event \n" +
                "[13] Message Event Attendees \n" +
                "[14] Create Event\n" +
                "[15] Modify Event Capacity. \n" +
                "[16] Get List of Attendees for Event.\n" +
                "[17] Get List of Requests");
    }

    /**
     * If the user is a speaker, this should be displayed along with optionsAttendee()
     */
    public void optionsSpeaker() {
        System.out.println("---Speaker Specific Actions---");
        System.out.println("[9] View List of My Events\n"+
                "[10] Send Message");
    }

    public void optionsAdmin() {
        System.out.println("---Admin Specific Actions---");
        System.out.println("[9] Delete message\n"+
                "[10] Delete event with no attendees");
    }

    /**
     * Prompts the user to input a response
     */
    public void awaitResponse() {
        System.out.println("What would you like to do?");
    }

    /**
     * This is what the user should see if their response was invalid.
     */
    public void invalidResponse() {
        System.out.println("That is not a valid response. Please try again!");
    }

    /**
     * This prints the schedule for a given building.
     *
     * @param building Which building we want to see the schedule of.
     */
    public void printBuildingSchedule(BuildingManager building, EventManager em) {
        System.out.println(building.getToString(em));
    }

    /**
     * This is what the user should see if they choose to sign up for or cancel enrollment in an event.
     */
    public void eventPrompt(String action) {
        if (action.equals("sign up")) {
            System.out.println("Please enter the name of event you want to sign up for:");
        } else { System.out.println("Please enter the name of event you want to cancel:"); } }

    /**
     * This is what the user should see after signing up for an event.
     */
    public void signUpEventStatus(String eventTitle, String status) {
        if (status.equals("1")) {
        System.out.println("You have successfully signed up for the event " + eventTitle + ".");
        } else if (status.equals("2")) {
            System.out.println("Event is at capacity. Please choose a different event.\n" +
                    "[1] Go back \n[2] Enter another event");
        }else{
            System.out.println("Event " + eventTitle + " does not exist.\n" +
                    "[1] Go back \n[2] Enter another event");}
    }

    /**
     * This is what the user should see after cancelling their enrollment in an event.
     */
    public void cancelEnrolStatus(String eventTitle, String status) {
        if (status.equals("1")) {
            System.out.println("You have successfully cancelled your enrollment in " + eventTitle + ".");
        } else {
            System.out.println("You did not sign up for the event " + eventTitle + ". \n" +
                    "[1] Go back \n[2] Enter another event");
        }
    }

    /**
     * This is what the user should see if they choose to remove an event.
     */
    public void manageEvent(){
        System.out.println("Which event would you like to remove?");
    }

    /**
     * This is what the user should see if they choose to send a message to a specific user.
     */
    public void sendMessageUser(){
        System.out.println("Which user would you like to send a message to?");
    }

    /**
     * This is what the user should when they are asked to input a to message content.
     */
    public void sendMessageContent(){
        System.out.println("What would you like to send them?");
    }

    /**
     * Print print on screen.
     */
    public void printSomething(String print){
        System.out.println(print);
    }

    /**
     * This is what the user should see for inputting a room name if they choose to create an event.
     */
    public void createRoomName(){
        System.out.println("What will this room be called?");
    }

    /**
     * This is what the user should see for inputting the event start time if they choose to create an event.
     */
    public void createRoomStart(){
        System.out.println("When should this room open?");
        System.out.println("Please enter the hour (0-23) followed by the minute (0-59)");
    }

    /**
     * This is what the user should see for inputting the event end time if they choose to create an event.
     */
    public void createRoomEnd(){
        System.out.println("When should this room close?");
        System.out.println("Please enter the hour (0-23) followed by the minute (0-59)");
    }

    public void createUserType() {
        System.out.println("Is the new user an Attendee, Admin, Organizer or Speaker?");
        System.out.println("Enter 'U' for Attendee, 'A' for Admin, 'O' for Organizer or 'S' for Speaker:");
    }

    public void createSpeakerName(){
        System.out.println("Enter Speaker name:");
    }

    public void createUserName(){
        System.out.println("Enter User name:");
    }

    public void speakerMade(String uname){
        System.out.println("Speaker account for " + uname + " successfully created!");
        System.out.println("Default password is 'password'");
    }

    public void organizerMade(String uname){
        System.out.println("Organizer account for " + uname + " successfully created!");
        System.out.println("Default password is 'password'");
    }

    public void attendeeMade(String uname){
        System.out.println("Attendee account for " + uname + " successfully created!");
        System.out.println("Default password is 'password'");
    }

    public void adminMade(String uname){
        System.out.println("Admin account for " + uname + " successfully created!");
        System.out.println("Default password is 'password'");
    }

    public void enterEvent(){ System.out.println("Enter existing Event name:"); }

    public void printMessages(ArrayList<String> messages){
        StringBuilder builder = new StringBuilder();
        builder.append("Your messages: \n");
        for (String i : messages){
            builder.append(i);
        }
        System.out.println(builder);
    }

    public void createEventName(){
        System.out.println("What should this event be named?");
    }

    public void createEventRoom(){
        System.out.println("Which room is this event taking place in?");
    }

    public void createEventYear(){
        System.out.println("What year is this event taking place in?");
    }

    public void createEventMonth(){
        System.out.println("What month is this event in?");
        System.out.println("please answer as a number between 1 and 12");
    }

    public void promptagainonly(){
        System.out.println("Type A to see menu again");
    }

    public void operationComplete(){System.out.println("Operation Completed!");}

    public void createEventDay(){
        System.out.println("What day is this event taking place in?");
        System.out.println("please answer as a number between 1 and 31");
    }

    public void createEventHour(){
        System.out.println("What hour is this event taking place");
        System.out.println("Please enter a number between 0 and 23");
    }

    public void createEventMinute(){
        System.out.println("What minute is this event taking place");
        System.out.println("Please enter a number between 0 and 59");
    }

    public void createEventDuration(){
        System.out.println("What is the duration of this event in minutes?");
    }

    public void friendsList(){
        System.out.println("Would you like to add or remove someone from the friends list? Type A or R");
    }

    public void friendsListUsername(){
        System.out.println("What is their username?");
    }

    public void logoutSuccess() {
        System.out.println("You Have Successfully Logged Out Of Your Account.");
    }

    public void createEventCapacity() {
        System.out.println("What is the maximum occupancy for this event?");
    }

    public void createRoomCapacity() {
        System.out.println("What is the maximum occupancy for this room?");
    }

    public void modifyEventCapacity(){
        System.out.println("What is the new Capacity?");
    }

    public void scheduleDownload() { System.out.println("[1] Download schedule\n[2] Go back");}

    public void enterEventID() { System.out.println("What is the Event's ID?");}

    public void invalidID() { System.out.println("Invalid ID format entered."); }

    public void printAttendees(String eventAttendees) {
        System.out.println(eventAttendees);
    }

    public void displayEventsWithNoAttendees(EventManager em){
        System.out.println("Here are the events with no attendees:");
        System.out.println("Event ID\t\tEvent name");
        for(UUID id : em.getEventIDNoAttendees())
            System.out.println(id.toString() + "   " + em.getEventTitle(id));
        System.out.println("\n\nEnter the ID of the event you want to delete or 'q' to exit:");
    }

    public void viewSpeakerEvents(String text){
        System.out.println("List of events I'm talking at:\n" + text);
    }

    public void request() { System.out.println("What is your request?");
    }

    public void requestAction() { System.out.println("Would you like to [Reject] or [Adress] or [Leave]?");
    }

    public void enterUsername() { System.out.println("Please enter the username of the desired request.");
    }

    public void enterRequestNum() { System.out.println("Please enter the associated number of the request.");
    }
}
