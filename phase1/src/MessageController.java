public class MessageController {
    // The bridge between Message Creator and Attendee's Message List
    public MessageController(Attendee person, Message inputMessage) {
        person.receiveMessage(inputMessage);
    }
}
