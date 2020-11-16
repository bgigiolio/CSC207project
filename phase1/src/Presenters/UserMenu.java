package Presenters;

public class UserMenu {
    private final String username;
    public UserMenu(String username){
        this.username = username;
    }
    public void optionsAttendee(){
        System.out.println("Welcome " + this.username + "!");
        System.out.println("---General Actions---");
        System.out.println("[See Entities.Event UseCases.Schedule]  [Sign Up For Entities.Event]  [Cancel Entities.Event]  " +
                "[Send Entities.Message]  [Review Messages]  [Manage Friends List]");
    }
    public void optionsOrganizer(){
        System.out.println("---Entities.Organizer Specific Actions---");
        System.out.println("[Create Entities.Speaker]  [Add Room]  [UseCases.Schedule Entities.Speaker]  [Manage Entities.Event]  ");
    }
    public void awaitResponse(){
        System.out.println("What would you like to do?");
    }
    public void invalidResponse(){
        System.out.println("That is not a valid response. Please try again!");
    }
}
