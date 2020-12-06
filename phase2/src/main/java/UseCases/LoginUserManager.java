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
            if(role.equalsIgnoreCase("attendee"))
                credentialsMap.put(username, new Attendee(username, password, role));
            else if(role.equalsIgnoreCase("speaker"))
                credentialsMap.put(username, new Speaker(username, password, role));
            else if(role.equalsIgnoreCase("organizer"))
                credentialsMap.put(username, new Organizer(username, password, role));
            else
                return false;
            return true;
        }
        return false;
    }


    public String loginUser(String username, String password) {
        Attendee res = credentialsMap.get(username);

        //res is null if username is not found
        if (res == null) {
            return "usernameNotFound";
        } else if (!res.getPassword().equals(password)) {
            return "wrongPassword";
        }else {
            return "loggedIn";
        }
    }

    public boolean resetPassword(String username, String newPassword) {
        Attendee user = credentialsMap.get(username);
        if (user != null) {
            user.setPassword(newPassword);
            return true;
        } else
            return false;
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
        return credentialsMap.getOrDefault(username, null);
    }

    public boolean checkUsername(String username) {
        return this.credentialsMap.containsKey(username);
    }

    public String getUsername(Attendee user){
        return user.getUsername();
    }

    public HashMap<String, Attendee> getCredentialsMap() {
        return this.credentialsMap;
    }
}