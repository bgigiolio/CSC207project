package main.java.Controllers;

import main.java.Gateways.UserLoginInfo;

import java.io.IOException;

/**
 * <h1>LoginSystem</h1>
 * Responsible for interacting with <code>UserLoginInfo</code> gateway to retrieve and modify
 * data about users stored in the database
 */
public class LoginSystem {

    /**
     * The UserLoginInfo (Gateway) object that stores the user information.
     */
    private final UserLoginInfo userLoginInfo;

    public LoginSystem(){
        userLoginInfo = new UserLoginInfo();
    }

    public boolean register(String username, String password, String role) throws IOException {
        return userLoginInfo.createUser(username, password, role);
    }

    public boolean usernameExist(String username){
        return userLoginInfo.userExists(username);
    }

    public String login(String username, String password) throws IOException {
        return userLoginInfo.login(username, password);
    }

    public boolean resetPassword(String username, String password) {
        return userLoginInfo.resetPassword(username, password);
    }

    public String roleOfAccount(String username) {
        return userLoginInfo.getUserRole(username);
    }

    public void logout() {
        userLoginInfo.logout();
    }

/*    public Object user(String username){
        return manager.getAttendee(username);
    }

    public String username(Object user){
        return manager.getUsername(user);
    }*/
}
