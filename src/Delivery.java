package src;

import lib.Page.Page;
import lib.Page.DeliveryPage.DeliveryPage;

public class Delivery extends User {
    DeliveryPage deliveryPage;
    
    Delivery(String name,String password){
        this.username = name;
        this.password = password;
    }

    public void setPage(DeliveryPage deliveryPage) {
        this.deliveryPage = deliveryPage;
    }

    public Page getPage() {
        return this.deliveryPage;
    }
    
}
