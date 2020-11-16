package Gateways;

import java.io.*;
import java.util.*;

public class FriendListGateway implements Serializable{

    private HashMap<String, ArrayList<String>> friendlistDatabase;

    public FriendListGateway(HashMap<String, ArrayList<String>> friendlistDatabase){
        this.friendlistDatabase = friendlistDatabase;
    }

    public FriendListGateway(){
        friendlistDatabase = new HashMap<>();
    }

    public HashMap<String, ArrayList<String>> getFriendlistDatabase(){
        return this.friendlistDatabase;
    }

    public void setFriendlistDatabase(HashMap<String, ArrayList<String>> friendlistDatabase){
        this.friendlistDatabase = friendlistDatabase;
    }


    public HashMap<String, ArrayList<String>> getFriendlistFile(String filePath) throws IOException {
        try{
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInputStream input = new ObjectInputStream(buffer);

            this.friendlistDatabase = (HashMap<String, ArrayList<String>>) input.readObject();
            input.close();
        }
        catch (FileNotFoundException|ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Existing friendlist database is returned");
        }
        return this.friendlistDatabase;
    }

    public void setFriendlistDatabase(String filePath) throws IOException{
        try {
            OutputStream file = new FileOutputStream(filePath);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutputStream output = new ObjectOutputStream(buffer);

            output.writeObject(this.friendlistDatabase);
            output.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }
}
