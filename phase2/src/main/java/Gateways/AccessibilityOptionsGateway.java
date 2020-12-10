package src.main.java.Gateways;

import main.java.Entities.AccessibilityOptions;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AccessibilityOptionsGateway implements Serializable{

    protected HashMap<String, ArrayList<main.java.Entities.AccessibilityOptions>> requestList;
    private final String requireListPath;

    public AccessibilityOptionsGateway(){
        this.requestList = new HashMap<>();
        this.requireListPath = "phase2\\src\\main\\java\\DB\\RequestList.ser";
    }

    public void addRequest(String sender, main.java.Entities.AccessibilityOptions request){
        this.requestList.put(sender, new ArrayList<AccessibilityOptions>());
        ArrayList<AccessibilityOptions> senderRequest = this.requestList.get(sender);
        senderRequest.add(request);
        this.requestList.replace(sender, senderRequest);
    }

    public void removeRequest(String sender, main.java.Entities.AccessibilityOptions request){
        ArrayList<AccessibilityOptions> senderRequest = this.requestList.get(sender);
        senderRequest.remove(request);
        this.requestList.replace(sender, senderRequest);
    }

    public HashMap<String, ArrayList<AccessibilityOptions>> getRequestList(){
        File list = new File(this.requireListPath);
        if(list.length() == 0){
            return new HashMap<>();
        }
        try{
            FileInputStream fileInputStream = new FileInputStream(this.requireListPath);
        } catch (FileNotFoundException e) {
            System.out.println("File path invalid.");
        }
        return this.requestList;
    }


}

