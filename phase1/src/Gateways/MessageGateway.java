package Gateways;

import Entities.Message;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MessageGateway implements Serializable{
    private HashMap<String, ArrayList<Message>> inbox; //received messages

    private HashMap<String, ArrayList<Message>> outbox; //sent messages

    public MessageGateway(HashMap<String, ArrayList<Message>> inbox, HashMap<String, ArrayList<Message>> outbox){
        this.inbox = inbox;
        this.outbox = outbox;
    }

    public MessageGateway(HashMap<String, ArrayList<Message>> box, String boxType){
        if (boxType.equals("inbox") | boxType.equals("Inbox") | boxType.equals("INBOX")){
            this.inbox = box;
            this.outbox = new HashMap<>();
        }
        else{
            this.inbox = new HashMap<>();
            this.outbox = box;
        }
    }

    public MessageGateway(){
        this.inbox = new HashMap<>();
        this.outbox = new HashMap<>();
    }

    public HashMap<String, ArrayList<Message>> getInbox(){
        return this.inbox;
    }
    public HashMap<String, ArrayList<Message>> getOutbox(){
        return this.outbox;
    }

    public void setInbox(HashMap<String, ArrayList<Message>> inbox){
        this.inbox = inbox;
    }

    public void setOutbox(HashMap<String, ArrayList<Message>> outbox){
        this.outbox = outbox;
    }

    public void addNewMessage(String sender, String receiver, Message message){
        if (!this.inbox.containsKey(receiver)){
            this.inbox.put(receiver, new ArrayList<>());
        }
        ArrayList<Message> receiverInbox = this.inbox.get(receiver);
        receiverInbox.add(message);
        this.inbox.replace(receiver, receiverInbox);

        if (!this.outbox.containsKey(sender)){
            this.outbox.put(sender, new ArrayList<>());
        }
        ArrayList<Message> senderOutbox = this.outbox.get(sender);
        senderOutbox.add(message);
        this.outbox.replace(sender, senderOutbox);
    }

    public void removeMessage(String sender, String receiver, Message message){
        if (this.outbox.containsKey(sender) && this.outbox.get(sender).contains(message)
        && this.inbox.containsKey(receiver) && this.inbox.get(receiver).contains(message)){
            ArrayList<Message> senderOutbox = this.outbox.get(sender);
            senderOutbox.remove(message);
            this.outbox.replace(sender, senderOutbox);

            ArrayList<Message> receiverInbox = this.inbox.get(receiver);
            receiverInbox.remove(message);
            this.inbox.replace(receiver, receiverInbox);
        }
    }



    public HashMap<String, ArrayList<Message>> getInboxFile(String filePath) throws IOException{
        try{
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            this.inbox = (HashMap<String, ArrayList<Message>>) input.readObject();
            input.close();
        }
        catch (FileNotFoundException|ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Existing version of inbox is returned");
        }
        return this.inbox;
    }
    public HashMap<String, ArrayList<Message>> getOutboxFile(String filePath) throws IOException{
        try{
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            this.outbox = (HashMap<String, ArrayList<Message>>) input.readObject();
            input.close();
        }
        catch (FileNotFoundException|ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Existing version of outbox is returned");
        }
        return this.outbox;
    }


    public void setInbox(String filePath) throws IOException{
        try{
            OutputStream file = new FileOutputStream(filePath);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);

            output.writeObject(this.inbox);
            output.close();

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void setInbox(String filePath, HashMap<String, ArrayList<Message>> inbox) throws IOException{
        try{
            OutputStream file = new FileOutputStream(filePath);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);

            output.writeObject(inbox);
            output.close();

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }



    public void setOutbox(String filePath) throws IOException{
        try{
            OutputStream file = new FileOutputStream(filePath);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);

            output.writeObject(this.outbox);
            output.close();

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public void setOutbox(String filePath, HashMap<String, ArrayList<Message>> outbox) throws IOException{
        try{
            OutputStream file = new FileOutputStream(filePath);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);

            output.writeObject(outbox);
            output.close();

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
