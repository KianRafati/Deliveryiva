package lib.Page.Authintication_Page;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AuthPageController {
    Stage stage;
    Authintication_Page authPage = new Authintication_Page();
    
    void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    private Button CustomerBtn;

    @FXML
    private Button RestAdminBtn;

    //==============================================

    @FXML
    private Button LoginBtn;

    @FXML
    private TextField UsernameField;
    
    @FXML
    private javafx.scene.control.PasswordField PasswordField;

    @FXML
    private Hyperlink registerHyperlink;

    @FXML
    void CustomerClicked(ActionEvent event) {
        Authintication_Page.inputBuilder.append("Customer");
        Authintication_Page.inputbase = "Customer";
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AuthPageScene2.fxml"));
            stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setTitle("Deliveryiva");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void DeliveryClicked(ActionEvent event) {
        Authintication_Page.inputBuilder.append("Delivery");
        Authintication_Page.inputbase = "Delivery";
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AuthPageScene2.fxml"));
            stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setTitle("Deliveryiva");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void RestAdminClicked(ActionEvent event) {
        Authintication_Page.inputbase = "restaurant admin";
        Authintication_Page.inputBuilder.append("restaurant admin");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AuthPageScene2.fxml"));
            stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setTitle("Deliveryiva");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Login(ActionEvent event){
        Authintication_Page.inputBuilder.append(" login ");
        Authintication_Page.inputBuilder.append(UsernameField.getText());
        Authintication_Page.inputBuilder.append(" ");
        Authintication_Page.inputBuilder.append(PasswordField.getText());
        authPage.run(Authintication_Page.inputBuilder.toString());
    }

    
    @FXML
    void Register(ActionEvent event) {
        Authintication_Page.inputBuilder.append(" add ");
        Authintication_Page.inputBuilder.append(UsernameField.getText());
        Authintication_Page.inputBuilder.append(" ");
        Authintication_Page.inputBuilder.append(PasswordField.getText());
        authPage.run(Authintication_Page.inputBuilder.toString());
    }
}
