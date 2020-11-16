package Controllers;

import Entities.Message;
import Gateways.MessageGateway;
import UseCases.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MessageController {

    protected String sender;
    protected String receiver;
    protected String messageString;
    protected MessageCreator messageSystem;
    protected MessageGateway allMessages;
    protected String outboxFilePath;
    protected String inboxFilePath;
    protected HashMap<String, ArrayList<Message>> outbox;
    protected HashMap<String, ArrayList<Message>> inbox;

    public MessageController(String sending, String receiving, String inputMessage, String outboxFilePath, String inboxFilePath) {
        this.sender = sending;
        this.receiver = receiving;
        this.messageString = inputMessage;
        this.outboxFilePath = outboxFilePath;
        this.inboxFilePath = inboxFilePath;
        this.outbox = new MessageGateway(outboxFilePath).getOutbox();
        this.inbox = new MessageGateway(inboxFilePath).getOutbox();

        this.messageSystem = new MessageCreator(this.messageString, this.receiver, this.sender);
    }

    public MessageController sendMessage() throws IOException {
        this.allMessages.addNewMessage(this.sender, this.receiver, messageSystem.getMessage());
        this.allMessages.setOutbox(this.outboxFilePath);
        this.allMessages.setInbox(this.inboxFilePath);
        return null;
    }

    public void deleteMessage() throws IOException {
        this.allMessages.removeMessage(this.sender, this.receiver, messageSystem.getMessage());
        this.allMessages.setOutbox(this.outboxFilePath);
        this.allMessages.setInbox(this.inboxFilePath);
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
        this.allMessages.setOutbox(this.outboxFilePath);
        this.allMessages.setInbox(this.inboxFilePath);
    }



}


