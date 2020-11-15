package Presenters;

import java.util.Scanner;
import Controllers.*;

//These UI classes are just thrown together to make running the program a bit easier.
// PLEASE dont be afraid to delete these and change them a bunch!!!
public class InitialPrompt {
    private String password;
    private String username;
    private String role;
    private LoginMenu Menu;
    public void startProgram() {
        boolean answered = false;
        boolean answered2 = false;
        Scanner uname = new Scanner(System.in);
        System.out.println("Are you a new user or a returning user?");
        System.out.println("Type [N] for new.  Type [R] for returning.");
        while (!answered) {
            String response = uname.nextLine();
            if (response.equals("N") || response.equals("[N]")) {
                this.Menu = new NewUserMenu();
                answered = true;
            } else if (response.equals("R") || response.equals("[R]")) {
                this.Menu = new ReturningUserMenu();
                answered = true;
            } else {
                System.out.println("Invalid response, try again!");
            }
        }
        System.out.println("Is this account for a user or an organizer?");
        System.out.println("Type [O] for organizer or type [A] for attendee.");
        while(!answered2){
            String response2 = uname.nextLine();
            if(response2.equals("O") || response2.equals("[O]")){
                answered2 = true;
                this.role = "Entities.Organizer";
            }else if(response2.equals("A") || response2.equals("[A]")){
                answered2 = true;
                this.role = "Entities.Attendee";
            }
        }
        this.username = this.Menu.usernamePrompt();
        this.password = this.Menu.passwordPrompt();
        login();
    }
    private void login(){
        if (Menu.logReg(this.username, this.password, this.role)) {
            System.out.println("Account successfully logged in to");

        }else{
            System.out.println("Invalid account, please try again!");
            startProgram();
        }
    }
}
