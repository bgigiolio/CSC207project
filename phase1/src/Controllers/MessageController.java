package Controllers;

import Entities.Message;
import Gateways.MessageGateway;
import UseCases.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Message Controller</h1>
 * This controller handles all messaging of the program while also connecting with the Message Gateway and
 * Message Creator use case.
 * @author Utkarsh Mali
 */

public class MessageController {
    /**
     * The username of sending user as a string.
     */
    protected String sender;
    /**
     * The username of receiving user as a string.
     */
    protected String receiver;
    /**
     * The message to be sent as a string.
     */
    protected String messageString;
    /**
     * An instantiated version of Message Creator as a the message system.
     */
    protected MessageCreator messageSystem;
    /**
     * An instantiated version of Message Gateway as a the message database access point.
     */
    protected MessageGateway allMessages;
    /**
     * Hashmap containing outbox of all messages
     */
    protected HashMap<String, ArrayList<Message>> outbox;
    /**
     * Hashmap containing inbox of all messages
     */
    protected HashMap<String, ArrayList<Message>> inbox;


    /**
     * Construct an Message Controller object when instantiated.
     * Initialized with a username of sender and receiver with input string message. If no input is sent then simply
     * instantiates the inboxes to be accessed.
     * loggedIn is initialized to be false and role is initialized to be "attendee".
     *
     * @param sending the username of the sending user.
     * @param receiving the username of the receiving user.
     * @param inputMessage the message to be entered into the system as a string.
     */
    public MessageController(String sending, String receiving, String inputMessage) throws IOException {
        this.sender = sending;
        this.receiver = receiving;
        this.messageString = inputMessage;
        this.outbox = new MessageGateway().getOutbox();
        this.inbox = new MessageGateway().getInbox();

        this.messageSystem = new MessageCreator(this.messageString, this.receiver, this.sender);
    }

    public MessageController() throws IOException {
        this.outbox = new MessageGateway().getOutbox();
        this.inbox = new MessageGateway().getInbox();

        this.messageSystem = new MessageCreator(this.messageString, this.receiver, this.sender);
    }

    public MessageController sendMessage() throws IOException {
        this.allMessages.addNewMessage(this.sender, this.receiver, messageSystem.getMessage());
        this.allMessages.setOutbox();
        this.allMessages.setInbox();
        return null;
    }

    public void deleteMessage() throws IOException {
        this.allMessages.removeMessage(this.sender, this.receiver, messageSystem.getMessage());
        this.allMessages.setOutbox();
        this.allMessages.setInbox();
    }

    private ArrayList<String> allMessageHelper(HashMap<String, ArrayList<Message>> hm){
        ArrayList<String> ret = new ArrayList<String>();
        for (Map.Entry<String, ArrayList<Message>> mapElement : hm.entrySet()) {
            String key = (String)mapElement.getKey();
            for(Message m: mapElement.getValue()){
                ret.add(key + " : " + m.getTime_sent() + " : " + m.getContent());
            }
        }
        return ret;
    }

    public ArrayList<String> getAllMessages(){
        HashMap<String, ArrayList<Message>> ret1;
        ret1 = this.outbox;
        return allMessageHelper(ret1);
    }

    public ArrayList<String> getMessageForMe(String me){
        ArrayList<Message> messageList = new ArrayList<Message>();
        ArrayList<String> ret = new ArrayList<String>();
        messageList = this.inbox.get(me);
        for(Message m: messageList){
            ret.add(me + " : " + m.getTime_sent() + " : " + m.getContent());
        }
        return ret;
    }


    public ArrayList<String> getMessagesFromSender(String sender){
        ArrayList<Message> messageList = new ArrayList<Message>();
        ArrayList<String> ret = new ArrayList<String>();
        messageList = this.outbox.get(sender);
        for(Message m: messageList){
            ret.add(sender + " : " + m.getTime_sent() + " : " + m.getContent());
        }
        return ret;
    }

    public ArrayList<String> getMessagesToReceiverFromSender(String sender, String receiver){
        ArrayList<Message> messageList = new ArrayList<Message>();
        ArrayList<String> ret = new ArrayList<String>();
        messageList = this.outbox.get(sender);
        for(Message m: messageList){
            if (m.getSender().equals(receiver)){
                ret.add(sender + " : " + m.getTime_sent() + " : " + m.getContent());
            }
        }
        return ret;
    }

    public void saveMessages() throws IOException {
        this.allMessages.setOutbox();
        this.allMessages.setInbox();
    }



}


