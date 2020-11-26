package Controllers;
import Presenters.StartingMenu;

import java.io.IOException;
import java.util.Scanner;

/**
 * <h1>NewUserController</h1>
 * This Controller is responsible for getting the user input from the NewUserMenu.
 * This should only interact with the AttendeeMenuController and the InitialPrompt.
 * @author Zachary Werle
 * @version Phase 1
 */
public class NewUserController implements LoginMenu {
    private final StartingMenu menu;

    /**
     * This constructor creates a new instance of NewUserController.
     */
    public NewUserController(){
        this.menu = new StartingMenu();
    }

    /**
     * This method gets the user's username from user input.
     * @return The String object representing the user's username.
     */
    public String usernamePrompt(){
        Scanner uname = new Scanner(System.in);
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

    /**
     * This method sends the new user's credentials to storage.
     * @return The boolean that shows whether the user was logged in successfully or not.
     */
    public boolean logReg(String username, String password, String role) throws IOException {
        LoginSystem log = new LoginSystem();
        return log.register(username, password, role);
    }
}
