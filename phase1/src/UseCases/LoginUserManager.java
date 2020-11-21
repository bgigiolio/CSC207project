package UseCases;

import Entities.*;

import java.io.*;
import java.util.*;

/**
 * Allows a user to log in, log out, and allows other classes to access information about the user
 */
public class LoginUserManager implements Serializable {
    /**
     * A Hashmap that maps a user's username to its corresponding Attendee object.
     */
    private HashMap<String, Attendee> credentialsMap;  //need for organizer message controller

    /**
     * Creates a new credentials map
     */
    public LoginUserManager(){
        this.credentialsMap = new HashMap<>();
    }

    /**
     * Allows us to create a new user by logging in with new credentials
     * @param username The username of the new user
     * @param password The password of the new user
     * @param role The new users role
     * @return True if the user was created, false if it wasn't.
     */
    public boolean registerUser(String username, String password, String role) {
        if (!credentialsMap.containsKey(username)) {
            HashMap<String, Attendee> newUser = new HashMap<>();

            if (role.equalsIgnoreCase("attendee")) {
                newUser.put(username, new Attendee(username, password));
                if (credentialsMap == null) { //to get rid of NullPointerException
                    credentialsMap = newUser;
                } else {
                    credentialsMap.put(username, new Attendee(username, password));
                }
                return true;
            }
            if (role.equalsIgnoreCase("organizer")) {
                newUser.put(username, new Organizer(username, password));
                if (credentialsMap == null) { //to get rid of NullPointerException
                    credentialsMap = newUser;
                } else {
                    credentialsMap.put(username, new Organizer(username, password));
                }
                return true;
            }
            if (role.equalsIgnoreCase("speaker")) {
                newUser.put(username, new Speaker(username, password));
                if (credentialsMap == null) { //to get rid of NullPointerException
                    credentialsMap = newUser;
                } else {
                    credentialsMap.put(username, new Speaker(username, password));
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Logs a user in using existing credentials.
     * @param username The users username
     * @param password The users password
     * @return stuff
     * @throws IOException accounts for input
     */
    public boolean loginUser(String username, String password) throws IOException {
        Attendee res = credentialsMap.get(username);

        //res is null if username is not found
        if (res != null && res.getPassword().equals(password)) {
            res.setLoggedIn(true);
            return true;
        }
        else
            return false;
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
            return credentialsMap.get(username).getRole();  //same for this one
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