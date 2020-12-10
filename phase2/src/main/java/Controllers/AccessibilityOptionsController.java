package src.main.java.Controllers;

import src.main.java.Gateways.AccessibilityOptionsGateway;

import java.util.ArrayList;
import java.util.HashMap;

public class AccessibilityOptionsController {

    private final String sender;
    private final String request;
    private final AccessibilityOptionsGateway requestList;
    private final main.java.UseCases.AccessibilityOptionsCreator requestCreator;

    public AccessibilityOptionsController(String sender, String request){
        this.sender = sender;
        this.request = request;
        this.requestList = new AccessibilityOptionsGateway();
        this.requestCreator = new main.java.UseCases.AccessibilityOptionsCreator(this.request, this.sender);

    }
    public void sendRequest(){
        this.requestList.addRequest(this.sender, this.requestCreator.getRequest());

    }

    public void deleteRequest(){
        this.requestList.removeRequest(this.sender, this.requestCreator.getRequest());
    }
}
