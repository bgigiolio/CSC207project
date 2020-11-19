package Controllers;

import java.io.IOException;
import java.util.Scanner;
import Presenters.*;
import UseCases.BuildingManager;
import UseCases.LoginUserManager;

//These UI classes are just thrown together to make running the program a bit easier.
// PLEASE dont be afraid to delete these and change them a bunch!!!

/**
 * <h1>Initial Prompt</h1>
 * This controller facilitates logging in and creating new accounts.
 * This should be the first thing called in the program!
 * @author Blake Gigiolio
 */
public class InitialPrompt {
    private String password;
    private String username;
    private String role;
    private LoginMenu Menu;
    private StartingMenu presenter;
    private BuildingManager buildingManager;

    /**
     * This constructor sets up which building the program is going to run for.
     * @param buildingManager The building manager for the building in question.
     */
    public InitialPrompt(BuildingManager buildingManager ){
        this.buildingManager = buildingManager;
    }

    /**
     * This is the first method that should be run in the program, allowing the user to log in
     * by inputting strings for usernames and passwords.
     * This method follows this pattern:
     *  -Asks whether or not user is new or returning
     *  -Asks user whether they are an organizer or an attendee
     *  -Logs the user in
     *  -Initializes the post-login menu
     * @throws IOException Handles Scanners.
     */
    public void startProgram() throws IOException {
        boolean answered = false;
        boolean answered2 = false;
        Scanner uname = new Scanner(System.in);
        this.presenter = new StartingMenu();
        this.presenter.initialPrompt();
        while (!answered) {
            String response = uname.nextLine();
            if (response.equalsIgnoreCase("N") || response.equalsIgnoreCase("[N]")) {
                this.Menu = new NewUserController();
                answered = true;
            } else if (response.equalsIgnoreCase("R") || response.equalsIgnoreCase("[R]")) {
                this.Menu = new ReturningUserController();
                answered = true;
            } else {
            this.presenter.failedPrompt();
            }
        }
        this.presenter.rolePrompt();
        while(!answered2){
            String response2 = uname.nextLine();
            if(response2.equalsIgnoreCase("O") || response2.equalsIgnoreCase("[O]")){
                answered2 = true;
                this.role = "Organizer";
            }else if(response2.equalsIgnoreCase("A") || response2.equalsIgnoreCase("[A]")){
                answered2 = true;
                this.role = "Attendee";
            }
        }
        this.username = this.Menu.usernamePrompt();
        this.password = this.Menu.passwordPrompt();
        login();
        LoginUserManager manager = new LoginUserManager();
        if (this.role.equals("Organizer")) {
            OrganizerMenu organizerMenu = new OrganizerMenu(this.username, this.buildingManager, manager);
            organizerMenu.printMenu();
            organizerMenu.menuSelection();
        } else {
//            AttendeeMenuController userMenu = new AttendeeMenuController();
//            userMenu.optionsAttendee();
            //userMenu.menuSelection();
        }
    }

    /**
     * This is how a user will log in. Here we call the log in menu prompt.
     * @throws IOException Handles Scanner.
     */
    private void login() throws IOException {
        if (Menu.logReg(this.username, this.password, this.role)) {
            this.presenter.loggedInPrompt();

        }else{
            this.presenter.failedPrompt();
            startProgram();
        }
    }
}
