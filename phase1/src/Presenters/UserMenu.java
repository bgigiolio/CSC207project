package Presenters;

import UseCases.BuildingManager;

import java.util.ArrayList;

/**
 * <h1>User Menu</h1>
 * This presenter contains the possible print statements the user should see in the user menu.
 * This should only be seen by the user after they have successfully logged in.
 * @author Blake Gigiolio
 */
public class UserMenu {
    private final String username;

    /**
     * This constructor sets up the menu with for a user with a given username.
     *
     * @param username The username of the user that is using this menu.
     */
    public UserMenu(String username) {
        this.username = username;
    }

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
                "[8] Logout\n");
    }

    /**
     * If the user is an organizer, this should also be displayed along with optionsAttendee()
     */
    public void optionsOrganizer() {
        System.out.println("---Organizer Specific Actions---");
        System.out.println("[9] Create Speaker\n" +
                "[10] Add Room\n" +
                "[11] Schedule Speaker\n" +
                "[12] Remove Event \n" +
                "[13] Message Event Attendees \n" +
                "[14] Create Event");
    }
    /**
     * If the user is a speaker, this should be displayed along with optionsAttendee()
     */
    public void optionsSpeaker() {
        System.out.println("---Speaker Specific Actions---");
        System.out.println("[15] View List of My Events\n"+
                "[16] Send Message");
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
    public void printBuildingSchedule(BuildingManager building) {
        System.out.println(building.toString());
    }

    /**
     * This is what the user should see if they choose to sign up for an event.
     */
    public void eventPrompt(String action) {
        if (action.equals("sign up")) {
            System.out.println("Please enter the name of event you want to sign up for:");
        } else { System.out.println("Please enter the name of event you want to cancel:"); } }

    public void signUpEventStatus(String eventTitle, String status) {
        if (status.equals("1")) {
        System.out.println("You have successfully signed up for the event " + eventTitle + ".");
        } else {
            System.out.println("Event " + eventTitle + " does not exist.\n" +
                    "[1] Go back \n[2] Enter another event");}
    }

    public void cancelEnrolStatus(String eventTitle, String status) {
        if (status.equals("1")) {
            System.out.println("You have successfully cancelled your enrollment in " + eventTitle + ".");
        } else {
            System.out.println("You did not sign up for the event " + eventTitle + ". \n" +
                    "[1] Go back \n[2] Enter another event");
        }
    }
    public void manageEvent(){
        System.out.println("Which event would you like to remove?");
    }
    public void sendMessageUser(){
        System.out.println("Which user would you like to send a message to?");
    }
    public void sendMessageContent(){
        System.out.println("What would you like to send them?");
    }
    public void printSomething(String print){
        System.out.println(print);
    }
    public void createRoomName(){
        System.out.println("What will this room be called?");
    }
    public void createRoomStart(){
        System.out.println("When should this room open?");
        System.out.println("Please type a number between 0 and 24");
    }
    public void createRoomEnd(int start){
        System.out.println("When should this room close?");
        System.out.println("Please type a number between " + start + " and 24");
    }
    public void createSpeakerName(){
        System.out.println("Enter Speaker name:");
    }
    public void speakerMade(){
        System.out.println("Speaker made!");
    }
    public void enterEvent(){
        System.out.println("Enter existing Event name:");
    }
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
        System.out.println("Which room is this event taking place?");
    }
    public void createEventYear(){
        System.out.println("What year is this event taking place");
    }
    public void createEventMonth(){
        System.out.println("What month is this event in?");
        System.out.println("please answer as a number between 1 and 12");
    }
    public void createEventDay(){
        System.out.println("Which day is this event taking place");
        System.out.println("please answer as a number between 1 and 31");
    }
    public void createEventHour(){
        System.out.println("What hour is this event taking place");
        System.out.println("please answer as a number between 1 and 24");
    }
    public void createEventSpeaker(){
        System.out.println("Would you like to add a speaker?");
    }
    public void createEventSpeakerName(){
        System.out.println("What is the speaker's name?");
    }
    public void friendsList(){
        System.out.println("Would you like to add or remove someone from the friends list? Type A or R");
    }
    public void friendsListUsername(){
        System.out.println("What is their username?");
    }
    public void invalidRole(){
        System.out.println("This action is only available for organizers");
    }

    public void logoutSuccess() {
        System.out.println("You Have Successfully Logged Out Of Your Account.");
    }
}
