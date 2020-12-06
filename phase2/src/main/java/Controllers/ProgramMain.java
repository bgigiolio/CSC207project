package main.java.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.Presenters.*;
import main.java.UseCases.BuildingManager;
import main.java.UseCases.LoginUserManager;

/**
 * <h1>ProgramMain</h1>
 * This controller facilitates logging in and creating new accounts.
 * This should be the first thing called in the program!
 * @author Blake Gigiolio
 * @version phase1
 */
public class ProgramMain {
    private final StartingMenu presenter;
    private final BuildingManager buildingManager;
    private final NewUserController menu;
    private LoginSystem logSys;

    /**
     * This constructor sets up which building the program is going to run for.
     * @param buildingManager The building manager for the building in question.
     */
    public ProgramMain(BuildingManager buildingManager ){
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
    public void start() throws IOException{
        LoginUserManager manager;
        AttendeeMenuController currentSession;

        String password;
        String username;
        String role;
        String userType;

        do {
            do{
                presenter.initialPrompt();
                userType = askUserType();
                role = askRole();
                username = logReg(userType, role).get(0);
                password = logReg(userType, role).get(1);
            } while(username == null);

            manager = new LoginUserManager();
            manager.registerUser(username, password, role);
            currentSession = new AttendeeMenuController(username, role, this.buildingManager, manager);
        } while(!currentSession.menuSelection());
    }

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

    private List<String> logReg(String userType, String role) throws IOException {
        if (userType.equals("N"))
            return register(role);

        this.presenter.uPrompt();
        String username = this.menu.usernamePrompt();

        List<String> login_return = login(username, role);
        if(login_return.size() != 0) {
            return login_return;
        }

        return null;
    }

    private List<String> register(String role) throws IOException {
        String username, password;
        List<String> user_details = new ArrayList<>();

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
        user_details.add(username);
        user_details.add(password);
        return user_details;
    }

    /**
     * This is how a user will log in. Here we call the log in menu prompt.
     * @throws IOException Handles Scanner.
     */
    private List<String> login(String username, String role) throws IOException {
        List<String> user_details = new ArrayList<>();
        String password = this.menu.passwordPrompt();

        switch (logSys.login(username, password, role)) {
            case "loggedIn":
                this.presenter.loggedInPrompt();
                presenter.welcome(username);
                user_details.add(username);
                user_details.add(password);
                return user_details;

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

            case "wrongRole":
                presenter.wrongRole(role);
                break;

            default:
                break;
        }
        return user_details;
    }
}