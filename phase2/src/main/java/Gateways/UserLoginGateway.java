package main.java.Gateways;

import java.io.*;

import main.java.UseCases.UserManager;

/**
 * <h1>UserLoginInfo</h1>
 * Responsible for accessing database that stores user info
 * @version phase2
 * @author Konstantinos Papaspyridis
 */
public class UserLoginGateway {

    /**
     * Stores the path to the database file
     */
    private final String dbPath = "phase2/src/main/java/DB/UserLoginInfo.ser";

    /**
     * Clear contents of database file and write LoginUserManager to database
     * @param ob The LoginUserManager object to store
     */
    public void saveUserLoginInfo(UserManager ob) {
        clearFileContentsUtil();
        saveUtil(ob);
    }

    /**
     * Read user data from database.
     * @return A LoginUserManager object
     */
    public UserManager getStoredUserData() {
        UserManager loginUserManager;

        try {
            InputStream file = new FileInputStream(dbPath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            loginUserManager = (UserManager) input.readObject();
            input.close();
        } catch (EOFException e) { //database file is empty
            loginUserManager = new UserManager();
        } catch (ClassNotFoundException | StreamCorruptedException e) {   //incorrect class format
            System.err.println("Corrupted file contents in user database. Clearing file...");
            clearFileContentsUtil();
            loginUserManager = new UserManager();
        } catch (IOException e) {  //other IO exception
            System.err.println("Unknown error when reading from user database file.");
            e.printStackTrace();
            loginUserManager = new UserManager();
        }

        return loginUserManager;
    }

    /**
     * Save loginUserManager to database.
     */
    private void saveUtil(UserManager ob) {
        try {
            OutputStream file = new FileOutputStream(dbPath);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);

            output.writeObject(ob);
            output.close();
        } catch (IOException e) {
            System.err.println("Could not save user data to database.");
            e.printStackTrace();
        }
    }

    /**
     * Utility method to clear file contents if file contains corrupt data
     */
    private void clearFileContentsUtil() {
        try {
            PrintWriter writer = new PrintWriter(dbPath);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("Unexpected error when accessing the user database file.");
            e.printStackTrace();
        }
    }
}