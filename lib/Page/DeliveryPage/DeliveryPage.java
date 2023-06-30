package lib.Page.DeliveryPage;

import lib.Page.Page;
import src.Delivery;
import src.RestaurantAdmin;

public class DeliveryPage extends Page {
    private Delivery delivery;

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
    
}
