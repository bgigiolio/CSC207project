package main.java.Gateways;

import java.io.*;

import main.java.UseCases.*;

/**
 * <h1>UserLoginInfo</h1>
 * Responsible for accessing database that stores user info and modifying their data.
 * @version phase2
 * @author Konstantinos Papaspyridis
 */
public class UserLoginInfo implements Serializable {

    /**
     * The LoginUserManager object that stores the user information.
     */
    private LoginUserManager loginUserManager;

    /**
     * Stores the path to the database file
     */
    private final String dbPath;

    public UserLoginInfo() {
        dbPath = "phase2/src/main/java/DB/UserLoginInfo.ser";
        getFileUserLoginInfo();
    }

    public boolean createUser(String username, String password, String role) {
        return loginUserManager.registerUser(username, password, role);
    }

    public boolean userExists(String username) {
        return loginUserManager.checkUsername(username);
    }

    public String login(String username, String password) {
        return loginUserManager.loginUser(username, password);
    }

    public boolean resetPassword(String username, String newPassword) {
        return loginUserManager.resetPassword(username, newPassword);
    }

    public String getUserRole(String username) {
        return loginUserManager.userRole(username);
    }

    /**
     * Clear contents of database file and save loginUserManager again to update
     */
    public void logout() {
        clearFileContentsUtil();
        setFileUserLoginInfo();
    }

    /**
     * Read user data from database.
     */
    private void getFileUserLoginInfo() {
        try {
            InputStream file = new FileInputStream(dbPath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            loginUserManager = ((LoginUserManager) input.readObject());
            input.close();
        } catch (EOFException e) { //database file is empty
            loginUserManager = new LoginUserManager();
        } catch (ClassNotFoundException | StreamCorruptedException e) {   //incorrect class format
            System.err.println("Corrupted file contents in user database. Clearing file...");
            clearFileContentsUtil();
            loginUserManager = new LoginUserManager();
        } catch (IOException e) {  //other IO exception
            System.err.println("Unknown error when reading from user database file.");
            e.printStackTrace();
            loginUserManager = new LoginUserManager();
        }
    }

    /**
     * Save loginUserManager to database.
     */
    private void setFileUserLoginInfo() {
        try {
            OutputStream file = new FileOutputStream(dbPath);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);

            output.writeObject(loginUserManager);
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