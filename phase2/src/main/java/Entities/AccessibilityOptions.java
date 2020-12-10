package main.java.Entities;

import main.java.Entities.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * <h1>AccessibilityOptions</h1>
 * This is the constructor for the AccessibilityOptions.
 * This should only be called by the AccessibilityOptions creator.
 * @version phase2
 * @author Yuteng Gao
 */

public class AccessibilityOptions extends Message {

    /**
     * The time when the request is sent.
     */
    private final LocalDateTime time_sent;
    /**
     * The request of the sender
     */
    private final String request;
    /**
     * the status of the request, initially "pending"
     */
    public String status;

    /**
     * Construct an instance of AccessibilityOptions.
     * Initialized with the request and username of the sender as well as the send time.
     * The request can only be one of: food, transportation and vision.
     *
     * @param request the request of the user
     * @param sender the user who send the request
     */

    public AccessibilityOptions(String request, String sender){
        this.request = request;
        time_sent = LocalDateTime.now(ZoneId.of("America/Toronto"));
        this.status = "pending";
    }

    /**
     * This method provide the sent time of request
     * @return a LocalDateTime object which represents the sent time of request
     */

    public LocalDateTime getTime_sent() {
        return time_sent;
    }

    /**
     * The getter for the request of sender
     * @return the request of sender
     */
    public String getRequest(){
        return this.request;
    }
}
