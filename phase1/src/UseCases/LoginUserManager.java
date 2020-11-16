package UseCases;

import Entities.*;

import java.util.*;

public class LoginUserManager {
    private HashMap<String, Attendee> credentialsMap;

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
                return true;
            }
            if (role.equalsIgnoreCase("organizer")){
                credentialsMap.put(username, new Organizer(username, password));
                return true;
            }
            if (role.equalsIgnoreCase("speaker")){
                credentialsMap.put(username, new Speaker(username, password));
                return true;
            }
            else
                return false;
        }
    }

    public boolean loginUser(String username, String password){
        Attendee res = credentialsMap.get(username);

        //res is null if username is not found
        if (res != null && res.getPassword().equals(password)) {
            res.setLoggedIn(true);
            return true;
        }
        else
            return false;
    }

    public void logoutUser(String username){
        Attendee res = credentialsMap.get(username);

        if (res != null) {
            credentialsMap.get(username).setLoggedIn(false);    //will this crash if username is not found?
        }
    }

    public String userRole(String username){
        Attendee res = credentialsMap.get(username);

        if (res != null) {
            return credentialsMap.get(username).getRole();  //same for this one
        }
        else{
            return "null";
        }
    }

    public Attendee getAttendee(String username){
        if (credentialsMap.containsKey(username)) {
            return credentialsMap.get(username);
        }
        else{
            return null;
        }
    }

    public String getUsername(Attendee user){
        return user.getUsername();
    }
}