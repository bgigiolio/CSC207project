package src.main.java.Controllers;

import src.main.java.Gateways.AccessibilityOptionsGateway;

import java.util.ArrayList;
import java.util.HashMap;

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
