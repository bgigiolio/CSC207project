package Controllers;

import Entities.Attendee;
import Entities.Organizer;
import Entities.Speaker;
import UseCases.*;import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class OrganizerMessageController {

    public Attendee organizer;

    public OrganizerMessageController(Attendee organizer){
        this.organizer = organizer;
    }

    public void toOneSpeaker(String oneSpeaker, String inputMessage) throws IOException {
        MessageController send = new MessageController(this.organizer.getUsername(), oneSpeaker,
                inputMessage);
        send.sendMessage();
    }

    public void toOneAttendee(String oneAttendee, String inputMessage) throws IOException {
        MessageController send = new MessageController(this.organizer.getUsername(),
                oneAttendee, inputMessage);
                send.sendMessage();
    }

    public void toFriends(String inputMessage) throws IOException {
        for(int i = 0; i < this.organizer.getNumOfFriends(); i ++){
            MessageController send = new MessageController(this.organizer.getUsername(),
                    this.organizer.getFriendList().get(i), inputMessage);
            send.sendMessage();
        }
    }

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


