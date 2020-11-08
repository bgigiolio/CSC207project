import java.util.*;

public class LoginUserManager {
    private HashMap<Object, Attendee> credentialsMap = new HashMap<>();

    public LoginUserManager(){
        this.credentialsMap = new HashMap<>();
    }

    public boolean registerUser(String username, String password, String role){
        if (credentialsMap.containsKey(username)){
            return false;
        }
        else {
            if (role.equalsIgnoreCase("attendee")){
                credentialsMap.put(username, new Attendee(username, password));
                return true;}
            if (role.equalsIgnoreCase("organizer")){
                credentialsMap.put(username, new Organizer(username, password));
                return true;}
            if (role.equalsIgnoreCase("speaker")){
                credentialsMap.put(username, new Speaker(username, password));
                return true;}
            else{
                return false;
            }
        }
    }

    public boolean loginUser(String username, String password){
        if (credentialsMap.containsKey(username)) {
            credentialsMap.get(username).setLoggedIn(true);
            return credentialsMap.get(username).password.equals(password);
        }
        else{
            return false;
        }
    }

    public void logoutUser(String username){
        credentialsMap.get(username).setLoggedIn(false);
    }

    public String userRole(String username){
        return credentialsMap.get(username).getRole();
    }
}