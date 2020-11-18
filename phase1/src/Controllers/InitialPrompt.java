package Controllers;

import java.io.IOException;
import java.util.Scanner;
import Presenters.*;

//These UI classes are just thrown together to make running the program a bit easier.
// PLEASE dont be afraid to delete these and change them a bunch!!!
public class InitialPrompt {
    private String password;
    private String username;
    private String role;
    private LoginMenu Menu;
    private StartingMenu presenter;

    public void startProgram() throws IOException {
        boolean answered = false;
        boolean answered2 = false;
        Scanner uname = new Scanner(System.in);
        this.presenter = new StartingMenu();
        this.presenter.initialPrompt();
        while (!answered) {
            String response = uname.nextLine();
            if (response.equals("N") || response.equals("[N]")) {
                this.Menu = new NewUserController();
                answered = true;
            } else if (response.equals("R") || response.equals("[R]")) {
                this.Menu = new ReturningUserController();
                answered = true;
            } else {
            this.presenter.failedPrompt();
            }
        }
        this.presenter.rolePrompt();
        while(!answered2){
            String response2 = uname.nextLine();
            if(response2.equals("O") || response2.equals("[O]")){
                answered2 = true;
                this.role = "Organizer";
            }else if(response2.equals("A") || response2.equals("[A]")){
                answered2 = true;
                this.role = "Attendee";
            }
        }
        this.username = this.Menu.usernamePrompt();
        this.password = this.Menu.passwordPrompt();
        login();

        if (this.role.equals("Organizer")) {
            OrganizerMenu organizerMenu = new OrganizerMenu(this.username);
            organizerMenu.printMenu();
        } else {
            UserMenu userMenu = new UserMenu(this.username);
            userMenu.optionsAttendee();
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
