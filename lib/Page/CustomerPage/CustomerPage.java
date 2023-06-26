package lib.Page.CustomerPage;

import lib.Page.Page;

public class CustomerPage extends Page {

    private static CustomerPage instance = null;

    private CustomerPage() {
    }

    public static CustomerPage getInstance() {
        if (instance == null)
            instance = new CustomerPage();
        return instance;
    }


    @Override
    public void run(String input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
    
}
