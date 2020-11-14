import java.util.List;

public class OrganizerMessageController {

    public Organizer organizer;

    public OrganizerMessageController(Organizer organizer){
        this.organizer = organizer;
    }

    public void toOneSpeaker(Speaker oneSpeaker, String inputMessage){
        MessageCreator message = new MessageCreator(inputMessage, oneSpeaker, this.organizer.userid);
    }

    public void toOneAttendee(Attendee oneAttendee, String inputMessage){
        MessageCreator message = new MessageCreator(inputMessage, oneAttendee, this.organizer.userid);
    }

    public void toFriends(String inputMessage){
        for(int i = 0; i < this.organizer.friendlist.size(); i ++){
            MessageCreator message = new MessageCreator(inputMessage, this.organizer.friendlist.get(i),
                    this.organizer.userid);
        }
    }

    public void toAllSpeaker(String inputMessage){
        for (int i = 0; i < Attendee.user.size(); i++){
            if(Attendee.user.get(i).role.equals("speaker")){
                MessageCreator message = new MessageCreator(inputMessage, Attendee.user.get(i), this.organizer.userid);
            }
        }
    }

    public void toAllAttendee(String inputMessage){
        for (int i = 0; i < Attendee.user.size(); i++){
            if(Attendee.user.get(i).role.equals("attendee")){
                MessageCreator message = new MessageCreator(inputMessage, Attendee.user.get(i), this.organizer.userid);
            }
        }
    };
}


