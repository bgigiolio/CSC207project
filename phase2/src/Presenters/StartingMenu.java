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
        System.out.println("Account successfully logged into");
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

    public void usernameUsed() { System.out.println("Username already exists! Please enter another username: ");}

    public void newUserCreated() {System.out.println("Account successfully created");}

    public void usernameNotFoundPrompt() {
        System.out.println("That account doesn't exist.\n" +
                "[1] Enter a different account\n" +
                "[2] Get a new one");
    }

    public void wrongPasswordPrompt() {
        System.out.println("Incorrect password.\n" +
                "[1] Try again\n" +
                "[2] Forgot password?");
    }

    public void resetPasswordPrompt() {
        System.out.println("Please enter your new password: ");
    }

    public void wrongRole(String role) {
        System.out.println("The username you entered belongs to a(n) " + role + " account.\n" +
                "Please try logging in again.");
    }

    public void passwordResetSuccess() {
        System.out.println("You have successfully rest your password.\n" +
                "You may now use your new password to log in.");
    }
}
