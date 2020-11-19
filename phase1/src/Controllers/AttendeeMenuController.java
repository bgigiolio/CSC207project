package Controllers;

import java.io.IOException;
import java.util.Scanner;
import Presenters.*;
import UseCases.BuildingManager;
import UseCases.LoginUserManager;

//These UI classes are just thrown together to make running the program a bit easier.
// PLEASE dont be afraid to delete these and change them a bunch!!!

/**
 * <h1>Attendee Menu Controller</h1>
 * This controller takes in input from an attendee when they are presented with a menu of options
 * @author Blake Gigiolio
 */
public class AttendeeMenuController {
    private final String username;
    private String role;
    private final UserMenu menu;
    private final BuildingManager building;
    private EventStatusChanger eventStatusChanger = new EventStatusChanger();

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

    }

    public void homepage() throws IOException, ClassNotFoundException {
        if (role.equals("Attendee")) {
            menu.optionsAttendee();
        } else if (role.equals("Organizer")) {
            menu.optionsOrganizer();
        }
        menuSelection();
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

    /**
     * This is where the user will decide what they want to do. The possible options are:
     * See Event Schedule: Prints the schedule for the building they are in.
     * Sign Up for Event: Enrolls the user in a specific event.
     * Cancel Event: Removes the user from a specific event.
     * Send Message: Sends a message to another user.
     * Review Message: Prints out the users 'inbox'
     * Manage Friends List: Allows the user to add/see/remove friends from their friends list.
     * @throws IOException Handles the Scanner.
     */
    public void menuSelection() throws IOException, ClassNotFoundException {
        Scanner uname = new Scanner(System.in);
        boolean answered = false;
        while (!answered) {
            String response = uname.nextLine();
            switch (response) {
                case "1":
                    this.menu.printBuildingSchedule(this.building);
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
                case "8": //logout
                    answered = true;
                    break;
                default:
                    this.menu.invalidResponse();
                    break;
            }
        }
    }
    }
