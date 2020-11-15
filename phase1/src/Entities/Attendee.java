package Entities;

import java.util.ArrayList;
import java.util.List;

public class Attendee {
    protected String username;
    protected String password;
    //String userid;
    protected List<String> friendList;  //list of friend's username
    protected List<Message> messages;     //shouldn't contain entities
    List<Event> eventsRegistered;   //shouldn't contain entities
    protected boolean loggedIn;
    protected String role;
    //public static List<Entities.Attendee> user; //should be in UseCases.LoginUserManager

    public Attendee(String username, String password){
        this.username = username;
        this.password = password;
        //this.userid = UseridGenerator.getAlphaNumericString(20); //assigns random alphanumeric string of size n to user
        this.friendList = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.eventsRegistered = new ArrayList<>();
        this.loggedIn = false;
        this.role = "attendee";
        //user.add(this);
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    /*public String getUserid(){
        return this.userid;
    }*/

    public List<Event> getEventsRegistered(){
        return this.eventsRegistered;
    }

    public boolean getLoginStatus(){
        return this.loggedIn;
    }

    public String getRole(){
        return this.role;
    }

    public void sendMessage(String stringMessage, Attendee receiver){
        new MessageCreator(stringMessage, receiver, this.username);
    }

    public void setLoggedIn(boolean value){
        this.loggedIn = value;
    }

    public int getNumOfFriends() { return this.friendList.size(); }

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

