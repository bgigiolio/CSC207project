package Controllers;

import java.io.IOException;

public interface LoginMenu {
    String usernamePrompt();
    String passwordPrompt();
    boolean logReg(String username, String password, String role) throws IOException;
}
