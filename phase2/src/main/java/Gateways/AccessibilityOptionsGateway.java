package src.main.java.Gateways;

import main.java.Entities.AccessibilityOptions;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <h1>AccessibilityOptions Gateway</h1>
 * This is a gateway class that can store updated request list into database and add and remove
 * requests from the requirements list.
 * @author Yuteng Gao
 * @version Phase2
 */

public class AccessibilityOptionsGateway implements Serializable{

    /**
     * The request list as a Hashmap consisted of the Sting username and the Arraylist of AccessibilityOptions objects.
     */
    protected HashMap<String, ArrayList<main.java.Entities.AccessibilityOptions>> requestList;
    /**
     * The Sting represents the path of the requireList file.
     */
    private final String requireListPath;


    /**
     * The constructor of the AccessibilityOptionsGateway object. Connect the object with the database by file path.
     */
    public AccessibilityOptionsGateway(){
        this.requestList = new HashMap<>();
        this.requireListPath = "phase2/src/main/java/DB/RequestList.ser";
    }


    /**
     * This method add request to the request list.
     * @param sender the username of the sender
     * @param request the type of the request
     */
    public void addRequest(String sender, main.java.Entities.AccessibilityOptions request){
        this.requestList.put(sender, new ArrayList<AccessibilityOptions>());
        ArrayList<AccessibilityOptions> senderRequest = this.requestList.get(sender);
        senderRequest.add(request);
        this.requestList.replace(sender, senderRequest);
    }

    /**
     * This method remove request in the request list.
     * @param sender the username of the sender of the request
     * @param request the name of request that will be removed
     */
    public void removeRequest(String sender, main.java.Entities.AccessibilityOptions request){
        ArrayList<AccessibilityOptions> senderRequest = this.requestList.get(sender);
        senderRequest.remove(request);
        this.requestList.replace(sender, senderRequest);
    }

    /**
     * The getter for the request list.
     * @return the request list
     */
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

