package lib.Page.DeliveryPage;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.stage.Stage;
import lib.Page.Page;
import src.Delivery;
import src.RestaurantAdmin;

public class DeliveryPage extends Page {
    private Delivery delivery;
    public Page previousPage;

    public DeliveryPage(Delivery delivery){
        this.delivery = delivery;
    }

    @Override
    public void run(String input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    public Delivery getUser() {
        return delivery;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    @Override
    public FXMLLoader getLoader() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRoot'");
    }

    @Override
    public void start(Stage arg0) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }
    
}
