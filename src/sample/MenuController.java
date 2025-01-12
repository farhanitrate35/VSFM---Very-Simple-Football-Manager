package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class MenuController {

    private Main main;
    void setMain(Main main) {
        this.main = main;
    }
    Main getMain() { return main; }
    Database DB = new Database();

    private String optionSelected;
    public int refreshChecker = 0;

    private List<Player> searchArray;
    private List<Country> searchCountryArray;

    private final String defaultStyle = "-fx-background-color : #3f2b63; -fx-text-fill : #c9c9c9";
    private final String changedStyle = "-fx-background-color : #434343; -fx-text-fill : #ffffff";

    @FXML
    private Label labelUser;

    @FXML
    private Label labelSearch;

    @FXML
    private Label labelLogoutConfirmation;

    @FXML
    private ImageView clubIcon;

    @FXML
    private GridPane gridPane;

    @FXML
    private VBox logoutConfirmationDialogue;
    @FXML
    private Button buttonDeclineLogout;
    @FXML
    private Button buttonConfirmLogout;

    @FXML
    private Button buttonBuyPlayers;

    @FXML
    private Button buttonLogout;

    @FXML
    private Button buttonRefresh;

    @FXML
    private Button buttonSearch;

    @FXML
    private Button buttonSearchPlayerName;

    @FXML
    private Button buttonSearchCountry;

    @FXML
    private Button buttonSearchPosition;

    @FXML
    private Button buttonSearchSalaryRange;

    @FXML
    private Button buttonCountryWisePlayerCount;

    @FXML
    private Button buttonMaximumSalaryPlayer;

    @FXML
    private Button buttonMaximumAgePlayer;

    @FXML
    private Button buttonMaximumHeightPlayer;

    @FXML
    private TextField textfieldSecondarySearch;

    @FXML
    private TextField textfieldPrimarySearch;

    @FXML
    private void setAllDefaultStyle(){
        buttonMaximumAgePlayer.setStyle(defaultStyle);
        buttonCountryWisePlayerCount.setStyle(defaultStyle);
        buttonMaximumHeightPlayer.setStyle(defaultStyle);
        buttonSearchCountry.setStyle(defaultStyle);
        buttonMaximumSalaryPlayer.setStyle(defaultStyle);
        buttonSearchPlayerName.setStyle(defaultStyle);
        buttonSearchPosition.setStyle(defaultStyle);
        buttonSearchSalaryRange.setStyle(defaultStyle);
    }

    public void showClubIcon(){
        String name1 = labelUser.getText().replace(" ", "");
        Image image = new Image(name1 + ".png");
        clubIcon.setImage(image);
    }

    public void showLogoutBox(){
        //logoutConfirmationDialogue.setVisible(true);
        labelLogoutConfirmation.setVisible(true);
        buttonConfirmLogout.setVisible(true);
        buttonDeclineLogout.setVisible(true);
    }
    public void hideLogoutBox(){
        labelLogoutConfirmation.setVisible(false);
        buttonConfirmLogout.setVisible(false);
        buttonDeclineLogout.setVisible(false);
    }

    public void hideSearchFields(){
        hidePrimary();
        hideSecondary();
        buttonSearch.setVisible(false);
        buttonRefresh.setVisible(false);
    }

    public void prepareSearchFields() {
        setAllDefaultStyle();
        buttonSearch.setVisible(true);
        buttonRefresh.setVisible(true);
        //textfieldSecondarySearch.setText("");
        //textfieldPrimarySearch.setText("");
    }

    @FXML
    private void showPrimary(){
        textfieldPrimarySearch.setVisible(true);
    }
    @FXML
    private void hidePrimary(){
        textfieldPrimarySearch.setVisible(false);
    }
    @FXML
    private void showSecondary(){
        textfieldSecondarySearch.setVisible(true);
    }
    @FXML
    private void hideSecondary(){
        textfieldSecondarySearch.setVisible(false);
    }
    @FXML
    private void disableSecondary() {
        textfieldSecondarySearch.setDisable(true);
        textfieldSecondarySearch.setPromptText("");
    }
    @FXML
    private void enableSecondary() {
        textfieldSecondarySearch.setDisable(false);
        textfieldSecondarySearch.setPromptText("Max Range");
    }
    @FXML
    private void disablePrimary() {
        textfieldPrimarySearch.setDisable(true);
    }
    @FXML
    private void enablePrimary() {
        textfieldPrimarySearch.setDisable(false);
    }

    @FXML
    void onActionRefresh(ActionEvent event) {
        if(refreshChecker == 1) { searchOptions(); }
        else if(refreshChecker == 2) {
            preparingMarket();
            showMarket();
        }
    }

    @FXML
    void onActionSearch(ActionEvent event){
        refreshChecker = 1;
        searchOptions();
    }
    void searchOptions() {
        if(optionSelected.equals(buttonSearchPlayerName.getText())){
            searchArray = DB.searchName(textfieldPrimarySearch.getText());
        }
        else if(optionSelected.equals(buttonSearchCountry.getText())){
            searchArray = DB.searchCountry(textfieldPrimarySearch.getText());
        }
        else if(optionSelected.equals(buttonSearchPosition.getText())){
            searchArray = DB.searchPosition(textfieldPrimarySearch.getText());
        }
        else if(optionSelected.equals(buttonSearchSalaryRange.getText())){
            searchArray = DB.searchSalary(Double.parseDouble(textfieldPrimarySearch.getText()),Double.parseDouble(textfieldSecondarySearch.getText()));
        }
        else if(optionSelected.equals(buttonCountryWisePlayerCount.getText())){
            searchCountryArray = main.countriesForCountryWisePlayerCount;
        }
        else if(optionSelected.equals(buttonMaximumSalaryPlayer.getText())){
            searchArray = DB.searchMaxSalary();
        }
        else if(optionSelected.equals(buttonMaximumAgePlayer.getText())){
            searchArray = DB.searchMaxAge();
        }
        else if(optionSelected.equals(buttonMaximumHeightPlayer.getText())){
            searchArray = DB.searchMaxHeight();
        }
        gridPane.getChildren().clear();
        if(optionSelected.equals(buttonCountryWisePlayerCount.getText())){
            showCountryList();
        }
        else {
            showList(searchArray);
        }
    }

    @FXML
    void setLabelUser(String userName){
        labelUser.setText(userName);
    }
    public String getLabelUser() { return labelUser.getText(); }

    @FXML
    void onClickCountryWisePlayerCount(ActionEvent event) {
        optionSelected = buttonCountryWisePlayerCount.getText();
        prepareSearchFields();
        showPrimary();
        showSecondary();
        disablePrimary();
        disableSecondary();
        textfieldPrimarySearch.setPromptText("");
        buttonCountryWisePlayerCount.setStyle(changedStyle);
    }

    @FXML
    void onClickMaximumAgePlayer(ActionEvent event) {
        optionSelected = buttonMaximumAgePlayer.getText();
        prepareSearchFields();
        showPrimary();
        showSecondary();
        disablePrimary();
        disableSecondary();
        textfieldPrimarySearch.setPromptText("");
        buttonMaximumAgePlayer.setStyle(changedStyle);
    }

    @FXML
    void onClickMaximumHeightPlayer(ActionEvent event) {
        optionSelected = buttonMaximumHeightPlayer.getText();
        prepareSearchFields();
        buttonMaximumHeightPlayer.setStyle(changedStyle);
        showPrimary();
        showSecondary();
        disablePrimary();
        disableSecondary();
        textfieldPrimarySearch.setPromptText("");
    }

    @FXML
    void onClickMaximumSalaryPlayer(ActionEvent event) {
        optionSelected = buttonMaximumSalaryPlayer.getText();
        prepareSearchFields();
        buttonMaximumSalaryPlayer.setStyle(changedStyle);
        showPrimary();
        showSecondary();
        disablePrimary();
        disableSecondary();
        textfieldPrimarySearch.setPromptText("");
    }

    @FXML
    void onClickSearchByCountry(ActionEvent event) {
        optionSelected = buttonSearchCountry.getText();
        prepareSearchFields();
        buttonSearchCountry.setStyle(changedStyle);
        showPrimary();
        enablePrimary();
        showSecondary();
        disableSecondary();
        textfieldPrimarySearch.setPromptText("Players' country of origin");
    }

    @FXML
    void onClickSearchByPlayerName(ActionEvent event) {
        optionSelected = buttonSearchPlayerName.getText();
        prepareSearchFields();
        buttonSearchPlayerName.setStyle(changedStyle);
        showPrimary();
        enablePrimary();
        showSecondary();
        disableSecondary();
        textfieldPrimarySearch.setPromptText("Player Name");
    }

    @FXML
    void onClickSearchByPosition(ActionEvent event) {
        optionSelected = buttonSearchPosition.getText();
        prepareSearchFields();
        buttonSearchPosition.setStyle(changedStyle);
        showPrimary();
        enablePrimary();
        showSecondary();
        disableSecondary();
        textfieldPrimarySearch.setPromptText("Players' position");
    }

    @FXML
    void onClickSearchBySalaryRange(ActionEvent event) {
        optionSelected = buttonSearchSalaryRange.getText();
        prepareSearchFields();
        buttonSearchSalaryRange.setStyle(changedStyle);
        showPrimary();
        enablePrimary();
        showSecondary();
        enableSecondary();
        textfieldPrimarySearch.setPromptText("Min Range");
    }

    @FXML
    void onClickMarketPlace(ActionEvent event) {
        refreshChecker = 2;
        preparingMarket();
        showMarket();
    }

    void showList(List<Player> listPlayers) {
        int row = 0;
        try {
            for(int i = 0; i < listPlayers.size(); i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("playerScene.fxml"));
                Parent playerCard = loader.load();
                PlayerController controller = loader.getController();
                controller.setMain(this.main);
                controller.SetMenuController(this);
                controller.setPlayerCard(listPlayers.get(i));
                gridPane.add(playerCard, 0, row++);

                gridPane.setMargin(playerCard, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void showCountryList() {
        int row = 0;
        try {
            for(Country c : searchCountryArray) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("countryScene.fxml"));
                Parent countryCard = loader.load();
                CountryController controller = loader.getController();
                controller.setCountryCard(c);
                gridPane.add(countryCard, 0, row++);
                gridPane.setMargin(countryCard, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void preparingMarket() {
        hideSearchFields();
        buttonRefresh.setVisible(true);
        try {
            main.netUtil.write("showTransferMarket");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!main.flag) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        if(main.flag) notifyAll();
        gridPane.getChildren().clear();
    }

    void showMarket() {
        int row = 0;
        if(!main.flag) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            for(int i = 0; i < main.transferMarket.size(); i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("marketScene.fxml"));
                Parent MarketCard = loader.load();
                MarketController controller = loader.getController();
                controller.setMain(this.main);
                controller.setMenuController(this);
                controller.setMarketCard(main.transferMarket.get(i));
                gridPane.add(MarketCard, 0, row++);
                gridPane.setMargin(MarketCard, new Insets(10));
            }
        } catch (Exception e) {
            System.out.println("Exception while showing Transfer Market: " + e);
        }
        main.flag = false;
    }

    public void onClickLogout(ActionEvent actionEvent) {
        showLogoutBox();
    }
    public void onClickConfirmLogout() throws Exception {
        //Database.closeMainDatabase();
        main.netUtil.closeConnection();
        main.showLoginPage();
    }
    public void onClickDismissLogout(ActionEvent actionEvent) {
        hideLogoutBox();
    }
}
