package main.java.UseCases;

import main.java.Entities.AccessibilityOptions;

import java.time.LocalDateTime;

public class AccessibilityOptionsCreator {

    protected AccessibilityOptions accessibilityOption;

    public AccessibilityOptionsCreator(String request, String sender){
        if (request.equals("food") || request.equals("transportation") || request.equals("vision")){
            accessibilityOption = new AccessibilityOptions(request, sender);
        }
        else {
            System.err.println("This is not included in our accessibility options.");
        }
    }

    public LocalDateTime getTimeSent(){
        return accessibilityOption.getTime_sent();
    }

    public AccessibilityOptions getRequest() {
        return accessibilityOption;
    }

}
