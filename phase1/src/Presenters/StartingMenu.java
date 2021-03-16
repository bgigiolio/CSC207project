package Presenters;

/**
 * <h1>Starting Menu</h1>
 * This presenter contains the possible print statements that can occur while creating an account or signing in.
 * This is the menu that should be shown to a user immediately when they start up the program.
 * @author Blake Gigiolio
 */
public class StartingMenu {
    /**
     * Prints the first prompt the user should see when the program is started.
     */
    public void initialPrompt(){
        System.out.println("Are you a new user or a returning user?");
        System.out.println("Type [N] for new.  Type [R] for returning.");
    }

    /**
     * This is what the user should see when they input an invalid response.
     */
    public void failedPrompt(){
        System.out.println("Invalid response, try again!");
    }

    /**
     * This is what the user should see if the account they are trying to make already exists.
     */
    public void alreadyRegistered() {
        System.out.println("User already registered. Try logging in...");
    }

    /**
     * This is what the user should see when creating account asking them what their desired role is.
     */
    public void rolePrompt(){
        System.out.println("Is this account for an organizer, an attendee, or a speaker?");
        System.out.println("Type [O] for organizer, type [A] for attendee, or type [S] for speaker.");
    }

    /**
     * This is what the user should see if they properly logged in.
     */
    public void loggedInPrompt(){
        System.out.println("Account successfully logged in to");
    }

    /**
     * This is what the user should see when they need to input a username.
     */
    public void uPrompt(){
        System.out.println("Please enter your username: ");
    }

    /**
     * This is what the user should see when they need to input a password.
     */
    public void pPrompt(){
        System.out.println("Please enter your password: ");
    }
}
