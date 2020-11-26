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
 * @version phase1
 */
public class InitialPrompt {
    private String password;
    private String username;
    private String role;
    private String userType;
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
    public void startProgram() throws IOException, ClassNotFoundException {
        Scanner uname = new Scanner(System.in);
        this.presenter = new StartingMenu();
        this.presenter.initialPrompt();
        this.Menu = new NewUserController();

        askUserType();

        askRole();

        logReg();

        LoginUserManager manager = new LoginUserManager();
        AttendeeMenuController organizerMenu =
                new AttendeeMenuController(this.username, this.role, this.buildingManager, manager);
        organizerMenu.menuSelection();
    }


    private void askUserType() {
        boolean answered = false;
        while (!answered) {
            String response = new Scanner(System.in).nextLine();
            if (response.equalsIgnoreCase("N") || response.equalsIgnoreCase("[N]")) {
                userType = "N";
                answered = true;
            } else if (response.equalsIgnoreCase("R") || response.equalsIgnoreCase("[R]")) {
                userType = "R";
                answered = true;
            } else {
                this.presenter.failedPrompt();
            }
        }
    }

    public void askRole() {
        boolean answered2 = false;
        this.presenter.rolePrompt();
        while(!answered2){
            String response2 = new Scanner(System.in).nextLine();
            if(response2.equalsIgnoreCase("O") || response2.equalsIgnoreCase("[O]")){
                answered2 = true;
                this.role = "Organizer";
            }else if(response2.equalsIgnoreCase("A") || response2.equalsIgnoreCase("[A]")){
                answered2 = true;
                this.role = "Attendee";
            }else if(response2.equalsIgnoreCase("S") || response2.equalsIgnoreCase("[S]")){
                answered2 = true;
                this.role = "Speaker";
            } else {
                this.presenter.failedPrompt();
            }
        }
    }

    /**
     * This is how a user will log in. Here we call the log in menu prompt.
     * @throws IOException Handles Scanner.
     */
    private void login() throws IOException, ClassNotFoundException {
        LoginSystem log = new LoginSystem();
        this.password = this.Menu.passwordPrompt();
        if (log.login(this.username, this.password, this.role).equals("loggedIn")) {
            this.presenter.loggedInPrompt();
            System.out.println("Welcome " + this.username + "!");
        } else if (log.login(this.username, this.password, this.role).equals("usernameNotFound")) {
            this.presenter.usernameNotFoundPrompt();
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("1")) {
                logReg();
            } else {
                askRole();
                register();
            }
        } else if (log.login(this.username, this.password, this.role).equals("wrongPassword")) {
            presenter.wrongPasswordPrompt();
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("2")) {
                presenter.resetPasswordPrompt();
                String newPassword = new Scanner(System.in).nextLine();
                if (log.resetPassword(this.username, newPassword)) {
                    presenter.passwordResetSuccess();
                    logReg();
                } else {
                    System.out.println("failed"); //need to fix later
                }
            } else {
                login();
            }

        } else if (log.login(this.username, this.password, this.role).equals("wrongRole")) {
            presenter.wrongRole(log.roleOfAccount(username));
            startProgram();
        }
    }

    private void register() throws IOException {
        LoginSystem log = new LoginSystem();
        boolean answered = false;
        this.presenter.uPrompt();
        this.username = this.Menu.usernamePrompt();
        while (!answered) {
            if (log.usernameExist(username)) {
                presenter.usernameUsed();
                this.username = this.Menu.usernamePrompt();
            } else {
                answered = true;
            }
        }
        this.password = this.Menu.passwordPrompt();
        if (Menu.logReg(this.username, this.password, this.role)) {
            this.presenter.newUserCreated();
            System.out.println("Welcome " + this.username + "!");
        }

    }

    public void logReg() throws IOException, ClassNotFoundException {
        if (userType.equals("N")) {
            register();
        } else {
            this.presenter.uPrompt();
            this.username = this.Menu.usernamePrompt();
            login();
        }
    }
}
