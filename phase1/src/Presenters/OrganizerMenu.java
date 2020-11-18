package Presenters;
import Controllers.AttendeeMenuController;
import Controllers.BuildingController;
import Controllers.NewUserController;
import Entities.Speaker;
import UseCases.BuildingManager;

import java.io.IOException;
import java.util.Scanner;

//These UI classes are just thrown together to make running the program a bit easier.
// PLEASE dont be afraid to delete these and change them a bunch!!!
public class OrganizerMenu extends AttendeeMenuController {
    private String username;

    public OrganizerMenu(String username) {
        super(username);
        this.username = username;
    }

    public void printMenu() {
        System.out.println("Welcome " + this.username + "!");
        System.out.println("What would you like to do?");
        System.out.println("---Entities.Organizer Specific Actions---");
        System.out.println("[Create Entities.Speaker]  [Add Room]  [UseCases.Schedule Entities.Speaker]  [Manage Entities.Event]  ");
        System.out.println("---General Actions---");
        System.out.println("[See Entities.Event UseCases.Schedule]  [Sign Up For Entities.Event]  [Cancel Entities.Event]  " +
                "[Send Entities.Message]  [Review Messages]");
    }

    public void menuSelection() throws IOException {
        Scanner uname = new Scanner(System.in);
        boolean answered = false;
        while (!answered) {
            String response = uname.nextLine();
            switch (response) {
                case "Create Entities.Speaker":
                case "create speaker":
                    Scanner sname = new Scanner(System.in);
                    NewUserController menu = new NewUserController();
                    if (menu.logReg(sname.nextLine(), "password", "Speaker")) {
                        System.out.println("Speaker created");
                    }else{
                        System.out.println("Something went wrong, try again!");
                        menuSelection();
                    }
                    answered = true;
                case "Add Room":
                case "add room":
                    Scanner roomname = new Scanner(System.in);
                    Scanner starthour = new Scanner(System.in);
                    Scanner endhour = new Scanner(System.in);
                    //BuildingController building = new BuildingController();
                    answered = true;
                    break;
                case "UseCases.Schedule Entities.Speaker":
                case "schedule speaker":

                    answered = true;
                    break;
                case "Manage Entities.Event":
                case "manage event":
                    answered = true;
                    break;
                case "See Entities.Event UseCases.Schedule":
                case "see event schedule":
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
