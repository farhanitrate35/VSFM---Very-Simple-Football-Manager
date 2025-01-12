package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class MarketController {

    private Main main;
    private MenuController menuController;
    private Player player;

    private Image playerImage;
    private Image countryImage;

    @FXML
    private Button buttonBuyPlayer;

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
    private ImageView countryImgView;

    @FXML
    private Label labelAge;

    @FXML
    private Label labelHeight;

    @FXML
    private Label labelSalary;

    @FXML
    void onActionBuyPlayer() {
        BuyPlayerRequest buyPlayerRequest = new BuyPlayerRequest("BuyPlayer", player, menuController.getLabelUser());
        try {
            main.netUtil.write(buyPlayerRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Database.PlayerList2.add(main.playerJustBought);
        menuController.preparingMarket();
        menuController.showMarket();
    }

    public void setMarketCard(Player p){
        player = p;
        playerImage = new Image(p.imgPlayer());
        countryImage = new Image(p.imgCountry());
        labelName1.setText(p.firstName());
        labelName2.setText(p.lastName());
        labelJerseyNo.setText(Integer.toString(p.getNumber()));
        labelPosition.setText(p.getPosition());
        labelAge.setText("Age : " + p.getAge());
        labelHeight.setText("Height : " + p.getHeight());
        labelSalary.setText("$" + p.getSalary());
        playerImgView.setImage(playerImage);
        countryImgView.setImage(countryImage);
    }

    public void setMain(Main main) {
        this.main = main;
    }
    void setMenuController(MenuController menuController) { this.menuController = menuController; }
}
