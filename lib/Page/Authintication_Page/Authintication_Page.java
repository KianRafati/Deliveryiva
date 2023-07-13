package lib.Page.Authintication_Page;


import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lib.Page.Page;
import lib.Page.CustomerPage.CustomerPage;
import lib.Page.DeliveryPage.DeliveryPage;
import lib.Page.RestaurantAdminPage.RestaurantAdminPage;
import src.*;

public class Authintication_Page implements Page {
    Parent root;
    static String inputbase;
    static StringBuilder inputBuilder = new StringBuilder();


    @Override
    public void run(String input) {
        inputBuilder = new StringBuilder(inputbase);

        int counter = 0;
        for (Authintication_Page_Commmands command : Authintication_Page_Commmands.values()) {
            if (input.matches(command.content))
                break;
            counter++;
        }

        try {
            switch (counter) { // TODO handling wrong class casting
                case 0: // create restAdmin
                    String[] temp0 = input.split("\\s");
                    if (User.CreateUser(temp0[3], temp0[4], 1)) {
                        RestaurantAdminPage RestAdminPage = new RestaurantAdminPage((RestaurantAdmin) User.currUser);
                        RestAdminPage.previousPage = PageHandler.currPage;
                        RestAdminPage.getUser().setPage(RestAdminPage);
                        PageHandler.changePage(RestAdminPage);
                    } else
                        return;

                    break;
                case 1: // create customer
                    String[] temp1 = input.split("\\s");
                    if (User.CreateUser(temp1[2], temp1[3], 2)) {
                        CustomerPage customerPage = new CustomerPage((Customer) User.currUser);
                        customerPage.previousPage = PageHandler.currPage;
                        customerPage.getUser().setPage(customerPage);
                        PageHandler.changePage((Page) customerPage);
                    } else
                        return;

                    break;
                case 2: // create delivery
                    String[] temp2 = input.split("\\s");
                    if (User.CreateUser(temp2[2], temp2[3], 3)) {
                        DeliveryPage deliveryPage = new DeliveryPage((Delivery) User.currUser);
                        deliveryPage.previousPage = PageHandler.currPage;
                        deliveryPage.getUser().setPage(deliveryPage);
                        PageHandler.changePage((Page)deliveryPage);
                    } else
                        return;

                    break;
                case 3:// login restAdmin
                    String[] temp3 = input.split("\\s");
                    if (User.LoginUser(temp3[3], temp3[4])) {
                        RestaurantAdminPage RestAdminPage = new RestaurantAdminPage((RestaurantAdmin) User.currUser);
                        User.receiveRests(((RestaurantAdmin) User.currUser).user_id);
                        RestAdminPage.previousPage = PageHandler.currPage;
                        RestAdminPage.getUser().setPage(RestAdminPage);
                        PageHandler.changePage(RestAdminPage);
                    }
                    break;
                case 4: // login customer
                    String[] temp4 = input.split("\\s");
                    if (User.LoginUser(temp4[2], temp4[3])) {
                        CustomerPage customerPage = new CustomerPage((Customer) User.currUser);
                        User.receiveLocalRests(((Customer) User.currUser).location.getNum());
                        customerPage.previousPage = PageHandler.currPage;
                        customerPage.getUser().setPage(customerPage);
                        PageHandler.changePage((Page)customerPage);
                    }
                    break;
                case 5:// login delivery
                    String[] temp5 = input.split("\\s");
                    if (User.LoginUser(temp5[2], temp5[3])) {
                        DeliveryPage deliveryPage = new DeliveryPage((Delivery) User.currUser);
                        deliveryPage.previousPage = PageHandler.currPage;
                        deliveryPage.getUser().setPage(deliveryPage);
                        PageHandler.changePage((Page)deliveryPage);
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error! please retry your request");
        }
    }

    @Override
    public Parent getRoot() {
        try {
            root = FXMLLoader.load(getClass().getResource("AuthPageScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

}
