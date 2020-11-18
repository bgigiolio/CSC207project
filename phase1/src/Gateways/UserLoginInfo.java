package Gateways;

import java.io.*;
import java.util.*;

import Entities.Attendee;
import UseCases.*;
import Controllers.*;
// GATEWAY
public class UserLoginInfo implements Serializable{

    private LoginUserManager loginUserManager;

    public UserLoginInfo() {this.loginUserManager = new LoginUserManager(); }

    public LoginUserManager getLoginUserManager() { return this.loginUserManager; }

    public void setLoginUserManager(LoginUserManager loginUserManager) {
        this.loginUserManager = loginUserManager;
    }

    public LoginUserManager getFileUserLoginInfo(String filePath) throws IOException {
        try{
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            loginUserManager = ((LoginUserManager) input.readObject());
            input.close();
        } catch (FileNotFoundException | ClassNotFoundException | EOFException | InvalidClassException e){
            this.setFileUserLoginInfo(filePath);
        }
        return loginUserManager;
    }

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