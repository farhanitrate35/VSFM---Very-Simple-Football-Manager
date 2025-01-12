package sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LoginInformation implements Serializable {
    private String username;
    private String password;

    LoginInformation(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static List<LoginInformation> presentClubs() {
        List<LoginInformation> loginList = new ArrayList<>();
        LoginInformation x1 = new LoginInformation("Manchester United", "ManU1");
        loginList.add(x1);
        LoginInformation x2 = new LoginInformation("Manchester City", "ManC2");
        loginList.add(x2);
        LoginInformation x3 = new LoginInformation("Chelsea", "CHE3");
        loginList.add(x3);
        LoginInformation x4 = new LoginInformation("Liverpool", "LFC4");
        loginList.add(x4);
        LoginInformation x5 = new LoginInformation("Arsenal", "ARS5");
        loginList.add(x5);
        return loginList;
    }
}
