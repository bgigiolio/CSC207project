package Controllers;

import java.io.IOException;
import java.util.Scanner;
import Presenters.*;
import UseCases.BuildingManager;
import UseCases.LoginUserManager;

//These UI classes are just thrown together to make running the program a bit easier.
// PLEASE dont be afraid to delete these and change them a bunch!!!
public class InitialPrompt {
    private String password;
    private String username;
    private String role;
    private LoginMenu Menu;
    private StartingMenu presenter;
    private BuildingManager buildingManager;

    public InitialPrompt(BuildingManager buildingManager ){
        this.buildingManager = buildingManager;
    }
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

    private void login() throws IOException {
        if (Menu.logReg(this.username, this.password, this.role)) {
            this.presenter.loggedInPrompt();

        }else{
            this.presenter.failedPrompt();
            startProgram();
        }
    }
}
