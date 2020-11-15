package Controllers;

import UseCases.*;

public class MessageController {
    // The bridge between Entities.Message Creator and Entities.Attendee's Entities.Message List
    public MessageController(Attendee person, Message inputMessage) {
        person.messages.add(inputMessage);
    }
}
