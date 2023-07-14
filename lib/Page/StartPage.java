package lib.Page;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lib.Page.Authintication_Page.Authintication_Page;
import src.PageHandler;
import src.User;

public class StartPage extends Application implements Page {
    private Parent root;
    private Stage primaryStage;
    public static boolean loged_in = false;

    @Override
    public void run(String input) {
        launch();
    }

    @Override
    public Parent getRoot() {
        return this.root;
    }

    @Override
    public void start(Stage stage) throws Exception {
        if(!loged_in)
            root = FXMLLoader.load(getClass().getResource("StartPageScene.fxml"));
        else    
            root = User.currUser.getPage().getRoot();
            
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(this::handleKeyPressed);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                // Display a confirmation dialog
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Confirm Exit");
                alert.setContentText("Are you sure you want to exit?");

                // Customize the alert buttons
                ButtonType buttonYes = new ButtonType("Yes");
                ButtonType buttonNo = new ButtonType("No");

                alert.getButtonTypes().setAll(buttonYes, buttonNo);

                // Wait for user response
                ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
                
                if (result == buttonYes) {
                    // Perform any necessary clean-up or closing operations here
                    
                    PageHandler.terminate();
                    // Close the application gracefully
                    System.exit(0);
                } else {
                    // Cancel the close request
                    event.consume();
                }
            }
        });


        primaryStage = stage;
        PageHandler.primaryStage = stage;
        primaryStage.setResizable(false);
        Image icon = new Image("E:\\Sharif University of Technology\\2th semester\\OOP\\Project\\Deliveryiva\\Deliveryiva\\lib\\Assets\\Deliveryiva_logo.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Deliveryiva");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleKeyPressed(KeyEvent event) {
        PageHandler.changePage((Page) new Authintication_Page(), event);
    }

    public void startStage(boolean login){
        this.loged_in = login;
    }

}
