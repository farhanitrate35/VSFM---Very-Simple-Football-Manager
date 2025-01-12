package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CountryController {
    private Country country;
    private Image countryImage;

    @FXML
    private Label countryNameLabel;
    @FXML
    private Label countryPlayerCountLabel;
    @FXML
    private ImageView countryImgView;

    public void setCountryCard(Country _country) {
        country = _country;
        countryImage = new Image(_country.imgCountry());
        countryImgView.setImage(countryImage);
        countryNameLabel.setText(_country.getName());
        countryPlayerCountLabel.setText(Integer.toString(_country.getNumberOfPlayers()));
    }
}
