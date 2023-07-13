package lib.Page.RestaurantPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import src.PageHandler;

public class RestaurantPageSceneController {
    RestaurantPage restaurantPage = (RestaurantPage) PageHandler.currPage;

    @FXML
    private Button BackBtn;

    @FXML
    private Button LogoutBtn;

    @FXML
    private Label RestNameLabel;

    @FXML
    private AnchorPane scrollAnchor;

    @FXML
    void Back(ActionEvent event) {
        restaurantPage.run("back");
    }

    @FXML
    void Logout(ActionEvent event) {

    }

}
