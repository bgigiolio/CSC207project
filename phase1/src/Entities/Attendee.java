package Entities;

import UseCases.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Attendee implements Serializable {
    /**
     * The username of current user.
     */
    protected String username;
    /**
     * The password of current user.
     */
    protected String password;
    /**
     * A List that stores friend's username of current user.
     */
    protected List<String> friendList;

    protected List<Message> messages;     //shouldn't contain entities

    //protected EventStatus eventsRegistered;

    /**
     * The login status of current user.
     */
    protected boolean loggedIn;
    /**
     * The role of current user.
     */
    protected final String role;

    //public static List<Entities.Attendee> user; //should be in UseCases.LoginUserManager

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
        //this.eventsRegistered = new EventStatus(this);
        this.loggedIn = false;
        this.role = "attendee";
        //user.add(this);
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

    //public List<Event> getEvents(){ return this.eventsRegistered.getEventsRegistered(this); }

    /**
     * Return loggedIn.
     *
     * @return login status of current user.
     */
    public boolean getLoginStatus(){
        return this.loggedIn;
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
     * Set loggedIn.
     *
     * @param value the login status of current user.
     */
    public void setLoggedIn(boolean value){
        this.loggedIn = value;
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

