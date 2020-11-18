package Controllers;

import UseCases.*;

import java.io.IOException;

public class LoginSystem {
    public LoginUserManager manager;

    public LoginSystem() {
        this.manager = new LoginUserManager();
    }

    public boolean register(String username, String password, String role) throws IOException {
        manager.getFileUserLoginInfo("UserLoginInfo.txt");
        boolean returnVal = manager.registerUser(username, password, role);
        manager.setFileUserLoginInfo("UserLoginInfo.txt");
        return returnVal;
    }

    public boolean login(String username, String password) {
        return manager.loginUser(username, password);
    }

    public void logout(String username) {
        manager.logoutUser(username);
    }

    public String checkPrivileges(String username) {
        return manager.userRole(username);
    }

/*    public Object user(String username){
        return manager.getAttendee(username);
    }

    public String username(Object user){
        return manager.getUsername(user);
    }*/
}
