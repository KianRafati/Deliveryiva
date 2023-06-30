package lib.Page.CustomerPage;

import lib.Page.Page;
import src.Customer;
import src.RestaurantAdmin;

public class CustomerPage extends Page {
    private Customer customer;

    public CustomerPage(Customer customer){
        this.customer = customer;
    }

    @Override
    public void run(String input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    public Customer getUser() {
        return customer;
    }
    
}
