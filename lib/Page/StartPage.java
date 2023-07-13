package lib.Page;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
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

        primaryStage = stage;
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
