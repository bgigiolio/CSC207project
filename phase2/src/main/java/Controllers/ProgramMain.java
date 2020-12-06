package main.java.Controllers;

import java.io.IOException;
import java.util.Scanner;

import main.java.Presenters.*;
import main.java.UseCases.BuildingManager;
import main.java.UseCases.LoginUserManager;

/**
 * <h1>ProgramMain</h1>
 * This controller facilitates logging in and creating new accounts.
 * This should be the first thing called in the program!
 * @author Konstantinos Papaspyridis
 * @version phase2
 */
public class ProgramMain {
    private final StartingMenu presenter;
    private final BuildingManager buildingManager;
    private final NewUserController menu;
    private final LoginSystem logSys;

    /**
     * This constructor sets up which building the program is going to run for.
     * @param buildingManager The building manager for the building in question.
     */
    public ProgramMain(BuildingManager buildingManager ) {
        this.buildingManager = buildingManager;
        this.presenter = new StartingMenu();
        this.menu = new NewUserController();
        this.logSys =  new LoginSystem();
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
    public void start() throws IOException, ClassNotFoundException {
        LoginUserManager manager;
        AttendeeMenuController currentSession;

        String username;
        String role;
        String userType;

        do {
            do{
                presenter.initialPrompt();
                userType = askUserType();

                if(userType.equals("N")) {
                    role = askRole();
                    username = register(role);
                }else {
                    username = login();
                    role = logSys.roleOfAccount(username);
                }
            } while(username == null);

            manager = new LoginUserManager();
            currentSession = new AttendeeMenuController(username, role, buildingManager, manager);
        } while(!currentSession.menuSelection());
    }

    /**
     * Return if user wants to create new account or login
     * @return "N" for new user, "R" for returning user
     */
    private String askUserType() {
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
                this.presenter.failedPrompt();
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
        this.presenter.rolePrompt();

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
                this.presenter.failedPrompt();
        }
        return role;
    }

    /**
     * Register a new user
     * @param role user's roles
     * @return the username of the registered user
     * @throws IOException Handles IO Exception by Scanner
     */
    private String register(String role) throws IOException {
        String username, password;

        this.presenter.uPrompt();
        username = this.menu.usernamePrompt();

        while(this.logSys.usernameExist(username)){
            presenter.usernameUsed();
            username = this.menu.usernamePrompt();
        }

        password = this.menu.passwordPrompt();

        if (logSys.register(username, password, role)) {
            presenter.newUserCreated();
            presenter.welcome(username);
        }

        return username;
    }

    /**
     * This is how a user will log in. Here we call the log in menu prompt.
     * @return username of user logged. Null if couldn't log in.
     * @throws IOException Handles Scanner.
     */
    private String login() throws IOException {
        this.presenter.uPrompt();
        String username = this.menu.usernamePrompt();
        String password = this.menu.passwordPrompt();

        switch (logSys.login(username, password)) {
            case "loggedIn":
                this.presenter.loggedInPrompt();
                presenter.welcome(username);
                return username;

            case "usernameNotFound": {
                this.presenter.usernameNotFoundPrompt();
                break;
            }

            case "wrongPassword": {
                presenter.wrongPasswordPrompt();
                String choice = new Scanner(System.in).nextLine();

                if (choice.equals("2")) {
                    presenter.resetPasswordPrompt();
                    String newPassword = new Scanner(System.in).nextLine();

                    if (logSys.resetPassword(username, newPassword)) {
                        presenter.passwordResetSuccess();
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