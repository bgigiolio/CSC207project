package main.java.UseCases;

import main.java.Entities.*;

import java.io.*;
import java.util.*;

/**
 * <h1>Login User Manager</h1>
 * Maps users' username to corresponding Attendee object in a hashmap.
 * @author Morgan Chang
 * @version phase2
 */
public class LoginUserManager implements Serializable {
    /**
     * A Hashmap that maps a user's username to its corresponding Attendee object.
     */
    private HashMap<String, Attendee> credentialsMap;  //need for organizer message controller

    public LoginUserManager(){
        this.credentialsMap = new HashMap<>();
    }

    public boolean registerUser(String username, String password, String role) {
        if (!credentialsMap.containsKey(username)) {
            HashMap<String, Attendee> newUser = new HashMap<>();
            newUser.put(username, new Attendee(username, password, role));
            if (credentialsMap == null) { //to get rid of NullPointerException
                    credentialsMap = newUser;
                } else {
                    credentialsMap.put(username, new Attendee(username, password, role));
                }
                return true;
            } return false;
    }


    //long method, need to simplify later
//    public boolean registerUser(String username, String password, String role) {
//        if (!credentialsMap.containsKey(username)) {
//            HashMap<String, Attendee> newUser = new HashMap<>();
//
//            if (role.equalsIgnoreCase("attendee")) {
//                newUser.put(username, new Attendee(username, password));
//                if (credentialsMap == null) { //to get rid of NullPointerException
//                    credentialsMap = newUser;
//                } else {
//                    credentialsMap.put(username, new Attendee(username, password));
//                }
//                return true;
//            }
//            if (role.equalsIgnoreCase("organizer")) {
//                newUser.put(username, new Organizer(username, password));
//                if (credentialsMap == null) { //to get rid of NullPointerException
//                    credentialsMap = newUser;
//                } else {
//                    credentialsMap.put(username, new Organizer(username, password));
//                }
//                return true;
//            }
//            if (role.equalsIgnoreCase("speaker")) {
//                newUser.put(username, new Speaker(username, password));
//                if (credentialsMap == null) { //to get rid of NullPointerException
//                    credentialsMap = newUser;
//                } else {
//                    credentialsMap.put(username, new Speaker(username, password));
//                }
//                return true;
//            }
//        }
//        return false;
//    }

    public String loginUser(String username, String password, String role) {
        Attendee res = credentialsMap.get(username);

        //res is null if username is not found
        if (res == null) {
            return "usernameNotFound";
        } else if (!res.getPassword().equals(password)) {
            return "wrongPassword";
        } else if (res.getPassword().equals(password) && !res.getRole().equalsIgnoreCase(role)) {
            return "wrongRole";
        } else {
            return "loggedIn";
        }
    }

    public boolean resetPassword(String username, String newPassword) {
        Attendee user = credentialsMap.get(username);
        if (user != null) {
            user.setPassword(newPassword);
            return true;
        } else {
            return false; }
    }

    public void logoutUser(String username){
        Attendee res = credentialsMap.get(username);

        if (res != null) {
            credentialsMap.get(username).setLoggedIn(false);    //will this crash if username is not found?
        }
    }

    public String userRole(String username){
        Attendee res = credentialsMap.get(username);

        if (res != null) {
            return res.getRole();  //same for this one
        }
        else{
            return "null";
        }
    }

    public Attendee getAttendee(String username){
        if (credentialsMap.containsKey(username)) {
            return credentialsMap.get(username);
        }
        else{
            return null;
        }
    }

    public boolean checkUsername(String username) {
        Attendee res = credentialsMap.get(username);

        if (res != null) {
            return true;
        }
        else{
            return false;
        }
    }

    public String getUsername(Attendee user){
        return user.getUsername();
    }

    // for serializing

    public HashMap<String, Attendee> getCredentialsMap() {
        return this.credentialsMap;
    }

    public void setCredentialsMap(HashMap<String, Attendee> credentialsMap) {
        this.credentialsMap = credentialsMap;
    }
}