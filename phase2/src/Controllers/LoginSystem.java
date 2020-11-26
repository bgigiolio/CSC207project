package Controllers;

import Gateways.UserLoginInfo;
import UseCases.*;

import java.io.IOException;

public class LoginSystem {

    /**
     * The UserLoginInfo object that stores the user information.
     */
    private UserLoginInfo userLoginInfo = new UserLoginInfo(); //gateway

    /**
     * Construct a LoginSystem.
     * Initialized a new UserLoginInfo object.
     */

    public boolean register(String username, String password, String role) throws IOException {
        String db = "phase2/src/DB/UserLoginInfo.ser";
        userLoginInfo.setLoginUserManager(userLoginInfo.getFileUserLoginInfo(db));
        boolean returnVal = userLoginInfo.getLoginUserManager().registerUser(username, password, role);
        userLoginInfo.setFileUserLoginInfo(db);
        return returnVal;
    }

    public boolean usernameExist(String username) throws IOException {
        String db = "phase2/src/DB/UserLoginInfo.ser";
        userLoginInfo.setLoginUserManager(userLoginInfo.getFileUserLoginInfo(db));
        return userLoginInfo.getLoginUserManager().checkUsername(username);
    }

    public String login(String username, String password, String role) throws IOException {
        String db = "phase2/src/DB/UserLoginInfo.ser";
        userLoginInfo.setLoginUserManager(userLoginInfo.getFileUserLoginInfo(db));
        return userLoginInfo.getLoginUserManager().loginUser(username, password, role);
    }

    public boolean resetPassword(String username, String password) throws IOException {
        String db = "phase2/src/DB/UserLoginInfo.ser";
        userLoginInfo.setLoginUserManager(userLoginInfo.getFileUserLoginInfo(db));
        boolean returnVal = userLoginInfo.getLoginUserManager().resetPassword(username, password);
        userLoginInfo.setFileUserLoginInfo(db);
        return returnVal;
    }

    public String roleOfAccount(String username) throws IOException {
        String db = "phase2/src/DB/UserLoginInfo.ser";
        userLoginInfo.setLoginUserManager(userLoginInfo.getFileUserLoginInfo(db));
        return userLoginInfo.getLoginUserManager().userRole(username);
    }

    public void logout(String username) {
        userLoginInfo.getLoginUserManager().logoutUser(username);
    }

    public String checkPrivileges(String username) {
        return userLoginInfo.getLoginUserManager().userRole(username);
    }

/*    public Object user(String username){
        return manager.getAttendee(username);
    }

    public String username(Object user){
        return manager.getUsername(user);
    }*/
}
