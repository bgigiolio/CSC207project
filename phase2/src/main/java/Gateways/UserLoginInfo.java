package main.java.Gateways;

import java.io.*;

import main.java.UseCases.*;

/**
 * <h1>UserLoginInfo</h1>
 * Responsible for accessing database that stores user info and modifying their data
 */
public class UserLoginInfo implements Serializable{

    /**
     * The LoginUserManager object that stores the user information.
     */
    private LoginUserManager loginUserManager;

    /**
     * Stores the path to the database file
     */
    private final String dbPath;

    public UserLoginInfo(){
        dbPath = "phase2/src/main/java/DB/UserLoginInfo.ser";
        getFileUserLoginInfo();
    }

    public boolean createUser(String username, String password, String role){
        return loginUserManager.registerUser(username, password, role);
    }

    public boolean userExists(String username){
        return loginUserManager.checkUsername(username);
    }

    public String login(String username, String password){
        return loginUserManager.loginUser(username, password);
    }

    public boolean resetPassword(String username, String newPassword){
        return loginUserManager.resetPassword(username, newPassword);
    }

    public String getUserRole(String username){
        return loginUserManager.userRole(username);
    }

    /**
     * Clear contents of database file and save loginUserManager again to update
     */
    public void logout(){
        try{
            PrintWriter writer = new PrintWriter(dbPath);
            writer.print("");
            writer.close();
            setFileUserLoginInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update LoginUserManager with existing users' login information.
     */
    private void getFileUserLoginInfo(){
        try{
            InputStream file = new FileInputStream(dbPath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            loginUserManager = ((LoginUserManager) input.readObject());
            input.close();
        }catch(EOFException e){
            loginUserManager = new LoginUserManager();
        }catch(ClassNotFoundException e){
            System.err.println("Error when reading from database");
            loginUserManager = new LoginUserManager();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Save loginUserManager to filepath.
     */
    private void setFileUserLoginInfo() throws IOException {
        try {
            OutputStream file = new FileOutputStream(dbPath);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);

            output.writeObject(loginUserManager);
            output.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}