import java.util.Scanner;

//These UI classes are just thrown together to make running the program a bit easier.
// PLEASE dont be afraid to delete these and change them a bunch!!!
public class OrganizerMenu extends AttendeeMenu{
    private String username;
    public OrganizerMenu(String username) {
        super(username);
        this.username = username;


    }

    public void printMenu() {
        System.out.println("Welcome " + this.username + "!");
        System.out.println("What would you like to do?");
        System.out.println("---Organizer Specific Actions---");
        System.out.println("[Create Speaker]  [Add Room]  [Schedule Speaker]  [Manage Event]  ");
        System.out.println("---General Actions---");
        System.out.println("[See Event Schedule]  [Sign Up For Event]  [Cancel Event]  " +
                "[Send Message]  [Review Messages]");
    }

    public void menuSelection() {
        Scanner uname = new Scanner(System.in);
        boolean answered = false;
        while (!answered) {
            String response = uname.nextLine();
            switch (response) {
                case "Create Speaker":
                case "create speaker":
                    answered = true;
                    break;
                case "Add Room":
                case "add room":
                    answered = true;
                    break;
                case "Schedule Speaker":
                case "schedule speaker":
                    answered = true;
                    break;
                case "Manage Event":
                case "manage event":
                    answered = true;
                    break;
                case "See Event Schedule":
                case "see event schedule":
                    answered = true;
                    break;
                case "Sign Up For Event":
                case "sign up for event":
                    answered = true;
                    break;
                case "Cancel Event":
                case "cancel event":
                    answered = true;
                    break;
                case "Send Message":
                case "send message":
                    answered = true;
                    break;
                case "Review Messages":
                case "review messages":
                    answered = true;
                    break;
                default:
                    System.out.println("Invalid response! Please Try again");
                    break;
            }
        }
    }
    public boolean manageEventMenu(){
        System.out.println("doesn't work yet");
        return false;
    }
}
