package Controllers;

import Gateways.UserLoginInfo;
import UseCases.*;

import java.io.IOException;

public class LoginSystem {

    private UserLoginInfo userLoginInfo; //gateway

    public LoginSystem() {
        this.userLoginInfo = new UserLoginInfo();
    }

    public boolean register(String username, String password, String role) throws IOException {
        String db = "phase1/src/DB/UserLoginInfo.txt";
        userLoginInfo.setLoginUserManager(userLoginInfo.getFileUserLoginInfo(db));
        boolean returnVal = userLoginInfo.getLoginUserManager().registerUser(username, password, role);
        userLoginInfo.setFileUserLoginInfo(db);
        return returnVal;
    }

    public boolean login(String username, String password) throws IOException {
        String db = "phase1/src/DB/UserLoginInfo.txt";
        userLoginInfo.setLoginUserManager(userLoginInfo.getFileUserLoginInfo(db));
        return userLoginInfo.getLoginUserManager().loginUser(username, password);
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
