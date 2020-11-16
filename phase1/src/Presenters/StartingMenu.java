package Presenters;

public class StartingMenu {
    public void initialPrompt(){
        System.out.println("Are you a new user or a returning user?");
        System.out.println("Type [N] for new.  Type [R] for returning.");
    }
    public void failedPrompt(){
        System.out.println("Invalid response, try again!");
    }
    public void rolePrompt(){
        System.out.println("Is this account for a user or an organizer?");
        System.out.println("Type [O] for organizer or type [A] for attendee.");
    }
    public void loggedInPrompt(){
        System.out.println("Account successfully logged in to");
    }
    public void uPrompt(){
        System.out.println("Please enter your username: ");
    }
    public void pPrompt(){
        System.out.println("Please enter your password: ");
    }
}
