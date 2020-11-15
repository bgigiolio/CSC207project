package Gateways;

import java.io.*;
import java.util.*;

public class MessageGateway implements Serializable{
    private HashMap<String, ArrayList<ArrayList<Message>>> messageDatabase;

    public MessageGateway(HashMap<String, ArrayList<ArrayList<Message>>> messagedatabase){
        this.messageDatabase = messagedatabase;
    }

    public MessageGateway(){
        this.messageDatabase = new HashMap<>();
    }

    public HashMap<String, ArrayList<ArrayList<Message>>> getMessageDatabase(){
        return this.messageDatabase;
    }
    public void setMessageDatabase(HashMap<String, ArrayList<ArrayList<Message>>> messageDatabase){
        this.messageDatabase = messageDatabase;
    }


    public HashMap<String, ArrayList<ArrayList<Message>>> getMessageFile(String filePath) throws IOException{
        try{
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            this.messageDatabase = (HashMap<String, ArrayList<ArrayList<Message>>>) input.readObject();
            input.close();
        }
        catch (FileNotFoundException|ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Existing version of messagedatabase is returned");
        }
        return messageDatabase;
    }

    public void setMessageDatabase(String filePath) throws IOException{
        try{
            OutputStream file = new FileOutputStream(filePath);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);

            output.writeObject(this.messageDatabase);
            output.close();

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
