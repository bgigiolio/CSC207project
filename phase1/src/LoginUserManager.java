import java.util.*;

public class LoginUserManager {
    private HashMap<Object, Attendee> credentialsMap = new HashMap<>();

    public LoginUserManager(){
        this.credentialsMap = new HashMap<>();
    }

    public boolean registerUser(String username, String password){
        Attendee user = new Attendee(username, password);
        String id = user.userid;
        if (credentialsMap.containsKey(username)){
            return false;
        }
        else {
            credentialsMap.put(username, new Attendee(username, password));
            return true;
        }
    }

    public boolean loginUser(String username, String password){
        return credentialsMap.get(username).password.equals(password);
    }
}