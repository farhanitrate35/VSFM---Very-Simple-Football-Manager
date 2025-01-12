package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayerController {

    private Main main;
    private MenuController menuController;

    private Player player;

    private Image playerImage;
    private Image clubImage;
    private Image countryImage;

    @FXML
    private Button buttonSellPlayer;

    @FXML
    private Label labelJerseyNo;

    @FXML
    private ImageView playerImgView;

    @FXML
    private Label labelName1;

    @FXML
    private Label labelName2;

    @FXML
    private Label labelPosition;

    @FXML
    private ImageView clubImgView;

    @FXML
    private ImageView countryImgView;

    @FXML
    private Label labelAge;

    @FXML
    private Label labelHeight;

    @FXML
    private Label labelSalary;

    @FXML
    void onActionSellPlayer(ActionEvent event) throws Exception {
        SellPlayerRequest sellPlayerRequest = new SellPlayerRequest(player, "SellPlayer");
        main.netUtil.write(sellPlayerRequest);
        main.removePlayer(player);
        menuController.searchOptions();
    }

    public void setPlayerCard(Player p){
        player = p;
        playerImage = new Image(p.imgPlayer());
        countryImage = new Image(p.imgCountry());
        clubImage = new Image(p.imgClub());
        labelName1.setText(p.firstName());
        labelName2.setText(p.lastName());
        labelJerseyNo.setText(Integer.toString(p.getNumber()));
        labelPosition.setText(p.getPosition());
        labelAge.setText("Age : " + p.getAge());
        labelHeight.setText("Height : " + p.getHeight());
        labelSalary.setText("$" + p.getSalary());
        playerImgView.setImage(playerImage);
        countryImgView.setImage(countryImage);
        clubImgView.setImage(clubImage);
    }

    void setMain(Main main) {
        this.main = main;
    }
    void SetMenuController(MenuController menuController) { this.menuController = menuController; }

}
