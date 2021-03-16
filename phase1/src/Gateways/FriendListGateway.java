package Gateways;

import java.io.*;
import java.util.*;

/**
 * <h1>Friend List Gateway</h1>
 * FriendlistGateway is a gateway class that interacts with outside file to store friendlists of every user.
 * @author Qi Zheng
 * @version Phase1
 */


public class FriendListGateway implements Serializable{

    private HashMap<String, ArrayList<String>> friendlist;
    private String filePath;

    /**
     * Takes no input.
     * Instantiates an instance of FriendlistGateway by assigning an empty hashmap to friendlist and sets a default\\
     * filepath.
     */
    public FriendListGateway(){
        friendlist = new HashMap<>();
        this.filePath = "phase1\\src\\DB\\Friendlist.ser";
    }

    /**
     * Returns the hashmap that is currently assigned to friendlist in the program.
     * @return this.friendlist
     */
    public HashMap<String, ArrayList<String>> getFriendlist(){
        return this.friendlist;
    }

    /**
     * Takes a friend list in type hashmap as a parameter
     * Assigns the input to the class attribute friendlist.
     * @param friendlist is a hashmap whose keys are users' usernames and the values are arraylists of their friends
     */
    public void setFriendlist(HashMap<String, ArrayList<String>> friendlist){
        this.friendlist = friendlist;
    }

    /**
     * Returns the filepath string that is assigned to the attribute filePath
     * @return this.filePath
     */

    public String getFilePath(){
        return this.filePath;
    }

    /**
     * Takes an input filePath.
     * Assigns the input to the class attribute named filePath
     * @param filePath is a string that tells the location of the file.
     */
    public void setFilePath(String filePath){
        this.filePath = filePath;
    }

    /**
     * Retrieves and returns the hashmap that is stored in the file located at filePath, outside the program.
     * Assigns the retrieved hashmap to the class attribute friendlist.
     * @return this.friendlist
     * @throws IOException that checks if there exists a file in particular location and if there exists the specified\\
     * class in the file to read.
     */
    public HashMap<String, ArrayList<String>> getFriendlistFile() throws IOException {
        try{
            InputStream file = new FileInputStream(this.filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInputStream input = new ObjectInputStream(buffer);

            this.friendlist = (HashMap<String, ArrayList<String>>) input.readObject();
            input.close();
        }
        catch (FileNotFoundException|ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Existing friendlist database is returned");
        }
        return this.friendlist;
    }

    /**
     * Writes the hashmap that is assigned to friendlist to the file located at filePath.
     * @throws FileNotFoundException checks if there exists a file at filePath.
     */
    public void setFriendlistDatabase() throws IOException {
        try {
            OutputStream file = new FileOutputStream(this.filePath);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutputStream output = new ObjectOutputStream(buffer);

            output.writeObject(this.friendlist);
            output.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }
}
