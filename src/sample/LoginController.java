package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import util.NetworkUtil;

import java.io.IOException;

public class LoginController {

    private Main main;
    public String reply;

    @FXML
    private Button buttonReset;

    @FXML
    private Button buttonLogin;

    @FXML
    private TextField textfieldClubName;

    @FXML
    private PasswordField passwordfieldPassword;

    @FXML
    void onActionLogin(ActionEvent event) throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 45454;
        NetworkUtil nU = new NetworkUtil(serverAddress, serverPort);
        main.netUtil = nU;
        //Image testImage = new Image("MohamedSalah.png");
        String userName = textfieldClubName.getText();
        String password = passwordfieldPassword.getText();
        LoginInformation loginInformation = new LoginInformation(userName, password);
        try {
            main.netUtil.write(loginInformation);
            reply = (String) main.netUtil.read();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        main.loginCheck(reply, userName);
    }

    @FXML
    void onActionReset(ActionEvent event) {
        textfieldClubName.setText("");
        passwordfieldPassword.setText("");
    }

    void setMain(Main main) {
        this.main = main;
    }

}
