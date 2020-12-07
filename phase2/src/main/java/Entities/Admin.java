package main.java.Entities;

public class Admin extends Attendee{
    /**
     * The role of current user.
     */
    private final String role;

    public Admin(String username, String password){
        super(username, password);
        this.role = "admin";
    }
}
