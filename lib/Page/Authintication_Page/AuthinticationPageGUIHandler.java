package lib.Page.Authintication_Page;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AuthinticationPageGUIHandler extends Application {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("AuthPageScene.fxml"));

    public void launchGUI() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = loader.load();
        AuthPageController controller = loader.getController();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public Parent getLoader(){
        try {
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
