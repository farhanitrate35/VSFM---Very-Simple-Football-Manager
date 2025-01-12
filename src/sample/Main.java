package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import util.NetworkUtil;

import java.io.IOException;
import java.util.List;

public class Main extends Application {

    private Stage stage;
    public Stage getStage() {
        return stage;
    }
    public static NetworkUtil netUtil;
    public boolean flag = false;
    public List<Country> countriesForCountryWisePlayerCount;
    public List<Player> transferMarket;
    ReadThreadMain readThreadMain;
    public Player playerJustBought;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        showLoginPage();
    }

    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("loginScene.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showMenuPage(String userName) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("menuScene.fxml"));
        Parent root = loader.load();

        // Loading the controller
        MenuController controller = loader.getController();
        controller.setMain(this);
        controller.setLabelUser(userName);
        controller.hideSearchFields();
        controller.showClubIcon();
        controller.hideLogoutBox();

        // Set the primary stage
        stage.setTitle("Football Club Manager");
        stage.setScene(new Scene(root, 1230, 700));
        stage.show();
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The club name and password that you provided are not correct.");
        alert.showAndWait();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void loginCheck(String reply, String user) {
        if(reply.equals("accepted")) {
            try {
                netUtil.write("makeClubDatabase");
                RespondClubDatabase respondClubDatabase = (RespondClubDatabase) netUtil.read();
                Database.PlayerList2 = respondClubDatabase.getClubPlayers();
                System.out.println("Club player list is ready!");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            //------------------------------------------------------------------------------------
            try {
                netUtil.write("makeCountryList");
                RespondCountryCount respondCountryCount = (RespondCountryCount) netUtil.read();
                countriesForCountryWisePlayerCount = respondCountryCount.getCountries();
                System.out.println("Country list is ready!");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            //------------------------------------------------------------------------------------
            readThreadMain = new ReadThreadMain(netUtil, this);
            try {
                showMenuPage(user);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else {
            showAlert();
        }
    }

    public void removePlayer(Player player) {
        Database.PlayerList2.remove(player);
    }
}
