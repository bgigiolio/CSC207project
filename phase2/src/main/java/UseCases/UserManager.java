package main.java.UseCases;

import main.java.Entities.*;

import java.io.*;
import java.util.*;

/**
 * <h1>UserManager</h1>
 * Maps users' username to corresponding Attendee object in a hashmap.
 * @author Morgan Chang
 * @version phase2
 */
public class UserManager implements Serializable {

    /**
     * A Hashmap that maps a user's username to its corresponding Attendee object.
     */
    private final HashMap<String, Attendee> credentialsMap;  //need for organizer message controller

    public UserManager(){
        this.credentialsMap = new HashMap<>();
    }

    /**
     * Add a new user to the list of registered users in credentialsMap
     * @param username the username of the user
     * @param password the user's password
     * @param role the user's role
     * @return True if user was successfully registered, False otherwise
     */
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

    /**
     * Check if user with following credentials can sign into the system
     * @param username user's username
     * @param password user's password
     * @return a string indicating whether this user can sign in: "usernameNotFound"/"wrongPassword"/"loggedIn"
     */
    public String loginUser(String username, String password) {
        Attendee res = credentialsMap.get(username);

        //res is null if username is not found
        if (res == null) {
            return "usernameNotFound";
        } else if (!res.getPassword().equals(password)) {
            return "wrongPassword";
        }else {
            res.setLoggedIn(true);
            return "loggedIn";
        }
    }

    /**
     * Reset the password of the user with the given credentials
     * @param username user's username
     * @param newPassword user's new password
     * @return True if password was successfully changed, False if username was not located
     */
    public boolean resetPassword(String username, String newPassword) {
        Attendee user = credentialsMap.get(username);
        if (user != null) {
            user.setPassword(newPassword);
            return true;
        } else
            return false;
    }

    /**
     * Get the role of the user with the given username
     * @param username user's username
     * @return the role of the user, null if user was not found
     */
    public String getUserRole(String username){
        Attendee res = credentialsMap.get(username);

        if (res != null) {
            return res.getRole();
        }
        else{
            return "null";
        }
    }

    /**
     * Check if given username is in credentialsMap
     * @param username the username to check
     * @return True if username is found, False otherwise
     */
    public boolean checkUsername(String username) {
        return this.credentialsMap.containsKey(username);
    }

    protected String getUsername(Attendee user){
        return user.getUsername();
    }

    /**
     * Add user2 to user1's friend list
     * @param user1 username of user1
     * @param user2 username of user2
     */
    public void addFriend(String user1, String user2){
        Attendee at1 = credentialsMap.get(user1);

        if(at1 == null || getAttendee(user2) == null)
            return;

        List<String> friendList = at1.getFriendList();

        if (!friendList.contains(user2))
            friendList.add(user2);
    }

    /**
     * Remove user2 from user1's friend list
     * @param user1 username of user1
     * @param user2 username of user2
     */
    public void removeFriend(String user1, String user2){
        Attendee at1 = credentialsMap.get(user1);

        if(at1 == null || getAttendee(user2) == null)
            return;

        List<String> friendList = at1.getFriendList();

        friendList.remove(user2);
    }

    public int getNumOfFriends(String username){
        Attendee user = getAttendee(username);

        if(user != null)
            return user.getFriendList().size();
        return -1;
    }

    private Attendee getAttendee(String username){
        return credentialsMap.getOrDefault(username, null);
    }

    public HashMap<String, Attendee> getCredentialsMap() {
        return this.credentialsMap;
    }
}