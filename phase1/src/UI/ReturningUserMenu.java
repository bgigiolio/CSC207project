package UI;
//These UI classes are just thrown together to make running the program a bit easier.
// PLEASE dont be afraid to delete these and change them a bunch!!!
import java.util.Scanner;

public class ReturningUserMenu {
    public String usernamePrompt(){
        Scanner uname = new Scanner(System.in);
        System.out.println("Please enter your username: ");
        return uname.nextLine();
    }
    public String passwordPrompt(){
        Scanner pass = new Scanner(System.in);
        System.out.println("Please enter your password: ");
        return pass.nextLine();
    }
}
