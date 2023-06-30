package src;

import lib.Page.Page;
import lib.Page.CustomerPage.CustomerPage;

public class Customer extends User {
    private CustomerPage customerPage;

    Customer(String name,String password){
        this.username = name;
        this.password = password;
    }

    public void setPage(CustomerPage customerPage) {
        this.customerPage = customerPage;
    }

    public Page getPage() {
        return this.customerPage;
    }
    
}
