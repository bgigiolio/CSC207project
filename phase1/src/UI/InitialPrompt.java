package UI;

import java.util.Scanner;
//These UI classes are just thrown together to make running the program a bit easier.
// PLEASE dont be afraid to delete these and change them a bunch!!!
public class InitialPrompt {
    public Object Menu;
    public void startProgram() {
        boolean answered = false;
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
    }
}
