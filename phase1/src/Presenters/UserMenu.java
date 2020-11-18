package Presenters;

import UseCases.BuildingManager;

import java.util.Scanner;

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
    public void printBuildingSchedule(BuildingManager building){
        System.out.println(building.toString());
    }
    public void signUpEvent(){
        System.out.println("Which event would you like to sign up for?");
    }

    public void menuSelection() {
        Scanner uname = new Scanner(System.in);
        boolean answered = false;
        while (!answered) {
            String response = uname.nextLine();
            switch (response) {
                case "See Entities.Event UseCases.Schedule":
                case "see event schedule": //TODO: implement function to fetch schedule
                    answered = true;
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
                case "Manage Friends List":
                case "manage friends list":
                    answered = true;
                    break;
                default:
                    System.out.println("Invalid response! Please Try again");
                    break;
            }
        }
    }
}
