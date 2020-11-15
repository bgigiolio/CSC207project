package UseCases;

import Entities.*;

public class MessageCreator {
    protected String receiver;
    protected Message message;

    public MessageCreator(String toBeMessage, String receiver, String senderUserid){
        message = new Message(toBeMessage, senderUserid);
    }

    public java.time.LocalDateTime getTimeSent(){
        return message.getTime_sent();
    }

    public Message getMessage() {
        return message;
    }
}
