package Controllers;

import Entities.Attendee;
import Entities.Organizer;
import Entities.Speaker;
import UseCases.*;import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * <h1>OrganizerMessageController</h1>
 * This Controller is responsible for sending Messages to various users.
 * This should only interact with the AttendeeMenuController.
 * @author Zachary Werle
 * @version Phase 1
 */
public class OrganizerMessageController {

    public Attendee organizer;

    /**
     * This constructor creates a new instance of OrganizerMessageController given the current Organizer.
     * @param organizer The Organizer object representing the current organizer using the program.
     */
    public OrganizerMessageController(Attendee organizer){
        this.organizer = organizer;
    }

    /**
     * This method sends Messages to one Speaker.
     * @param oneSpeaker The Speaker receiving the Message.
     * @param inputMessage The Message the speaker is to receive.
     */
    public void toOneSpeaker(String oneSpeaker, String inputMessage) throws IOException {
        MessageController send = new MessageController(this.organizer.getUsername(), oneSpeaker,
                inputMessage);
        send.sendMessage();
    }

    /**
     * This method sends Messages to one Attendee.
     * @param oneAttendee The Attendee receiving the Message.
     * @param inputMessage The Message the Attendee is to receive.
     */
    public void toOneAttendee(String oneAttendee, String inputMessage) throws IOException {
        MessageController send = new MessageController(this.organizer.getUsername(),
                oneAttendee, inputMessage);
                send.sendMessage();
    }

    /**
     * This method sends Messages to Attendees' Friends.
     * @param inputMessage The Message the Friends are receiving.
     */
    public void toFriends(String inputMessage) throws IOException {
        for(int i = 0; i < this.organizer.getNumOfFriends(); i ++){
            MessageController send = new MessageController(this.organizer.getUsername(),
                    this.organizer.getFriendList().get(i), inputMessage);
            send.sendMessage();
        }
    }

    /**
     * This method sends Messages to all Speakers.
     * @param inputMessage The Message the Speakers are to receive.
     * @param manager The LoginUserManager that will be used to send the Messages to the Speakers.
     */
    public void toAllSpeaker(String inputMessage, LoginUserManager manager) throws IOException {
        HashMap<String, Attendee> users = manager.getCredentialsMap();
        for(String username : users.keySet()){     //accessing entities through a controller - no good
            if(manager.userRole(username).equals("speaker")){
                MessageController send = new MessageController(this.organizer.getUsername(), username,
                        inputMessage);
                send.sendMessage();
            }
        }
    }

    /**
     * This method sends Messages to all Attendees.
     * @param inputMessage The Message the Attendees are to receive.
     * @param manager The LoginUserManager that will be used to send the Messages to the Attendees.
     */
    public void toAllAttendee(String inputMessage, LoginUserManager manager) throws IOException {
        HashMap<String, Attendee> users = manager.getCredentialsMap();
        for (String username : users.keySet()){ //same here!!
            if(manager.userRole(username).equals("attendee")){
                MessageController send = new MessageController(this.organizer.getUsername(), username,
                        inputMessage);
                send.sendMessage();
            }
        }
    }
}


