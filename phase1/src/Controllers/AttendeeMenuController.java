package Controllers;

import java.io.IOException;
import java.util.Scanner;
import Presenters.*;
import UseCases.BuildingManager;

//These UI classes are just thrown together to make running the program a bit easier.
// PLEASE dont be afraid to delete these and change them a bunch!!!
public class AttendeeMenuController {
    private final String username;
    private final UserMenu menu;
    public AttendeeMenuController(String username) {
        this.username = username;
        this.menu = new UserMenu(this.username);

    }

    public void menuSelection() throws IOException {
        Scanner uname = new Scanner(System.in);
        boolean answered = false;
        this.menu.optionsAttendee();
        while (!answered) {
            this.menu.awaitResponse();
            String response = uname.nextLine();
            switch (response) {
                case "See Event Schedule":
                case "see event schedule":
                    System.out.println("Which building would you like to see the schedule of?");
                    if(seeEventSchedule(uname.nextLine())){
                        answered = true;
                    }else{
                        this.menu.invalidResponse();
                    }
                    break;
                case "Sign Up For Event":
                case "sign up for event":
                    System.out.println("Which event would you like to sign up for?");
 //                   if(){
//                        answered = true;
//                    }else{
//                        this.menu.invalidResponse();
//                    }
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
                case "Manage Friends List":
                case "manage friends list":
                    answered = true;
                    break;
                default:
                    this.menu.invalidResponse();
                    break;
            }
        }
    }
    public boolean seeEventSchedule(String building){
        return false;
    }
}
