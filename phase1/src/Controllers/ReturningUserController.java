package Controllers;//These UI classes are just thrown together to make running the program a bit easier.
// PLEASE dont be afraid to delete these and change them a bunch!!!
import java.util.Scanner;
import Controllers.*;
import Presenters.LoginMenu;
import Presenters.StartingMenu;

public class ReturningUserController implements LoginMenu {
    private final StartingMenu menu;
    public ReturningUserController(){
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

    @Override
    public boolean logReg(String username, String password, String role) {
        LoginSystem log = new LoginSystem();
        return (log.login(username, password));
    }
}
