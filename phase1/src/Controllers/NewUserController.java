package Controllers;
import Presenters.StartingMenu;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 */
//These UI classes are just thrown together to make running the program a bit easier.
// PLEASE dont be afraid to delete these and change them a bunch!!!
public class NewUserController implements LoginMenu {
    private final StartingMenu menu;
    public NewUserController(){
        this.menu = new StartingMenu();
    }
    public String usernamePrompt(){
        Scanner uname = new Scanner(System.in);
        menu.uPrompt();
        return uname.nextLine();
    }
    public String passwordPrompt(){
        Scanner pass = new Scanner(System.in);
        menu.pPrompt();
        return pass.nextLine();
    }
    public boolean logReg(String username, String password, String role) throws IOException {
        LoginSystem log = new LoginSystem();
        return log.register(username, password, role);
    }
}
