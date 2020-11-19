package Presenters;

import Controllers.EventStatusChanger;
import UseCases.BuildingManager;

import java.io.IOException;
import java.util.Scanner;

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
     * @param username The username of the user that is using this menu.
     */
    public UserMenu(String username){
        this.username = username;
    }

    /**
     * Once the user logs in to the program this is the first thing they should see, listing their
     * possible actions.
     */
    public void optionsAttendee(){
        System.out.println("Welcome " + this.username + "!");
        System.out.println("---General Actions---");
        System.out.println("[See Entities.Event UseCases.Schedule]  [Sign Up For Entities.Event]  [Cancel Entities.Event]  " +
                "[Send Entities.Message]  [Review Messages]  [Manage Friends List]");
    }

    /**
     * If the user is an organizer, this should also be displayed along with optionsAttendee()
     */
    public void optionsOrganizer(){
        System.out.println("---Entities.Organizer Specific Actions---");
        System.out.println("[Create Entities.Speaker]  [Add Room]  [UseCases.Schedule Entities.Speaker]  [Manage Entities.Event]  ");
    }

    /**
     * Prompts the user to input a response
     */
    public void awaitResponse(){
        System.out.println("What would you like to do?");
    }

    /**
     * This is what the user should see if their response was invalid.
     */
    public void invalidResponse(){
        System.out.println("That is not a valid response. Please try again!");
    }

    /**
     * This prints the schedule for a given building.
     * @param building Which building we want to see the schedule of.
     */
    public void printBuildingSchedule(BuildingManager building){
        System.out.println(building.toString());
    }

    /**
     * This is what the user should see if they choose to sign up for an event.
     */
    public void signUpEvent(){
        System.out.println("Which event would you like to sign up for?");
    }

    //Not really sure why this is here vvvv Might remove.
    public void menuSelection() throws IOException {
        Scanner uname = new Scanner(System.in);
        boolean answered = false;
        while (!answered) {
            String response = uname.nextLine();
            switch (response) {
                case "See Entities.Event UseCases.Schedule":
                case "see event schedule": //TODO: implement function to fetch schedule
                    answered = true;
                    break;
                case "Sign Up For Entities.Event":
                case "sign up for event":
                    answered = true;
                    signUpEvent();
                    Scanner eventTitle = new Scanner(System.in);
                    EventStatusChanger eventStatusChanger = new EventStatusChanger();
                    if (eventStatusChanger.signUpForEvent(this.username, eventTitle.nextLine())) {
                        System.out.println("You have successfully signed up for the event " + eventTitle + ".");
                    } else {
                        invalidResponse();
                        // ask event title again here
                    };
                    break;
                case "Cancel Entities.Event":
                case "cancel event":
                    answered = true;
                    break;
                case "Send Entities.Message":
                case "send message":
                    answered = true;
                    break;
                case "Review Messages":
                case "review messages":
                    answered = true;
                    break;
                case "Manage Friends List":
                case "manage friends list":
                    answered = true;
                    break;
                default:
                    System.out.println("Invalid response! Please Try again");
                    break;
            }
        }
    }
}
