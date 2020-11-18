package Gateways;

import java.io.*;
import java.util.*;
import UseCases.*;
import Controllers.*;
// GATEWAY
public class UserLoginInfo implements Serializable{

    private HashMap<String, ArrayList<String>> LoginInfo;

    public UserLoginInfo(){
        HashMap<String, ArrayList<String>> LoginInfo = new HashMap<>();
    }

    public HashMap<String, ArrayList<String>> getLoginInfo() {
        return LoginInfo;
    }

    public void setLoginInfo(HashMap<String, ArrayList<String>> LoginInfo) {
        this.LoginInfo = LoginInfo;
    }

    public void addUserInfo(String username, String password, String role){
        HashMap<String, ArrayList<String>> newline = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        list.add(password);
        list.add(role);
        newline.put(username, list);
        if (LoginInfo == null) {
            LoginInfo = newline;
        } else { LoginInfo.put(username, list); }
    }

    public void removeUserInfo(String username){
        LoginInfo.remove(username);
    }

    public HashMap<String, ArrayList<String>> getFileUserLoginInfo(String filePath) throws IOException {
        try{
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            this.LoginInfo = (HashMap<String, ArrayList<String>>) input.readObject();
            input.close();
        } catch (FileNotFoundException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Existing version is returned.");
        }
        return this.LoginInfo;
    }

    public void setFileUserLoginInfo(String filePath) throws IOException {
        try {
            OutputStream file = new FileOutputStream(filePath);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);

            output.writeObject(this.LoginInfo);
            output.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}