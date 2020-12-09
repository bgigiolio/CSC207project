package main.java.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Attendee</h1>
 * Represents an Attendee in the system.
 *
 * @author Sehajroop Singh Bath
 */
public class Attendee implements Serializable {

    /**
     * The username of current user.
     */
    private final String username;

    /**
     * The password of current user.
     */
    private String password;

    /**
     * A List that stores friend's username of current user.
     */
    private final List<String> friendList;

    private final List<Message> messages;     //shouldn't contain entities

    /**
     * The role of current user.
     */
    private final String role;


    /**
     * Construct an Attendee object when signed up.
     * Initialized with a username, a password, an empty list of friends, and an empty list of
     * inbox messages.
     * loggedIn is initialized to be false and role is initialized to be "attendee".
     *
     * @param username the username of the user.
     * @param password the password of the user.
     */
    public Attendee(String username, String password){
        this.username = username;
        this.password = password;
        this.friendList = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.role = "attendee";
    }

    /**
     * Return username.
     *
     * @return username of current user.
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * Return password.
     *
     * @return password of current user.
     */
    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Return role.
     *
     * @return role of current user.
     */
    public String getRole(){
        return this.role;
    }

    /**
     * Return number of friends in friendList of current user.
     *
     * @return number of friends in friendList.
     */
    public int getNumOfFriends() { return this.friendList.size(); }

    /**
     * Return friendList.
     *
     * @return friendList of current user.
     */
    public List<String> getFriendList() { return this.friendList; }

    // test to check class works as expected
/*
    public static void main(String[] args)
    {

        // Generate new user
        Entities.Attendee user1 = new Entities.Attendee("user1", "pass");
        Entities.Attendee user2 = new Entities.Attendee("user2", "pass");

        // Get and display the alphanumeric string
        System.out.println("username is " + user1.username + "\n" + "password is " + user1.password + "\n" +
                "userID is " + user1.userid + "\n" + "\n" +
                "username is " + user2.username + "\n" +
                "password is " + user2.password + "\n" +
                "userID is " + user2.userid + "\n" + "\n" +
                "user1 userID is " + user1.userid );
    }*/
}

