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
