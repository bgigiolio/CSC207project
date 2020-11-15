package Controllers;

import Entities.Attendee;
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

    public String assignPrivileges(String username) {
        return manager.userRole(username);
    }

    public Attendee user(String username){
        return manager.user(username);
    }

    public String username(Attendee user){
        return manager.username(user);
    }
}
