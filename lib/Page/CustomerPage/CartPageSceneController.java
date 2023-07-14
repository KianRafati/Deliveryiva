package lib.Page.CustomerPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import src.PageHandler;
import src.User;

public class CartPageSceneController {
    CustomerPage previousPage;

    @FXML
    private Button BackBtn;

    @FXML
    private Label CustomerPageLabel;

    @FXML
    private Button LogoutBtn;

    @FXML
    private AnchorPane scrollAnchor;

    @FXML
    void Back(ActionEvent event) {
        PageHandler.changePage(previousPage);
    }

    @FXML
    void Logout(ActionEvent event) {
        User.Logout();
    }

    void init(CustomerPage page){
        previousPage = page;
        
    }

}
