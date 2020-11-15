import java.util.Scanner;

//These UI classes are just thrown together to make running the program a bit easier.
// PLEASE dont be afraid to delete these and change them a bunch!!!
public class AttendeeMenu {
    private final String username;

    public AttendeeMenu(String username) {
        this.username = username;

    }

    public void printMenu() {
        System.out.println("Welcome " + this.username + "!");
        System.out.println("---General Actions---");
        System.out.println("[See Event Schedule]  [Sign Up For Event]  [Cancel Event]  " +
                "[Send Message]  [Review Messages]");
    }

    public void menuSelection() {
        Scanner uname = new Scanner(System.in);
        boolean answered = false;
        while (!answered) {
            System.out.println("What would you like to do?");
            String response = uname.nextLine();
            switch (response) {
                case "See Event Schedule":
                case "see event schedule":
                    System.out.println("Which building would you like to see the schedule of?");
                    if(seeEventSchedule(uname.nextLine())){
                        answered = true;
                    }else{
                        System.out.println("That is not a valid response. Please try again!");
                    }
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
    public boolean seeEventSchedule(String building){
        System.out.println("Doesn't work yet");
        return false;
    }
}
