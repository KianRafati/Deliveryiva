package lib.Page.DeliveryPage;

import lib.Page.Page;

public class DeliveryPage extends Page {

    private static DeliveryPage instance = null;

    private DeliveryPage() {
    }

    public static DeliveryPage getInstance() {
        if (instance == null)
            instance = new DeliveryPage();
        return instance;
    }


    @Override
    public void run(String input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
    
}
