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
    private final UserMenu menu;
    private final BuildingManager building;

    /**
     * This constructor takes in the parameters needed to operate the menu.
     * @param username This is the username of the user who this menu is for.
     * @param building This is the Building Manager for the building that the user is interested in.
     * @param userManager This is the user manager for the user that this menu is for.
     */
    public AttendeeMenuController(String username, String role, BuildingManager building, LoginUserManager userManager) {
        this.username = username;
        this.menu = new UserMenu(this.username, role);
        this.building = building;

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
    public void menuSelection() throws IOException {
        Scanner uname = new Scanner(System.in);
        boolean answered = false;
        this.menu.optionsAttendee();
        while (!answered) {
            this.menu.awaitResponse();
            String response = uname.nextLine();
            switch (response) {
                case "See Event Schedule":
                case "see event schedule":
                    this.menu.printBuildingSchedule(this.building);
                    answered = true;
                    break;
                case "Sign Up For Event":
                case "sign up for event":
                    this.menu.signUpEvent();

//                    if(signUpEvent(uname.nextLine())){
//                        answered = true;
//                    }else{
//                        this.menu.invalidResponse();
//                    }
                    break;
                case "Cancel Event":
                case "cancel event":
                    answered = true;
                    break;
                case "Send Message":
                case "send message":
                    answered = true;
                    break;
                case "Review Messages":
                case "review messages":
                    //MessageController mc = new MessageController();
                    answered = true;
                    break;
                case "Manage Friends List":
                case "manage friends list":
                    answered = true;
                    break;
                default:
                    this.menu.invalidResponse();
                    break;
            }
        }
    }
//    public boolean signUpEvent(String event){
//        if(building.getEvent(event) != null){
//            EventStatusChanger changer = new EventStatusChanger(building.getEvent(event));
//        }
//    }
}
