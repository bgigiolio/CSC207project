import java.util.ArrayList;
import java.util.List;

public class Attendee {
    String username;
    String password;
    String userid;
    List<Attendee> friendlist;
    List<Message> messages;
    List<Event> registration;
    boolean loggedIn;
    String role;
    public static List<Attendee> user;

    public Attendee(String username, String password){
        this.username = username;
        this.password = password;
        this.userid = UseridGenerator.getAlphaNumericString(20); //assigns random alphanumeric string of size n to user
        this.friendlist = new ArrayList<Attendee>();
        this.messages = new ArrayList<Message>();
        this.registration = new ArrayList<Event>();
        this.loggedIn = false;
        this.role = "attendee";
        user.add(this);
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getUserid(){
        return this.userid;
    }

    public List<Event> getRegistration(){
        return this.registration;
    }

    public boolean getLoginStatus(){
        return this.loggedIn;
    }

    public String getRole(){
        return this.role;
    }

    public void sendMessage(String stringMessage, Attendee receiver){
        new MessageCreator(stringMessage, receiver, this.userid);
    }

    public void setLoggedIn(boolean value){
        this.loggedIn = value;
    }


    // test to check class works as expected
/*
    public static void main(String[] args)
    {

        // Generate new user
        Attendee user1 = new Attendee("user1", "pass");
        Attendee user2 = new Attendee("user2", "pass");

        // Get and display the alphanumeric string
        System.out.println("username is " + user1.username + "\n" + "password is " + user1.password + "\n" +
                "userID is " + user1.userid + "\n" + "\n" +
                "username is " + user2.username + "\n" +
                "password is " + user2.password + "\n" +
                "userID is " + user2.userid + "\n" + "\n" +
                "user1 userID is " + user1.userid );
    }*/
}

