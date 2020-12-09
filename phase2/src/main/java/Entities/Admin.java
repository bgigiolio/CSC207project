package main.java.Entities;

public class Admin extends Attendee{

    public Admin(String username, String password){
        super(username, password, "admin");
    }
}
