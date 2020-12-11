package main.java.Controllers;

//import main.java.Entities.Organizer;
import src.main.java.Gateways.AccessibilityOptionsGateway;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This AccessibilityOptions controller is responsible for sending and deleting request by users, and
 * managing these requests by organizers.
 * @author Yuteng Gao
 * @version phase2
 */

public class AccessibilityOptionsController {
    /**
     * The username of the sender
     */
    private final String sender;
    /**
     * the request of the sender
     */
    private final String request;
    /**
     * the request list as an AccessibilityOptionsGateway object
     */
    private final AccessibilityOptionsGateway requestList;
    /**
     * creator of the new request
     */
    private final main.java.UseCases.AccessibilityOptionsCreator requestCreator;

    /**
     * the constructor of the Accessibility Options controller
     * @param sender the username of the sender
     * @param request the accessibility request of the sender
     */
    public AccessibilityOptionsController(String sender, String request){
        this.sender = sender;
        this.request = request;
        this.requestList = new AccessibilityOptionsGateway();
        this.requestCreator = new main.java.UseCases.AccessibilityOptionsCreator(this.request, this.sender);
    }


    /**
     * This method allow the organizers to address the request in request list
     * @param sender the username of the request sender
     * @param request the content of the request
     */
    public void addressRequest(String sender, String request){
        HashMap<String, ArrayList<main.java.Entities.AccessibilityOptions>> list = this.requestList.getRequestList();
        if (list.containsKey(sender)){
            ArrayList<main.java.Entities.AccessibilityOptions> senderRequest = list.get(sender);
            for (main.java.Entities.AccessibilityOptions accessibilityOptions : senderRequest) {
                if (accessibilityOptions.getRequest().equals(request)) {
                    accessibilityOptions.status = "addressed";
                }
            }
        }
    }


    /**
     * This method allow the organizers to reject the request in request list
     * @param sender the username of the request sender
     * @param request the content of the request
     */
    public void rejectRequest(String sender, String request){
        HashMap<String, ArrayList<main.java.Entities.AccessibilityOptions>> list = this.requestList.getRequestList();
        if (list.containsKey(sender)){
            ArrayList<main.java.Entities.AccessibilityOptions> senderRequest = list.get(sender);
            for (main.java.Entities.AccessibilityOptions accessibilityOptions : senderRequest) {
                if (accessibilityOptions.getRequest().equals(request)) {
                    accessibilityOptions.status = "rejected";
                }
            }

        }
    }


    /**
     * Print all accessibility requests to user
     * @return the complete request list
     */
    public ArrayList<String> getAllRequest(){
        AccessibilityOptionsGateway obj = new AccessibilityOptionsGateway();
        ArrayList<String> allRequest = new ArrayList<>();
        HashMap<String, ArrayList<main.java.Entities.AccessibilityOptions>> list = obj.getRequestList();
        for(String key : list.keySet()){
            String senderName = key;
            ArrayList<main.java.Entities.AccessibilityOptions> senderRequest = list.get(key);
            for(int i = 0; i<senderRequest.size(); i++){
                allRequest.add(key + " : " + senderRequest.get(i).getRequest() + " send at " +
                        senderRequest.get(i).getTime_sent().format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")));
            }
        }
        return allRequest;
    }


    /**
     * send the request from the sender to request list
     */
    public void sendRequest(){
        this.requestList.addRequest(this.sender, this.requestCreator.getRequest());
    }

    /**
     * delete the request from the request list
     */
    public void deleteRequest(){
        this.requestList.removeRequest(this.sender, this.requestCreator.getRequest());
    }

}


