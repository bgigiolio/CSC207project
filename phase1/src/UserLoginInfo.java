import java.io.*;
import java.util.*;
// GATEWAY
public class UserLoginInfo implements Serializable{

    private HashMap<String, String> LoginInfo;

    public UserLoginInfo(){
        HashMap<String, String> LoginInfo = new HashMap<>();
    }

    public HashMap<String, String> getLoginInfo() {
        return LoginInfo;
    }

    public void addUserInfo(String username, String password){
        if(!LoginInfo.containsKey(username)){ LoginInfo.put(username, password); }
    }

    public void removeUserInfo(String username){
        LoginInfo.remove(username);
    }

    public HashMap<String, String> getFileUserLoginInfo(String filePath) throws IOException {
        try{
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            LoginInfo = (HashMap<String, String>) input.readObject();
            input.close();
        } catch (FileNotFoundException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Existing version is returned.");
        }
        return LoginInfo;
    }

    public void setFileUserLoginInfo(String filePath) throws IOException {
        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(this.LoginInfo);
        output.close();
    }
}