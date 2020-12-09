package main.java.Controllers;

import java.io.IOException;
import java.util.Scanner;

import main.java.Gateways.UserLoginGateway;
import main.java.Presenters.*;
import main.java.UseCases.BuildingManager;
import main.java.UseCases.UserManager;

/**
 * <h1>ProgramMain</h1>
 * This controller facilitates logging in and creating new accounts.
 * This should be the first thing called in the program!
 * @author Konstantinos Papaspyridis
 * @version phase2
 */
public class ProgramMainGUI {
    private final BuildingManager buildingManager;
    private final UserManager userManager;

    /**
     * This constructor sets up which building the program is going to run for.
     * @param buildingManager The building manager for the building in question.
     */
    public ProgramMainGUI(BuildingManager buildingManager) {
        UserLoginGateway userLoginGateway = new UserLoginGateway();
        this.buildingManager = buildingManager;
        this.userManager = userLoginGateway.read();
    }

    /**
     * This is the first method that should be run in the program, allowing the user to log in
     * by inputting strings for usernames and passwords.
     * This method follows this pattern:
     *  -Asks whether or not user is new or returning
     *  -Asks user whether they are an organizer or an attendee
     *  -Logs the user in
     *  -Initializes the post-login menu
     */
    public void start() throws ClassNotFoundException, IOException {
        AttendeeMenuController currentSession;
        StartingMenu menuPresenter = new StartingMenu();
        UserLoginGateway userLoginGateway = new UserLoginGateway();
        String username;
        String role;
        String userType;
        boolean didQuit;

        do {
            do{
                menuPresenter.initialPrompt();
                userType = askUserType();

                if(userType.equals("N")) {
                    role = askRole();
                    username = register(role);
                }else {
                    username = login();
                    role = userManager.getUserRole(username);
                }
            } while(username == null);

            currentSession = new AttendeeMenuController(username, role, buildingManager, userManager);

            currentSession.homepage();
            didQuit = currentSession.menuSelection();
            userLoginGateway.save(userManager);
        } while(!didQuit);
    }

    /**
     * Return if user wants to create new account or login
     * @return "N" for new user, "R" for returning user
     */
    private String askUserType() {
        StartingMenu menuPresenter = new StartingMenu();
        boolean answered = false;
        String userType = "";

        while (!answered) {
            String response = new Scanner(System.in).nextLine();
            if (response.equalsIgnoreCase("N")) {
                userType = "N";
                answered = true;
            } else if (response.equalsIgnoreCase("R")) {
                userType = "R";
                answered = true;
            } else {
                menuPresenter.failedPrompt();
            }
        }
        return userType;
    }

    /**
     * Return what kind of account this user wants to create
     * @return "Organizer"/"Attendee"/"Speaker"
     */
    private String askRole() {
        boolean answered2 = false;
        String role = "";
        StartingMenu menuPresenter = new StartingMenu();

        menuPresenter.rolePrompt();

        while(!answered2){
            String response2 = new Scanner(System.in).nextLine();
            if(response2.equalsIgnoreCase("O") || response2.equalsIgnoreCase("[O]")){
                answered2 = true;
                role = "Organizer";
            }else if(response2.equalsIgnoreCase("A") || response2.equalsIgnoreCase("[A]")){
                answered2 = true;
                role = "Attendee";
            }else if(response2.equalsIgnoreCase("S") || response2.equalsIgnoreCase("[S]")){
                answered2 = true;
                role = "Speaker";
            } else
                menuPresenter.failedPrompt();
        }
        return role;
    }

    /**
     * This method gets new user's username from user input.
     * @return The String object representing the user's username.
     */
    public String newUserUsernamePrompt(){
        Scanner uname = new Scanner(System.in);
        return uname.nextLine();
    }

    /**
     * This method gets returning user's username from user input.
     * @return The String object representing the user's username.
     */
    public String retUserUsernamePrompt(){
        Scanner uname = new Scanner(System.in);
        new StartingMenu().uPrompt();
        return uname.nextLine();
    }

    /**
     * This method gets the user's password from user input.
     * @return The String object representing the user's password.
     */
    public String passwordPrompt(){
        Scanner pass = new Scanner(System.in);
        new StartingMenu().pPrompt();
        return pass.nextLine();
    }

    /**
     * Register a new user
     * @param role user's roles
     * @return the username of the registered user
     */
    private String register(String role){
        String username, password;
        StartingMenu menuPresenter = new StartingMenu();

        menuPresenter.uPrompt();
        username = newUserUsernamePrompt();

        while(this.userManager.checkUsername(username)){
            menuPresenter.usernameUsed();
            username = newUserUsernamePrompt();
        }

        password = passwordPrompt();

        if (this.userManager.registerUser(username, password, role)) {
            menuPresenter.newUserCreated();
            menuPresenter.welcome(username);
        }

        return username;
    }

    /**
     * This is how a user will log in. Here we call the log in menu prompt.
     * @return username of user logged. Null if couldn't log in.
     */
    private String login() {
        StartingMenu menuPresenter = new StartingMenu();

        String username = retUserUsernamePrompt();
        String password = passwordPrompt();

        switch (this.userManager.loginUser(username, password)) {
            case "loggedIn":
                menuPresenter.loggedInPrompt();
                menuPresenter.welcome(username);
                return username;

            case "usernameNotFound": {
                menuPresenter.usernameNotFoundPrompt();
                String choice = new Scanner(System.in).nextLine();
                if (choice.equals("1"))
                    return login();
                break;
            }

            case "wrongPassword": {
                menuPresenter.wrongPasswordPrompt();
                String choice = new Scanner(System.in).nextLine();

                if (choice.equals("2")) {
                    menuPresenter.resetPasswordPrompt();
                    String newPassword = new Scanner(System.in).nextLine();

                    if (this.userManager.resetPassword(username, newPassword)) {
                        menuPresenter.passwordResetSuccess();
                        return username;
                    } else {
                        System.out.println("failed");
                    }
                }
                else if (choice.equals("1")) {
                    return login();
                }
                break;
            }

            default:
                break;
        }
        return null;
    }
}