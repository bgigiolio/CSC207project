package Controllers;

import UseCases.*;

public class LoginSystem {
    public LoginUserManager manager;

    public LoginSystem() {
        this.manager = new LoginUserManager();
    }

    public boolean register(String username, String password, String role) {
        return manager.registerUser(username, password, role);
    }

    public boolean login(String username, String password) {
        return manager.loginUser(username, password);
    }

    public void logout(String username) {
        manager.logoutUser(username);
    }

//    public String assignPrivileges(String username) {
//        //need to assign access level based on user role
//    }
}
/*    public static void main(String[] args)
    {
        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
        System.out.print("Enter first number- ");
        int a= sc.nextInt();
        System.out.print("Enter second number- ");
        int b= sc.nextInt();
        System.out.print("Enter third number- ");
        int c= sc.nextInt();
        int d=a+b+c;
        System.out.println("Total= " +d);
    }
}*/
