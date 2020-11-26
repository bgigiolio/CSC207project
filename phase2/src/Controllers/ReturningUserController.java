package Controllers;//These UI classes are just thrown together to make running the program a bit easier.
// PLEASE dont be afraid to delete these and change them a bunch!!!
import java.io.IOException;
import java.util.Scanner;

import Presenters.StartingMenu;

/**
 * <h1>ReturningUserController</h1>
 * This Controller is responsible for getting the user input from the ReturningUserMenu.
 * This should only interact with the AttendeeMenuController and the InitialPrompt.
 * @author Zachary Werle
 * @version Phase 1
 */
public class ReturningUserController {

    private final StartingMenu menu;

    /**
     * This constructor creates a new instance of ReturningUserController.
     */
    public ReturningUserController(){
        this.menu = new StartingMenu();
    }

    /**
     * This method gets the user's username from user input.
     * @return The String object representing the user's username.
     */
    public String usernamePrompt(){
        Scanner uname = new Scanner(System.in);
        menu.uPrompt();
        return uname.nextLine();
    }

    /**
     * This method gets the user's password from user input.
     * @return The String object representing the user's password.
     */
    public String passwordPrompt(){
        Scanner pass = new Scanner(System.in);
        menu.pPrompt();
        return pass.nextLine();
    }

}
