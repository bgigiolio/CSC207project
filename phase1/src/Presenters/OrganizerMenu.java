package Presenters;
import Controllers.AttendeeMenuController;
import Controllers.BuildingController;
import Controllers.NewUserController;
import Entities.Event;
import Entities.Speaker;
import Gateways.EventGateway;
import UseCases.BuildingManager;
import UseCases.LoginUserManager;

import java.io.IOException;
import java.util.Scanner;

//These UI classes are just thrown together to make running the program a bit easier.
// PLEASE dont be afraid to delete these and change them a bunch!!!
public class OrganizerMenu extends AttendeeMenuController {
    private String username;

    public OrganizerMenu(String username, String role, BuildingManager building, LoginUserManager userManager) {
        super(username, role, building, userManager);
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

    public void menuSelection() throws IOException, ClassNotFoundException {
        Scanner uname = new Scanner(System.in);
        boolean answered = false;
        while (!answered) {
            String response = uname.nextLine();
            switch (response) {
                case "Create Entities.Speaker":
                case "create speaker":
                    System.out.println("Enter Speaker name:");
                    Scanner sname = new Scanner(System.in);
                    NewUserController menu = new NewUserController();
                    if (menu.logReg(sname.nextLine(), "password", "Speaker")) {
                        System.out.println("Speaker created");
                    }else{
                        System.out.println("Something went wrong, try again!");
                        menuSelection();
                    }
                    answered = true;
                    menuSelection();
                case "Add Room":
                case "add room":
                    System.out.println("Enter building name:");
                    Scanner buildingname = new Scanner(System.in);
                    System.out.println("Enter room name:");
                    Scanner roomname = new Scanner(System.in);
                    System.out.println("Enter start hour for schedule:");
                    Scanner starthour = new Scanner(System.in);
                    System.out.println("Enter end hour for schedule:");
                    Scanner endhour = new Scanner(System.in);
                    BuildingManager building = new BuildingManager(buildingname.nextLine());
                    System.out.println("Do you have an existing schedule? Enter [Y] for yes or [N] for no:");
                    boolean answered2 = false;
                    Scanner response1 = new Scanner(System.in);
                    while (!answered2){
                        String res1 = response1.nextLine();
                        if (res1.equalsIgnoreCase("N") || res1.equalsIgnoreCase("[N]")) {
                            building.addRoom1(roomname.nextLine(), Integer.parseInt(starthour.nextLine()), Integer.parseInt(endhour.nextLine()));
                            answered2 = true;
                        }
                        else if (response.equalsIgnoreCase("Y") || response.equalsIgnoreCase("[Y]")) {
                            //TODO: unsure about how to handle this case without directly referencing an Event entity
                        }
                    }
                    System.out.println(building.toString());
                    answered = true;
                    menuSelection();
                case "UseCases.Schedule Entities.Speaker":
                case "schedule speaker":
                    System.out.println("Enter speaker username:");
                    Scanner speakername = new Scanner(System.in);
                    System.out.println("Enter building name:");
                    Scanner bname = new Scanner(System.in);
                    System.out.println("Enter existing Event name:");
                    Scanner eventname = new Scanner(System.in);
                    BuildingManager building1 = new BuildingManager(bname.nextLine());
                    Event event = building1.getEvent(eventname.nextLine());
                    //TODO: Convert to Talk and then add Speaker
                    answered = true;
                    menuSelection();
                case "Manage Entities.Event":
                case "manage event":
                    EventGateway eventmanage = new EventGateway();
                    System.out.println(eventmanage.getEvents().toString());
                    System.out.println("Enter event to manage:");
                    answered = true;
                    menuSelection();
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
