package Controllers;

import UseCases.*;;

public class OrganizerMessageController {

    public Organizer organizer;

    public OrganizerMessageController(Organizer organizer){
        this.organizer = organizer;
    }

    public void toOneSpeaker(Speaker oneSpeaker, String inputMessage){
        MessageCreator message = new MessageCreator(inputMessage, oneSpeaker, this.organizer.getUsername());
    }

    public void toOneAttendee(Attendee oneAttendee, String inputMessage){
        MessageCreator message = new MessageCreator(inputMessage, oneAttendee, this.organizer.getUsername());
    }

    public void toFriends(String inputMessage){
        for(int i = 0; i < this.organizer.getNumOfFriends(); i ++){
            MessageCreator message = new MessageCreator(inputMessage, this.organizer.friendList.get(i),
                    this.organizer.username);
        }
    }

    public void toAllSpeaker(String inputMessage){
        for (int i = 0; i < Attendee.user.size(); i++){ //accessing entities through a controller - no good
            if(Attendee.user.get(i).role.equals("speaker")){
                MessageCreator message = new MessageCreator(inputMessage, Attendee.user.get(i), this.organizer.username);
            }
        }
    }

    public void toAllAttendee(String inputMessage){
        for (int i = 0; i < Attendee.user.size(); i++){ //same here!!
            if(Attendee.user.get(i).role.equals("attendee")){
                MessageCreator message = new MessageCreator(inputMessage, Attendee.user.get(i), this.organizer.username);
            }
        }
    }
}


