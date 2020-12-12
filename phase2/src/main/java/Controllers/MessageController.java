package main.java.Controllers;

import main.java.Entities.Message;
import main.java.Gateways.MessageGateway;
import main.java.UseCases.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Message Controller</h1>
 * This controller handles all messaging of the program while also connecting with the Message Gateway and
 * Message Creator use case.
 *
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
     * @param sending      the username of the sending user.
     * @param receiving    the username of the receiving user.
     * @param inputMessage the message to be entered into the system as a string.
     */
    public MessageController(String sending, String receiving, String inputMessage) {
        this.sender = sending;
        this.receiver = receiving;
        this.messageString = inputMessage;
        this.outbox = new MessageGateway().getOutbox();
        this.inbox = new MessageGateway().getInbox();
        this.allMessages = new MessageGateway();

        this.messageSystem = new MessageCreator(this.messageString, this.receiver, this.sender);
    }

    public MessageController() {
        this.outbox = new MessageGateway().getOutbox();
        this.inbox = new MessageGateway().getInbox();
        this.allMessages = new MessageGateway();
        this.messageSystem = new MessageCreator(this.messageString, this.receiver, this.sender);
    }

    /**
     * Sends message and updates inbox and outbox through Message Gateway.
     */
    public void sendMessage() {
        this.allMessages.addNewMessage(this.sender, this.receiver, messageSystem.getMessage());
        this.allMessages.setInbox();
        this.allMessages.setOutbox();
    }

    /**
     * Deletes message and updates inbox and outbox through Message Gateway.
     */
    public boolean deleteMessage() {
        boolean retVal = this.allMessages.removeMessage(this.sender, this.receiver, messageSystem.getMessage());
        this.allMessages.setOutbox();
        this.allMessages.setInbox();
        return retVal;
    }

    private ArrayList<String> allMessageHelper(HashMap<String, ArrayList<Message>> hm) {
        ArrayList<String> ret = new ArrayList<>();
        for (Map.Entry<String, ArrayList<Message>> mapElement : hm.entrySet()) {
            for (Message m : mapElement.getValue()) {
                ret.add(m.toString()+"\n");
                ret.add("\n");
            }
        }
        return ret;
    }

    /**
     * Gets all the Messages in the outbox, to be called by Presenter Class
     *
     * @return ArrayList with all the messages.
     */
    public ArrayList<String> getAllMessages() {
        HashMap<String, ArrayList<Message>> ret1;
        ret1 = this.outbox;
        return allMessageHelper(ret1);
    }

    /**
     * Gets all my messages send to me. Gets my inbox!
     *
     * @param me my username entered through presenter
     * @return ArrayList with all the messages for me.
     */
    public ArrayList<String> getMessageForMe(String me) {
        ArrayList<Message> messageList;
        ArrayList<String> ret = new ArrayList<>();
        messageList = this.inbox.get(me);
        for (Message m : messageList) {
            ret.add(m.getSender() + " : " + m.getTime_sent() + " : " + m.getContent());
        }
        System.out.println(ret);
        return ret;
    }

    /**
     * Gets all my messages that I have sent. Gets my Outbox!
     *
     * @param sender username for the sender
     * @return ArrayList with all the messages sent from me.
     */
    public ArrayList<String> getMessagesFromSender(String sender) {
        ArrayList<Message> messageList;
        ArrayList<String> ret = new ArrayList<>();
        messageList = this.outbox.get(sender);
        for (Message m : messageList) {
            ret.add(sender + " : " + m.getTime_sent() + " : " + m.getContent());
        }
        return ret;
    }

    /**
     * Gets all my messages that I have sent to a specific person. Gets my chat history!
     *
     * @param sender   username for the sender
     * @param receiver username for the receiver
     * @return ArrayList with all the messages sent from me to the receiver.
     */
    public ArrayList<String> getMessagesToReceiverFromSender(String sender, String receiver) {
        ArrayList<Message> messageList;
        ArrayList<String> ret = new ArrayList<>();
        messageList = this.outbox.get(sender);
        for (Message m : messageList) {
            if (m.getSender().equals(receiver)) {
                ret.add(sender + " : " + m.getTime_sent() + " : " + m.getContent());
            }
        }
        return ret;
    }

    /**
     * Saves all the messages.
     *
     */
    public void saveMessages() {
        this.allMessages.setOutbox();
        this.allMessages.setInbox();
    }


}


