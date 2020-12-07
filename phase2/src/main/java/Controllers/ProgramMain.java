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
public class ProgramMain {
    private final BuildingManager buildingManager;
    private final UserManager userManager;

    /**
     * This constructor sets up which building the program is going to run for.
     * @param buildingManager The building manager for the building in question.
     */
    public ProgramMain(BuildingManager buildingManager ) {
        UserLoginGateway userLoginGateway = new UserLoginGateway();
        this.buildingManager = buildingManager;
        this.userManager = userLoginGateway.getStoredUserData();
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
    public void start(){
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

            didQuit = currentSession.menuSelection();
            userLoginGateway.saveUserLoginInfo(userManager);
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
     * Register a new user
     * @param role user's roles
     * @return the username of the registered user
     */
    private String register(String role){
        String username, password;
        NewUserController credentialsPrompt = new NewUserController();
        StartingMenu menuPresenter = new StartingMenu();

        menuPresenter.uPrompt();
        username = credentialsPrompt.usernamePrompt();

        while(this.userManager.checkUsername(username)){
            menuPresenter.usernameUsed();
            username = credentialsPrompt.usernamePrompt();
        }

        password = credentialsPrompt.passwordPrompt();

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
    private String login(){
        NewUserController credentialsPrompt = new NewUserController();
        StartingMenu menuPresenter = new StartingMenu();

        menuPresenter.uPrompt();

        String username = credentialsPrompt.usernamePrompt();
        String password = credentialsPrompt.passwordPrompt();

        switch (this.userManager.loginUser(username, password)) {
            case "loggedIn":
                menuPresenter.loggedInPrompt();
                menuPresenter.welcome(username);
                return username;

            case "usernameNotFound": {
                menuPresenter.usernameNotFoundPrompt();
                String choice = new Scanner(System.in).nextLine();
                if (choice.equals("1")) {
                    login();
                } else { start(); }
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
                    } else {
                        System.out.println("failed"); //need to fix later
                    }
                }
                break;
            }

            default:
                break;
        }
        return null;
    }
}