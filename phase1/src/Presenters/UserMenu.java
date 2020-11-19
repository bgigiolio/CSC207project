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
    private String role;

    /**
     * This constructor sets up the menu with for a user with a given username.
     *
     * @param username The username of the user that is using this menu.
     */
    public UserMenu(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public void homepage() throws IOException {
        if (this.role.equals("Attendee")) {
            optionsAttendee();
        } else if (role.equals("Organizer")) {
            optionsOrganizer();
        }
        menuSelection();
    }

    /**
     * Once the user logs in to the program this is the first thing they should see, listing their
     * possible actions.
     */
    public void optionsAttendee() {
        System.out.println("---General Actions---");
        System.out.println("[1] See Entities.Event UseCases.Schedule\n" +
                "[2] Review Your Events Schedule\n" +
                "[3] Sign Up For Entities.Event\n" +
                "[4] Cancel Entities.Event\n" +
                "[5] Send Entities.Message\n" +
                "[6] Review Messages\n" +
                "[7] Manage Friends List");
    }

    /**
     * If the user is an organizer, this should also be displayed along with optionsAttendee()
     */
    public void optionsOrganizer() {
        System.out.println("---Entities.Organizer Specific Actions---");
        System.out.println("[Create Entities.Speaker]  [Add Room]  [UseCases.Schedule Entities.Speaker]  [Manage Entities.Event]  ");
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
    public void signUpEvent() throws IOException {
        System.out.println("Which event would you like to sign up for?");
        String eventTitle = new Scanner(System.in).nextLine();
        EventStatusChanger eventStatusChanger = new EventStatusChanger();
        if (eventStatusChanger.signUpForEvent(this.username, eventTitle)) {
            System.out.println("You have successfully signed up for the event " + eventTitle + ".");
        } else {
            System.out.println("Event " + eventTitle + " does not exist. \n" +
                    "[1] Go back \n [2] Enter another event");
            String response = new Scanner(System.in).nextLine();
            if (response.equals("1")) {
                homepage();
            } else if (response.equals("2")) {
                signUpEvent();
            }
        }
    }

    public void cancelEnrolEvent() throws IOException {
        System.out.println("Please enter the name of event you want to cancel. ");
        String eventTitle = new Scanner(System.in).nextLine();
        EventStatusChanger eventStatusChanger = new EventStatusChanger();
        if (eventStatusChanger.cancelEvent(this.username, eventTitle)) {
            System.out.println("You have successfully cancelled your enrollment in " + eventTitle + ".");
        } else {
            System.out.println("You did not sign up for the event " + eventTitle + ". \n" +
                    "[1] Go back \n [2] Enter another event");
            String response = new Scanner(System.in).nextLine();
            if (response.equals("1")) {
                homepage();
            } else if (response.equals("2")) {
                cancelEnrolEvent();
            }
        }
    }


    //Not really sure why this is here vvvv Might remove.
    public void menuSelection() throws IOException {
        Scanner uname = new Scanner(System.in);
        boolean answered = false;
        while (!answered) {
            String response = uname.nextLine();
            switch (response) {
                case "1": //TODO: implement function to fetch schedule
                    answered = true;
                    break;
                case "2":
                    answered = true; //TODO: implement function to see registered events' schedule
                case "3":
                    answered = true;
                    signUpEvent();
                    break;
                case "4":
                    answered = true;
                    cancelEnrolEvent();
                    break;
                case "5": //send message
                    answered = true;
                    break;
                case "6": //review messages
                    answered = true;
                    break;
                case "7": //Manage Friends List
                    answered = true;
                    break;
                default:
                    System.out.println("Invalid response! Please Try again");
                    break;
            }
        }
    }
}
