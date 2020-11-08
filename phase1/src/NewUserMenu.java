import java.util.Scanner;
//These UI classes are just thrown together to make running the program a bit easier.
// PLEASE dont be afraid to delete these and change them a bunch!!!
public class NewUserMenu implements LoginMenu{
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
    public boolean logReg(String username, String password, String role) {
        LoginSystem log = new LoginSystem();
        return log.register(username, password, role);
    }
}
