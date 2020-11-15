package Presenters;

import java.util.Scanner;
import Controllers.*;

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
        System.out.println("[See Entities.Event UseCases.Schedule]  [Sign Up For Entities.Event]  [Cancel Entities.Event]  " +
                "[Send Entities.Message]  [Review Messages]");
    }

    public void menuSelection() {
        Scanner uname = new Scanner(System.in);
        boolean answered = false;
        while (!answered) {
            System.out.println("What would you like to do?");
            String response = uname.nextLine();
            switch (response) {
                case "See Entities.Event UseCases.Schedule":
                case "see event schedule":
                    System.out.println("Which building would you like to see the schedule of?");
                    if(seeEventSchedule(uname.nextLine())){
                        answered = true;
                    }else{
                        System.out.println("That is not a valid response. Please try again!");
                    }
                    break;
                case "Sign Up For Entities.Event":
                case "sign up for event":
                    answered = true;
                    break;
                case "Cancel Entities.Event":
                case "cancel event":
                    answered = true;
                    break;
                case "Send Entities.Message":
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
