package main.java.Gateways;

import java.io.*;

import main.java.UseCases.*;

// GATEWAY
@SuppressWarnings("DuplicatedCode")
public class UserLoginInfo implements Serializable{

    /**
     * The LoginUserManager object that stores the user information.
     */
    private LoginUserManager loginUserManager;

    /**
     * Construct an UserLoginInfo object.
     * Initialized with a new LoginUserManager.
     */
    public UserLoginInfo() {this.loginUserManager = new LoginUserManager(); }

    /**
     * Return loginUserManager.
     *
     * @return loginUserManager of this UserLoginInfo object.
     */
    public LoginUserManager getLoginUserManager() { return this.loginUserManager; }

    /**
     * Set loginUserManager.
     *
     * @param loginUserManager of this UserLoginInfo object.
     */
    public void setLoginUserManager(LoginUserManager loginUserManager) {
        this.loginUserManager = loginUserManager;
    }

    /**
     * Load filepath and update loginUserManager with existing users' login information.
     *
     * @return loginUserManager object that stores the existing users' login information.
     */
    public LoginUserManager getFileUserLoginInfo(String filePath) throws IOException {

        try{
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            loginUserManager = ((LoginUserManager) input.readObject());
            input.close();
        } catch (FileNotFoundException | ClassNotFoundException e){
            e.printStackTrace();
        } catch (EOFException ignored) {}
        return loginUserManager;
    }

    /**
     * Save loginUserManager to filepath.
     */
    public void setFileUserLoginInfo(String filePath) throws IOException {
        try {
            OutputStream file = new FileOutputStream(filePath);
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